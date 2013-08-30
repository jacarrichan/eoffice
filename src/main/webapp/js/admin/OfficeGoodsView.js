Ext.ns("OfficeGoodsView");
OfficeGoodsView = Ext
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
						OfficeGoodsView.superclass.constructor.call(this, {
							id : "OfficeGoodsView",
							title : "办公用品列表",
							region : "center",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel(
								{
									id : "OfficeGoodsSearchForm",
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
												text : "物品名称"
											},
											{
												xtype : "textfield",
												name : "Q_goodsName_S_LK"
											},
											{
												text : "所属分类"
											},
											{
												xtype : "textfield",
												name : "Q_officeGoodsType.typeName_S_LK"
											},
											{
												xtype : "button",
												text : "查询",
												iconCls : "search",
												handler : function() {
													var c = Ext
															.getCmp("OfficeGoodsSearchForm");
													var d = Ext
															.getCmp("OfficeGoodsGrid");
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
								url : __ctxPath + "/admin/listOfficeGoods.do"
							}),
							reader : new Ext.data.JsonReader( {
								root : "result",
								totalProperty : "totalCounts",
								id : "id",
								fields : [ {
									name : "goodsId",
									type : "int"
								}, {
									name : "typeName",
									mapping : "officeGoodsType.typeName"
								}, "goodsName", "goodsNo", "specifications",
										"unit", "isWarning", "warnCounts",
										"notes", "stockCounts" ]
							}),
							remoteSort : true
						});
						this.store.setDefaultSort("goodsId", "desc");
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
												header : "goodsId",
												dataIndex : "goodsId",
												hidden : true
											},
											{
												header : "所属分类",
												dataIndex : "typeName"
											},
											{
												header : "物品名称",
												dataIndex : "goodsName"
											},
											{
												header : "编号",
												dataIndex : "goodsNo"
											},
											{
												header : "规格",
												dataIndex : "specifications"
											},
											{
												header : "计量单位",
												dataIndex : "unit"
											},
											{
												header : "是否启用库存警示",
												dataIndex : "isWarning",
												renderer : function(c) {
													if (c == "0") {
														return "未启动";
													}
													if (c == "1") {
														return "已启动";
													}
												}
											},
											{
												header : "备注",
												dataIndex : "notes"
											},
											{
												header : "库存总数",
												dataIndex : "stockCounts",
												renderer : function(g, f, c, i,
														d) {
													var e = c.data.warnCounts;
													var h = c.data.isWarning;
													if (g <= e && h == "1") {
														return '<a style="color:red;" title="已少于警报库存！">'
																+ g + "</a>";
													} else {
														return g;
													}
												}
											},
											{
												header : "管理",
												dataIndex : "goodsId",
												width : 50,
												renderer : function(f, e, c, i,
														d) {
													var h = c.data.goodsId;
													var g = "";
													if (isGranted("_OfficeGoodsDel")) {
														g = '<button title="删除" value=" " class="btn-del" onclick="OfficeGoodsView.remove(' + h + ')">&nbsp;</button>';
													}
													if (isGranted("_OfficeGoodsEdit")) {
														g += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="OfficeGoodsView.edit(' + h + ')">&nbsp;</button>';
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
							id : "OfficeGoodsFootBar",
							height : 30,
							bodyStyle : "text-align:left",
							items : []
						});
						if (isGranted("_OfficeGoodsAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "添加办公用品",
								handler : function() {
									new OfficeGoodsForm().show();
									Ext.getCmp("OfficeGoodsFormItems").remove(
											"goodsNo");
									Ext.getCmp("OfficeGoodsFormS").remove(
											"stockCounts");
								}
							}));
						}
						if (isGranted("_OfficeGoodsDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除办公用品",
								handler : function() {
									var e = Ext.getCmp("OfficeGoodsGrid");
									var c = e.getSelectionModel()
											.getSelections();
									if (c.length == 0) {
										Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
										return;
									}
									var f = Array();
									for ( var d = 0; d < c.length; d++) {
										f.push(c[d].data.goodsId);
									}
									OfficeGoodsView.remove(f);
								}
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "OfficeGoodsGrid",
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
							if (isGranted("_OfficeGoodsEdit")) {
								d.getSelectionModel().each(function(e) {
									OfficeGoodsView.edit(e.data.goodsId);
								});
							}
						});
					}
				});
OfficeGoodsView.prototype.setTypeId = function(a) {
	this.typeId = a;
	OfficeGoodsView.typeId = a;
};
OfficeGoodsView.prototype.getTypeId = function() {
	return this.typeId;
};
OfficeGoodsView.remove = function(b) {
	var a = Ext.getCmp("OfficeGoodsGrid");
	Ext.Msg.confirm("信息确认", "会把入库和申请记录一起删除，您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelOfficeGoods.do",
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
OfficeGoodsView.edit = function(a) {
	new OfficeGoodsForm( {
		goodsId : a
	}).show();
};