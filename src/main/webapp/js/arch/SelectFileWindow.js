SelectFileWindow = Ext
		.extend(
				Ext.Window,
				{
					viewConfig : [],
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						SelectFileWindow.superclass.constructor.call(this, {
							id : "SelectFileWindow",
							title : "文件",
							region : "center",
							layout : "border",
							modal : true,
							width : 800,
							height : 600,
							buttonAlign : "center",
							buttons : [ {
								text : "选择",
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
							} ],
							items : [ this.searchPanel, this.leftPanel,
									this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new HT.SearchPanel(
								{
									id : "SelectFileWindowSearchPanel",
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
													"select" : function(d, b, c) {
														Ext
																.getCmp(
																		"SelectFileWindow.Q_rollNo_S_EQ")
																.getStore()
																.load(
																		{
																			params : {
																				"Q_archFond.archFondId_L_EQ" : b.data.archFondId
																			}
																		});
														Ext
																.getCmp(
																		"SelectFileWindow.Q_rollNo_S_EQ")
																.reset();
													}
												}
											},
											{
												fieldLabel : "案卷号",
												id : "SelectFileWindow.Q_rollNo_S_EQ",
												name : "Q_rollNo_S_EQ",
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
												name : "Q_fileNo_S_EQ",
												flex : 1,
												xtype : "textfield"
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
												id : "SelectFileWindow.proTypeId",
												name : "Q_globalType.proTypeId_L_EQ",
												flex : 1,
												xtype : "hidden"
											},
											{
												fieldLabel : "分类Name",
												id : "SelectFileWindow.typeName",
												name : "Q_typeName_S_EQ",
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
										id : "SelectFileWindowGlobalTypeTree",
										split : true,
										rootVisible : false,
										border : false,
										frame : false,
										autoScroll : true,
										scope : this,
										url : __ctxPath
												+ "/system/treeGlobalType.do?catKey=AR_RLF",
										onclick : function(d) {
											var b = d.id;
											var c = d.text;
											if (b == "0") {
												Ext
														.getCmp(
																"SelectFileWindowSearchPanel")
														.getForm()
														.findField(
																"SelectFileWindow.proTypeId")
														.setValue("");
												Ext
														.getCmp(
																"SelectFileWindowSearchPanel")
														.getForm()
														.findField(
																"SelectFileWindow.typeName")
														.setValue("");
											} else {
												Ext
														.getCmp(
																"SelectFileWindowSearchPanel")
														.getForm()
														.findField(
																"SelectFileWindow.proTypeId")
														.setValue(b);
												Ext
														.getCmp(
																"SelectFileWindowSearchPanel")
														.getForm()
														.findField(
																"SelectFileWindow.typeName")
														.setValue(c);
											}
											Ext.getCmp("SelectFileWindow")
													.search();
										}
									} ]
								});
						this.store = new Ext.data.Store( {
							proxy : new Ext.data.HttpProxy( {
								url : __ctxPath + "/arch/listRollFile.do",
								method : "GET"
							}),
							root : "result",
							reader : new Ext.data.JsonReader( {
								root : "result",
								totalProperty : "totalCounts",
								remoteSort : false,
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
										"fileTime", "notes", "dutyPerson" ]
							})
						});
						this.store.load();
						var a = new Ext.grid.CheckboxSelectionModel();
						this.gridPanel = new Ext.grid.GridPanel( {
							region : "center",
							height : 200,
							id : "SelectRollFileWindowGrid",
							defaults : {
								anchor : "96%"
							},
							viewConfig : {
								forceFit : true,
								autoFill : true
							},
							sm : a,
							store : this.store,
							bbar : new HT.PagingBar( {
								store : this.store
							}),
							columns : [ a, {
								header : "rollFileId",
								dataIndex : "rollFileId",
								hidden : true
							}, {
								header : "全宗号",
								dataIndex : "archRoll",
								renderer : function(b) {
									if (b) {
										return b.archFond.afNo;
									}
								}
							}, {
								header : "案卷号",
								dataIndex : "archRoll",
								renderer : function(b) {
									if (b) {
										return b.rollNo;
									}
								}
							}, {
								header : "文件编号",
								dataIndex : "fileNo"
							}, {
								header : "文件题名",
								dataIndex : "fileName"
							} ]
						});
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
					cancel : function() {
						this.close();
					},
					save : function() {
						var a = this.gridPanel.getSelectionModel()
								.getSelections();
						if (a.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择记录！");
							return;
						}
						if (this.callBack != null) {
							this.close();
							this.callBack.call(this, a);
						}
					}
				});