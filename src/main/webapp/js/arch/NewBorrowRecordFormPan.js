NewBorrowRecordFormPan = Ext
		.extend(
				Ext.Panel,
				{
					returnStatusName : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						NewBorrowRecordFormPan.superclass.constructor
								.call(
										this,
										{
											id : "NewBorrowRecordFormPan",
											layoutConfig : {
												padding : "5",
												pack : "center",
												align : "middle"
											},
											layout : "form",
											items : [ this.formPanel,
													this.fileListGrid ],
											title : "借阅文件详细信息",
											buttonAlign : "center",
											listeners : {
												"afterlayout" : function(d) {
													var c = d.getInnerHeight();
													var b = d.formPanel
															.getHeight();
													d.fileListGrid.setHeight(c
															- b);
													d.returnStatusName = "申请";
													d.formPanel
															.getForm()
															.findField(
																	"NewBorrowRecordForm.borrowRecord.returnStatusName")
															.setValue(
																	d.returnStatusName);
												}
											},
											buttons : [ this.saveButton,
													this.resetButton, {
														text : "关闭",
														iconCls : "btn-cancel",
														scope : this,
														handler : this.cancel
													} ]
										});
					},
					initUIComponents : function() {
						this.saveButton = new Ext.Button( {
							text : "申请",
							iconCls : "btn-save",
							scope : this,
							handler : function() {
								this.save(0);
							}
						});
						this.resetButton = new Ext.Button( {
							text : "重置",
							iconCls : "btn-reset",
							scope : this,
							handler : this.reset
						});
						this.formPanel = new Ext.FormPanel(
								{
									id : "NewBorrowRecordForm",
									layout : "column",
									bodyStyle : "padding:30px",
									border : true,
									region : "north",
									autoScroll : true,
									defaults : {
										border : false,
										anchor : "98%,98%"
									},
									items : [
											{
												name : "borrowRecord.recordId",
												xtype : "hidden",
												value : this.recordId == null ? ""
														: this.recordId
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅编号",
													allowBlank : false,
													anchor : "97%",
													xtype : "textfield",
													readOnly : true,
													value : new Date()
															.getFullYear()
															+ ""
															+ (new Date()
																	.getMonth() + 1)
															+ ""
															+ new Date()
																	.getDate()
															+ "-"
															+ new Date()
																	.getHours()
															+ new Date()
																	.getMinutes(),
													name : "borrowRecord.borrowNum"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅人",
													anchor : "95%",
													readOnly : true,
													value : curUserInfo.fullname,
													width : 100,
													xtype : "textfield",
													name : "borrowRecord.checkUserName"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅日期",
													anchor : "95%",
													allowBlank : false,
													width : 100,
													xtype : "datefield",
													format : "Y-m-d",
													value : new Date(),
													name : "borrowRecord.borrowDate"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "应还日期",
													allowBlank : false,
													anchor : "95%",
													width : 100,
													name : "borrowRecord.returnDate",
													xtype : "datefield",
													format : "Y-m-d"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅方式",
													anchor : "95%",
													width : 100,
													name : "borrowRecord.borrowType",
													editable : true,
													lazyInit : false,
													forceSelection : false,
													xtype : "diccombo",
													itemName : "借阅方式"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅目的",
													allowBlank : false,
													anchor : "95%",
													width : 100,
													name : "borrowRecord.borrowReason",
													editable : true,
													lazyInit : false,
													forceSelection : false,
													xtype : "diccombo",
													itemName : "借阅目的"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												hidden : true,
												items : {
													fieldLabel : "借阅状态",
													anchor : "95%",
													allowBlank : false,
													width : 100,
													id : "NewBorrowRecordForm.borrowRecord.returnStatusName",
													readOnly : true,
													xtype : "textfield"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "备注",
													allowBlank : true,
													anchor : "97%",
													xtype : "textarea",
													name : "borrowRecord.borrowRemark"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "登记人ID",
													value : curUserInfo.userId,
													width : 100,
													xtype : "hidden",
													name : "borrowRecord.checkUserId"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												hidden : true,
												items : {
													fieldLabel : "登记日期",
													width : 100,
													xtype : "datefield",
													format : "Y-m-d",
													value : new Date(),
													name : "borrowRecord.checkDate"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												hidden : true,
												items : {
													fieldLabel : "借阅状态",
													allowBlank : false,
													width : 100,
													id : "NewBorrowRecordForm.borrowRecord.returnStatus",
													name : "borrowRecord.returnStatus",
													xtype : "numberfield",
													value : 0
												}
											} ]
								});
						this.fileListGrid = new BorrowFileListView( {
							region : "center",
							returnStatus : 0
						});
						this.fileListGrid.store
								.on(
										"beforeload",
										function(a) {
											if (this.recordId) {
												a.baseParams = {
													"Q_borrowRecord.recordId_L_EQ" : this.recordId,
													start : 0,
													limit : 100000
												};
											} else {
												return false;
											}
										}, this);
						this.load();
					},
					load : function() {
						if (this.recordId != null
								&& this.recordId != "undefined") {
							this.formPanel.loadData( {
								url : __ctxPath
										+ "/arch/getBorrowRecord.do?recordId="
										+ this.recordId,
								root : "data",
								preName : "borrowRecord"
							});
							this.fileListGrid.store.load();
						}
					},
					reset : function() {
						this.formPanel.getForm().reset();
					},
					cancel : function() {
						var a = Ext.getCmp("centerTabPanel");
						a.remove(Ext.getCmp("NewBorrowRecordFormPan"));
					},
					save : function() {
						if (this.formPanel.getForm().isValid()) {
							this.formPanel
									.getForm()
									.findField(
											"NewBorrowRecordForm.borrowRecord.returnStatus")
									.setValue(0);
							var d = [];
							var b = this.fileListGrid.pageingStore;
							var c = b.getTotalCount();
							for (i = 0; i < c; i++) {
								var a = b.allData.items[i];
								d.push(a.data);
							}
							if (d.length > 0) {
								this.formPanel
										.getForm()
										.submit(
												{
													clientValidation : true,
													url : __ctxPath
															+ "/arch/saveBorrowRecord.do",
													params : {
														params : Ext.encode(d)
													},
													success : function(f, g) {
														Ext.ux.Toast.msg(
																"操作信息",
																"添加申请成功!");
														var e = Ext
																.getCmp("centerTabPanel");
														e
																.remove(Ext
																		.getCmp("NewBorrowRecordFormPan"));
													},
													failure : function(e, f) {
														Ext.MessageBox
																.show( {
																	title : "操作信息",
																	msg : "信息保存出错，请联系管理员！",
																	buttons : Ext.MessageBox.OK,
																	icon : Ext.MessageBox.ERROR
																});
													}
												});
							} else {
								Ext.ux.Toast.msg("操作信息", "借阅清单不能为空!");
							}
						}
					}
				});