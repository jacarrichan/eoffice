var ProfileForm = function(a) {
	return this.setup(a);
};
ProfileForm.prototype.setup = function(b) {
	var c = this.initFooterToolbar(b);
	var a = new Ext.form.FormPanel(
			{
				id : "ProfileForm",
				title : "个人资料",
				closable : true,
				iconCls : "menu-customer",
				border : false,
				autoScroll : true,
				labelAlign : "right",
				layout : "fit",
				tbar : c,
				defaultType : "textfield",
				url : __ctxPath + "/system/profileAppUser.do",
				reader : new Ext.data.JsonReader({
					root : "data"
				}, [ {
					name : "appUser.userId",
					mapping : "userId"
				}, {
					name : "appUser.username",
					mapping : "username"
				}, {
					name : "appUser.fullname",
					mapping : "fullname"
				}, {
					name : "appUser.email",
					mapping : "email"
				}, {
					name : "profileTitle",
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
				items : [ {
					xtype : "panel",
					frame : false,
					autoWidth : true,
					autoHeight : true,
					border : false,
					layout : "table",
					bodyStyle : "margin-top:5px;margin-left: 17%; background-color: transparent;",
					layoutConfig : {
						columns : 2
					},
					items : [
							{
								id : "displayProfilePhoto",
								xtype : "panel",
								width : 230,
								rowspan : 2,
								style : "padding:3px 4px 25px 0px;",
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
											ProfileForm.uploadPhotoBtn(b);
										}
									}, {
										text : "删除",
										iconCls : "btn-delete",
										handler : function() {
											ProfileForm.deleteUserPhoto(b);
										}
									} ]
								})
							},
							{
								xtype : "panel",
								id : "ProfileMustInfo",
								width : 305,
								height : 435,
								title : "个人资料",
								layout : "form",
								style : "padding:3px 4px 25px 0px;",
								defaultType : "textfield",
								defaults : {
									width : 203
								},
								labelWidth : 55,
								labelAlign : "right",
								hideLabels : false,
								items : [
										{
											xtype : "hidden",
											fieldLabel : "员工ID",
											name : "appUser.userId",
											id : "profile.userId"
										},
										{
											fieldLabel : "登录账号",
											name : "appUser.username",
											id : "profile.username",
											readOnly : true
										},
										{
											fieldLabel : "员工姓名",
											name : "appUser.fullname",
											id : "profile.fullname",
											allowBlank : false,
											blankText : "员工姓名不能为空!"
										},
										{
											fieldLabel : "E-mail",
											name : "appUser.email",
											id : "profile.email",
											vtype : "email",
											allowBlank : false,
											blankText : "邮箱不能为空!",
											vtypeText : "邮箱格式不正确!"
										},
										{
											fieldLabel : "性别",
											xtype : "combo",
											hiddenName : "appUser.title",
											id : "profileTitle",
											mode : "local",
											editable : false,
											triggerAction : "all",
											store : [ [ "1", "先生" ],
													[ "0", "女士" ] ],
											value : "1",
											listeners : {
												select : function(h, d, f) {
													var e = Ext
															.getCmp("profile.photo");
													if (e.value == ""
															|| e.value == "undefined"
															|| e.value == null) {
														var g = Ext
																.getCmp("displayProfilePhoto");
														if (h.value == "0") {
															g.body
																	.update('<img src="'
																			+ __ctxPath
																			+ '/images/default_image_female.jpg" />');
														} else {
															g.body
																	.update('<img src="'
																			+ __ctxPath
																			+ '/images/default_image_male.jpg" />');
														}
													}
												}
											}
										}, {
											fieldLabel : "家庭电话",
											name : "appUser.phone",
											id : "profile.phone"
										}, {
											fieldLabel : "移动电话",
											xtype : "numberfield",
											name : "appUser.mobile",
											id : "profile.mobile"
										}, {
											fieldLabel : "传真",
											name : "appUser.fax",
											id : "profile.fax"
										}, {
											fieldLabel : "家庭住址",
											name : "appUser.address",
											id : "profile.address"
										}, {
											fieldLabel : "邮编",
											xtype : "numberfield",
											name : "appUser.zip",
											id : "profile.zip"
										}, {
											filedLabel : "照片",
											xtype : "hidden",
											id : "profile.photo",
											name : "appUser.photo"
										} ]
							} ]
				} ]
			});
	a.getForm().load(
			{
				deferredRender : false,
				url : __ctxPath + "/system/getAppUser.do",
				waitMsg : "正在载入数据...",
				success : function(f, g) {
					var d = Ext.getCmp("profile.photo");
					var h = Ext.getCmp("displayProfilePhoto");
					var e = Ext.getCmp("profileTitle");
					if (d.value != "" && d.value != null
							&& d.value != "undefined") {
						h.body.update('<img src="' + __ctxPath
								+ "/attachFiles/" + d.value
								+ '" width="100%" height="100%"/>');
					} else {
						if (e.value == "0") {
							h.body.update('<img src="' + __ctxPath
									+ '/images/default_image_female.jpg" />');
						}
					}
				},
				failure : function(d, e) {
					Ext.ux.Toast.msg("编辑", "载入失败");
				}
			});
	return a;
};
ProfileForm.prototype.initFooterToolbar = function(a) {
	var b = new Ext.Toolbar({
		id : "ProfileFormToolbar",
		height : 30,
		items : [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var c = Ext.getCmp("ProfileForm");
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
					}
				});
			}
		}, {
			text : "取消",
			iconCls : "reset",
			handler : function() {
				var c = Ext.getCmp("centerTabPanel");
				c.remove("ProfileForm");
			}
		}, {
			text : "修改密码",
			iconCls : "btn-password",
			handler : function() {
				new ResetPasswordForm(a);
			}
		} ]
	});
	return b;
};
ProfileForm.uploadPhotoBtn = function(c) {
	var a = Ext.getCmp("profile.photo");
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
																			.getCmp("profileTitle");
																	var i = Ext
																			.getCmp("displayProfilePhoto");
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
													var g = Ext
															.getCmp("profileTitle");
													var f = Ext
															.getCmp("displayProfilePhoto");
													if (g.value == 1) {
														f.body
																.update('<img src="'
																		+ __ctxPath
																		+ '/images/default_image_male.jpg" />');
													} else {
														f.body
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
ProfileForm.deleteUserPhoto = function(b) {
	var a = Ext.getCmp("profile.photo");
	if (a.value != null && a.value != "" && a.value != "undefined") {
		var c = "照片一旦删除将不可恢复,";
		Ext.Msg
				.confirm(
						"确认信息",
						c + "是否删除?",
						function(d) {
							if (d == "yes") {
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
																	var h = Ext
																			.getCmp("profileTitle");
																	var g = Ext
																			.getCmp("displayProfilePhoto");
																	if (h.value == 1) {
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
													var f = Ext
															.getCmp("profileTitle");
													var e = Ext
															.getCmp("displayProfilePhoto");
													if (f.value == 1) {
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
	var a = Ext.getCmp("profile.photo");
	var c = Ext.getCmp("displayProfilePhoto");
	a.setValue(b[0].filepath);
	c.body.update('<img src="' + __ctxPath + "/attachFiles/" + b[0].filepath
			+ '"  width="100%" height="100%"/>');
}