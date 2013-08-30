MyBorrowFilePanel = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						MyBorrowFilePanel.superclass.constructor.call(this, {
							buttonAlign : "center",
							region : "center",
							layout : "card",
							activeItem : 0,
							border : false,
							defaults : {
								anchor : "98%,98%"
							},
							items : [ this.fileTypePanel, this.fileListPanel ],
							listeners : {
								"afterlayout" : function(b) {
								}
							}
						});
					},
					initUIComponents : function() {
						this.returnButton = new Ext.Button( {
							text : "返回",
							iconCls : "btn-reset",
							scope : this,
							handler : this.activeItem_0
						});
						this.fileListPanel = new MyBorrowFileListPanel( {
							borrowNum : this.borrowNum,
							forceLayout : true,
							frame : false,
							height : 400,
							border : false,
							defaults : {
								anchor : "96%,96%"
							}
						});
						this.fileListPanel.searchPanel
								.addButton(this.returnButton);
						this.fileTypePanel = new MyBorrowFileTypePanel( {
							forceLayout : true,
							height : 200,
							border : true,
							defaults : {
								anchor : "96%,96%"
							}
						}).show();
						this.fileTypePanel.store
								.on(
										"beforeload",
										function(a) {
											if (this.recordId) {
												a.baseParams = {
													"Q_borrowRecord.recordId_L_EQ" : this.recordId,
													start : 0,
													limit : 25
												};
											} else {
												return false;
											}
										}, this);
						this.fileTypePanel.store.load();
						this.fileTypePanel.rowActions.on("action",
								this.onRowAction, this);
					},
					rowClick : function(b, a, c) {
						var d = this.id;
						b.getSelectionModel().each(function(e) {
							Ext.getCmp(d).activeItem_1(e);
						});
					},
					activeItem_1 : function(a) {
						var c = this.id;
						if (a.data.listType == "全宗") {
							this.getLayout().setActiveItem(1);
							this.fileListPanel.reset();
							this.fileListPanel.afNo.readOnly = true;
							this.fileListPanel.rollNo.readOnly = false;
									this.fileListPanel.afNo.setEditable(false),
									this.fileListPanel.rollNo.setEditable(true),
									this.fileListPanel.afNo
											.setValue(a.data.archFond.archFondId);
							this.fileListPanel.rollNo
									.getStore()
									.load(
											{
												params : {
													"Q_archFond.archFondId_L_EQ" : a.data.archFond.archFondId
												}
											});
							this.fileListPanel.rollNo.setValue("");
							var b = this.fileListPanel.leftPanel
									.findByType("treepanel")[0];
							b.loader = new Ext.tree.TreeLoader(
									{
										baseParams : {
											"Q_archFondId_L_EQ" : a.data.archFond.archFondId
										},
										dataUrl : __ctxPath
												+ "/arch/listRollTreeArchFond.do?sno="
												+ new Date(),
										requestMethod : "GET"
									});
							b.root.reload();
							this.fileListPanel.search();
							Ext.getCmp(c).setTitle(
									"借阅清单>>全宗号:" + a.data.archFond.afNo);
							Ext.getCmp(c).doLayout();
						} else {
							if (a.data.listType == "案卷") {
								this.getLayout().setActiveItem(1);
								this.fileListPanel.reset();
								this.fileListPanel.afNo.readOnly = true;
								this.fileListPanel.rollNo.readOnly = true;
										this.fileListPanel.afNo
												.setEditable(false),
										this.fileListPanel.rollNo
												.setEditable(false),
										this.fileListPanel.afNo
												.setValue(a.data.archFond.archFondId);
								this.fileListPanel.rollNo.getStore().load();
								this.fileListPanel.rollNo
										.setValue(a.data.archRoll.rollNo);
								var b = this.fileListPanel.leftPanel
										.findByType("treepanel")[0];
								b.loader = new Ext.tree.TreeLoader(
										{
											baseParams : {
												"Q_archFondId_L_EQ" : a.data.archFond.archFondId,
												"Q_rollNo_S_LK" : a.data.archRoll.rollNo
											},
											dataUrl : __ctxPath
													+ "/arch/listRollTreeArchFond.do?sno="
													+ new Date(),
											requestMethod : "POST"
										});
								b.root.reload();
								this.fileListPanel.search();
								Ext.getCmp(c).setTitle(
										"借阅清单>>案卷号:" + a.data.archRoll.rollNo);
								Ext.getCmp(c).doLayout();
							} else {
								if (a.data.listType == "文件") {
									new MyBorrowFileViewWindow( {
										rollFileId : a.data.rollFile.rollFileId
									}).show();
								}
							}
						}
						this.doLayout(true, true);
					},
					activeItem_0 : function() {
						this.getLayout().setActiveItem(0);
						if (this.showFlag) {
							if (this.showFlag == "check") {
								Ext.getCmp(this.id).setTitle("借阅清单");
							} else {
								if (this.showFlag == "view") {
									Ext.getCmp(this.id).setTitle(
											"我的借阅>>编号：" + this.borrowNum);
								}
							}
						}
						Ext.getCmp(this.id).doLayout();
					},
					viewFile : function(a) {
						Ext.Ajax
								.request( {
									url : __ctxPath
											+ "/arch/listRollFileList.do",
									method : "POST",
									success : function(b, d) {
										var e = Ext.decode(b.responseText);
										var c = [];
										for (i = 0; i < e.result.length; i++) {
											c
													.push( {
														fileName : e.result[i].fileAttach.fileName,
														filePath : e.result[i].fileAttach.filePath
													});
										}
										new ViewFileWindow( {
											viewConfig : c,
											startIndex : 0
										}).show();
									},
									failure : function(b, c) {
									},
									params : {
										"Q_rollFile.rollFileId_L_EQ" : a.data.rollFile.rollFileId,
										dir : "ASC",
										sort : "sn"
									}
								});
					},
					onRowAction : function(c, a, d, e, b) {
						switch (d) {
						case "btn-showDetail":
							this.activeItem_1(a);
							break;
						case "btn-readdocument":
							this.viewFile.call(this, a);
							break;
						default:
							break;
						}
					}
				});