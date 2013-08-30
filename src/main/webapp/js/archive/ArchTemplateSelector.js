ArchTemplateSelector = Ext.extend(Ext.Window, {
	curTypeId : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchTemplateSelector.superclass.constructor.call(this, {
			title : "公文模板选择",
			iconCls : "menu-archive-template",
			layout : "border",
			maximizable : true,
			width : 800,
			height : 500,
			modal : true,
			closeAction : "hide",
			scope : this,
			items : [ this.leftTypePanel, this.archTemplateView ],
			buttonAlign : "center",
			buttons : [
					{
						text : "选择模板",
						iconCls : "menu-archive-template",
						scope : this,
						handler : function() {
							if (this.callback != null) {
								var b = this.archTemplateView.gridPanel
										.getSelectionModel().getSelections();
								if (b.length == 0) {
									Ext.ux.Toast.msg("信息", "请选择模板！");
									return;
								}
								this.callback.call(this, b[0].data.fileId,
										b[0].data.tempPath);
								this.close();
							}
						}
					}, {
						text : "取消",
						iconCls : "btn-cancel",
						scope : this,
						handler : function() {
							this.close();
						}
					} ]
		});
	},
	initUIComponents : function() {
		this.archTemplateView = new ArchTemplateView( {
			allowEdit : false,
			singleSelect : true
		});
		var a = this.archTemplateView;
		this.leftTypePanel = new cyjt.ux.TreePanelEditor( {
			region : "west",
			title : "公文分类",
			collapsible : true,
			split : true,
			width : 200,
			url : __ctxPath + "/archive/treeArchivesType.do",
			scope : this,
			onclick : function(d) {
				var c = d.id;
				if (d.id == 0) {
					a.setTitle("所有模板");
					c = null;
				} else {
					a.setTitle("[" + d.text + "]模板列表");
				}
				a.setTypeId(d.id);
				var b = a.gridPanel.getStore();
				b.url = __ctxPath + "/archive/listArchTemplate.do";
				b.baseParams = {
					"Q_archivesType.typeId_L_EQ" : c
				};
				b.params = {
					start : 0,
					limit : 25
				};
				b.reload( {
					params : {
						start : 0,
						limit : 25
					}
				});
			}
		});
	}
});