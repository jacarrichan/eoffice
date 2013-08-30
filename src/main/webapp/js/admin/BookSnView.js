Ext.ns("BookSnView");
var BookSnView = function() {
	return new Ext.Panel( {
		id : "BookSnView",
		title : "图书SN列表",
		autoScroll : true,
		items : [ new Ext.FormPanel( {
			id : "BookSnSearchForm",
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
				text : "图书ID"
			}, {
				xtype : "textfield",
				name : "Q_bookId_S_LK"
			}, {
				text : "图书SN号"
			}, {
				xtype : "textfield",
				name : "Q_bookSN_S_LK"
			}, {
				text : "借阅状态"
			}, {
				xtype : "textfield",
				name : "Q_status_S_LK"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var a = Ext.getCmp("BookSnSearchForm");
					var b = Ext.getCmp("BookSnGrid");
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
BookSnView.prototype.setup = function() {
	return this.grid();
};
BookSnView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "bookSnId",
							dataIndex : "bookSnId",
							hidden : true
						},
						{
							header : "书名",
							dataIndex : "bookName"
						},
						{
							header : "图书SN号",
							dataIndex : "bookSN"
						},
						{
							header : "借阅状态",
							dataIndex : "status",
							renderer : function(e) {
								if (e == "0") {
									return "未借出";
								} else {
									if (e == "1") {
										return "已借出";
									} else {
										if (e == "2") {
											return "预订";
										} else {
											return "注销";
										}
									}
								}
							}
						},
						{
							header : "管理",
							dataIndex : "bookSnId",
							width : 50,
							renderer : function(h, g, e, k, f) {
								var j = e.data.bookSnId;
								var i = '<input type="button" title="删除" value=" " class="btn-del" onclick="BookSnView.remove(' + j + ')"/>';
								i += '&nbsp;<input type="button" title="编辑" value=" " class="btn-edit" onclick="BookSnView.edit(' + j + ')"/>';
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
		id : "BookSnGrid",
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
			BookSnView.edit(e.data.bookSnId);
		});
	});
	return c;
};
BookSnView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/admin/listBookSn.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "bookSnId",
				type : "int"
			}, {
				name : "bookName",
				mapping : "book.bookName"
			}, "bookSN", "status" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("bookSnId", "desc");
	return a;
};
BookSnView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "BookSnFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : [ {
			iconCls : "btn-add",
			text : "添加图书Sn",
			xtype : "button",
			handler : function() {
				new BookSnForm();
			}
		}, {
			iconCls : "btn-del",
			text : "删除图书Sn",
			xtype : "button",
			handler : function() {
				var d = Ext.getCmp("BookSnGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.bookSnId);
				}
				BookSnView.remove(e);
			}
		} ]
	});
	return a;
};
BookSnView.remove = function(b) {
	var a = Ext.getCmp("BookSnGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelBookSn.do",
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
BookSnView.edit = function(a) {
	new BookSnForm(a);
};