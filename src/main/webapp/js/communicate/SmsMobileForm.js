SmsMobileForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(b) {
		Ext.applyIf(this, b);
		this.initUIComponents();
		SmsMobileForm.superclass.constructor.call(this, {
			id : "SmsMobileFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 245,
			width : 718,
			minHeight : 225,
			minWidth : 717,
			iconCls : "menu-mobile",
			maximizable : true,
			title : "手机详细信息",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/communicate/saveSmsMobile.do",
			id : "SmsMobileForm",
			defaults : {
				anchor : "98%,98%"
			},
			defaultType : "textfield",
			items : [ {
				name : "smsMobile.smsId",
				id : "smsId",
				xtype : "hidden",
				value : this.smsId == null ? "" : this.smsId
			}, {
				xtype : "container",
				hidden : this.isInner ? false : true,
				layout : "hbox",
				layoutConfig : {
					padding : "5",
					align : "middle"
				},
				items : [ {
					style : "padding-left:0px;",
					xtype : "displayfield",
					value : "收信人:",
					width : 100
				}, {
					xtype : "textfield",
					id : "recipients",
					width : 350,
					style : "padding-right:8px;"
				}, {
					xtype : "button",
					text : "选择",
					iconCls : "btn-add",
					handler : function() {
						UserSelector.getView(function(c, d) {
							Ext.getCmp("recipients").setValue(d);
							Ext.getCmp("recipientIds").setValue(c);
						}, null, null, true).show();
					}
				} ]
			}, {
				xtype : "container",
				layout : "form",
				anchor : "99%,99%",
				hidden : this.isInner ? true : false,
				items : [ {
					fieldLabel : "收信号码",
					xtype : "textarea",
					anchor : "99%,99%",
					name : "smsMobile.phoneNumber",
					id : "phoneNumber"
				} ]
			}, {
				fieldLabel : "发信人",
				name : "smsMobile.userName",
				id : "userName",
				value : curUserInfo.fullname
			}, {
				fieldLabel : "短信内容",
				name : "smsMobile.smsContent",
				id : "smsContent",
				xtype : "textarea"
			}, {
				name : "recipientIds",
				xtype : "hidden",
				id : "recipientIds"
			} ]
		});
		if (this.smsId != null && this.smsId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath
								+ "/communicate/getSmsMobile.do?smsId="
								+ this.smsId,
						waitMsg : "正在载入数据...",
						success : function(d, c) {
						},
						failure : function(d, c) {
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
	reset : function(b) {
		b.getForm().reset();
	},
	cancel : function(b) {
		b.close();
	},
	save : function(d, c) {
		if (d.getForm().isValid()) {
			d.getForm().submit({
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(h, b) {
					var a = b.result.msg;
					if (a != null && a != "" && a != "undefined") {
						Ext.ux.Toast.msg("操作信息", a);
					} else {
						Ext.ux.Toast.msg("操作信息", "短信正在发送,请等待接收！");
					}
					var g = Ext.getCmp("SmsMobileGrid");
					if (g != null) {
						g.getStore().reload();
					}
					c.close();
				},
				failure : function(b, a) {
					Ext.MessageBox.show({
						title : "操作信息",
						msg : "信息保存出错，请联系管理员！",
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					c.close();
				}
			});
		}
	}
});