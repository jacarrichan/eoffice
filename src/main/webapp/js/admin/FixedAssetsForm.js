FixedAssetsForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						FixedAssetsForm.superclass.constructor.call(this, {
							layout : "fit",
							id : "FixedAssetsFormWin",
							title : "固定资产详细信息(*为必填)",
							width : 520,
							iconCls : "menu-assets",
							height : 450,
							minWidth : 519,
							minHeight : 449,
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
											+ "/admin/saveFixedAssets.do",
									layout : "form",
									id : "FixedAssetsForm",
									frame : false,
									bodyStyle : "padding:5px;",
									formId : "FixedAssetsFormId",
									defaultType : "textfield",
									items : [
											{
												name : "fixedAssets.assetsId",
												id : "assetsId",
												xtype : "hidden",
												value : this.assetsId == null ? ""
														: this.assetsId
											},
											{
												name : "fixedAssets.depreRate",
												id : "depreRate",
												xtype : "hidden"
											},
											{
												name : "calDepreMethod",
												id : "calDepreMethod",
												xtype : "hidden"
											},
											{
												fieldLabel : "资产名称*",
												name : "fixedAssets.assetsName",
												id : "assetsName",
												anchor : "95.5%",
												allowBlank : false
											},
											{
												xtype : "container",
												layout : "column",
												id : "assetsNoContainer",
												style : "padding-left:0px;padding-bottom:3px;",
												items : [
														{
															xtype : "label",
															style : "padding-left:0px;padding-top:3px;",
															text : "资产编号:",
															width : 105
														},
														{
															xtype : "textfield",
															name : "fixedAssets.assetsNo",
															id : "assetsNo",
															readOnly : true,
															width : 248
														} ]
											},
											{
												fieldLabel : "资产类别*",
												hiddenName : "fixedAssets.assetsType.assetsTypeId",
												id : "assetsTypeId",
												xtype : "combo",
												mode : "local",
												anchor : "95.5%",
												allowBlank : false,
												editable : false,
												valueField : "id",
												displayField : "name",
												triggerAction : "all",
												store : new Ext.data.SimpleStore(
														{
															url : __ctxPath
																	+ "/admin/comboxAssetsType.do",
															fields : [ "id",
																	"name" ]
														})
											},
											{
												fieldLabel : "置办日期*",
												name : "fixedAssets.buyDate",
												id : "buyDate",
												format : "Y-m-d",
												xtype : "datefield",
												allowBlank : false,
												editable : false,
												anchor : "95.5%"
											},
											{
												xtype : "container",
												layout : "hbox",
												layoutConfigs : {
													align : "middle"
												},
												defaults : {
													margins : "0 2 0 0"
												},
												items : [
														{
															xtype : "label",
															text : "所属部门*:",
															height : 28,
															width : 103
														},
														{
															xtype : "textfield",
															name : "fixedAssets.beDep",
															id : "beDep",
															allowBlank : false,
															readOnly : true,
															width : 108
														},
														{
															xtype : "button",
															iconCls : "btn-dep-sel",
															text : "选择部门",
															handler : function() {
																DepSelector
																		.getView(
																				function(
																						b,
																						a) {
																					Ext
																							.getCmp(
																									"beDep")
																							.setValue(
																									a);
																				},
																				true)
																		.show();
															}
														},
														{
															xtype : "button",
															text : "清除记录",
															iconCls : "reset",
															handler : function() {
																Ext
																		.getCmp(
																				"beDep")
																		.setValue(
																				"");
															}
														} ]
											},
											{
												xtype : "container",
												layout : "hbox",
												height : 28,
												layoutConfigs : {
													align : "middle"
												},
												defaults : {
													margins : "0 2 0 0"
												},
												items : [
														{
															xtype : "label",
															text : "保管人:",
															style : "padding-left:0px;padding-top:3px;",
															width : 103
														},
														{
															xtype : "textfield",
															name : "fixedAssets.custodian",
															id : "custodian",
															readOnly : true,
															width : 108
														},
														{
															xtype : "button",
															iconCls : "btn-user-sel",
															text : "选择人员",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						b,
																						a) {
																					Ext
																							.getCmp(
																									"custodian")
																							.setValue(
																									a);
																				},
																				true)
																		.show();
															}
														},
														{
															xtype : "button",
															text : "清除记录",
															iconCls : "reset",
															handler : function() {
																Ext
																		.getCmp(
																				"custodian")
																		.setValue(
																				"");
															}
														} ]
											},
											{
												xtype : "tabpanel",
												height : 220,
												plain : true,
												activeTab : 0,
												items : [
														{
															layout : "form",
															id : "deprePanel",
															title : "折旧信息",
															frame : false,
															bodyStyle : "padding:4px 4px 4px 4px",
															height : 190,
															defaults : {
																anchor : "98%,98%"
															},
															defaultType : "textfield",
															items : [
																	{
																		fieldLabel : "折旧类型*",
																		hiddenName : "fixedAssets.depreType.depreTypeId",
																		id : "depreTypeId",
																		xtype : "combo",
																		mode : "local",
																		allowBlank : false,
																		editable : false,
																		valueField : "id",
																		displayField : "name",
																		triggerAction : "all",
																		store : new Ext.data.SimpleStore(
																				{
																					url : __ctxPath
																							+ "/admin/comboxDepreType.do",
																					fields : [
																							"id",
																							"name",
																							"method" ]
																				}),
																		listeners : {
																			select : function(
																					c,
																					a,
																					b) {
																				var d = a.data.method;
																				if (d == "2") {
																					Ext
																							.getCmp(
																									"calDepreMethod")
																							.setValue(
																									d);
																					Ext
																							.getCmp(
																									"WorkGrossPanel")
																							.show();
																					Ext
																							.getCmp(
																									"intendTermContainer")
																							.hide();
																					Ext
																							.getCmp(
																									"intendTerm")
																							.setValue(
																									"");
																				} else {
																					Ext
																							.getCmp(
																									"calDepreMethod")
																							.setValue(
																									d);
																					Ext
																							.getCmp(
																									"intendTermContainer")
																							.show();
																					Ext
																							.getCmp(
																									"WorkGrossPanel")
																							.hide();
																					Ext
																							.getCmp(
																									"intendWorkGross")
																							.setValue(
																									"");
																					Ext
																							.getCmp(
																									"workGrossUnit")
																							.setValue(
																									"");
																					Ext
																							.getCmp(
																									"defPerWorkGross")
																							.setValue(
																									"");
																				}
																			}
																		}
																	},
																	{
																		fieldLabel : "开始折旧日期",
																		name : "fixedAssets.startDepre",
																		id : "startDepre",
																		format : "Y-m-d",
																		xtype : "datefield",
																		editable : false
																	},
																	{
																		xtype : "container",
																		layout : "hbox",
																		height : 28,
																		layoutConfigs : {
																			align : "middle"
																		},
																		defaults : {
																			margins : "0 2 0 0"
																		},
																		id : "intendTermContainer",
																		items : [
																				{
																					xtype : "label",
																					text : "预计使用年限*:",
																					width : 103
																				},
																				{
																					xtype : "numberfield",
																					name : "fixedAssets.intendTerm",
																					allowNegative : false,
																					allowDecimals : false,
																					id : "intendTerm",
																					width : 230
																				},
																				{
																					xtype : "label",
																					text : "年",
																					width : 10
																				} ]
																	},
																	{
																		layout : "form",
																		xtype : "container",
																		id : "WorkGrossPanel",
																		items : [
																				{
																					xtype : "container",
																					layout : "hbox",
																					height : 28,
																					layoutConfigs : {
																						align : "middle"
																					},
																					defaults : {
																						margins : "0 2 0 0"
																					},
																					items : [
																							{
																								xtype : "label",
																								text : "预使用总工作量*:",
																								width : 103
																							},
																							{
																								xtype : "numberfield",
																								name : "fixedAssets.intendWorkGross",
																								allowNegative : false,
																								id : "intendWorkGross"
																							},
																							{
																								xtype : "label",
																								text : "单位*:"
																							},
																							{
																								xtype : "textfield",
																								name : "fixedAssets.workGrossUnit",
																								id : "workGrossUnit",
																								width : 30
																							} ]
																				},
																				{
																					fieldLabel : "默认周期工作量",
																					xtype : "numberfield",
																					allowNegative : false,
																					name : "fixedAssets.defPerWorkGross",
																					id : "defPerWorkGross"
																				} ]
																	},
																	{
																		xtype : "container",
																		layout : "hbox",
																		height : 28,
																		layoutConfigs : {
																			align : "middle"
																		},
																		defaults : {
																			margins : "0 2 0 0"
																		},
																		items : [
																				{
																					xtype : "label",
																					text : "残值率*",
																					width : 103
																				},
																				{
																					xtype : "numberfield",
																					name : "fixedAssets.remainValRate",
																					allowNegative : false,
																					decimalPrecision : 2,
																					id : "remainValRate",
																					width : 230,
																					allowBlank : false
																				},
																				{
																					xtype : "label",
																					text : "%",
																					width : 10
																				} ]
																	},
																	{
																		fieldLabel : "资产值*",
																		name : "fixedAssets.assetValue",
																		id : "assetValue",
																		allowBlank : false,
																		allowNegative : false,
																		decimalPrecision : 2,
																		xtype : "numberfield"
																	},
																	{
																		fieldLabel : "资产当前值*",
																		name : "fixedAssets.assetCurValue",
																		id : "assetCurValue",
																		allowBlank : false,
																		allowNegative : false,
																		decimalPrecision : 2,
																		xtype : "numberfield"
																	} ]
														},
														{
															layout : "form",
															title : "扩展信息",
															width : 300,
															id : "assetsFormPanel",
															height : 190,
															bodyStyle : "padding:4px 4px 4px 4px",
															defaults : {
																anchor : "98%,98%"
															},
															defaultType : "textfield",
															items : [
																	{
																		fieldLabel : "规格型号",
																		name : "fixedAssets.model",
																		id : "model"
																	},
																	{
																		fieldLabel : "制造厂商",
																		name : "fixedAssets.manufacturer",
																		id : "manufacturer"
																	},
																	{
																		fieldLabel : "出厂日期",
																		name : "fixedAssets.manuDate",
																		format : "Y-m-d",
																		id : "manuDate",
																		xtype : "datefield"
																	},
																	{
																		fieldLabel : "备注",
																		name : "fixedAssets.notes",
																		id : "notes",
																		height : 100,
																		xtype : "textarea"
																	} ]
														} ]
											} ]
								});
						if (this.assetsId != null
								&& this.assetsId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/admin/getFixedAssets.do?assetsId="
														+ this.assetsId,
												waitMsg : "正在载入数据...",
												success : function(b, c) {
													var d = c.result.data.depreType.calMethod;
													if (d == "2") {
														Ext
																.getCmp(
																		"calDepreMethod")
																.setValue(d);
														Ext
																.getCmp(
																		"WorkGrossPanel")
																.show();
														Ext
																.getCmp(
																		"intendTermContainer")
																.hide();
													} else {
														Ext
																.getCmp(
																		"calDepreMethod")
																.setValue(d);
														Ext
																.getCmp(
																		"WorkGrossPanel")
																.hide();
														Ext
																.getCmp(
																		"intendTermContainer")
																.show();
													}
													Ext
															.getCmp("buyDate")
															.setValue(
																	new Date(
																			getDateFromFormat(
																					c.result.data.buyDate,
																					"yyyy-MM-dd HH:mm:ss")));
													Ext
															.getCmp(
																	"startDepre")
															.setValue(
																	new Date(
																			getDateFromFormat(
																					c.result.data.startDepre,
																					"yyyy-MM-dd HH:mm:ss")));
													var a = c.result.data.manuDate;
													if (a != null) {
														Ext
																.getCmp(
																		"manuDate")
																.setValue(
																		new Date(
																				getDateFromFormat(
																						a,
																						"yyyy-MM-dd HH:mm:ss")));
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
										var b = Ext.getCmp("FixedAssetsForm");
										var f = Ext.getCmp("calDepreMethod")
												.getValue();
										var a = false;
										if (f != "" && f != null
												&& f != "undefind") {
											if (f == 2) {
												var c = Ext.getCmp(
														"intendWorkGross")
														.getValue();
												var d = Ext.getCmp(
														"workGrossUnit")
														.getValue();
												if (c != null
														&& c != "undefind"
														&& c != "" && d != null
														&& d != "undefind"
														&& d != "") {
													a = true;
												}
											} else {
												var e = Ext
														.getCmp("intendTerm")
														.getValue();
												if (e != null
														&& e != "undefind"
														&& e != "") {
													a = true;
												}
											}
											if (b.getForm().isValid() && a) {
												b
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
																						"FixedAssetsGrid")
																				.getStore()
																				.reload();
																		Ext
																				.getCmp(
																						"FixedAssetsFormWin")
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
																						"FixedAssetsFormWin")
																				.close();
																	}
																});
											} else {
												Ext.ux.Toast.msg("操作信息",
														"带*项为必填！！");
											}
										}
									}
								},
								{
									text : "取消",
									iconCls : "btn-cancel",
									handler : function() {
										Ext.getCmp("FixedAssetsFormWin")
												.close();
									}
								} ];
					}
				});