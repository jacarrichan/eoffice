Ext.ns("DocFolderView");
var DocFolderView = function() {
	return new Ext.Panel( {
		id : "DocFolderView",
		title : "公共文件夹列表",
		autoScroll : true,
		items : [ new Ext.FormPanel( {
			height : 35,
			frame : true,
			id : "DocFolderSearchForm",
			layout : "column",
			defaults : {
				xtype : "label"
			},
			items : [ {
				text : "请输入查询条件:"
			}, {
				text : "主键"
			}, {
				xtype : "textfield",
				name : "Q_userId_S_LK"
			}, {
				text : "目录名称"
			}, {
				xtype : "textfield",
				name : "Q_folderName_S_LK"
			}, {
				text : "父目录"
			}, {
				xtype : "textfield",
				name : "Q_parentId_S_LK"
			}, {
				text : ""
			}, {
				xtype : "textfield",
				name : "Q_isShared_S_LK"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var a = Ext.getCmp("DocFolderSearchForm");
					var b = Ext.getCmp("DocFolderGrid");
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
DocFolderView.prototype.setup = function() {
	return this.grid();
};
DocFolderView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "folderId",
							dataIndex : "folderId",
							hidden : true
						},
						{
							header : "文件夹名称",
							dataIndex : "folderName"
						},
						{
							header : "父目录",
							dataIndex : "parentId"
						},
						{
							header : "管理",
							dataIndex : "folderId",
							width : 50,
							renderer : function(h, g, e, k, f) {
								var j = e.data.folderId;
								var i = '<button title="删除" value=" " class="btn-del" onclick="DocFolderView.remove(' + j + ')">&nbsp;</button>';
								i += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="DocFolderView.edit(' + j + ')">&nbsp;</button>';
								i += '&nbsp;<button title="授权" value=" " class="btn-shared" onclick="DocFolderView.right(' + j + ')">&nbsp;</button>';
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
		id : "DocFolderGrid",
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
			DocFolderView.edit(e.data.folderId);
		});
	});
	return c;
};
DocFolderView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/document/shareDocFolder.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "folderId",
				type : "int"
			}, "userId", "folderName", "parentId", "path", "isShared" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("folderId", "desc");
	return a;
};
DocFolderView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "DocFolderFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : [ {
			iconCls : "btn-add",
			text : "添加DocFolder",
			xtype : "button",
			handler : function() {
				new DocFolderForm(null, null, 1);
			}
		}, {
			iconCls : "btn-del",
			text : "删除DocFolder",
			xtype : "button",
			handler : function() {
				var d = Ext.getCmp("DocFolderGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.folderId);
				}
				DocFolderView.remove(e);
			}
		} ]
	});
	return a;
};
DocFolderView.remove = function(b) {
	var a = Ext.getCmp("DocFolderGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/document/multiDelDocFolder.do",
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
DocFolderView.edit = function(a) {
	new DocFolderForm(a);
};
DocFolderView.right = function(a) {
	new DocFolderSharedForm(a).getView().show();
};