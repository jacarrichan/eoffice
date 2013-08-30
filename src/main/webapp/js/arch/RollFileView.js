RollFileView = Ext
		.extend(
				Ext.Panel,
				{
					viewConfig : [],
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						RollFileView.superclass.constructor.call(this, {
							id : "RollFileView",
							title : "卷内文件管理",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.leftPanel,
									this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new HT.SearchPanel(
								{
									id : "RollFileSearchPanel",
									layout : "form",
									region : "north",
									colNums : 3,
									items : [
											{
												fieldLabel : "全宗号",
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
													"select" : function(c, a, b) {
														Ext
																.getCmp(
																		"RollFileView.Search.archRoll.rollNo")
																.getStore()
																.load(
																		{
																			params : {
																				"Q_archFond.archFondId_L_EQ" : a.data.archFondId
																			}
																		});
														Ext
																.getCmp(
																		"RollFileView.Search.archRoll.rollNo")
																.reset();
													}
												}
											},
											{
												fieldLabel : "案卷号",
												id : "RollFileView.Search.archRoll.rollNo",
												name : "Q_archRoll.rollNo_S_LK",
												allowBlank : true,
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
												fieldLabel : "文件编号",
												name : "Q_fileNo_S_LK",
												flex : 1,
												xtype : "textfield"
											},
											{
												fieldLabel : "文件题名",
												name : "Q_fileName_S_LK",
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
												itemName : "文件密级"
											},
											{
												fieldLabel : "保管期限",
												name : "Q_timeLimit_S_LK",
												flex : 1,
												editable : true,
												lazyInit : false,
												forceSelection : false,
												xtype : "diccombo",
												itemName : "文件保管期限"
											},
											{
												fieldLabel : "开放形式",
												name : "Q_openStyle_S_LK",
												flex : 1,
												editable : true,
												lazyInit : false,
												forceSelection : false,
												xtype : "diccombo",
												itemName : "文件开放形式"
											},
											{
												fieldLabel : "归档状态",
												hiddenName : "Q_archStatus_SN_EQ",
												flex : 1,
												xtype : "combo",
												mode : "local",
												editable : false,
												triggerAction : "all",
												store : [ [ "", "全部" ],
														[ "0", "未归档" ],
														[ "1", "已归档" ] ]
											},
											{
												fieldLabel : "分类Id",
												id : "RollFileView.proTypeId",
												name : "Q_globalType.proTypeId_L_EQ",
												flex : 1,
												xtype : "hidden"
											},
											{
												fieldLabel : "分类Name",
												id : "RollFileView.typeName",
												name : "Q_globalType.typeName_S_EQ",
												flex : 1,
												xtype : "hidden"
											} ],
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
						this.leftPanel = new Ext.Panel(
								{
									region : "west",
									layout : "fit",
									collapsible : true,
									split : true,
									frame : false,
									width : 200,
									border : true,
									items : [ {
										xtype : "treePanelEditor",
										id : "RollFileGlobalTypeTree",
										split : true,
										rootVisible : false,
										border : false,
										frame : false,
										autoScroll : true,
										scope : this,
										url : __ctxPath
												+ "/system/treeGlobalType.do?catKey=AR_RLF",
										onclick : function(c) {
											var a = c.id;
											var b = c.text;
											if (a == "0") {
												Ext
														.getCmp(
																"RollFileSearchPanel")
														.getForm()
														.findField(
																"RollFileView.proTypeId")
														.setValue("");
												Ext
														.getCmp(
																"RollFileSearchPanel")
														.getForm()
														.findField(
																"RollFileView.typeName")
														.setValue("");
											} else {
												Ext
														.getCmp(
																"RollFileSearchPanel")
														.getForm()
														.findField(
																"RollFileView.proTypeId")
														.setValue(a);
												Ext
														.getCmp(
																"RollFileSearchPanel")
														.getForm()
														.findField(
																"RollFileView.typeName")
														.setValue(b);
											}
											Ext.getCmp("RollFileView").search();
										},
										contextMenuItems : [
												{
													text : "新建分类",
													scope : this,
													iconCls : "btn-add",
													handler : function() {
														var a = Ext
																.getCmp("RollFileGlobalTypeTree");
														var c = a.selectedNode.id;
														var b = new GlobalTypeForm(
																{
																	parentId : c,
																	catKey : "AR_RLF",
																	callback : function() {
																		Ext
																				.getCmp("RollFileGlobalTypeTree").root
																				.reload();
																	}
																});
														b.show();
													}
												},
												{
													text : "修改分类",
													scope : this,
													iconCls : "btn-edit",
													handler : function() {
														var b = Ext
																.getCmp("RollFileGlobalTypeTree");
														var a = b.selectedNode.id;
														var c = new GlobalTypeForm(
																{
																	proTypeId : a,
																	callback : function() {
																		Ext
																				.getCmp("RollFileGlobalTypeTree").root
																				.reload();
																	}
																});
														c.show();
													}
												},
												{
													text : "删除分类",
													scope : this,
													iconCls : "btn-del",
													handler : function() {
														var b = Ext
																.getCmp("RollFileGlobalTypeTree");
														var a = b.selectedNode.id;
														var c = Array();
														c.push(a);
														Ext.Msg
																.confirm(
																		"信息确认",
																		"您确认要删除所选记录吗？",
																		function(
																				d) {
																			if (d == "yes") {
																				Ext.Ajax
																						.request( {
																							url : __ctxPath
																									+ "/system/multiDelGlobalType.do",
																							params : {
																								ids : c
																							},
																							method : "POST",
																							success : function(
																									e,
																									f) {
																								Ext.ux.Toast
																										.msg(
																												"操作信息",
																												"成功删除该产品分类！");
																								Ext
																										.getCmp("RollFileGlobalTypeTree").root
																										.reload();
																								Ext
																										.getCmp(
																												"RollFileGrid")
																										.getStore()
																										.reload();
																							},
																							failure : function(
																									e,
																									f) {
																								Ext.ux.Toast
																										.msg(
																												"操作信息",
																												"操作出错，请联系管理员！");
																							}
																						});
																			}
																		});
													}
												} ]
									} ]
								});
						this.topbar = new Ext.Toolbar( {
							items : [ {
								iconCls : "btn-add",
								text : "添加卷内文件",
								xtype : "button",
								scope : this,
								handler : this.createRs
							}, {
								iconCls : "btn-del",
								text : "删除卷内文件",
								xtype : "button",
								scope : this,
								handler : this.removeSelRs
							} ]
						});
						this.gridPanel = new HT.GridPanel( {
							region : "center",
							tbar : this.topbar,
							rowActions : true,
							id : "RollFileGrid",
							url : __ctxPath + "/arch/listRollFile.do",
							fields : [ {
								name : "rollFileId",
								type : "int"
							}, "afNo", "createTime", "creatorName",
									"creatorId", "archStatus", "proTypeId",
									"typeName", "openStyle", "archRoll",
									"rollNo", "fileName", "fileNo", "fileNo",
									"catNo", "seqNo", "pageNo", "pageNums",
									"secretLevel", "timeLimit", "keyWords",
									"keyWords", "content", "fileTime", "notes",
									"dutyPerson", "globalType" ],
							columns : [ {
								header : "rollFileId",
								dataIndex : "rollFileId",
								hidden : true
							}, {
								header : "全宗号",
								dataIndex : "archRoll",
								renderer : function(a) {
									if (a) {
										return a.archFond.afNo;
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
							}, new Ext.ux.grid.RowActions( {
								header : "管理",
								width : 100,
								actions : [ {
									iconCls : "btn-readdocument",
									qtip : "预览附件",
									style : "margin:0 3px 0 3px"
								}, {
									iconCls : "btn-edit",
									qtip : "编辑",
									style : "margin:0 3px 0 3px"
								} ],
								listeners : {
									scope : this,
									"action" : this.onRowAction
								}
							}) ]
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
							new RollFileForm( {
								rollFileId : d.data.rollFileId
							}).show();
						});
					},
					createRs : function() {
						new RollFileForm( {
							proTypeId : Ext.getCmp("RollFileSearchPanel")
									.getForm().findField(
											"RollFileView.proTypeId")
									.getValue(),
							typeName : Ext.getCmp("RollFileSearchPanel")
									.getForm().findField(
											"RollFileView.typeName").getValue()
						}).show();
					},
					removeRs : function(a) {
						$postDel( {
							url : __ctxPath + "/arch/multiDelRollFile.do",
							ids : a,
							grid : this.gridPanel
						});
					},
					removeSelRs : function() {
						$delGridRs( {
							url : __ctxPath + "/arch/multiDelRollFile.do",
							grid : this.gridPanel,
							idName : "rollFileId"
						});
					},
					editRs : function(a) {
						new RollFileForm( {
							rollFileId : a.data.rollFileId
						}).show();
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
					onRowAction : function(c, a, d, e, b) {
						switch (d) {
						case "btn-readdocument":
							this.viewFile.call(this, a);
							break;
						case "btn-edit":
							this.editRs.call(this, a);
							break;
						default:
							break;
						}
					}
				});