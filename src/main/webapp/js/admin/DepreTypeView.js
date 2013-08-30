Ext.ns("DepreTypeView");
DepreTypeView = Ext
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
						DepreTypeView.superclass.constructor.call(this, {
							id : "DepreTypeView",
							title : "折算类型列表",
							iconCls : "menu-depre-type",
							region : "center",
							layout : "border",
							items : [ this.searchPanel, this.gridPanel ]
						});
					},
					initUIComponents : function() {
						this.searchPanel = new Ext.FormPanel(
								{
									id : "DepreTypeSearchForm",
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
									items : [
											{
												text : "请输入查询条件:"
											},
											{
												text : "类型名称"
											},
											{
												xtype : "textfield",
												name : "Q_typeName_S_LK"
											},
											{
												text : "折算类型"
											},
											{
												xtype : "textfield",
												hiddenName : "Q_calMethod_SN_EQ",
												xtype : "combo",
												allowBlank : false,
												mode : "local",
												editable : false,
												triggerAction : "all",
												store : [ [ "1", "平均年限法" ],
														[ "2", "工作量法" ],
														[ "3", "双倍余额递减法" ],
														[ "4", "年数总和法" ] ]
											},
											{
												xtype : "button",
												text : "查询",
												iconCls : "search",
												handler : function() {
													var c = Ext
															.getCmp("DepreTypeSearchForm");
													var d = Ext
															.getCmp("DepreTypeGrid");
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
								url : __ctxPath + "/admin/listDepreType.do"
							}),
							reader : new Ext.data.JsonReader( {
								root : "result",
								totalProperty : "totalCounts",
								id : "id",
								fields : [ {
									name : "depreTypeId",
									type : "int"
								}, "typeName", "deprePeriod", "typeDesc",
										"calMethod" ]
							}),
							remoteSort : true
						});
						this.store.setDefaultSort("depreTypeId", "desc");
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
												header : "depreTypeId",
												dataIndex : "depreTypeId",
												hidden : true
											},
											{
												header : "分类名称",
												dataIndex : "typeName"
											},
											{
												header : "折旧周期(月)",
												dataIndex : "deprePeriod"
											},
											{
												header : "折旧方法",
												dataIndex : "calMethod",
												renderer : function(c) {
													if (c == "1") {
														return "平均年限法";
													}
													if (c == "2") {
														return "工作量法";
													}
													if (c == "3") {
														return "双倍余额递减法";
													}
													if (c == "4") {
														return "年数总和法";
													}
												}
											},
											{
												header : "方法描述",
												dataIndex : "typeDesc"
											},
											{
												header : "管理",
												dataIndex : "depreTypeId",
												width : 50,
												sortable : false,
												renderer : function(f, e, c, i,
														d) {
													var h = c.data.depreTypeId;
													var g = "";
													if (isGranted("_DepreTypeDel")) {
														g = '<button title="删除" value=" " class="btn-del" onclick="DepreTypeView.remove(' + h + ')">&nbsp;</button>';
													}
													if (isGranted("_DepreTypeEdit")) {
														g += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="DepreTypeView.edit(' + h + ')">&nbsp;</button>';
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
							id : "DepreTypeFootBar",
							height : 30,
							bodyStyle : "text-align:left",
							items : []
						});
						if (isGranted("_DepreTypeAdd")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-add",
								text : "添加折旧类型",
								handler : function() {
									new DepreTypeForm().show();
								}
							}));
						}
						if (isGranted("_DepreTypeDel")) {
							this.topbar.add(new Ext.Button( {
								iconCls : "btn-del",
								text : "删除折旧类型",
								handler : function() {
									var e = Ext.getCmp("DepreTypeGrid");
									var c = e.getSelectionModel()
											.getSelections();
									if (c.length == 0) {
										Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
										return;
									}
									var f = Array();
									for ( var d = 0; d < c.length; d++) {
										f.push(c[d].data.depreTypeId);
									}
									DepreTypeView.remove(f);
								}
							}));
						}
						this.gridPanel = new Ext.grid.GridPanel( {
							id : "DepreTypeGrid",
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
								if (isGranted("_DepreTypeEdit")) {
									DepreTypeView.edit(e.data.depreTypeId);
								}
							});
						});
					}
				});
DepreTypeView.remove = function(b) {
	var a = Ext.getCmp("DepreTypeGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelDepreType.do",
				params : {
					ids : b
				},
				method : "post",
				success : function(d, f) {
					var e = Ext.util.JSON.decode(d.responseText);
					if (e.success == false) {
						Ext.ux.Toast.msg("信息提示", e.message);
					} else {
						Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
						a.getStore().reload( {
							params : {
								start : 0,
								limit : 25
							}
						});
					}
				}
			});
		}
	});
};
DepreTypeView.edit = function(a) {
	new DepreTypeForm( {
		depreTypeId : a
	}).show();
};