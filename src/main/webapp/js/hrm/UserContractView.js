UserContractView = Ext
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
						UserContractView.superclass.constructor.call(this, {
							id : "UserContractView",
							title : "合同管理",
							iconCls : "menu-contract",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel( {
							id : "UserContractSearchForm",
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
										text : "请输入查询条件:"
									},
									{
										text : "合同编号"
									},
									{
										width : 100,
										name : "Q_contractNo_S_LK",
										xtype : "textfield"
									},
									{
										text : "签约人"
									},
									{
										width : 100,
										name : "Q_fullname_S_LK",
										xtype : "textfield"
									},
									{
										text : "合同状态"
									},
									{
										width : 80,
										name : "Q_status_S_LK",
										xtype : "textfield",
										xtype : "combo",
										editable : false,
										mode : "local",
										triggerAction : "all",
										store : [ [ "0", "草稿" ], [ "1", "有效" ],
												[ "2", "终止" ] ]
									},
									{
										text : "签约日期"
									},
									{
										name : "Q_signDate_S_LK",
										xtype : "datefield",
										width : 110,
										format : "Y-m-d",
										editable : false
									},
									{
										xtype : "button",
										text : "查询",
										iconCls : "search",
										handler : this.search
												.createCallback(this)
									} ]
						});
						this.store = new Ext.data.JsonStore( {
							url : __ctxPath + "/hrm/listUserContract.do",
							root : "result",
							totalProperty : "totalCounts",
							remoteSort : true,
							fields : [ {
								name : "contractId",
								type : "int"
							}, "contractNo", "userId", "fullname", "status",
									"timeLimit", "isCompeted", "isSecret",
									"breakBurden", "otherItems",
									"contractType", "signDate", "startDate",
									"expireDate" ]
						});
						this.store.setDefaultSort("contractId", "desc");
						this.store.load( {
							params : {
								start : 0,
								limit : 25
							}
						});
						this.rowActions = new Ext.ux.grid.RowActions( {
							header : "管理",
							width : 180,
							actions : [ {
								iconCls : "btn-del",
								qtip : "删除",
								style : "margin:0 3px 0 3px"
							}, {
								iconCls : "btn-edit",
								qtip : "合同变更",
								style : "margin:0 3px 0 3px"
							}, {
								iconCls : "btn-carapply-del",
								qtip : "合同终止",
								style : "margin:0 3px 0 3px"
							}, {
								iconCls : "menu-arch-undertake",
								qtip : "合同续约",
								style : "margin:0 3px 0 3px"
							}, {
								iconCls : "menu-list",
								qtip : "查看合同",
								style : "margin:0 3px 0 3px"
							} ]
						});
						var b = new Ext.grid.CheckboxSelectionModel();
						var a = new Ext.grid.ColumnModel(
								{
									columns : [
											b,
											new Ext.grid.RowNumberer(),
											{
												header : "contractId",
												dataIndex : "contractId",
												hidden : true
											},
											{
												header : "合同编号",
												dataIndex : "contractNo"
											},
											{
												header : "签约职员ID",
												dataIndex : "userId",
												hidden : true
											},
											{
												header : "签约人",
												dataIndex : "fullname"
											},
											{
												header : "合同状态",
												dataIndex : "status",
												renderer : function(i, g, d, j,
														f) {
													var h = new Date(
															getDateFromFormat(
																	i,
																	"yyyy-MM-dd"));
													var c = new Date(
															getDateFromFormat(
																	d.data.expireDate,
																	"yyyy-MM-dd"));
													var e = new Date();
													if (i == "0") {
														return "草稿";
													} else {
														if (i == "1") {
															return '<img title="有效" src="' + __ctxPath + '/images/flag/customer/effective.png"/>';
														} else {
															if (e >= c
																	&& i == "2") {
																return '<img title="终止" src="' + __ctxPath + '/images/flag/customer/invalid.png"/>';
															}
														}
													}
												}
											}, {
												header : "期限形式",
												dataIndex : "timeLimit"
											}, {
												header : "竞业条款",
												dataIndex : "isCompeted",
												renderer : function(c) {
													if (c == "0") {
														return "无";
													} else {
														return "有";
													}
												}
											}, {
												header : "保密协议",
												dataIndex : "isSecret",
												renderer : function(c) {
													if (c == "0") {
														return "无";
													} else {
														return "有";
													}
												}
											}, {
												header : "合同类型",
												dataIndex : "contractType"
											}, {
												width : 180,
												header : "签约日期",
												dataIndex : "signDate"
											}, this.rowActions ],
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
								iconCls : "btn-add",
								text : "添加合同",
								xtype : "button",
								handler : this.createRecord
							}, {
								iconCls : "btn-del",
								text : "删除合同",
								xtype : "button",
								handler : this.delRecords,
								scope : this
							} ]
						});
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "UserContractGrid",
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
						this.gridPanel.addListener("rowdblclick", function(d,
								c, f) {
							d.getSelectionModel().each(function(e) {
								new UserContractForm( {
									contractId : e.data.contractId
								}).show();
							});
						});
						this.rowActions.on("action", this.onRowAction, this);
					},
					search : function(c) {
						var a = Ext.getCmp("UserContractSearchForm");
						if (a.getForm().isValid()) {
							var e = Ext.getCmp("UserContractGrid");
							var b = e.getStore();
							var f = Ext.Ajax.serializeForm(a.getForm().getEl());
							var d = Ext.urlDecode(f);
							d.start = 0;
							d.limit = b.baseParams.limit;
							b.baseParams = d;
							e.getBottomToolbar().moveFirst();
						}
					},
					createRecord : function() {
						new UserContractForm().show();
					},
					endContract : function(b) {
						var a = b.data.status;
						if (a == 2) {
							return new EndContractForm().show();
						} else {
							alert("此合同是草稿或是有效，只能变更或续约合同内容!");
						}
					},
					continueContract : function(b) {
						var a = b.data.status;
						if (a != 0) {
							return new ContinueContractForm().show();
						} else {
							alert("此人签订的合同还没生效，无法进行合同续约!");
						}
					},
					LookContract : function(a) {
						new UserContractDetailWin( {
							contractId : a.data.contractId
						}).show();
					},
					delByIds : function(a) {
						Ext.Msg
								.confirm(
										"信息确认",
										"您确认要删除所选记录下及所附属的合同附件和合同记录吗？",
										function(b) {
											if (b == "yes") {
												Ext.Ajax
														.request( {
															url : __ctxPath
																	+ "/hrm/multiDelUserContract.do",
															params : {
																ids : a
															},
															method : "POST",
															success : function(
																	c, d) {
																Ext.ux.Toast
																		.msg(
																				"操作信息",
																				"成功删除该合同！");
																Ext
																		.getCmp(
																				"UserContractGrid")
																		.getStore()
																		.reload();
																Ext
																		.getCmp(
																				"ContractEventGrid")
																		.getStore()
																		.reload();
															},
															failure : function(
																	c, d) {
																Ext.ux.Toast
																		.msg(
																				"操作信息",
																				"操作出错，请联系管理员！");
															}
														});
											}
										});
					},
					delRecords : function() {
						var c = Ext.getCmp("UserContractGrid");
						var a = c.getSelectionModel().getSelections();
						if (a.length == 0) {
							Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
							return;
						}
						var d = Array();
						for ( var b = 0; b < a.length; b++) {
							d.push(a[b].data.contractId);
						}
						this.delByIds(d);
					},
					editRecord : function(b) {
						var a = b.data.status;
						if (a == 0) {
							return new UserContractForm( {
								contractId : b.data.contractId
							}).show();
						} else {
							alert("此合同不是草稿！");
						}
					},
					onRowAction : function(c, a, d, e, b) {
						switch (d) {
						case "btn-del":
							this.delByIds(a.data.contractId);
							break;
						case "btn-edit":
							this.editRecord(a);
							break;
						case "btn-carapply-del":
							this.endContract(a);
							break;
						case "menu-arch-undertake":
							this.continueContract(a);
							break;
						case "menu-list":
							this.LookContract(a);
							break;
						default:
							break;
						}
					}
				});