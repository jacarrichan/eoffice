Ext.ns("BookTypeTree");
var BookTypeTree = function() {
	return this.setup();
};
BookTypeTree.prototype.setup = function() {
	var b;
	var c = new Ext.tree.TreePanel( {
		id : "typeTree",
		title : "图书类别",
		region : "west",
		width : 200,
		height : 480,
		autoScroll : true,
		collapsible : true,
		split : true,
		tbar : new Ext.Toolbar( {
			height : 30,
			items : [ {
				text : "添加",
				iconCls : "add-info",
				handler : function() {
					var h = Ext.getCmp("bookTypeForm");
					if (h == null) {
						new BookTypeForm();
					} else {
						h.getForm().reset();
					}
				}
			}, {
				xtype : "button",
				iconCls : "btn-refresh",
				text : "刷新",
				handler : function() {
					c.root.reload();
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
			"click" : BookTypeTree.clickNode
		}
	});
	if (isGranted("_BookTypeAdd") || isGranted("_BookTypeEdit")
			|| isGranted("_BookTypeDel")) {
		c.on("contextmenu", d, c);
	}
	var g = new Ext.menu.Menu( {
		id : "BookTypeTreeMenu",
		items : []
	});
	if (isGranted("_BookTypeAdd")) {
		g.add( {
			text : "新建",
			scope : this,
			handler : e
		});
	}
	if (isGranted("_BookTypeEdit")) {
		g.add( {
			text : "修改",
			scope : this,
			handler : a
		});
	}
	if (isGranted("_BookTypeDel")) {
		g.add( {
			text : "删除",
			scope : this,
			handler : f
		});
	}
	function d(h, i) {
		b = new Ext.tree.TreeNode( {
			id : h.id,
			text : h.text
		});
		g.showAt(i.getXY());
	}
	function e() {
		var h = Ext.getCmp("bookTypeForm");
		if (h == null) {
			new BookTypeForm();
		}
	}
	function f() {
		var i = b.id;
		var h = Ext.getCmp("typeTree");
		if (i > 0) {
			Ext.Msg.confirm("删除操作", "你确定删除图书类别吗?", function(j) {
				if (j == "yes") {
					Ext.Ajax.request( {
						url : __ctxPath + "/admin/removeBookType.do",
						params : {
							typeId : i
						},
						method : "post",
						success : function() {
							Ext.ux.Toast.msg("操作信息", "删除成功!");
							var k = Ext.getCmp("BookTypeView");
							if (k != null) {
								k.getStore().reload();
							}
							if (h != null) {
								h.root.reload();
							}
						}
					});
				}
			});
		}
	}
	function a() {
		var i = b.id;
		var h = Ext.getCmp("bookTypeForm");
		if (h == null) {
			new BookTypeForm(null);
			h = Ext.getCmp("bookTypeForm");
		}
		h.form.load( {
			url : __ctxPath + "/admin/getBookType.do",
			params : {
				typeId : i
			},
			method : "post",
			deferredRender : true,
			layoutOnTabChange : true,
			waitMsg : "正在载入数据...",
			success : function() {
			},
			failure : function() {
				Ext.ux.Toast.msg("编辑", "载入失败");
			}
		});
	}
	return c;
};
BookTypeTree.clickNode = function(c) {
	if (c != null) {
		var b = Ext.getCmp("BookGrid");
		var a = b.getStore();
		a.proxy = new Ext.data.HttpProxy( {
			url : __ctxPath + "/admin/categoryBook.do"
		});
		a.baseParams = {
			typeId : c.id
		};
		a.reload( {
			params : {
				start : 0,
				limit : 25
			}
		});
	}
};