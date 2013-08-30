Ext.ns("GoodsApplyView");
GoodsApplyView = Ext
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
						GoodsApplyView.superclass.constructor.call(this, {
							id : "GoodsApplyView",
							title : "办公用品申请列表",
							iconCls : "menu-goods-apply",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel( {
							id : "GoodsApplySearchForm",
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
								text : "申请号"
							}, {
								xtype : "textfield",
								name : "Q_applyNo_S_LK"
							}, {
								text : "申请人"
							}, {
								xtype : "textfield",
								name : "Q_proposer_S_LK"
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var c = Ext.getCmp("GoodsApplySearchForm");
									var d = Ext.getCmp("GoodsApplyGrid");
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
								url : __ctxPath + "/admin/listGoodsApply.do"
							}),
							reader : new Ext.data.JsonReader( {
								root : "result",
								totalProperty : "totalCounts",
								id : "id",
								fields : [ {
									name : "applyId",
									type : "int"
								}, {
									name : "goodsName",
									mapping : "officeGoods.goodsName"
								}, "applyDate", "applyNo", "useCounts",
										"proposer", "notes", "approvalStatus" ]
							}),
							remoteSort : true
						});
						this.store.setDefaultSort("applyId", "desc");
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
												header : "applyId",
												dataIndex : "applyId",
												hidden : true
											},
											{
												header : "商品名称",
												dataIndex : "goodsName"
											},
											{
												header : "申请日期",
												dataIndex : "applyDate",
												renderer : function(c) {
													return c.substring(0, 10);
												}
											},
											{
												header : "申请号",
												dataIndex : "applyNo"
											},
											{
												header : "申请数量",
												dataIndex : "useCounts"
											},
											{
												header : "申请人",
												dataIndex : "proposer"
											},
											{
												header : "备注",
												dataIndex : "notes"
											},
											{
												header : "审批状态",
												dataIndex : "approvalStatus",
												renderer : function(c) {
													if (c == "0") {
														return "未审批";
													} else {
														if (c == "1") {
															return '<font color="green">通过审批</font>';
														} else {
															return '<font color="red">未通过审批</font>';
														}
													}
												}
											},
											{
												header : "管理",
												dataIndex : "applyId",
												width : 50,
												renderer : function(g, f, d, j,
														e) {
													var i = d.data.applyId;
													var c = d.data.approvalStatus;
													var h = "";
													if (isGranted("_GoodsApplyDel")) {
														h = '<button title="删除" value=" " class="btn-del" onclick="GoodsApplyView.remove(' + i + ')">&nbsp;</button>';
													}
													if (isGranted("_GoodsApplyEdit")) {
														if (c == 0) {
															h += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="GoodsApplyView.edit(' + i + ')">&nbsp;</button>';
														}
													}
													if (isGranted("_GoodsApplyCheck")) {
														if (c == 0) {
															h += '&nbsp;<button title="审批" value=" " class="btn-ok" onclick="GoodsApplyView.check(' + i + ')">&nbsp;</button>';
														}
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
							id : "GoodsApplyFootBar",
							height : 30,
							bodyStyle : "text-align:left",
							items : []
						});
						if (isGranted("_GoodsApplyAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "添加申请单",
								handler : function() {
									new GoodsApplyForm().show();
									Ext.getCmp("GoodsApplyForm").remove(
											"applyNo");
									Ext.getCmp("GoodsApplyForm").remove(
											"approvalStatus");
								}
							}));
						}
						if (isGranted("_GoodsApplyDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除申请单",
								handler : function() {
									var e = Ext.getCmp("GoodsApplyGrid");
									var c = e.getSelectionModel()
											.getSelections();
									if (c.length == 0) {
										Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
										return;
									}
									var f = Array();
									for ( var d = 0; d < c.length; d++) {
										f.push(c[d].data.applyId);
									}
									GoodsApplyView.remove(f);
								}
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "GoodsApplyGrid",
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
								if (isGranted("_GoodsApplyEdit")) {
									if (e.data.approvalStatus == 0) {
										GoodsApplyView.edit(e.data.applyId);
									}
								}
							});
						});
					}
				});
GoodsApplyView.remove = function(b) {
	var a = Ext.getCmp("GoodsApplyGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelGoodsApply.do",
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
GoodsApplyView.edit = function(a) {
	new GoodsApplyForm( {
		applyId : a
	}).show();
};
GoodsApplyView.check = function(a) {
	new GoodsApplyCheck( {
		applyId : a
	}).show();
};