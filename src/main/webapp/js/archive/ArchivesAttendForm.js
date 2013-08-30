ArchivesAttendForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchivesAttendForm.superclass.constructor.call(this, {
			id : "ArchivesAttendFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 200,
			width : 400,
			maximizable : true,
			title : "[ArchivesAttend]详细信息",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/archive/saveArchivesAttend.do",
			id : "ArchivesAttendForm",
			defaults : {
				anchor : "98%,98%"
			},
			defaultType : "textfield",
			items : [ {
				name : "archivesAttend.attendId",
				id : "attendId",
				xtype : "hidden",
				value : this.attendId == null ? "" : this.attendId
			}, {
				fieldLabel : "",
				name : "archivesAttend.archivesId",
				id : "archivesId"
			}, {
				fieldLabel : "用户ID",
				name : "archivesAttend.userID",
				id : "userID"
			}, {
				fieldLabel : "姓名",
				name : "archivesAttend.fullname",
				id : "fullname"
			}, {
				fieldLabel : "参与类型",
				name : "archivesAttend.attendType",
				id : "attendType"
			}, {
				fieldLabel : "执行时间",
				name : "archivesAttend.executeTime",
				id : "executeTime"
			}, {
				fieldLabel : "备注",
				name : "archivesAttend.memo",
				id : "memo"
			} ]
		});
		if (this.attendId != null && this.attendId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath
								+ "/archive/getArchivesAttend.do?attendId="
								+ this.attendId,
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
					var d = Ext.getCmp("ArchivesAttendGrid");
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