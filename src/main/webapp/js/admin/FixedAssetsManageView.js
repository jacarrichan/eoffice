Ext.ns("FixedAssetsManageView");
var FixedAssetsManageView = function() {
	var e;
	var g = new FixedAssetsView();
	var c = new Ext.tree.TreePanel( {
		region : "west",
		id : "leftFixedAssetsManagePanel",
		title : "资产类型",
		collapsible : true,
		split : true,
		width : 160,
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
			url : __ctxPath + "/admin/treeAssetsType.do"
		}),
		root : new Ext.tree.AsyncTreeNode( {
			expanded : true
		}),
		rootVisible : false,
		listeners : {
			"click" : function(l) {
				if (l != null) {
					g.setTypeId(l.id);
					var k = Ext.getCmp("FixedAssetsView");
					if (l.id == 0) {
						k.setTitle("所有固定资产列表");
					} else {
						k.setTitle("[" + l.text + "]类型固定资产列表");
					}
					var m = Ext.getCmp("FixedAssetsGrid");
					var j = m.getStore();
					j.url = __ctxPath + "/admin/listFixedAssets.do";
					j.baseParams = {
						"Q_assetsType.assetsTypeId_L_EQ" : l.id == 0 ? null
								: l.id
					};
					j.params = {
						start : 0,
						limit : 25
					};
					j.reload( {
						params : {
							start : 0,
							limit : 25
						}
					});
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
	if (isGranted("_AssetsTypeManage")) {
		c.on("contextmenu", b, c);
	}
	var f = new Ext.menu.Menu( {
		id : "FixedAssetsTreeMenu",
		items : [ {
			text : "新建类型",
			scope : this,
			iconCls : "btn-add",
			handler : i
		}, {
			text : "修改类型",
			scope : this,
			iconCls : "btn-edit",
			handler : h
		}, {
			text : "删除类型",
			scope : this,
			iconCls : "btn-delete",
			handler : d
		} ]
	});
	function i() {
		new AssetsTypeForm(null);
	}
	function h() {
		var j = e.id;
		if (j > 0) {
			new AssetsTypeForm(j);
		} else {
			Ext.MessageBox.show( {
				title : "操作信息",
				msg : "该处不能被修改",
				buttons : Ext.MessageBox.OK,
				icon : "ext-mb-error"
			});
		}
	}
	function d() {
		var j = e.id;
		Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(k) {
			if (k == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/admin/multiDelAssetsType.do",
					params : {
						ids : j
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
		id : "FixedAssetsManageView",
		title : "固定资产管理",
		iconCls : "menu-assets",
		layout : "border",
		height : 800,
		items : [ c, g.getView() ]
	});
	return a;
};