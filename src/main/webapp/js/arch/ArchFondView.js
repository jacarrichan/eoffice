ArchFondView = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						ArchFondView.superclass.constructor.call(this, {
							id : "ArchFondView",
							title : "全宗管理",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.leftPanel,
									this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new HT.SearchPanel( {
							id : "ArchFondSearchPanel",
							layout : "form",
							region : "north",
							colNums : 4,
							items : [
									{
										fieldLabel : "全宗号",
										name : "Q_afNo_S_LK",
										flex : 1,
										xtype : "textfield"
									},
									{
										fieldLabel : "全宗名",
										name : "Q_afName_S_LK",
										flex : 1,
										xtype : "textfield"
									},
									{
										fieldLabel : "状态",
										hiddenName : "Q_status_SN_EQ",
										flex : 1,
										xtype : "combo",
										mode : "local",
										editable : false,
										triggerAction : "all",
										store : [ [ "", "全部" ], [ "0", "草稿" ],
												[ "1", "启用" ], [ "-1", "禁用" ] ]
									}, {
										fieldLabel : "开放形式",
										name : "Q_openStyle_S_EQ",
										flex : 1,
										editable : true,
										lazyInit : false,
										forceSelection : false,
										xtype : "diccombo",
										itemName : "全宗开放形式"
									}, {
										fieldLabel : "创建时间	从",
										name : "Q_createTime_D_GE",
										flex : 1,
										xtype : "datefield",
										format : "Y-m-d"
									}, {
										fieldLabel : "至	",
										name : "Q_createTime_D_LE",
										flex : 1,
										xtype : "datefield",
										format : "Y-m-d"
									}, {
										fieldLabel : "最后更新时间	从",
										name : "Q_updatetime_D_EQ",
										flex : 1,
										xtype : "datefield",
										format : "Y-m-d"
									}, {
										fieldLabel : "至",
										name : "Q_updatetime_D_LE",
										flex : 1,
										xtype : "datefield",
										format : "Y-m-d"
									}, {
										fieldLabel : "",
										id : "ArchFondView.proTypeId",
										name : "Q_globalType.proTypeId_L_EQ",
										xtype : "hidden",
										hideLabel : true,
										flex : 1
									}, {
										fieldLabel : "",
										id : "ArchFondView.typeName",
										name : "Q_typeName_S_LK",
										xtype : "hidden",
										hideLabel : true,
										flex : 1
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
										id : "ArchFondGlobalTypeTree",
										split : true,
										rootVisible : false,
										border : false,
										height : 380,
										autoScroll : true,
										scope : this,
										url : __ctxPath
												+ "/system/treeGlobalType.do?catKey=AR_FD",
										onclick : function(c) {
											var a = c.id;
											var b = c.text;
											if (a == "0") {
												Ext
														.getCmp(
																"ArchFondSearchPanel")
														.getForm()
														.findField(
																"ArchFondView.proTypeId")
														.setValue("");
												Ext
														.getCmp(
																"ArchFondSearchPanel")
														.getForm()
														.findField(
																"ArchFondView.typeName")
														.setValue("");
											} else {
												Ext
														.getCmp(
																"ArchFondSearchPanel")
														.getForm()
														.findField(
																"ArchFondView.proTypeId")
														.setValue(a);
												Ext
														.getCmp(
																"ArchFondSearchPanel")
														.getForm()
														.findField(
																"ArchFondView.typeName")
														.setValue(b);
											}
											Ext.getCmp("ArchFondView").search();
										},
										contextMenuItems : [
												{
													text : "新建分类",
													scope : this,
													iconCls : "btn-add",
													handler : function() {
														var a = Ext
																.getCmp("ArchFondGlobalTypeTree");
														var c = a.selectedNode.id;
														var b = new GlobalTypeForm(
																{
																	parentId : c,
																	catKey : "AR_FD",
																	callback : function() {
																		Ext
																				.getCmp("ArchFondGlobalTypeTree").root
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
																.getCmp("ArchFondGlobalTypeTree");
														var a = b.selectedNode.id;
														var c = new GlobalTypeForm(
																{
																	proTypeId : a,
																	callback : function() {
																		Ext
																				.getCmp("ArchFondGlobalTypeTree").root
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
																.getCmp("ArchFondGlobalTypeTree");
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
																										.getCmp("ArchFondGlobalTypeTree").root
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
								text : "添加全宗",
								xtype : "button",
								scope : this,
								handler : this.createRs
							}, {
								iconCls : "btn-del",
								text : "删除全宗",
								xtype : "button",
								scope : this,
								handler : this.removeSelRs
							} ]
						});
						this.gridPanel = new HT.GridPanel( {
							region : "center",
							tbar : this.topbar,
							rowActions : true,
							id : "ArchFondGrid",
							url : __ctxPath + "/arch/listArchFond.do",
							fields : [ {
								name : "archFondId",
								type : "int"
							}, "afNo", "afName", "shortDesc", "descp",
									"clearupDesc", "createTime", "updateTime",
									"creatorName", "creatorId", "caseNums",
									"status", "globalType", "typeName",
									"openStyle" ],
							columns : [ {
								header : "archFondId",
								dataIndex : "archFondId",
								hidden : true
							}, {
								header : "全宗号",
								dataIndex : "afNo"
							}, {
								header : "全宗名",
								dataIndex : "afName"
							}, {
								header : "案卷数",
								dataIndex : "caseNums"
							}, {
								header : "状态",
								dataIndex : "status",
								render : function(a) {
									switch (a) {
									case 0:
										return "草稿";
										break;
									case 1:
										return "启用";
										break;
									case -1:
										return "禁用";
										break;
									}
								}
							}, {
								header : "全宗分类",
								dataIndex : "typeName"
							}, {
								header : "开放形式",
								dataIndex : "openStyle"
							}, {
								header : "创建人",
								dataIndex : "creatorName"
							}, {
								header : "创建时间",
								dataIndex : "createTime"
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
							new ArchFondForm( {
								archFondId : d.data.archFondId
							}).show();
						});
					},
					createRs : function() {
						new ArchFondForm( {
							proTypeId : Ext.getCmp("ArchFondSearchPanel")
									.getForm().findField(
											"ArchFondView.proTypeId")
									.getValue(),
							typeName : Ext.getCmp("ArchFondSearchPanel")
									.getForm().findField(
											"ArchFondView.typeName").getValue()
						}).show();
					},
					removeRs : function(a) {
						$postDel( {
							url : __ctxPath + "/arch/multiDelArchFond.do",
							ids : a,
							grid : this.gridPanel
						});
					},
					removeSelRs : function() {
						$delGridRs( {
							url : __ctxPath + "/arch/multiDelArchFond.do",
							grid : this.gridPanel,
							idName : "archFondId"
						});
					},
					editRs : function(a) {
						new ArchFondForm( {
							archFondId : a.data.archFondId
						}).show();
					},
					onRowAction : function(c, a, d, e, b) {
						switch (d) {
						case "btn-del":
							this.removeRs.call(this, a.data.archFondId);
							break;
						case "btn-edit":
							this.editRs.call(this, a);
							break;
						default:
							break;
						}
					}
				});