var BookReturnForm = function(b) {
	this.recordId = b;
	var a = this.setup();
	var c = new Ext.Window( {
		id : "BookReturnFormWin",
		title : "图书归还详细记录",
		iconCls : "menu-book-return",
		width : 520,
		height : 240,
		modal : true,
		layout : "fit",
		buttonAlign : "center",
		items : [ this.setup() ],
		buttons : [ {
			text : "归还图书",
			iconCls : "btn-save",
			handler : function() {
				var d = Ext.getCmp("BookReturnForm");
				if (d.getForm().isValid()) {
					d.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(e, g) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							var f = Ext.getCmp("BookBorrowGrid");
							if (f != null) {
								f.getStore().reload();
							}
							var h = Ext.getCmp("BookReturnGrid");
							if (h != null) {
								h.getStore().reload();
							}
							c.close();
						},
						failure : function(e, f) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : "ext-mb-error"
							});
							c.close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				c.close();
			}
		} ]
	});
	c.show();
};
BookReturnForm.prototype.setup = function() {
	var a = new Ext.FormPanel(
			{
				url : __ctxPath + "/admin/saveReturnBookBorRet.do",
				layout : "form",
				id : "BookReturnForm",
				bodyStyle : "padding:5px;",
				border : false,
				defaultType : "textfield",
				items : [
						{
							name : "bookBorRet.recordId",
							id : "recordId",
							xtype : "hidden",
							value : this.recordId == null ? "" : this.recordId
						},
						{
							name : "bookBorRet.bookSnId",
							id : "bookSnId",
							xtype : "hidden"
						},
						{
							xtype : "container",
							height : 32,
							layout : "hbox",
							layoutConfigs : {
								align : "middle"
							},
							defaults : {
								margins : "0 2 0 2"
							},
							items : [
									{
										xtype : "label",
										text : "借出图书名称:",
										width : 100
									},
									{
										xtype : "textfield",
										name : "bookBorRet.bookName",
										id : "bookName",
										allowBlank : false,
										blankText : "借出图书名称不能为空",
										readOnly : true,
										width : 200
									},
									{
										xtype : "button",
										id : "ReturnFormButtonSel",
										text : "选择图书",
										iconCls : "menu-book-manage",
										handler : function() {
											BookSelector
													.getView(
															function(e, d) {
																var c = Ext
																		.getCmp("bookName");
																c.setValue(d);
																var b = Ext
																		.getCmp(
																				"borrowIsbn")
																		.getStore();
																b
																		.reload( {
																			params : {
																				bookId : e
																			}
																		});
															}, true).show();
										}
									}, {
										xtype : "button",
										text : " 清除记录",
										iconCls : "reset",
										id : "ReturnFormButtonCle",
										handler : function() {
											var b = Ext.getCmp("bookName");
											b.setValue("");
										}
									} ]
						},
						{
							xtype : "container",
							height : 32,
							layout : "hbox",
							layoutConfigs : {
								align : "middle"
							},
							defaults : {
								margins : "0 2 0 2"
							},
							items : [
									{
										xtype : "label",
										text : "借出图书的ISBN:",
										width : 100
									},
									{
										name : "bookBorRet.borrowIsbn",
										id : "borrowIsbn",
										allowBlank : false,
										blankText : "借出图书的ISBN不能为空",
										width : 200,
										maxHeight : 200,
										xtype : "combo",
										mode : "local",
										editable : false,
										triggerAction : "all",
										emptyText : "请选择图书系列",
										store : new Ext.data.SimpleStore(
												{
													url : __ctxPath
															+ "/admin/getReturnSnBookSn.do",
													fields : [ "bookSnId",
															"bookSn" ]
												}),
										displayField : "bookSn",
										valueField : "bookSnId",
										listeners : {
											select : function(e, c, d) {
												Ext.getCmp("bookSnId")
														.setValue(e.value);
												var b = e.value;
												Ext.Ajax
														.request( {
															url : __ctxPath
																	+ "/admin/getBorRetTimeBookBorRet.do?bookSnId="
																	+ b,
															success : function(
																	h) {
																var o = Ext.util.JSON
																		.decode(h.responseText).data;
																var m = o.borrowTime;
																var i = Ext
																		.getCmp("borrowTime");
																var f = o.returnTime;
																var l = Ext
																		.getCmp("returnTime");
																var g = o.recordId;
																var k = Ext
																		.getCmp("recordId");
																var n = o.fullname;
																var j = Ext
																		.getCmp("fullname");
																k.setValue(g);
																i.setValue(m);
																l.setValue(f);
																j.setValue(n);
															}
														});
											},
											focus : function(e, b, c) {
												var d = Ext.getCmp("bookName")
														.getValue();
												if (d == "") {
													Ext.ux.Toast.msg("提示信息",
															"请先选择图书！");
												}
											}
										}
									} ]
						}, {
							fieldLabel : "借阅人",
							xtype : "textfield",
							id : "fullname",
							name : "bookBorRet.fullname",
							readOnly : true,
							allowBlank : false,
							width : 200
						}, {
							fieldLabel : "借出时间",
							name : "bookBorRet.borrowTime",
							id : "borrowTime",
							readOnly : true,
							width : 200,
							allowBlank : false,
							blankText : "借出时间不能为空"
						}, {
							fieldLabel : "应还时间",
							name : "bookBorRet.returnTime",
							id : "returnTime",
							readOnly : true,
							width : 200,
							allowBlank : false,
							blankText : "应还时间不能为空"
						} ]
			});
	if (this.recordId != null && this.recordId != "undefined") {
		a.getForm().load(
				{
					deferredRender : false,
					url : __ctxPath + "/admin/getBookBorRet.do?recordId="
							+ this.recordId,
					method : "post",
					waitMsg : "正在载入数据...",
					success : function(f, g) {
						var c = g.result.data.borrowTime;
						var e = Ext.getCmp("borrowTime");
						var d = g.result.data.returnTime;
						var b = Ext.getCmp("returnTime");
						e.setValue(c);
						b.setValue(d);
					},
					failure : function(b, c) {
					}
				});
	}
	return a;
};