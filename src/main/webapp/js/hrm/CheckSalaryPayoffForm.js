CheckSalaryPayoffForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		if (a == null) {
			a = {};
		}
		Ext.apply(this, a);
		this.initComponents();
		CheckSalaryPayoffForm.superclass.constructor.call(this, {
			id : "CheckSalaryPayoffFormWin",
			iconCls : "btn-empProfile-pass",
			layout : "form",
			items : [ this.displayPanel, this.formPanel ],
			modal : true,
			height : 415,
			shadow : false,
			autoScroll : true,
			width : 520,
			maximizable : true,
			title : "薪酬发放审核",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initComponents : function() {
		this.displayPanel = new Ext.Panel( {
			id : "CheckSalaryPayoffFormPanel",
			autoHeight : true,
			border : false,
			autoLoad : {
				url : __ctxPath + "/pages/hrm/checkSalaryPayoff.jsp?recordId="
						+ this.recordId
			}
		});
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			border : false,
			url : __ctxPath + "/hrm/checkSalaryPayoff.do?recordId="
					+ this.recordId,
			id : "CheckSalaryPayoffForm",
			bodyStyle : "padding:0 0 0 10px;",
			defaultType : "recordId",
			items : [ {
				fieldLabel : "审核意见",
				xtype : "textarea",
				anchor : "98%",
				allowBlank : false,
				blankText : "审核意见为必填!",
				name : "salaryPayoff.checkOpinion",
				id : "CheckSalaryPayoffForm.checkOpinion"
			}, {
				xtype : "hidden",
				name : "salaryPayoff.checkStatus",
				id : "CheckSalaryPayoffForm.checkStatus"
			} ]
		});
		this.buttons = [ {
			text : "审核通过",
			iconCls : "btn-salaryPayoff-pass",
			id : "salaryPayoffbtnY",
			handler : this.check.createCallback(this.formPanel, this)
		}, {
			text : "审核未通过",
			id : "salaryPayoffbtnN",
			iconCls : "btn-salaryPayoff-notpass",
			handler : this.refuse.createCallback(this.formPanel, this)
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
	},
	refuse : function(a, b) {
		Ext.getCmp("CheckSalaryPayoffForm.checkStatus").setValue("2");
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("SalaryPayoffGrid");
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
		Ext.getCmp("CheckSalaryPayoffForm.checkStatus").setValue("1");
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("SalaryPayoffGrid");
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