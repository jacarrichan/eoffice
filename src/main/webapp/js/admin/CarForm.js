Ext.ns("CarForm");
CarForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						CarForm.superclass.constructor.call(this, {
							layout : "fit",
							id : "CarFormWin",
							title : "车辆详细信息",
							iconCls : "menu-car",
							width : 830,
							height : 450,
							minWidth : 829,
							minHeight : 449,
							items : this.formPanel,
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
									url : __ctxPath + "/admin/saveCar.do",
									layout : "hbox",
									layoutConfig : {
										padding : "5",
										align : "middle"
									},
									defaults : {
										margins : "0 5 0 0",
										width : 400
									},
									id : "CarForm",
									frame : false,
									formId : "CarFormId",
									items : [
											{
												xtype : "panel",
												title : "基本信息",
												layout : "form",
												frame : false,
												height : 360,
												bodyStyle : "padding:5px;",
												labelWidth : 100,
												defaults : {
													anchor : "100%,100%"
												},
												defaultType : "textfield",
												items : [
														{
															name : "car.carId",
															id : "carId",
															xtype : "hidden",
															value : this.carId == null ? ""
																	: this.carId
														},
														{
															name : "car.cartImage",
															id : "cartImage",
															xtype : "hidden"
														},
														{
															fieldLabel : "车牌号码",
															name : "car.carNo",
															id : "carNo",
															allowBlank : false,
															xtype : "textfield"
														},
														{
															fieldLabel : "车辆类型",
															name : "car.carType",
															id : "carType",
															xtype : "combo",
															mode : "local",
															editable : true,
															allowBlank : false,
															triggerAction : "all",
															value : "1",
															store : [
																	[ "1", "轿车" ],
																	[ "2", "货车" ],
																	[ "3",
																			"商务车" ] ]
														},
														{
															fieldLabel : "发动机型号",
															name : "car.engineNo",
															id : "engineNo"
														},
														{
															fieldLabel : "购买保险时间",
															name : "car.buyInsureTime",
															id : "buyInsureTime",
															editable : false,
															xtype : "datefield",
															format : "Y-m-d"
														},
														{
															fieldLabel : "年审时间",
															name : "car.auditTime",
															id : "auditTime",
															editable : false,
															xtype : "datefield",
															format : "Y-m-d"
														},
														{
															fieldLabel : "厂牌型号",
															name : "car.factoryModel",
															id : "factoryModel"
														},
														{
															fieldLabel : "驾驶员",
															name : "car.driver",
															id : "driver"
														},
														{
															fieldLabel : "购置日期",
															name : "car.buyDate",
															id : "buyDate",
															editable : false,
															xtype : "datefield",
															format : "Y-m-d"
														},
														{
															fieldLabel : "当前状态",
															hiddenName : "car.status",
															id : "status",
															xtype : "combo",
															mode : "local",
															allowBlank : false,
															editable : false,
															triggerAction : "all",
															value : "1",
															store : [
																	[ "1", "可用" ],
																	[ "2",
																			"维修中" ],
																	[ "0",
																			"已报废" ] ]
														},
														{
															fieldLabel : "备注",
															name : "car.notes",
															xtype : "textarea",
															anchor : "100%,100%",
															id : "notes"
														} ]
											},
											{
												xtype : "panel",
												id : "carImageDisplay",
												frame : false,
												border : true,
												height : 360,
												html : '<img src="' + __ctxPath + '/images/default_image_car.jpg" width="400" height="350"/>',
												tbar : new Ext.Toolbar(
														{
															width : "100%",
															height : 30,
															items : [
																	{
																		text : "上传",
																		iconCls : "btn-upload",
																		handler : function() {
																			var a = Ext
																					.getCmp("cartImage");
																			var b = App
																					.createUploadDialog( {
																						file_cat : "admin/car",
																						callback : CarForm.uploadCarPhoto,
																						permitted_extensions : [ "jpg" ]
																					});
																			if (a
																					.getValue() != ""
																					&& a
																							.getValue() != null
																					&& a
																							.getValue() != "undefined") {
																				var c = "再次上传需要先删除原有图片,";
																				Ext.Msg
																						.confirm(
																								"信息确认",
																								c
																										+ "是否删除？",
																								function(
																										e) {
																									if (e == "yes") {
																										var d = Ext
																												.getCmp(
																														"carId")
																												.getValue();
																										if (d != null
																												&& d != "undefined") {
																											Ext.Ajax
																													.request( {
																														url : __ctxPath
																																+ "/admin/delphotoCar.do",
																														method : "post",
																														params : {
																															carId : d
																														},
																														success : function() {
																															var g = a.value;
																															a
																																	.setValue("");
																															var f = Ext
																																	.getCmp("carImageDisplay");
																															f.body
																																	.update('<img src="' + __ctxPath + '/images/default_image_car.jpg" width="100%" height="100%" />');
																															Ext.Ajax
																																	.request( {
																																		url : __ctxPath
																																				+ "/system/deleteFileAttach.do",
																																		method : "post",
																																		params : {
																																			filePath : g
																																		},
																																		success : function() {
																																			b
																																					.show("queryBtn");
																																		}
																																	});
																														}
																													});
																										}
																									}
																								});
																			} else {
																				b
																						.show("queryBtn");
																			}
																		}
																	},
																	{
																		text : "删除",
																		iconCls : "btn-delete",
																		handler : function() {
																			var a = Ext
																					.getCmp("cartImage");
																			if (a.value != null
																					&& a.value != ""
																					&& a.value != "undefined") {
																				var b = "照片一旦删除将不可恢复,";
																				Ext.Msg
																						.confirm(
																								"确认信息",
																								b
																										+ "是否删除?",
																								function(
																										d) {
																									if (d == "yes") {
																										Ext.ux.Toast
																												.msg(
																														"提示信息",
																														"请上传规格为400 X 350,或者此比例的照片.");
																										var c = Ext
																												.getCmp(
																														"carId")
																												.getValue();
																										if (c != null
																												&& c != "undefined") {
																											Ext.Ajax
																													.request( {
																														url : __ctxPath
																																+ "/admin/delphotoCar.do",
																														method : "post",
																														params : {
																															carId : c
																														},
																														success : function() {
																															var f = a.value;
																															a
																																	.setValue("");
																															var e = Ext
																																	.getCmp("carImageDisplay");
																															e.body
																																	.update('<img src="' + __ctxPath + '/images/default_image_car.jpg" width="400" height="350" />');
																															Ext.Ajax
																																	.request( {
																																		url : __ctxPath
																																				+ "/system/deleteFileAttach.do",
																																		method : "post",
																																		params : {
																																			filePath : f
																																		},
																																		success : function() {
																																		}
																																	});
																														}
																													});
																										}
																									}
																								});
																			} else {
																				Ext.ux.Toast
																						.msg(
																								"提示信息",
																								"您还未增加照片.");
																			}
																		}
																	} ]
														})
											} ]
								});
						if (this.carId != null && this.carId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/admin/getCar.do?carId="
														+ this.carId,
												method : "post",
												waitMsg : "正在载入数据...",
												success : function(d, e) {
													var b = e.result.data.buyInsureTime;
													var a = e.result.data.auditTime;
													var f = e.result.data.buyDate;
													Ext.getCmp("buyInsureTime")
															.setValue(b);
													Ext.getCmp("auditTime")
															.setValue(a);
													Ext.getCmp("buyDate")
															.setValue(f);
													var c = e.result.data.cartImage;
													var g = Ext
															.getCmp("carImageDisplay");
													if (c != null && c != ""
															&& c != "undefind"
															&& g.body != null) {
														g.body
																.update('<img src="'
																		+ __ctxPath
																		+ "/attachFiles/"
																		+ c
																		+ '"  width="400" height="350"/>');
													}
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
										var a = Ext.getCmp("CarForm");
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
																					"CarGrid")
																			.getStore()
																			.reload();
																	Ext
																			.getCmp(
																					"CarFormWin")
																			.close();
																},
																failure : function(
																		b, c) {
																	Ext.MessageBox
																			.show( {
																				title : "操作信息",
																				msg : "信息保存出错，请联系管理员！",
																				buttons : Ext.MessageBox.OK,
																				icon : "ext-mb-error"
																			});
																	Ext
																			.getCmp(
																					"CarFormWin")
																			.close();
																}
															});
										}
									}
								}, {
									text : "取消",
									iconCls : "btn-cancel",
									handler : function() {
										Ext.getCmp("CarFormWin").close();
									}
								} ];
					}
				});
CarForm.uploadCarPhoto = function(b) {
	var a = Ext.getCmp("cartImage");
	var c = Ext.getCmp("carImageDisplay");
	a.setValue(b[0].filepath);
	c.body.update('<img src="' + __ctxPath + "/attachFiles/" + b[0].filepath
			+ '"  width="100%" height="100%"/>');
};