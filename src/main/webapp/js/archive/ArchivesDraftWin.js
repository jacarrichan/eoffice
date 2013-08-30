Ext.ns("ArchivesDraftWin");
ArchivesDraftWin = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.init();
						ArchivesDraftWin.superclass.constructor.call(this, {
							title : "发文修改",
							id : "ArchivesDraftWin",
							iconCls : "menu-archive-draft",
							layout : "fit",
							bodyStyle : "padding:2px 20px 2px 2px;",
							border : false,
							modal : true,
							height : 530,
							width : 800,
							maximizable : true,
							autoScroll : true,
							buttonAlign : "center",
							buttons : [ {
								text : "保存",
								iconCls : "btn-save",
								handler : this.onSend,
								scope : this
							}, {
								text : "关闭",
								iconCls : "btn-del",
								handler : this.closePanel,
								scope : this
							} ],
							items : [ this.formPanel ]
						});
					},
					closePanel : function() {
						this.close();
					},
					onSave : function(e) {
						if (this.formPanel.getForm().isValid()) {
							var a = [];
							for ( var d = 0, c = this.store.getCount(); d < c; d++) {
								a.push(this.store.getAt(d).data);
							}
							var b = this.detailPanel;
							this.formPanel.getForm().submit( {
								method : "POST",
								waitMsg : "正在提交数据...",
								params : {
									docs : Ext.encode(a)
								},
								success : function(f, g) {
									Ext.ux.Toast.msg("操作信息", "成功保存信息！");
									if (b != null && b != "undefined") {
										b.getUpdater().refresh();
									}
									e.close();
								},
								failure : function(f, g) {
									Ext.MessageBox.show( {
										title : "操作信息",
										msg : "信息保存出错，请联系管理员！",
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
								}
							});
						}
					},
					onSend : function() {
						this.onSave(this);
					},
					addArchiveDoc : function() {
						var a = Ext.getCmp("archiveTypeId");
						new ArchTemplateSelector(
								{
									callback : function(c, e) {
										var d = Ext.getCmp("archiveDocGrid")
												.getStore();
										var b = Ext.getCmp("ArchivesDraftWin");
										var f = function(g) {
											b.insertNewDoc(d, g);
										};
										new ArchivesDocForm( {
											fileId : c,
											docPath : e,
											callback : f
										}).show();
									}
								}).show();
					},
					insertNewDoc : function(a, c) {
						var b;
						if (a.recordType) {
							b = new a.recordType();
							b.data = {};
							b.data["docId"] = c.docId;
							b.data["fileId"] = c.fileId;
							b.data["docPath"] = c.docPath;
							b.data["docName"] = c.docName;
							b.data["curVersion"] = c.curVersion ? c.curVersion
									: 1;
							b.data.newRecord = true;
							b.commit();
							a.add(b);
						}
					},
					addNewArchiveDoc : function() {
						var b = this.store;
						var a = this;
						var c = function(d) {
							a.insertNewDoc(b, d);
						};
						new ArchivesDocForm( {
							callback : c
						}).show();
					},
					uploadArchiveDoc : function() {
						var b = this.store;
						var a = this;
						var d = function(g) {
							for ( var e = 0; e < g.length; e++) {
								var f = {
									docId : 0,
									fileId : g[e].fileId,
									docPath : g[e].filepath,
									docName : g[e].filename,
									curVersion : 1
								};
								a.insertNewDoc(b, f);
							}
						};
						var c = App.createUploadDialog( {
							file_cat : "archive",
							callback : d
						});
						c.show();
					},
					deleteArchiveDoc : function() {
						var e = Ext.getCmp("archiveDocGrid");
						var b = e.getSelectionModel().getSelections();
						if (b.length == 0) {
							Ext.Msg.alert("信息", "请选择要查看的文档！");
							return;
						}
						var a = b[0];
						var c = e.getStore();
						var d = a.data.docId;
						Ext.Msg
								.confirm(
										"信息确认",
										"您确认要删除所选记录吗？",
										function(f) {
											if (f == "yes") {
												Ext.Ajax
														.request( {
															url : __ctxPath
																	+ "/archive/multiDelArchivesDoc.do",
															params : {
																ids : d
															},
															method : "POST",
															success : function(
																	g, h) {
																Ext.ux.Toast
																		.msg(
																				"操作信息",
																				"成功删除该文档附件！");
																c.remove(a);
															},
															failure : function(
																	g, h) {
																Ext.ux.Toast
																		.msg(
																				"操作信息",
																				"操作出错，请联系管理员！");
															}
														});
											}
										});
					},
					detailArchivesDoc : function() {
						var a = Ext.getCmp("archiveDocGrid");
						var d = a.getSelectionModel().getSelections();
						if (d.length == 0) {
							Ext.Msg.alert("信息", "请选择要查看的文档！");
							return;
						}
						var e = d[0];
						var i = e.data.docPath;
						var b = e.data.docId;
						var c = null;
						if (e.data.fileAttach) {
							c = e.data.fileAttach.fileId;
						} else {
							c = e.data.fileId;
						}
						var g = a.getStore();
						var f = Ext.getCmp("ArchivesDraftWin");
						var h = function(j) {
							g.remove(e);
							f.insertNewDoc(g, j);
						};
						new ArchivesDocForm( {
							fileId : c,
							docId : b,
							docPath : i,
							callback : h
						}).show();
					},
					init : function() {
						this.store = new Ext.data.JsonStore( {
							url : __ctxPath
									+ "/archive/listArchivesDoc.do?archivesId="
									+ this.archivesId,
							root : "result",
							totalProperty : "totalCounts",
							remoteSort : true,
							fields : [ {
								name : "docId",
								type : "int"
							}, "fileAttach", "creator", "creatorId",
									"menderId", "mender", "docName",
									"docStatus", "curVersion", "docPath",
									"updatetime", "createtime" ]
						});
						this.store.setDefaultSort("docId", "desc");
						if (this.archivesId != null && this.archivesId != ""
								&& this.archivesId != "undefined") {
							this.store.load();
						}
						this.toolbar = new Ext.Toolbar( {
							height : 30,
							items : [ {
								text : "按模板在线添加",
								iconCls : "menu-archive-template",
								handler : this.addArchiveDoc,
								scope : this
							}, "-", {
								text : "在线添加",
								iconCls : "btn-edit-online",
								handler : this.addNewArchiveDoc,
								scope : this
							}, "-", {
								text : "上传文档",
								iconCls : "btn-upload",
								handler : this.uploadArchiveDoc,
								scope : this
							}, "-", {
								text : "删除附件文档",
								iconCls : "btn-del",
								scope : this,
								handler : this.deleteArchiveDoc
							}, "-", {
								text : "查看修改文档",
								iconCls : "menu-archive-issue-manage",
								scope : this,
								handler : this.detailArchivesDoc
							} ]
						});
						var a = new Ext.grid.CheckboxSelectionModel( {
							singleSelect : true
						});
						this.docGridPanel = new Ext.grid.EditorGridPanel(
								{
									title : "公文正文",
									iconCls : "menu-attachment",
									border : true,
									id : "archiveDocGrid",
									autoHeight : true,
									store : this.store,
									tbar : this.toolbar,
									sm : a,
									columns : [
											new Ext.grid.RowNumberer(),
											a,
											{
												dataIndex : "docId",
												hidden : true
											},
											{
												dataIndex : "fileAttach",
												hidden : true,
												renderer : function(b) {
												}
											},
											{
												dataIndex : "docStatus",
												hidden : true
											},
											{
												dataIndex : "menderId",
												hidden : true
											},
											{
												dataIndex : "creatorId",
												hidden : true
											},
											{
												dataIndex : "docName",
												width : 150,
												header : "文档名称"
											},
											{
												dataIndex : "docPath",
												header : "文档路径",
												width : 300
											},
											{
												dataIndex : "curVersion",
												header : "当前版本",
												renderer : function(b) {
													return "第" + b + "版";
												}
											},
											{
												header : "管理",
												width : 100,
												dataIndex : "docId",
												sortable : false,
												renderer : function(e, d, b, g,
														c) {
													var f = "";
													f += '<button title="历史版本" value=" " class="btn-archive-history" onclick="ArchivesDraftWin.attach(' + e + ')">&nbsp;&nbsp;</button>';
													return f;
												}
											} ]
								});
						this.formPanel = new Ext.FormPanel(
								{
									bodyStyle : "padding: 4px 8px 4px 8px",
									layout : "form",
									autoHeight : true,
									url : __ctxPath
											+ "/archive/saveIssueArchives.do",
									reader : new Ext.data.JsonReader( {
										root : "data"
									}, [ {
										name : "archives.archviesId",
										mapping : "archivesId"
									}, {
										name : "archives.typeId",
										mapping : "typeId"
									}, {
										name : "archives.archivesNo",
										mapping : "archivesNo"
									}, {
										name : "archives.privacyLevel",
										mapping : "privacyLevel"
									}, {
										name : "archives.urgentLevel",
										mapping : "urgentLevel"
									}, {
										name : "archives.subject",
										mapping : "subject"
									}, {
										name : "archives.issueDep",
										mapping : "issueDep"
									}, {
										name : "archives.depId",
										mapping : "depId"
									}, {
										name : "archives.keywords",
										mapping : "keywords"
									}, {
										name : "archives.shortContent",
										mapping : "shortContent"
									}, {
										name : "archives.handleOpinion",
										mapping : "handleOpinion"
									}, {
										name : "archives.fileCounts",
										mapping : "fileCounts"
									}, {
										name : "archives.recDepIds",
										mapping : "recDepIds"
									}, {
										name : "archives.recDepNames",
										mapping : "recDepNames"
									}, {
										name : "archives.sources",
										mapping : "sources"
									} ]),
									items : [
											{
												name : "archives.archivesId",
												id : "archivesWin.archivesId",
												xtype : "hidden",
												value : this.archivesId == null ? ""
														: this.archivesId
											},
											{
												layout : "column",
												border : false,
												items : [ {
													columnWidth : 0.5,
													border : false,
													style : "padding:0px 0px 0px 10px;",
													layout : "form",
													items : {
														fieldLabel : "公文类型",
														hiddenName : "archives.typeId",
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
																	listeners : {
																		scope : this,
																		load : function() {
																			var b = this
																					.getCmpByName("archives.typeId");
																			b
																					.setValue(b.hiddenField.value);
																		}
																	},
																	url : __ctxPath
																			+ "/archive/comboArchivesType.do",
																	fields : [
																			"typeId",
																			"typeName" ]
																}),
														displayField : "typeName",
														valueField : "typeId"
													}
												} ]
											},
											{
												xtype : "fieldset",
												title : "发文设置",
												border : true,
												defaults : {
													anchor : "98%,98%"
												},
												items : [
														{
															layout : "form",
															border : false,
															items : {
																fieldLabel : "发文字号",
																name : "archives.archivesNo",
																id : "archivesWin.archivesNo",
																xtype : "textfield",
																allowBlank : false,
																anchor : "100%"
															}
														},
														{
															layout : "form",
															border : false,
															style : "padding:0px 0px 7px 0px;",
															defaults : {
																anchor : "96%,96%"
															},
															items : [ {
																layout : "column",
																border : false,
																items : [
																		{
																			layout : "form",
																			anchor : "99%",
																			style : "padding:0px 0px 0px 0px;",
																			border : false,
																			items : {
																				fieldLabel : "密级",
																				width : 200,
																				name : "archives.privacyLevel",
																				id : "archivesWin.privacyLevel",
																				triggerAction : "all",
																				lazyRender : true,
																				allowBlank : false,
																				emptyText : "选择密级",
																				xtype : "combo",
																				store : [
																						"普通",
																						"秘密",
																						"机密",
																						"绝密" ]
																			}
																		},
																		{
																			layout : "form",
																			border : false,
																			items : {
																				fieldLabel : "紧急程度",
																				width : 200,
																				name : "archives.urgentLevel",
																				id : "archivesWin.urgentLevel",
																				triggerAction : "all",
																				lazyRender : true,
																				allowBlank : false,
																				emptyText : "选择紧急程度",
																				xtype : "combo",
																				store : [
																						"普通",
																						"紧急",
																						"特急",
																						"特提" ]
																			}
																		} ]
															} ]
														},
														{
															fieldLabel : "文件标题",
															name : "archives.subject",
															id : "archivesWin.subject",
															xtype : "textfield",
															allowBlank : false
														},
														{
															xtype : "container",
															layout : "column",
															style : "padding-left:0px;margin-left:0px;",
															height : 30,
															defaults : {
																border : false
															},
															items : [
																	{
																		xtype : "label",
																		text : "发文机关或部门",
																		style : "padding:0px 0px 0px 0px;",
																		width : 105
																	},
																	{
																		name : "archives.issueDep",
																		id : "archivesWin.issueDep",
																		xtype : "textfield",
																		width : "70%",
																		allowBlank : false,
																		readOnly : true
																	},
																	{
																		name : "archives.depId",
																		id : "archivesWin.depId",
																		xtype : "hidden"
																	},
																	{
																		xtype : "button",
																		iconCls : "menu-department",
																		text : "选择部门",
																		handler : function() {
																			DepSelector
																					.getView(
																							function(
																									b,
																									c) {
																								Ext
																										.getCmp(
																												"archivesWin.issueDep")
																										.setValue(
																												c);
																								Ext
																										.getCmp(
																												"archivesWin.depId")
																										.setValue(
																												b);
																							},
																							true)
																					.show();
																		}
																	} ]
														},
														{
															xtype : "container",
															layout : "column",
															style : "padding:0px 0px 8px 0px;margin-left:0px;",
															defaults : {
																border : false
															},
															items : [
																	{
																		xtype : "label",
																		style : "padding:0px 0px 0px 0px;",
																		text : "接收单位或部门",
																		width : 105
																	},
																	{
																		xtype : "textarea",
																		name : "archives.recDepNames",
																		width : "70%",
																		readOnly : true,
																		id : "archivesWin.recDepNames"
																	},
																	{
																		xtype : "hidden",
																		name : "archives.recDepIds",
																		id : "archivesWin.recDepIds"
																	},
																	{
																		xtype : "button",
																		iconCls : "menu-department",
																		text : "选择部门",
																		handler : function() {
																			DepSelector
																					.getView(
																							function(
																									b,
																									c) {
																								Ext
																										.getCmp(
																												"archivesWin.recDepIds")
																										.setValue(
																												b);
																								Ext
																										.getCmp(
																												"archivesWin.recDepNames")
																										.setValue(
																												c);
																							},
																							false)
																					.show();
																		}
																	} ]
														},
														{
															fieldLabel : "主题词",
															name : "archives.keywords",
															id : "archivesWin.keywords",
															xtype : "textfield"
														},
														{
															fieldLabel : "内容简介",
															name : "archives.shortContent",
															id : "archivesWin.shortContent",
															xtype : "textarea"
														},
														{
															fieldLabel : "公文来源",
															name : "archives.sources",
															id : "archivesWin.sources",
															xtype : "textfield"
														} ]
											}, this.docGridPanel ]
								});
						if (this.archivesId != null
								&& this.archivesId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/archive/getIssueArchives.do?archivesId="
														+ this.archivesId,
												waitMsg : "正在载入数据...",
												success : function(b, c) {
												},
												failure : function(b, c) {
												}
											});
						}
					}
				});
ArchivesDraftWin.attach = function(f) {
	var e = Ext.getCmp("archiveDocGrid");
	var c = e.getSelectionModel().getSelections();
	if (c.length == 0) {
		Ext.Msg.alert("信息", "请选择要查看的文档！");
		return;
	}
	var b = c[0];
	var a = Ext.getCmp("ArchivesDraftWin");
	var d = e.getStore();
	var g = function(h) {
		d.remove(b);
		a.insertNewDoc(d, h);
	};
	new ArchivesDocHistoryWin( {
		docId : f,
		callback : g
	}).show();
};