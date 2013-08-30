ArchTemplateForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						if (a == null) {
							a = {};
						}
						Ext.apply(this, a);
						this.initComponents();
						ArchTemplateForm.superclass.constructor.call(this, {
							id : "ArchTemplateFormWin",
							layout : "fit",
							items : this.formPanel,
							modal : true,
							height : 180,
							width : 560,
							title : "公文模板详细信息",
							iconCls : "menu-archive-template",
							buttonAlign : "center",
							buttons : this.buttons
						});
					},
					initComponents : function() {
						this.formPanel = new Ext.FormPanel(
								{
									layout : "form",
									bodyStyle : "padding:10px 10px 10px 10px",
									border : false,
									url : __ctxPath
											+ "/archive/saveArchTemplate.do",
									id : "ArchTemplateForm",
									defaults : {
										anchor : "98%,98%"
									},
									defaultType : "textfield",
									items : [
											{
												name : "archTemplate.templateId",
												id : "templateId",
												xtype : "hidden",
												value : this.templateId == null ? ""
														: this.templateId
											},
											{
												fieldLabel : "所属类型",
												hiddenName : "archTemplate.typeId",
												id : "typeId",
												xtype : "combo",
												allowBlank : false,
												editable : false,
												lazyInit : false,
												allowBlank : false,
												triggerAction : "all",
												store : new Ext.data.SimpleStore(
														{
															autoLoad : true,
															url : __ctxPath
																	+ "/archive/comboArchivesType.do",
															fields : [
																	"typeId",
																	"typeName" ]
														}),
												displayField : "typeName",
												valueField : "typeId"
											},
											{
												fieldLabel : "模板名称",
												name : "archTemplate.tempName",
												id : "tempName",
												allowBlank : false
											},
											{
												xtype : "container",
												layout : "column",
												style : "padding-left:0px;margin-left:0px;",
												defaults : {
													border : false
												},
												items : [
														{
															width : 280,
															height : 36,
															style : "padding-left:0px;",
															layout : "form",
															items : {
																xtype : "textfield",
																fieldLabel : "模板文件",
																name : "archTemplate.tempPath",
																readOnly : true,
																id : "tempPath",
																anchor : "98%,98%"
															}
														},
														{
															xtype : "button",
															text : "上传模板",
															iconCls : "btn-upload",
															handler : function() {
																var a = App
																		.createUploadDialog( {
																			file_cat : "archive",
																			callback : function(
																					c) {
																				for ( var b = 0; b < c.length; b++) {
																					Ext
																							.getCmp(
																									"fileId")
																							.setValue(
																									c[b].fileId);
																					Ext
																							.getCmp(
																									"tempPath")
																							.setValue(
																									c[b].filepath);
																				}
																			}
																		});
																a.show();
															}
														},
														{
															xtype : "button",
															text : "在线编辑",
															iconCls : "btn-edit-online",
															handler : function() {
																var b = Ext
																		.getCmp(
																				"tempPath")
																		.getValue();
																var a = Ext
																		.getCmp(
																				"fileId")
																		.getValue();
																new OfficeTemplateView(
																		a,
																		b,
																		false,
																		function(
																				c,
																				d) {
																			Ext
																					.getCmp(
																							"fileId")
																					.setValue(
																							c);
																			Ext
																					.getCmp(
																							"tempPath")
																					.setValue(
																							d);
																		});
															}
														} ]
											}, {
												xtype : "hidden",
												name : "archTemplate.fileId",
												id : "fileId"
											} ]
								});
						if (this.templateId != null
								&& this.templateId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/archive/getArchTemplate.do?templateId="
														+ this.templateId,
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
									handler : this.save.createCallback(
											this.formPanel, this)
								},
								{
									text : "重置",
									iconCls : "btn-reset",
									handler : this.reset
											.createCallback(this.formPanel)
								}, {
									text : "取消",
									iconCls : "btn-cancel",
									handler : this.cancel.createCallback(this)
								} ];
					},
					reset : function(a) {
						a.getForm().reset();
					},
					cancel : function(a) {
						a.close();
					},
					save : function(a, b) {
						if (a.getForm().isValid()) {
							a.getForm().submit( {
								method : "POST",
								waitMsg : "正在提交数据...",
								success : function(c, e) {
									Ext.ux.Toast.msg("操作信息", "成功保存信息！");
									var d = Ext.getCmp("ArchTemplateGrid");
									if (d != null) {
										d.getStore().reload();
									}
									b.close();
								},
								failure : function(c, d) {
									Ext.MessageBox.show( {
										title : "操作信息",
										msg : "信息保存出错，请联系管理员！",
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
									b.close();
								}
							});
						}
					}
				});