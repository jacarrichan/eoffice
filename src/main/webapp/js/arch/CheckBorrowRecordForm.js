CheckBorrowRecordForm = Ext
		.extend(
				Ext.Window,
				{
					returnStatusName : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						CheckBorrowRecordForm.superclass.constructor
								.call(
										this,
										{
											id : "CheckBorrowRecordFormWin",
											items : [ this.formPanel,
													this.filePanel ],
											modal : true,
											height : 600,
											width : 800,
											maximizable : true,
											title : "借阅编号:" + this.borrowNum,
											buttonAlign : "center",
											listeners : {
												"afterlayout" : function(d) {
													var c = d.getInnerHeight();
													var b = d.formPanel
															.getInnerHeight();
													d.filePanel.setHeight(c - b
															+ 50);
													var e = d.returnStatus;
													d.returnStatusName = "";
													switch (e) {
													case 0:
														d.returnStatusName = "申请";
														break;
													case 1:
														d.returnStatusName = "通过";
														d.okButton
																.setVisible(false);
														d.cancelButton
																.setVisible(false);
														break;
													case -1:
														d.returnStatusName = "驳回";
														d.okButton
																.setVisible(false);
														d.cancelButton
																.setVisible(false);
														break;
													case 2:
														d.returnStatusName = "归还";
														d.okButton
																.setVisible(false);
														d.cancelButton
																.setVisible(false);
														break;
													}
													d.formPanel
															.getForm()
															.findField(
																	"CheckBorrowRecordForm.borrowRecord.returnStatusName")
															.setValue(
																	d.returnStatusName);
												}
											},
											buttons : [ this.okButton,
													this.cancelButton, {
														text : "关闭",
														iconCls : "btn-close",
														scope : this,
														handler : this.clo
													} ]
										});
					},
					initUIComponents : function() {
						this.okButton = new Ext.Button( {
							text : "通过",
							iconCls : "btn-ok",
							scope : this,
							handler : function() {
								this.doCheck(1);
							}
						});
						this.cancelButton = new Ext.Button( {
							text : "驳回",
							iconCls : "btn-cancel",
							scope : this,
							handler : function() {
								this.doCheck(-1);
							}
						});
						this.formPanel = new Ext.FormPanel(
								{
									id : "CheckBorrowRecordForm",
									layout : "column",
									bodyStyle : "padding:10px",
									border : true,
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
													xtype : "displayfield",
													name : "borrowRecord.borrowNum"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅人",
													xtype : "displayfield",
													name : "borrowRecord.checkUserName"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅日期",
													xtype : "displayfield",
													name : "borrowRecord.borrowDate"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "应还日期",
													name : "borrowRecord.returnDate",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅方式",
													name : "borrowRecord.borrowType",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅目的",
													name : "borrowRecord.borrowReason",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 0.33,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "借阅状态",
													id : "CheckBorrowRecordForm.borrowRecord.returnStatusName",
													xtype : "displayfield"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "备注",
													xtype : "displayfield",
													name : "borrowRecord.borrowRemark"
												}
											},
											{
												columnWidth : 1,
												layout : "form",
												labelWidth : 100,
												items : {
													fieldLabel : "登记人ID",
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
													xtype : "datefield",
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
													id : "CheckBorrowRecordForm.borrowRecord.returnStatus",
													name : "borrowRecord.returnStatus",
													xtype : "numberfield",
													value : 0
												}
											} ]
								});
						this.filePanel = new MyBorrowFilePanel( {
							id : "check" + this.borrowNum,
							title : "借阅清单",
							recordId : this.recordId,
							borrowNum : this.borrowNum,
							showFlag : "check"
						});
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
						}
					},
					clo : function() {
						this.close();
					},
					doCheck : function(a) {
						if (this.formPanel.getForm().isValid()) {
							this.formPanel
									.getForm()
									.findField(
											"CheckBorrowRecordForm.borrowRecord.returnStatus")
									.setValue(a);
							this.formPanel.getForm().submit(
									{
										clientValidation : true,
										url : __ctxPath
												+ "/arch/checkBorrowRecord.do",
										success : function(b, c) {
											Ext.getCmp(
													"CheckBorrowRecordFormWin")
													.close();
											Ext.getCmp("CheckBorrowRecordGrid")
													.getStore().reload();
										},
										failure : function(b, c) {
											Ext.MessageBox.show( {
												title : "操作信息",
												msg : "信息保存出错，请联系管理员！",
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.ERROR
											});
										}
									});
						}
					}
				});