Ext.ns("BorrowFileListView");
BorrowFileListView = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						BorrowFileListView.superclass.constructor.call(this, {
							layout : "fit",
							border : true,
							items : [ this.gridPanel ],
							frame : false,
							listeners : {
								afterlayout : function(b) {
									b.store.on("load", function(d, c) {
										var e = [];
										for (i = 0; i < c.length; i++) {
											e.push(c[i].data);
										}
										b.pageingStore.loadData( {
											"success" : true,
											"totalProperty" : e.length,
											"result" : e
										});
										b.gridPanel.getBottomToolbar()
												.moveFirst();
									});
								}
							}
						});
					},
					initUIComponents : function() {
						var b = [ {
							name : "listId",
							type : "int"
						}, "recordId", "listType", "archFond", "afNo",
								"afName", "archRoll", "rollNo", "rolllName",
								"rollFile", "fileNo", "fileName" ];
						this.pageingStore = new Ext.ux.data.JsonPagingStore( {
							root : "result",
							totalProperty : "totalCounts",
							remoteSort : false,
							idProperty : "listId",
							fields : b
						});
						this.store = new Ext.data.Store( {
							proxy : new Ext.data.HttpProxy( {
								url : __ctxPath
										+ "/arch/listBorrowFileList.do?sno="
										+ new Date(),
								method : "GET"
							}),
							root : "result",
							reader : new Ext.data.JsonReader( {
								root : "result",
								fields : b
							})
						});
						var c = new Ext.grid.CheckboxSelectionModel();
						var a = new Ext.grid.ColumnModel( {
							columns : [ c, new Ext.grid.RowNumberer(), {
								header : "listId",
								dataIndex : "listId",
								hidden : true
							}, {
								header : "借阅单位",
								dataIndex : "listType"
							}, {
								header : "全宗ID",
								dataIndex : "archFond",
								hidden : true,
								renderer : function(d) {
									if (d) {
										return d.archFondId;
									}
								}
							}, {
								header : "全宗号",
								dataIndex : "archFond",
								renderer : function(d) {
									if (d) {
										return d.afNo;
									}
								}
							}, {
								header : "全宗名",
								dataIndex : "archFond",
								renderer : function(d) {
									if (d) {
										return d.afName;
									}
								}
							}, {
								header : "案卷ID",
								hidden : true,
								dataIndex : "archRoll",
								renderer : function(d) {
									if (d) {
										return d.rollId;
									}
								}
							}, {
								header : "案卷号",
								dataIndex : "archRoll",
								renderer : function(d) {
									if (d) {
										return d.rollNo;
									}
								}
							}, {
								header : "案卷名",
								dataIndex : "archRoll",
								renderer : function(d) {
									if (d) {
										return d.rolllName;
									}
								}
							}, {
								header : "文件ID",
								hidden : true,
								dataIndex : "rollFile",
								renderer : function(d) {
									if (d) {
										return d.rollFileId;
									}
								}
							}, {
								header : "文件号",
								dataIndex : "fileNo"
							}, {
								header : "文件题名",
								dataIndex : "fileName"
							} ],
							defaults : {
								sortable : true,
								menuDisabled : false
							}
						});
						this.topbar = new Ext.Toolbar(
								{
									items : [
											{
												xtype : "tbtext",
												text : "借阅清单"
											},
											{
												xtype : "tbseparator"
											},
											{
												iconCls : "btn-add",
												text : "全宗",
												hidden : (this.returnStatus == 1) ? true
														: false,
												xtype : "button",
												scope : this,
												handler : this.createFond
											},
											{
												iconCls : "btn-add",
												text : "案卷",
												hidden : (this.returnStatus == 1) ? true
														: false,
												xtype : "button",
												scope : this,
												handler : this.createRoll
											},
											{
												iconCls : "btn-add",
												text : "文件",
												hidden : (this.returnStatus == 1) ? true
														: false,
												xtype : "button",
												scope : this,
												handler : this.createFile
											},
											{
												iconCls : "btn-del",
												text : "删除",
												hidden : (this.returnStatus == 1) ? true
														: false,
												xtype : "button",
												scope : this,
												handler : this.removeSelRs
											} ]
								});
						this.gridPanel = new Ext.grid.GridPanel( {
							frame : false,
							border : false,
							tbar : this.topbar,
							store : this.pageingStore,
							cm : a,
							sm : c,
							viewConfig : {
								forceFit : true,
								autoFill : true
							},
							bbar : new HT.PagingBar( {
								store : this.pageingStore
							}),
							listeners : {}
						});
					},
					createFond : function() {
						var c = this.pageingStore;
						var a = this.gridPanel;
						var b = this.removeByIds;
						new SelectFondWindow(
								{
									callBack : function(d) {
										if (c.allData) {
											var g = Array();
											for (i = 0; i < d.length; i++) {
												for (j = 0; j < c.allData.items.length; j++) {
													if (c.allData.items[j].data.archRoll
															&& c.allData.items[j].data.archFond
															&& d[i].data.archFondId == c.allData.items[j].data.archFond.archFondId
															&& c.allData.items[j].data.listType == "案卷") {
														if (c.allData.items[j].data.listId != ""
																&& c.allData.items[j].data.listId != null) {
															g
																	.push(c.allData.items[j].data.listId);
														}
														c.removeAt(j);
														c.commitChanges();
														j--;
														Ext.ux.Toast.msg(
																"操作信息",
																"所选案卷重覆！");
													}
												}
												for (j = 0; j < c.allData.items.length; j++) {
													if (c.allData.items[j].data.rollFile
															&& c.allData.items[j].data.archFond
															&& d[i].data.archFondId == c.allData.items[j].data.archFond.archFondId
															&& c.allData.items[j].data.listType == "文件") {
														if (c.allData.items[j].data.listId != ""
																&& c.allData.items[j].data.listId != null) {
															g
																	.push(c.allData.items[j].data.listId);
														}
														c.removeAt(j);
														c.commitChanges();
														j--;
														Ext.ux.Toast.msg(
																"操作信息",
																"所选文件重覆！");
													}
												}
												for (j = 0; j < c.allData.items.length; j++) {
													if (c.allData.items[j].data.archFond
															&& d[i].data.archFondId == c.allData.items[j].data.archFond.archFondId
															&& c.allData.items[j].data.listType == "全宗") {
														d.splice(i, 1);
														i--;
														Ext.ux.Toast.msg(
																"操作信息",
																"所选全宗重覆！");
														break;
													}
												}
											}
											c.commitChanges();
											if (g.length > 0) {
												b(g);
											}
										}
										for (i = 0; i < d.length; i++) {
											var f = new c.recordType();
											f.data = {};
											f.data.listType = "全宗";
											var e = {};
											Ext
													.apply(
															e,
															{
																archFondId : d[i].data.archFondId,
																afNo : d[i].data.afNo,
																afName : d[i].data.afName
															});
											Ext.apply(f.data, {
												archFond : e
											});
											f.data.afNo = d[i].data.afNo;
											f.data.afName = d[i].data.afName;
											c.add(f);
										}
										c.commitChanges();
										a.getBottomToolbar().moveFirst();
										a.getView().refresh();
										a.doLayout(true, true);
									}
								}).show();
					},
					createRoll : function() {
						var c = this.pageingStore;
						var a = this.gridPanel;
						var b = this.removeByIds;
						new SelectRollWindow(
								{
									callBack : function(d) {
										if (c.allData) {
											var g = Array();
											for (i = 0; i < d.length; i++) {
												for (j = 0; j < c.allData.items.length; j++) {
													if (c.allData.items[j].data.rollFile
															&& c.allData.items[j].data.archRoll
															&& d[i].data.rollId == c.allData.items[j].data.archRoll.rollId
															&& c.allData.items[j].data.listType == "文件") {
														if (c.allData.items[j].data.listId != ""
																&& c.allData.items[j].data.listId != null) {
															g
																	.push(c.allData.items[j].data.listId);
														}
														c.removeAt(j);
														c.commitChanges();
														j--;
														Ext.ux.Toast.msg(
																"操作信息",
																"所选文件重覆！");
													}
												}
												for (j = 0; j < c.allData.items.length; j++) {
													if (c.allData.items[j].data.archRoll
															&& d[i].data.rollId == c.allData.items[j].data.archRoll.rollId
															&& c.allData.items[j].data.listType == "案卷") {
														d.splice(i, 1);
														i--;
														Ext.ux.Toast.msg(
																"操作信息",
																"所选案卷重覆！");
														break;
													}
												}
												for (j = 0; j < c.allData.items.length; j++) {
													if (c.allData.items[j].data.archFond
															&& d[i].data.archFond.archFondId == c.allData.items[j].data.archFond.archFondId
															&& c.allData.items[j].data.listType == "全宗") {
														d.splice(i, 1);
														i--;
														Ext.ux.Toast.msg(
																"操作信息",
																"所选案卷重覆！");
														break;
													}
												}
											}
											c.commitChanges();
											if (g.length > 0) {
												b(g);
											}
										}
										for (i = 0; i < d.length; i++) {
											var f = new c.recordType();
											f.data = {};
											f.data.listType = "案卷";
											var e = {};
											Ext
													.apply(
															e,
															{
																archFondId : d[i].data.archFond.archFondId,
																afNo : d[i].data.archFond.afNo,
																afName : d[i].data.archFond.afName
															});
											Ext.apply(f.data, {
												archFond : e
											});
											f.data.afNo = d[i].data.archFond.afNo;
											f.data.afName = d[i].data.archFond.afName;
											var h = {};
											Ext.apply(h, {
												rollId : d[i].data.rollId,
												rollNo : d[i].data.rollNo,
												rolllName : d[i].data.rollNo
											});
											Ext.apply(f.data, {
												archRoll : h
											});
											f.data.rollNo = d[i].data.rollNo;
											f.data.rolllName = d[i].data.rollNo;
											c.add(f);
										}
										c.commitChanges();
										a.getBottomToolbar().moveFirst();
										a.getView().refresh();
										a.doLayout(true, true);
									}
								}).show();
					},
					createFile : function() {
						var b = this.pageingStore;
						var a = this.gridPanel;
						new SelectFileWindow(
								{
									callBack : function(c) {
										if (b.allData) {
											for (i = 0; i < c.length; i++) {
												for (j = 0; j < b.allData.items.length; j++) {
													if (b.allData.items[j].data.rollFile
															&& c[i].data.rollFileId == b.allData.items[j].data.rollFile.rollFileId
															&& b.allData.items[j].data.listType == "文件") {
														c.splice(i, 1);
														i--;
														Ext.ux.Toast.msg(
																"操作信息",
																"所选文件重覆！");
														break;
													}
												}
												for (j = 0; j < b.allData.items.length; j++) {
													if (b.allData.items[j].data.archFond
															&& c[i].data.archRoll.archFond.archFondId == b.allData.items[j].data.archFond.archFondId
															&& b.allData.items[j].data.listType == "全宗") {
														c.splice(i, 1);
														i--;
														Ext.ux.Toast.msg(
																"操作信息",
																"所选案卷重覆！");
														break;
													}
												}
												for (j = 0; j < b.allData.items.length; j++) {
													if (b.allData.items[j].data.archRoll
															&& c[i].data.archRoll.rollId == b.allData.items[j].data.archRoll.rollId
															&& b.allData.items[j].data.listType == "案卷") {
														c.splice(i, 1);
														i--;
														Ext.ux.Toast.msg(
																"操作信息",
																"所选案卷重覆！");
														break;
													}
												}
											}
											b.commitChanges();
										}
										for (i = 0; i < c.length; i++) {
											var e = new b.recordType();
											e.data = {};
											e.data.listType = "文件";
											if (c[i].data.archRoll) {
												var g = {};
												Ext
														.apply(
																g,
																{
																	rollId : c[i].data.archRoll.rollId,
																	rollNo : c[i].data.archRoll.rollNo,
																	rolllName : c[i].data.archRoll.rolllName
																});
												Ext.apply(e.data, {
													archRoll : g
												});
												e.data.rollNo = c[i].data.archRoll.rollNo;
												e.data.rolllName = c[i].data.archRoll.rolllName;
												if (c[i].data.archRoll.archFond) {
													var d = {};
													Ext
															.apply(
																	d,
																	{
																		archFondId : c[i].data.archRoll.archFond.archFondId,
																		afNo : c[i].data.archRoll.archFond.afNo,
																		afName : c[i].data.archRoll.archFond.afName
																	});
													Ext.apply(e.data, {
														archFond : d
													});
													e.data.afNo = c[i].data.archRoll.archFond.afNo;
													e.data.afName = c[i].data.archRoll.archFond.afName;
												}
											}
											var f = {};
											Ext
													.apply(
															f,
															{
																rollFileId : c[i].data.rollFileId
															});
											Ext.apply(e.data, {
												rollFile : f
											});
											e.data.fileNo = c[i].data.fileNo;
											e.data.fileName = c[i].data.fileName;
											b.add(e);
										}
										b.commitChanges();
										a.getBottomToolbar().moveFirst();
										a.getView().refresh();
										a.doLayout(true, true);
									}
								}).show();
					},
					removeByIds : function(a) {
						Ext.Ajax
								.request( {
									url : __ctxPath
											+ "/arch/multiDelBorrowFileList.do",
									params : {
										ids : a
									},
									method : "POST",
									success : function(b, c) {
										Ext.ux.Toast.msg("操作信息", "成功删除该明细！");
									},
									failure : function(b, c) {
										Ext.ux.Toast
												.msg("操作信息", "操作出错，请联系管理员！");
									}
								});
					},
					removeSelRs : function() {
						Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(d) {
							if (d == "yes") {
								var c = this.gridPanel;
								var f = c.getStore();
								var a = c.getSelectionModel().getSelections();
								if (a.length == 0) {
									Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
									return;
								}
								var e = Array();
								for ( var b = 0; b < a.length; b++) {
									if (a[b].data.listId != ""
											&& a[b].data.listId != null) {
										e.push(a[b].data.listId);
										f.remove(a[b]);
									} else {
										f.remove(a[b]);
									}
								}
								if (e.length > 0) {
									this.removeByIds(e);
								}
								c.getBottomToolbar().moveFirst();
							}
						}, this);
					}
				});