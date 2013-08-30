var AppUserForm = function(a, b) {
	return this.setup(a, b);
};
AppUserForm.prototype.setup = function(d, c) {
	var b = __ctxPath + "/system/listDepartment.do?opt=appUser";
	var f = new TreeSelector("depTreeSelector", b, "所属部门", "appUser.depId",
			false);
	Ext.getCmp("depTreeSelectorTree").on("click", function(g) {
		Ext.getCmp("appUser.position").setValue("");
		Ext.getCmp("appUser.position").getStore().removeAll();
	});
	var e = this.initFooterToolbar(c);
	var a = new Ext.form.FormPanel(
			{
				id : "AppUserForm",
				title : d,
				closable : true,
				iconCls : "menu-customer",
				border : false,
				autoScroll : true,
				bodyStyle : "margin-top:5px;margin-left: 4%; background-color: transparent;",
				labelAlign : "right",
				layout : "table",
				autoScroll : true,
				tbar : e,
				defaultType : "textfield",
				layoutConfig : {
					columns : 3
				},
				url : __ctxPath + "/system/saveAppUser.do",
				reader : new Ext.data.JsonReader({
					root : "data"
				}, [ {
					name : "appUser.userId",
					mapping : "userId"
				}, {
					name : "appUser.username",
					mapping : "username"
				}, {
					name : "appUser.password",
					mapping : "password"
				}, {
					name : "appUser.fullname",
					mapping : "fullname"
				}, {
					name : "appUser.email",
					mapping : "email"
				}, {
					name : "appUser.depName",
					mapping : "department.depName"
				}, {
					name : "appUser.accessionTime",
					mapping : "accessionTime"
				}, {
					name : "appUserStatus",
					mapping : "status"
				}, {
					name : "appUserTitle",
					mapping : "title"
				}, {
					name : "appUser.position",
					mapping : "position"
				}, {
					name : "appUser.phone",
					mapping : "phone"
				}, {
					name : "appUser.mobile",
					mapping : "mobile"
				}, {
					name : "appUser.fax",
					mapping : "fax"
				}, {
					name : "appUser.address",
					mapping : "address"
				}, {
					name : "appUser.zip",
					mapping : "zip"
				}, {
					name : "appUser.photo",
					mapping : "photo"
				} ]),
				items : [
						{
							id : "displayUserPhoto",
							xtype : "panel",
							width : 230,
							rowspan : 2,
							height : 435,
							title : "个人照片",
							html : '<img src="' + __ctxPath
									+ '/images/default_image_male.jpg"/>',
							tbar : new Ext.Toolbar({
								height : 30,
								items : [ {
									text : "上传",
									iconCls : "btn-upload",
									handler : function() {
										AppUserForm.uploadPhotoBtn(c);
									}
								}, {
									text : "删除",
									iconCls : "btn-delete",
									handler : function() {
										AppUserForm.deletePhotoBtn(c);
									}
								} ]
							})
						},
						{
							xtype : "panel",
							id : "AppUserMustInfo",
							width : 265,
							height : 215,
							title : "基本信息(必填)",
							layout : "form",
							defaultType : "textfield",
							defaults : {
								width : 163
							},
							labelWidth : 55,
							labelAlign : "right",
							hideLabels : false,
							items : [ {
								xtype : "hidden",
								fieldLabel : "员工ID",
								name : "appUser.userId",
								id : "appUser.userId"
							}, {
								fieldLabel : "登录账号",
								name : "appUser.username",
								id : "appUser.username",
								allowBlank : false,
								blankText : "登录账号不能为空!"
							}, {
								fieldLabel : "登录密码",
								name : "appUser.password",
								id : "appUser.password",
								inputType : "password",
								allowBlank : false,
								blankText : "登录密码不能为空!"
							}, {
								fieldLabel : "员工姓名",
								name : "appUser.fullname",
								id : "appUser.fullname",
								allowBlank : false,
								blankText : "员工姓名不能为空!"
							}, {
								fieldLabel : "E-mail",
								name : "appUser.email",
								id : "appUser.email",
								vtype : "email",
								allowBlank : false,
								blankText : "邮箱不能为空!",
								vtypeText : "邮箱格式不正确!"
							}, f, {
								fieldLabel : "入职时间",
								xtype : "datefield",
								format : "Y-m-d",
								name : "appUser.accessionTime",
								id : "appUser.accessionTime",
								allowBlank : false,
								blankText : "入职时间不能为空!",
								length : 50
							}, {
								fieldLabel : "状态",
								id : "appUserStatus",
								hiddenName : "appUser.status",
								xtype : "combo",
								mode : "local",
								editable : false,
								triggerAction : "all",
								store : [ [ "1", "激活" ], [ "0", "禁用" ] ],
								value : 1
							}, {
								xtype : "hidden",
								name : "appUser.department.depId",
								id : "appUser.depId"
							} ]
						},
						{
							xtype : "panel",
							width : 265,
							height : 215,
							title : "扩展信息(选填)",
							layout : "form",
							defaultType : "textfield",
							labelWidth : 55,
							defaults : {
								width : 163
							},
							hideLabel : false,
							items : [
									{
										fieldLabel : "性别",
										xtype : "combo",
										hiddenName : "appUser.title",
										id : "appUserTitle",
										mode : "local",
										editable : false,
										triggerAction : "all",
										store : [ [ "1", "先生" ], [ "0", "女士" ] ],
										value : "1",
										listeners : {
											select : function(k, g, i) {
												var h = Ext
														.getCmp("appUser.photo");
												if (h.value == ""
														|| h.value == "undefined"
														|| h.value == null) {
													var j = Ext
															.getCmp("displayUserPhoto");
													if (k.value == "0") {
														j.body
																.update('<img src="'
																		+ __ctxPath
																		+ '/images/default_image_female.jpg" />');
													} else {
														j.body
																.update('<img src="'
																		+ __ctxPath
																		+ '/images/default_image_male.jpg" />');
													}
												}
											}
										}
									},
									{
										fieldLabel : "职位",
										name : "appUser.position",
										id : "appUser.position",
										xtype : "combo",
										mode : "local",
										editable : false,
										valueField : "jobName",
										displayField : "jobName",
										triggerAction : "all",
										store : new Ext.data.SimpleStore({
											url : __ctxPath
													+ "/hrm/comboJob.do",
											fields : [ "jobId", "jobName" ]
										}),
										listeners : {
											focus : function(h) {
												var g = Ext.getCmp(
														"appUser.depId")
														.getValue();
												if (g != null && g != ""
														&& g != "undefined") {
													Ext.getCmp(
															"appUser.position")
															.getStore()
															.reload({
																params : {
																	depId : g
																}
															});
												} else {
													Ext.ux.Toast.msg("操作信息",
															"请先选择部门！");
												}
											}
										}
									}, {
										fieldLabel : "家庭电话",
										name : "appUser.phone",
										id : "appUser.phone"
									}, {
										fieldLabel : "移动电话",
										xtype : "numberfield",
										name : "appUser.mobile",
										id : "appUser.mobile"
									}, {
										fieldLabel : "传真",
										name : "appUser.fax",
										id : "appUser.fax"
									}, {
										fieldLabel : "家庭住址",
										name : "appUser.address",
										id : "appUser.address"
									}, {
										fieldLabel : "邮编",
										xtype : "numberfield",
										name : "appUser.zip",
										id : "appUser.zip"
									}, {
										filedLabel : "照片",
										xtype : "hidden",
										id : "appUser.photo",
										name : "appUser.photo"
									} ]
						},
						{
							xtype : "panel",
							title : "用户角色",
							width : 530,
							height : 220,
							colspan : 2,
							items : [ {
								xtype : "itemselector",
								id : "AppUserRoles",
								name : "AppUserRoles",
								fromLegend : "",
								imagePath : __ctxPath + "/ext3/ux/images/",
								multiselects : [
										{
											id : "chooseRoles",
											title : "可选角色",
											width : 247,
											height : 190,
											store : new Ext.data.SimpleStore(
													{
														autoLoad : true,
														baseParams : {
															userId : c
														},
														url : __ctxPath
																+ "/system/chooseRolesAppUser.do",
														fields : [ "roleId",
																"roleName" ]
													}),
											displayField : "roleName",
											valueField : "roleId"
										},
										{
											id : "selectedRoles",
											name : "selectedRoles",
											title : "已有角色",
											width : 247,
											height : 190,
											store : new Ext.data.SimpleStore(
													{
														autoLoad : true,
														baseParams : {
															userId : c
														},
														url : __ctxPath
																+ "/system/selectedRolesAppUser.do",
														fields : [ "roleId",
																"roleName" ]
													}),
											tbar : [ {
												text : "清除所选",
												handler : function() {
													Ext
															.getCmp(
																	"AppUserForm")
															.getForm()
															.findField(
																	"AppUserRoles")
															.reset();
												}
											} ],
											displayField : "roleName",
											valueField : "roleId"
										} ]
							} ]
						} ]
			});
	return a;
};
AppUserForm.prototype.initFooterToolbar = function(a) {
	var b = new Ext.Toolbar({
		id : "AppUserFormToolbar",
		height : 30,
		items : [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var c = Ext.getCmp("AppUserForm");
				if (c.getForm().isValid()) {
					c.getForm().submit({
						waitMsg : "正在提交用户信息",
						success : function(d, f) {
							Ext.ux.Toast.msg("操作信息", "保存成功！");
							var e = Ext.getCmp("AppUserGrid");
							if (e != null) {
								e.getStore().reload({
									start : 0,
									limit : 25
								});
							}
							AppUtil.removeTab("AppUserForm");
						},
						failure : function(d, e) {
							Ext.ux.Toast.msg("错误信息", e.result.msg);
							Ext.getCmp("appUser.username").setValue("");
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "reset",
			handler : function() {
				var c = Ext.getCmp("centerTabPanel");
				c.remove("AppUserForm");
			}
		}, {
			text : "修改密码",
			id : "resetPassword",
			hidden : true,
			iconCls : "btn-password",
			handler : function() {
				new ResetPasswordForm(a);
			}
		} ]
	});
	if (a) {
		b.add([ "-", {
			text : "代办账号",
			iconCls : "btn-super",
			handler : function() {
				var c = Ext.getCmp("appUser.username").getValue();
				new UserAgentWindow({
					userId : a,
					username : c
				}).show();
			}
		} ]);
	}
	return b;
};
AppUserForm.uploadPhotoBtn = function(c) {
	var a = Ext.getCmp("appUser.photo");
	var b = App.createUploadDialog({
		file_cat : "system/appUser",
		callback : uploadUserPhoto,
		permitted_extensions : [ "jpg" ]
	});
	if (a.value != "" && a.value != null && a.value != "undefined") {
		var d = "再次上传需要先删除原有图片,";
		Ext.Msg
				.confirm(
						"信息确认",
						d + "是否删除？",
						function(e) {
							if (e == "yes") {
								Ext.Ajax
										.request({
											url : __ctxPath
													+ "/system/deleteFileAttach.do",
											method : "post",
											params : {
												filePath : a.value
											},
											success : function() {
												if (c != "" && c != null
														&& c != "undefined") {
													Ext.Ajax
															.request({
																url : __ctxPath
																		+ "/system/photoAppUser.do",
																method : "post",
																params : {
																	userId : c
																},
																success : function() {
																	a
																			.setValue("");
																	var h = Ext
																			.getCmp("appUserTitle");
																	var i = Ext
																			.getCmp("displayUserPhoto");
																	if (h.value == 1) {
																		i.body
																				.update('<img src="'
																						+ __ctxPath
																						+ '/images/default_image_male.jpg" />');
																	} else {
																		i.body
																				.update('<img src="'
																						+ __ctxPath
																						+ '/images/default_image_female.jpg" />');
																	}
																	b
																			.show("queryBtn");
																}
															});
												} else {
													a.setValue("");
													var f = Ext
															.getCmp("appUserTitle");
													var g = Ext
															.getCmp("displayUserPhoto");
													if (f.value == 1) {
														g.body
																.update('<img src="'
																		+ __ctxPath
																		+ '/images/default_image_male.jpg" />');
													} else {
														g.body
																.update('<img src="'
																		+ __ctxPath
																		+ '/images/default_image_female.jpg" />');
													}
													b.show("queryBtn");
												}
											}
										});
							}
						});
	} else {
		b.show("queryBtn");
	}
};
AppUserForm.deletePhotoBtn = function(b) {
	var a = Ext.getCmp("appUser.photo");
	if (a.value != null && a.value != "" && a.value != "undefined") {
		Ext.Msg
				.confirm(
						"确认信息",
						"照片一旦删除将不可恢复, 是否删除?",
						function(c) {
							if (c == "yes") {
								Ext.Ajax
										.request({
											url : __ctxPath
													+ "/system/deleteFileAttach.do",
											method : "post",
											params : {
												filePath : a.value
											},
											success : function() {
												if (b != "" && b != null
														&& b != "undefined") {
													Ext.Ajax
															.request({
																url : __ctxPath
																		+ "/system/photoAppUser.do",
																method : "post",
																params : {
																	userId : b
																},
																success : function() {
																	a
																			.setValue("");
																	var f = Ext
																			.getCmp("appUserTitle");
																	var g = Ext
																			.getCmp("displayUserPhoto");
																	if (f.value == 1) {
																		g.body
																				.update('<img src="'
																						+ __ctxPath
																						+ '/images/default_image_male.jpg" />');
																	} else {
																		g.body
																				.update('<img src="'
																						+ __ctxPath
																						+ '/images/default_image_female.jpg" />');
																	}
																}
															});
												} else {
													a.setValue("");
													var d = Ext
															.getCmp("appUserTitle");
													var e = Ext
															.getCmp("displayUserPhoto");
													if (d.value == 1) {
														e.body
																.update('<img src="'
																		+ __ctxPath
																		+ '/images/default_image_male.jpg" />');
													} else {
														e.body
																.update('<img src="'
																		+ __ctxPath
																		+ '/images/default_image_female.jpg" />');
													}
												}
											}
										});
							}
						});
	} else {
		Ext.ux.Toast.msg("提示信息", "您还未增加照片.");
	}
};
function uploadUserPhoto(b) {
	var a = Ext.getCmp("appUser.photo");
	var c = Ext.getCmp("displayUserPhoto");
	a.setValue(b[0].filepath);
	c.body.update('<img src="' + __ctxPath + "/attachFiles/" + b[0].filepath
			+ '"  width="100%" height="100%"/>');
}