ArchRollView = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						ArchRollView.superclass.constructor.call(this, {
							id : "ArchRollView",
							title : "案卷管理",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.leftPanel,
									this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new HT.SearchPanel( {
							layout : "form",
							region : "north",
							id : "ArchRollSearchPanel",
							colNums : 4,
							items : [
									{
										fieldLabel : "全宗号",
										name : "Q_archFond.afNo_S_LK",
										flex : 1,
										xtype : "combo",
										mode : "local",
										editable : false,
										triggerAction : "all",
										store : new Ext.data.JsonStore( {
											url : __ctxPath
													+ "/arch/listArchFond.do",
											autoLoad : true,
											autoShow : true,
											root : "result",
											fields : [ "afNo", "afNo" ]
										}),
										valueField : "afNo",
										displayField : "afNo"
									},
									{
										fieldLabel : "案卷号",
										name : "Q_rollNo_S_LK",
										flex : 1,
										xtype : "textfield"
									},
									{
										fieldLabel : "案卷名称",
										name : "Q_rolllName_S_LK",
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
										fieldLabel : "保管期限",
										name : "Q_timeLimit_S_LK",
										editable : true,
										lazyInit : false,
										forceSelection : false,
										xtype : "diccombo",
										itemName : "案卷保管期限"
									},
									{
										fieldLabel : "开放形式",
										name : "Q_openStyle_S_LK",
										flex : 1,
										editable : true,
										lazyInit : false,
										forceSelection : false,
										xtype : "diccombo",
										itemName : "案卷开放形式"
									},
									{
										fieldLabel : "状态",
										hiddenName : "Q_status_SN_EQ",
										flex : 1,
										xtype : "combo",
										mode : "local",
										editable : false,
										triggerAction : "all",
										store : [ [ "", "全部" ], [ "1", "正常" ],
												[ "0", "销毁" ] ]
									}, {
										fieldLabel : "案卷分类ID",
										id : "ArchRollView.proTypeId",
										name : "Q_globalType.proTypeId_L_EQ",
										flex : 1,
										xtype : "hidden"
									}, {
										fieldLabel : "案卷分类名称",
										id : "ArchRollView.typeName",
										name : "Q_typeName_S_LK",
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
									layout : "anchor",
									collapsible : true,
									split : true,
									width : 200,
									items : [ {
										xtype : "treePanelEditor",
										id : "ArchRollGlobalTypeTree",
										split : true,
										rootVisible : false,
										border : false,
										height : 380,
										autoScroll : true,
										scope : this,
										url : __ctxPath
												+ "/system/treeGlobalType.do?catKey=AR_RL",
										onclick : function(c) {
											var a = c.id;
											var b = c.text;
											if (a == "0") {
												Ext
														.getCmp(
																"ArchRollSearchPanel")
														.getForm()
														.findField(
																"ArchRollView.proTypeId")
														.setValue("");
												Ext
														.getCmp(
																"ArchRollSearchPanel")
														.getForm()
														.findField(
																"ArchRollView.typeName")
														.setValue("");
											} else {
												Ext
														.getCmp(
																"ArchRollSearchPanel")
														.getForm()
														.findField(
																"ArchRollView.proTypeId")
														.setValue(a);
												Ext
														.getCmp(
																"ArchRollSearchPanel")
														.getForm()
														.findField(
																"ArchRollView.typeName")
														.setValue(b);
											}
											Ext.getCmp("ArchRollView").search();
										},
										contextMenuItems : [
												{
													text : "新建分类",
													scope : this,
													iconCls : "btn-add",
													handler : function() {
														var a = Ext
																.getCmp("ArchRollGlobalTypeTree");
														var c = a.selectedNode.id;
														var b = new GlobalTypeForm(
																{
																	parentId : c,
																	catKey : "AR_RL",
																	callback : function() {
																		Ext
																				.getCmp("ArchRollGlobalTypeTree").root
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
																.getCmp("ArchRollGlobalTypeTree");
														var a = b.selectedNode.id;
														var c = new GlobalTypeForm(
																{
																	proTypeId : a,
																	callback : function() {
																		Ext
																				.getCmp("ArchRollGlobalTypeTree").root
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
																.getCmp("ArchRollGlobalTypeTree");
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
																										.getCmp("ArchRollGlobalTypeTree").root
																										.reload();
																								Ext
																										.getCmp(
																												"ArchFondGrid")
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
								text : "添加案卷",
								xtype : "button",
								scope : this,
								handler : this.createRs
							}, {
								iconCls : "btn-del",
								text : "删除案卷",
								xtype : "button",
								scope : this,
								handler : this.removeSelRs
							} ]
						});
						this.gridPanel = new HT.GridPanel( {
							region : "center",
							tbar : this.topbar,
							rowActions : true,
							id : "ArchRollGrid",
							url : __ctxPath + "/arch/listArchRoll.do",
							fields : [ {
								name : "rollId",
								type : "int"
							}, "createTime", "updateTime", "creatorName",
									"creatorId", "status", "proTypeId",
									"typeName", "openStyle", "archFond",
									"rolllName", "rollNo", "catNo",
									"timeLimit", "startTime", "endTime",
									"author", "setupTime", "checker",
									"keyWords", "editCompany", "editDep",
									"decp" ],
							columns : [ {
								header : "rollId",
								dataIndex : "rollId",
								hidden : true
							}, {
								header : "全宗号",
								dataIndex : "archFond",
								renderer : function(a) {
									if (a) {
										return a.afNo;
									}
								}
							}, {
								header : "案卷号",
								dataIndex : "rollNo"
							}, {
								header : "案卷名称",
								dataIndex : "rolllName"
							}, {
								header : "案卷分类",
								dataIndex : "typeName"
							}, {
								header : "目录号",
								dataIndex : "catNo"
							}, {
								header : "保管期限",
								dataIndex : "timeLimit"
							}, {
								header : "状态",
								dataIndex : "status",
								renderer : function(a) {
									switch (a) {
									case 1:
										return "正常";
										break;
									case 0:
										return "销毁";
										break;
									}
								}
							}, new Ext.ux.grid.RowActions( {
								header : "管理",
								width : 100,
								actions : [ {
									iconCls : "btn-del",
									qtip : "删除",
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
							new ArchRollForm( {
								rollId : d.data.rollId
							}).show();
						});
					},
					createRs : function() {
						new ArchRollForm( {
							proTypeId : Ext.getCmp("ArchRollSearchPanel")
									.getForm().findField(
											"ArchRollView.proTypeId")
									.getValue(),
							typeName : Ext.getCmp("ArchRollSearchPanel")
									.getForm().findField(
											"ArchRollView.typeName").getValue()
						}).show();
					},
					removeRs : function(a) {
						$postDel( {
							url : __ctxPath + "/arch/multiDelArchRoll.do",
							ids : a,
							grid : this.gridPanel
						});
					},
					removeSelRs : function() {
						$delGridRs( {
							url : __ctxPath + "/arch/multiDelArchRoll.do",
							grid : this.gridPanel,
							idName : "rollId"
						});
					},
					editRs : function(a) {
						new ArchRollForm( {
							rollId : a.data.rollId
						}).show();
					},
					onRowAction : function(c, a, d, e, b) {
						switch (d) {
						case "btn-del":
							this.removeRs.call(this, a.data.rollId);
							break;
						case "btn-edit":
							this.editRs.call(this, a);
							break;
						default:
							break;
						}
					}
				});