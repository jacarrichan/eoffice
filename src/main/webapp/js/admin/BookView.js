Ext.ns("BookView");
var BookView = function() {
};
BookView.prototype.setTypeId = function(a) {
	this.typeId = a;
	BookView.typeId = a;
};
BookView.prototype.getTypeId = function() {
	return this.typeId;
};
BookView.prototype.getView = function() {
	return new Ext.Panel( {
		id : "BookView",
		title : "图书列表",
		region : "center",
		layout : "anchor",
		autoScroll : true,
		items : [ new Ext.FormPanel( {
			id : "BookSearchForm",
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
			items : [ {
				text : "请输入查询条件:"
			}, {
				text : "书名"
			}, {
				xtype : "textfield",
				name : "Q_bookName_S_LK"
			}, {
				text : "作者"
			}, {
				xtype : "textfield",
				name : "Q_author_S_LK"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var a = Ext.getCmp("BookSearchForm");
					var b = Ext.getCmp("BookGrid");
					if (a.getForm().isValid()) {
						$search( {
							searchPanel : a,
							gridPanel : b
						});
					}
				}
			} ]
		}), this.setup() ]
	});
};
BookView.prototype.setup = function() {
	return this.grid();
};
BookView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "bookId",
							dataIndex : "bookId",
							hidden : true
						},
						{
							header : "类别",
							dataIndex : "typeName"
						},
						{
							header : "书名",
							dataIndex : "bookName"
						},
						{
							header : "作者",
							dataIndex : "author"
						},
						{
							header : "ISBN号",
							dataIndex : "isbn"
						},
						{
							header : "存放地点",
							dataIndex : "location"
						},
						{
							header : "所属部门",
							dataIndex : "department"
						},
						{
							header : "数量",
							dataIndex : "amount",
							width : 50
						},
						{
							header : "在库数",
							dataIndex : "leftAmount",
							width : 60
						},
						{
							header : "管理",
							dataIndex : "bookId",
							sortable : false,
							width : 100,
							renderer : function(i, h, e, l, g) {
								var k = e.data.bookId;
								var f = e.data.leftAmount;
								var j = "";
								if (isGranted("_BookDel")) {
									j = '<button title="删除" value=" " class="btn-del" onclick="BookView.remove(' + k + ')"></button>';
								}
								if (isGranted("_BookEdit")) {
									j += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="BookView.edit(' + k + ')"></button>';
								}
								if (isGranted("_BookBorrowAdd")) {
									if (f != 0) {
										j += '&nbsp;<button title="借阅该书" value=" " class="menu-book-borrow" onclick="BookView.borrow(' + k + ')"></button>';
									}
								}
								return j;
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
		id : "BookGrid",
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
			if (isGranted("_BookEdit")) {
				BookView.edit(e.data.bookId);
			}
		});
	});
	return c;
};
BookView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/admin/listBook.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "bookId",
				type : "int"
			}, {
				name : "typeName",
				mapping : "bookType.typeName"
			}, "bookName", "author", "isbn", "publisher", "price", "location",
					"department", "amount", "leftAmount" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("bookId", "desc");
	return a;
};
BookView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "BookFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : []
	});
	if (isGranted("_BookAdd")) {
		a.add(new Ext.Button( {
			iconCls : "btn-add",
			text : "添加图书",
			handler : function() {
				new BookForm();
				Ext.getCmp("BookForm").remove("bookSnContainer");
				Ext.getCmp("amoutContainer").remove("bookAmoutButton");
			}
		}));
	}
	if (isGranted("_BookDel")) {
		a.add(new Ext.Button( {
			iconCls : "btn-del",
			text : "删除图书",
			handler : function() {
				var d = Ext.getCmp("BookGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.bookId);
				}
				BookView.remove(e);
			}
		}));
	}
	return a;
};
BookView.remove = function(b) {
	var a = Ext.getCmp("BookGrid");
	Ext.Msg.confirm("信息确认", "会把图书的借阅归还记录和ISBN一起删除，您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelBook.do",
				params : {
					ids : b
				},
				method : "post",
				success : function() {
					Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
					a.getStore().reload( {
						params : {
							start : 0,
							limit : 25
						}
					});
				}
			});
		}
	});
};
BookView.edit = function(a) {
	new BookForm(a);
};
BookView.borrow = function(a) {
	new BookBorrowForm(null, a);
	Ext.Ajax.request( {
		url : __ctxPath + "/admin/getBook.do?bookId=" + a,
		method : "post",
		success : function(d) {
			var b = Ext.util.JSON.decode(d.responseText);
			var e = Ext.getCmp("bookName");
			e.setValue(b.data.bookName);
			var c = Ext.getCmp("borrowIsbn").getStore();
			c.reload( {
				params : {
					bookId : a
				}
			});
		}
	});
};