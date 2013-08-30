Ext.ns("BookBorrowView");
var BookBorrowView = function() {
	return new Ext.Panel(
			{
				id : "BookBorrowView",
				title : "图书借阅列表",
				iconCls : "menu-book-borrow",
				layout : "anchor",
				autoScroll : true,
				items : [
						new Ext.FormPanel(
								{
									id : "BookBorrowSearchForm",
									height : 40,
									frame : false,
									border : false,
									layout : "hbox",
									layoutConfig : {
										padding : "5",
										align : "middle"
									},
									defaults : {
										xtype : "label",
										margins : {
											top : 0,
											right : 4,
											bottom : 4,
											left : 4
										}
									},
									items : [
											{
												text : "请输入查询条件:"
											},
											{
												text : "借出图书名称"
											},
											{
												xtype : "textfield",
												name : "Q_bookName_S_LK"
											},
											{
												text : "借出图书的ISBN"
											},
											{
												xtype : "textfield",
												name : "Q_borrowIsbn_S_LK"
											},
											{
												xtype : "button",
												text : "查询",
												iconCls : "search",
												handler : function() {
													var a = Ext
															.getCmp("BookBorrowSearchForm");
													var b = Ext
															.getCmp("BookBorrowGrid");
													if (a.getForm().isValid()) {
														a
																.getForm()
																.submit(
																		{
																			waitMsg : "正在提交查询",
																			url : __ctxPath
																					+ "/admin/listBookBorRet.do",
																			success : function(
																					d,
																					e) {
																				var c = Ext.util.JSON
																						.decode(e.response.responseText);
																				b
																						.getStore()
																						.loadData(
																								c);
																			}
																		});
													}
												}
											} ]
								}), this.setup() ]
			});
};
BookBorrowView.prototype.setup = function() {
	return this.grid();
};
BookBorrowView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "recordId",
							dataIndex : "recordId",
							hidden : true
						},
						{
							header : "登记人",
							dataIndex : "registerName"
						},
						{
							header : "借阅人",
							dataIndex : "fullname"
						},
						{
							header : "借出图书名称",
							dataIndex : "bookName"
						},
						{
							header : "借出图书ISBN",
							dataIndex : "borrowIsbn"
						},
						{
							header : "借出时间",
							dataIndex : "borrowTime"
						},
						{
							header : "应还时间",
							dataIndex : "returnTime",
							renderer : function(e) {
								return e.substring(0, 10);
							}
						},
						{
							header : "管理",
							dataIndex : "recordId",
							sortable : false,
							width : 50,
							renderer : function(h, g, e, k, f) {
								var j = e.data.recordId;
								var i = "";
								if (isGranted("_BookBorrowEdit")) {
									i = '<button title="编辑" value=" " class="btn-edit" onclick="BookBorrowView.edit(' + j + ')"></button>';
								}
								if (isGranted("_BookReturn")) {
									i += '<button title="归还" value=" " class="menu-book-return" onclick="BookBorrowView.returnB(' + j + ')"></button>';
								}
								return i;
							}
						} ],
				defaults : {
					sortable : true,
					menuDisabled : false,
					width : 100
				}
			});
	var b = this.store();
	b.load( {
		params : {
			start : 0,
			limit : 25
		}
	});
	var c = new Ext.grid.GridPanel( {
		id : "BookBorrowGrid",
		tbar : this.topbar(),
		store : b,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		autoHeight : true,
		cm : a,
		sm : d,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		bbar : new Ext.PagingToolbar( {
			pageSize : 25,
			store : b,
			displayInfo : true,
			displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
			emptyMsg : "当前没有记录"
		})
	});
	c.addListener("rowdblclick", function(g, f, h) {
		g.getSelectionModel().each(function(e) {
			if (isGranted("_BookBorrowEdit")) {
				BookBorrowView.edit(e.data.recordId);
			}
		});
	});
	return c;
};
BookBorrowView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/admin/listBorrowBookBorRet.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "recordId",
				type : "int"
			}, {
				name : "bookSnId",
				mapping : "bookSn.bookSnId"
			}, "borrowTime", "returnTime", "lastReturnTime", "borrowIsbn",
					"bookName", "registerName", "fullname" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("recordId", "desc");
	return a;
};
BookBorrowView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "BookBorrowFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : []
	});
	if (isGranted("_BookBorrowAdd")) {
		a.add(new Ext.Button( {
			iconCls : "btn-add",
			text : "添加借出记录 ",
			handler : function() {
				new BookBorrowForm();
			}
		}));
	}
	return a;
};
BookBorrowView.edit = function(a) {
	new BookBorrowForm(a);
};
BookBorrowView.returnB = function(a) {
	new BookReturnForm(a);
	Ext.getCmp("ReturnFormButtonSel").hide();
	Ext.getCmp("ReturnFormButtonCle").hide();
};