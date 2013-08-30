JobForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		if (a == null) {
			a = {};
		}
		Ext.apply(this, a);
		this.initComponents();
		JobForm.superclass.constructor.call(this, {
			id : "JobFormWin",
			layout : "fit",
			iconCls : "menu-job",
			items : this.formPanel,
			modal : true,
			height : 200,
			width : 400,
			maximizable : true,
			title : "职位详细信息",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initComponents : function() {
		var a = __ctxPath + "/system/listDepartment.do?opt=appUser";
		this.jobDepartmentName = new TreeSelector("jobDepartmentName", a,
				"所属部门", "depId", false);
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/hrm/saveJob.do",
			id : "JobForm",
			defaults : {
				anchor : "98%,98%"
			},
			defaultType : "textfield",
			items : [ {
				name : "job.jobId",
				id : "jobId",
				xtype : "hidden",
				value : this.jobId == null ? "" : this.jobId
			}, {
				fieldLabel : "职位名称",
				name : "job.jobName",
				id : "jobName",
				allowBlank : false,
				blankText : "职位名称不能为空!"
			}, {
				fieldLabel : "所属部门",
				name : "job.depId",
				id : "depId",
				xtype : "hidden"
			}, this.jobDepartmentName, {
				fieldLabel : "备注",
				name : "job.memo",
				id : "memo",
				xtype : "textarea"
			} ]
		});
		if (this.jobId != null && this.jobId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath + "/hrm/getJob.do?jobId=" + this.jobId,
						waitMsg : "正在载入数据...",
						success : function(b, c) {
							Ext.getCmp("jobDepartmentName").setValue(
									c.result.data.department.depName);
						},
						failure : function(b, c) {
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
					var d = Ext.getCmp("JobGrid");
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