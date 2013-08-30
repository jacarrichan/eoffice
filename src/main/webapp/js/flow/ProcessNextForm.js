ProcessNextForm = Ext
		.extend(
				Ext.Panel,
				{
					formPanel : null,
					formButtons : [],
					constructor : function(c) {
						Ext.applyIf(this, c);
						var a = false;
						Ext.Ajax.request({
							params : {
								taskId : this.taskId
							},
							async : false,
							scope : this,
							url : __ctxPath + "/flow/checkTask.do",
							success : function(e, f) {
								var d = Ext.util.JSON.decode(e.responseText);
								if (d.assigned != undefined) {
									if (!d.assigned) {
										Ext.ux.Toast.msg("操作信息",
												"该任务已经被其他用户锁定执行！");
										a = true;
									}
									if (d.assigned) {
										Ext.ux.Toast.msg("操作信息", "该任务已经成功锁定!");
									}
								}
							}
						});
						if (a) {
							var b = Ext.getCmp("TaskPanelView");
							if (b != null && b != undefined) {
								b.getUpdater().update(
										__ctxPath + "/flow/displayTask.do");
							}
							ProcessNextForm.superclass.constructor.call(null);
							return;
						}
						ProcessNextForm.superclass.constructor.call(this, {
							id : "ProcessForm" + this.taskId,
							iconCls : "btn-approvalTask",
							title : this.activityName,
							layout : "border",
							bodyStyle : "padding:5px",
							items : []
						});
					},
					initComponent : function() {
						var taskId = this.taskId;
						var formOuterPanel = new Ext.Panel({
							autoHeight : true,
							layout : "hbox",
							border : false,
							layoutConfig : {
								padding : "5",
								pack : "center",
								align : "middle"
							},
							items : [ new Ext.form.Label({
								text : "正在加载流程表单...",
								height : 60
							}) ]
						});
						var detailPanel = new Ext.Panel({
							title : "审批信息",
							layout : "fit",
							autoHeight : true,
							autoScroll : true,
							autoLoad : {
								url : __ctxPath
										+ "/flow/processRunDetail.do?randId="
										+ Math.random() + "&taskId="
										+ this.taskId
							}
						});
						var leftPanel = new Ext.Panel({
							border : false,
							region : "center",
							layout : "anchor",
							autoScroll : true,
							bodyStyle : "padding:5px",
							items : [ formOuterPanel, detailPanel ]
						});
						var rightPanel = new Ext.Panel({
							bodyStyle : "padding:5px",
							title : "自由跳转",
							region : "east",
							collapsible : true,
							split : true,
							width : 240,
							layout : "fit"
						});
						this.items = [ leftPanel, rightPanel ];
						this.tbar = new Ext.Toolbar({
							items : [ {
								text : "流程示意图",
								iconCls : "btn-flow-chart",
								scope : this,
								handler : function() {
									this.showFlowImage();
								}
							}, "-" ]
						});
						ProcessNextForm.superclass.initComponent.call(this);
						var activityName = this.activityName;
						Ext.Ajax
								.request({
									url : __ctxPath
											+ "/flow/getProcessActivity.do",
									scope : this,
									params : {
										activityName : activityName,
										taskId : this.taskId
									},
									success : function(response, options) {
										try {
											this.isFormPanel = true;
											var formPanel = null;
											if (response.responseText.trim()
													.indexOf("[") == 0) {
												if (activityName == ""
														|| activityName == "undefined"
														|| activityName == null) {
													activityName = "开始";
												}
												eval('formPanel = new Ext.FormPanel({title:"任务表单-'
														+ activityName
														+ '",defaults:{border:false},width:600,bodyStyle:"padding:8px 8px 8px 8px;",layout:"form",autoHeight:true,items:'
														+ response.responseText
														+ "});");
											} else {
												if (response.responseText
														.indexOf("Ext.extend(Ext.Panel") != -1) {
													this.isFormPanel = false;
													eval("formPanel= new ("
															+ response.responseText
															+ ")();");
												} else {
													eval("formPanel= new ("
															+ response.responseText
															+ ")();");
												}
											}
											formOuterPanel.removeAll(true);
											formOuterPanel.add(formPanel);
											var msgPanel = new Ext.Panel({
												border : false,
												autoHeight : true,
												layout : "column",
												bodyStyle : "padding:4px",
												items : [ {
													xtype : "checkbox",
													name : "sendMail",
													inputValue : "true",
													boxLabel : "发送邮件",
													columnWidth : 0.2,
													width : 200
												}, {
													xtype : "checkbox",
													name : "sendMsg",
													inputValue : "true",
													boxLabel : "发送短信",
													columnWidth : 0.2,
													width : 200
												} ]
											});
											formPanel.add(msgPanel);
											formPanel.doLayout();
											formPanel.taskId = this.taskId;
											this.formPanel = formPanel;
											if (this.isFormPanel) {
												Ext.Ajax
														.request({
															url : __ctxPath
																	+ "/flow/transProcessActivity.do",
															params : {
																taskId : this.taskId
															},
															scope : this,
															success : function(
																	response,
																	options) {
																var object = Ext.util.JSON
																		.decode(response.responseText);
																this
																		.getTopToolbar()
																		.add(
																				new Ext.Toolbar.Separator());
																for ( var i = 0; i < object.data.length; i++) {
																	this
																			.getTopToolbar()
																			.insert(
																					2 + i,
																					this
																							.genFormButton(
																									formPanel,
																									taskId,
																									object.data[i].name,
																									object.data[i].destination,
																									activityName));
																}
																this.doLayout();
															}
														});
											} else {
												formOuterPanel.doLayout();
											}
										} catch (e) {
											Ext.ux.Toast.msg("表单加载信息",
													"流程表单加载出现异常！" + e);
										}
									}
								});
						this.genFreeJumpPanel.call(this);
						rightPanel.add(this.freeJumpPanel);
						rightPanel.doLayout();
					},
					genFreeJumpPanel : function() {
						var a = "";
						this.freeTransCombo = new Ext.form.ComboBox(
								{
									xtype : "combo",
									allowBlank : false,
									editable : false,
									lazyInit : false,
									anchor : "96%,96%",
									hiddenName : "freeCombo",
									triggerAction : "all",
									listeners : {
										scope : this,
										select : function(d, b, c) {
											this.destName = b.data.destName;
											this.userJumpPanel.removeAll();
											if ("fork" == b.data.sourceType) {
												this.destType = "fork";
												this.genForkUserAssign
														.call(this);
											} else {
												if (b.data.sourceType
														.indexOf("end") == -1) {
													this.genCommonUserAssign
															.call(this);
												}
											}
										}
									},
									store : new Ext.data.ArrayStore(
											{
												autoLoad : true,
												url : __ctxPath
														+ "/flow/freeTransProcessActivity.do?taskId="
														+ this.taskId,
												fields : [ "signalName",
														"destName",
														"sourceType" ]
											}),
									displayField : "destName",
									valueField : "signalName"
								});
						this.userJumpPanel = new Ext.Panel({
							border : false,
							autoHeight : true,
							layout : "form",
							hideLabels : true
						});
						this.freeJumpPanel = new Ext.FormPanel({
							border : false,
							layout : "form",
							hideLabels : true,
							autoHeight : true,
							items : [ {
								xtype : "label",
								text : "跳转任务"
							}, this.freeTransCombo, this.userJumpPanel, {
								xtype : "button",
								text : "自由跳转",
								iconCls : "btn-transition",
								scope : this,
								handler : function() {
									this.freeJump.call(this);
								}
							} ]
						});
					},
					genCommonUserAssign : function() {
						this.userJumpPanel
								.add([
										{
											xtype : "label",
											text : this.destName + "务执行人:"
										},
										{
											xtype : "textfield",
											name : "nextAssignUserNames",
											width : 160,
											readOnly : true
										},
										{
											xtype : "button",
											name : "userSelectButton",
											text : "...",
											iconCls : "btn-users",
											scope : this,
											handler : function() {
												var a = this.freeJumpPanel
														.getCmpByName("nextAssignUserNames");
												UserSelector.getView({
													scope : this,
													callback : function(b, c) {
														a.setValue(c);
														this.flowAssignId = b;
													}
												}).show();
											}
										} ]);
						this.userJumpPanel.doLayout();
					},
					genForkUserAssign : function() {
						Ext.Ajax
								.request({
									url : __ctxPath
											+ "/flow/outerTransProcessActivity.do?taskId="
											+ this.taskId,
									params : {
										nodeName : this.destName
									},
									scope : this,
									success : function(d, a) {
										var c = Ext.decode(d.responseText);
										this.flowForkAssignId = new Array(
												c.length);
										for ( var b = 0; b < c.length; b++) {
											this.userJumpPanel
													.add(this.genUserFieldSel
															.call(this, c[b]));
										}
										this.userJumpPanel.doLayout();
									}
								});
					},
					genUserFieldSel : function(outers) {
						var destName = outers[1];
						var textField = new Ext.form.TextField({
							value : destName
						});
						var flowAssignUserName = new Ext.form.TextField({
							allowBlank : false
						});
						var cmpField = new Ext.form.CompositeField(
								{
									border : false,
									items : [
											{
												xtype : "displayfield",
												value : destName
											},
											flowAssignUserName,
											{
												xtype : "button",
												text : "...",
												iconCls : "btn-users",
												scope : this,
												handler : function() {
													UserSelector
															.getView(
																	{
																		scope : this,
																		callback : function(
																				uIds,
																				uNames) {
																			flowAssignUserName
																					.setValue(uNames);
																			eval("this.flowForkAssignId['"
																					+ destName
																					+ "']=uIds;");
																		}
																	}).show();
												}
											} ]
								});
						return cmpField;
					},
					freeJump : function() {
						if (!this.freeJumpPanel.getForm().isValid()) {
							return;
						}
						var e = null;
						if (!this.isFormPanel) {
							e = this.formPanel.formPanel.getForm();
						} else {
							e = this.formPanel.getForm();
						}
						if (e.isValid()) {
							var b = "";
							if (this.destType == "fork") {
								var c = 0;
								for ( var d in this.flowForkAssignId) {
									if (c++ >= this.flowForkAssignId.length) {
										break;
									}
									if (b != "") {
										b += "|";
									}
									b += d + ":" + this.flowForkAssignId[d];
								}
							} else {
								b = this.flowAssignId;
							}
							var a = this.freeTransCombo.getValue();
							e
									.submit({
										url : __ctxPath
												+ "/flow/nextProcessActivity.do",
										method : "post",
										waitMsg : "正在提交处理，请稍等",
										scope : this,
										params : {
											taskId : this.taskId,
											signalName : a,
											activityName : this.activityName,
											destName : this.destName,
											flowAssignId : b
										},
										success : function(f, h) {
											Ext.ux.Toast.msg("操作信息", "成功保存！");
											AppUtil.removeTab("ProcessForm"
													+ this.taskId);
											var i = Ext.getCmp("MyTaskGrid");
											var g = Ext.getCmp("TaskPanelView");
											if (g != null) {
												g
														.getUpdater()
														.update(
																__ctxPath
																		+ "/flow/displayTask.do");
											}
											if (i != null) {
												i.getStore().reload();
											}
										},
										failure : function(f, g) {
											Ext.ux.Toast.msg("操作信息",
													"操作出错，请联系管理员！");
										}
									});
						}
					},
					genFormButton : function(b, d, a, e, f) {
						var c = b;
						return {
							iconCls : "btn-transition",
							text : "转至[" + e + "]",
							listeners : {
								"click" : function() {
									if (c.save) {
										var g = c.save.call(c, d, a, e, f);
										if (g == false) {
											return;
										}
									}
									var h = c.getForm();
									if (h.isValid()) {
										h
												.submit({
													url : __ctxPath
															+ "/flow/nextProcessActivity.do",
													method : "post",
													waitMsg : "正在提交处理，请稍等",
													params : {
														taskId : d,
														signalName : a,
														activityName : f,
														destName : e
													},
													success : function(i, k) {
														Ext.ux.Toast
																.msg("操作信息",
																		"成功保存！");
														AppUtil
																.removeTab("ProcessForm"
																		+ d);
														var l = Ext
																.getCmp("MyTaskGrid");
														var j = Ext
																.getCmp("TaskPanelView");
														if (j != null) {
															j
																	.getUpdater()
																	.update(
																			__ctxPath
																					+ "/flow/displayTask.do");
														}
														if (l != null) {
															l.getStore()
																	.reload();
														}
													},
													failure : function(i, j) {
														Ext.ux.Toast.msg(
																"操作信息",
																"操作出错，请联系管理员！");
													}
												});
									}
								}
							}
						};
					},
					showFlowImage : function() {
						var a = new Ext.Window({
							autoScroll : true,
							iconCls : "btn-flow-chart",
							bodyStyle : "background-color:white",
							maximizable : true,
							title : "流程示意图",
							width : 600,
							height : 500,
							modal : true,
							layout : "fit",
							html : '<img src="' + __ctxPath
									+ "/jbpmImage?taskId=" + this.taskId
									+ "&rand=" + Math.random() + '"/>'
						});
						a.show();
					}
				});