Ext.ns("CarView");
CarView = Ext
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
						CarView.superclass.constructor.call(this, {
							id : "CarView",
							title : "车辆列表",
							iconCls : "menu-car",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel(
								{
									id : "CarSearchForm",
									height : 40,
									frame : false,
									region : "north",
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
												text : "车牌号码"
											},
											{
												xtype : "textfield",
												name : "Q_carNo_S_LK"
											},
											{
												text : "车类型"
											},
											{
												name : "Q_carType_S_LK",
												xtype : "combo",
												anchor : "95%",
												mode : "local",
												editable : true,
												triggerAction : "all",
												store : [ [ "1", "轿车" ],
														[ "2", "货车" ],
														[ "3", "商务车" ] ]
											},
											{
												text : "当前状态"
											},
											{
												hiddenName : "Q_status_SN_EQ",
												xtype : "combo",
												anchor : "95%",
												mode : "local",
												editable : true,
												triggerAction : "all",
												store : [ [ "1", "可用" ],
														[ "2", "维修中" ],
														[ "0", "已报废" ] ]
											},
											{
												xtype : "button",
												text : "查询",
												iconCls : "search",
												handler : function() {
													var c = Ext
															.getCmp("CarSearchForm");
													var d = Ext
															.getCmp("CarGrid");
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
								url : __ctxPath + "/admin/listCar.do"
							}),
							reader : new Ext.data.JsonReader( {
								root : "result",
								totalProperty : "totalCounts",
								id : "id",
								fields : [ {
									name : "carId",
									type : "int"
								}, "carNo", "carType", "engineNo",
										"buyInsureTime", "auditTime", "notes",
										"factoryModel", "driver", "buyDate",
										"status", "cartImage" ]
							}),
							remoteSort : true
						});
						this.store.setDefaultSort("carId", "desc");
						this.store.reload( {
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
												header : "carId",
												dataIndex : "carId",
												hidden : true
											},
											{
												header : "车牌号码",
												dataIndex : "carNo"
											},
											{
												header : "车辆类型",
												dataIndex : "carType"
											},
											{
												header : "发动机型号",
												dataIndex : "engineNo"
											},
											{
												header : "购买保险时间",
												dataIndex : "buyInsureTime"
											},
											{
												header : "年审时间",
												dataIndex : "auditTime"
											},
											{
												header : "厂牌型号",
												dataIndex : "factoryModel"
											},
											{
												header : "驾驶员",
												dataIndex : "driver"
											},
											{
												header : "购置日期",
												dataIndex : "buyDate"
											},
											{
												header : "当前状态",
												dataIndex : "status",
												renderer : function(c) {
													if (c == "1") {
														return "可用";
													}
													if (c == "2") {
														return "维修中";
													}
													if (c == "0") {
														return "已报废";
													}
												}
											},
											{
												header : "管理",
												dataIndex : "carId",
												width : 50,
												sortable : false,
												renderer : function(f, e, c, i,
														d) {
													var h = c.data.carId;
													var g = "";
													if (isGranted("_CarDel")) {
														g = '<button title="删除" value=" " class="btn-del" onclick="CarView.remove(' + h + ')">&nbsp;</button>';
													}
													if (isGranted("_CarEdit")) {
														g += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="CarView.edit(' + h + ')">&nbsp;</button>';
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
							id : "CarFootBar",
							height : 30,
							bodyStyle : "text-align:left",
							items : []
						});
						if (isGranted("_CarAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-car_add",
								text : "添加车辆",
								handler : function() {
									new CarForm().show();
								}
							}));
						}
						if (isGranted("_CarDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-car_del",
								text : "删除车辆",
								handler : function() {
									var e = Ext.getCmp("CarGrid");
									var c = e.getSelectionModel()
											.getSelections();
									if (c.length == 0) {
										Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
										return;
									}
									var f = Array();
									for ( var d = 0; d < c.length; d++) {
										f.push(c[d].data.carId);
									}
									CarView.remove(f);
								}
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "CarGrid",
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
								displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
						});
						this.gridPanel.addListener("rowdblclick", function(d,
								c, f) {
							d.getSelectionModel().each(function(e) {
								if (isGranted("_CarEdit")) {
									CarView.edit(e.data.carId);
								}
							});
						});
					}
				});
CarView.remove = function(b) {
	var a = Ext.getCmp("CarGrid");
	Ext.Msg.confirm("信息确认", "删除车辆会把该车辆申请记录和维修记录一起删除，您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelCar.do",
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
CarView.edit = function(a) {
	new CarForm( {
		carId : a
	}).show();
};