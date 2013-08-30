GoodsApplyForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						GoodsApplyForm.superclass.constructor.call(this, {
							layout : "fit",
							id : "GoodsApplyFormWin",
							title : "申请表详细信息",
							iconCls : "menu-goods-apply",
							width : 560,
							height : 340,
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
						this.formPanel = new Ext.FormPanel(
								{
									url : __ctxPath
											+ "/admin/saveGoodsApply.do",
									layout : "form",
									id : "GoodsApplyForm",
									frame : false,
									bodyStyle : "padding : 5px;",
									defaults : {
										anchor : "100%,100%"
									},
									formId : "GoodsApplyFormId",
									defaultType : "textfield",
									items : [
											{
												name : "goodsApply.applyId",
												id : "applyId",
												xtype : "hidden",
												value : this.applyId == null ? ""
														: this.applyId
											},
											{
												name : "goodsApply.goodsId",
												id : "goodsId",
												xtype : "hidden"
											},
											{
												name : "goodsApply.userId",
												id : "userId",
												value : curUserInfo.userId,
												xtype : "hidden"
											},
											{
												xtype : "container",
												layout : "column",
												style : "padding-left:0px;margin-bottom:4px;",
												items : [
														{
															xtype : "label",
															text : "办公用品名称:",
															style : "margin-top:2px;",
															width : 105
														},
														{
															xtype : "textfield",
															name : "inStock.officeGoods.goodsName",
															id : "goodsName",
															allowBlank : false,
															readOnly : true,
															width : 240
														},
														{
															xtype : "button",
															text : "选择办公用品",
															iconCls : "btn-select",
															handler : function() {
																GoodsSelector
																		.getView(
																				function(
																						d,
																						b) {
																					var a = Ext
																							.getCmp("goodsId");
																					a
																							.setValue(d);
																					var c = Ext
																							.getCmp("goodsName");
																					c
																							.setValue(b);
																				},
																				true)
																		.show();
															}
														},
														{
															xtype : "button",
															text : " 清除记录",
															iconCls : "reset",
															handler : function() {
																var a = Ext
																		.getCmp("goodsId");
																a.setValue("");
																var b = Ext
																		.getCmp("goodsName");
																b.setValue("");
															}
														} ]
											},
											{
												fieldLabel : "申请日期",
												name : "goodsApply.applyDate",
												id : "applyDate",
												xtype : "datefield",
												value : new Date(),
												format : "Y-m-d",
												allowBlank : false,
												editable : false
											},
											{
												fieldLabel : "申请号",
												name : "goodsApply.applyNo",
												id : "applyNo",
												readOnly : true
											},
											{
												fieldLabel : "申请数量",
												name : "goodsApply.useCounts",
												allowBlank : false,
												xtype : "numberfield",
												id : "useCounts"
											},
											{
												xtype : "container",
												layout : "column",
												style : "padding-left:0px;margin-bottom:4px;",
												border : true,
												items : [
														{
															xtype : "label",
															text : "申请人:",
															style : "margin-top:2px;",
															width : 105
														},
														{
															xtype : "textfield",
															name : "goodsApply.proposer",
															id : "proposer",
															value : curUserInfo.fullname,
															allowBlank : false,
															width : 220,
															readOnly : true
														},
														{
															xtype : "button",
															text : "选择人员",
															iconCls : "btn-users",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						c,
																						b) {
																					var a = Ext
																							.getCmp("proposer");
																					a
																							.setValue(b);
																					Ext
																							.getCmp(
																									"userId")
																							.setValue(
																									c);
																				},
																				true)
																		.show();
															}
														},
														{
															xtype : "button",
															text : " 清除记录",
															iconCls : "reset",
															handler : function() {
																var a = Ext
																		.getCmp("proposer");
																a.setValue("");
															}
														} ]
											},
											{
												fieldLabel : "审批状态 ",
												hiddenName : "goodsApply.approvalStatus",
												id : "approvalStatus",
												xtype : "combo",
												mode : "local",
												editable : false,
												triggerAction : "all",
												store : [ [ "1", "已审批" ],
														[ "0", "未审批" ] ]
											}, {
												fieldLabel : "备注",
												name : "goodsApply.notes",
												id : "notes",
												height : 120,
												xtype : "textarea"
											} ]
								});
						if (this.applyId != null && this.applyId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/admin/getGoodsApply.do?applyId="
														+ this.applyId,
												method : "post",
												waitMsg : "正在载入数据...",
												success : function(f, g) {
													var e = g.result.data.applyDate;
													var d = Ext
															.getCmp("applyDate");
													d
															.setValue(new Date(
																	getDateFromFormat(
																			e,
																			"yyyy-MM-dd HH:mm:ss")));
													var b = g.result.data.officeGoods.goodsId;
													var a = g.result.data.officeGoods.goodsName;
													var c = Ext
															.getCmp("goodsId");
													var h = Ext
															.getCmp("goodsName");
													c.setValue(b);
													h.setValue(a);
												},
												failure : function(a, b) {
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
										var a = Ext.getCmp("GoodsApplyForm");
										if (a.getForm().isValid()) {
											a
													.getForm()
													.submit(
															{
																method : "post",
																waitMsg : "正在提交数据...",
																success : function(
																		b, c) {
																	Ext.ux.Toast
																			.msg(
																					"操作信息",
																					"成功保存信息！");
																	Ext
																			.getCmp(
																					"GoodsApplyGrid")
																			.getStore()
																			.reload();
																	Ext
																			.getCmp(
																					"GoodsApplyFormWin")
																			.close();
																},
																failure : function(
																		b, d) {
																	var c = d.result.message;
																	Ext.MessageBox
																			.show( {
																				title : "操作信息",
																				msg : c == null
																						&& c == "" ? "信息保存出错，请联系管理员！"
																						: c,
																				buttons : Ext.MessageBox.OK,
																				icon : "ext-mb-error"
																			});
																	Ext
																			.getCmp(
																					"GoodsApplyFormWin")
																			.close();
																}
															});
										}
									}
								},
								{
									text : "取消",
									iconCls : "btn-cancel",
									handler : function() {
										Ext.getCmp("GoodsApplyFormWin").close();
									}
								} ];
					}
				});