AppRoleForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						AppRoleForm.superclass.constructor.call(this, {
							layout : "fit",
							items : this.formPanel,
							border : false,
							modal : true,
							plain : true,
							id : "AppRoleFormWin",
							title : "角色详细信息",
							iconCls : "menu-role",
							width : 370,
							height : 220,
							minWidth : 300,
							minHeight : 200,
							bodyStyle : "padding:5px;",
							buttonAlign : "center",
							buttons : this.buttons
						});
					},
					initUIComponents : function() {
						this.formPanel = new Ext.FormPanel( {
							url : __ctxPath + "/system/saveAppRole.do?isCopy="
									+ this.isCopy,
							layout : "form",
							id : "AppRoleForm",
							frame : true,
							defaults : {
								widht : 400,
								anchor : "100%,100%"
							},
							formId : "AppRoleFormId",
							defaultType : "textfield",
							items : [ {
								name : "appRole.roleId",
								id : "roleId",
								xtype : "hidden",
								value : this.roleId == null ? "" : this.roleId
							}, {
								fieldLabel : "角色名称",
								name : "appRole.roleName",
								id : "roleName"
							}, {
								fieldLabel : "角色描述",
								xtype : "textarea",
								name : "appRole.roleDesc",
								id : "roleDesc"
							}, {
								fieldLabel : "状态",
								hiddenName : "appRole.status",
								id : "status",
								xtype : "combo",
								mode : "local",
								editable : true,
								triggerAction : "all",
								store : [ [ "0", "禁用" ], [ "1", "可用" ] ],
								value : 0
							} ]
						});
						if (this.roleId != null && this.roleId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/system/getAppRole.do?roleId="
														+ this.roleId,
												waitMsg : "正在载入数据...",
												success : function(a, b) {
												},
												failure : function(a, b) {
												}
											});
						}
						this.buttons = [
								{
									text : "保存",
									iconCls : "btn-save",
									handler : function() {
										var b = Ext.getCmp("AppRoleForm");
										if (this.isCopy == 1) {
											var a = Ext.getCmp("roleName")
													.getValue();
											Ext.Ajax
													.request( {
														url : __ctxPath
																+ "/system/checkAppRole.do",
														params : {
															roleName : a
														},
														method : "post",
														success : function(d) {
															var c = Ext.util.JSON
																	.decode(d.responseText);
															if (c.success) {
																if (b
																		.getForm()
																		.isValid()) {
																	b
																			.getForm()
																			.submit(
																					{
																						method : "post",
																						waitMsg : "正在提交数据...",
																						success : function(
																								e,
																								f) {
																							Ext.ux.Toast
																									.msg(
																											"操作信息",
																											"成功信息保存！");
																							Ext
																									.getCmp(
																											"AppRoleGrid")
																									.getStore()
																									.reload();
																							window
																									.close();
																						},
																						failure : function(
																								e,
																								f) {
																							Ext.MessageBox
																									.show( {
																										title : "操作信息",
																										msg : "信息保存出错，请联系管理员！",
																										buttons : Ext.MessageBox.OK,
																										icon : "ext-mb-error"
																									});
																							Ext
																									.getCmp(
																											"AppRoleFormWin")
																									.close();
																						}
																					});
																}
															} else {
																Ext.ux.Toast
																		.msg(
																				"提示信息",
																				"该角色名字已经存在，请更改！");
															}
														},
														failure : function() {
														}
													});
										} else {
											if (b.getForm().isValid()) {
												b
														.getForm()
														.submit(
																{
																	method : "post",
																	waitMsg : "正在提交数据...",
																	success : function(
																			c,
																			d) {
																		Ext.ux.Toast
																				.msg(
																						"操作信息",
																						"成功信息保存！");
																		Ext
																				.getCmp(
																						"AppRoleGrid")
																				.getStore()
																				.reload();
																		Ext
																				.getCmp(
																						"AppRoleFormWin")
																				.close();
																	},
																	failure : function(
																			c,
																			d) {
																		Ext.MessageBox
																				.show( {
																					title : "操作信息",
																					msg : "信息保存出错，请联系管理员！",
																					buttons : Ext.MessageBox.OK,
																					icon : "ext-mb-error"
																				});
																		Ext
																				.getCmp(
																						"AppRoleFormWin")
																				.close();
																	}
																});
											}
										}
									}
								}, {
									text : "取消",
									iconCls : "btn-cancel",
									handler : function() {
										Ext.getCmp("AppRoleFormWin").close();
									}
								} ];
					}
				});