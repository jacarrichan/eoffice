Ext.ns("InStockView");
InStockView = Ext
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
						InStockView.superclass.constructor.call(this, {
							id : "InStockView",
							title : "入库用品列表",
							iconCls : "menu-instock",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel( {
							id : "InStockSearchForm",
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
								text : "商品名称"
							}, {
								xtype : "textfield",
								name : "Q_officeGoods.goodsName_S_LK"
							}, {
								text : "供应商"
							}, {
								xtype : "textfield",
								name : "Q_providerName_S_LK"
							}, {
								text : "购买人"
							}, {
								xtype : "textfield",
								name : "Q_buyer_S_LK"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var c = Ext.getCmp("InStockSearchForm");
									var d = Ext.getCmp("InStockGrid");
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
								url : __ctxPath + "/admin/listInStock.do"
							}),
							reader : new Ext.data.JsonReader(
									{
										root : "result",
										totalProperty : "totalCounts",
										id : "id",
										fields : [ {
											name : "buyId",
											type : "int"
										}, {
											name : "goodsName",
											mapping : "officeGoods.goodsName"
										}, "providerName", "stockNo", "price",
												"inCounts", "amount", "inDate",
												"buyer" ]
									}),
							remoteSort : true
						});
						this.store.setDefaultSort("buyId", "desc");
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
												header : "buyId",
												dataIndex : "buyId",
												hidden : true
											},
											{
												header : "商品名称",
												dataIndex : "goodsName"
											},
											{
												header : "供应商",
												dataIndex : "providerName"
											},
											{
												header : "库存号",
												dataIndex : "stockNo"
											},
											{
												header : "价格",
												dataIndex : "price"
											},
											{
												header : "入货数量",
												dataIndex : "inCounts"
											},
											{
												header : "总额",
												dataIndex : "amount"
											},
											{
												header : "入库日期",
												dataIndex : "inDate",
												renderer : function(c) {
													return c.substring(0, 10);
												}
											},
											{
												header : "购买人",
												dataIndex : "buyer"
											},
											{
												header : "管理",
												dataIndex : "buyId",
												sortable : false,
												width : 50,
												renderer : function(f, e, c, i,
														d) {
													var h = c.data.buyId;
													var g = "";
													if (isGranted("_InStockDel")) {
														g = '<button title="删除" value=" " class="btn-del" onclick="InStockView.remove(' + h + ')">&nbsp;</button>';
													}
													if (isGranted("_InStockEdit")) {
														g += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="InStockView.edit(' + h + ')">&nbsp;</button>';
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
							id : "InStockFootBar",
							height : 30,
							bodyStyle : "text-align:left",
							items : []
						});
						if (isGranted("_InStockAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "添加入库单",
								handler : function() {
									new InStockForm().show();
									Ext.getCmp("InStockFormContainer").remove(
											"stockNo");
									Ext.getCmp("InStockFormContainer").remove(
											"amount");
								}
							}));
						}
						if (isGranted("_InStockDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除入库单",
								handler : function() {
									var e = Ext.getCmp("InStockGrid");
									var c = e.getSelectionModel()
											.getSelections();
									if (c.length == 0) {
										Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
										return;
									}
									var f = Array();
									for ( var d = 0; d < c.length; d++) {
										f.push(c[d].data.buyId);
									}
									InStockView.remove(f);
								}
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "InStockGrid",
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
							if (isGranted("_InStockEdit")) {
								d.getSelectionModel().each(function(e) {
									InStockView.edit(e.data.buyId);
								});
							}
						});
					}
				});
InStockView.remove = function(b) {
	var a = Ext.getCmp("InStockGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelInStock.do",
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
InStockView.edit = function(a) {
	new InStockForm( {
		buyId : a
	}).show();
};