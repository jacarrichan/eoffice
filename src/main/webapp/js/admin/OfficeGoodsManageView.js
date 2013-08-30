Ext.ns("OfficeGoodsManageView");
var OfficeGoodsManageView = function() {
	var e;
	var f = new OfficeGoodsView();
	var c = new Ext.tree.TreePanel( {
		region : "west",
		id : "leftOfficeGoodManagePanel",
		title : "办公用品类型",
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
			url : __ctxPath + "/admin/treeOfficeGoodsType.do"
		}),
		root : new Ext.tree.AsyncTreeNode( {
			expanded : true
		}),
		rootVisible : false,
		listeners : {
			"click" : function(l) {
				if (l != null) {
					f.setTypeId(l.id);
					var m = Ext.getCmp("OfficeGoodsView");
					if (l.id == 0) {
						m.setTitle("所有用品列表");
					} else {
						m.setTitle("[" + l.text + "]类型用品列表");
					}
					var k = Ext.getCmp("OfficeGoodsGrid");
					var j = k.getStore();
					j.url = __ctxPath + "/admin/listOfficeGoods.do";
					j.baseParams = {
						"Q_officeGoodsType.typeId_L_EQ" : l.id == 0 ? null
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
		g.showAt(k.getXY());
	}
	if (isGranted("_OfficeGoodsTypeManage")) {
		c.on("contextmenu", b, c);
	}
	var g = new Ext.menu.Menu( {
		id : "OfficeGoodsManageTreeMenu",
		items : [ {
			text : "新建类别",
			scope : this,
			iconCls : "btn-add",
			handler : i
		}, {
			text : "修改类别",
			scope : this,
			iconCls : "btn-edit",
			handler : h
		}, {
			text : "删除类别",
			scope : this,
			iconCls : "btn-delete",
			handler : d
		} ]
	});
	function i() {
		new OfficeGoodsTypeForm(null);
	}
	function h() {
		var j = e.id;
		if (j > 0) {
			new OfficeGoodsTypeForm(j);
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
					url : __ctxPath + "/admin/multiDelOfficeGoodsType.do",
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
		id : "OfficeGoodsManageView",
		title : "办公用品管理",
		iconCls : "menu-goods",
		layout : "border",
		height : 800,
		items : [ c, f ]
	});
	return a;
};