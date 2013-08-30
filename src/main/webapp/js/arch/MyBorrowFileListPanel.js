MyBorrowFileListPanel = Ext
		.extend(
				Ext.Panel,
				{
					viewConfig : [],
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						MyBorrowFileListPanel.superclass.constructor
								.call(
										this,
										{
											region : "center",
											layout : "border",
											buttonAlign : "center",
											items : [ this.searchPanel,
													this.leftPanel,
													this.gridPanel ],
											listeners : {
												"afterlayout" : function(b) {
													b.typePanel
															.on(
																	"click",
																	function(e) {
																		b.typePanel.clickNodes = true;
																		var c = e.id;
																		var d = e.text;
																		if (c == "0") {
																			b.SearchProTypeId
																					.setValue("");
																			b.comboxWithTree
																					.setValue("");
																			b.comboxWithTree.id = "";
																			b.comboxWithTree
																					.collapse();
																		} else {
																			b.SearchProTypeId
																					.setValue(c);
																			b.comboxWithTree
																					.setValue(d);
																			b.comboxWithTree.id = c;
																			b.comboxWithTree
																					.collapse();
																		}
																	});
													b.typePanel
															.on(
																	"collapsenode",
																	function(c) {
																		b.comboxWithTree
																				.expand();
																	});
													b.typePanel
															.on(
																	"expandnode",
																	function(c) {
																		b.comboxWithTree
																				.expand();
																	});
													b.comboxWithTree
															.on(
																	"expand",
																	function() {
																		b.typePanel
																				.render(b.borrowNum
																						+ "MyBorrowFileListPanelComBoxWithTreeDiv");
																	});
												},
												"afterrender" : function(b) {
													b.leftPanel
															.findByType("treepanel")[0]
															.on(
																	"click",
																	function(c) {
																		var d = c.id;
																		if (d == "0") {
																			b.rollNo
																					.setValue("");
																		} else {
																			b.rollNo
																					.setValue(d);
																		}
																		b
																				.search();
																	});
												}
											}
										});
					},
					initUIComponents : function() {
						this.afNo = new Ext.form.ComboBox( {
							hidden : true,
							hiddenName : "Q_archRoll.archFondId_L_EQ",
							flex : 1,
							mode : "local",
							editable : true,
							lazyInit : false,
							forceSelection : false,
							triggerAction : "all",
							store : new Ext.data.JsonStore( {
								url : __ctxPath + "/arch/listArchFond.do",
								autoLoad : true,
								autoShow : true,
								root : "result",
								fields : [ "archFondId", "afNo" ]
							}),
							valueField : "archFondId",
							displayField : "afNo"
						});
						this.rollNo = new Ext.form.ComboBox( {
							hidden : true,
							name : "Q_archRoll.rollNo_S_EQ",
							allowBlank : true,
							flex : 1,
							mode : "local",
							editable : true,
							lazyInit : false,
							forceSelection : false,
							triggerAction : "all",
							store : new Ext.data.JsonStore( {
								url : __ctxPath + "/arch/listArchRoll.do",
								autoLoad : false,
								autoShow : true,
								root : "result",
								idProperty : "rollId",
								fields : [ "rollNo", "rollNo" ]
							}),
							valueField : "rollNo",
							displayField : "rollNo",
							listeners : {}
						});
						this.comboxWithTree = new Ext.form.ComboBox(
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
									tpl : "<tpl for='.'><div style='height:200px;'><div id='"
											+ this.borrowNum
											+ "MyBorrowFileListPanelComBoxWithTreeDiv'></div></div></tpl>",
									selectedClass : ""
								});
						this.typePanel = new Ext.tree.TreePanel(
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
						this.SearchProTypeId = new Ext.form.Hidden( {
							name : "Q_globalType.proTypeId_L_EQ",
							flex : 1
						});
						this.searchPanel = new HT.SearchPanel( {
							method : "POST",
							layout : "form",
							region : "north",
							colNums : 5,
							items : [
									this.comboxWithTree,
									{
										fieldLabel : "文件题名",
										name : "Q_fileName_S_LK",
										flex : 1,
										xtype : "textfield"
									},
									{
										fieldLabel : "文件编号",
										name : "Q_fileNo_S_LK",
										flex : 1,
										xtype : "textfield"
									},
									{
										fieldLabel : "目录号",
										name : "Q_catNo_S_LK",
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
										hiddenName : "Q_archStatus_SN_EQ",
										flex : 1,
										xtype : "combo",
										mode : "local",
										editable : false,
										triggerAction : "all",
										store : [ [ "", "全部" ], [ "0", "未归档" ],
												[ "1", "已归档" ] ]
									}, this.SearchProTypeId, this.afNo,
									this.rollNo ],
							buttons : [ {
								text : "查询",
								scope : this,
								iconCls : "btn-search",
								handler : this.search
							}, {
								text : "重置",
								scope : this,
								iconCls : "btn-reset",
								handler : this.reset
							} ]
						});
						this.leftPanel = new Ext.Panel( {
							region : "west",
							layout : "fit",
							collapsible : true,
							split : true,
							frame : false,
							title : "全宗/案卷",
							width : 200,
							border : true,
							items : [ {
								xtype : "treepanel",
								loader : new Ext.tree.TreeLoader( {
									url : __ctxPath
											+ "/arch/listRollTreeArchFond.do"
								}),
								root : new Ext.tree.AsyncTreeNode( {
									expanded : true
								}),
								rootVisible : false,
								split : true,
								border : false,
								frame : false,
								autoShow : true,
								autoScroll : true,
								scope : this
							} ]
						});
						this.leftPanel.findByType("treepanel")[0].getRootNode()
								.expand();
						this.store = new Ext.data.Store( {
							proxy : new Ext.data.HttpProxy( {
								url : __ctxPath + "/arch/listRollFile.do",
								method : "POST"
							}),
							reader : new Ext.data.JsonReader( {
								root : "result",
								totalProperty : "totalCounts",
								remoteSort : false,
								idProperty : "listId",
								fields : [ {
									name : "rollFileId",
									type : "int"
								}, "afNo", "createTime", "creatorName",
										"creatorId", "archStatus", "proTypeId",
										"typeName", "openStyle", "archRoll",
										"rollNo", "fileName", "fileNo",
										"fileNo", "catNo", "seqNo", "pageNo",
										"pageNums", "secretLevel", "timeLimit",
										"keyWords", "keyWords", "content",
										"fileTime", "notes", "dutyPerson",
										"globalType" ]
							})
						});
						this.rowActions = new Ext.ux.grid.RowActions( {
							header : "管理",
							width : 100,
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
						this.gridPanel = new Ext.grid.GridPanel( {
							region : "center",
							store : this.store,
							plugins : this.rowActions,
							height : 200,
							defaults : {
								anchor : "96%,96%"
							},
							viewConfig : {
								forceFit : true,
								enableRowBody : false,
								showPreview : false
							},
							bbar : new HT.PagingBar( {
								store : this.store
							}),
							columns : [ {
								header : "rollFileId",
								dataIndex : "rollFileId",
								hidden : true
							}, {
								header : "全宗号",
								dataIndex : "archRoll",
								renderer : function(a) {
									if (a) {
										return a.afNo;
									}
								}
							}, {
								header : "案卷号",
								dataIndex : "archRoll",
								renderer : function(a) {
									if (a) {
										return a.rollNo;
									}
								}
							}, {
								header : "所属分类",
								dataIndex : "globalType",
								renderer : function(a) {
									if (a) {
										return a.typeName;
									}
								}
							}, {
								header : "文件题名",
								dataIndex : "fileName"
							}, {
								header : "文件编号",
								dataIndex : "fileNo"
							}, {
								header : "密级",
								dataIndex : "secretLevel"
							}, {
								header : "保管期限",
								dataIndex : "timeLimit"
							}, {
								header : "开放形式",
								dataIndex : "openStyle"
							}, {
								header : "归档状态",
								dataIndex : "archStatus",
								renderer : function(a) {
									switch (a) {
									case 0:
										return "未归档";
										break;
									case 1:
										return "已归档";
										break;
									}
								}
							}, this.rowActions ]
						});
						this.gridPanel
								.addListener("rowdblclick", this.rowClick);
					},
					reset : function() {
						this.searchPanel.getForm().reset();
					},
					search : function() {
						$search( {
							searchPanel : this.searchPanel,
							gridPanel : this.gridPanel
						});
					},
					rowClick : function(b, a, c) {
						b.getSelectionModel().each(function(d) {
							new MyBorrowFileViewWindow( {
								rollFileId : d.data.rollFileId,
								archStatus : d.data.archStatus
							}).show();
						});
					},
					viewFile : function(a) {
						Ext.Ajax
								.request( {
									url : __ctxPath
											+ "/arch/listRollFileList.do",
									method : "POST",
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
							this.showFile(a);
							break;
						default:
							break;
						}
					}
				});