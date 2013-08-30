AddConferenceView = Ext
		.extend(
				Ext.Panel,
				{
					store : null,
					gridPanel : null,
					formPanel : null,
					basePanel : null,
					contextPanel : null,
					jonerPanel : null,
					grantPanel : null,
					filePanel : null,
					applyPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						AddConferenceView.superclass.constructor.call(this, {
							id : "AddConferenceViewWin",
							layout : "form",
							region : "center",
							iconCls : "menu-conference_add",
							items : this.formPanel,
							modal : true,
							maximizable : true,
							bodyStyle : "padding : 5px 5px 5px 5px",
							autoScroll : true,
							title : "会议申请"
						});
					},
					initUIComponents : function() {
						this.basePanel = new Ext.form.FieldSet(
								{
									id : "addConference.basePanel",
									title : "会议信息",
									layout : "form",
									border : true,
									items : [ {
										layout : "column",
										columnWidth : 1,
										border : false,
										defaults : {
											border : false,
											width : 350
										},
										items : [
												{
													layout : "form",
													columnWidth : 0.5,
													defaults : {
														width : 200
													},
													items : [
															{
																xtype : "hidden",
																name : "conference.confId",
																value : this.confId != null ? this.confId
																		: ""
															},
															{
																xtype : "textfield",
																name : "conference.confTopic",
																fieldLabel : "会议标题",
																allowBlank : false,
																blankText : "会议标题不能为空！",
																maxLength : 128,
																maxLengthText : "会议标题不能超过128个字符长度！"
															},
															{
																xtype : "hidden",
																name : "conference.typeId"
															},
															{
																xtype : "combo",
																name : "conference.confProperty",
																fieldLabel : "会议类型",
																valueField : "typeId",
																displayField : "typeName",
																mode : "local",
																editable : false,
																emptyText : "--请选择会议类型--",
																triggerAction : "all",
																forceSelection : true,
																allowBlank : false,
																blankText : "请选择会议类型！",
																store : new Ext.data.SimpleStore(
																		{
																			url : __ctxPath
																					+ "/admin/getTypeAllConference.do",
																			autoLoad : true,
																			fields : [
																					"typeId",
																					"typeName" ]
																		}),
																listeners : {
																	scope : this,
																	select : function(
																			c,
																			a,
																			b) {
																		this
																				.getCmpByName(
																						"conference.typeId")
																				.setValue(
																						c
																								.getValue());
																	}
																}
															},
															{
																name : "conference.feeBudget",
																fieldLabel : "经费(人民币)",
																text : "0",
																xtype : "numberfield",
																maxLength : 21,
																maxLengthText : "会议经费不能超过21个字符串长度!"
															},
															{
																xtype : "container",
																layout : "hbox",
																width : 350,
																defaults : {
																	margins : "0 20 0 10"
																},
																items : [
																		{
																			xtype : "label",
																			text : "留言方式："
																		},
																		{
																			xtype : "checkbox",
																			boxLabel : "普通留言",
																			name : "conference.isEmail",
																			inputValue : 1,
																			checked : true,
																			width : 100
																		},
																		{
																			xtype : "checkbox",
																			boxLabel : "手机留言",
																			name : "conference.isMobile",
																			inputValue : 1,
																			width : 100
																		} ]
															} ]
												},
												{
													layout : "form",
													columnWidth : 0.5,
													defaults : {
														width : 200
													},
													items : [
															{
																xtype : "combo",
																id : "roomId",
																hiddenName : "conference.roomId",
																fieldLabel : "会议室名称",
																valueField : "roomId",
																displayField : "roomName",
																mode : "local",
																editable : false,
																emptyText : "--请选择会议室--",
																triggerAction : "all",
																forceSelection : true,
																allowBlank : false,
																blankText : "请选择会议室！",
																store : new Ext.data.SimpleStore(
																		{
																			url : __ctxPath
																					+ "/admin/getBoardrooConference.do",
																			autoLoad : true,
																			fields : [
																					"roomId",
																					"roomName" ],
																			listeners : {
																				scope : this,
																				load : function() {
																					var a = Ext
																							.getCmp("roomId");
																					if (a.hiddenField.value) {
																						a
																								.setValue(a.hiddenField.value);
																					}
																				}
																			}
																		}),
																listeners : {
																	select : function(
																			d,
																			a,
																			b) {
																		var c = Ext
																				.getCmp("addConference.basePanel");
																		c
																				.getCmpByName(
																						"conference.roomName")
																				.setValue(
																						d
																								.getRawValue());
																		c
																				.getCmpByName(
																						"conference.roomLocation")
																				.setValue(
																						d
																								.getRawValue());
																	}
																}
															},
															{
																xtype : "textfield",
																readOnly : true,
																fieldLabel : "会议室",
																name : "conference.roomName"
															},
															{
																xtype : "textfield",
																fieldLabel : "地址",
																name : "conference.roomLocation",
																allowBlank : false,
																blankText : "会议室地址不能为空！",
																maxLength : "128",
																maxLengthText : "会议室地址不能超过128个字符串长度！"
															},
															{
																xtype : "radiogroup",
																fieldLabel : "重要级别",
																border : false,
																items : [
																		{
																			boxLabel : "普通",
																			name : "conference.importLevel",
																			inputValue : 0,
																			checked : true
																		},
																		{
																			boxLabel : "重要",
																			name : "conference.importLevel",
																			inputValue : 1
																		} ]
															} ]
												} ]
									} ]
								});
						this.jonerPanel = new Ext.form.FieldSet(
								{
									id : "addConference.jonerPanel",
									title : "参加人员",
									layout : "form",
									border : true,
									items : [
											{
												xtype : "hidden",
												name : "conference.compere"
											},
											{
												xtype : "container",
												layout : "hbox",
												layoutConfigs : {
													align : "middle"
												},
												defaults : {
													margins : "5px 5px 5px 5px "
												},
												items : [
														{
															xtype : "label",
															text : "主持人:"
														},
														{
															xtype : "textfield",
															name : "conference.compereName",
															readOnly : true,
															allowBlank : false,
															blankText : "请选择主持人！",
															width : 300,
															maxLength : 256,
															maxLengthText : "数据长度不能超过256个字符！"
														},
														{
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						a,
																						c) {
																					var b = Ext
																							.getCmp("addConference.jonerPanel");
																					b
																							.getCmpByName(
																									"conference.compere")
																							.setValue(
																									a);
																					b
																							.getCmpByName(
																									"conference.compereName")
																							.setValue(
																									c);
																				})
																		.show();
															}
														} ]
											},
											{
												xtype : "hidden",
												name : "conference.recorder"
											},
											{
												xtype : "container",
												layout : "hbox",
												defaults : {
													margins : "5px 5px 5px 5px"
												},
												layoutConfigs : {
													align : "middle"
												},
												items : [
														{
															xtype : "label",
															text : "记录人:"
														},
														{
															xtype : "textfield",
															name : "conference.recorderName",
															readOnly : true,
															allowBlank : false,
															blankText : "请选择记录人！",
															width : 300,
															maxLength : 256,
															maxLengthText : "数据长度不能超过256个字符！"
														},
														{
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						a,
																						c) {
																					var b = Ext
																							.getCmp("addConference.jonerPanel");
																					b
																							.getCmpByName(
																									"conference.recorder")
																							.setValue(
																									a);
																					b
																							.getCmpByName(
																									"conference.recorderName")
																							.setValue(
																									c);
																				})
																		.show();
															}
														} ]
											},
											{
												xtype : "hidden",
												name : "conference.attendUsers"
											},
											{
												xtype : "container",
												layout : "hbox",
												defaults : {
													margins : "5px 5px 5px 5px"
												},
												layoutConfigs : {
													align : "middle"
												},
												items : [
														{
															xtype : "label",
															text : "参加人:"
														},
														{
															xtype : "textfield",
															name : "conference.attendUsersName",
															readOnly : true,
															allowBlank : false,
															blankText : "请选择参加会议的人员！",
															width : 300,
															maxLength : 4000,
															maxLengthText : "参加人员不能超过4000个字符长度！"
														},
														{
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						a,
																						c) {
																					var b = Ext
																							.getCmp("addConference.jonerPanel");
																					b
																							.getCmpByName(
																									"conference.attendUsers")
																							.setValue(
																									a);
																					b
																							.getCmpByName(
																									"conference.attendUsersName")
																							.setValue(
																									c);
																				})
																		.show();
															}
														} ]
											} ]
								});
						this.grantPanel = new Ext.form.FieldSet(
								{
									id : "addConference.grantPanel",
									title : "权限设置",
									region : "center",
									layout : "form",
									border : true,
									items : [
											{
												xtype : "hidden",
												name : "viewer"
											},
											{
												xtype : "container",
												layout : "hbox",
												defaults : {
													margins : "5px 5px 5px 5px "
												},
												layoutConfigs : {
													align : "middle"
												},
												items : [
														{
															xtype : "label",
															text : "查看人:"
														},
														{
															xtype : "textfield",
															name : "viewers",
															readOnly : true,
															allowBlank : false,
															blankText : "请选择查看人",
															width : 300,
															maxLength : 4000,
															maxLengthText : "输入字符不能超过4000个字符长度！"
														},
														{
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						a,
																						c) {
																					var b = Ext
																							.getCmp("addConference.grantPanel");
																					b
																							.getCmpByName(
																									"viewer")
																							.setValue(
																									a);
																					b
																							.getCmpByName(
																									"viewers")
																							.setValue(
																									c);
																				})
																		.show();
															}
														} ]
											},
											{
												xtype : "hidden",
												name : "updater"
											},
											{
												xtype : "container",
												layout : "hbox",
												layoutConfigs : {
													align : "middle"
												},
												defaults : {
													margins : "5px 5px 5px 5px"
												},
												items : [
														{
															xtype : "label",
															text : "修改人:"
														},
														{
															xtype : "textfield",
															name : "updaters",
															readOnly : true,
															allowBlank : false,
															blankText : "请选择修改人",
															width : 300,
															maxLength : 256,
															maxLengthText : "输入字符不能超过256个字符长度！"
														},
														{
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						a,
																						c) {
																					var b = Ext
																							.getCmp("addConference.grantPanel");
																					b
																							.getCmpByName(
																									"updater")
																							.setValue(
																									a);
																					b
																							.getCmpByName(
																									"updaters")
																							.setValue(
																									c);
																				})
																		.show();
															}
														} ]
											},
											{
												xtype : "hidden",
												name : "summary"
											},
											{
												xtype : "container",
												layout : "hbox",
												defaults : {
													margins : "5 5 5 5"
												},
												layoutConfigs : {
													align : "middle"
												},
												items : [
														{
															xtype : "label",
															text : "纪要人:"
														},
														{
															xtype : "textfield",
															name : "summarys",
															readOnly : true,
															allowBlank : false,
															blankText : "请选择创建纪要人",
															width : 300,
															maxLength : 256,
															maxLengthText : "输入字符不能超过256个字符长度！"
														},
														{
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						a,
																						c) {
																					var b = Ext
																							.getCmp("addConference.grantPanel");
																					b
																							.getCmpByName(
																									"summary")
																							.setValue(
																									a);
																					b
																							.getCmpByName(
																									"summarys")
																							.setValue(
																									c);
																				})
																		.show();
															}
														} ]
											} ]
								});
						this.contextPanel = new Ext.form.FieldSet( {
							title : "时间和内容设置",
							layout : "column",
							columnWidth : 1,
							border : true,
							items : [ {
								columnWidth : 0.5,
								layout : "form",
								border : false,
								items : [ {
									xtype : "datetimefield",
									format : "Y-m-d H:i:s",
									editable : false,
									anchor : "98%,98%",
									name : "conference.startTime",
									fieldLabel : "开始时间",
									allowBlank : false,
									blankText : "请输入会议开始时间！"
								} ]
							}, {
								columnWidth : 0.5,
								layout : "form",
								border : false,
								items : [ {
									anchor : "98%,98%",
									name : "conference.endTime",
									xtype : "datetimefield",
									format : "Y-m-d H:i:s",
									editable : false,
									fieldLabel : "结束时间",
									allowBlank : false,
									blankText : "请输入会议结束时间！"
								} ]
							}, {
								columnWidth : 1,
								layout : "form",
								height : 100,
								width : 550,
								border : false,
								items : [ {
									name : "conference.confContent",
									xtype : "htmleditor",
									height : 100,
									width : 800,
									fieldLabel : "会议内容",
									allowBlank : false,
									blankText : "请输入会议内容！",
									maxLength : 4000,
									maxLengthText : "会议内容不能超过4000个字符长度！"
								} ]
							} ]
						});
						this.filePanel = new Ext.form.FieldSet( {
							id : "addConferenceView.filePath",
							layout : "form",
							region : "center",
							title : "附件信息",
							bodyStyle : "padding : 5px 5px 5px 5px",
							items : [ {
								columnWidth : 1,
								layout : "column",
								border : false,
								items : [ {
									xtype : "hidden",
									name : "filePath"
								}, {
									columnWidth : 0.8,
									xtype : "panel",
									id : "resumeFilePanel",
									height : 50,
									border : true,
									autoScroll : true,
									html : ""
								}, {
									columnWidth : 0.1,
									xtype : "button",
									iconCls : "btn-upload",
									text : "上传",
									handler : this.upLoadFile
								} ]
							} ]
						});
						this.applyPanel = new Ext.form.FieldSet(
								{
									id : "addConferenceView.applyPanel",
									region : "center",
									layout : "form",
									border : true,
									title : "审核人员",
									items : [
											{
												xtype : "hidden",
												name : "conference.checkUserId"
											},
											{
												xtype : "container",
												layout : "hbox",
												defaults : {
													margins : "5 5 5 5"
												},
												layoutConfigs : {
													align : "middle"
												},
												items : [
														{
															xtype : "label",
															text : "审核人:"
														},
														{
															xtype : "textfield",
															name : "conference.checkName",
															readOnly : true,
															allowBlank : false,
															blankText : "请选择审核人",
															width : 300,
															maxLength : 64,
															maxLengthText : "审核人名称不能超过64个字符"
														},
														{
															xtype : "button",
															text : "请选择",
															iconCls : "btn-user-sel",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						a,
																						c) {
																					var b = Ext
																							.getCmp("addConferenceView.applyPanel");
																					b
																							.getCmpByName(
																									"conference.checkUserId")
																							.setValue(
																									a);
																					b
																							.getCmpByName(
																									"conference.checkName")
																							.setValue(
																									c);
																				},
																				true)
																		.show();
															}
														} ]
											} ]
								});
						this.formPanel = new Ext.FormPanel(
								{
									id : "AddConferenceViewPanel",
									autoScroll : true,
									layout : "form",
									region : "center",
									bodyStyle : "padding-left: 5%;padding-right : 100px",
									border : false,
									defaults : {
										readOnly : true
									},
									items : [ {
										id : "addConferenceViewAllfieldset",
										xtype : "fieldset",
										title : "会议申请",
										bodyStyle : "padding:5px 5px 5px 5px",
										layout : "form",
										buttonAlign : "center",
										items : [
												this.basePanel,
												this.contextPanel,
												{
													layout : "column",
													border : false,
													columnWidth : 1,
													defaults : {
														border : false
													},
													items : [
															{
																columnWidth : 0.5,
																width : "360",
																layout : "form",
																items : [ this.jonerPanel ]
															},
															{
																width : "360",
																columnWidth : 0.5,
																layout : "form",
																items : [ this.grantPanel ]
															} ]
												}, this.applyPanel,
												this.filePanel ],
										buttons : [
												{
													text : "暂存会议信息",
													iconCls : "temp",
													handler : this.save
															.createCallback(this)
												},
												{
													text : "发送会议通知",
													iconCls : "btn-mail_send",
													handler : this.send
															.createCallback(this)
												}, {
													text : "重置",
													iconCls : "btn-reset",
													handler : this.reset
												} ]
									} ]
								});
					},
					reset : function() {
						Ext.getCmp("AddConferenceViewPanel").getForm().reset();
						Ext.getCmp("resumeFilePanel").body.update("");
					},
					save : function(b) {
						var a = Ext.getCmp("AddConferenceViewPanel");
						if (a.getForm().isValid()) {
							if (a.getCmpByName("conference.startTime").value >= a
									.getCmpByName("conference.endTime").value) {
								Ext.ux.Toast.msg("操作提示", "对不起，开会时间有误，请重新输入！");
								a.getCmpByName("conference.startTime").focus(
										true);
								return;
							}
							if (!AddConferenceView.validateCompereAndRecorder()) {
								return;
							}
							a
									.getForm()
									.submit(
											{
												url : __ctxPath
														+ "/admin/tempConference.do",
												method : "post",
												waitMsg : "数据正在保存，请稍后...",
												success : function(c, d) {
													Ext.ux.Toast.msg("操作信息",
															"成功保存信息！");
													Ext
															.getCmp(
																	"AddConferenceViewPanel")
															.getForm().reset();
													Ext
															.getCmp("resumeFilePanel").body
															.update("");
													App
															.clickTopTab("ZanCunConferenceView");
												},
												failure : function(c, d) {
													Ext.MessageBox
															.show( {
																title : "操作信息",
																msg : Ext.util.JSON
																		.decode(d.response.responseText).msg,
																buttons : Ext.MessageBox.OK,
																icon : "ext-mb-error"
															});
												}
											});
						}
					},
					send : function(b) {
						var a = Ext.getCmp("AddConferenceViewPanel");
						if (a.getForm().isValid()) {
							if (a.getCmpByName("conference.startTime").value >= a
									.getCmpByName("conference.endTime").value) {
								Ext.ux.Toast.msg("操作提示", "对不起，开会时间有误，请重新输入！");
								a.getCmpByName("conference.startTime").focus(
										true);
								return;
							}
							if (!AddConferenceView.validateCompereAndRecorder()) {
								return;
							}
							a
									.getForm()
									.submit(
											{
												url : __ctxPath
														+ "/admin/sendConference.do",
												method : "post",
												waitMsg : "数据正在发送，请稍后...",
												success : function(c, d) {
													Ext.ux.Toast.msg("操作信息",
															"成功发送会议申请信息,等待审批！");
													Ext
															.getCmp(
																	"AddConferenceViewPanel")
															.getForm().reset();
													Ext
															.getCmp("resumeFilePanel").body
															.update("");
													App
															.clickTopTab("DaiConfApplyView");
												},
												failure : function(c, d) {
													Ext.MessageBox
															.show( {
																title : "操作信息",
																msg : Ext.util.JSON
																		.decode(d.response.responseText).msg,
																buttons : Ext.MessageBox.OK,
																icon : "ext-mb-error"
															});
												}
											});
						}
					},
					upLoadFile : function() {
						var a = App
								.createUploadDialog( {
									file_cat : "admin/conference",
									callback : function(b) {
										var c = "";
										var e = Ext.getCmp("resumeFilePanel");
										for ( var d = 0; d < b.length; d++) {
											c += b[d].fileId + ",";
											Ext.DomHelper
													.append(
															e.body,
															'<span><a href="#" onclick="FileAttachDetail.show('
																	+ b[d].fileId
																	+ ')">'
																	+ b[d].filename
																	+ '</a><img class="img-delete" src="'
																	+ __ctxPath
																	+ '/images/system/delete.gif" onclick="removeResumeFile(this,'
																	+ b[d].fileId
																	+ ')"/>&nbsp;|&nbsp;</span>');
										}
										Ext
												.getCmp(
														"addConferenceView.filePath")
												.getCmpByName("filePath")
												.setValue(
														c.substring(0,
																c.length - 1));
									}
								});
						a.show("querybtn");
					}
				});
AddConferenceView.validateCompereAndRecorder = function() {
	var d = Ext.getCmp("addConference.jonerPanel");
	var b = d.getCmpByName("conference.compere").value.split(",");
	var a = d.getCmpByName("conference.recorder").value.split(",");
	var e = true;
	if (b.length == 1 && a.length == 1) {
		if (b[0].search(a) >= 0) {
			e = false;
		}
	} else {
		if (b.length == 1 && a.length != 1) {
			for ( var c = 0; c < a.length; c++) {
				if (a[c].search(b) >= 0) {
					e = false;
				}
			}
		} else {
			if (b.length != 1 && a.length == 1) {
				for ( var c = 0; c < b.length; c++) {
					if (b[c].search(a) >= 0) {
						e = false;
					}
				}
			}
		}
	}
	if (e == false) {
		d.getCmpByName("conference.compereName").focus(true);
		Ext.ux.Toast.msg("操作提示", "对不起，会议主持人和记录人不能出现重复，请重新选择！");
	}
	return e;
};
function removeResumeFile(e, a) {
	var b = Ext.getCmp("addConferenceView.filePath").getCmpByName("filePath");
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