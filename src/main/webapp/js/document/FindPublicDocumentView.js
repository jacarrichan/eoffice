Ext.ns("FindPublicDocumentView");
var FindPublicDocumentView = function() {
	var d;
	var c = new PublicDocumentView();
	var b = new Ext.tree.TreePanel({
		region : "west",
		id : "leftPublicDocumentPanel",
		title : "公共文档目录",
		collapsible : true,
		split : true,
		width : 200,
		height : 800,
		tbar : new Ext.Toolbar({
			items : [ {
				xtype : "button",
				iconCls : "btn-refresh",
				text : "刷新",
				handler : function() {
					b.root.reload();
				}
			}, {
				xtype : "button",
				text : "展开",
				iconCls : "btn-expand",
				handler : function() {
					b.expandAll();
				}
			}, {
				xtype : "button",
				text : "收起",
				iconCls : "btn-collapse",
				handler : function() {
					b.collapseAll();
				}
			} ]
		}),
		loader : new Ext.tree.TreeLoader({
			url : __ctxPath + "/document/treeDocFolder.do"
		}),
		root : new Ext.tree.AsyncTreeNode({
			expanded : true
		}),
		rootVisible : false,
		listeners : {
			"click" : function(g) {
				if (g != null) {
					c.setFolderId(g.id);
					var f = Ext.getCmp("PublicDocumentView");
					if (g.id == 0) {
						f.setTitle("所有文档");
					} else {
						f.setTitle("[" + g.text + "]文档列表");
					}
					var h = Ext.getCmp("PublicDocumentGrid");
					var e = h.getStore();
					e.url = __ctxPath + "/document/publicListDocument.do";
					e.baseParams = {
						folderId : g.id
					};
					e.reload({
						params : {
							start : 0,
							limit : 25
						}
					});
				}
			}
		}
	});
	var a = new Ext.Panel({
		id : "FindPublicDocumentView",
		title : "公共文档",
		iconCls : "menu-find-doc",
		layout : "border",
		height : 800,
		items : [ b, c.getView() ]
	});
	return a;
};