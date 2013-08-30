ArchivesDepForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		if (a == null) {
			a = {};
		}
		Ext.apply(this, a);
		this.initComponents();
		ArchivesDepForm.superclass.constructor.call(this, {
			id : "ArchivesDepFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 200,
			width : 400,
			maximizable : true,
			title : "[ArchivesDep]详细信息",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initComponents : function() {
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/archive/saveArchivesDep.do",
			id : "ArchivesDepForm",
			defaults : {
				anchor : "98%,98%"
			},
			defaultType : "textfield",
			items : [ {
				name : "archivesDep.archDepId",
				id : "archDepId",
				xtype : "hidden",
				value : this.archDepId == null ? "" : this.archDepId
			}, {
				fieldLabel : "自编号",
				name : "archivesDep.signNo",
				id : "signNo"
			}, {
				fieldLabel : "收文部门",
				name : "archivesDep.depId",
				id : "depId"
			}, {
				fieldLabel : "所属公文",
				name : "archivesDep.archivesId",
				id : "archivesId"
			}, {
				fieldLabel : "公文标题",
				name : "archivesDep.subject",
				id : "subject"
			}, {
				fieldLabel : "所属收文分类",
				name : "archivesDep.typeId",
				id : "typeId"
			}, {
				fieldLabel : "签收状态",
				name : "archivesDep.status",
				id : "status"
			}, {
				fieldLabel : "签收日期",
				name : "archivesDep.signTime",
				id : "signTime"
			}, {
				fieldLabel : "签收人",
				name : "archivesDep.signFullname",
				id : "signFullname"
			}, {
				fieldLabel : "办理结果反馈",
				name : "archivesDep.handleFeedback",
				id : "handleFeedback"
			}, {
				fieldLabel : "主送、抄送",
				name : "archivesDep.isMain",
				id : "isMain"
			} ]
		});
		if (this.archDepId != null && this.archDepId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath
								+ "/archive/getArchivesDep.do?archDepId="
								+ this.archDepId,
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
					var d = Ext.getCmp("ArchivesDepGrid");
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