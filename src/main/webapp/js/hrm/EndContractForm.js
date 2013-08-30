EndContractForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		EndContractForm.superclass.constructor.call(this, {
			id : "EndContractFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 280,
			width : 400,
			maximizable : true,
			title : "合同终止",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/hrm/saveContractEvent.do",
			id : "EndContractForm",
			defaults : {
				anchor : "98%,98%"
			},
			defaultType : "textfield",
			items : [ {
				name : "contractEvent.eventId",
				id : "eventId",
				xtype : "hidden",
				value : this.eventId == null ? "" : this.eventId
			}, {
				fieldLabel : "记录名称",
				name : "contractEvent.eventName",
				id : "eventName"
			}, {
				xtype : "fieldset",
				title : "情况说明",
				layout : "form",
				defaultType : "textfield",
				items : [ {
					fieldLabel : "终止理由",
					name : "contractEvent.eventDescp",
					id : "eventDescp",
					xtype : "textarea",
					anchor : "100%"
				} ]
			} ]
		});
		if (this.eventId != null && this.eventId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath + "/hrm/getContractEvent.do?eventId="
								+ this.eventId,
						waitMsg : "正在载入数据...",
						success : function(a, b) {
						},
						failure : function(a, b) {
						}
					});
		}
		this.buttons = [ {
			text : "保存",
			iconCls : "btn-save",
			handler : this.save.createCallback(this.formPanel, this)
		}, {
			text : "重置",
			iconCls : "btn-reset",
			handler : this.reset.createCallback(this.formPanel)
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
	},
	reset : function(a) {
		a.getForm().reset();
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
					var d = Ext.getCmp("ContractEventGrid");
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