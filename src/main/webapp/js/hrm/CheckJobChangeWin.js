CheckJobChangeWin = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		if (a == null) {
			a = {};
		}
		Ext.apply(this, a);
		this.initComponents();
		CheckJobChangeWin.superclass.constructor.call(this, {
			id : "CheckJobChangeWinPanel",
			layout : "form",
			iconCls : "menu-job-check",
			modal : true,
			autoHeight : true,
			x : 280,
			y : 50,
			shadow : false,
			width : 550,
			maximizable : true,
			title : "职位调动详细信息",
			buttonAlign : "center",
			items : [ this.showPanel, this.formPanel ],
			buttons : this.buttons
		});
	},
	initComponents : function() {
		this.showPanel = new Ext.Panel( {
			id : "CheckJobChangePanel",
			autoScroll : true,
			height : 220,
			border : false,
			autoLoad : {
				url : __ctxPath + "/hrm/loadJobChange.do?changeId="
						+ this.changeId
			}
		});
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:8px 8px 2px 8px",
			border : false,
			url : __ctxPath + "/hrm/checkJobChange.do",
			id : "CheckJobChangeWin",
			autoScroll : true,
			defaults : {
				anchor : "100%,100%"
			},
			items : [ {
				xtype : "hidden",
				id : "CheckJobChangeWin.changeId",
				name : "jobChange.changeId",
				value : this.changeId == null ? "" : this.changeId
			}, {
				xtype : "hidden",
				id : "CheckJobChangeWin.status",
				name : "jobChange.status"
			}, {
				xtype : "textarea",
				fieldLabel : "审核意见",
				name : "jobChange.checkOpinion",
				allowBlank : false,
				id : "CheckJobChangeWin.checkOpinion"
			} ]
		});
		this.buttons = [ {
			text : "审核通过",
			id : "JobChangeBtnY",
			iconCls : "btn-empProfile-pass",
			handler : this.pass.createCallback(this.formPanel, this)
		}, {
			text : "审核不通过",
			id : "JobChangeBtnN",
			iconCls : "btn-empProfile-notpass",
			handler : this.notpass.createCallback(this.formPanel, this)
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
	},
	cancel : function(a) {
		a.close();
	},
	pass : function(a, b) {
		Ext.getCmp("CheckJobChangeWin.status").setValue(1);
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("JobChangeGrid");
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
	notpass : function(a, b) {
		Ext.getCmp("CheckJobChangeWin.status").setValue(2);
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("JobChangeGrid");
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