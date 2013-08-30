var CompanyView = function() {
	return this.setup();
};
CompanyView.prototype.setup = function() {
	var b = this.topbar();
	var a = new Ext.form.FormPanel(
			{
				id : "CompanyView",
				closable : true,
				url : __ctxPath + "/system/addCompany.do",
				title : "单位信息",
				layout : "form",
				tbar : b,
				autoScroll : true,
				iconCls : "menu-company",
				reader : new Ext.data.JsonReader( {
					root : "result"
				}, [ {
					name : "companyId",
					mapping : "companyId"
				}, {
					name : "companyNo",
					mapping : "companyNo"
				}, {
					name : "companyName",
					mapping : "companyName"
				}, {
					name : "companyDesc",
					mapping : "companyDesc"
				}, {
					name : "legalPerson",
					mapping : "legalPerson"
				}, {
					name : "companySetup",
					mapping : "setup"
				}, {
					name : "companyPhone",
					mapping : "phone"
				}, {
					name : "companyFax",
					mapping : "fax"
				}, {
					name : "companySite",
					mapping : "site"
				}, {
					name : "companyLogo",
					mapping : "logo"
				} ]),
				items : [
						{
							xtype : "hidden",
							name : "company.companyId",
							id : "companyId"
						},
						{
							xtype : "container",
							labelAlign : "top",
							border : false,
							style : "padding-left:10%;padding-right:10%",
							layout : "form",
							items : [
									{
										xtype : "textfield",
										fieldLabel : "单位编号",
										name : "company.companyNo",
										id : "companyNo",
										anchor : "78%"
									},
									{
										xtype : "textfield",
										fieldLabel : "单位名称",
										name : "company.companyName",
										id : "companyName",
										allowBlank : false,
										anchor : "78%"
									},
									{
										layout : "column",
										border : false,
										anchor : "78%",
										items : [ {
											layout : "form",
											columnWidth : 0.5,
											border : false,
											style : "padding-left:0px;",
											items : [ {
												xtype : "textfield",
												fieldLabel : "负责人",
												name : "company.legalPerson",
												id : "legalPerson",
												anchor : "98%"
											}, {
												xtype : "textfield",
												fieldLabel : "电话",
												name : "company.phone",
												id : "companyPhone",
												anchor : "98%"
											} ]
										}, {
											layout : "form",
											border : false,
											columnWidth : 0.5,
											items : [ {
												fieldLabel : "成立时间",
												xtype : "datefield",
												format : "Y-m-d",
												name : "company.setup",
												id : "companySetup",
												anchor : "100%"
											}, {
												xtype : "textfield",
												fieldLabel : "传真",
												name : "company.fax",
												id : "companyFax",
												anchor : "100%"
											} ]
										} ]
									},
									{
										xtype : "hidden",
										fieldLabel : "Logo",
										name : "company.logo",
										id : "companyLogo"
									},
									{
										xtype : "container",
										fieldLabel : "单位主页",
										style : "padding-left:0px;padding-bottom:3px;",
										layout : "column",
										items : [
												{
													xtype : "textfield",
													name : "company.site",
													id : "companySite",
													width : 300
												},
												{
													xtype : "button",
													text : "访问单位主页",
													handler : function() {
														var e = Ext
																.getCmp("companySite");
														var d = e.getValue()
																.trim();
														if (d
																.indexOf("http://") == 0) {
															window.open(d,
																	"_bank");
														} else {
															Ext.ux.Toast.msg(
																	"提示信息",
																	"没写完整网址.");
														}
													}
												}, {
													xtype : "label",
													width : 80,
													text : "以http://开头"
												} ]
									},
									{
										xtype : "container",
										fieldLabel : "Logo",
										style : "padding-left:0px;padding-bottom:3px;",
										layout : "column",
										items : [
												{
													xtype : "container",
													border : true,
													style : "padding-left:0px;",
													layout : "form",
													height : 58,
													items : [ {
														xtype : "panel",
														height : 55,
														width : 247,
														id : "LogoPanel",
														autoScroll : true,
														html : '<img src="' + __ctxPath + '/images/default_image_car.jpg" width="100%" height="100%"/>'
													} ]
												},
												{
													border : false,
													xtype : "container",
													layout : "form",
													width : 93,
													style : "padding-left:3px;",
													items : [
															{
																xtype : "button",
																iconCls : "btn-add",
																text : "上传LOGO",
																handler : function() {
																	var d = Ext
																			.getCmp("companyLogo");
																	var e = App
																			.createUploadDialog( {
																				file_cat : "system/company",
																				callback : c,
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
																								Ext.Ajax
																										.request( {
																											url : __ctxPath
																													+ "/system/deleteFileAttach.do",
																											method : "post",
																											params : {
																												filePath : d.value
																											},
																											success : function() {
																												d
																														.setValue("");
																												var i = Ext
																														.getCmp("LogoPanel");
																												i.body
																														.update('<img src="' + __ctxPath + '/images/default_image_car.jpg" width="100%" height="100%" />');
																												var h = document
																														.getElementById("CompanyLogo");
																												h.src = __ctxPath
																														+ "/images/";
																												Ext.Ajax
																														.request( {
																															url : __ctxPath
																																	+ "/system/delphotoCompany.do",
																															method : "post",
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
															},
															{
																xtype : "button",
																text : "删除LOGO",
																iconCls : "btn-delete",
																handler : function() {
																	var d = Ext
																			.getCmp("companyLogo");
																	if (d.value != null
																			&& d.value != ""
																			&& d.value != "undefined") {
																		var e = "LOGO一旦删除将不可恢复,";
																		Ext.Msg
																				.confirm(
																						"确认信息",
																						e
																								+ "是否删除?",
																						function(
																								f) {
																							if (f == "yes") {
																								Ext.Ajax
																										.request( {
																											url : __ctxPath
																													+ "/system/deleteFileAttach.do",
																											method : "post",
																											params : {
																												filePath : d.value
																											},
																											success : function() {
																												Ext.Ajax
																														.request( {
																															url : __ctxPath
																																	+ "/system/delphotoCompany.do",
																															method : "post",
																															success : function() {
																																d
																																		.setValue("");
																																var h = Ext
																																		.getCmp("LogoPanel");
																																h.body
																																		.update('<img src="' + __ctxPath + '/images/default_image_car.jpg" width="100%" height="100%" />');
																																var g = document
																																		.getElementById("CompanyLogo");
																																g.src = __ctxPath
																																		+ "/images/ht-logo.png";
																															}
																														});
																											}
																										});
																							}
																						});
																	} else {
																		Ext.ux.Toast
																				.msg(
																						"提示信息",
																						"您还未增加照片.");
																	}
																}
															} ]
												},
												{
													border : false,
													xtype : "label",
													width : 150,
													html : '<a style="color:red;">请上传比例为247*55的图片,透明底的图片更佳</a>'
												} ]
									}, {
										xtype : "htmleditor",
										fieldLabel : "单位描述",
										name : "company.companyDesc",
										id : "companyDesc",
										height : 200,
										anchor : "78%"
									} ]
						} ]
			});
	Ext.Ajax.request( {
		url : __ctxPath + "/system/checkCompany.do",
		success : function(d, f) {
			var e = Ext.util.JSON.decode(d.responseText);
			if (e.success) {
				a.form.load( {
					url : __ctxPath + "/system/listCompany.do",
					deferredRender : true,
					layoutOnTabChange : true,
					waitMsg : "正在载入数据...",
					success : function(g, h) {
						var j = h.result.data.companyLogo;
						var i = Ext.getCmp("LogoPanel");
						if (j != null && j != "" && j != "undefind"
								&& i.body != null) {
							i.body.update('<img src="' + __ctxPath
									+ "/attachFiles/" + j
									+ '"  width="100%" height="100%"/>');
						}
					},
					failure : function(g, h) {
						Ext.ux.Toast.msg("编辑", "载入失败");
					}
				});
			} else {
				Ext.ux.Toast.msg("提示", "还没填写单位信息");
			}
		},
		failure : function(d, e) {
		}
	});
	function c(f) {
		var d = Ext.getCmp("companyLogo");
		var g = Ext.getCmp("LogoPanel");
		d.setValue(f[0].filepath);
		g.body.update('<img src="' + __ctxPath + "/attachFiles/"
				+ f[0].filepath + '"  width="100%" height="100%"/>');
		var e = document.getElementById("CompanyLogo");
		e.src = __ctxPath + "/attachFiles/" + f[0].filepath;
	}
	return a;
};
CompanyView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "CompanyTopBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : []
	});
	if (isGranted("_CompanyEdit")) {
		a.add(new Ext.Button( {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var b = Ext.getCmp("CompanyView");
				if (b.getForm().isValid()) {
					b.getForm().submit(
							{
								waitMsg : "正在修改单位信息",
								success : function(c, e) {
									Ext.ux.Toast.msg("操作信息", "单位信息保存成功！");
									var d = Ext.getCmp("companyName")
											.getValue();
									Ext.getCmp("toolbarCompanyName").setText(
											d + "办公协同管理系统");
								}
							});
				}
			}
		}));
	}
	a.add(new Ext.Button( {
		text : "关闭",
		iconCls : "btn-close",
		handler : function() {
			var b = Ext.getCmp("centerTabPanel");
			b.remove("CompanyView");
		}
	}));
	return a;
};