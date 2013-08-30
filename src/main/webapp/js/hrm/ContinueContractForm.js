ContinueContractForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						ContinueContractForm.superclass.constructor
								.call(this,
										{
											id : "ContinueContractFormWin",
											layout : {
												type : "vbox",
												align : "stretch"
											},
											items : [ this.formPanel,
													this.detailPanel ],
											region : "center",
											modal : true,
											height : 520,
											width : 650,
											autoScroll : false,
											title : "合同续约",
											buttonAlign : "center",
											buttons : this.buttons
										});
					},
					initUIComponents : function() {
						this.formPanel = new Ext.FormPanel(
								{
									region : "north",
									layout : "form",
									bodyStyle : "padding:10px 10px 10px 10px",
									border : false,
									url : __ctxPath
											+ "/hrm/RenewUserContract.do",
									id : "ContinueContractForm",
									autoHeight : true,
									defaultType : "textfield",
									items : [ {
										xtype : "fieldset",
										title : "合同信息",
										layout : "form",
										labelWidth : 60,
										defaultType : "textfield",
										defaults : {
											anchor : "98%,98%"
										},
										items : [
												{
													name : "userContract.contractId",
													id : "contractId",
													xtype : "hidden",
													value : this.contractId == null ? ""
															: this.contractId
												},
												{
													width : 300,
													fieldLabel : "合同编号",
													name : "userContract.contractNo",
													id : "contractNo",
													allowBlank : false,
													blankText : "合同类型不可为空!"
												},
												{
													xtype : "compositefield",
													fieldLabel : "签约人",
													items : [
															{
																xtype : "textfield",
																name : "userContract.fullname",
																id : "fullname",
																allowBlank : false,
																blankText : "姓名不能为空！",
																width : 420,
																readOnly : true
															},
															{
																xtype : "button",
																id : "UserContractSelectEmp",
																text : "选择员工",
																iconCls : "btn-mail_recipient",
																handler : function() {
																	UserSelector
																			.getView(
																					function(
																							e,
																							d) {
																						Ext
																								.getCmp(
																										"fullname")
																								.setValue(
																										d);
																						Ext
																								.getCmp(
																										"userId")
																								.setValue(
																										e);
																						Ext.Ajax
																								.request( {
																									url : __ctxPath
																											+ "/system/getAppUser.do",
																									params : {
																										userId : e
																									},
																									method : "post"
																								});
																					},
																					true)
																			.show();
																}
															} ]
												},
												{
													fieldLabel : "合同类型",
													name : "userContract.contractType",
													id : "contractType",
													allowBlank : false,
													blankText : "合同类型不可为空!"
												},
												{
													fieldLabel : "签约职员ID",
													name : "userContract.userId",
													id : "userId",
													xtype : "hidden"
												},
												{
													xtype : "compositefield",
													fieldLabel : "续约时间",
													items : [
															{
																width : 200,
																name : "userContract.timeLimit",
																id : "timeLimit",
																xtype : "combo",
																mode : "local",
																editable : true,
																triggerAction : "all",
																store : [],
																listeners : {
																	focus : function(
																			e) {
																		var d = Ext
																				.getCmp(
																						"timeLimit")
																				.getStore();
																		if (d
																				.getCount() <= 0) {
																			Ext.Ajax
																					.request( {
																						url : __ctxPath
																								+ "/system/loadDictionary.do",
																						method : "post",
																						params : {
																							itemName : "期限形式"
																						},
																						success : function(
																								g) {
																							var f = Ext.util.JSON
																									.decode(g.responseText);
																							d
																									.loadData(f);
																						}
																					});
																		}
																	}
																}
															},
															{
																xtype : "displayfield",
																value : "合同状态:",
																width : 64
															},
															{
																width : 200,
																hiddenName : "userContract.status",
																id : "status",
																xtype : "combo",
																mode : "local",
																editable : true,
																triggerAction : "all",
																store : [
																		[ "0",
																				"草稿" ],
																		[ "1",
																				"有效" ],
																		[ "2",
																				"终止" ] ]
															} ]
												},
												{
													xtype : "compositefield",
													fieldLabel : "竞业条款",
													items : [
															{
																width : 200,
																hiddenName : "userContract.isCompeted",
																id : "isCompeted",
																xtype : "combo",
																mode : "local",
																editable : true,
																triggerAction : "all",
																store : [
																		[ "0",
																				"无" ],
																		[ "1",
																				"有" ] ]
															},
															{
																xtype : "displayfield",
																value : "保密协议:",
																width : 64
															},
															{
																width : 200,
																hiddenName : "userContract.isSecret",
																id : "isSecret",
																xtype : "combo",
																mode : "local",
																editable : true,
																triggerAction : "all",
																store : [
																		[ "0",
																				"无" ],
																		[ "1",
																				"有" ] ]
															} ]
												},
												{
													xtype : "compositefield",
													fieldLabel : "有效日期",
													items : [
															{
																xtype : "datefield",
																width : 200,
																format : "Y-m-d",
																editable : false,
																name : "userContract.startDate",
																id : "startDate",
																allowBlank : false,
																blankText : "合同生效日期不可为空!"
															},
															{
																xtype : "displayfield",
																value : "至",
																width : 20
															},
															{
																xtype : "datefield",
																width : 200,
																format : "Y-m-d",
																editable : false,
																name : "userContract.expireDate",
																id : "expireDate",
																allowBlank : false,
																blankText : "合同满约日期不可为空!"
															} ]
												},
												{
													width : 300,
													fieldLabel : "违约责任",
													name : "userContract.breakBurden",
													id : "breakBurden"
												},
												{
													width : 300,
													fieldLabel : "其他事宜",
													name : "userContract.otherItems",
													id : "otherItems"
												} ]
									} ]
								});
						this.detailStore = new Ext.data.JsonStore( {
							fields : [ {
								name : "eventId",
								type : "int"
							}, "userContract", "eventName", "eventDescp",
									"createTime", "creator" ]
						});
						var c = new Ext.grid.CheckboxSelectionModel();
						var a = new Ext.grid.ColumnModel( {
							columns : [ c, new Ext.grid.RowNumberer(), {
								header : "eventId",
								dataIndex : "eventId",
								hidden : true
							}, {
								header : "记录名称",
								dataIndex : "eventName",
								editor : new Ext.form.TextField( {
									allowBlank : false,
									blankText : "事件名称不可为空!"
								})
							}, {
								header : "记录理由",
								dataIndex : "eventDescp",
								editor : new Ext.form.TextField()
							} ],
							defaults : {
								sortable : true,
								menuDisabled : false,
								width : 100
							}
						});
						this.topbar = new Ext.Toolbar( {
							items : [ {
								xtype : "button",
								iconCls : "btn-add",
								scope : this,
								text : "添加合同记录",
								handler : function() {
									var e = this.detailPanel.getStore();
									var d = e.recordType;
									e.add(new d());
									this.detailPanel.stopEditing();
								}
							} ]
						});
						this.detailPanel = new Ext.grid.EditorGridPanel( {
							title : "合同记录信息",
							region : "center",
							stripeRows : true,
							tbar : this.topbar,
							clicksToEdit : 1,
							store : this.detailStore,
							trackMouseOver : true,
							loadMask : true,
							height : 150,
							cm : a,
							sm : c
						});
						var b = this.defaultStatus;
						if (this.contractId != null
								&& this.contractId != "undefined") {
							this.formPanel
									.getForm()
									.load(
											{
												deferredRender : false,
												url : __ctxPath
														+ "/hrm/getUserContract.do?contractId="
														+ this.contractId,
												waitMsg : "正在载入数据...",
												success : function(e, f) {
													var d = Ext.util.JSON
															.decode(f.response.responseText);
													b
															.loadDate(d.data.contractEvents);
												},
												failure : function(d, e) {
												}
											});
						}
						this.buttons = [
								{
									text : "保存",
									iconCls : "btn-save",
									handler : this.save.createCallback(
											this.formPanel, this)
								},
								{
									text : "重置",
									iconCls : "btn-reset",
									handler : this.reset
											.createCallback(this.formPanel)
								}, {
									text : "取消",
									iconCls : "btn-cancel",
									handler : this.cancel.createCallback(this)
								} ];
					},
					reset : function(a) {
						a.getForm().reset();
					},
					cancel : function(a) {
						a.close();
					},
					save : function(a, d) {
						var c = d.detailPanel.getStore();
						var e = [];
						if (c.getCount() > 0) {
							for (i = 0, cnt = c.getCount(); i < cnt; i++) {
								var b = c.getAt(i);
								e.push(b.data);
							}
						} else {
							Ext.ux.Toast.msg("提示信息", "请添加合同记录信息！");
							return;
						}
						if (a.getForm().isValid()) {
							a.getForm().submit( {
								method : "POST",
								waitMsg : "正在提交数据...",
								params : {
									details : Ext.encode(e)
								},
								success : function(f, h) {
									Ext.ux.Toast.msg("操作信息", "成功保存信息！");
									var g = Ext.getCmp("UserContractGrid");
									if (g != null) {
										g.getStore().reload();
									}
									d.close();
								},
								failure : function(f, g) {
									Ext.MessageBox.show( {
										title : "操作信息",
										msg : g.result.msg,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
									d.close();
								}
							});
						}
					}
				});