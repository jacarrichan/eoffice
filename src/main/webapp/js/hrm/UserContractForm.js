UserContractForm = Ext
		.extend(
				Ext.Window,
				{
					formPanel : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						UserContractForm.superclass.constructor.call(this, {
							id : "UserContractFormWin",
							autoScroll : false,
							layout : {
								type : "vbox",
								align : "stretch"
							},
							items : [ this.formPanel, this.detailPanel ],
							region : "center",
							maximizable : true,
							modal : true,
							height : 520,
							width : 650,
							title : "合同管理信息",
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
											+ "/hrm/saveUserContract.do",
									id : "UserContractForm",
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
													fieldLabel : "合同编号",
													name : "userContract.contractNo",
													id : "contractNo",
													allowBlank : false,
													blankText : "合同编号不可为空!"
												},
												{
													fieldLabel : "签约职员ID",
													name : "userContract.userId",
													id : "userId",
													xtype : "hidden"
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
													xtype : "compositefield",
													fieldLabel : "期限形式",
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
													fieldLabel : "合同类型",
													items : [
															{
																width : 200,
																xtype : "textfield",
																name : "userContract.contractType",
																id : "contractType"
															},
															{
																xtype : "displayfield",
																value : "签约日期:",
																width : 64
															},
															{
																width : 200,
																name : "userContract.signDate",
																id : "signDate",
																xtype : "datefield",
																format : "Y-m-d",
																allowBlank : false,
																blankText : "签约日期不可为空!"
															} ]
												},
												{
													width : 350,
													fieldLabel : "违约责任",
													name : "userContract.breakBurden",
													id : "breakBurden"
												},
												{
													width : 350,
													fieldLabel : "其他事宜",
													name : "userContract.otherItems",
													id : "otherItems"
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
													xtype : "container",
													autoHeight : true,
													layout : "column",
													autoWidth : true,
													defaultType : "label",
													style : "padding-left:0px;",
													items : [
															{
																text : "合同附件:",
																width : 64,
																style : "padding-left:0px;padding-top:3px;"
															},
															{
																xtype : "hidden",
																id : "contractAttachId",
																name : "fileIds"
															},
															{
																xtype : "panel",
																id : "contractAttachPanel",
																width : 280,
																height : 60,
																frame : false,
																autoScroll : true,
																style : "padding-left:0px;padding-top:0px;",
																html : ""
															},
															{
																xtype : "button",
																text : "添加附件",
																iconCls : "menu-attachment",
																handler : function() {
																	var d = App
																			.createUploadDialog( {
																				file_cat : "hrm",
																				callback : function(
																						h) {
																					var e = Ext
																							.getCmp("contractAttachId");
																					var g = Ext
																							.getCmp("contractAttachPanel");
																					for ( var f = 0; f < h.length; f++) {
																						if (e
																								.getValue() != "") {
																							e
																									.setValue(e
																											.getValue()
																											+ ",");
																						}
																						e
																								.setValue(e
																										.getValue()
																										+ h[f].fileId);
																						Ext.DomHelper
																								.append(
																										g.body,
																										'<span><a href="#" onclick="FileAttachDetail.show('
																												+ h[f].fileId
																												+ ')">'
																												+ h[f].filename
																												+ '</a> <img class="img-delete" src="'
																												+ __ctxPath
																												+ '/images/system/delete.gif" onclick="removeContractAttach(this,'
																												+ h[f].fileId
																												+ ')"/>&nbsp;|&nbsp;</span>');
																					}
																				}
																			});
																	d
																			.show(this);
																}
															} ]
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
									blankText : "记录名称不可为空!"
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
								text : "添加记录",
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
							id : "detailPanel",
							autoScroll : true,
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
						var b = this.detailStore;
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
												success : function(j, k) {
													var g = Ext.util.JSON
															.decode(k.response.responseText);
													var e = k.result.data.contractAttachs;
													var h = Ext
															.getCmp("contractAttachPanel");
													var d = Ext
															.getCmp("contractAttachId");
													for ( var f = 0; f < e.length; f++) {
														if (d.getValue() != "") {
															d.setValue(d
																	.getValue()
																	+ ",");
														}
														d.setValue(d.getValue()
																+ e[f].fileId);
														Ext.DomHelper
																.append(
																		h.body,
																		'<span><a href="#" onclick="FileAttachDetail.show('
																				+ e[f].fileId
																				+ ')">'
																				+ e[f].fileName
																				+ '</a><img class="img-delete" src="'
																				+ __ctxPath
																				+ '/images/system/delete.gif" onclick="removeContractAttach(this,'
																				+ e[f].fileId
																				+ ')"/>&nbsp;|&nbsp;</span>');
													}
													b
															.loadData(g.data.contractEvents);
													Ext.getCmp("contractNo")
															.getEl().dom.readOnly = true;
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
								success : function(g, j) {
									Ext.ux.Toast.msg("操作信息", "成功保存信息！");
									var h = Ext.getCmp("UserContractGrid");
									if (h != null) {
										h.getStore().reload();
									}
									var f = Ext.getCmp("ContractEventGrid");
									if (f != null) {
										f.getStore().reload();
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
								}
							});
						}
					}
				});
function removeContractAttach(e, a) {
	var b = Ext.getCmp("contractAttachId");
	var d = b.getValue();
	if (d.indexOf(",") < 0) {
		b.setValue("");
	} else {
		d = d.replace("," + a, "").replace(a + ",", "");
		b.setValue(d);
	}
	var c = Ext.get(e.parentNode);
	c.remove();
}