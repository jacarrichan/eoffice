Ext.ns("BookManageView");
var BookManageView = function() {
	var f;
	var c = new BookView();
	var d = new Ext.tree.TreePanel( {
		region : "west",
		id : "leftBookManagePanel",
		title : "图书类别",
		collapsible : true,
		split : true,
		width : 150,
		height : 800,
		tbar : new Ext.Toolbar( {
			id : "BookManageBar",
			items : [ {
				xtype : "button",
				iconCls : "btn-refresh",
				text : "刷新",
				handler : function() {
					d.root.reload();
				}
			} ]
		}),
		loader : new Ext.tree.TreeLoader( {
			url : __ctxPath + "/admin/treeBookType.do"
		}),
		root : new Ext.tree.AsyncTreeNode( {
			expanded : true
		}),
		rootVisible : false,
		listeners : {
			"click" : function(m) {
				if (m != null) {
					c.setTypeId(m.id);
					var k = Ext.getCmp("BookView");
					if (m.id == 0) {
						k.setTitle("所有图书列表");
					} else {
						k.setTitle("[" + m.text + "]列表");
					}
					var l = Ext.getCmp("BookGrid");
					var j = l.getStore();
					j.url = __ctxPath + "/admin/listBook.do";
					j.baseParams = {
						"Q_bookType.typeId_L_EQ" : m.id == 0 ? null : m.id
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
		f = new Ext.tree.TreeNode( {
			id : j.id,
			text : j.text
		});
		g.showAt(k.getXY());
	}
	if (isGranted("_BookTypeAdd") || isGranted("_BookTypeEdit")
			|| isGranted("_BookTypeDel")) {
		d.on("contextmenu", b, d);
		Ext.getCmp("BookManageBar").add(new Ext.Button( {
			text : "添加类别",
			iconCls : "btn-add",
			handler : function() {
				var j = Ext.getCmp("bookTypeForm");
				if (j == null) {
					new BookTypeForm();
				} else {
					j.getForm().reset();
				}
			}
		}));
	}
	var g = new Ext.menu.Menu( {
		id : "BookManageTreeMenu",
		items : []
	});
	if (isGranted("_BookTypeAdd")) {
		g.add( {
			text : "添加类别",
			scope : this,
			iconCls : "btn-add",
			handler : i
		});
	}
	if (isGranted("_BookTypeEdit")) {
		g.add( {
			text : "修改类别",
			scope : this,
			iconCls : "btn-edit",
			handler : h
		});
	}
	if (isGranted("_BookTypeDel")) {
		g.add( {
			text : "删除类别",
			scope : this,
			iconCls : "btn-delete",
			handler : e
		});
	}
	function i() {
		new BookTypeForm(null);
	}
	function h() {
		var j = f.id;
		if (j > 0) {
			new BookTypeForm(j);
		} else {
			Ext.MessageBox.show( {
				title : "操作信息",
				msg : "该处不能被修改",
				buttons : Ext.MessageBox.OK,
				icon : "ext-mb-error"
			});
		}
	}
	function e() {
		var j = f.id;
		Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(k) {
			if (k == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/admin/multiDelBookType.do",
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
							d.root.reload();
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
		id : "BookManageView",
		title : "图书管理",
		iconCls : "menu-book-manage",
		layout : "border",
		height : 800,
		items : [ d, c.getView() ]
	});
	return a;
};