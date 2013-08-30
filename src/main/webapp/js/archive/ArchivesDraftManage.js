Ext.ns("ArchivesDraftManage");
ArchivesDraftManage = Ext
		.extend(
				Ext.Panel,
				{
					searchPanel : null,
					gridPanel : null,
					store : null,
					topbar : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						ArchivesDraftManage.superclass.constructor.call(this, {
							id : "ArchivesDraftManage",
							iconCls : "menu-archive-draft-manage",
							title : "拟稿管理",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel( {
							height : 35,
							region : "north",
							frame : false,
							border : false,
							layout : "hbox",
							layoutConfig : {
								padding : "5",
								align : "middle"
							},
							defaults : {
								style : "padding:0px 5px 0px 5px;",
								border : false,
								anchor : "98%,98%",
								labelWidth : 70,
								xtype : "label"
							},
							items : [ {
								text : "公文类型"
							}, {
								name : "Q_typeName_S_LK",
								xtype : "textfield"
							}, {
								text : "发文字号"
							}, {
								name : "Q_archivesNo_S_LK",
								xtype : "textfield"
							}, {
								text : "文件标题"
							}, {
								name : "Q_subject_S_LK",
								xtype : "textfield"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : this.search.createCallback(this)
							}, {
								xtype : "hidden",
								name : "Q_status_SN_GE",
								value : 0
							}, {
								xtype : "hidden",
								name : "Q_status_SN_LE",
								value : 2
							}, {
								xtype : "hidden",
								name : "Q_archType_SN_EQ",
								value : 0
							}, {
								xtype : "hidden",
								name : "Q_issuerId_L_EQ",
								value : curUserInfo.userId
							} ]
						});
						this.store = new Ext.data.JsonStore( {
							url : __ctxPath + "/archive/listArchives.do",
							root : "result",
							baseParams : {
								"Q_issuerId_L_EQ" : curUserInfo.userId,
								"Q_archType_SN_EQ" : 0,
								"Q_status_SN_GE" : 0,
								"Q_status_SN_LE" : 2
							},
							totalProperty : "totalCounts",
							remoteSort : true,
							fields : [ {
								name : "archivesId",
								type : "int"
							}, "typeId", "typeName", "archivesNo", "issueDep",
									"depId", "subject", "issueDate", "status",
									"shortContent", "fileCounts",
									"privacyLevel", "urgentLevel", "issuer",
									"issuerId", "keywords", "sources",
									"archType", "createtime" ]
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
												header : "公文类型",
												dataIndex : "typeId",
												hidden : true
											},
											{
												header : "公文类型",
												dataIndex : "typeName"
											},
											{
												header : "发文字号",
												dataIndex : "archivesNo"
											},
											{
												header : "发文机关或部门",
												dataIndex : "issueDep"
											},
											{
												header : "文件标题",
												dataIndex : "subject"
											},
											{
												header : "公文状态",
												dataIndex : "status",
												renderer : function(c) {
													if (c == 0) {
														return '<font color="#777">草稿</font>';
													} else {
														if (c == 1) {
															return '<font color="red">待核稿</font>';
														} else {
															if (c == 2) {
																return '<font color="green">核稿完成</font>';
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
												header : "发文时间",
												dataIndex : "createtime",
												renderer : function(c) {
													return c.substring(0, 10);
												}
											},
											{
												header : "管理",
												width : 100,
												sortable : false,
												renderer : function(g, f, d, j,
														e) {
													var i = d.data.archivesId;
													var c = d.data.status;
													var h = "";
													if (c == "0") {
														if (isGranted("_ArchivesDrafmDel")) {
															h += '<button title="删除" value=" " class="btn-del" onclick="ArchivesDraftManage.remove(' + i + ')">&nbsp;&nbsp;</button>';
														}
														if (isGranted("_ArchivesDrafmEdit")) {
															h += '<button title="编辑草稿" value=" " class="btn-archive-draft" onclick="ArchivesDraftManage.editDraft(' + i + ')">&nbsp;&nbsp;</button>';
														}
													} else {
														h += '<button title="查看详情" value=" " class="btn-archives-detail" onclick="ArchivesDraftManage.detail(' + i + ')">&nbsp;&nbsp;</button>';
													}
													return h;
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
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "ArchivesGrid",
							region : "center",
							stripeRows : true,
							tbar : this.topbar,
							store : this.store,
							trackMouseOver : true,
							disableSelection : false,
							loadMask : true,
							height : 600,
							cm : a,
							sm : b,
							plugins : this.rowActions,
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
						new ArchivesDraftView().show();
					},
					delByIds : function(a) {
						Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
							if (b == "yes") {
								Ext.Ajax.request( {
									url : __ctxPath
											+ "/archive/multiDelArchives.do",
									params : {
										ids : a
									},
									method : "POST",
									success : function(c, d) {
										Ext.ux.Toast.msg("操作信息", "成功删除该拟稿！");
										Ext.getCmp("ArchivesGrid").getStore()
												.reload();
									},
									failure : function(c, d) {
										Ext.ux.Toast
												.msg("操作信息", "操作出错，请联系管理员！");
									}
								});
							}
						});
					}
				});
ArchivesDraftManage.edit = function(a) {
	new ArchivesDraftWin( {
		archivesId : a
	}).show();
};
ArchivesDraftManage.editDraft = function(c) {
	var a = Ext.getCmp("centerTabPanel");
	var b = Ext.getCmp("ArchivesDraftView");
	if (b == null) {
		b = new ArchivesDraftView( {
			archivesId : c
		});
		a.add(b);
	} else {
		a.remove("ArchivesDraftView");
		b = new ArchivesDraftView( {
			archivesId : c
		});
		a.add(b);
	}
	a.activate(b);
};
ArchivesDraftManage.remove = function(a) {
	Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
		if (b == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/archive/multiDelArchives.do",
				params : {
					ids : a
				},
				method : "POST",
				success : function(c, d) {
					Ext.ux.Toast.msg("操作信息", "成功删除该拟稿！");
					Ext.getCmp("ArchivesGrid").getStore().reload();
				},
				failure : function(c, d) {
					Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
				}
			});
		}
	});
};
ArchivesDraftManage.detail = function(a) {
	new ArchivesDetailWin( {
		archivesId : a
	}).show();
};