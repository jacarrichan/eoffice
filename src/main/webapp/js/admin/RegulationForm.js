RegulationForm = Ext
		.extend(
				Ext.Window,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						RegulationForm.superclass.constructor.call(this, {
							id : "RegulationFormWin",
							layout : "fit",
							items : this.formPanel,
							modal : true,
							height : 577,
							iconCls : "menu-regulation",
							width : 1000,
							maximizable : true,
							title : "规章制度详细信息",
							buttonAlign : "center",
							buttons : [
									{
										text : "草稿",
										iconCls : "btn-save",
										scope : this,
										handler : this.save.createCallback(0,
												this.formPanel, this)
									},
									{
										text : "生效",
										iconCls : "btn-save",
										scope : this,
										handler : this.save.createCallback(1,
												this.formPanel, this)
									}, {
										text : "重置",
										iconCls : "btn-reset",
										scope : this,
										handler : this.reset
									}, {
										text : "取消",
										iconCls : "btn-cancel",
										scope : this,
										handler : this.cancel
									} ]
						});
					},
					initUIComponents : function() {
						this.formPanel = new Ext.FormPanel(
								{
									layout : "form",
									bodyStyle : "padding:10px",
									border : false,
									autoScroll : true,
									defaults : {
										anchor : "98%,96%"
									},
									defaultType : "textfield",
									items : [
											{
												name : "regulation.regId",
												xtype : "hidden",
												value : this.regId == null ? ""
														: this.regId
											},
											{
												fieldLabel : "状态",
												name : "regulation.status",
												xtype : "hidden"
											},
											{
												name : "regulation.proTypeId",
												xtype : "hidden"
											},
											{
												name : "regulation.issueUserId",
												xtype : "hidden",
												value : curUserInfo.userId
											},
											{
												name : "regulation.issueDepId",
												xtype : "hidden",
												value : curUserInfo.depId
											},
											{
												name : "regulation.recDepIds",
												xtype : "hidden",
												maxLength : 1024
											},
											{
												name : "regulation.recUserIds",
												xtype : "hidden",
												maxLength : 1024
											},
											{
												name : "regAttachsFileIds",
												id : "regAttachsFileIds",
												xtype : "hidden"
											},
											{
												xtype : "compositefield",
												fieldLabel : "制度类型",
												items : [
														{
															name : "regulation.proTypeName",
															xtype : "textfield",
															width : 250,
															readOnly : true,
															allowBlank : false
														},
														{
															xtype : "button",
															text : "选择类型",
															iconCls : "btn-select",
															scope : this,
															handler : function() {
																var b = this.formPanel;
																new GlobalTypeSelector(
																		{
																			catKey : "REGULATION",
																			isSingle : true,
																			callback : function(
																					d,
																					c) {
																				b
																						.getCmpByName(
																								"regulation.proTypeId")
																						.setValue(
																								d);
																				b
																						.getCmpByName(
																								"regulation.proTypeName")
																						.setValue(
																								c);
																			}
																		})
																		.show();
															}
														},
														{
															xtype : "displayfield",
															value : "关键字",
															width : 60
														},
														{
															xtype : "textfield",
															name : "regulation.keywords",
															maxLength : 256,
															width : 250
														},
														{
															xtype : "displayfield",
															value : "发布日期",
															width : 60
														},
														{
															fieldLabel : "",
															name : "regulation.issueDate",
															xtype : "datefield",
															format : "Y-m-d",
															value : new Date()
														} ]
											},
											{
												xtype : "compositefield",
												fieldLabel : "发布人",
												items : [
														{
															value : curUserInfo.fullname,
															width : 250,
															xtype : "textfield",
															readOnly : true,
															name : "regulation.issueFullname",
															maxLength : 64
														},
														{
															xtype : "button",
															iconCls : "btn-select",
															text : "选择人员",
															scope : this,
															handler : function() {
																var b = this.formPanel;
																UserSelector
																		.getView(
																				function(
																						d,
																						c) {
																					b
																							.getCmpByName(
																									"regulation.issueFullname")
																							.setValue(
																									c);
																					b
																							.getCmpByName(
																									"regulation.issueUserId")
																							.setValue(
																									d);
																				},
																				true)
																		.show();
															}
														},
														{
															xtype : "displayfield",
															value : "发布部门",
															width : 60
														},
														{
															xtype : "textfield",
															name : "regulation.issueDep",
															maxLength : 64,
															width : 250,
															readOnly : true,
															value : curUserInfo.depName
														},
														{
															xtype : "button",
															iconCls : "btn-select",
															text : "选择部门",
															scope : this,
															handler : function() {
																var b = this.formPanel;
																DepSelector
																		.getView(
																				function(
																						c,
																						d) {
																					b
																							.getCmpByName(
																									"regulation.issueDep")
																							.setValue(
																									d);
																					b
																							.getCmpByName(
																									"regulation.issueDepId")
																							.setValue(
																									c);
																				},
																				true)
																		.show();
															}
														} ]
											},
											{
												xtype : "compositefield",
												fieldLabel : "接收部门范围",
												items : [
														{
															name : "regulation.recDeps",
															xtype : "textarea",
															readOnly : true,
															width : 650,
															maxLength : 1024
														},
														{
															xtype : "button",
															text : "选择部门",
															iconCls : "btn-select",
															scope : this,
															handler : function() {
																var b = this.formPanel;
																DepSelector
																		.getView(
																				function(
																						c,
																						d) {
																					b
																							.getCmpByName(
																									"regulation.recDeps")
																							.setValue(
																									d);
																					b
																							.getCmpByName(
																									"regulation.recDepIds")
																							.setValue(
																									c);
																				})
																		.show();
															}
														} ]
											},
											{
												xtype : "compositefield",
												fieldLabel : "接收人范围",
												items : [
														{
															width : 650,
															name : "regulation.recUsers",
															readOnly : true,
															xtype : "textarea",
															maxLength : 1024
														},
														{
															xtype : "button",
															iconCls : "btn-select",
															text : "选择人员",
															scope : this,
															handler : function() {
																a = this.formPanel;
																UserSelector
																		.getView(
																				function(
																						c,
																						b) {
																					a
																							.getCmpByName(
																									"regulation.recUsers")
																							.setValue(
																									b);
																					a
																							.getCmpByName(
																									"regulation.recUserIds")
																							.setValue(
																									c);
																				})
																		.show();
															}
														} ]
											},
											{
												fieldLabel : "标题",
												name : "regulation.subject",
												allowBlank : false,
												maxLength : 256
											},
											{
												fieldLabel : "内容",
												name : "regulation.content",
												xtype : "fckeditor",
												maxLength : 65535
											},
											{
												xtype : "container",
												layout : "column",
												border : false,
												defaults : {
													border : false
												},
												items : [
														{
															columnWidth : 0.7,
															layout : "form",
															border : false,
															items : [ {
																fieldLabel : "附件",
																xtype : "panel",
																name : "regAttachs",
																frame : false,
																border : true,
																bodyStyle : "padding:4px 4px 4px 4px",
																height : 70,
																autoScroll : true,
																html : ""
															} ]
														},
														{
															columnWidth : 0.3,
															items : [
																	{
																		border : false,
																		xtype : "button",
																		text : "添加附件",
																		scope : this,
																		iconCls : "menu-attachment",
																		handler : function() {
																			var c = this.formPanel;
																			var b = App
																					.createUploadDialog( {
																						file_cat : "regulation",
																						callback : function(
																								g) {
																							var d = c
																									.getCmpByName("regAttachsFileIds");
																							var f = c
																									.getCmpByName("regAttachs");
																							for ( var e = 0; e < g.length; e++) {
																								if (d
																										.getValue() != "") {
																									d
																											.setValue(d
																													.getValue()
																													+ ",");
																								}
																								d
																										.setValue(d
																												.getValue()
																												+ g[e].fileId);
																								Ext.DomHelper
																										.append(
																												f.body,
																												'<span><a href="#" onclick="FileAttachDetail.show('
																														+ g[e].fileId
																														+ ')">'
																														+ g[e].filename
																														+ '</a> <img class="img-delete" src="'
																														+ __ctxPath
																														+ '/images/system/delete.gif" onclick="removeFile(this,'
																														+ g[e].fileId
																														+ ')"/>&nbsp;|&nbsp;</span>');
																							}
																						}
																					});
																			b
																					.show(this);
																		}
																	},
																	{
																		xtype : "button",
																		text : "清除附件",
																		scope : this,
																		iconCls : "reset",
																		handler : function() {
																			var d = this.formPanel;
																			var b = d
																					.getCmpByName("regAttachsFileIds");
																			var c = d
																					.getCmpByName("regAttachs");
																			c.body
																					.update("");
																			b
																					.setValue("");
																		}
																	} ]
														} ]
											} ]
								});
						if (this.regId != null && this.regId != "undefined") {
							var a = this.formPanel;
							this.formPanel
									.loadData( {
										url : __ctxPath
												+ "/admin/getRegulation.do?regId="
												+ this.regId,
										root : "data",
										preName : "regulation",
										success : function(c, f) {
											var b = Ext.util.JSON
													.decode(c.responseText).data;
											var e = b.regAttachs;
											var h = a
													.getCmpByName("regAttachs");
											var d = a
													.getCmpByName("regAttachsFileIds");
											for ( var g = 0; g < e.length; g++) {
												if (d.getValue() != "") {
													d.setValue(d.getValue()
															+ ",");
												}
												d.setValue(d.getValue()
														+ e[g].fileId);
												Ext.DomHelper
														.append(
																h.body,
																'<span><a href="#" onclick="FileAttachDetail.show('
																		+ e[g].fileId
																		+ ')">'
																		+ e[g].fileName
																		+ '</a><img class="img-delete" src="'
																		+ __ctxPath
																		+ '/images/system/delete.gif" onclick="removeFile(this,'
																		+ e[g].fileId
																		+ ')"/>&nbsp;|&nbsp;</span>');
											}
										},
										failure : function(b, c) {
											Ext.ux.Toast.msg("编辑", "载入失败");
										}
									});
						}
					},
					reset : function() {
						this.formPanel.getForm().reset();
					},
					cancel : function() {
						this.close();
					},
					save : function(b, a, c) {
						a.getCmpByName("regulation.status").setValue(b);
						$postForm( {
							formPanel : a,
							scope : this,
							url : __ctxPath + "/admin/saveRegulation.do",
							callback : function(d, f) {
								var e = Ext.getCmp("RegulationGrid");
								if (e != null) {
									e.getStore().reload();
								}
								c.close();
							}
						});
					}
				});
function removeFile(e, a) {
	var b = Ext.getCmp("regAttachsFileIds");
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