Ext.ns("BookTypeView");
var BookTypeView = function() {
	return new Ext.Panel( {
		id : "BookTypeView",
		title : "图书类别列表",
		iconCls : "menu-book-type",
		autoScroll : true,
		items : [ new Ext.FormPanel( {
			id : "BookTypeSearchForm",
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
				text : "图书类别"
			}, {
				xtype : "textfield",
				name : "Q_typeName_S_LK"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var a = Ext.getCmp("BookTypeSearchForm");
					var b = Ext.getCmp("BookTypeGrid");
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
BookTypeView.prototype.setup = function() {
	return this.grid();
};
BookTypeView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "typeId",
							dataIndex : "typeId",
							hidden : true
						},
						{
							header : "图书类别",
							dataIndex : "typeName"
						},
						{
							header : "管理",
							dataIndex : "typeId",
							sortable : false,
							width : 50,
							renderer : function(h, g, e, k, f) {
								var j = e.data.typeId;
								var i = "";
								if (isGranted("_BookTypeDel")) {
									i = '<button title="删除" value=" " class="btn-del" onclick="BookTypeView.remove(' + j + ')"></button>';
								}
								if (isGranted("_BookTypeEdit")) {
									i += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="BookTypeView.edit(' + j + ')"></button>';
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
		id : "BookTypeGrid",
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
			if (isGranted("_BookTypeEdit")) {
				BookTypeView.edit(e.data.typeId);
			}
		});
	});
	return c;
};
BookTypeView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/admin/listBookType.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "typeId",
				type : "int"
			}, "typeName" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("typeId", "desc");
	return a;
};
BookTypeView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "BookTypeFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : []
	});
	if (isGranted("_BookTypeAdd")) {
		a.add(new Ext.Button( {
			iconCls : "btn-add",
			text : "添加图书类别",
			handler : function() {
				new BookTypeForm();
			}
		}));
	}
	if (isGranted("_BookTypeDel")) {
		a.add(new Ext.Button( {
			iconCls : "btn-del",
			text : "删除图书类别",
			handler : function() {
				var d = Ext.getCmp("BookTypeGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.typeId);
				}
				BookTypeView.remove(e);
			}
		}));
	}
	return a;
};
BookTypeView.remove = function(b) {
	var a = Ext.getCmp("BookTypeGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelBookType.do",
				params : {
					ids : b
				},
				method : "post",
				success : function(d, f) {
					var e = Ext.util.JSON.decode(d.responseText);
					if (e.success == false) {
						Ext.ux.Toast.msg("操作信息", e.message);
					} else {
						Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
						a.getStore().reload( {
							params : {
								start : 0,
								limit : 25
							}
						});
					}
				}
			});
		}
	});
};
BookTypeView.edit = function(a) {
	new BookTypeForm(a);
};