ArchUndertakeForm = Ext.extend(Ext.Window, {
	formPanel : null,
	displayPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchUndertakeForm.superclass.constructor.call(this, {
			id : "ArchUndertakeFormWin",
			layout : "form",
			items : [ this.formPanel, this.displayPanel ],
			modal : true,
			height : 400,
			width : 600,
			iconCls : "menu-arch-undertake",
			maximizable : true,
			title : "承办意见待办",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.displayPanel = new Ext.Panel( {
			id : "ArchivesHandleDisplayPanel",
			autoScroll : true,
			height : 220,
			border : false,
			autoLoad : {
				url : __ctxPath + "/pages/archive/dispatchread.jsp?dispatchId="
						+ this.dispatchId
			}
		});
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/archive/readArchDispatch.do",
			id : "ArchUndertakeForm",
			defaults : {
				anchor : "98%,98%"
			},
			items : [ {
				name : "dispatchId",
				id : "dispatchId",
				xtype : "hidden",
				value : this.dispatchId
			}, {
				fieldLabel : "承办意见",
				name : "readFeedback",
				xtype : "textarea",
				id : "readFeedback"
			} ]
		});
		if (this.dispatchId != null && this.dispatchId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath
								+ "/archive/getArchDispatch.do?dispatchId="
								+ this.dispatchId,
						waitMsg : "正在载入数据...",
						success : function(a, b) {
						},
						failure : function(a, b) {
						}
					});
		}
		this.buttons = [ {
			text : "取消",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
	},
	cancel : function(a) {
		a.close();
	},
	save : function(a, b) {
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("ArchUndertakeGrid");
					if (d != null) {
						d.getStore().reload();
					}
					b.close();
				},
				failure : function(c, d) {
					Ext.MessageBox.show( {
						title : "操作信息",
						msg : "信息保存出错，请联系管理员！",
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					b.close();
				}
			});
		}
	}
});