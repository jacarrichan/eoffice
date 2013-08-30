Ext.ns("SalaryItemView");
SalaryItemView = Ext
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
						SalaryItemView.superclass.constructor.call(this, {
							id : "SalaryItemView",
							title : "工资项列表",
							iconCls : "menu-salary",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel( {
							id : "SalaryItemSearchForm",
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
								text : "请输入查询条件:"
							}, {
								text : "工资项名称"
							}, {
								xtype : "textfield",
								name : "Q_itemName_S_LK"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var c = Ext.getCmp("SalaryItemSearchForm");
									var d = Ext.getCmp("SalaryItemGrid");
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
								url : __ctxPath + "/hrm/listSalaryItem.do"
							}),
							reader : new Ext.data.JsonReader( {
								root : "result",
								totalProperty : "totalCounts",
								id : "id",
								fields : [ {
									name : "salaryItemId",
									type : "int"
								}, "itemName", "defaultVal" ]
							}),
							remoteSort : true
						});
						this.store.setDefaultSort("salaryItemId", "desc");
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
												header : "salaryItemId",
												dataIndex : "salaryItemId",
												hidden : true
											},
											{
												header : "薪资项名称",
												dataIndex : "itemName"
											},
											{
												header : "缺省值",
												dataIndex : "defaultVal",
												renderer : function(c) {
													return '<img src="'
															+ __ctxPath
															+ '/images/flag/customer/rmb.png"/>'
															+ c;
												}
											},
											{
												header : "管理",
												dataIndex : "salaryItemId",
												width : 50,
												sortable : false,
												renderer : function(f, e, c, i,
														d) {
													var h = c.data.salaryItemId;
													var g = "";
													if (isGranted("_SalaryItemDel")) {
														g += '<button title="删除" value=" " class="btn-del" onclick="SalaryItemView.remove(' + h + ')">&nbsp;&nbsp;</button>';
													}
													if (isGranted("_SalaryItemEdit")) {
														g += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="SalaryItemView.edit(' + h + ')">&nbsp;&nbsp;</button>';
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
							id : "SalaryItemFootBar",
							height : 30,
							bodyStyle : "text-align:left",
							items : []
						});
						if (isGranted("_SalaryItemAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "添加薪酬项目",
								handler : function() {
									new SalaryItemForm().show();
								}
							}));
						}
						if (isGranted("_SalaryItemDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除薪酬项目",
								xtype : "button",
								handler : function() {
									var e = Ext.getCmp("SalaryItemGrid");
									var c = e.getSelectionModel()
											.getSelections();
									if (c.length == 0) {
										Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
										return;
									}
									var f = Array();
									for ( var d = 0; d < c.length; d++) {
										f.push(c[d].data.salaryItemId);
									}
									SalaryItemView.remove(f);
								}
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "SalaryItemGrid",
							region : "center",
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
								enableRowBody : false,
								showPreview : false
							},
							bbar : new Ext.PagingToolbar( {
								pageSize : 25,
								store : this.store,
								displayInfo : true,
								displayMsg : "当前显示{0}至{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
						});
						this.gridPanel.addListener("rowdblclick", function(d,
								c, f) {
							d.getSelectionModel().each(function(e) {
								SalaryItemView.edit(e.data.salaryItemId);
							});
						});
					}
				});
SalaryItemView.remove = function(b) {
	var a = Ext.getCmp("SalaryItemGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/hrm/multiDelSalaryItem.do",
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
SalaryItemView.edit = function(a) {
	new SalaryItemForm( {
		salaryItemId : a
	}).show();
};