Ext.ns("CartRepairView");
CartRepairView = Ext
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
						CartRepairView.superclass.constructor.call(this, {
							id : "CartRepairView",
							title : "车辆维修列表",
							iconCls : "menu-car_repair",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel( {
							id : "CartRepairSearchForm",
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
								text : "请输入查询条件:"
							}, {
								text : "车牌号码"
							}, {
								xtype : "textfield",
								name : "Q_car.carNo_S_LK"
							}, {
								text : "维修类型"
							}, {
								xtype : "textfield",
								name : "Q_repairType_S_LK"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var c = Ext.getCmp("CartRepairSearchForm");
									var d = Ext.getCmp("CartRepairGrid");
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
								url : __ctxPath + "/admin/listCartRepair.do"
							}),
							reader : new Ext.data.JsonReader( {
								root : "result",
								totalProperty : "totalCounts",
								id : "id",
								fields : [ {
									name : "repairId",
									type : "int"
								}, {
									name : "carNo",
									mapping : "car.carNo"
								}, "repairDate", "reason", "executant",
										"notes", "repairType", "fee" ]
							}),
							remoteSort : true
						});
						this.store.setDefaultSort("repairId", "desc");
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
												header : "repairId",
												dataIndex : "repairId",
												hidden : true
											},
											{
												header : "车辆车牌号",
												dataIndex : "carNo"
											},
											{
												header : "维护日期",
												dataIndex : "repairDate",
												renderer : function(c) {
													return c.substring(0, 10);
												}
											},
											{
												header : "维护原因",
												dataIndex : "reason"
											},
											{
												header : "经办人",
												dataIndex : "executant"
											},
											{
												header : "备注",
												dataIndex : "notes"
											},
											{
												header : "维修类型",
												dataIndex : "repairType"
											},
											{
												header : "费用",
												dataIndex : "fee"
											},
											{
												header : "管理",
												dataIndex : "repairId",
												width : 50,
												sortable : false,
												renderer : function(f, e, c, i,
														d) {
													var h = c.data.repairId;
													var g = "";
													if (isGranted("_CarRepairDel")) {
														g = '<button title="删除" value=" " class="btn-del" onclick="CartRepairView.remove(' + h + ')">&nbsp;</button>';
													}
													if (isGranted("_CarRepairEdit")) {
														g += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="CartRepairView.edit(' + h + ')">&nbsp;</button>';
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
							id : "CartRepairFootBar",
							height : 30,
							bodyStyle : "text-align:left",
							items : []
						});
						if (isGranted("_CarRepairAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "添加维修单",
								handler : function() {
									new CartRepairForm().show();
								}
							}));
						}
						if (isGranted("_CarRepairDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除维修单",
								handler : function() {
									var e = Ext.getCmp("CartRepairGrid");
									var c = e.getSelectionModel()
											.getSelections();
									if (c.length == 0) {
										Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
										return;
									}
									var f = Array();
									for ( var d = 0; d < c.length; d++) {
										f.push(c[d].data.repairId);
									}
									CartRepairView.remove(f);
								}
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "CartRepairGrid",
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
								if (isGranted("_CarRepairEdit")) {
									CartRepairView.edit(e.data.repairId);
								}
							});
						});
					}
				});
CartRepairView.remove = function(b) {
	var a = Ext.getCmp("CartRepairGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelCartRepair.do",
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
CartRepairView.edit = function(a) {
	new CartRepairForm( {
		repairId : a
	}).show();
};