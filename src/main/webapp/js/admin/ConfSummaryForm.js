ConfSummaryForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						ConfSummaryForm.superclass.constructor.call(this, {
							id : "ConfSummaryFormWin",
							iconCls : "menu-confSummary",
							layout : "fit",
							items : this.formPanel,
							modal : true,
							height : 350,
							width : 400,
							maximizable : false,
							resizable : false,
							title : "编辑会议纪要",
							buttonAlign : "center",
							buttons : this.buttons,
							keys : {
								key : Ext.EventObject.ENTER,
								fn : this.save(this.formPanel, this, 0),
								scope : this
							}
						});
					},
					initUIComponents : function() {
						this.formPanel = new Ext.FormPanel(
								{
									layout : "form",
									bodyStyle : "padding:10px 10px 10px 10px",
									border : false,
									id : "ConfSummaryForm",
									defaults : {
										anchor : "98%,98%",
										width : 250
									},
									items : [
											{
												name : "confSummary.sumId",
												xtype : "hidden",
												value : this.sumId == null ? ""
														: this.sumId
											},
											{
												fieldLabel : "会议议题",
												name : "confSummary.confId.confTopic",
												xtype : "textfield",
												readOnly : true
											},
											{
												xtype : "hidden",
												name : "confSummary.confId.confId"
											},
											{
												fieldLabel : "纪要创建时间",
												name : "confSummary.createtime",
												xtype : "textfield",
												readOnly : true
											},
											{
												xtype : "container",
												layout : "hbox",
												items : [
														{
															xtype : "label",
															text : "纪要人:"
														},
														{
															style : "margin-left:65px;width: 200px;",
															xtype : "textfield",
															name : "confSummary.creator",
															readOnly : true,
															allowBlank : false,
															blankText : "请选择纪要人",
															maxLength : 256,
															maxLengthText : "纪要人输入不能超过256个字符长度！"
														},
														{
															style : "margin-left: 50px;",
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						a,
																						b) {
																					Ext
																							.getCmp(
																									"ConfSummaryForm")
																							.getCmpByName(
																									"confSummary.creator")
																							.setValue(
																									b);
																				})
																		.show();
															}
														} ]
											},
											{
												xtype : "textarea",
												fieldLabel : "纪要内容",
												name : "confSummary.sumContent",
												width : 250,
												allowBlank : false,
												blankText : "会议纪要内容不能为空！",
												maxLength : 4000,
												maxLengthText : "会议纪要内容不能超过4000个字符！"
											},
											{
												fieldLabel : "状态",
												hiddenName : "confSummary.status",
												xtype : "combo",
												mode : "local",
												readOnly : true,
												editable : false,
												triggerAction : "all",
												store : [ [ "0", "未发送" ],
														[ "1", "发送" ] ]
											},
											{
												xtype : "compositefield",
												fieldLabel : "附件",
												items : [
														{
															xtype : "panel",
															id : "confSummaryFormFilePanel",
															name : "confSummaryFormFilePanel",
															border : true,
															height : 50,
															width : 190
														},
														{
															xtype : "button",
															iconCls : "btn-upload",
															text : "上传",
															handler : this.upLoadFile
																	.createCallback(this)
														}, {
															xtype : "hidden",
															name : "fileIds"
														} ]
											} ]
								});
						if (this.sumId != null && this.sumId != "undefined") {
							this.formPanel.loadData( {
								url : __ctxPath
										+ "/admin/getConfSummary.do?sumId="
										+ this.sumId,
								root : "data",
								preName : "confSummary",
								success : function(a, c) {
									var b = Ext.util.JSON
											.decode(a.responseText);
									ConfSummaryForm
											.setFilePanel(b.data.attachFiles);
								},
								failure : function(a, b) {
									Ext.ux.Toast.msg("操作提示", "对不起，数据加载有误！");
								}
							});
						}
						this.buttons = [
								{
									text : "保存",
									iconCls : "btn-save",
									handler : this.save.createCallback(
											this.formPanel, this, 0)
								},
								{
									text : "发送",
									iconCls : "btn-mail_send",
									handler : this.save.createCallback(
											this.formPanel, this, 1)
								}, {
									text : "取消",
									iconCls : "btn-cancel",
									handler : this.cancel.createCallback(this)
								} ];
					},
					cancel : function(a) {
						a.close();
					},
					save : function(a, d, c) {
						var b = c == 0 ? __ctxPath
								+ "/admin/editConfSummary.do" : __ctxPath
								+ "/admin/sendConfSummary.do";
						if (a.getForm().isValid()) {
							a.getForm().submit(
									{
										url : b,
										method : "post",
										waitMsg : "正在提交数据，请稍后...",
										success : function(e, g) {
											Ext.ux.Toast.msg("操作信息",
													c == 0 ? "会议纪要保存成功！"
															: "会议纪要发送成功！");
											var f = Ext
													.getCmp("ConfSummaryGrid");
											if (f != null) {
												f.getStore().reload();
											}
											d.close();
										},
										failure : function(e, f) {
											Ext.MessageBox.show( {
												title : "操作信息",
												msg : "信息发送出错，请联系管理员！",
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.ERROR
											});
											d.close();
										}
									});
						}
					},
					upLoadFile : function(b) {
						var a = Ext.getCmp("ConfSummaryForm");
						var c = App
								.createUploadDialog( {
									file_cat : "admin/confSummary",
									callback : function(d) {
										var e = "";
										var g = Ext
												.getCmp("confSummaryFormFilePanel");
										for ( var f = 0; f < records.length; f++) {
											e += d[f].fileId + ",";
											Ext.DomHelper
													.append(
															g.body,
															'<span><a href="#" onclick="FileAttachDetail.show('
																	+ d[f].fileId
																	+ ')">'
																	+ d[f].fileName
																	+ '</a><img class="img-delete" src="'
																	+ __ctxPath
																	+ '/images/system/delete.gif" onclick="removeResumeFile(this,'
																	+ d[f].fileId
																	+ ')"/>&nbsp;|&nbsp;</span>');
										}
										a.getCmpByName("fileIds").setValue(
												e.substring(0, e.length - 1));
									}
								});
						c.show("confSummaryForm");
					}
				});
function removeResumeFile(e, a) {
	var b = Ext.getCmp("ConfSummaryForm").getCmpByName("fileIds");
	var d = b.getValue();
	if (d.indexOf(",") < 0) {
		b.setValue("");
	} else {
		d = d.replace("," + a, "").replace(a + ",", "");
		b.setValue(d);
	}
	alert(b);
	var c = Ext.get(e.parentNode);
	c.remove();
}
ConfSummaryForm.setFilePanel = function(b) {
	var c = "";
	var e = Ext.getCmp("confSummaryFormFilePanel");
	for ( var d = 0; d < b.length; d++) {
		c += b[d].fileId + ",";
		var a = '<img class="img-delete" src="' + __ctxPath
				+ '/images/system/delete.gif" onclick="removeResumeFile(this,'
				+ b[d].fileId + ')"/>';
		Ext.DomHelper.append(e.body,
				'<span><a href="#" onclick="FileAttachDetail.show('
						+ b[d].fileId + ')">' + b[d].fileName + "</a>" + a
						+ "&nbsp;|&nbsp;</span>");
	}
	Ext.getCmp("ConfSummaryForm").getCmpByName("fileIds").setValue(
			c.substring(0, c.length - 1));
};