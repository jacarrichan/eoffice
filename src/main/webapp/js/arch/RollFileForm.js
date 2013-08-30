RollFileForm = Ext
		.extend(
				Ext.Window,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						RollFileForm.superclass.constructor.call(this, {
							id : "RollFileFormWin",
							layout : "form",
							items : [ this.formPanel, this.fileGrid ],
							modal : true,
							height : 600,
							width : 800,
							maximizable : true,
							title : "卷内文件详细信息",
							buttonAlign : "center",
							buttons : [ {
								text : "保存",
								iconCls : "btn-save",
								scope : this,
								handler : this.save
							}, {
								text : "重置",
								iconCls : "btn-reset",
								scope : this,
								handler : this.reset
							}, {
								text : "关闭",
								iconCls : "btn-cancel",
								scope : this,
								handler : this.cancel
							} ]
						});
					},
					initUIComponents : function() {
						this.formPanel = new Ext.FormPanel(
								{
									id : "RollFileForm",
									layout : "column",
									bodyStyle : "padding:10px",
									border : false,
									autoScroll : true,
									defaults : {
										border : false,
										anchor : "98%,98%"
									},
									items : [
											{
												name : "rollFile.rollFileId",
												xtype : "hidden",
												value : this.rollFileId == null ? ""
														: this.rollFileId
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "文件题名",
													width : 100,
													xtype : "textfield",
													name : "rollFile.fileName",
													allowBlank : false,
													maxLength : 128
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "文件编号",
													width : 100,
													xtype : "textfield",
													id : "RollFileForm.rollFile.fileNo",
													name : "rollFile.fileNo",
													allowBlank : false,
													maxLength : 64
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "文件分类",
													width : 100,
													name : "rollFile.globalType.typeName",
													xtype : "combo",
													mode : "local",
													editable : false,
													triggerAction : "all",
													value : this.typeName == null ? ""
															: this.typeName,
													store : new Ext.data.JsonStore(
															{
																url : __ctxPath
																		+ "/system/subGlobalType.do",
																baseParams : {
																	parentId : 0,
																	catKey : "AR_RLF"
																},
																autoLoad : true,
																autoShow : true,
																root : "result",
																idProperty : "proTypeId",
																fields : [
																		"proTypeId",
																		"typeName" ]
															}),
													valueField : "proTypeId",
													displayField : "typeName",
													listeners : {
														scope : this,
														"select" : function(c,
																a, b) {
															Ext
																	.getCmp(
																			"RollFileForm")
																	.getForm()
																	.findField(
																			"rollFile.proTypeId")
																	.setValue(
																			a
																					.get("proTypeId"));
														}
													}
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "全宗号",
													width : 100,
													name : "rollFile.archRoll.archFond.afNo",
													id : "rollFile.afNo",
													xtype : "combo",
													mode : "local",
													editable : true,
													lazyInit : false,
													forceSelection : false,
													triggerAction : "all",
													store : new Ext.data.JsonStore(
															{
																url : __ctxPath
																		+ "/arch/listArchFond.do",
																autoLoad : true,
																autoShow : true,
																root : "result",
																fields : [
																		"archFondId",
																		"afNo" ]
															}),
													valueField : "archFondId",
													displayField : "afNo",
													listeners : {
														scope : this,
														"select" : function(c,
																a, b) {
															Ext
																	.getCmp(
																			"rollFile.archRoll.rollNo")
																	.getStore()
																	.load(
																			{
																				params : {
																					"Q_archFond.archFondId_L_EQ" : a.data.archFondId
																				}
																			});
															Ext
																	.getCmp(
																			"rollFile.archRoll.rollNo")
																	.reset();
														}
													}
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "案卷号",
													width : 100,
													id : "rollFile.archRoll.rollNo",
													name : "rollFile.archRoll.rollNo",
													allowBlank : true,
													xtype : "combo",
													mode : "local",
													editable : false,
													triggerAction : "all",
													store : new Ext.data.JsonStore(
															{
																url : __ctxPath
																		+ "/arch/listArchRoll.do",
																autoLoad : true,
																autoShow : true,
																root : "result",
																idProperty : "rollId",
																fields : [
																		"rollId",
																		"rollNo",
																		"afNo" ]
															}),
													valueField : "rollId",
													displayField : "rollNo",
													listeners : {
														scope : this,
														"select" : function(c,
																a, b) {
															Ext
																	.getCmp(
																			"RollFileForm")
																	.getForm()
																	.findField(
																			"rollFile.rollId")
																	.setValue(
																			a
																					.get("rollId"));
														}
													}
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "目录号",
													width : 100,
													xtype : "textfield",
													name : "rollFile.catNo",
													maxLength : 64
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "顺序号",
													width : 100,
													name : "rollFile.seqNo",
													xtype : "numberfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "页号",
													width : 100,
													name : "rollFile.pageNo",
													xtype : "numberfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "页数",
													width : 100,
													name : "rollFile.pageNums",
													xtype : "numberfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "密级",
													width : 100,
													name : "rollFile.secretLevel",
													editable : true,
													lazyInit : false,
													forceSelection : false,
													xtype : "diccombo",
													itemName : "文件密级"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "保管期限",
													width : 100,
													name : "rollFile.timeLimit",
													editable : true,
													lazyInit : false,
													forceSelection : false,
													xtype : "diccombo",
													itemName : "文件保管期限"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "开放形式",
													width : 100,
													name : "rollFile.openStyle",
													editable : true,
													lazyInit : false,
													forceSelection : false,
													xtype : "diccombo",
													itemName : "文件开放形式"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "主题词",
													width : 604,
													height : 40,
													name : "rollFile.keyWords",
													xtype : "textarea",
													maxLength : 512
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "附注",
													width : 604,
													height : 40,
													name : "rollFile.notes",
													xtype : "textarea",
													maxLength : 4000
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "内容",
													width : 604,
													name : "rollFile.content",
													xtype : "textarea",
													maxLength : 65535
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "文件时间",
													width : 100,
													name : "rollFile.fileTime",
													xtype : "datefield",
													format : "Y-m-d",
													value : new Date()
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "责任者",
													width : 100,
													xtype : "textfield",
													name : "rollFile.dutyPerson",
													maxLength : 32
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "归档状态",
													width : 100,
													hiddenName : "rollFile.archStatus",
													xtype : "combo",
													mode : "local",
													editable : false,
													value : "0",
													triggerAction : "all",
													store : [ [ "0", "未归档" ],
															[ "1", "已归档" ] ]
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "录入人",
													width : 100,
													xtype : "textfield",
													readOnly : true,
													value : curUserInfo.fullname,
													name : "rollFile.creatorName",
													maxLength : 128
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "录入时间",
													width : 100,
													name : "rollFile.createTime",
													readOnly : true,
													xtype : "datefield",
													format : "Y-m-d",
													value : new Date()
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "文件分类ID",
													width : 100,
													id : "rollFile.proTypeId",
													value : this.proTypeId == null ? ""
															: this.proTypeId,
													name : "rollFile.proTypeId",
													xtype : "hidden"
												}
											}, {
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "案卷ID",
													width : 100,
													name : "rollFile.rollId",
													xtype : "hidden"
												}
											} ]
								});
						this.fileGrid = new RollFileListView();
						this.fileGrid
								.getStore()
								.on(
										"beforeload",
										function(a) {
											if (this.rollFileId) {
												a.baseParams = {
													"Q_rollFile.rollFileId_L_EQ" : this.rollFileId
												};
											} else {
												return false;
											}
										}, this);
						this.load();
					},
					load : function() {
						if (this.rollFileId != null
								&& this.rollFileId != "undefined") {
							this.formPanel.loadData( {
								url : __ctxPath
										+ "/arch/getRollFile.do?rollFileId="
										+ this.rollFileId,
								root : "data",
								preName : "rollFile"
							});
							this.fileGrid.getStore().load();
						}
					},
					reset : function() {
						this.formPanel.getForm().reset();
					},
					cancel : function() {
						this.close();
					},
					save : function(f) {
						if (this.rollFileId == null
								|| this.rollFileId == "undefined") {
							var e = true;
							Ext.Ajax
									.request( {
										url : __ctxPath
												+ "/arch/listRollFile.do",
										method : "POST",
										async : false,
										success : function(h, j) {
											var k = Ext.decode(h.responseText);
											if (k.result.length > 0) {
												e = false;
											}
										},
										failure : function(h, j) {
										},
										params : {
											Q_fileNo_S_EQ : this.formPanel
													.getForm()
													.findField(
															"RollFileForm.rollFile.fileNo")
													.getValue()
										}
									});
							if (!e) {
								Ext.ux.Toast.msg("操作信息", "文件编号重覆，不能保存！");
								return;
							}
						}
						if (this.formPanel.getForm().isValid()) {
							var g = [];
							var b = this.fileGrid.getStore();
							var d = b.getCount();
							for (i = 0; i < d; i++) {
								var a = b.getAt(i);
								var c = a.data["fileAttach"];
								if (c.createtime) {
									Ext.apply(c, {
										createtime : new Date(c.createtime)
												.format("Y-m-d")
									});
								}
								g.push(a.data);
							}
							this.formPanel
									.getForm()
									.submit(
											{
												clientValidation : true,
												url : __ctxPath
														+ "/arch/saveRollFile.do",
												params : {
													params : Ext.encode(g)
												},
												success : function(h, j) {
													Ext
															.getCmp("RollFileFormWin").rollFileId = j.result.rollFileId;
													Ext.getCmp(
															"RollFileFormWin")
															.load();
													Ext.ux.Toast.msg("操作信息",
															"卷内文件信息保存成功！");
													Ext.getCmp("RollFileGrid")
															.getStore()
															.reload();
													if (f != "sn") {
														Ext
																.getCmp(
																		"RollFileFormWin")
																.close();
													}
												},
												failure : function(h, j) {
													Ext.MessageBox
															.show( {
																title : "操作信息",
																msg : "信息保存出错，请联系管理员！",
																buttons : Ext.MessageBox.OK,
																icon : Ext.MessageBox.ERROR
															});
												}
											}, this);
						}
					}
				});