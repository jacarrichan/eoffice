EmpProfileForm = Ext
		.extend(
				Ext.Panel,
				{
					formPanel : null,
					constructor : function(a) {
						if (a == null) {
							a = {};
						}
						Ext.applyIf(this, a);
						this.initComponents();
						EmpProfileForm.superclass.constructor.call(this, {
							id : "EmpProfileForm",
							iconCls : "menu-profile-create",
							layout : "fit",
							items : this.formPanel,
							modal : true,
							tbar : this.topbar,
							maximizable : true,
							title : "档案详细信息",
							buttonAlign : "center",
							buttons : this.buttons
						});
					},
					topbar : null,
					initComponents : function() {
						var a = __ctxPath
								+ "/system/listDepartment.do?opt=appUser";
						var b = new TreeSelector("empProfile.depName", a,
								"所属部门或公司", "empProfileForm.depId", false);
						Ext.getCmp("empProfile.depNameTree").on(
								"click",
								function(c) {
									var d = Ext
											.getCmp("empProfileForm.position");
									d.getStore().removeAll();
									d.reset();
								});
						this.formPanel = new Ext.FormPanel(
								{
									layout : "form",
									autoScroll : true,
									tbar : this.topbar,
									bodyStyle : "padding:10px 20px 10px 10px",
									border : false,
									url : __ctxPath + "/hrm/saveEmpProfile.do",
									id : "EmpProfileFormPanel",
									defaults : {
										anchor : "98%,98%"
									},
									reader : new Ext.data.JsonReader(
											{
												root : "data"
											},
											[
													{
														name : "empProfileForm.profileId",
														mapping : "profileId"
													},
													{
														name : "empProfileForm.creator",
														mapping : "creator"
													},
													{
														name : "empProfileForm.createtime",
														mapping : "createtime"
													},
													{
														name : "empProfileForm.checkName",
														mapping : "checkName"
													},
													{
														name : "empProfileForm.checktime",
														mapping : "checktime"
													},
													{
														name : "empProfileForm.approvalStatus",
														mapping : "approvalStatus"
													},
													{
														name : "empProfileForm.depId",
														mapping : "depId"
													},
													{
														name : "empProfileForm.delFlag",
														mapping : "delFlag"
													},
													{
														name : "empProfileForm.jobId",
														mapping : "jobId"
													},
													{
														name : "empProfileForm.photo",
														mapping : "photo"
													},
													{
														name : "empProfileForm.profileNo",
														mapping : "profileNo"
													},
													{
														name : "empProfileForm.fullname",
														mapping : "fullname"
													},
													{
														name : "empProfileForm.idCard",
														mapping : "idCard"
													},
													{
														name : "empProfileForm.birthday",
														mapping : "birthday"
													},
													{
														name : "empProfileForm.sex",
														mapping : "sex"
													},
													{
														name : "empProfileForm.marriage",
														mapping : "marriage"
													},
													{
														name : "empProfileForm.religion",
														mapping : "religion"
													},
													{
														name : "empProfileForm.party",
														mapping : "party"
													},
													{
														name : "empProfileForm.nationality",
														mapping : "nationality"
													},
													{
														name : "empProfileForm.race",
														mapping : "race"
													},
													{
														name : "empProfileForm.birthPlace",
														mapping : "birthPlace"
													},
													{
														name : "empProfileForm.address",
														mapping : "address"
													},
													{
														name : "empProfileForm.homeZip",
														mapping : "homeZip"
													},
													{
														name : "empProfileForm.mobile",
														mapping : "mobile"
													},
													{
														name : "empProfileForm.phone",
														mapping : "phone"
													},
													{
														name : "empProfileForm.qq",
														mapping : "qq"
													},
													{
														name : "empProfileForm.email",
														mapping : "email"
													},
													{
														name : "empProfileForm.eduDegree",
														mapping : "eduDegree"
													},
													{
														name : "empProfileForm.eduMajor",
														mapping : "eduMajor"
													},
													{
														name : "empProfileForm.eduCollege",
														mapping : "eduCollege"
													},
													{
														name : "empProfileForm.startWorkDate",
														mapping : "startWorkDate"
													},
													{
														name : "empProfileForm.eduCase",
														mapping : "eduCase"
													},
													{
														name : "empProfileForm.designation",
														mapping : "designation"
													},
													{
														name : "empProfileForm.position",
														mapping : "position"
													},
													{
														name : "empProfileForm.openBank",
														mapping : "openBank"
													},
													{
														name : "empProfileForm.bankNo",
														mapping : "bankNo"
													},
													{
														name : "empProfileForm.depName",
														mapping : "depName"
													},
													{
														name : "empProfileForm.standardMiNo",
														mapping : "standardMiNo"
													},
													{
														name : "empProfileForm.standardMoney",
														mapping : "standardMoney"
													},
													{
														name : "empProfileForm.standardName",
														mapping : "standardName"
													},
													{
														name : "empProfileForm.standardId",
														mapping : "standardId"
													},
													{
														name : "empProfileForm.trainingCase",
														mapping : "trainingCase"
													},
													{
														name : "empProfileForm.awardPunishCase",
														mapping : "awardPunishCase"
													},
													{
														name : "empProfileForm.workCase",
														mapping : "workCase"
													},
													{
														name : "empProfileForm.hobby",
														mapping : "hobby"
													},
													{
														name : "empProfileForm.memo",
														mapping : "memo"
													},
													{
														name : "empProfileForm.userId",
														mapping : "userId"
													} ]),
									defaultType : "textfield",
									items : [
											{
												name : "empProfile.profileId",
												id : "empProfileForm.profileId",
												xtype : "hidden",
												value : this.profileId == null ? ""
														: this.profileId
											},
											{
												fieldLabel : "建档人",
												name : "empProfile.creator",
												xtype : "hidden",
												id : "empProfileForm.creator"
											},
											{
												fieldLabel : "建档时间",
												name : "empProfile.createtime",
												xtype : "hidden",
												id : "empProfileForm.createtime"
											},
											{
												fieldLabel : "审核人",
												name : "empProfile.checkName",
												xtype : "hidden",
												id : "empProfileForm.checkName"
											},
											{
												fieldLabel : "审核时间",
												name : "empProfile.checktime",
												xtype : "hidden",
												id : "empProfileForm.checktime"
											},
											{
												fieldLabel : "核审状态",
												name : "empProfile.approvalStatus",
												xtype : "hidden",
												id : "empProfileForm.approvalStatus"
											},
											{
												fieldLabel : "所属部门Id",
												name : "empProfile.depId",
												xtype : "hidden",
												id : "empProfileForm.depId"
											},
											{
												fieldLabel : "删除状态",
												name : "empProfile.delFlag",
												xtype : "hidden",
												id : "empProfileForm.delFlag"
											},
											{
												fieldLabel : "所属职位",
												name : "empProfile.jobId",
												xtype : "hidden",
												id : "empProfileForm.jobId"
											},
											{
												fieldLabel : "照片",
												name : "empProfile.photo",
												xtype : "hidden",
												id : "empProfileForm.photo"
											},
											{
												fieldLabel : "薪酬标准单编号",
												name : "empProfile.standardId",
												xtype : "hidden",
												id : "empProfileForm.standardId"
											},
											{
												fieldLabel : "所属员工ID",
												name : "empProfile.userId",
												xtype : "hidden",
												id : "empProfileForm.userId"
											},
											{
												xtype : "container",
												layout : "column",
												height : 26,
												anchor : "100%",
												items : [
														{
															xtype : "label",
															style : "padding:3px 5px 0px 17px;",
															text : "档案编号:"
														},
														{
															name : "empProfile.profileNo",
															width : 200,
															xtype : "textfield",
															id : "empProfileForm.profileNo",
															readOnly : true,
															allowBlank : false,
															blankText : "档案编号不能为空!"
														},
														{
															xtype : "button",
															autoWidth : true,
															id : "EmpProfileSystemSetting",
															text : "系统生成",
															iconCls : "btn-system-setting",
															handler : function() {
																Ext.Ajax
																		.request( {
																			url : __ctxPath
																					+ "/hrm/numberEmpProfile.do",
																			success : function(
																					d) {
																				var c = Ext.util.JSON
																						.decode(d.responseText);
																				Ext
																						.getCmp(
																								"empProfileForm.profileNo")
																						.setValue(
																								c.profileNo);
																			}
																		});
															}
														} ]
											},
											{
												xtype : "container",
												height : 26,
												layout : "column",
												anchor : "100%",
												items : [
														{
															xtype : "label",
															style : "padding:3px 5px 0px 17px;",
															text : "员工姓名:"
														},
														{
															width : 200,
															xtype : "textfield",
															name : "empProfile.fullname",
															id : "empProfileForm.fullname",
															allowBlank : false,
															blankText : "姓名不能为空！",
															readOnly : true
														},
														{
															xtype : "button",
															id : "EmpProfileSelectEmp",
															text : "选择员工",
															iconCls : "btn-mail_recipient",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						d,
																						c) {
																					Ext
																							.getCmp(
																									"empProfileForm.fullname")
																							.setValue(
																									c);
																					Ext
																							.getCmp(
																									"empProfileForm.userId")
																							.setValue(
																									d);
																					Ext.Ajax
																							.request( {
																								url : __ctxPath
																										+ "/system/getAppUser.do",
																								params : {
																									userId : d
																								},
																								method : "post",
																								success : function(
																										f) {
																									var e = Ext.util.JSON
																											.decode(f.responseText).data[0];
																									Ext
																											.getCmp(
																													"empProfileForm.depId")
																											.setValue(
																													e.department.depId);
																									Ext
																											.getCmp(
																													"empProfile.depName")
																											.setValue(
																													e.department.depName);
																								}
																							});
																				},
																				true)
																		.show();
															}
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
															items : [
																	{
																		fieldLabel : "身份证号",
																		name : "empProfile.idCard",
																		id : "empProfileForm.idCard"
																	},
																	{
																		fieldLabel : "出生日期",
																		name : "empProfile.birthday",
																		id : "empProfileForm.birthday",
																		xtype : "datefield",
																		format : "Y-m-d"
																	},
																	{
																		fieldLabel : "性别",
																		name : "empProfile.sex",
																		id : "empProfileForm.sex",
																		xtype : "combo",
																		editable : false,
																		mode : "local",
																		triggerAction : "all",
																		store : [
																				"男",
																				"女" ]
																	},
																	{
																		fieldLabel : "婚姻状况",
																		name : "empProfile.marriage",
																		id : "empProfileForm.marriage",
																		xtype : "combo",
																		editable : false,
																		mode : "local",
																		triggerAction : "all",
																		store : [
																				"已婚",
																				"未婚" ]
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
																		name : "empProfile.religion",
																		id : "empProfileForm.religion",
																		maxHeight : 200,
																		xtype : "combo",
																		mode : "local",
																		editable : true,
																		triggerAction : "all",
																		store : [],
																		listeners : {
																			focus : function(
																					d) {
																				var c = Ext
																						.getCmp(
																								"empProfileForm.religion")
																						.getStore();
																				if (c
																						.getCount() <= 0) {
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
																		name : "empProfile.party",
																		id : "empProfileForm.party",
																		maxHeight : 200,
																		xtype : "combo",
																		mode : "local",
																		editable : true,
																		triggerAction : "all",
																		store : [],
																		listeners : {
																			focus : function(
																					d) {
																				var c = Ext
																						.getCmp(
																								"empProfileForm.party")
																						.getStore();
																				if (c
																						.getCount() <= 0) {
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
																		name : "empProfile.nationality",
																		id : "empProfileForm.nationality",
																		maxHeight : 200,
																		xtype : "combo",
																		mode : "local",
																		editable : true,
																		triggerAction : "all",
																		store : [],
																		listeners : {
																			focus : function(
																					d) {
																				var c = Ext
																						.getCmp(
																								"empProfileForm.nationality")
																						.getStore();
																				if (c
																						.getCount() <= 0) {
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
																		name : "empProfile.race",
																		id : "empProfileForm.race",
																		maxHeight : 200,
																		xtype : "combo",
																		mode : "local",
																		editable : true,
																		triggerAction : "all",
																		store : [],
																		listeners : {
																			focus : function(
																					d) {
																				var c = Ext
																						.getCmp(
																								"empProfileForm.race")
																						.getStore();
																				if (c
																						.getCount() <= 0) {
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
																	},
																	{
																		fieldLabel : "出生地",
																		name : "empProfile.birthPlace",
																		id : "empProfileForm.birthPlace"
																	} ]
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
																					id : "ProfilePhotoPanel",
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
																						var d = Ext
																								.getCmp("empProfileForm.photo");
																						var e = App
																								.createUploadDialog( {
																									file_cat : "hrm/profile",
																									callback : function c(
																											h) {
																										var g = Ext
																												.getCmp("empProfileForm.photo");
																										var i = Ext
																												.getCmp("ProfilePhotoPanel");
																										g
																												.setValue(h[0].filepath);
																										i.body
																												.update('<img src="'
																														+ __ctxPath
																														+ "/attachFiles/"
																														+ h[0].filepath
																														+ '"  width="88" height="120"/>');
																									},
																									permitted_extensions : [
																											"jpg",
																											"png" ]
																								});
																						if (d
																								.getValue() != ""
																								&& d
																										.getValue() != null
																								&& d
																										.getValue() != "undefined") {
																							var f = "再次上传需要先删除原有图片,";
																							Ext.Msg
																									.confirm(
																											"信息确认",
																											f
																													+ "是否删除？",
																											function(
																													g) {
																												if (g == "yes") {
																													var h = Ext
																															.getCmp(
																																	"empProfileForm.profileId")
																															.getValue();
																													Ext.Ajax
																															.request( {
																																url : __ctxPath
																																		+ "/hrm/delphotoEmpProfile.do",
																																method : "post",
																																params : {
																																	profileId : h
																																},
																																success : function() {
																																	var i = d.value;
																																	Ext
																																			.getCmp(
																																					"empProfileForm.photo")
																																			.setValue(
																																					"");
																																	Ext
																																			.getCmp("ProfilePhotoPanel").body
																																			.update('<img src="' + __ctxPath + '/images/default_person.gif" width="88" height="120"/>');
																																	Ext.Ajax
																																			.request( {
																																				url : __ctxPath
																																						+ "/system/deleteFileAttach.do",
																																				method : "post",
																																				params : {
																																					filePath : i
																																				},
																																				success : function() {
																																					e
																																							.show("queryBtn");
																																				}
																																			});
																																}
																															});
																												}
																											});
																						} else {
																							e
																									.show("queryBtn");
																						}
																					}
																				} ]
																	} ]
														} ]
											},
											{
												xtype : "fieldset",
												title : "职务薪酬信息",
												defaultType : "textfield",
												layout : "column",
												items : [
														{
															xtype : "container",
															columnWidth : 0.5,
															defaultType : "textfield",
															layout : "form",
															defaults : {
																anchor : "100%,100%"
															},
															items : [
																	b,
																	{
																		fieldLabel : "职称",
																		name : "empProfile.designation",
																		id : "empProfileForm.designation"
																	},
																	{
																		fieldLabel : "开户银行",
																		name : "empProfile.openBank",
																		id : "empProfileForm.openBank"
																	},
																	{
																		fieldLabel : "银行账号",
																		name : "empProfile.bankNo",
																		id : "empProfileForm.bankNo"
																	} ]
														},
														{
															xtype : "container",
															columnWidth : 0.5,
															defaultType : "textfield",
															layout : "form",
															defaults : {
																anchor : "100%,100%"
															},
															items : [
																	{
																		fieldLabel : "职位",
																		name : "empProfile.position",
																		id : "empProfileForm.position",
																		xtype : "combo",
																		mode : "local",
																		allowBlank : false,
																		editable : false,
																		valueField : "jobName",
																		displayField : "jobName",
																		triggerAction : "all",
																		store : new Ext.data.SimpleStore(
																				{
																					url : __ctxPath
																							+ "/hrm/comboJob.do",
																					fields : [
																							"jobId",
																							"jobName" ]
																				}),
																		listeners : {
																			focus : function(
																					d) {
																				var c = Ext
																						.getCmp(
																								"empProfileForm.depId")
																						.getValue();
																				if (c != null
																						&& c != ""
																						&& c != "undefined") {
																					Ext
																							.getCmp(
																									"empProfileForm.position")
																							.getStore()
																							.reload(
																									{
																										params : {
																											depId : c
																										}
																									});
																				} else {
																					Ext.ux.Toast
																							.msg(
																									"操作信息",
																									"请先选择部门！");
																				}
																			},
																			select : function(
																					e,
																					c,
																					d) {
																				Ext
																						.getCmp(
																								"empProfileForm.jobId")
																						.setValue(
																								c.data.jobId);
																			}
																		}
																	},
																	{
																		fieldLabel : "薪酬标准单名称",
																		name : "empProfile.standardName",
																		id : "empProfileForm.standardName",
																		xtype : "combo",
																		mode : "local",
																		allowBlank : false,
																		editable : false,
																		valueField : "standardName",
																		displayField : "standardName",
																		triggerAction : "all",
																		store : new Ext.data.JsonStore(
																				{
																					url : __ctxPath
																							+ "/hrm/comboStandSalary.do",
																					fields : [
																							{
																								name : "standardId",
																								type : "int"
																							},
																							"standardNo",
																							"standardName",
																							"totalMoney",
																							"setdownTime",
																							"status" ]
																				}),
																		listeners : {
																			focus : function() {
																				Ext
																						.getCmp(
																								"empProfileForm.standardName")
																						.getStore()
																						.reload();
																			},
																			select : function(
																					e,
																					c,
																					d) {
																				Ext
																						.getCmp(
																								"empProfileForm.standardId")
																						.setValue(
																								c.data.standardId);
																				Ext
																						.getCmp(
																								"empProfileForm.standardMiNo")
																						.setValue(
																								c.data.standardNo);
																				Ext
																						.getCmp(
																								"empProfileForm.standardMoney")
																						.setValue(
																								c.data.totalMoney);
																			}
																		}
																	},
																	{
																		fieldLabel : "薪酬标准编号",
																		name : "empProfile.standardMiNo",
																		id : "empProfileForm.standardMiNo",
																		readOnly : true
																	},
																	{
																		fieldLabel : "薪酬标准金额",
																		name : "empProfile.standardMoney",
																		id : "empProfileForm.standardMoney",
																		readOnly : true
																	} ]
														},
														{
															xtype : "container",
															columnWidth : 1,
															defaultType : "textfield",
															layout : "form",
															defaults : {
																anchor : "100%,100%"
															},
															items : [ {
																fieldLabel : "培训情况",
																name : "empProfile.trainingCase",
																xtype : "textarea",
																id : "empProfileForm.trainingCase"
															} ]
														} ]
											},
											{
												xtype : "fieldset",
												title : "联系方式",
												defaultType : "textfield",
												layout : "column",
												items : [
														{
															xtype : "container",
															columnWidth : 0.5,
															defaultType : "textfield",
															layout : "form",
															defaults : {
																anchor : "100%,100%"
															},
															items : [
																	{
																		fieldLabel : "家庭地址",
																		name : "empProfile.address",
																		id : "empProfileForm.address"
																	},
																	{
																		fieldLabel : "家庭邮编",
																		name : "empProfile.homeZip",
																		id : "empProfileForm.homeZip"
																	},
																	{
																		fieldLabel : "手机号码",
																		name : "empProfile.mobile",
																		id : "empProfileForm.mobile"
																	} ]
														},
														{
															xtype : "container",
															columnWidth : 0.5,
															defaultType : "textfield",
															layout : "form",
															defaults : {
																anchor : "100%,100%"
															},
															items : [
																	{
																		fieldLabel : "电话号码",
																		name : "empProfile.phone",
																		id : "empProfileForm.phone"
																	},
																	{
																		fieldLabel : "QQ号码",
																		name : "empProfile.qq",
																		id : "empProfileForm.qq"
																	},
																	{
																		fieldLabel : "电子邮箱",
																		name : "empProfile.email",
																		id : "empProfileForm.email",
																		vtype : "email",
																		vtypeText : "邮箱格式不正确!"
																	} ]
														} ]
											},
											{
												xtype : "fieldset",
												title : "教育情况",
												defaultType : "textfield",
												layout : "column",
												items : [
														{
															xtype : "container",
															columnWidth : 0.5,
															defaultType : "textfield",
															layout : "form",
															defaults : {
																anchor : "100%,100%"
															},
															items : [
																	{
																		fieldLabel : "学历",
																		name : "empProfile.eduDegree",
																		id : "empProfileForm.eduDegree",
																		maxHeight : 200,
																		xtype : "combo",
																		mode : "local",
																		editable : true,
																		triggerAction : "all",
																		store : [],
																		listeners : {
																			focus : function(
																					d) {
																				var c = Ext
																						.getCmp(
																								"empProfileForm.eduDegree")
																						.getStore();
																				if (c
																						.getCount() <= 0) {
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
																		name : "empProfile.eduMajor",
																		id : "empProfileForm.eduMajor",
																		maxHeight : 200,
																		xtype : "combo",
																		mode : "local",
																		editable : true,
																		triggerAction : "all",
																		store : [],
																		listeners : {
																			focus : function(
																					d) {
																				var c = Ext
																						.getCmp(
																								"empProfileForm.eduMajor")
																						.getStore();
																				if (c
																						.getCount() <= 0) {
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
															xtype : "container",
															columnWidth : 0.5,
															defaultType : "textfield",
															layout : "form",
															defaults : {
																anchor : "100%,100%"
															},
															items : [
																	{
																		fieldLabel : "毕业院校",
																		name : "empProfile.eduCollege",
																		id : "empProfileForm.eduCollege"
																	},
																	{
																		fieldLabel : "参加工作时间",
																		name : "empProfile.startWorkDate",
																		id : "empProfileForm.startWorkDate",
																		xtype : "datefield",
																		format : "Y-m-d"
																	} ]
														},
														{
															xtype : "container",
															columnWidth : 1,
															defaultType : "textfield",
															layout : "form",
															defaults : {
																anchor : "100%,100%"
															},
															items : [ {
																fieldLabel : "教育背景",
																name : "empProfile.eduCase",
																xtype : "textarea",
																id : "empProfileForm.eduCase"
															} ]
														} ]
											},
											{
												xtype : "fieldset",
												title : "奖惩情况",
												layout : "anchor",
												items : [ {
													fieldLabel : "奖惩情况",
													name : "empProfile.awardPunishCase",
													xtype : "textarea",
													id : "empProfileForm.awardPunishCase",
													anchor : "100%"
												} ]
											},
											{
												xtype : "fieldset",
												title : "工作经历",
												layout : "anchor",
												items : [ {
													fieldLabel : "工作经历",
													name : "empProfile.workCase",
													xtype : "textarea",
													anchor : "100%",
													id : "empProfileForm.workCase"
												} ]
											},
											{
												xtype : "fieldset",
												title : "个人爱好",
												layout : "anchor",
												items : [ {
													fieldLabel : "爱好",
													name : "empProfile.hobby",
													id : "empProfileForm.hobby",
													anchor : "100%",
													xtype : "textarea"
												} ]
											}, {
												xtype : "fieldset",
												title : "备注",
												layout : "anchor",
												items : [ {
													fieldLabel : "备注",
													name : "empProfile.memo",
													anchor : "100%",
													id : "empProfileForm.memo",
													xtype : "textarea"
												} ]
											} ]
								});
						this.topbar = new Ext.Toolbar( {
							height : 30,
							bodyStyle : "text-align:left",
							defaultType : "button",
							items : [
									{
										text : "保存",
										iconCls : "btn-save",
										handler : this.save.createCallback(
												this.formPanel, this)
									},
									{
										text : "重置",
										iconCls : "btn-reset",
										handler : this.reset
												.createCallback(this.formPanel)
									},
									{
										text : "取消",
										iconCls : "btn-cancel",
										handler : this.cancel
												.createCallback(this)
									} ]
						});
						if (this.profileId != null
								&& this.profileId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/hrm/getEmpProfile.do?profileId="
														+ this.profileId,
												waitMsg : "正在载入数据...",
												success : function(g, h) {
													var e = Ext.util.JSON
															.decode(h.response.responseText).data[0];
													var d = e.photo;
													if (e.startWorkDate != ""
															&& e.startWorkDate != null
															&& e.startWorkDate != "undefined") {
														var c = getDateFromFormat(
																e.startWorkDate,
																"yyyy-MM-dd HH:mm:ss");
														Ext
																.getCmp(
																		"empProfileForm.startWorkDate")
																.setValue(
																		new Date(
																				c));
													}
													if (e.birthday != ""
															&& e.birthday != null
															&& e.birthday != "undefined") {
														var f = getDateFromFormat(
																e.birthday,
																"yyyy-MM-dd HH:mm:ss");
														Ext
																.getCmp(
																		"empProfileForm.birthday")
																.setValue(
																		new Date(
																				f));
													}
													if (d != null && d != "") {
														Ext
																.getCmp("ProfilePhotoPanel").body
																.update('<img src="'
																		+ __ctxPath
																		+ "/attachFiles/"
																		+ d
																		+ '" width="88" height="120"/>');
													}
													Ext
															.getCmp(
																	"empProfile.depName")
															.setValue(e.depName);
													Ext
															.getCmp(
																	"EmpProfileSystemSetting")
															.hide();
													Ext
															.getCmp(
																	"empProfileForm.profileNo")
															.getEl().dom.readOnly = true;
													Ext
															.getCmp(
																	"EmpProfileSelectEmp")
															.hide();
												},
												failure : function(c, d) {
												}
											});
						}
					},
					reset : function(a) {
						a.getForm().reset();
					},
					cancel : function(a) {
						var b = Ext.getCmp("centerTabPanel");
						if (a != null) {
							b.remove("EmpProfileForm");
						}
					},
					save : function(a, b) {
						if (a.getForm().isValid()) {
							a
									.getForm()
									.submit(
											{
												method : "POST",
												waitMsg : "正在提交数据...",
												success : function(c, e) {
													Ext.ux.Toast.msg("操作信息",
															"成功保存信息！");
													var d = Ext
															.getCmp("EmpProfileGrid");
													if (d != null) {
														d.getStore().reload();
														AppUtil
																.removeTab("EmpProfileForm");
													}
												},
												failure : function(c, d) {
													Ext.MessageBox
															.show( {
																title : "操作信息",
																msg : d.result.msg,
																buttons : Ext.MessageBox.OK,
																icon : Ext.MessageBox.ERROR
															});
													Ext
															.getCmp(
																	"empProfileForm.profileNo")
															.setValue("");
												}
											});
						}
					}
				});