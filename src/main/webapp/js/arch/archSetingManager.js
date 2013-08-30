archSetingManager = Ext
		.extend(
				Ext.Panel,
				{
					rootId : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						archSetingManager.superclass.constructor.call(this, {
							id : "archSetingManager",
							height : 450,
							autoScroll : true,
							layout : "border",
							title : "档案系统设置",
							items : [ this.leftPanel, this.centerPanel ]
						});
					},
					initUIComponents : function() {
						var b = null;
						Ext.Ajax.request( {
							url : __ctxPath + "/system/listGlobalType.do",
							method : "POST",
							async : false,
							success : function(e, f) {
								var g = Ext.decode(e.responseText);
								b = g.result[0].proTypeId;
							},
							failure : function(e, f) {
							},
							params : {
								Q_catKey_S_EQ : "DIC",
								Q_parentId_L_EQ : 0,
								Q_nodeKey_S_EQ : "arch"
							}
						});
						this.rootId = b;
						this.leftPanel = new cyjt.ux.TreePanelEditor(
								{
									region : "west",
									id : "archDicType",
									collapsible : true,
									split : true,
									height : 450,
									width : 160,
									autoScroll : true,
									url : __ctxPath
											+ "/arch/treeArchType.do?catKey=DIC&rootId="
											+ this.rootId,
									onclick : function(g) {
										this.selectedNode = g;
										var h = g.id;
										var f = Ext.getCmp("archDicGrid");
										if (f != null) {
											if (h == 0) {
												f.setTitle("所有设置");
											} else {
												f.setTitle(g.text + "-设置");
											}
											var e = f.getStore();
											e.url = __ctxPath
													+ "/system/listDictionary.do";
											e.baseParams = {
												parentId : h
											};
											e.reload();
										}
									}
								});
						this.store = new Ext.data.JsonStore( {
							url : __ctxPath + "/system/listDictionary.do",
							root : "result",
							totalProperty : "totalCounts",
							remoteSort : true,
							fields : [ {
								name : "dicId",
								type : "int"
							}, "itemName", "itemValue", "descp", "sn",
									"globalType" ]
						});
						this.store.setDefaultSort("dicId", "desc");
						this.store.baseParams = {
							parentId : this.rootId
						};
						this.store.load( {
							params : {
								start : 0,
								limit : 25
							}
						});
						var d = new Ext.grid.CheckboxSelectionModel();
						var a = new Ext.grid.ColumnModel( {
							columns : [ d, new Ext.grid.RowNumberer(), {
								header : "ID",
								dataIndex : "dicId",
								hidden : true
							}, {
								header : "parentId",
								dataIndex : "globalType",
								hidden : true,
								renderer : function(e) {
									if (e) {
										return e.parentId;
									} else {
										return 0;
									}
								}
							}, {
								header : "proTypeId",
								dataIndex : "globalType",
								hidden : true,
								renderer : function(e) {
									if (e) {
										return e.proTypeId;
									}
								}
							}, {
								header : "分类名称",
								dataIndex : "itemName"
							}, {
								header : "值",
								dataIndex : "itemValue",
								editor : new Ext.form.TextField( {
									allowBlank : false
								})
							}, {
								header : "描述",
								dataIndex : "descp",
								editor : new Ext.form.TextField()
							}, {
								header : "序号",
								dataIndex : "sn",
								editor : new Ext.form.TextField()
							} ],
							defaults : {
								sortable : true,
								menuDisabled : false,
								width : 100
							}
						});
						var c = new Ext.Toolbar(
								{
									items : [
											{
												text : "添加设置",
												iconCls : "btn-add",
												scope : this,
												handler : function() {
													var f = this.centerPanel;
													var g = Ext
															.getCmp("archDicType");
													var h = g.selectedNode;
													var e = g.selectedNode.text;
													var i = 0;
													if (h != null) {
														i = h.id;
													}
													if (i == 0) {
														Ext.ux.Toast.msg(
																"操作信息",
																"请从左选择字典分类");
														return;
													}
													new DictionaryForm( {
														parentId : i,
														typeName : e,
														callback : function() {
															f.getStore()
																	.reload();
														}
													}).show();
												}
											},
											"-",
											{
												xtype : "button",
												text : "保存",
												iconCls : "btn-save",
												scope : this,
												handler : function() {
													var h = this.centerPanel;
													var f = h.getStore();
													var j = [];
													for ( var g = 0; g < f
															.getCount(); g += 1) {
														var e = f.getAt(g);
														j.push(e.data);
													}
													Ext.Ajax
															.request( {
																method : "post",
																url : __ctxPath
																		+ "/system/mulSaveDictionary.do",
																params : {
																	data : Ext
																			.encode(j)
																},
																success : function(
																		i) {
																	Ext.ux.Toast
																			.msg(
																					"操作信息",
																					"成功设置");
																	f.reload();
																	h
																			.getView()
																			.refresh();
																},
																failure : function(
																		i) {
																	Ext.ux.Toast
																			.msg(
																					"操作信息",
																					"设置出错，请联系管理员!");
																}
															});
												}
											},
											"-",
											{
												text : "删除",
												iconCls : "btn-del",
												scope : this,
												handler : function() {
													var g = this.centerPanel;
													var e = g
															.getSelectionModel()
															.getSelections();
													if (e.length == 0) {
														Ext.ux.Toast.msg("信息",
																"请选择要删除的记录！");
														return;
													}
													var h = Array();
													for ( var f = 0; f < e.length; f++) {
														h.push(e[f].data.dicId);
													}
													Ext.Msg
															.confirm(
																	"信息确认",
																	"您确认要删除所选记录吗？",
																	function(i) {
																		if (i == "yes") {
																			Ext.Ajax
																					.request( {
																						url : __ctxPath
																								+ "/system/multiDelDictionary.do",
																						params : {
																							ids : h
																						},
																						method : "POST",
																						success : function(
																								j,
																								k) {
																							Ext.ux.Toast
																									.msg(
																											"操作信息",
																											"成功删除该数字字典！");
																							g
																									.getStore()
																									.reload();
																						},
																						failure : function(
																								j,
																								k) {
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
								});
						this.centerPanel = new Ext.grid.EditorGridPanel( {
							region : "center",
							title : "档案管理设置",
							tbar : c,
							clicksToEdit : 1,
							id : "archDicGrid",
							store : this.store,
							sm : d,
							cm : a,
							height : 450,
							viewConfig : {
								forceFit : true,
								autoFill : true
							},
							bbar : new Ext.PagingToolbar( {
								pageSize : 25,
								store : this.store,
								displayInfo : true,
								displayMsg : "当前页记录索引{0}-{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
						});
					}
				});