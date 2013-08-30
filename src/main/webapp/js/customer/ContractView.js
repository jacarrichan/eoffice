Ext.ns("ContractView");
ContractView = Ext
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
						ContractView.superclass.constructor.call(this, {
							id : "ContractView",
							title : "合同列表",
							iconCls : "menu-contract",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel( {
							id : "ContractSearchForm",
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
								text : "查询条件:"
							}, {
								text : "合同编号"
							}, {
								xtype : "textfield",
								width : 80,
								name : "Q_contractNo_S_LK"
							}, {
								text : "合同标题"
							}, {
								xtype : "textfield",
								width : 80,
								name : "Q_subject_S_LK"
							}, {
								text : "签约人"
							}, {
								xtype : "textfield",
								width : 80,
								name : "Q_signupUser_S_LK"
							}, {
								text : "所属项目"
							}, {
								xtype : "textfield",
								width : 80,
								name : "Q_projectId_S_LK"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var c = Ext.getCmp("ContractSearchForm");
									var d = Ext.getCmp("ContractGrid");
									if (c.getForm().isValid()) {
										$search( {
											searchPanel : c,
											gridPanel : d
										});
									}
								}
							} ]
						});
						this.store = new Ext.data.Store( {
							proxy : new Ext.data.HttpProxy( {
								url : __ctxPath + "/customer/listContract.do"
							}),
							reader : new Ext.data.JsonReader( {
								root : "result",
								totalProperty : "totalCounts",
								id : "id",
								fields : [ {
									name : "contractId",
									type : "int"
								}, "contractNo", "subject", "contractAmount",
										"creator", "createtime", "project",
										"signupUser", "signupTime" ]
							}),
							remoteSort : true
						});
						this.store.setDefaultSort("contractId", "desc");
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
												header : "contractId",
												dataIndex : "contractId",
												hidden : true
											},
											{
												header : "合同编号",
												dataIndex : "contractNo"
											},
											{
												header : "合同标题",
												dataIndex : "subject"
											},
											{
												header : "合同金额",
												dataIndex : "contractAmount",
												renderer : function(c) {
													return c + "元";
												}
											},
											{
												header : "签约人",
												dataIndex : "signupUser"
											},
											{
												header : "签约时间",
												dataIndex : "signupTime"
											},
											{
												header : "所属项目",
												dataIndex : "project",
												renderer : function(c) {
													return c.projectName;
												}
											},
											{
												header : "管理",
												dataIndex : "contractId",
												width : 50,
												sortable : false,
												renderer : function(f, e, c, i,
														d) {
													var h = c.data.contractId;
													var g = "";
													if (isGranted("_ContractDel")) {
														g = '<button title="删除" value=" " class="btn-del" onclick="ContractView.remove(' + h + ')">&nbsp</button>';
													}
													if (isGranted("_ContractEdit")) {
														g += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="ContractView.edit(' + h + ')">&nbsp</button>';
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
							id : "ContractFootBar",
							height : 30,
							bodyStyle : "text-align:left",
							items : []
						});
						if (isGranted("_ContractAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "添加合同",
								handler : function() {
									new ContractForm().show();
								}
							}));
						}
						if (isGranted("_ContractDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除合同",
								handler : function() {
									var e = Ext.getCmp("ContractGrid");
									var c = e.getSelectionModel()
											.getSelections();
									if (c.length == 0) {
										Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
										return;
									}
									var f = Array();
									for ( var d = 0; d < c.length; d++) {
										f.push(c[d].data.contractId);
									}
									ContractView.remove(f);
								}
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "ContractGrid",
							tbar : this.topbar,
							region : "center",
							store : this.store,
							trackMouseOver : true,
							disableSelection : false,
							loadMask : true,
							autoHeight : true,
							cm : a,
							sm : b,
							viewConfig : {
								forceFit : true,
								enableRowBody : false,
								showPreview : false
							},
							bbar : new Ext.PagingToolbar( {
								pageSize : 25,
								store : this.store,
								displayInfo : true,
								displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
						});
						this.gridPanel.addListener("rowdblclick", function(d,
								c, f) {
							d.getSelectionModel().each(function(e) {
								if (isGranted("_ContractEdit")) {
									ContractView.edit(e.data.contractId);
								}
							});
						});
					}
				});
ContractView.remove = function(b) {
	var a = Ext.getCmp("ContractGrid");
	Ext.Msg
			.confirm(
					"信息确认",
					'删除合同，则合同下的<font color="red">附件</font>及<font color="red">配置单信息</font>也删除，您确认要删除该记录吗？',
					function(c) {
						if (c == "yes") {
							Ext.Ajax.request( {
								url : __ctxPath
										+ "/customer/multiDelContract.do",
								params : {
									ids : b
								},
								method : "post",
								success : function() {
									Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
									a.getStore().reload( {
										params : {
											start : 0,
											limit : 25
										}
									});
								}
							});
						}
					});
};
ContractView.edit = function(a) {
	new ContractForm( {
		contractId : a
	}).show();
};