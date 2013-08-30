Ext.ns("ArchivesRecView");
ArchivesRecView = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						if (a == null) {
							a = {};
						}
						Ext.apply(this, a);
						this.initComponents();
						ArchivesRecView.superclass.constructor.call(this, {
							id : "ArchivesRecView",
							title : "公文登记待办",
							region : "center",
							iconCls : "menu-arch-reg",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					typeId : null,
					searchPanel : null,
					gridPanel : null,
					store : null,
					topbar : null,
					initComponents : function() {
						this.searchPanel = new Ext.FormPanel( {
							height : 40,
							region : "north",
							frame : false,
							border : false,
							layout : "hbox",
							layoutConfig : {
								padding : "5",
								align : "middle"
							},
							defaults : {
								xtype : "label",
								margins : {
									top : 0,
									right : 4,
									bottom : 4,
									left : 4
								}
							},
							items : [ {
								text : "发文字号"
							}, {
								name : "Q_archivesNo_S_LK",
								xtype : "textfield",
								anchor : "98%"
							}, {
								text : "文件标题"
							}, {
								name : "Q_subject_S_LK",
								xtype : "textfield",
								anchor : "98%"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : this.search.createCallback(this)
							} ]
						});
						this.store = new Ext.data.JsonStore( {
							url : __ctxPath + "/archive/listArchives.do",
							baseParams : {
								"Q_archType_SN_EQ" : 1,
								"Q_issuerId_L_EQ" : curUserInfo.userId
							},
							root : "result",
							totalProperty : "totalCounts",
							remoteSort : true,
							fields : [ {
								name : "archivesId",
								type : "int"
							}, "typeId", "typeName", "archivesNo", "issueDep",
									"depId", "subject", "issueDate", "status",
									"shortContent", "fileCounts",
									"handleOpinion", "privacyLevel",
									"urgentLevel", "issuer", "issuerId",
									"keywords", "sources", "archiveType" ]
						});
						this.store.setDefaultSort("archivesId", "desc");
						this.store.load( {
							params : {
								start : 0,
								limit : 25
							}
						});
						var b = new Ext.grid.CheckboxSelectionModel();
						var a = new Ext.grid.ColumnModel(
								{
									columns : [
											b,
											new Ext.grid.RowNumberer(),
											{
												header : "archivesId",
												dataIndex : "archivesId",
												hidden : true
											},
											{
												header : "发文字号",
												dataIndex : "archivesNo"
											},
											{
												header : "来文机关或部门",
												dataIndex : "issueDep"
											},
											{
												header : "文件标题",
												dataIndex : "subject"
											},
											{
												header : "发布日期",
												dataIndex : "issueDate"
											},
											{
												header : "公文状态",
												dataIndex : "status",
												renderer : function(c) {
													if (c == 0) {
														return '<font color="red">草稿</font>';
													} else {
														if (c == 1) {
															return '<font color="green">等待主任审批</font>';
														} else {
															if (c == 2) {
																return '<font color="green">等待领导审批</font>';
															} else {
																if (c == 3) {
																	return '<font color="green">等待分发</font>';
																} else {
																	if (c == 4) {
																		return '<font color="green">等待处理</font>';
																	} else {
																		if (c == 5) {
																			return '<font color="green">处理中</font>';
																		} else {
																			if (c == 6) {
																				return '<font color="green">等待申请归档</font>';
																			} else {
																				if (c == 7) {
																					return '<font color="green">等待归档</font>';
																				} else {
																					if (c == 8) {
																						return '<font color="#777">归档结束</font>';
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											},
											{
												header : "秘密等级",
												dataIndex : "privacyLevel"
											},
											{
												header : "紧急程度",
												dataIndex : "urgentLevel"
											},
											{
												header : "管理",
												dataIndex : "dispatchId",
												sortable : false,
												width : 50,
												renderer : function(h, f, d, j,
														e) {
													var g = d.data.status;
													var c = d.data.archivesId;
													var i = "";
													if (g == 0) {
														if (isGranted("_ArchivesRecDel")) {
															i += '<button title="删除" value=" " class="btn-del" onclick="ArchivesRecView.remove(' + c + ')"></button>';
														}
														if (isGranted("_ArchivesRecEdit")) {
															i += '<button title="编辑" value=" " class="btn-edit" onclick="ArchivesRecView.edit(' + c + ')"></button>';
														}
													} else {
														if (isGranted("_ArchivesRecQuery")) {
															i += '<button title="查看" value=" " class="btn-readdocument" onclick="ArchivesRecView.detail(' + c + ')"></button>';
														}
													}
													return i;
												}
											} ],
									defaults : {
										sortable : true,
										menuDisabled : false,
										width : 100
									}
								});
						this.topbar = new Ext.Toolbar( {
							height : 30,
							bodyStyle : "text-align:left",
							items : []
						});
						if (isGranted("_ArchivesRecAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "添加收文",
								handler : this.createRecord
							}));
						}
						if (isGranted("_ArchivesRecDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除收文",
								handler : this.delRecords
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "ArchivesRecGrid",
							region : "center",
							stripeRows : true,
							tbar : this.topbar,
							store : this.store,
							trackMouseOver : true,
							disableSelection : false,
							loadMask : true,
							autoHeight : true,
							cm : a,
							sm : b,
							viewConfig : {
								forceFit : true,
								autoFill : true,
								forceFit : true
							},
							bbar : new Ext.PagingToolbar( {
								pageSize : 25,
								store : this.store,
								displayInfo : true,
								displayMsg : "当前页记录索引{0}-{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
						});
						this.gridPanel.addListener("rowdblclick", function(d,
								c, f) {
							d.getSelectionModel().each(function(e) {
								ArchivesRecView.detail(e.data.archivesId);
							});
						});
					},
					search : function(a) {
						if (a.searchPanel.getForm().isValid()) {
							$search( {
								searchPanel : a.searchPanel,
								gridPanel : a.gridPanel
							});
						}
					},
					createRecord : function() {
						var a = Ext.getCmp("centerTabPanel");
						var b = Ext.getCmp("ArchivesRecForm");
						if (b == null) {
							b = new ArchivesRecForm();
							a.add(b);
						} else {
							a.remove("ArchivesRecForm");
							b = new ArchivesRecForm();
							a.add(b);
						}
						a.activate(b);
					},
					delRecords : function() {
						var d = Ext.getCmp("ArchivesRecGrid");
						var a = d.getSelectionModel().getSelections();
						if (a.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
							return;
						}
						var e = Array();
						var f = new Array();
						var b = 0;
						for ( var c = 0; c < a.length; c++) {
							if (a[c].data.status == 0) {
								e.push(a[c].data.archivesId);
							} else {
								b++;
								f.push(a[c].data.subject);
							}
						}
						if (b == 0) {
							ArchivesRecView.remove(e);
						} else {
							Ext.ux.Toast.msg("操作提示", f + "不能被删除！");
						}
					}
				});
ArchivesRecView.edit = function(c) {
	var a = Ext.getCmp("centerTabPanel");
	var b = Ext.getCmp("ArchivesRecForm");
	if (b != null) {
		a.remove("ArchivesRecForm");
	}
	b = new ArchivesRecForm( {
		archivesId : c
	});
	a.add(b);
	a.activate(b);
};
ArchivesRecView.remove = function(a) {
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(b) {
		if (b == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/archive/multiDelArchives.do",
				params : {
					ids : a
				},
				method : "POST",
				success : function(c, d) {
					Ext.ux.Toast.msg("操作信息", "成功删除该公文分类！");
					Ext.getCmp("ArchivesRecGrid").getStore().reload();
				},
				failure : function(c, d) {
					Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
				}
			});
		}
	});
};
ArchivesRecView.detail = function(a) {
	new ArchivesDetail( {
		archivesId : a
	}).show();
};