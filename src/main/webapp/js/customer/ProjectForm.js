Ext.ns("ProjectForm");
ProjectForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						ProjectForm.superclass.constructor.call(this, {
							layout : "fit",
							id : "ProjectFormWin",
							title : "项目详细信息",
							iconCls : "menu-project",
							items : this.formPanel,
							maximizable : true,
							border : false,
							width : 873,
							height : 485,
							minWidth : 872,
							minHeight : 484,
							modal : true,
							plain : true,
							bodyStyle : "padding:5px;",
							buttonAlign : "center",
							buttons : this.buttons
						});
					},
					initUIComponents : function() {
						this.formPanel = new Ext.FormPanel(
								{
									url : __ctxPath
											+ "/customer/saveProject.do",
									layout : "hbox",
									layoutConfig : {
										padding : "5",
										align : "middle"
									},
									defaults : {
										margins : "0 5 0 0"
									},
									id : "ProjectForm",
									frame : false,
									formId : "ProjectFormId",
									defaultType : "textfield",
									items : [
											{
												xtype : "fieldset",
												title : "项目信息",
												layout : "form",
												width : 420,
												defaultType : "textfield",
												labelWidth : 80,
												defaults : {
													width : 295
												},
												items : [
														{
															xtype : "compositefield",
															fieldLabel : "项目编号*",
															items : [
																	{
																		xtype : "textfield",
																		width : 150,
																		name : "project.projectNo",
																		id : "projectNo",
																		allowBlank : false,
																		blankText : "项目编号不能为空!",
																		listeners : {
																			change : function(
																					a,
																					b,
																					c) {
																				ProjectForm
																						.checkProjectNo(b);
																			}
																		}
																	},
																	{
																		id : "getNoButton",
																		xtype : "button",
																		text : "系统生成",
																		iconCls : "btn-system-setting",
																		handler : function() {
																			Ext.Ajax
																					.request({
																						url : __ctxPath
																								+ "/customer/numberProject.do",
																						success : function(
																								b) {
																							var a = Ext.util.JSON
																									.decode(b.responseText);
																							Ext
																									.getCmp(
																											"projectNo")
																									.setValue(
																											a.projectNo);
																							Ext
																									.getCmp("CheckMessage").body
																									.update("");
																						}
																					});
																		}
																	},
																	{
																		id : "CheckMessage",
																		xtype : "panel",
																		border : false,
																		style : "padding-top:3px"
																	},
																	{
																		name : "project.projectId",
																		id : "projectId",
																		xtype : "hidden",
																		value : this.projectId == null ? ""
																				: this.projectId
																	} ]
														},
														{
															fieldLabel : "项目名称*",
															name : "project.projectName",
															id : "projectName",
															allowBlank : false,
															blankText : "项目名称不能为空!"
														},
														{
															fieldLabel : "需求描述",
															name : "project.reqDesc",
															xtype : "htmleditor",
															autoHeight : true,
															id : "reqDesc"
														},
														{
															fieldLabel : "是否签订合同",
															hiddenName : "project.isContract",
															id : "isContract",
															xtype : "combo",
															mode : "local",
															editable : true,
															triggerAction : "all",
															store : [
																	[ "0", "无" ],
																	[ "1", "有" ] ],
															value : 0
														},
														{
															fieldLabel : "业务人员",
															name : "project.userId",
															id : "userId",
															xtype : "hidden"
														},
														{
															xtype : "compositefield",
															fieldLabel : "业务人员*",
															items : [
																	{
																		xtype : "textfield",
																		width : 180,
																		readOnly : true,
																		allowBlank : false,
																		blankText : "业务人员不能为空!",
																		name : "salesman",
																		id : "salesman"
																	},
																	{
																		xtype : "button",
																		width : 60,
																		text : "业务人员",
																		iconCls : "btn-mail_recipient",
																		handler : function() {
																			UserSelector
																					.getView(
																							function(
																									b,
																									a) {
																								Ext
																										.getCmp(
																												"salesman")
																										.setValue(
																												a);
																								Ext
																										.getCmp(
																												"userId")
																										.setValue(
																												b);
																							},
																							true)
																					.show();
																		}
																	} ]
														},
														{
															xtype : "compositefield",
															fieldLabel : "项目附件",
															items : [
																	{
																		xtype : "panel",
																		id : "displayProjectAttach",
																		width : 215,
																		height : 65,
																		frame : false,
																		autoScroll : true,
																		style : "padding-left:0px;padding-top:0px;",
																		html : ""
																	},
																	{
																		xtype : "button",
																		iconCls : "btn-upload",
																		text : "上传",
																		handler : function() {
																			var a = App
																					.createUploadDialog({
																						file_cat : "csutomer/project",
																						callback : uploadProjectAttach
																					});
																			a
																					.show("queryBtn");
																		}
																	},
																	{
																		xtype : "hidden",
																		name : "projectAttachIDs",
																		id : "projectAttachIDs"
																	} ]
														} ]
											},
											{
												xtype : "fieldset",
												title : "负责人信息",
												layout : "form",
												width : 420,
												labelWidth : 80,
												defaultType : "textfield",
												defaults : {
													width : 295
												},
												items : [
														{
															fieldLabel : "所属客户",
															name : "project.customerId",
															id : "customerId",
															xtype : "hidden"
														},
														{
															xtype : "compositefield",
															fieldLabel : "所属客户*",
															items : [
																	{
																		xtype : "textfield",
																		width : 180,
																		readOnly : true,
																		allowBlank : false,
																		blankText : "所属客户为必选!",
																		name : "customerName",
																		id : "customerName"
																	},
																	{
																		xtype : "button",
																		text : "选择客户",
																		iconCls : "menu-customerView",
																		handler : function() {
																			CustomerSelector
																					.getView(
																							function(
																									b,
																									a) {
																								Ext
																										.getCmp(
																												"customerId")
																										.setValue(
																												b);
																								Ext
																										.getCmp(
																												"customerName")
																										.setValue(
																												a);
																								Ext
																										.getCmp(
																												"fullname")
																										.getStore()
																										.reload(
																												{
																													params : {
																														"Q_customer.customerId_L_EQ" : b
																													}
																												});
																							},
																							true)
																					.show();
																		}
																	} ]
														},
														{
															fieldLabel : "联系人姓名",
															allowBlank : false,
															blankText : "联系人不能为空!",
															name : "project.fullname",
															id : "fullname",
															xtype : "combo",
															mode : "local",
															editable : true,
															triggerAction : "all",
															store : new Ext.data.SimpleStore(
																	{
																		method : "post",
																		url : __ctxPath
																				+ "/customer/findCusLinkman.do",
																		fields : [
																				"linkmanId",
																				"fullname" ]
																	}),
															displayField : "fullname",
															valueField : "linkmanId",
															enableKeyEvent : true,
															listeners : {
																select : function(
																		c, a, b) {
																	ProjectForm
																			.selectLinkman(c.value);
																}
															}
														},
														{
															fieldLabel : "手机",
															name : "project.mobile",
															id : "mobile"
														},
														{
															fieldLabel : "电话",
															name : "project.phone",
															id : "phone"
														},
														{
															fieldLabel : "传真",
															name : "project.fax",
															id : "fax"
														},
														{
															fieldLabel : "其他联系方式",
															xtype : "htmleditor",
															height : 219,
															name : "project.otherContacts",
															id : "otherContacts"
														} ]
											} ]
								});
						if (this.projectId != null
								&& this.projectId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/customer/getProject.do?projectId="
														+ this.projectId,
												waitMsg : "正在载入数据...",
												success : function(e, f) {
													Ext
															.getCmp("userId")
															.setValue(
																	f.result.userId);
													Ext
															.getCmp(
																	"customerName")
															.setValue(
																	f.result.customerName);
													Ext
															.getCmp("salesman")
															.setValue(
																	f.result.salesman);
													Ext
															.getCmp(
																	"customerId")
															.setValue(
																	f.result.customerId);
													var b = f.result.data.projectFiles;
													var d = Ext
															.getCmp("displayProjectAttach");
													var a = Ext
															.getCmp("projectAttachIDs");
													for ( var c = 0; c < b.length; c++) {
														if (a.getValue() != "") {
															a.setValue(a
																	.getValue()
																	+ ",");
														}
														a.setValue(a.getValue()
																+ b[c].fileId);
														Ext.DomHelper
																.append(
																		d.body,
																		'<span><a href="#" onclick="FileAttachDetail.show('
																				+ b[c].fileId
																				+ ')">'
																				+ b[c].fileName
																				+ '</a><img class="img-delete" src="'
																				+ __ctxPath
																				+ '/images/system/delete.gif" onclick="removeProjectAttach(this,'
																				+ b[c].fileId
																				+ ')"/>&nbsp;|&nbsp;</span>');
													}
												},
												failure : function(a, b) {
													Ext.ux.Toast.msg("编辑",
															"载入失败");
												}
											});
						}
						this.buttons = [
								{
									text : "保存",
									iconCls : "btn-save",
									handler : function() {
										var a = Ext.getCmp("ProjectForm");
										if (a.getForm().isValid()) {
											a
													.getForm()
													.submit(
															{
																method : "post",
																waitMsg : "正在提交数据...",
																success : function(
																		b, d) {
																	var c = Ext
																			.getCmp("isContract").value;
																	if (c == 0) {
																		Ext.ux.Toast
																				.msg(
																						"操作信息",
																						"成功保存信息！");
																	} else {
																		if (Ext
																				.getCmp(
																						"projectId")
																				.getValue() == null
																				|| Ext
																						.getCmp(
																								"projectId")
																						.getValue() == "") {
																			Ext.Msg
																					.confirm(
																							"操作信息",
																							"信息保存成功,是否现在录入合同信息？",
																							function(
																									e) {
																								if (e == "yes") {
																									new ContractForm()
																											.show();
																									Ext
																											.getCmp(
																													"projectId")
																											.setValue(
																													d.result.projectId);
																									ContractForm
																											.getProject(d.result.projectId);
																								}
																							});
																		}
																	}
																	Ext
																			.getCmp(
																					"ProjectGrid")
																			.getStore()
																			.reload();
																	Ext
																			.getCmp(
																					"ProjectFormWin")
																			.close();
																},
																failure : function(
																		b, c) {
																	Ext.MessageBox
																			.show({
																				title : "操作信息",
																				msg : c.result.msg,
																				buttons : Ext.MessageBox.OK,
																				icon : "ext-mb-error"
																			});
																}
															});
										}
									}
								}, {
									text : "取消",
									iconCls : "btn-cancel",
									handler : function() {
										Ext.getCmp("ProjectFormWin").close();
									}
								} ];
					}
				});
