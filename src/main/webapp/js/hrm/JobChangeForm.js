JobChangeForm = Ext
		.extend(
				Ext.Panel,
				{
					formPanel : null,
					constructor : function(a) {
						if (a == null) {
							a = {};
						}
						Ext.apply(this, a);
						this.initComponents();
						JobChangeForm.superclass.constructor.call(this, {
							id : "JobChangeForm",
							layout : "fit",
							iconCls : "menu-job-reg",
							items : this.formPanel,
							modal : true,
							height : 200,
							width : 400,
							tbar : this.toolbar,
							maximizable : true,
							title : "登记职位调动信息",
							buttonAlign : "center"
						});
					},
					initComponents : function() {
						var a = __ctxPath
								+ "/system/listDepartment.do?opt=appUser";
						var b = new TreeSelector("jobChange.newDepName", a,
								"新部门名称", "JobChangeForm.newDepId", false);
						Ext.getCmp("jobChange.newDepNameTree").on(
								"click",
								function(c) {
									Ext.getCmp("JobChangeForm.newJobName")
											.getStore().removeAll();
									Ext.getCmp("JobChangeForm.newJobName")
											.reset();
								});
						this.formPanel = new Ext.FormPanel(
								{
									layout : "form",
									bodyStyle : "padding:10px 20px 10px 10px",
									border : false,
									url : __ctxPath + "/hrm/saveJobChange.do",
									id : "JobChangeFormPanel",
									autoScroll : true,
									defaults : {
										anchor : "98%,98%"
									},
									reader : new Ext.data.JsonReader( {
										root : "data"
									}, [ {
										name : "JobChangeForm.changeId",
										mapping : "changeId"
									}, {
										name : "JobChangeForm.profileId",
										mapping : "profileId"
									}, {
										name : "JobChangeForm.profileNo",
										mapping : "profileNo"
									}, {
										name : "JobChangeForm.userName",
										mapping : "userName"
									}, {
										name : "JobChangeForm.orgJobId",
										mapping : "orgJobId"
									}, {
										name : "JobChangeForm.orgJobName",
										mapping : "orgJobName"
									}, {
										name : "JobChangeForm.newJobId",
										mapping : "newJobId"
									}, {
										name : "JobChangeForm.newJobName",
										mapping : "newJobName"
									}, {
										name : "JobChangeForm.orgStandardNo",
										mapping : "orgStandardNo"
									}, {
										name : "JobChangeForm.orgStandardName",
										mapping : "orgStandardName"
									}, {
										name : "JobChangeForm.orgDepId",
										mapping : "orgDepId"
									}, {
										name : "JobChangeForm.orgDepName",
										mapping : "orgDepName"
									}, {
										name : "JobChangeForm.orgTotalMoney",
										mapping : "orgTotalMoney"
									}, {
										name : "JobChangeForm.newStandardNo",
										mapping : "newStandardNo"
									}, {
										name : "JobChangeForm.newStandardName",
										mapping : "newStandardName"
									}, {
										name : "JobChangeForm.newDepId",
										mapping : "newDepId"
									}, {
										name : "JobChangeForm.newDepName",
										mapping : "newDepName"
									}, {
										name : "JobChangeForm.newTotalMoney",
										mapping : "newTotalMoney"
									}, {
										name : "JobChangeForm.changeReason",
										mapping : "changeReason"
									}, {
										name : "JobChangeForm.regName",
										mapping : "regName"
									}, {
										name : "JobChangeForm.regTime",
										mapping : "regTime"
									}, {
										name : "JobChangeForm.checkName",
										mapping : "checkName"
									}, {
										name : "JobChangeForm.checkTime",
										mapping : "checkTime"
									}, {
										name : "JobChangeForm.checkOpinion",
										mapping : "checkOpinion"
									}, {
										name : "JobChangeForm.status",
										mapping : "status"
									}, {
										name : "JobChangeForm.memo",
										mapping : "memo"
									} ]),
									defaultType : "textfield",
									items : [
											{
												name : "jobChange.changeId",
												id : "JobChangeForm.changeId",
												xtype : "hidden",
												value : this.changeId == null ? ""
														: this.changeId
											},
											{
												xtype : "hidden",
												name : "jobChange.profileId",
												id : "JobChangeForm.profileId"
											},
											{
												xtype : "hidden",
												name : "jobChange.orgJobId",
												id : "JobChangeForm.orgJobId"
											},
											{
												xtype : "hidden",
												name : "jobChange.newJobId",
												id : "JobChangeForm.newJobId"
											},
											{
												xtype : "hidden",
												name : "jobChange.orgDepId",
												id : "JobChangeForm.orgDepId"
											},
											{
												xtype : "hidden",
												name : "jobChange.newDepId",
												id : "JobChangeForm.newDepId"
											},
											{
												xtype : "hidden",
												name : "jobChange.orgStandardId",
												id : "JobChangeForm.orgStandardId"
											},
											{
												xtype : "hidden",
												name : "jobChange.newStandardId",
												id : "JobChangeForm.newStandardId"
											},
											{
												xtype : "hidden",
												name : "jobChange.regName",
												id : "JobChangeForm.regName"
											},
											{
												xtype : "hidden",
												name : "jobChange.regTime",
												id : "JobChangeForm.regTime"
											},
											{
												xtype : "hidden",
												name : "jobChange.checkName",
												id : "JobChangeForm.checkName"
											},
											{
												xtype : "hidden",
												name : "jobChange.checkTime",
												id : "JobChangeForm.checkTime"
											},
											{
												xtype : "hidden",
												name : "jobChange.checkOpinion",
												id : "JobChangeForm.checkOpinion"
											},
											{
												xtype : "hidden",
												name : "jobChange.status",
												id : "JobChangeForm.status"
											},
											{
												xtype : "container",
												layout : "column",
												anchor : "100%",
												items : [
														{
															xtype : "label",
															style : "padding:3px 5px 0px 17px;",
															text : "档案编号:"
														},
														{
															name : "jobChange.profileNo",
															width : "50%",
															xtype : "textfield",
															allowBlank : false,
															readOnly : true,
															id : "JobChangeForm.profileNo"
														},
														{
															xtype : "button",
															autoWidth : true,
															text : "选择档案",
															iconCls : "menu-profile",
															handler : function() {
																EmpProfileSelector
																		.getView(
																				function(
																						c) {
																					Ext
																							.getCmp(
																									"JobChangeForm.profileId")
																							.setValue(
																									c[0]);
																					Ext
																							.getCmp(
																									"JobChangeForm.profileNo")
																							.setValue(
																									c[1]);
																					Ext
																							.getCmp(
																									"JobChangeForm.userName")
																							.setValue(
																									c[2]);
																					Ext
																							.getCmp(
																									"JobChangeForm.orgJobId")
																							.setValue(
																									c[3]);
																					Ext
																							.getCmp(
																									"JobChangeForm.orgJobName")
																							.setValue(
																									c[4]);
																					Ext
																							.getCmp(
																									"JobChangeForm.orgDepId")
																							.setValue(
																									c[5]);
																					Ext
																							.getCmp(
																									"JobChangeForm.orgDepName")
																							.setValue(
																									c[6]);
																					Ext
																							.getCmp(
																									"JobChangeForm.orgStandardNo")
																							.setValue(
																									c[7]);
																					Ext
																							.getCmp(
																									"JobChangeForm.orgStandardName")
																							.setValue(
																									c[8]);
																					Ext
																							.getCmp(
																									"JobChangeForm.orgTotalMoney")
																							.setValue(
																									c[9]);
																					Ext
																							.getCmp(
																									"JobChangeForm.orgStandardId")
																							.setValue(
																									c[10]);
																				})
																		.show();
															}
														} ]
											},
											{
												xtype : "fieldset",
												title : "员工基本信息",
												layout : "column",
												items : [
														{
															xtype : "container",
															columnWidth : 0.5,
															layout : "form",
															defaults : {
																anchor : "98%,98%"
															},
															defaultType : "textfield",
															items : [
																	{
																		fieldLabel : "姓名",
																		readOnly : true,
																		allowBlank : false,
																		name : "jobChange.userName",
																		id : "JobChangeForm.userName"
																	},
																	{
																		fieldLabel : "原部门名称",
																		readOnly : true,
																		name : "jobChange.orgDepName",
																		id : "JobChangeForm.orgDepName"
																	},
																	{
																		fieldLabel : "原职位名称",
																		readOnly : true,
																		name : "jobChange.orgJobName",
																		id : "JobChangeForm.orgJobName"
																	} ]
														},
														{
															xtype : "container",
															columnWidth : 0.5,
															defaults : {
																anchor : "98%,98%"
															},
															layout : "form",
															defaultType : "textfield",
															items : [
																	{
																		fieldLabel : "原薪酬标准",
																		readOnly : true,
																		name : "jobChange.orgStandardName",
																		id : "JobChangeForm.orgStandardName"
																	},
																	{
																		fieldLabel : "原薪酬编号",
																		readOnly : true,
																		name : "jobChange.orgStandardNo",
																		id : "JobChangeForm.orgStandardNo"
																	},
																	{
																		fieldLabel : "原工资总额",
																		readOnly : true,
																		name : "jobChange.orgTotalMoney",
																		id : "JobChangeForm.orgTotalMoney"
																	} ]
														} ]
											},
											{
												xtype : "fieldset",
												title : "员工调动信息",
												layout : "column",
												items : [
														{
															xtype : "container",
															layout : "form",
															columnWidth : 0.5,
															defaults : {
																anchor : "98%,98%"
															},
															defaultType : "textfield",
															items : [
																	b,
																	{
																		fieldLabel : "新薪酬标准",
																		name : "jobChange.newStandardName",
																		id : "JobChangeForm.newStandardName",
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
																								"JobChangeForm.newStandardName")
																						.getStore()
																						.reload();
																			},
																			select : function(
																					e,
																					c,
																					d) {
																				Ext
																						.getCmp(
																								"JobChangeForm.newStandardId")
																						.setValue(
																								c.data.standardId);
																				Ext
																						.getCmp(
																								"JobChangeForm.newStandardNo")
																						.setValue(
																								c.data.standardNo);
																				Ext
																						.getCmp(
																								"JobChangeForm.newTotalMoney")
																						.setValue(
																								c.data.totalMoney);
																			}
																		}
																	},
																	{
																		fieldLabel : "新薪酬编号",
																		allowBlank : false,
																		readOnly : true,
																		name : "jobChange.newStandardNo",
																		id : "JobChangeForm.newStandardNo"
																	} ]
														},
														{
															xtype : "container",
															layout : "form",
															defaults : {
																anchor : "98%,98%"
															},
															columnWidth : 0.5,
															defaultType : "textfield",
															items : [
																	{
																		fieldLabel : "新职位名称",
																		allowBlank : false,
																		name : "jobChange.newJobName",
																		id : "JobChangeForm.newJobName",
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
																								"JobChangeForm.newDepId")
																						.getValue();
																				if (c != null
																						&& c != ""
																						&& c != "undefined") {
																					Ext
																							.getCmp(
																									"JobChangeForm.newJobName")
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
																								"JobChangeForm.newJobId")
																						.setValue(
																								c.data.jobId);
																			}
																		}
																	},
																	{
																		fieldLabel : "新工资总额",
																		allowBlank : false,
																		readOnly : true,
																		name : "jobChange.newTotalMoney",
																		id : "JobChangeForm.newTotalMoney"
																	} ]
														} ]
											},
											{
												xtype : "fieldset",
												title : "调动情况说明",
												layout : "form",
												defaultType : "textfield",
												items : [
														{
															fieldLabel : "更改原由",
															name : "jobChange.changeReason",
															id : "JobChangeForm.changeReason",
															xtype : "textarea",
															anchor : "100%"
														},
														{
															fieldLabel : "备注",
															name : "jobChange.memo",
															id : "JobChangeForm.memo",
															xtype : "textarea",
															anchor : "100%"
														} ]
											} ]
								});
						this.toolbar = new Ext.Toolbar( {
							id : "JobChangeFormToolbar",
							items : [
									{
										text : "保存为草稿",
										xtype : "button",
										iconCls : "btn-save",
										handler : this.save
												.createCallback(this.formPanel)
									},
									{
										text : "提交审核",
										xtype : "button",
										iconCls : "btn-ok",
										handler : this.submit
												.createCallback(this.formPanel)
									},
									{
										text : "重置",
										xtype : "button",
										iconCls : "btn-reset",
										handler : this.reset
												.createCallback(this.formPanel)
									},
									{
										text : "取消",
										xtype : "button",
										iconCls : "btn-cancel",
										handler : this.cancel
												.createCallback(this)
									} ]
						});
						if (this.changeId != null
								&& this.changeId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/hrm/getJobChange.do?changeId="
														+ this.changeId,
												waitMsg : "正在载入数据...",
												success : function(c, d) {
													var e = Ext.util.JSON
															.decode(d.response.responseText).data[0];
													Ext
															.getCmp(
																	"JobChangeForm.newDepId")
															.setValue(
																	e.newDepId);
													Ext
															.getCmp(
																	"jobChange.newDepName")
															.setValue(
																	e.newDepName);
												},
												failure : function(c, d) {
												}
											});
						}
					},
					reset : function(a) {
						a.getForm().reset();
						Ext.getCmp("JobChangeForm.newJobName").getStore()
								.removeAll();
					},
					cancel : function(b) {
						var a = Ext.getCmp("centerTabPanel");
						if (b != null) {
							a.remove("JobChangeForm");
						}
					},
					save : function(a) {
						Ext.getCmp("JobChangeForm.status").setValue("-1");
						if (a.getForm().isValid()) {
							a
									.getForm()
									.submit(
											{
												method : "POST",
												waitMsg : "正在提交数据...",
												success : function(b, d) {
													Ext.ux.Toast.msg("操作信息",
															"成功保存信息！");
													var c = Ext
															.getCmp("JobChangeGrid");
													if (c != null) {
														c.getStore().reload();
													}
													a.getForm().reset();
													Ext
															.getCmp(
																	"JobChangeForm.newJobName")
															.getStore()
															.removeAll();
												},
												failure : function(b, c) {
													Ext.MessageBox
															.show( {
																title : "操作信息",
																msg : "信息保存出错，请联系管理员！",
																buttons : Ext.MessageBox.OK,
																icon : Ext.MessageBox.ERROR
															});
												}
											});
						}
					},
					submit : function(a) {
						Ext.getCmp("JobChangeForm.status").setValue("0");
						if (a.getForm().isValid()) {
							a
									.getForm()
									.submit(
											{
												method : "POST",
												waitMsg : "正在提交数据...",
												success : function(b, d) {
													Ext.ux.Toast.msg("操作信息",
															"成功保存信息！");
													var c = Ext
															.getCmp("JobChangeGrid");
													if (c != null) {
														c.getStore().reload();
													}
													a.getForm().reset();
													Ext
															.getCmp(
																	"JobChangeForm.newJobName")
															.getStore()
															.removeAll();
												},
												failure : function(b, c) {
													Ext.MessageBox
															.show( {
																title : "操作信息",
																msg : "信息保存出错，请联系管理员！",
																buttons : Ext.MessageBox.OK,
																icon : Ext.MessageBox.ERROR
															});
												}
											});
						}
					}
				});