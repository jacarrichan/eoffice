ArchiveTypeTempView = Ext.extend(Ext.Panel, {
	curTypeId : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchiveTypeTempView.superclass.constructor.call(this, {
			id : "ArchiveTypeTempView",
			title : "公文分类及模板管理",
			layout : "border",
			iconCls : "menu-archive-template",
			scope : this,
			items : [ this.leftTypePanel, this.archTemplateView ]
		});
	},
	initUIComponents : function() {
		this.archTemplateView = new ArchTemplateView( {
			allowEdit : true
		});
		var a = this.archTemplateView;
		var b = [];
		if (isGranted("_ArchivesTypeAdd")) {
			b.push( {
				text : "新建分类",
				scope : this,
				iconCls : "btn-add",
				handler : function() {
					new ArchivesTypeForm().show();
				}
			});
		}
		if (isGranted("_ArchivesTypeEdit")) {
			b.push( {
				text : "修改分类",
				scope : this,
				iconCls : "btn-edit",
				handler : function() {
					new ArchivesTypeForm( {
						typeId : this.leftTypePanel.selectedNode.id
					}).show();
				}
			});
		}
		if (isGranted("_ArchivesTypeDel")) {
			b.push( {
				text : "删除分类",
				scope : this,
				iconCls : "btn-delete",
				handler : function() {
					var c = this.leftTypePanel;
					var d = c.selectedNode.id;
					Ext.Ajax.request( {
						url : __ctxPath + "/archive/multiDelArchivesType.do",
						params : {
							ids : d
						},
						method : "POST",
						success : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "成功删除该公文分类！");
							c.root.reload();
						},
						failure : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
						}
					});
				}
			});
		}
		this.leftTypePanel = new cyjt.ux.TreePanelEditor( {
			region : "west",
			id : "archivesTypeTree",
			title : "公文分类",
			collapsible : true,
			split : true,
			width : 200,
			url : __ctxPath + "/archive/treeArchivesType.do",
			scope : this,
			onclick : function(e) {
				var d = e.id;
				if (e.id == 0) {
					a.setTitle("所有模板");
					d = null;
				} else {
					a.setTitle("[" + e.text + "]模板列表");
				}
				a.setTypeId(e.id);
				var c = a.gridPanel.getStore();
				c.url = __ctxPath + "/archive/listArchTemplate.do";
				c.baseParams = {
					"Q_archivesType.typeId_L_EQ" : d
				};
				c.params = {
					start : 0,
					limit : 25
				};
				c.reload( {
					params : {
						start : 0,
						limit : 25
					}
				});
			},
			contextMenuItems : b
		});
	}
});