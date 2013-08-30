ArchivesDocForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchivesDocForm.superclass.constructor.call(this, {
			id : "ArchivesDocFormWin",
			layout : "fit",
			border : false,
			items : this.formPanel,
			modal : true,
			height : 600,
			width : 800,
			iconCls : "btn-archive-attachment",
			maximizable : true,
			title : "发文附件",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.docPanel = new NtkOfficePanel( {
			showToolbar : true,
			fileId : this.fileId,
			height : 480
		});
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			frame : true,
			url : __ctxPath + "/archive/saveArchivesDoc.do",
			id : "ArchivesDocForm",
			defaults : {
				anchor : "98%,98%"
			},
			items : [ {
				name : "archivesDoc.docId",
				id : "docId",
				xtype : "hidden",
				value : this.docId == null ? "" : this.docId
			}, {
				xtype : "hidden",
				name : "archivesDoc.fileId",
				id : "fileId"
			}, {
				fieldLabel : "文档名称",
				name : "archivesDoc.docName",
				xtype : "textfield",
				allowBlank : false,
				id : "docName"
			}, {
				xtype : "hidden",
				fieldLabel : "文档路径",
				name : "archivesDoc.docPath",
				id : "docPath"
			}, this.docPanel.panel ]
		});
		if (this.docId != null && this.docId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath + "/archive/getArchivesDoc.do?docId="
								+ this.docId,
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
			scope : this,
			handler : this.onSave
		}, {
			text : "重置",
			iconCls : "btn-reset",
			scope : this,
			handler : this.onReset
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			scope : this,
			handler : this.onCancel
		} ];
	},
	show : function() {
		ArchivesDocForm.superclass.show.call(this);
		if (this.fileId) {
			this.docPanel.openDoc(this.fileId);
		}
	},
	onReset : function() {
		this.formPanel.getForm().reset();
	},
	onCancel : function() {
		this.close();
	},
	onSave : function() {
		var c = this;
		var f = this.callback;
		if (this.formPanel.getForm().isValid()) {
			var b = null;
			var a = null;
			var e = this.formPanel.getCmpByName("archivesDoc.docName")
					.getValue();
			var d = this.docPanel.saveDoc( {
				fileId : a,
				docName : e,
				doctype : "doc"
			});
			if (d && d.success) {
				a = d.fileId;
				b = d.filePath;
				Ext.getCmp("docPath").setValue(b);
			} else {
				Ext.ux.Toast.msg("操作信息", "保存文档出错！");
				return;
			}
			this.formPanel.getForm().submit( {
				method : "POST",
				params : {
					docPath : b,
					fileId : a
				},
				success : function(h, i) {
					Ext.ux.Toast.msg("操作信息", "成功保存附加文档！");
					var g = Ext.util.JSON.decode(i.response.responseText);
					f.call(this, g.data);
					c.close();
				},
				failure : function(g, h) {
					Ext.MessageBox.show( {
						title : "操作信息",
						msg : "信息保存出错，请联系管理员！",
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
				}
			});
		}
	},
	insertNewDoc : function(a, c) {
		var b;
		if (a.recordType) {
			b = new a.recordType();
			b.data = {};
			b.data["docId"] = c.docId;
			b.data["fileId"] = c.fileId;
			b.data["docPath"] = c.docPath;
			b.data["docName"] = c.docName;
			b.data["curVersion"] = c.curVersion ? c.curVersion : 1;
			b.data.newRecord = true;
			b.commit();
			a.add(b);
		}
	}
});