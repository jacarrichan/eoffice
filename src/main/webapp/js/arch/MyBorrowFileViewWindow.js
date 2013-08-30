MyBorrowFileViewWindow = Ext
		.extend(
				Ext.Window,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						MyBorrowFileViewWindow.superclass.constructor.call(
								this, {
									layout : "form",
									items : [ this.formPanel, this.fileGrid ],
									modal : true,
									height : 600,
									width : 800,
									maximizable : true,
									title : "卷内文件详细信息",
									buttonAlign : "center",
									buttons : [ {
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
													readOnly : true,
													width : 100,
													xtype : "displayfield",
													id : "rollFile.fileName",
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
													readOnly : true,
													width : 100,
													xtype : "displayfield",
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
													readOnly : true,
													width : 100,
													name : "rollFile.globalType.typeName",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "全宗号",
													readOnly : true,
													width : 100,
													readOnly : true,
													xtype : "displayfield",
													name : "rollFile.archRoll.archFond.afNo",
													maxLength : 64
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "案卷号",
													readOnly : true,
													width : 100,
													name : "rollFile.archRoll.rollNo",
													allowBlank : true,
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "目录号",
													readOnly : true,
													width : 100,
													xtype : "displayfield",
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
													readOnly : true,
													width : 100,
													name : "rollFile.seqNo",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "页号",
													readOnly : true,
													width : 100,
													name : "rollFile.pageNo",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "页数",
													readOnly : true,
													width : 100,
													name : "rollFile.pageNums",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "密级",
													readOnly : true,
													width : 100,
													name : "rollFile.secretLevel",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "保管期限",
													readOnly : true,
													width : 100,
													name : "rollFile.timeLimit",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "开放形式",
													readOnly : true,
													width : 100,
													name : "rollFile.openStyle",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "主题词",
													readOnly : true,
													width : 604,
													height : 40,
													name : "rollFile.keyWords",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "附注",
													readOnly : true,
													width : 604,
													height : 40,
													name : "rollFile.notes",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "内容",
													readOnly : true,
													width : 604,
													name : "rollFile.content",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "文件时间",
													readOnly : true,
													width : 100,
													name : "rollFile.fileTime",
													xtype : "displayfield",
													format : "Y-m-d"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "责任者",
													readOnly : true,
													width : 100,
													xtype : "displayfield",
													name : "rollFile.dutyPerson"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "归档状态",
													readOnly : true,
													width : 100,
													id : "MyBorrowFileWiewWindow.rollFile.archStatus",
													name : "rollFile.archStatus",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "录入人",
													readOnly : true,
													width : 100,
													xtype : "displayfield",
													value : curUserInfo.fullname,
													name : "rollFile.creatorName"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "录入时间",
													readOnly : true,
													width : 100,
													name : "rollFile.createTime",
													xtype : "displayfield"
												}
											} ]
								});
						this.fileGrid = new MyBorrowFileSlaveListGrid( {
							height : 257,
							rollFileId : this.rollFileId
						});
						this.fileGrid.getStore().baseParams = {
							"Q_rollFile.rollFileId_L_EQ" : this.rollFileId
						};
						this.load();
					},
					load : function() {
						if (this.rollFileId != null
								&& this.rollFileId != "undefined") {
							Ext
									.override(
											Ext.Panel,
											{
												loadData : function(conf) {
													var ct = this;
													var setByName = function(
															container, data) {
														var items = container.items;
														if (items != null) {
															for ( var i = 0; i < items
																	.getCount(); i++) {
																var comp = items
																		.get(i);
																if (comp.items) {
																	setByName(
																			comp,
																			data);
																	continue;
																}
																var xtype = comp
																		.getXType();
																try {
																	if (xtype == "textfield"
																			|| xtype == "textarea"
																			|| xtype == "radio"
																			|| xtype == "checkbox"
																			|| xtype == "datefield"
																			|| xtype == "combo"
																			|| xtype == "hidden"
																			|| xtype == "datetimefield"
																			|| xtype == "htmleditor"
																			|| xtype == "numberfield"
																			|| xtype == "diccombo"
																			|| xtype == "displayfield"
																			|| xtype == "fckeditor") {
																		var name = comp
																				.getName();
																		if (name) {
																			if (conf.preName) {
																				if (name
																						.indexOf(conf.preName) != -1) {
																					name = name
																							.substring(conf.preName.length + 1);
																				}
																			}
																			var val = eval("data."
																					+ name);
																			if (val != null
																					&& val != undefined) {
																				if (comp
																						.getId() == "MyBorrowFileWiewWindow.rollFile.archStatus") {
																					if (val == 1) {
																						val = "已归档";
																					} else {
																						val = "未归档";
																					}
																				}
																				comp
																						.setValue(val);
																			}
																		}
																	}
																} catch (e) {
																}
															}
														}
													};
													if (!ct.loadMask) {
														ct.loadMask = new Ext.LoadMask(
																Ext.getBody());
														ct.loadMask.show();
													}
													Ext.Ajax
															.request( {
																method : "POST",
																url : conf.url,
																scope : this,
																success : function(
																		response,
																		options) {
																	var json = Ext.util.JSON
																			.decode(response.responseText);
																	var data = null;
																	if (conf.root) {
																		data = eval("json."
																				+ conf.root);
																	} else {
																		data = json;
																	}
																	setByName(
																			ct,
																			data);
																	if (ct.loadMask) {
																		ct.loadMask
																				.hide();
																		ct.loadMask = null;
																	}
																	if (conf.success) {
																		conf.success
																				.call(
																						ct,
																						response,
																						options);
																	}
																},
																failure : function(
																		response,
																		options) {
																	if (ct.loadMask) {
																		ct.loadMask
																				.hide();
																		ct.loadMask = null;
																	}
																	if (conf.failure) {
																		conf.failure
																				.call(
																						ct,
																						response,
																						options);
																	}
																}
															});
												}
											});
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
					cancel : function() {
						this.close();
					}
				});