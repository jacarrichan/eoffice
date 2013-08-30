ArchivesForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchivesForm.superclass.constructor.call(this, {
			id : "ArchivesFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 200,
			width : 400,
			maximizable : true,
			title : "[Archives]详细信息",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/archive/saveArchives.do",
			id : "ArchivesForm",
			defaults : {
				anchor : "98%,98%"
			},
			defaultType : "textfield",
			items : [ {
				name : "archives.archivesId",
				id : "archivesId",
				xtype : "hidden",
				value : this.archivesId == null ? "" : this.archivesId
			}, {
				fieldLabel : "公文类型",
				name : "archives.typeId",
				id : "typeId"
			}, {
				fieldLabel : "公文类型名称",
				name : "archives.typeName",
				id : "typeName"
			}, {
				fieldLabel : "发文字号",
				name : "archives.archivesNo",
				id : "archivesNo"
			}, {
				fieldLabel : "发文机关或部门",
				name : "archives.issueDep",
				id : "issueDep"
			}, {
				fieldLabel : "发文部门ID",
				name : "archives.depId",
				id : "depId"
			}, {
				fieldLabel : "文件标题",
				name : "archives.subject",
				id : "subject"
			}, {
				fieldLabel : "发布日期",
				name : "archives.issueDate",
				id : "issueDate"
			}, {
				fieldLabel : "公文状态",
				name : "archives.status",
				id : "status"
			}, {
				fieldLabel : "内容简介",
				name : "archives.shortContent",
				id : "shortContent"
			}, {
				fieldLabel : "文件数",
				name : "archives.fileCounts",
				id : "fileCounts"
			}, {
				fieldLabel : "秘密等级",
				name : "archives.privacyLevel",
				id : "privacyLevel"
			}, {
				fieldLabel : "紧急程度",
				name : "archives.urgentLevel",
				id : "urgentLevel"
			}, {
				fieldLabel : "发文人",
				name : "archives.issuer",
				id : "issuer"
			}, {
				fieldLabel : "发文人ID",
				name : "archives.issuerId",
				id : "issuerId"
			}, {
				fieldLabel : "主题词",
				name : "archives.keywords",
				id : "keywords"
			}, {
				fieldLabel : "公文来源",
				name : "archives.sources",
				id : "sources"
			}, {
				fieldLabel : "0=发文 1=收文",
				name : "archives.archType",
				id : "archType"
			}, {
				fieldLabel : "",
				name : "archives.createtime",
				id : "createtime"
			} ]
		});
		if (this.archivesId != null && this.archivesId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath + "/archive/getArchives.do?archivesId="
								+ this.archivesId,
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
					var d = Ext.getCmp("ArchivesGrid");
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