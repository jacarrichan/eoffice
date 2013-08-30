CheckEmpProfileForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		if (a == null) {
			a = {};
		}
		Ext.apply(this, a);
		this.initComponents();
		CheckEmpProfileForm.superclass.constructor.call(this, {
			id : "CheckEmpProfileFormWin",
			iconCls : "btn-empProfile-pass",
			layout : "form",
			items : [ this.formPanel, this.displayPanel ],
			modal : true,
			autoHeight : true,
			shadow : false,
			y : 10,
			width : 820,
			maximizable : true,
			title : "档案审核",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initComponents : function() {
		this.displayPanel = new Ext.Panel( {
			id : "CheckEmpProfileFormPanel",
			height : 430,
			autoScroll : true,
			border : false,
			autoLoad : {
				url : __ctxPath + "/pages/hrm/CheckEmpProfile.jsp?profileId="
						+ this.profileId
			}
		});
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			border : false,
			url : __ctxPath + "/hrm/checkEmpProfile.do?profileId="
					+ this.profileId,
			id : "CheckEmpProfileForm",
			bodyStyle : "padding:10px 10px 0 10px;",
			defaultType : "textfield",
			items : [ {
				fieldLabel : "审核意见",
				xtype : "textarea",
				anchor : "98%",
				allowBlank : false,
				blankText : "审核意见为必填!",
				name : "empProfile.opprovalOpinion",
				id : "CheckEmpProfileForm.opprovalOpinion"
			}, {
				xtype : "hidden",
				name : "empProfile.approvalStatus",
				id : "CheckEmpProfileForm.approvalStatus"
			} ]
		});
		this.buttons = [ {
			text : "审核通过",
			id : "CheckEmpProfileButY",
			iconCls : "btn-empProfile-pass",
			handler : this.check.createCallback(this.formPanel, this)
		}, {
			text : "审核未通过",
			id : "CheckEmpProfileButN",
			iconCls : "btn-empProfile-notpass",
			handler : this.refuse.createCallback(this.formPanel, this)
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
	},
	refuse : function(a, b) {
		Ext.getCmp("CheckEmpProfileForm.approvalStatus").setValue("2");
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("EmpProfileGrid");
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
	},
	cancel : function(a) {
		a.close();
	},
	check : function(a, b) {
		Ext.getCmp("CheckEmpProfileForm.approvalStatus").setValue("1");
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("EmpProfileGrid");
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