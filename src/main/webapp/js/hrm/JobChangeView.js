Ext.ns("JobChangeView");
JobChangeView = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						if (a == null) {
							a = {};
						}
						Ext.apply(this, a);
						this.initComponents();
						JobChangeView.superclass.constructor.call(this, {
							id : "JobChangeView",
							title : "职位调动管理",
							region : "center",
							iconCls : "menu-jobchange",
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
							region : "north",
							height : 40,
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
								text : "查询条件：档案编号："
							}, {
								width : 100,
								name : "Q_profileNo_S_LK",
								xtype : "textfield"
							}, {
								text : "姓名："
							}, {
								width : 100,
								name : "Q_userName_S_LK",
								xtype : "textfield"
							}, {
								text : "原职位："
							}, {
								width : 100,
								name : "Q_orgJobName_S_LK",
								xtype : "textfield"
							}, {
								text : "新职位："
							}, {
								width : 100,
								name : "Q_newJobName_S_LK",
								xtype : "textfield"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : this.search.createCallback(this)
							} ]
						});
						this.store = new Ext.data.JsonStore( {
							url : __ctxPath + "/hrm/listJobChange.do",
							root : "result",
							totalProperty : "totalCounts",
							remoteSort : true,
							fields : [ {
								name : "changeId",
								type : "int"
							}, "profileId", "profileNo", "userName",
									"orgJobId", "orgJobName", "newJobId",
									"newJobName", "orgStandardNo",
									"orgStandardName", "orgDepId",
									"orgDepName", "orgTotalMoney",
									"newStandardNo", "newStandardName",
									"newDepId", "newDepName", "newTotalMoney",
									"changeReason", "regName", "regTime",
									"checkName", "checkTime", "checkOpinion",
									"status", "memo" ]
						});
						this.store.setDefaultSort("changeId", "desc");
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
												header : "changeId",
												dataIndex : "changeId",
												hidden : true
											},
											{
												header : "档案编号",
												dataIndex : "profileNo"
											},
											{
												header : "姓名",
												dataIndex : "userName"
											},
											{
												header : "原职位名称",
												dataIndex : "orgJobName"
											},
											{
												header : "新职位名称",
												dataIndex : "newJobName"
											},
											{
												header : "原部门名称",
												dataIndex : "orgDepName"
											},
											{
												header : "新部门名称",
												dataIndex : "newDepName"
											},
											{
												header : "登记人",
												dataIndex : "regName"
											},
											{
												header : "登记时间",
												dataIndex : "regTime"
											},
											{
												header : "状态",
												dataIndex : "status",
												renderer : function(c) {
													if (c == -1) {
														return '<font color="red">草稿</font>';
													} else {
														if (c == 1) {
															return '<img title="通过审核" src="' + __ctxPath + '/images/flag/customer/effective.png"/>';
														} else {
															if (c == 2) {
																return '<img title="没通过审核" src="' + __ctxPath + '/images/flag/customer/invalid.png"/>';
															} else {
																return '<font color="green">提交审核</font>';
															}
														}
													}
												}
											},
											{
												header : "管理",
												dataIndex : "profileId",
												width : 100,
												sortable : false,
												renderer : function(g, f, d, j,
														e) {
													var i = d.data.changeId;
													var c = d.data.status;
													var h = "";
													if (isGranted("_JobChangeDel")) {
														h += '<button title="删除" value=" " class="btn-del" onclick="JobChangeView.remove(' + i + ')">&nbsp;&nbsp;</button>';
													}
													if (c != 1 && c != 2) {
														if (isGranted("_JobChangeEdit")) {
															h += '<button title="编辑" value=" " class="btn-edit" onclick="JobChangeView.edit(' + i + ')">&nbsp;&nbsp;</button>';
														}
														if (c != -1) {
															if (isGranted("_JobChangeCheck")) {
																h += '<button title="审核" value=" " class="btn-empProfile-check" onclick="JobChangeView.check(' + i + ')">&nbsp;&nbsp;</button>';
															}
														}
													} else {
														if (isGranted("_JobChangeQuery")) {
															h += '<button title="查看" value=" " class="btn-readdocument" onclick="JobChangeView.look(' + i + ')">&nbsp;&nbsp;</button>';
														}
													}
													if (isGranted("_JobChangeQuery")) {
														h += '<button title="查看操作记录" value=" " class="btn-operation" onclick="JobChangeView.operation(' + i + ')">&nbsp;&nbsp;</button>';
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
						if (isGranted("_JobChangeAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "登记",
								handler : this.createRecord
							}));
						}
						if (isGranted("_JobChangeDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除",
								handler : this.delRecords,
								scope : this
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "JobChangeGrid",
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
							d.getSelectionModel().each(function(i) {
								var j = i.data.changeId;
								var e = i.data.status;
								if (e != 1 && e != 2) {
									if (isGranted("_JobChangeEdit")) {
										var h = Ext.getCmp("centerTabPanel");
										var g = Ext.getCmp("JobChangeForm");
										if (g != null) {
											h.remove("JobChangeForm");
										}
										g = new JobChangeForm( {
											changeId : j
										});
										h.add(g);
										h.activate(g);
									}
								} else {
									JobChangeView.look(j);
								}
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
						var b = Ext.getCmp("centerTabPanel");
						var a = Ext.getCmp("JobChangeForm");
						if (a != null) {
							b.remove("JobChangeForm");
						}
						a = new JobChangeForm();
						b.add(a);
						b.activate(a);
					},
					delRecords : function() {
						var c = Ext.getCmp("JobChangeGrid");
						var a = c.getSelectionModel().getSelections();
						if (a.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
							return;
						}
						var d = Array();
						for ( var b = 0; b < a.length; b++) {
							d.push(a[b].data.changeId);
						}
						JobChangeView.remove(d);
					}
				});
JobChangeView.edit = function(c) {
	var b = Ext.getCmp("centerTabPanel");
	var a = Ext.getCmp("JobChangeForm");
	if (a != null) {
		b.remove("JobChangeForm");
	}
	a = new JobChangeForm( {
		changeId : c
	});
	b.add(a);
	b.activate(a);
};
JobChangeView.remove = function(a) {
	Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
		if (b == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/hrm/multiDelJobChange.do",
				params : {
					ids : a
				},
				method : "POST",
				success : function(c, d) {
					Ext.ux.Toast.msg("操作信息", "成功删除记录！");
					Ext.getCmp("JobChangeGrid").getStore().reload();
				},
				failure : function(c, d) {
					Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
				}
			});
		}
	});
};
JobChangeView.check = function(a) {
	new CheckJobChangeWin( {
		changeId : a
	}).show();
};
JobChangeView.operation = function(b) {
	var a = new Ext.Window( {
		id : "JobChangeViewOperationWin",
		title : "职位调动操作纪录",
		iconCls : "btn-operation",
		width : 500,
		x : 300,
		y : 50,
		autoHeight : true,
		border : false,
		modal : true,
		layout : "anchor",
		plain : true,
		bodyStyle : "padding:5px;",
		buttonAlign : "center",
		items : [ new Ext.Panel( {
			autoScroll : true,
			autoHeight : true,
			border : false,
			autoLoad : {
				url : __ctxPath + "/pages/hrm/jobChangeOperation.jsp?changeId="
						+ b
			}
		}) ],
		buttons : [ {
			text : "关闭",
			iconCls : "btn-cancel",
			handler : function() {
				a.close();
			}
		} ]
	});
	a.show();
};
JobChangeView.look = function(b) {
	var a = new CheckJobChangeWin( {
		changeId : b
	}).show();
	a.setTitle("职位调换详细信息");
	Ext.getCmp("CheckJobChangePanel").setHeight(280);
	a.remove("CheckJobChangeWin");
	Ext.getCmp("JobChangeBtnY").hide();
	Ext.getCmp("JobChangeBtnN").hide();
};