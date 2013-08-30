var NewPublicDocumentForm = function(b, a) {
	return this.setup(b, a);
};
NewPublicDocumentForm.prototype.setup = function(f, e) {
	var c;
	this.docId = f;
	var d = this.initToolbar();
	var a = new Ext.FormPanel(
			{
				url : __ctxPath + "/document/saveDocument.do",
				layout : "form",
				id : "NewPublicDocumentFormPanel",
				autoScroll : true,
				border : false,
				bodyStyle : "padding:10px 10px 10px 10px;",
				formId : "NewPublicDocumentFormId",
				reader : new Ext.data.JsonReader({
					root : "data"
				}, [ {
					name : "NewPublicDocumentForm.docId",
					mapping : "docId"
				}, {
					name : "NewPublicDocumentForm.docName",
					mapping : "docName"
				}, {
					name : "NewPublicDocumentForm.content",
					mapping : "content"
				} ]),
				items : [
						{
							xtype : "hidden",
							id : "NewPublicDocumentForm.docId",
							name : "document.docId"
						},
						{
							xtype : "hidden",
							id : "NewPublicDocumentForm.folderId",
							name : "folderId"
						},
						{
							xtype : "container",
							layout : "column",
							height : 32,
							items : [
									{
										text : "选择目录:",
										xtype : "label",
										width : 104
									},
									{
										name : "docFolderName",
										id : "NewPublicDocumentForm.docFolderName",
										xtype : "textfield",
										width : 430,
										readOnly : true,
										allowBlank : false
									},
									{
										xtype : "button",
										text : "请选择目录",
										iconCls : "menu-mail_folder",
										handler : function() {
											DocFolderSelector
													.getView(
															function(j, g) {
																var i = Ext
																		.getCmp("NewPublicDocumentForm.docFolderName");
																var h = Ext
																		.getCmp("NewPublicDocumentForm.folderId");
																i.setValue(g);
																h.setValue(j);
															}).show();
										}
									},
									{
										xtype : "button",
										text : "清除目录",
										iconCls : "reset",
										handler : function() {
											var h = Ext
													.getCmp("NewPublicDocumentForm.docFolderName");
											var g = Ext
													.getCmp("NewPublicDocumentForm.folderId");
											h.setValue("");
											g.setValue("");
										}
									} ]
						},
						{
							fieldLabel : "文档名称",
							name : "document.docName",
							id : "NewPublicDocumentForm.docName",
							xtype : "textfield",
							anchor : "98%",
							allowBlank : false
						},
						{
							height : 350,
							anchor : "96%",
							xtype : "fckeditor",
							fieldLabel : "内容",
							name : "document.content",
							allowBlank : false,
							id : "NewPublicDocumentForm.content"
						},
						{
							layout : "column",
							border : false,
							anchor : "98%",
							items : [
									{
										columnWidth : 0.7,
										layout : "form",
										border : false,
										items : [ {
											fieldLabel : "附件",
											xtype : "panel",
											id : "NewPublicDocumentForm.filePanel",
											frame : false,
											height : 80,
											autoScroll : true,
											html : ""
										} ]
									},
									{
										columnWidth : 0.3,
										border : false,
										items : [
												{
													xtype : "button",
													text : "添加附件",
													iconCls : "menu-attachment",
													handler : function() {
														var g = App
																.createUploadDialog({
																	file_cat : "document",
																	callback : function(
																			l) {
																		var h = Ext
																				.getCmp("NewPublicDocumentForm.fileIds");
																		var k = Ext
																				.getCmp("NewPublicDocumentForm.filePanel");
																		for ( var j = 0; j < l.length; j++) {
																			if (h
																					.getValue() != "") {
																				h
																						.setValue(h
																								.getValue()
																								+ ",");
																			}
																			h
																					.setValue(h
																							.getValue()
																							+ l[j].fileId);
																			Ext.DomHelper
																					.append(
																							k.body,
																							'<span><a href="#" onclick="FileAttachDetail.show('
																									+ l[j].fileId
																									+ ')">'
																									+ l[j].filename
																									+ '</a> <img class="img-delete" src="'
																									+ __ctxPath
																									+ '/images/system/delete.gif" onclick="removeFile(this,'
																									+ l[j].fileId
																									+ ')"/>&nbsp;|&nbsp;</span>');
																		}
																	}
																});
														g.show(this);
													}
												},
												{
													xtype : "button",
													text : "清除附件",
													iconCls : "reset",
													handler : function() {
														var h = Ext
																.getCmp("NewPublicDocumentForm.fileIds");
														var g = Ext
																.getCmp("NewPublicDocumentForm.filePanel");
														g.body.update("");
														h.setValue("");
													}
												},
												{
													xtype : "hidden",
													id : "NewPublicDocumentForm.fileIds",
													name : "fileIds"
												} ]
									} ]
						} ]
			});
	if (this.docId != null && this.docId != "undefined") {
		a
				.getForm()
				.load(
						{
							deferredRender : false,
							url : __ctxPath + "/document/getDocument.do?docId="
									+ this.docId,
							waitMsg : "正在载入数据...",
							success : function(h, n) {
								var q = Ext.util.JSON
										.decode(n.response.responseText).data[0];
								var j = q.docFolder;
								var r = q.attachFiles;
								var p = Ext
										.getCmp("NewPublicDocumentForm.filePanel");
								var s = Ext
										.getCmp("NewPublicDocumentForm.fileIds");
								for ( var o = 0; o < r.length; o++) {
									if (s.getValue() != "") {
										s.setValue(s.getValue() + ",");
									}
									s.setValue(s.getValue() + r[o].fileId);
									Ext.DomHelper
											.append(
													p.body,
													'<span><a href="#" onclick="FileAttachDetail.show('
															+ r[o].fileId
															+ ')">'
															+ r[o].fileName
															+ '</a><img class="img-delete" src="'
															+ __ctxPath
															+ '/images/system/delete.gif" onclick="removeFormFile(this,'
															+ r[o].fileId
															+ ')"/>&nbsp;|&nbsp;</span>');
								}
								var g = j.folderId;
								var l = j.folderName;
								var m = Ext
										.getCmp("NewPublicDocumentForm.folderId");
								var k = Ext
										.getCmp("NewPublicDocumentForm.docFolderName");
								m.setValue(g);
								k.setValue(l);
							},
							failure : function(g, h) {
								Ext.MessageBox.show({
									title : "操作信息",
									msg : "载入信息失败，请联系管理员！",
									buttons : Ext.MessageBox.OK,
									icon : "ext-mb-error"
								});
							}
						});
	}
	var b = new Ext.Panel({
		id : "NewPublicDocumentForm",
		title : e == null ? "新建公共文档" : e,
		iconCls : "menu-new-document",
		layout : "fit",
		tbar : d,
		items : [ a ]
	});
	return b;
};
NewPublicDocumentForm.prototype.initToolbar = function() {
	var a = new Ext.Toolbar(
			{
				height : 30,
				items : [
						{
							text : "保存",
							iconCls : "btn-save",
							handler : function() {
								var b = Ext
										.getCmp("NewPublicDocumentFormPanel");
								if (b.getForm().isValid()) {
									b
											.getForm()
											.submit(
													{
														waitMsg : "正在提交,请稍候...",
														success : function(c, f) {
															Ext.ux.Toast.msg(
																	"操作信息",
																	"提交成功！");
															var c = Ext
																	.getCmp("NewPublicDocumentFormPanel");
															c.getForm().reset();
															var e = Ext
																	.getCmp("NewPublicDocumentForm.fileIds");
															var d = Ext
																	.getCmp("NewPublicDocumentForm.filePanel");
															d.body.update("");
															e.setValue("");
														},
														failure : function(c, d) {
															Ext.ux.Toast
																	.msg(
																			"错误信息",
																			d.result.msg);
														}
													});
								}
							}
						},
						{
							text : "重置",
							iconCls : "reset",
							handler : function() {
								var b = Ext
										.getCmp("NewPublicDocumentFormPanel");
								b.getForm().reset();
								var d = Ext
										.getCmp("NewPublicDocumentForm.fileIds");
								var c = Ext
										.getCmp("NewPublicDocumentForm.filePanel");
								c.body.update("");
								d.setValue("");
							}
						} ]
			});
	return a;
};
function removeFormFile(e, a) {
	var b = Ext.getCmp("NewPublicDocumentForm.fileIds");
	var d = b.getValue();
	if (d.indexOf(",") < 0) {
		b.setValue("");
	} else {
		d = d.replace("," + a, "").replace(a + ",", "");
		b.setValue(d);
	}
	var c = Ext.get(e.parentNode);
	c.remove();
}