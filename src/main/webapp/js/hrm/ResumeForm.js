var ResumeForm = function(b) {
	this.resumeId = b;
	var c = this.setup();
	var d = new Ext.Toolbar( {
		id : "ResumeFormToolbar",
		items : [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var e = Ext.getCmp("ResumeForm");
				if (e.getForm().isValid()) {
					e.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(f, g) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("ResumeGrid").getStore().reload();
							window.close();
						},
						failure : function(f, g) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
							window.close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				var e = Ext.getCmp("centerTabPanel");
				var f = Ext.getCmp("ResumeFormPanel");
				if (f != null) {
					e.remove("ResumeFormPanel");
				}
			}
		} ]
	});
	var a = new Ext.Panel( {
		id : "ResumeFormPanel",
		iconCls : "menu-resume",
		title : "简历详细信息",
		width : 500,
		tbar : d,
		height : 420,
		modal : true,
		layout : "anchor",
		plain : true,
		bodyStyle : "padding:5px;",
		buttonAlign : "center",
		items : [ this.setup() ]
	});
	return a;
};
ResumeForm.prototype.setup = function() {
	var a = new Ext.FormPanel(
			{
				url : __ctxPath + "/hrm/saveResume.do",
				layout : "form",
				id : "ResumeForm",
				defaults : {
					anchor : "98%,98%"
				},
				reader : new Ext.data.JsonReader( {
					root : "data"
				}, [ {
					name : "ResumeForm.resumeId",
					mapping : "resumeId"
				}, {
					name : "ResumeForm.registor",
					mapping : "registor"
				}, {
					name : "ResumeForm.regTime",
					mapping : "regTime"
				}, {
					name : "ResumeForm.status",
					mapping : "status"
				}, {
					name : "ResumeForm.fullname",
					mapping : "fullname"
				}, {
					name : "ResumeForm.age",
					mapping : "age"
				}, {
					name : "ResumeForm.birthday",
					mapping : "birthday"
				}, {
					name : "ResumeForm.birthPlace",
					mapping : "birthPlace"
				}, {
					name : "ResumeForm.startWorkDate",
					mapping : "startWorkDate"
				}, {
					name : "ResumeForm.idNo",
					mapping : "idNo"
				}, {
					name : "ResumeForm.religion",
					mapping : "religion"
				}, {
					name : "ResumeForm.party",
					mapping : "party"
				}, {
					name : "ResumeForm.nationality",
					mapping : "nationality"
				}, {
					name : "ResumeForm.race",
					mapping : "race"
				}, {
					name : "ResumeForm.sex",
					mapping : "sex"
				}, {
					name : "ResumeForm.position",
					mapping : "position"
				}, {
					name : "ResumeForm.photo",
					mapping : "photo"
				}, {
					name : "ResumeForm.address",
					mapping : "address"
				}, {
					name : "ResumeForm.zip",
					mapping : "zip"
				}, {
					name : "ResumeForm.email",
					mapping : "email"
				}, {
					name : "ResumeForm.phone",
					mapping : "phone"
				}, {
					name : "ResumeForm.mobile",
					mapping : "mobile"
				}, {
					name : "ResumeForm.eduCollege",
					mapping : "eduCollege"
				}, {
					name : "ResumeForm.eduDegree",
					mapping : "eduDegree"
				}, {
					name : "ResumeForm.eduMajor",
					mapping : "eduMajor"
				}, {
					name : "ResumeForm.hobby",
					mapping : "hobby"
				}, {
					name : "ResumeForm.workCase",
					mapping : "workCase"
				}, {
					name : "ResumeForm.trainCase",
					mapping : "trainCase"
				}, {
					name : "ResumeForm.projectCase",
					mapping : "projectCase"
				}, {
					name : "ResumeForm.memo",
					mapping : "memo"
				} ]),
				bodyStyle : "padding-left:10px;",
				formId : "ResumeFormId",
				defaultType : "textfield",
				items : [
						{
							name : "resume.resumeId",
							id : "ResumeForm.resumeId",
							xtype : "hidden",
							value : this.resumeId == null ? "" : this.resumeId
						},
						{
							xtype : "hidden",
							name : "resume.registor",
							id : "ResumeForm.registor"
						},
						{
							xtype : "hidden",
							name : "resume.regTime",
							id : "regTime"
						},
						{
							xtype : "container",
							layout : "column",
							height : 40,
							items : [
									{
										xtype : "label",
										style : "padding-left:48%;",
										html : '<span style="font-size:28px;color:blue;">简历</span>'
									},
									{
										xtype : "container",
										width : 150,
										style : "float:right;",
										layout : "column",
										items : [
												{
													xtype : "label",
													width : 30,
													text : "状态:",
													style : "padding-top:3px;"
												},
												{
													width : 100,
													xtype : "combo",
													name : "resume.status",
													id : "ResumeForm.status",
													editable : false,
													mode : "local",
													triggerAction : "all",
													store : [ "通过", "未通过",
															"准备安排面试", "通过面试" ],
													value : "未通过"
												} ]
									} ]
						},
						{
							xtype : "fieldset",
							title : "基本资料",
							layout : "column",
							items : [
									{
										xtype : "container",
										columnWidth : 0.37,
										defaultType : "textfield",
										layout : "form",
										defaults : {
											anchor : "100%,100%"
										},
										items : [ {
											fieldLabel : "姓名",
											name : "resume.fullname",
											id : "ResumeForm.fullname",
											allowBlank : false,
											blankText : "姓名不可为空!"
										}, {
											fieldLabel : "年龄",
											name : "resume.age",
											id : "ResumeForm.age",
											xtype : "numberfield"
										}, {
											fieldLabel : "生日",
											name : "resume.birthday",
											id : "ResumeForm.birthday",
											xtype : "datefield",
											format : "Y-m-d"
										}, {
											fieldLabel : "籍贯",
											name : "resume.birthPlace",
											id : "ResumeForm.birthPlace"
										}, {
											fieldLabel : "参加工作时间",
											name : "resume.startWorkDate",
											id : "ResumeForm.startWorkDate",
											xtype : "datefield",
											format : "Y-m-d"
										}, {
											fieldLabel : "身份证",
											name : "resume.idNo",
											id : "ResumeForm.idNo"
										} ]
									},
									{
										xtype : "container",
										columnWidth : 0.37,
										defaultType : "textfield",
										layout : "form",
										defaults : {
											anchor : "100%,100%"
										},
										items : [
												{
													fieldLabel : "宗教信仰",
													name : "resume.religion",
													id : "ResumeForm.religion",
													maxHeight : 200,
													xtype : "combo",
													mode : "local",
													editable : true,
													triggerAction : "all",
													store : [],
													listeners : {
														focus : function(d) {
															var c = Ext
																	.getCmp(
																			"ResumeForm.religion")
																	.getStore();
															if (c.getCount() <= 0) {
																Ext.Ajax
																		.request( {
																			url : __ctxPath
																					+ "/system/loadDictionary.do",
																			method : "post",
																			params : {
																				itemName : "宗教信仰"
																			},
																			success : function(
																					f) {
																				var e = Ext.util.JSON
																						.decode(f.responseText);
																				c
																						.loadData(e);
																			}
																		});
															}
														}
													}
												},
												{
													fieldLabel : "政治面貌",
													name : "resume.party",
													id : "ResumeForm.party",
													maxHeight : 200,
													xtype : "combo",
													mode : "local",
													editable : true,
													triggerAction : "all",
													store : [],
													listeners : {
														focus : function(d) {
															var c = Ext
																	.getCmp(
																			"ResumeForm.party")
																	.getStore();
															if (c.getCount() <= 0) {
																Ext.Ajax
																		.request( {
																			url : __ctxPath
																					+ "/system/loadDictionary.do",
																			method : "post",
																			params : {
																				itemName : "政治面貌"
																			},
																			success : function(
																					f) {
																				var e = Ext.util.JSON
																						.decode(f.responseText);
																				c
																						.loadData(e);
																			}
																		});
															}
														}
													}
												},
												{
													fieldLabel : "国籍",
													name : "resume.nationality",
													id : "ResumeForm.nationality",
													maxHeight : 200,
													xtype : "combo",
													mode : "local",
													editable : true,
													triggerAction : "all",
													store : [],
													listeners : {
														focus : function(d) {
															var c = Ext
																	.getCmp(
																			"ResumeForm.nationality")
																	.getStore();
															if (c.getCount() <= 0) {
																Ext.Ajax
																		.request( {
																			url : __ctxPath
																					+ "/system/loadDictionary.do",
																			method : "post",
																			params : {
																				itemName : "国籍"
																			},
																			success : function(
																					f) {
																				var e = Ext.util.JSON
																						.decode(f.responseText);
																				c
																						.loadData(e);
																			}
																		});
															}
														}
													}
												},
												{
													fieldLabel : "民族",
													name : "resume.race",
													id : "ResumeForm.race",
													maxHeight : 200,
													xtype : "combo",
													mode : "local",
													editable : true,
													triggerAction : "all",
													store : [],
													listeners : {
														focus : function(d) {
															var c = Ext
																	.getCmp(
																			"ResumeForm.race")
																	.getStore();
															if (c.getCount() <= 0) {
																Ext.Ajax
																		.request( {
																			url : __ctxPath
																					+ "/system/loadDictionary.do",
																			method : "post",
																			params : {
																				itemName : "民族"
																			},
																			success : function(
																					f) {
																				var e = Ext.util.JSON
																						.decode(f.responseText);
																				c
																						.loadData(e);
																			}
																		});
															}
														}
													}
												}, {
													fieldLabel : "性别",
													name : "resume.sex",
													id : "ResumeForm.sex",
													xtype : "combo",
													editable : false,
													mode : "local",
													triggerAction : "all",
													store : [ "男", "女" ]
												}, {
													fieldLabel : "职位名称",
													name : "resume.position",
													id : "ResumeForm.position"
												} ]
									},
									{
										name : "resume.photo",
										id : "ResumeForm.photo",
										xtype : "hidden"
									},
									{
										xtype : "container",
										columnWidth : 0.26,
										layout : "column",
										items : [
												{
													xtype : "label",
													text : "个人相片:",
													style : "padding-left:0px;"
												},
												{
													xtype : "container",
													layout : "form",
													width : 100,
													items : [
															{
																id : "ResumePhotoPanel",
																height : 120,
																width : 88,
																xtype : "panel",
																border : true,
																html : '<img src="' + __ctxPath + '/images/default_person.gif" width="88" height="120"/>'
															},
															{
																xtype : "button",
																style : "padding-top:3px;",
																iconCls : "btn-upload",
																text : "上传照片",
																handler : function() {
																	var c = Ext
																			.getCmp("ResumeForm.photo");
																	var d = App
																			.createUploadDialog( {
																				file_cat : "hrm/Resume",
																				callback : b,
																				permitted_extensions : [
																						"jpg",
																						"png" ]
																			});
																	if (c
																			.getValue() != ""
																			&& c
																					.getValue() != null
																			&& c
																					.getValue() != "undefined") {
																		var e = "再次上传需要先删除原有图片,";
																		Ext.Msg
																				.confirm(
																						"信息确认",
																						e
																								+ "是否删除？",
																						function(
																								g) {
																							if (g == "yes") {
																								var f = Ext
																										.getCmp(
																												"ResumeForm.resumeId")
																										.getValue();
																								Ext.Ajax
																										.request( {
																											url : __ctxPath
																													+ "/hrm/delphotoResume.do",
																											method : "post",
																											params : {
																												resumeId : f
																											},
																											success : function() {
																												var h = c.value;
																												Ext
																														.getCmp(
																																"ResumeForm.photo")
																														.setValue(
																																"");
																												Ext
																														.getCmp("ResumePhotoPanel").body
																														.update('<img src="' + __ctxPath + '/images/default_person.gif" width="88" height="120"/>');
																												Ext.Ajax
																														.request( {
																															url : __ctxPath
																																	+ "/system/deleteFileAttach.do",
																															method : "post",
																															params : {
																																filePath : h
																															},
																															success : function() {
																																d
																																		.show("queryBtn");
																															}
																														});
																											}
																										});
																							}
																						});
																	} else {
																		d
																				.show("queryBtn");
																	}
																}
															} ]
												} ]
									} ]
						},
						{
							xtype : "fieldset",
							title : "联系方式",
							defaultType : "textfield",
							layout : "form",
							items : [ {
								fieldLabel : "地址",
								name : "resume.address",
								id : "ResumeForm.address",
								anchor : "100%"
							}, {
								layout : "column",
								defaults : {
									anchor : "100%,100%"
								},
								style : "padding-left:0px;padding-right:0px;",
								xtype : "container",
								items : [ {
									layout : "form",
									xtype : "container",
									style : "padding-left:0px;",
									columnWidth : 0.5,
									defaults : {
										anchor : "100%,100%"
									},
									defaultType : "textfield",
									items : [ {
										fieldLabel : "邮编",
										name : "resume.zip",
										id : "ResumeForm.zip"
									}, {
										fieldLabel : "电子邮箱",
										name : "resume.email",
										id : "ResumeForm.email",
										vtype : "email"
									} ]
								}, {
									xtype : "container",
									columnWidth : 0.5,
									style : "padding-right:0px;",
									defaultType : "textfield",
									layout : "form",
									items : [ {
										fieldLabel : "电话号码",
										name : "resume.phone",
										id : "ResumeForm.phone",
										anchor : "100%"
									}, {
										fieldLabel : "手机号码",
										name : "resume.mobile",
										id : "ResumeForm.mobile",
										anchor : "100%"
									} ]
								} ]
							} ]
						},
						{
							xtype : "fieldset",
							title : "教育背景",
							defaultType : "textfield",
							layout : "form",
							items : [
									{
										fieldLabel : "毕业院校",
										name : "resume.eduCollege",
										id : "ResumeForm.eduCollege",
										anchor : "70%"
									},
									{
										fieldLabel : "学历",
										name : "resume.eduDegree",
										id : "ResumeForm.eduDegree",
										anchor : "50%",
										maxHeight : 200,
										xtype : "combo",
										mode : "local",
										editable : true,
										triggerAction : "all",
										store : [],
										listeners : {
											focus : function(d) {
												var c = Ext.getCmp(
														"ResumeForm.eduDegree")
														.getStore();
												if (c.getCount() <= 0) {
													Ext.Ajax
															.request( {
																url : __ctxPath
																		+ "/system/loadDictionary.do",
																method : "post",
																params : {
																	itemName : "学历"
																},
																success : function(
																		f) {
																	var e = Ext.util.JSON
																			.decode(f.responseText);
																	c
																			.loadData(e);
																}
															});
												}
											}
										}
									},
									{
										fieldLabel : "专业",
										name : "resume.eduMajor",
										id : "ResumeForm.eduMajor",
										anchor : "50%",
										maxHeight : 200,
										xtype : "combo",
										mode : "local",
										editable : true,
										triggerAction : "all",
										store : [],
										listeners : {
											focus : function(d) {
												var c = Ext.getCmp(
														"ResumeForm.eduMajor")
														.getStore();
												if (c.getCount() <= 0) {
													Ext.Ajax
															.request( {
																url : __ctxPath
																		+ "/system/loadDictionary.do",
																method : "post",
																params : {
																	itemName : "专业"
																},
																success : function(
																		f) {
																	var e = Ext.util.JSON
																			.decode(f.responseText);
																	c
																			.loadData(e);
																}
															});
												}
											}
										}
									} ]
						},
						{
							xtype : "fieldset",
							title : "爱好",
							layout : "anchor",
							items : [ {
								name : "resume.hobby",
								id : "ResumeForm.hobby",
								xtype : "textarea",
								anchor : "100%"
							} ]
						},
						{
							xtype : "fieldset",
							title : "工作经历",
							layout : "anchor",
							items : [ {
								name : "resume.workCase",
								id : "ResumeForm.workCase",
								xtype : "textarea",
								anchor : "100%"
							} ]
						},
						{
							xtype : "fieldset",
							title : "培训经历",
							layout : "anchor",
							items : [ {
								name : "resume.trainCase",
								id : "ResumeForm.trainCase",
								xtype : "textarea",
								anchor : "100%"
							} ]
						},
						{
							xtype : "fieldset",
							title : "项目经验",
							layout : "anchor",
							items : [ {
								name : "resume.projectCase",
								id : "ResumeForm.projectCase",
								xtype : "textarea",
								anchor : "100%"
							} ]
						},
						{
							xtype : "fieldset",
							title : "备注",
							layout : "anchor",
							items : [ {
								name : "resume.memo",
								id : "ResumeForm.memo",
								xtype : "textarea",
								anchor : "100%"
							} ]
						},
						{
							xtype : "fieldset",
							title : "附件",
							layout : "column",
							items : [
									{
										columnWidth : 0.8,
										layout : "form",
										items : [ {
											xtype : "panel",
											id : "resumeFilePanel",
											height : 50,
											border : false,
											autoScroll : true,
											html : ""
										} ]
									},
									{
										columnWidth : 0.2,
										border : false,
										items : [
												{
													xtype : "button",
													text : "添加附件",
													iconCls : "menu-attachment",
													handler : function() {
														var c = App
																.createUploadDialog( {
																	file_cat : "hrm",
																	callback : function(
																			g) {
																		var d = Ext
																				.getCmp("resumefileIds");
																		var f = Ext
																				.getCmp("resumeFilePanel");
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
																									+ '/images/system/delete.gif" onclick="removeResumeFile(this,'
																									+ g[e].fileId
																									+ ')"/>&nbsp;|&nbsp;</span>');
																		}
																	}
																});
														c.show(this);
													}
												},
												{
													xtype : "button",
													text : "清除附件",
													iconCls : "reset",
													handler : function() {
														var d = Ext
																.getCmp("resumefileIds");
														var c = Ext
																.getCmp("resumeFilePanel");
														c.body.update("");
														d.setValue("");
													}
												}, {
													xtype : "hidden",
													id : "resumefileIds",
													name : "fileIds"
												} ]
									} ]
						} ]
			});
	if (this.resumeId != null && this.resumeId != "undefined") {
		a
				.getForm()
				.load(
						{
							url : __ctxPath + "/hrm/getResume.do?resumeId="
									+ this.resumeId,
							waitMsg : "正在载入数据...",
							success : function(d, f) {
								var h = Ext.util.JSON
										.decode(f.response.responseText).data[0];
								var c = h.photo;
								var k = h.resumeFiles;
								var e = h.startWorkDate;
								var m = h.birthday;
								if (e != null) {
									Ext
											.getCmp("ResumeForm.startWorkDate")
											.setValue(
													new Date(
															getDateFromFormat(
																	e,
																	"yyyy-MM-dd HH:mm:ss")));
								}
								if (m != null) {
									Ext.getCmp("ResumeForm.birthday").setValue(
											new Date(getDateFromFormat(m,
													"yyyy-MM-dd HH:mm:ss")));
								}
								var j = Ext.getCmp("resumeFilePanel");
								var l = Ext.getCmp("resumefileIds");
								for ( var g = 0; g < k.length; g++) {
									if (l.getValue() != "") {
										l.setValue(l.getValue() + ",");
									}
									l.setValue(l.getValue() + k[g].fileId);
									Ext.DomHelper
											.append(
													j.body,
													'<span><a href="#" onclick="FileAttachDetail.show('
															+ k[g].fileId
															+ ')">'
															+ k[g].fileName
															+ '</a><img class="img-delete" src="'
															+ __ctxPath
															+ '/images/system/delete.gif" onclick="removeResumeFile(this,'
															+ k[g].fileId
															+ ')"/>&nbsp;|&nbsp;</span>');
								}
								if (c != null && c != "") {
									Ext.getCmp("ResumePhotoPanel").body
											.update('<img src="'
													+ __ctxPath
													+ "/attachFiles/"
													+ c
													+ '" width="88" height="120"/>');
								}
							},
							failure : function(c, d) {
							}
						});
	}
	function b(d) {
		var c = Ext.getCmp("ResumeForm.photo");
		var e = Ext.getCmp("ResumePhotoPanel");
		c.setValue(d[0].filepath);
		e.body.update('<img src="' + __ctxPath + "/attachFiles/"
				+ d[0].filepath + '"  width="88" height="120"/>');
	}
	return a;
};
function removeResumeFile(e, a) {
	var b = Ext.getCmp("resumefileIds");
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