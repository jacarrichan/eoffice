Ext.ns("DocFolderSharedView");
var DocFolderSharedView = function() {
	var e;
	var i = new DocPrivilegeView();
	var c = new Ext.tree.TreePanel( {
		region : "west",
		id : "leftDocFolderSharedPanel",
		title : "文件夹目录",
		collapsible : true,
		split : true,
		width : 180,
		height : 800,
		tbar : new Ext.Toolbar( {
			items : [ {
				xtype : "button",
				iconCls : "btn-refresh",
				text : "刷新",
				handler : function() {
					c.root.reload();
				}
			}, {
				xtype : "button",
				text : "展开",
				iconCls : "btn-expand",
				handler : function() {
					c.expandAll();
				}
			}, {
				xtype : "button",
				text : "收起",
				iconCls : "btn-collapse",
				handler : function() {
					c.collapseAll();
				}
			} ]
		}),
		loader : new Ext.tree.TreeLoader( {
			url : __ctxPath + "/document/treeDocFolder.do"
		}),
		root : new Ext.tree.AsyncTreeNode( {
			expanded : true
		}),
		rootVisible : false,
		listeners : {
			"click" : function(l) {
				if (l != null) {
					i.setFolderId(l.id);
					var m = Ext.getCmp("DocPrivilegeView");
					if (l.id == 0) {
						m.setTitle("文件夹授权");
					} else {
						m.setTitle("文件夹[" + l.text + "]授权情况");
					}
					var k = Ext.getCmp("DocPrivilegeGrid");
					var j = k.getStore();
					j.url = __ctxPath + "/document/listDocPrivilege.do";
					j.baseParams = {
						"Q_docFolder.folderId_L_EQ" : l.id
					};
					j.params = {
						start : 0,
						limit : 25
					};
					j.reload();
				}
			}
		}
	});
	function b(j, k) {
		e = new Ext.tree.TreeNode( {
			id : j.id,
			text : j.text
		});
		f.showAt(k.getXY());
	}
	if (isGranted("_DocFolderSharedManage")) {
		c.on("contextmenu", b, c);
	}
	var f = new Ext.menu.Menu( {
		tbar : new Ext.Toolbar( {
			items : [ {
				text : "刷新",
				handler : function() {
					alert("refresh");
				}
			} ]
		}),
		id : "DocFolderTreeMenu",
		items : [ {
			text : "新建目录",
			scope : this,
			iconCls : "btn-add",
			handler : h
		}, {
			text : "修改目录",
			scope : this,
			iconCls : "btn-edit",
			handler : g
		}, {
			text : "删除目录",
			scope : this,
			iconCls : "btn-delete",
			handler : d
		} ]
	});
	function h(j) {
		var k = e.id;
		new DocFolderForm(null, k, 1);
	}
	function g() {
		var j = e.id;
		new DocFolderForm(j, null, null);
	}
	function d() {
		var j = e.id;
		Ext.Msg.confirm("删除操作", "删除目录会将该目录的权限删除，你确定删除该目录吗?", function(k) {
			if (k == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/document/removeDocFolder.do",
					params : {
						folderId : j
					},
					method : "post",
					success : function(l, n) {
						var m = Ext.util.JSON.decode(l.responseText);
						if (m.success == false) {
							Ext.ux.Toast.msg("操作信息", m.message);
						} else {
							Ext.ux.Toast.msg("操作信息", "成功删除目录！");
							c.root.reload();
						}
					},
					failure : function(l, m) {
						Ext.MessageBox.show( {
							title : "操作信息",
							msg : "信息保存出错，请联系管理员！",
							buttons : Ext.MessageBox.OK,
							icon : "ext-mb-error"
						});
					}
				});
			}
		});
	}
	var a = new Ext.Panel( {
		id : "DocFolderSharedView",
		title : "公共文件夹管理",
		iconCls : "menu-public-fol",
		layout : "border",
		height : 800,
		items : [ c, i.getView() ]
	});
	return a;
};