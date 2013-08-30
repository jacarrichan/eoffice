Ext.ns("AddConfSummaryView");
AddConfSummaryView = Ext
		.extend(
				Ext.Panel,
				{
					topbar : null,
					form : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponent();
						AddConfSummaryView.superclass.constructor.call(this, {
							id : "AddConfSummaryViewPanel",
							title : "新建会议纪要",
							iconCls : "menu-confSummary_add",
							header : true,
							region : "center",
							layout : "border",
							bodyStyle : "padding:5px 5px 5px 5px",
							items : [ this.form ]
						});
					},
					initUIComponent : function() {
						this.topbar = new Ext.Toolbar( {
							id : "AddConfSummaryViewTool",
							heigth : 30,
							defaultType : "button",
							items : [ {
								iconCls : "btn-mail_send",
								text : "发送",
								handler : this.send
							}, {
								iconCls : "btn-save",
								text : "保存",
								handler : this.save
							} ]
						});
						this.form = new Ext.form.FormPanel(
								{
									id : "AddConfSummaryViewForm",
									region : "center",
									layout : "form",
									tbar : this.topbar,
									frame : false,
									border : true,
									bodyStyle : "padding-left:50px;",
									defaultType : "texfield",
									items : [ {
										xtype : "fieldset",
										title : "新增会议纪要信息",
										width : 800,
										layout : "form",
										buttonAlign : "center",
										defaults : {
											margins : {
												top : 5,
												left : 5,
												bottom : 5,
												right : 5
											}
										},
										items : [
												{
													xtype : "hidden",
													name : "confSummary.confId.confId"
												},
												{
													xtype : "container",
													layout : "hbox",
													items : [
															{
																xtype : "label",
																text : "会议标题："
															},
															{
																style : "margin-left:45px; margin-right:10px;margin-bottom:5px;",
																xtype : "textfield",
																name : "confTopic",
																width : 350,
																readOnly : true,
																allowBlank : false,
																blankText : "请选择会议标题！",
																maxLength : 128,
																maxLengthText : "会议标题不能超过128个字符长度！"
															},
															{
																xtype : "button",
																text : "请选择",
																iconCls : "btn-user-sel",
																handler : function() {
																	ConferenceSelector
																			.getView(
																					function(
																							c,
																							a) {
																						var b = Ext
																								.getCmp("AddConfSummaryViewForm");
																						b
																								.getCmpByName(
																										"confSummary.confId.confId")
																								.setValue(
																										c);
																						b
																								.getCmpByName(
																										"confTopic")
																								.setValue(
																										a);
																					},
																					true)
																			.show();
																}
															} ]
												},
												{
													xtype : "htmleditor",
													fieldLabel : "纪要内容",
													id : "sumContent",
													name : "confSummary.sumContent",
													width : 600,
													heigth : 400,
													allowBlank : false,
													blankText : "对不起，请输入会议纪要内容！",
													maxLength : 4000,
													maxLengthtext : "会议纪要内容不能超过4000个字符长度！"
												},
												{
													xtype : "hidden",
													name : "fileIds"
												},
												{
													xtype : "compositefield",
													fieldLabel : "上传附件",
													layout : "form",
													items : [
															{
																xtype : "textfield",
																fieldLabel : "地址",
																name : "filePath",
																width : 300,
																readOnly : true
															},
															{
																xtype : "button",
																iconCls : "btn-upload",
																text : "请选择",
																handler : this.upLoadFile
															},
															{
																xtype : "button",
																iconCls : "btn-cancel",
																text : "取消",
																handler : this.delLoadFile
															} ]
												} ],
										buttons : [ {
											text : "发送",
											iconCls : "btn-mail_send",
											handler : this.send
										}, {
											text : "保存",
											iconCls : "btn-save",
											handler : this.save
										} ]
									} ]
								});
					},
					save : function() {
						var a = Ext.getCmp("AddConfSummaryViewForm");
						if (a.getForm().isValid()) {
							a.getForm().submit(
									{
										url : __ctxPath
												+ "/admin/saveConfSummary.do",
										method : "post",
										success : function(b, c) {
											Ext.ux.Toast.msg("操作提示",
													"恭喜您，保存会议纪要成功！");
											a.getForm().reset();
											App.clickTopTab("ConfSummaryView");
											Ext.getCmp("ConfSummaryGrid")
													.getStore().reload();
										},
										failure : function(b, c) {
											Ext.ux.Toast.msg("操作提示",
													c.result.msg);
											Ext.getCmp("sumContent")
													.focus(true);
										}
									});
						}
					},
					send : function() {
						var a = Ext.getCmp("AddConfSummaryViewForm");
						if (a.getForm().isValid()) {
							a.getForm().submit(
									{
										url : __ctxPath
												+ "/admin/sendConfSummary.do",
										method : "post",
										success : function() {
											Ext.ux.Toast.msg("操作提示",
													"恭喜您，会议纪要发送成功！");
											a.getForm().reset();
											App.clickTopTab("ConfSummaryView");
											Ext.getCmp("ConfSummaryGrid")
													.getStore().reload();
										},
										failure : function(b, c) {
											Ext.ux.Toast.msg("操作提示",
													c.result.msg);
											Ext.getCmp("sumContent")
													.focus(true);
										}
									});
						}
					},
					upLoadFile : function() {
						var a = App.createUploadDialog( {
							url : __ctxPath + "/file-upload",
							file_cat : "admin/confSummary",
							callback : function(b) {
								var f = "";
								var d = "";
								for ( var c = 0; c < b.length; c++) {
									f += b[c].fileId + ",";
									d += b[c].filepath + ",";
								}
								f = f.substring(0, f.length - 1);
								var e = Ext.getCmp("AddConfSummaryViewForm");
								e.getCmpByName("fileIds").setValue(f);
								e.getCmpByName("filePath").setValue(
										d.substring(0, d.length - 1));
							}
						});
						a.show("querybtn");
					},
					delLoadFile : function() {
						var b = Ext.getCmp("AddConfSummaryViewForm");
						var a = b.getCmpByName("fileIds").value;
						if (a != null && a != "" && a != "undefined") {
							Ext.Msg
									.confirm(
											"确认信息",
											"您真的要删除上传文件吗？",
											function(c) {
												if (c == "yes") {
													Ext.Ajax
															.request( {
																url : __ctxPath
																		+ "/system/multiDelFileAttach.do",
																method : "post",
																params : {
																	ids : a
																},
																success : function() {
																	Ext.ux.Toast
																			.msg(
																					"操作提示",
																					"上传文件删除成功！");
																	b
																			.getCmpByName(
																					"fileIds")
																			.setValue(
																					"");
																	b
																			.getCmpByName(
																					"filePath")
																			.setValue(
																					"");
																},
																failure : function() {
																	Ext.ux.Toast
																			.msg(
																					"操作提示",
																					"对不起，您上传文件删除失败！");
																}
															});
												}
											});
						} else {
							Ext.ux.Toast.msg("操作提示", "对不起，你还没有上传文件！");
						}
					}
				});