TidyFileView = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						TidyFileView.superclass.constructor.call(this, {
							id : "TidyFileView",
							title : "归档文件管理",
							region : "center",
							layout : "border",
							buttonAlign : "center",
							items : [ this.searchPanel, this.centerPanel,
									this.eastPanel ],
							listeners : {
								afterrender : function(c) {
									var b = c.getInnerWidth();
									c.eastPanel.setWidth(b / 2);
								}
							}
						});
					},
					initUIComponents : function() {
						this.TidyFileViewSearchButton = new Ext.Button( {
							text : "查询",
							id : "TidyFileViewSearchButton",
							scope : this,
							iconCls : "btn-search",
							handler : this.search
						});
						this.searchPanel = new HT.SearchPanel(
								{
									listeners : {
										"afterlayout" : function(f) {
											Ext.getCmp("TidyFileView").search();
										}
									},
									hidden : true,
									forceLayout : true,
									id : "TidyFileSearchPanel",
									layout : "form",
									region : "north",
									colNums : 4,
									items : [
											{
												fieldLabel : "全宗号",
												id : "TidyFileView.Q_afNo_S_EQ",
												hiddenName : "Q_archRoll.archFondId_L_EQ",
												flex : 1,
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
													"select" : function(h, f, g) {
														Ext
																.getCmp(
																		"TidyFileView.Q_rollNo_S_EQ")
																.getStore()
																.load(
																		{
																			params : {
																				"Q_archFond.archFondId_L_EQ" : f.data.archFondId
																			}
																		});
														Ext
																.getCmp(
																		"TidyFileView.Q_rollNo_S_EQ")
																.reset();
													}
												}
											},
											{
												fieldLabel : "案卷号",
												id : "TidyFileView.Q_rollNo_S_EQ",
												name : "Q_archRoll.rollNo_S_EQ",
												flex : 1,
												xtype : "combo",
												mode : "local",
												editable : true,
												lazyInit : false,
												forceSelection : false,
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
												listeners : {}
											},
											{
												fieldLabel : "文件题名",
												name : "Q_fileName_S_EQ",
												flex : 1,
												xtype : "textfield"
											},
											{
												fieldLabel : "目录号",
												name : "Q_catNo_S_EQ",
												flex : 1,
												xtype : "textfield"
											},
											{
												fieldLabel : "密级",
												name : "Q_secretLevel_S_LK",
												flex : 1,
												editable : true,
												lazyInit : false,
												forceSelection : false,
												xtype : "diccombo",
												itemName : "密级"
											},
											{
												fieldLabel : "保管期限",
												name : "Q_timeLimit_S_LK",
												flex : 1,
												editable : true,
												lazyInit : false,
												forceSelection : false,
												xtype : "diccombo",
												itemName : "保管期限"
											},
											{
												fieldLabel : "开放形式",
												name : "Q_openStyle_S_LK",
												flex : 1,
												editable : true,
												lazyInit : false,
												forceSelection : false,
												xtype : "diccombo",
												itemName : "开放形式"
											},
											{
												fieldLabel : "归档状态",
												id : "TidyFileView.Q_archStatus_SN_EQ",
												hiddenName : "Q_archStatus_SN_EQ",
												flex : 1,
												xtype : "combo",
												value : "0",
												mode : "local",
												editable : false,
												triggerAction : "all",
												store : [ [ "", "全部" ],
														[ "0", "未归档" ],
														[ "1", "已归档" ] ]
											},
											{
												fieldLabel : "分类Id",
												id : "TidyFileView.proTypeId",
												name : "Q_globalType.proTypeId_L_EQ",
												flex : 1,
												xtype : "hidden"
											} ],
									buttons : [ this.TidyFileViewSearchButton,
											{
												text : "重置",
												scope : this,
												iconCls : "btn-reset",
												handler : this.reset
											} ]
								});
						var a = new Ext.form.ComboBox(
								{
									store : new Ext.data.SimpleStore( {
										fields : [],
										data : [ [] ]
									}),
									editable : false,
									mode : "local",
									fieldLabel : "文件分类",
									allowBlank : true,
									triggerAction : "all",
									maxHeight : 200,
									width : 100,
									selectOnFocus : false,
									tpl : "<tpl for='.'><div style='height:200px;'><div id='TidyFileViewComBoxWithTreeDiv'></div></div></tpl>",
									selectedClass : ""
								});
						var b = new Ext.tree.TreePanel(
								{
									height : 200,
									width : 100,
									autoScroll : true,
									split : true,
									loader : new Ext.tree.TreeLoader(
											{
												url : __ctxPath
														+ "/system/treeGlobalType.do?catKey=AR_RLF"
											}),
									root : new Ext.tree.AsyncTreeNode( {
										expanded : true
									}),
									rootVisible : false
								});
						b.on("collapsenode", function(f) {
							a.expand();
						});
						b.on("expandnode", function(f) {
							a.expand();
						});
						b.on("click", function(h) {
							b.clickNodes = true;
							var f = h.id;
							var g = h.text;
							if (f == "0") {
								Ext.getCmp("TidyFileSearchPanel").getForm()
										.findField("TidyFileView.proTypeId")
										.setValue("");
								a.setValue("");
								a.id = "";
								a.collapse();
								Ext.getCmp("TidyFileFormWin").store
										.clearFilter();
							} else {
								Ext.getCmp("TidyFileSearchPanel").getForm()
										.findField("TidyFileView.proTypeId")
										.setValue(f);
								a.setValue(g);
								a.id = f;
								a.collapse();
								Ext.getCmp("TidyFileFormWin").store.filter(
										"proTypeId", f, true, false);
							}
							Ext.getCmp("TidyFileView").search();
						});
						a.on("expand", function() {
							b.render("TidyFileViewComBoxWithTreeDiv");
						});
						var c = new Ext.form.ComboBox( {
							mode : "local",
							lazyInit : false,
							forceSelection : true,
							editable : false,
							triggerAction : "all",
							width : 50,
							store : new Ext.data.JsonStore( {
								url : __ctxPath + "/arch/listArchFond.do",
								autoLoad : true,
								autoShow : true,
								root : "result",
								fields : [ "archFondId", "afNo" ],
								listeners : {
									"load" : function(g, f, h) {
									}
								}
							}),
							valueField : "archFondId",
							displayField : "afNo"
						});
						c
								.on(
										"select",
										function(h, f, g) {
											Ext
													.getCmp(
															"TidyFileSearchPanel")
													.getForm()
													.findField(
															"TidyFileView.Q_afNo_S_EQ")
													.setValue(
															f.get("archFondId"));
											Ext
													.getCmp(
															"TidyFileView.Search.rollNo.ComboBox")
													.getStore()
													.load(
															{
																params : {
																	"Q_archFond.archFondId_L_EQ" : f.data.archFondId
																}
															});
											Ext
													.getCmp(
															"TidyFileView.Search.rollNo.ComboBox")
													.reset();
											Ext
													.getCmp(
															"TidyFileSearchPanel")
													.getForm()
													.findField(
															"TidyFileView.Q_rollNo_S_EQ")
													.reset();
										});
						var d = new Ext.form.ComboBox(
								{
									id : "TidyFileView.Search.rollNo.ComboBox",
									width : 50,
									mode : "local",
									lazyInit : false,
									forceSelection : true,
									editable : false,
									triggerAction : "all",
									store : new Ext.data.JsonStore( {
										url : __ctxPath
												+ "/arch/listArchRoll.do",
										autoLoad : true,
										autoShow : true,
										root : "result",
										idProperty : "rollId",
										fields : [ "rollId", "rollNo", "afNo" ]
									}),
									valueField : "rollId",
									displayField : "rollNo",
									listeners : {
										"select" : function(h, f, g) {
											Ext
													.getCmp(
															"TidyFileSearchPanel")
													.getForm()
													.findField(
															"TidyFileView.Q_rollNo_S_EQ")
													.setValue(f.get("rollId"));
										}
									}
								});
						this.topbar = new Ext.Toolbar( {
							items : [
									{
										xtype : "tbtext",
										text : "分类"
									},
									a,
									{
										xtype : "tbtext",
										text : "全宗号"
									},
									c,
									{
										xtype : "tbtext",
										text : "案卷号"
									},
									d,
									{
										text : "查询",
										scope : this,
										iconCls : "btn-search",
										handler : this.search
									},
									{
										text : "高级查询>>",
										scope : this,
										handler : function() {
											if (Ext.getCmp(
													"TidyFileSearchPanel")
													.isVisible()) {
												Ext.getCmp(
														"TidyFileSearchPanel")
														.setVisible(false);
												Ext.getCmp("TidyFileView")
														.doLayout(true, true);
											} else {
												Ext.getCmp(
														"TidyFileSearchPanel")
														.setVisible(true);
												Ext.getCmp("TidyFileView")
														.doLayout(true, true);
											}
										}
									} ]
						});
						this.fileRecord = Ext.data.Record
								.create( [ {
									name : "rollFileId",
									type : "int"
								}, "afNo", "createTime", "creatorName",
										"creatorId", "archStatus", "proTypeId",
										"typeName", "openStyle", "rollNo",
										"fileName", "fileNo", "fileNo",
										"catNo", "seqNo", "pageNo", "pageNums",
										"secretLevel", "timeLimit", "keyWords",
										"keyWords", "content", "fileTime",
										"notes", "dutyPerson", "archRoll",
										"globalType" ]);
						this.memoryProxy = new Ext.data.HttpProxy( {
							url : __ctxPath + "/arch/listRollFile.do"
						});
						this.jsonReader = new Ext.data.JsonReader( {
							root : "result",
							totalProperty : "totalCounts",
							idProperty : "rollFileId"
						}, this.fileRecord);
						this.mystore = new Ext.data.Store( {
							proxy : this.memoryProxy,
							reader : this.jsonReader
						});
						this.rowAtction = new Ext.ux.grid.RowActions( {
							header : "管理",
							width : 55,
							actions : [ {
								iconCls : "btn-readdocument",
								qtip : "预览附件",
								style : "margin:0 3px 0 3px"
							}, {
								iconCls : "btn-showDetail",
								qtip : "查看",
								style : "margin:0 3px 0 3px"
							} ],
							listeners : {
								scope : this,
								"action" : this.onRowAction
							}
						});
						var e = new Ext.grid.CheckboxSelectionModel();
						this.filesGrid = new Ext.grid.GridPanel( {
							frame : false,
							border : false,
							region : "center",
							tbar : this.topbar,
							rowActions : true,
							id : "TidyFileGrid",
							store : this.mystore,
							bbar : new HT.PagingBar( {
								store : this.mystore
							}),
							sm : e,
							plugins : this.rowAtction,
							viewConfig : {
								forceFit : true,
								autoFill : true
							},
							columns : [ e, {
								header : "rollFileId",
								dataIndex : "rollFileId",
								sortable : true,
								hidden : true
							}, {
								header : "全宗号",
								sortable : true,
								width : 55,
								dataIndex : "archRoll",
								renderer : function(f) {
									if (f) {
										return f.archFond.afNo;
									}
								}
							}, {
								header : "案卷号",
								sortable : true,
								width : 55,
								dataIndex : "archRoll",
								renderer : function(f) {
									if (f) {
										return f.rollNo;
									}
								}
							}, {
								header : "文件题名",
								sortable : true,
								dataIndex : "fileName"
							}, {
								header : "归档状态",
								sortable : true,
								dataIndex : "archStatus",
								width : 65,
								renderer : function(f) {
									switch (f) {
									case 0:
										return "未归档";
										break;
									case 1:
										return "已归档";
										break;
									}
								}
							}, this.rowAtction ]
						});
						this.centerPanel = new Ext.Panel( {
							title : "卷内文件详细信息",
							region : "center",
							layout : "fit",
							frame : false,
							border : true,
							items : [ this.filesGrid ],
							listeners : {
								afterlayout : function(f) {
									var g = f.getInnerHeight();
									Ext.getCmp("TidyFileGrid").setHeight(g);
								}
							}
						});
						this.selectPanel = new Ext.Panel( {
							frame : true,
							border : false,
							hideBorders : true,
							width : 35,
							region : "west",
							layout : {
								type : "vbox",
								pack : "center",
								align : "stretch"
							},
							defaults : {
								margins : "0 0 5 0"
							},
							items : [ {
								xtype : "button",
								iconCls : "btn-down",
								scope : this,
								handler : this.up
							}, {
								xtype : "button",
								iconCls : "btn-top",
								scope : this,
								handler : this.down
							} ]
						});
						this.tidyFileForm = new TidyFileForm( {
							frame : true,
							id : "TidyFileFormWin",
							region : "center"
						}).show();
						this.eastPanel = new Ext.Panel( {
							frame : false,
							border : false,
							region : "east",
							layout : "border",
							id : "TidyFileViewEastPanel",
							items : [ this.selectPanel, this.tidyFileForm ]
						});
					},
					up : function() {
						var b = Ext.getCmp("TidyFileGrid");
						var a = b.getSelectionModel().getSelections();
						if (a.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择要移动的记录！");
							return;
						}
						for (i = 0; i < a.length; i++) {
							if (a[i].data.archStatus == "1") {
								a.splice(i, 1);
								i--;
							}
						}
						if (a.length == 0) {
							Ext.ux.Toast.msg("信息", "所选择的文件件已归档！");
							return;
						}
						for (i = 0; i < a.length; i++) {
							this.tidyFileForm.store.add(a[i]);
							b.getStore().remove(a[i]);
						}
						this.tidyFileForm.gridPanel.getBottomToolbar()
								.moveFirst();
					},
					down : function() {
						var d = Ext.getCmp("TidyFileGrid");
						var c = this.tidyFileForm.gridPanel;
						var b = this.tidyFileForm.store;
						var a = c.getSelectionModel().getSelections();
						if (a.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择要移动的记录！");
							return;
						}
						for (i = 0; i < a.length; i++) {
							b.remove(a[i]);
							d.getStore().add(a[i]);
						}
					},
					reset : function() {
						this.searchPanel.getForm().reset();
					},
					search : function() {
						$search( {
							searchPanel : this.searchPanel,
							gridPanel : this.filesGrid
						});
					},
					viewFile : function(a) {
						Ext.Ajax
								.request( {
									url : __ctxPath
											+ "/arch/listRollFileList.do",
									method : "POST",
									async : false,
									success : function(b, d) {
										var e = Ext.decode(b.responseText);
										var c = [];
										for (i = 0; i < e.result.length; i++) {
											c
													.push( {
														fileName : e.result[i].fileAttach.fileName,
														filePath : e.result[i].fileAttach.filePath
													});
										}
										new ViewFileWindow( {
											viewConfig : c,
											startIndex : 0
										}).show();
									},
									failure : function(b, c) {
									},
									params : {
										"Q_rollFile.rollFileId_L_EQ" : a.data.rollFileId,
										dir : "ASC",
										sort : "sn"
									}
								});
					},
					showFile : function(a) {
						new MyBorrowFileViewWindow( {
							rollFileId : a.data.rollFileId,
							archStatus : a.data.archStatus
						}).show();
					},
					onRowAction : function(c, a, d, e, b) {
						switch (d) {
						case "btn-readdocument":
							this.viewFile.call(this, a);
							break;
						case "btn-showDetail":
							this.showFile.call(this, a);
							break;
						default:
							break;
						}
					}
				});