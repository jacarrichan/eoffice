Ext.ns("EmpProfileView");
EmpProfileView = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						if (a == null) {
							a = {};
						}
						Ext.apply(this, a);
						this.initComponents();
						EmpProfileView.superclass.constructor.call(this, {
							id : "EmpProfileView",
							title : "档案管理",
							iconCls : "menu-profile",
							region : "center",
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
							items : [
									{
										text : "查询条件：档案编号："
									},
									{
										name : "Q_profileNo_S_LK",
										width : 80,
										xtype : "textfield"
									},
									{
										text : "员工姓名："
									},
									{
										name : "Q_fullname_S_LK",
										width : 80,
										xtype : "textfield"
									},
									{
										text : "身份证号："
									},
									{
										width : 80,
										name : "Q_idCard_S_LK",
										xtype : "textfield"
									},
									{
										text : "审核状态："
									},
									{
										hiddenName : "Q_approvalStatus_SN_EQ",
										width : 110,
										xtype : "combo",
										editable : false,
										mode : "local",
										triggerAction : "all",
										store : [ [ "", "　" ], [ "0", "未审核" ],
												[ "1", "审核通过" ],
												[ "2", "审核未通过" ] ]
									},
									{
										xtype : "button",
										text : "查询",
										iconCls : "search",
										handler : this.search
												.createCallback(this)
									}, {
										name : "Q_delFlag_SN_EQ",
										width : 80,
										xtype : "hidden",
										value : 0
									} ]
						});
						this.store = new Ext.data.JsonStore( {
							url : __ctxPath + "/hrm/listEmpProfile.do",
							baseParams : {
								"Q_delFlag_SN_EQ" : 0
							},
							root : "result",
							totalProperty : "totalCounts",
							remoteSort : true,
							fields : [ {
								name : "profileId",
								type : "int"
							}, "profileNo", "fullname", "designation",
									"creator", "createtime", "approvalStatus",
									"memo", "depName" ]
						});
						this.store.setDefaultSort("profileId", "desc");
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
												header : "profileId",
												dataIndex : "profileId",
												hidden : true
											},
											{
												header : "档案编号",
												dataIndex : "profileNo"
											},
											{
												header : "员工姓名",
												dataIndex : "fullname"
											},
											{
												header : "建档人",
												dataIndex : "creator"
											},
											{
												header : "建档时间",
												dataIndex : "createtime",
												renderer : function(c) {
													return c.substring(0, 10);
												}
											},
											{
												header : "部门或公司",
												dataIndex : "depName"
											},
											{
												header : "职称",
												dataIndex : "designation"
											},
											{
												header : "审核状态",
												dataIndex : "approvalStatus",
												renderer : function(c) {
													if (c == "0") {
														return "未审核";
													} else {
														if (c == "1") {
															return '<img title="通过审核" src="' + __ctxPath + '/images/flag/customer/effective.png"/>';
														} else {
															return '<img title="没通过审核" src="' + __ctxPath + '/images/flag/customer/invalid.png"/>';
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
													var i = d.data.profileId;
													var c = d.data.approvalStatus;
													var h = "";
													if (isGranted("_EmpProfileDel")) {
														h += '<button title="删除" value=" " class="btn-del" onclick="EmpProfileView.remove(' + i + ')">&nbsp;&nbsp;</button>';
													}
													if (isGranted("_EmpProfileEdit")
															&& c != 1) {
														h += '<button title="编辑" value=" " class="btn-edit" onclick="EmpProfileView.edit(' + i + ')">&nbsp;&nbsp;</button>';
													}
													if (c != 1 && c != 2) {
														if (isGranted("_EmpProfileCheck")) {
															h += '<button title="审核" value=" " class="btn-empProfile-check" onclick="EmpProfileView.check(' + i + ')">&nbsp;&nbsp;</button>';
														}
													} else {
														if (isGranted("_EmpProfileQuery")) {
															h += '<button title="查看" value=" " class="btn-readdocument" onclick="EmpProfileView.look(' + i + ')">&nbsp;&nbsp;</button>';
														}
													}
													if (isGranted("_EmpProfileQuery")) {
														h += '<button title="查看操作记录" value=" " class="btn-operation" onclick="EmpProfileView.operation(' + i + ')">&nbsp;&nbsp;</button>';
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
						if (isGranted("_EmpProfileAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "添加档案",
								handler : this.createRecord
							}));
						}
						if (isGranted("_EmpProfileDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除档案",
								handler : this.delRecords,
								scope : this
							}));
						}
						if (isGranted("_EmpProfileRec")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-empProfile-recovery",
								text : "恢复档案",
								handler : this.recovery
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "EmpProfileGrid",
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
								if (isGranted("_EmpProfileEdit")) {
									var g = e.data.profileId;
									if (e.data.approvalStatus == 0) {
										EmpProfileView.edit(g);
									} else {
										EmpProfileView.look(g);
									}
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
						var a = Ext.getCmp("EmpProfileForm");
						if (a != null) {
							b.remove("EmpProfileForm");
						}
						a = new EmpProfileForm();
						b.add(a);
						b.activate(a);
					},
					delRecords : function(b) {
						var d = Ext.getCmp("EmpProfileGrid");
						var a = d.getSelectionModel().getSelections();
						if (a.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
							return;
						}
						var e = Array();
						for ( var c = 0; c < a.length; c++) {
							e.push(a[c].data.profileId);
						}
						EmpProfileView.remove(e);
					},
					recovery : function(a) {
						new RecoveryProfileWin().show();
					}
				});
EmpProfileView.remove = function(b) {
	var a = Ext.getCmp("EmpProfileGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/hrm/multiDelEmpProfile.do",
				params : {
					ids : b
				},
				method : "POST",
				success : function(d, e) {
					Ext.ux.Toast.msg("操作信息", "成功删除该记录！");
					a.getStore().reload();
				},
				failure : function(d, e) {
					Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
				}
			});
		}
	});
};
EmpProfileView.edit = function(c) {
	var a = Ext.getCmp("centerTabPanel");
	var b = Ext.getCmp("EmpProfileForm");
	if (b == null) {
		b = new EmpProfileForm( {
			profileId : c
		});
		a.add(b);
	} else {
		a.remove("EmpProfileForm");
		b = new EmpProfileForm( {
			profileId : c
		});
		a.add(b);
	}
	a.activate(b);
};
EmpProfileView.check = function(a) {
	new CheckEmpProfileForm( {
		profileId : a
	}).show();
};
EmpProfileView.look = function(b) {
	var a = new CheckEmpProfileForm( {
		profileId : b
	}).show();
	a.setTitle("档案详细信息");
	a.remove("CheckEmpProfileForm");
	Ext.getCmp("CheckEmpProfileButY").hide();
	Ext.getCmp("CheckEmpProfileButN").hide();
};
EmpProfileView.operation = function(b) {
	var a = new Ext.Window( {
		id : "EmpProfileViewOperationWin",
		title : "标准操作纪录",
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
				url : __ctxPath
						+ "/pages/hrm/empProfileOperation.jsp?profileId=" + b
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