ProjectForm.checkProjectNo = function(a) {
	if (a != null && a != "" && a != "undefined") {
		Ext.Ajax.request({
			url : __ctxPath + "/customer/checkProject.do",
			params : {
				projectNo : a
			},
			method : "post",
			success : function(c) {
				var b = Ext.util.JSON.decode(c.responseText);
				if (b.pass) {
					Ext.getCmp("CheckMessage").body.update('<img src="'
							+ __ctxPath
							+ '/images/flag/customer/passcheck.png" />可用');
				} else {
					Ext.getCmp("CheckMessage").body.update('<img src="'
							+ __ctxPath
							+ '/images/flag/customer/invalid.png" />不可用');
				}
			},
			failure : function() {
			}
		});
	}
};
ProjectForm.selectLinkman = function(a) {
	Ext.Ajax.request({
		url : __ctxPath + "/customer/getCusLinkman.do",
		method : "post",
		params : {
			linkmanId : a
		},
		success : function(c) {
			var b = Ext.util.JSON.decode(c.responseText).data;
			Ext.getCmp("mobile").setValue(b.mobile);
			Ext.getCmp("phone").setValue(b.phone);
			Ext.getCmp("fax").setValue(b.fax);
			Ext.getCmp("otherContacts").setValue(
					"E-mail:" + b.email + "<br/>MSN:" + b.msn + "<br/>QQ:"
							+ b.qq + "<br/>联系地址:" + b.homeAddress + "<br/>邮编:"
							+ b.homeZip);
		},
		failure : function() {
			Ext.ux.Toast.msg("错误信息", "载入出错!");
		}
	});
};
function uploadProjectAttach(c) {
	var a = Ext.getCmp("projectAttachIDs");
	var d = Ext.getCmp("displayProjectAttach");
	for ( var b = 0; b < c.length; b++) {
		if (a.getValue() != "") {
			a.setValue(a.getValue() + ",");
		}
		a.setValue(a.getValue() + c[b].fileId);
		Ext.DomHelper
				.append(
						d.body,
						'<span><a href="#" onclick="FileAttachDetail.show('
								+ c[b].fileId
								+ ')">'
								+ c[b].filename
								+ '</a><img class="img-delete" src="'
								+ __ctxPath
								+ '/images/system/delete.gif" onclick="removeProjectAttach(this,'
								+ c[b].fileId + ')"/>&nbsp;|&nbsp;</span>');
	}
}
function removeProjectAttach(f, d) {
	var b = Ext.getCmp("projectAttachIDs");
	var e = b.getValue();
	if (e.indexOf(",") < 0) {
		b.setValue("");
	} else {
		e = e.replace("," + d, "").replace(d + ",", "");
		b.setValue(e);
	}
	var c = Ext.get(f.parentNode);
	c.remove();
	var a = Ext.getCmp("projectId").value;
	if (a != null && a != "" && a != "undefined") {
		Ext.Ajax.request({
			url : __ctxPath + "/customer/removeFileProject.do",
			params : {
				projectId : a,
				projectAttachIDs : d
			},
			method : "post",
			success : function() {
				Ext.Ajax.request({
					url : __ctxPath + "/system/multiDelFileAttach.do",
					params : {
						ids : d
					},
					method : "post",
					success : function() {
						Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
					}
				});
			}
		});
	} else {
		Ext.Ajax.request({
			url : __ctxPath + "/system/multiDelFileAttach.do",
			params : {
				ids : d
			},
			method : "post",
			success : function() {
				Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
			}
		});
	}
}