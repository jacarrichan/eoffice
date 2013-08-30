Ext.ns("TidyFileForm");
TidyFileForm = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						TidyFileForm.superclass.constructor.call(this, {
							layout : "form",
							border : true,
							items : [ this.gridPanel ],
							title : "归档文件详细信息",
							frame : false,
							listeners : {
								afterlayout : function(c) {
									var b = c.getInnerHeight();
									c.gridPanel.setHeight(b);
								}
							}
						});
					},
					initUIComponents : function() {
						this.store = new Ext.ux.data.JsonPagingStore( {
							root : "result",
							totalProperty : "totalCounts",
							remoteSort : false,
							idProperty : "rollFileId",
							fields : [ {
								name : "rollFileId",
								type : "int"
							}, "afNo", "createTime", "creatorName",
									"creatorId", "archStatus", "proTypeId",
									"typeName", "openStyle", "rollNo",
									"fileName", "fileNo", "fileNo", "catNo",
									"seqNo", "pageNo", "pageNums",
									"secretLevel", "timeLimit", "keyWords",
									"keyWords", "content", "fileTime", "notes",
									"dutyPerson", "archRoll", "globalType" ]
						});
						this.rowActions = new Ext.ux.grid.RowActions( {
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
							} ]
						});
						var d = new Ext.grid.CheckboxSelectionModel();
						var b = new Ext.grid.ColumnModel( {
							columns : [ d, new Ext.grid.RowNumberer(), {
								header : "rollFileId",
								dataIndex : "rollFileId",
								hidden : true
							}, {
								header : "全宗号",
								sortable : true,
								dataIndex : "archRoll",
								renderer : function(e) {
									if (e) {
										return e.afNo;
									}
								}
							}, {
								header : "案卷号",
								sortable : true,
								dataIndex : "archRoll",
								renderer : function(e) {
									if (e) {
										return e.rollNo;
									}
								}
							}, {
								header : "文件题名",
								sortable : true,
								dataIndex : "fileName"
							}, this.rowActions ],
							defaults : {
								sortable : true,
								menuDisabled : false
							}
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
									allowBlank : false,
									triggerAction : "all",
									maxHeight : 200,
									width : 100,
									selectOnFocus : false,
									tpl : "<tpl for='.'><div style='height:200px;'><div id='TidyFileFormComBoxWithTreeDiv'></div></div></tpl>",
									selectedClass : ""
								});
						var c = new Ext.tree.TreePanel(
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
						c.on("collapsenode", function(e) {
							a.expand();
						});
						c.on("expandnode", function(e) {
							a.expand();
						});
						c.on("click", function(g) {
							c.clickNodes = true;
							var e = g.id;
							var f = g.text;
							if (e == "0") {
								Ext.getCmp("tidyFile.proTypeId").setValue("");
								Ext.getCmp("tidyFile.typeName").setValue("");
								a.setValue("");
								a.id = "";
								a.collapse();
							} else {
								Ext.getCmp("tidyFile.proTypeId").setValue(e);
								Ext.getCmp("tidyFile.typeName").setValue(f);
								a.setValue(f);
								a.id = e;
								a.collapse();
							}
							a.validate();
						});
						a.on("expand", function() {
							c.render("TidyFileFormComBoxWithTreeDiv");
						});
						this.topbar = new Ext.Toolbar(
								{
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
											{
												width : 50,
												name : "rollFile.afNo",
												id : "tidyFile.afNo",
												xtype : "combo",
												mode : "local",
												allowBlank : false,
												lazyInit : false,
												forceSelection : true,
												editable : false,
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
																	"afNo" ],
															listeners : {
																"load" : function(
																		f, e, g) {
																}
															}
														}),
												valueField : "afNo",
												displayField : "afNo",
												listeners : {
													scope : this,
													"select" : function(g, e, f) {
														Ext
																.getCmp(
																		"tidyFile.rollNo")
																.getStore()
																.load(
																		{
																			params : {
																				"Q_archFond.archFondId_L_EQ" : e.data.archFondId
																			}
																		});
														Ext
																.getCmp(
																		"tidyFile.rollNo")
																.reset();
													}
												}
											},
											{
												xtype : "tbtext",
												text : "案卷号"
											},
											{
												width : 50,
												id : "tidyFile.rollNo",
												name : "rollFile.rollNo",
												xtype : "textfield",
												allowBlank : false,
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
													"select" : function(g, e, f) {
														Ext
																.getCmp(
																		"tidyFile.rollId")
																.setValue(
																		e
																				.get("rollId"));
													}
												}
											},
											{
												xtype : "button",
												text : "保存",
												iconCls : "btn-save",
												scope : this,
												handler : this.save
											},
											{
												width : 100,
												id : "tidyFile.proTypeId",
												value : this.proTypeId == null ? ""
														: this.proTypeId,
												name : "rollFile.proTypeId",
												xtype : "hidden"
											}, {
												width : 100,
												id : "tidyFile.rollId",
												name : "rollFile.rollId",
												xtype : "hidden"
											}, {
												id : "tidyFile.typeName",
												name : "rollFile.typeName",
												width : 100,
												xtype : "hidden"
											} ]
								});
						this.gridPanel = new Ext.grid.GridPanel( {
							frame : false,
							border : false,
							region : "center",
							height : 400,
							tbar : this.topbar,
							store : this.store,
							cm : b,
							sm : d,
							autoExpandColumn : "fileName",
							plugins : this.rowActions,
							viewConfig : {
								forceFit : true,
								autoFill : true
							},
							bbar : new HT.PagingBar( {
								store : this.store
							}),
							listeners : {}
						});
						this.rowActions.on("action", this.onRowAction, this);
					},
					save : function() {
						var f = [];
						var b = this.store;
						var d = this.gridPanel;
						var c = b.getTotalCount();
						if (Ext.getCmp("tidyFile.proTypeId").getValue() == ""
								|| Ext.getCmp("tidyFile.proTypeId").getValue() == null
								|| Ext.getCmp("tidyFile.rollId").getValue() == ""
								|| Ext.getCmp("tidyFile.rollId").getValue() == null) {
							return;
						}
						for (i = 0; i < c; i++) {
							var a = b.allData.items[i];
							Ext
									.apply(
											a.data,
											{
												typeName : Ext
														.get("tidyFile.typeName").dom.value,
												afNo : Ext.getCmp(
														"tidyFile.afNo")
														.getValue(),
												rollNo : Ext
														.get("tidyFile.rollNo").dom.value,
												archStatus : 1
											});
							if (a.data.globalType) {
								Ext.apply(a.data.globalType, {
									proTypeId : Ext
											.getCmp("tidyFile.proTypeId")
											.getValue()
								});
							} else {
								var e = {};
								Ext.apply(e, {
									proTypeId : Ext
											.getCmp("tidyFile.proTypeId")
											.getValue()
								});
								Ext.apply(a.data, {
									globalType : e
								});
							}
							if (a.data.archRoll) {
								Ext.apply(a.data.archRoll, {
									rollId : Ext.getCmp("tidyFile.rollId")
											.getValue()
								});
							} else {
								var g = {};
								Ext.apply(g, {
									rollId : Ext.getCmp("tidyFile.rollId")
											.getValue()
								});
								Ext.apply(a.data, {
									archRoll : g
								});
							}
							f.push(a.data);
						}
						if (f.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择要归档的文件！");
							return;
						}
						Ext.Ajax.request( {
							url : __ctxPath + "/arch/tidyRollFile.do",
							method : "POST",
							async : true,
							success : function(h, j) {
								Ext.ux.Toast.msg("操作信息", "保存成功!");
								b.removeAll();
								d.getView().refresh();
								b.commitChanges();
							},
							failure : function(h, j) {
								Ext.MessageBox.show( {
									title : "操作信息",
									msg : "信息保存出错，请联系管理员！",
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.ERROR
								});
							},
							params : {
								params : Ext.encode(f)
							}
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
							this.viewFile(a);
							break;
						case "btn-showDetail":
							this.showFile.call(this, a);
							break;
						default:
							break;
						}
					}
				});