var AppointmentForm = function(b) {
	this.appointId = b;
	var a = this.setup();
	var c = new Ext.Window( {
		id : "AppointmentFormWin",
		iconCls : "menu-appointment",
		title : "约会详细信息",
		width : 500,
		height : 430,
		modal : true,
		layout : "fit",
		buttonAlign : "center",
		items : [ this.setup() ],
		buttons : [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var d = Ext.getCmp("AppointmentForm");
				if (d.getForm().isValid()) {
					d.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("AppointmentGrid").getStore().reload();
							c.close();
						},
						failure : function(e, f) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : "ext-mb-error"
							});
							c.close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				c.close();
			}
		} ]
	});
	c.show();
};
AppointmentForm.prototype.setup = function() {
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/task/saveAppointment.do",
		layout : "form",
		id : "AppointmentForm",
		frame : false,
		border : false,
		formId : "AppointmentFormId",
		bodyStyle : "padding:5px;",
		defaultType : "textfield",
		defaults : {
			width : 300
		},
		items : [ {
			name : "appointment.appointId",
			id : "appointId",
			xtype : "hidden",
			value : this.appointId == null ? "" : this.appointId
		}, {
			fieldLabel : "主题",
			allowBlank : false,
			name : "appointment.subject"
		}, {
			fieldLabel : "开始时间",
			name : "appointment.startTime",
			allowBlank : false,
			xtype : "datetimefield",
			format : "Y-m-d H:i:s"
		}, {
			fieldLabel : "结束时间",
			name : "appointment.endTime",
			allowBlank : false,
			xtype : "datetimefield",
			format : "Y-m-d H:i:s"
		}, {
			fieldLabel : "约会内容",
			name : "appointment.content",
			xtype : "textarea",
			allowBlank : false
		}, {
			fieldLabel : "地点",
			name : "appointment.location",
			allowBlank : false
		}, {
			fieldLabel : "备注",
			name : "appointment.notes",
			xtype : "textarea"
		}, {
			fieldLabel : "受邀人Email",
			xtype : "textarea",
			name : "appointment.inviteEmails"
		}, {
			xtype : "checkboxgroup",
			fieldLabel : "留言方式",
			items : [ {
				xtype : "checkbox",
				boxLabel : "普通留言",
				name : "appointment.isMsg",
				id : "isMsg",
				inputValue : 0
			}, {
				xtype : "checkbox",
				boxLabel : "手机留言",
				name : "appointment.isMobile",
				id : "isMobile",
				inputValue : 0
			} ]
		} ]
	});
	if (this.appointId != null && this.appointId != "undefined") {
		a.loadData( {
			deferredRender : false,
			url : __ctxPath + "/task/getAppointment.do?appointId="
					+ this.appointId,
			waitMsg : "正在载入数据...",
			preName : "appointment",
			root : "data",
			success : function(c, e) {
				var f = Ext.util.JSON.decode(c.responseText).data;
				if (f.isMsg == 0) {
					var d = Ext.getCmp("isMsg");
					d.setValue(true);
				}
				if (f.isMobile == 0) {
					var b = Ext.getCmp("isMobile");
					b.setValue(true);
				}
			},
			failure : function(b, c) {
				Ext.ux.Toast.msg("编辑", "载入失败");
			}
		});
	}
	return a;
};