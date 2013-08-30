CustomerForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						CustomerForm.superclass.constructor.call(this, {
							layout : "fit",
							id : "CustomerFormWin",
							title : "客户详细信息",
							iconCls : "menu-customerView",
							width : 600,
							height : 500,
							minWidth : 599,
							minHeight : 499,
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
											+ "/customer/saveCustomer.do",
									layout : "form",
									id : "CustomerForm",
									bodyStyle : "padding:5px;",
									frame : false,
									defaults : {
										widht : 400,
										anchor : "100%,100%"
									},
									formId : "CustomerFormId",
									defaultType : "textfield",
									items : [
											{
												name : "customer.customerId",
												id : "customerId",
												xtype : "hidden",
												value : this.customerId == null ? ""
														: this.customerId
											},
											{
												xtype : "compositefield",
												fieldLabel : "客户编号*",
												items : [
														{
															xtype : "textfield",
															width : 207,
															name : "customer.customerNo",
															id : "customerNo",
															allowBlank : false,
															blankText : "客户编号不能为空!",
															listeners : {
																change : function(
																		c, a, b) {
																	if (a != null
																			&& a != ""
																			&& a != "undefined") {
																		Ext.Ajax
																				.request({
																					url : __ctxPath
																							+ "/customer/checkCustomer.do",
																					params : {
																						customerNo : a
																					},
																					method : "post",
																					success : function(
																							e) {
																						var d = Ext.util.JSON
																								.decode(e.responseText);
																						if (d.pass) {
																							Ext
																									.getCmp("CheckMessage").body
																									.update('<img src="'
																											+ __ctxPath
																											+ '/images/flag/customer/passcheck.png" />可用');
																						} else {
																							Ext
																									.getCmp("CheckMessage").body
																									.update('<img src="'
																											+ __ctxPath
																											+ '/images/flag/customer/invalid.png" />不可用');
																						}
																					},
																					failure : function() {
																					}
																				});
																	}
																}
															}
														},
														{
															id : "getNoButton",
															xtype : "button",
															text : "系统生成",
															iconCls : "btn-system-setting",
															handler : function() {
																Ext.Ajax
																		.request({
																			url : __ctxPath
																					+ "/customer/numberCustomer.do",
																			success : function(
																					b) {
																				var a = Ext.util.JSON
																						.decode(b.responseText);
																				Ext
																						.getCmp(
																								"customerNo")
																						.setValue(
																								a.customerNo);
																				Ext
																						.getCmp("CheckMessage").body
																						.update("");
																			}
																		});
															}
														},
														{
															id : "CheckMessage",
															xtype : "panel",
															border : false,
															style : "padding-top:3px"
														} ]
											},
											{
												fieldLabel : '客户名称<a style="color:red;">*</a>',
												name : "customer.customerName",
												id : "customerName",
												allowBlank : false,
												blankText : "客户名称不能为空!"
											},
											{
												xtype : "compositefield",
												fieldLabel : "客户经理*",
												items : [
														{
															xtype : "textfield",
															width : 270,
															readOnly : true,
															allowBlank : false,
															blankText : "客户经理不能为空!",
															name : "customer.customerManager",
															id : "customerManager"
														},
														{
															xtype : "button",
															text : "选择经理",
															iconCls : "btn-mail_recipient",
															handler : function() {
																UserSelector
																		.getView(
																				function(
																						c,
																						b) {
																					var a = Ext
																							.getCmp("customerManager");
																					a
																							.setValue(b);
																				},
																				true)
																		.show();
															}
														} ]
											},
											{
												fieldLabel : '客户电话<a style="color:red;">*</a>',
												name : "customer.phone",
												xtype : "textfield",
												id : "phone",
												allowBlank : false,
												blankText : "客户电话不能为空!"
											},
											{
												fieldLabel : '所属行业<a style="color:red;">*</a>',
												name : "customer.industryType",
												id : "industryType",
												allowBlank : false,
												blankText : "客户所属行业不能为空!",
												xtype : "combo",
												mode : "local",
												editable : true,
												triggerAction : "all",
												store : this
														.initIndustryStore()
											},
											{
												fieldLabel : '客户来源<a style="color:red;">*</a>',
												hiddenName : "customer.customerSource",
												id : "customerSource",
												xtype : "combo",
												allowBlank : false,
												blankText : "客户来源不能为空!",
												mode : "local",
												editable : true,
												triggerAction : "all",
												store : [ [ "1", "电话访问" ],
														[ "2", "网络" ],
														[ "3", "客户或朋友介绍" ] ]
											},
											{
												fieldLabel : '客户类型<a style="color:red;">*</a>',
												hiddenName : "customer.customerType",
												id : "customerType",
												xtype : "combo",
												mode : "local",
												editable : false,
												triggerAction : "all",
												store : [ [ "1", "正式客户" ],
														[ "2", "重要客户" ],
														[ "3", "潜在客户" ],
														[ "4", "无效客户" ] ],
												value : "1"
											},
											{
												fieldLabel : '访问权限<a style="color:red;">*</a>',
												hiddenName : "customer.rights",
												id : "rights",
												xtype : "combo",
												mode : "local",
												editable : false,
												triggerAction : "all",
												store : [
														[ "1", "客户经理及上级经理有权查" ],
														[ "2", "公开" ],
														[ "3", "共享人员有权查看" ] ],
												value : "1"
											},
											{
												xtype : "compositefield",
												fieldLabel : "所属省",
												items : [
														{
															name : "customer.state",
															id : "state",
															maxHeight : 200,
															width : 127,
															xtype : "combo",
															mode : "local",
															editable : false,
															triggerAction : "all",
															store : new Ext.data.SimpleStore(
																	{
																		autoLoad : true,
																		url : __ctxPath
																				+ "/system/listRegion.do",
																		fields : [
																				"regionId",
																				"regionName" ]
																	}),
															displayField : "regionName",
															valueField : "regionId",
															listeners : {
																select : function(
																		c, a, b) {
																	Ext.Ajax
																			.request({
																				url : __ctxPath
																						+ "/system/listRegion.do",
																				params : {
																					regionId : c.value
																				},
																				method : "post",
																				success : function(
																						e) {
																					var d = Ext.util.JSON
																							.decode(e.responseText);
																					Ext
																							.getCmp(
																									"city")
																							.getStore()
																							.loadData(
																									d);
																					Ext
																							.getCmp(
																									"city")
																							.setValue(
																									"");
																				}
																			});
																}
															}
														},
														{
															xtype : "displayfield",
															value : "所属市:",
															width : 80
														},
														{
															name : "customer.city",
															id : "city",
															maxHeight : 200,
															width : 128,
															xtype : "combo",
															mode : "local",
															editable : false,
															triggerAction : "all",
															store : [],
															displayField : "regionName",
															valueField : "regionId"
														} ]
											},
											{
												xtype : "tabpanel",
												activeTab : 0,
												plain : true,
												height : 195,
												defaultType : "panel",
												bodyStyle : "padding:5px;",
												items : [
														{
															title : "联系方式",
															layout : "form",
															defaultType : "textfield",
															defaults : {
																widht : 400,
																anchor : "100%,100%"
															},
															items : [
																	{
																		fieldLabel : "单位负责人",
																		name : "customer.principal",
																		id : "principal"
																	},
																	{
																		fieldLabel : "传真",
																		name : "customer.fax",
																		xtype : "numberfield",
																		id : "fax"
																	},
																	{
																		fieldLabel : "网址",
																		name : "customer.site",
																		id : "site"
																	},
																	{
																		fieldLabel : "E-mail",
																		name : "customer.email",
																		id : "email",
																		vtype : "email",
																		vtypeText : "邮箱格式不正确!"
																	},
																	{
																		fieldLabel : "邮编",
																		name : "customer.zip",
																		xtype : "numberfield",
																		id : "zip"
																	},
																	{
																		fieldLabel : "地址",
																		name : "customer.address",
																		id : "address"
																	} ]
														},
														{
															title : "公司信息",
															layout : "form",
															defaults : {
																widht : 400,
																anchor : "100%,100%"
															},
															defaultType : "textfield",
															items : [
																	{
																		fieldLabel : "公司规模",
																		hiddenName : "customer.companyScale",
																		id : "companyScale",
																		xtype : "combo",
																		mode : "local",
																		editable : false,
																		triggerAction : "all",
																		store : [
																				[
																						"1",
																						"1-20人" ],
																				[
																						"2",
																						"20-50人" ],
																				[
																						"3",
																						"50-100人" ],
																				[
																						"4",
																						"100-200人" ],
																				[
																						"5",
																						"200-500人" ],
																				[
																						"6",
																						"500-1000人" ],
																				[
																						"7",
																						"1000人以上" ] ]
																	},
																	{
																		xtype : "container",
																		layout : "column",
																		height : 26,
																		items : [
																				{
																					xtype : "label",
																					text : "注册资金:",
																					width : 104
																				},
																				{
																					xtype : "textfield",
																					width : 270,
																					xtype : "numberfield",
																					name : "customer.registerFun",
																					id : "registerFun"
																				},
																				{
																					name : "customer.currencyUnit",
																					width : 70,
																					id : "currencyUnit",
																					xtype : "combo",
																					mode : "local",
																					editable : false,
																					triggerAction : "all",
																					store : [
																							"人民币",
																							"美元" ],
																					value : "人民币"
																				} ]
																	},
																	{
																		xtype : "container",
																		layout : "column",
																		height : 26,
																		items : [
																				{
																					xtype : "label",
																					text : "年营业额:",
																					width : 104
																				},
																				{
																					xtype : "textfield",
																					width : 270,
																					xtype : "numberfield",
																					name : "customer.turnOver",
																					id : "turnOver"
																				},
																				{
																					xtype : "label",
																					text : "(单位同上)"
																				} ]
																	},
																	{
																		fieldLabel : "开户银行",
																		name : "customer.openBank",
																		id : "openBank"
																	},
																	{
																		fieldLabel : "银行账号",
																		xtype : "numberfield",
																		name : "customer.accountsNo",
																		id : "accountsNo"
																	},
																	{
																		fieldLabel : "税号",
																		name : "customer.taxNo",
																		id : "taxNo"
																	} ]
														},
														{
															title : "其它信息",
															layout : "form",
															defaultType : "textarea",
															defaults : {
																widht : 400,
																anchor : "100%,100%"
															},
															items : [
																	{
																		fieldLabel : "公司描述",
																		name : "customer.otherDesc",
																		id : "otherDesc"
																	},
																	{
																		fieldLabel : "备注",
																		name : "customer.notes",
																		id : "notes"
																	} ]
														} ]
											} ]
								});
						if (this.customerId != null
								&& this.customerId != "undefined") {
							Ext.getCmp("getNoButton").hide();
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/customer/getCustomer.do?customerId="
														+ this.customerId,
												waitMsg : "正在载入数据...",
												success : function(a, b) {
													Ext.getCmp("customerNo")
															.getEl().dom.readOnly = true;
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
										var a = Ext.getCmp("CustomerForm");
										if (a.getForm().isValid()) {
											a
													.getForm()
													.submit(
															{
																method : "post",
																waitMsg : "正在提交数据...",
																success : function(
																		b, d) {
																	Ext
																			.getCmp(
																					"CustomerGrid")
																			.getStore()
																			.reload();
																	var c = Ext
																			.getCmp("customerId").value;
																	if (c == null
																			|| c == ""
																			|| c == "undefined") {
																		c = d.result.customerId;
																		Ext.Msg
																				.confirm(
																						"操作信息",
																						"客户信息保存成功！是否为客户增加联系人?",
																						function(
																								e) {
																							if (e == "yes") {
																								CustomerView
																										.addLinkman(c);
																							}
																						});
																	}
																	Ext
																			.getCmp(
																					"CustomerFormWin")
																			.close();
																},
																failure : function(
																		b, c) {
																	Ext.MessageBox
																			.show({
																				title : "操作信息",
																				msg : c.result.msg,
																				buttons : Ext.MessageBox.OK,
																				icon : "ext-mb-error"
																			});
																	if (c.result.rewrite) {
																		Ext
																				.getCmp(
																						"customerNo")
																				.setValue(
																						"");
																	}
																}
															});
										}
									}
								}, {
									text : "取消",
									iconCls : "btn-cancel",
									handler : function() {
										Ext.getCmp("CustomerFormWin").close();
									}
								} ];
					}
				});
CustomerForm.prototype.initIndustryStore = function() {
	var a = [ "照明工业", "电子元器件", "传媒、广电", "安全、防护", "包装", "纸业", "办公、文教", "数码、电脑",
			"电工电气", "纺织、皮革", "服装", "服饰", "机械及行业设备", "五金、工具", "化工", "精细化学品",
			"橡塑", "环保", "仪器仪表", "家居用品", "家用电器", "建筑、建材", "交通运输", "礼品、工艺品、饰品",
			"能源", "农业", "汽摩及配件", "食品、饮料", "通信产品", "玩具", "冶金矿产", "医药、保养", "印刷",
			"运动、休闲", "商务服务", "项目合作", "二手设备转让", "加工", "代理", "库存积压" ];
	return a;
};