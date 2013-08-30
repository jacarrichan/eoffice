OfficeGoodsForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						OfficeGoodsForm.superclass.constructor.call(this, {
							layout : "fit",
							id : "OfficeGoodsFormWin",
							title : "办公用品详细信息",
							iconCls : "menu-goods",
							width : 540,
							height : 280,
							minWidth : 539,
							minHeight : 239,
							items : this.formPanel,
							maximizable : true,
							border : false,
							modal : true,
							plain : true,
							buttonAlign : "center",
							buttons : this.buttons
						});
					},
					initUIComponents : function() {
						var a = __ctxPath
								+ "/admin/treeOfficeGoodsType.do?method=1";
						var b = new TreeSelector("goodsTypeSelect", a, "所属分类",
								"typeId");
						this.formPanel = new Ext.FormPanel(
								{
									url : __ctxPath
											+ "/admin/saveOfficeGoods.do",
									id : "OfficeGoodsForm",
									frame : false,
									bodyStyle : "padding:5px;",
									formId : "OfficeGoodsFormId",
									items : [
											{
												name : "officeGoods.goodsId",
												id : "goodsId",
												xtype : "hidden",
												value : this.goodsId == null ? ""
														: this.goodsId
											},
											{
												name : "officeGoods.officeGoodsType.typeId",
												id : "typeId",
												xtype : "hidden"
											},
											{
												layout : "column",
												xtype : "fieldset",
												style : "padding:0px",
												border : false,
												items : [
														{
															layout : "form",
															columnWidth : 0.6,
															defaultType : "textfield",
															id : "OfficeGoodsFormItems",
															border : false,
															items : [
																	b,
																	{
																		fieldLabel : "物品名称",
																		name : "officeGoods.goodsName",
																		id : "goodsName",
																		allowBlank : false,
																		width : 168
																	},
																	{
																		fieldLabel : "编号",
																		name : "officeGoods.goodsNo",
																		id : "goodsNo",
																		width : 168,
																		allowBlank : false,
																		readOnly : true
																	},
																	{
																		fieldLabel : "规格",
																		name : "officeGoods.specifications",
																		id : "specifications",
																		allowBlank : false,
																		width : 168
																	} ]
														},
														{
															layout : "form",
															columnWidth : 0.4,
															border : false,
															id : "OfficeGoodsFormS",
															defaultType : "textfield",
															items : [
																	{
																		fieldLabel : "计量单位",
																		name : "officeGoods.unit",
																		id : "unit",
																		allowBlank : false,
																		anchor : "95%"
																	},
																	{
																		fieldLabel : "库存总数",
																		name : "officeGoods.stockCounts",
																		id : "stockCounts",
																		xtype : "numberfield",
																		allowBlank : false,
																		anchor : "95%"
																	},
																	{
																		name : "counts",
																		id : "counts",
																		xtype : "hidden"
																	},
																	{
																		fieldLabel : "是否启用库存警示",
																		xtype : "combo",
																		anchor : "95%",
																		allowBlank : false,
																		hiddenName : "officeGoods.isWarning",
																		id : "isWarning",
																		mode : "local",
																		editable : false,
																		triggerAction : "all",
																		store : [
																				[
																						"0",
																						"否" ],
																				[
																						"1",
																						"是" ] ],
																		value : 0
																	},
																	{
																		fieldLabel : "警报库存数",
																		name : "officeGoods.warnCounts",
																		id : "warnCounts",
																		allowBlank : false,
																		xtype : "numberfield",
																		anchor : "95%"
																	} ]
														} ]
											}, {
												xtype : "container",
												layout : "form",
												items : [ {
													fieldLabel : "备注",
													name : "officeGoods.notes",
													id : "notes",
													xtype : "textarea",
													width : 378
												} ]
											} ]
								});
						if (this.goodsId != null && this.goodsId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/admin/getOfficeGoods.do?goodsId="
														+ this.goodsId,
												method : "post",
												waitMsg : "正在载入数据...",
												success : function(h, i) {
													var d = Ext
															.getCmp("typeId");
													var j = Ext
															.getCmp("goodsTypeSelect");
													var g = i.result.data.officeGoodsType.typeId;
													var e = i.result.data.officeGoodsType.typeName;
													d.setValue(g);
													j.setValue(e);
													var f = i.result.data.stockCounts;
													var c = Ext
															.getCmp("counts");
													c.setValue(f);
												},
												failure : function(c, d) {
													Ext.ux.Toast.msg("编辑",
															"载入失败");
												}
											});
						}
						this.buttons = [
								{
									text : "保存",
									iconCls : "btn-save",
									handler : function() {
										var c = Ext.getCmp("OfficeGoodsForm");
										if (Ext.getCmp("stockCounts") != null) {
											var f = Ext.getCmp("stockCounts")
													.getValue();
											var d = Ext.getCmp("counts")
													.getValue();
										}
										var e = Ext.getCmp("goodsTypeSelect")
												.getValue();
										if (e != null && e != ""
												&& e != "undefined") {
											if (f != d) {
												Ext.Msg
														.confirm(
																"操作信息",
																"你已经修改了库存，是否继续保存?",
																function(g) {
																	if (g == "yes") {
																		if (c
																				.getForm()
																				.isValid()) {
																			c
																					.getForm()
																					.submit(
																							{
																								method : "post",
																								waitMsg : "正在提交数据...",
																								success : function(
																										h,
																										i) {
																									Ext.ux.Toast
																											.msg(
																													"操作信息",
																													"成功保存信息！");
																									Ext
																											.getCmp(
																													"OfficeGoodsGrid")
																											.getStore()
																											.reload();
																									Ext
																											.getCmp(
																													"OfficeGoodsFormWin")
																											.close();
																								},
																								failure : function(
																										h,
																										i) {
																									Ext.MessageBox
																											.show( {
																												title : "操作信息",
																												msg : "信息保存出错，请联系管理员！",
																												buttons : Ext.MessageBox.OK,
																												icon : "ext-mb-error"
																											});
																									Ext
																											.getCmp(
																													"OfficeGoodsFormWin")
																											.close();
																								}
																							});
																		}
																	}
																});
											} else {
												if (c.getForm().isValid()) {
													c
															.getForm()
															.submit(
																	{
																		method : "post",
																		waitMsg : "正在提交数据...",
																		success : function(
																				g,
																				h) {
																			Ext.ux.Toast
																					.msg(
																							"操作信息",
																							"成功保存信息！");
																			Ext
																					.getCmp(
																							"OfficeGoodsGrid")
																					.getStore()
																					.reload();
																			Ext
																					.getCmp(
																							"OfficeGoodsFormWin")
																					.close();
																		},
																		failure : function(
																				g,
																				h) {
																			Ext.MessageBox
																					.show( {
																						title : "操作信息",
																						msg : "信息保存出错，请联系管理员！",
																						buttons : Ext.MessageBox.OK,
																						icon : "ext-mb-error"
																					});
																			Ext
																					.getCmp(
																							"OfficeGoodsFormWin")
																					.close();
																		}
																	});
												}
											}
										} else {
											Ext.ux.Toast.msg("操作信息", "分类不能为空！");
										}
									}
								},
								{
									text : "取消",
									iconCls : "btn-cancel",
									handler : function() {
										Ext.getCmp("OfficeGoodsFormWin")
												.close();
									}
								} ];
					}
				});