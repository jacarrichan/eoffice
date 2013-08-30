RecoveryProfileWin = Ext
		.extend(
				Ext.Window,
				{
					constructor : function(a) {
						if (a == null) {
							a = {};
						}
						Ext.apply(this, a);
						this.initComponents();
						RecoveryProfileWin.superclass.constructor.call(this, {
							id : "RecoveryProfileWin",
							iconCls : "btn-empProfile-recovery",
							title : "档案管理",
							border : false,
							width : 720,
							modal : true,
							height : 450,
							layout : "column",
							items : [ this.searchPanel, this.gridPanel ],
							bottons : this.buttons,
							buttonAlign : "center"
						});
					},
					typeId : null,
					searchPanel : null,
					gridPanel : null,
					store : null,
					topbar : null,
					initComponents : function() {
						this.buttons = [ {
							text : "取消",
							iconCls : "btn-cancel",
							handler : this.cancel.createCallback(this)
						} ];
						this.searchPanel = new Ext.FormPanel( {
							columnWidth : 1,
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
							items : [
									{
										text : "档案编号"
									},
									{
										name : "Q_profileNo_S_LK",
										width : 80,
										xtype : "textfield"
									},
									{
										text : "员工姓名"
									},
									{
										name : "Q_fullname_S_LK",
										width : 80,
										xtype : "textfield"
									},
									{
										text : "身份证号"
									},
									{
										width : 80,
										name : "Q_idCard_S_LK",
										xtype : "textfield"
									},
									{
										text : "审核状态"
									},
									{
										hiddenName : "Q_approvalStatus_SN_EQ",
										width : 80,
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
										value : 1
									} ]
						});
						this.store = new Ext.data.JsonStore( {
							url : __ctxPath + "/hrm/listEmpProfile.do",
							baseParams : {
								"Q_delFlag_SN_EQ" : 1
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
												dataIndex : "createtime"
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
							items : [ {
								iconCls : "btn-empProfile-recovery",
								text : "恢复档案",
								xtype : "button",
								handler : this.recoveryRecord
							} ]
						});
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "RecoveryProfileGrid",
							height : 300,
							columnWidth : 1,
							stripeRows : true,
							tbar : this.topbar,
							autoScroll : true,
							store : this.store,
							trackMouseOver : true,
							disableSelection : false,
							loadMask : true,
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
					recoveryRecord : function() {
						var c = Ext.getCmp("RecoveryProfileGrid");
						var a = c.getSelectionModel().getSelections();
						if (a.length == 0) {
							Ext.Msg.alert("信息", "请选择要恢复的档案记录！");
							return;
						}
						var d = Array();
						for ( var b = 0; b < a.length; b++) {
							d.push(a[b].data.profileId);
						}
						Ext.Msg.confirm("信息确认", "您确认要恢复该档案记录吗？", function(e) {
							if (e == "yes") {
								Ext.Ajax.request( {
									url : __ctxPath
											+ "/hrm/recoveryEmpProfile.do",
									params : {
										ids : d
									},
									method : "POST",
									success : function(f, g) {
										Ext.ux.Toast.msg("操作信息", "成功恢复档案！");
										Ext.getCmp("EmpProfileGrid").getStore()
												.reload();
										Ext.getCmp("RecoveryProfileGrid")
												.getStore().reload();
									},
									failure : function(f, g) {
										Ext.ux.Toast
												.msg("操作信息", "操作出错，请联系管理员！");
									}
								});
							}
						});
					},
					cancel : function(a) {
						a.close();
					}
				});