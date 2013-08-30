Ext.ns("ArchivesSignView");
ArchivesSignView = Ext
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
						ArchivesSignView.superclass.constructor.call(this, {
							id : "ArchivesSignView",
							iconCls : "menu-archive-sign",
							title : "公文签收待办",
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
								labelWidth : 75,
								xtype : "label"
							},
							items : [ {
								text : "类型名称"
							}, {
								name : "Q_archives.typeName_S_LK",
								width : 100,
								xtype : "textfield"
							}, {
								text : "发文字号"
							}, {
								width : 100,
								name : "Q_archives.archivesNo_S_LK",
								xtype : "textfield"
							}, {
								text : "文件标题"
							}, {
								width : 100,
								name : "Q_archives.subject_S_LK",
								xtype : "textfield"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : this.search.createCallback(this)
							}, {
								xtype : "hidden",
								name : "Q_archives.archType_SN_EQ",
								value : 0
							}, {
								xtype : "hidden",
								name : "Q_archives.status_SN_EQ",
								value : 7
							}, {
								xtype : "hidden",
								name : "Q_status_SN_EQ",
								value : 0
							} ]
						});
						this.store = new Ext.data.JsonStore( {
							url : __ctxPath + "/archive/listArchivesDep.do",
							root : "result",
							baseParams : {
								"Q_archives.archType_SN_EQ" : 0,
								"Q_archives.status_SN_EQ" : 7,
								"Q_status_SN_EQ" : 0
							},
							totalProperty : "totalCounts",
							remoteSort : true,
							fields : [ {
								name : "archDepId",
								type : "int"
							}, "archives" ]
						});
						this.store.setDefaultSort("archDepId", "desc");
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
												header : "archDepId",
												dataIndex : "archDepId",
												hidden : true
											},
											{
												header : "公文类型名称",
												dataIndex : "archives",
												renderer : function(c) {
													return c.typeName;
												}
											},
											{
												header : "发文字号",
												dataIndex : "archives",
												renderer : function(c) {
													return c.archivesNo;
												}
											},
											{
												header : "发文机关或部门",
												dataIndex : "archives",
												renderer : function(c) {
													return c.issueDep;
												}
											},
											{
												header : "文件标题",
												dataIndex : "archives",
												renderer : function(c) {
													return c.subject;
												}
											},
											{
												header : "公文状态",
												dataIndex : "archives",
												renderer : function(c) {
													if (c.status == 7) {
														return "已归档";
													} else {
														return "";
													}
												}
											},
											{
												header : "秘密等级",
												dataIndex : "archives",
												renderer : function(c) {
													return c.privacyLevel;
												}
											},
											{
												header : "紧急程度",
												dataIndex : "archives",
												renderer : function(c) {
													return c.urgentLevel;
												}
											},
											{
												header : "发文时间",
												dataIndex : "archives",
												renderer : function(c) {
													if (c.createtime == undefined) {
														return null;
													} else {
														return c.createtime
																.substring(0,
																		10);
													}
												}
											},
											{
												header : "管理",
												width : 100,
												dataIndex : "archives",
												sortable : false,
												renderer : function(j, i, e, h,
														k) {
													var c = j.archivesId;
													var d = j.status;
													var f = e.data.archDepId;
													var g = "";
													if (isGranted("_ArchivesSignQuery")) {
														g += '<button title="查阅详情" value=" " class="btn-archives-detail" onclick="ArchivesSignView.detail(' + c + ')">&nbsp;&nbsp;</button>';
													}
													if (isGranted("_ArchivesSignUp")) {
														g += '<button title="公文签收" value=" " class="btn-archive-sign" onclick="ArchivesSignView.attach('
																+ c
																+ ","
																+ f
																+ ')">&nbsp;&nbsp;</button>';
													}
													return g;
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
							id : "ArchivesSignGrid",
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
						this.gridPanel.addListener("rowdblclick", function(d,
								c, f) {
							d.getSelectionModel().each(function(e) {
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
						new ArchivesForm().show();
					}
				});
ArchivesSignView.detail = function(a) {
	new ArchivesDetailWin( {
		archivesId : a
	}).show();
};
ArchivesSignView.attach = function(d, a) {
	var b = Ext.getCmp("centerTabPanel");
	var c = Ext.getCmp("ArchivesRecForm");
	if (c == null) {
		c = new ArchivesRecForm( {
			archivesId : d,
			archDepId : a,
			isSign : true
		});
		b.add(c);
	} else {
		b.remove("ArchivesRecForm");
		c = new ArchivesRecForm( {
			archivesId : d,
			archDepId : a,
			isSign : true
		});
		b.add(c);
	}
	b.activate(c);
};