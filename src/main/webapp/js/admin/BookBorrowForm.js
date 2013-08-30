var BookBorrowForm = function(b, d) {
	this.recordId = b;
	this.bookId = d;
	var a = this.setup();
	var c = new Ext.Window(
			{
				id : "BookBorrowFormWin",
				title : "图书借出详细记录",
				iconCls : "menu-book-borrow",
				width : 520,
				height : 220,
				modal : true,
				layout : "fit",
				buttonAlign : "center",
				items : [ this.setup() ],
				buttons : [
						{
							text : "借出图书",
							iconCls : "btn-save",
							handler : function() {
								var e = Ext.getCmp("BookBorrowForm");
								if (e.getForm().isValid()) {
									e
											.getForm()
											.submit(
													{
														method : "post",
														waitMsg : "正在提交数据...",
														success : function(f, h) {
															Ext.ux.Toast.msg(
																	"操作信息",
																	"成功保存信息！");
															var g = Ext
																	.getCmp("BookBorrowGrid");
															if (g == null) {
																Ext
																		.getCmp(
																				"BookGrid")
																		.getStore()
																		.reload();
															} else {
																Ext
																		.getCmp(
																				"BookBorrowGrid")
																		.getStore()
																		.reload();
															}
															c.close();
														},
														failure : function(f, g) {
															Ext.MessageBox
																	.show( {
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
BookBorrowForm.prototype.setup = function() {
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/admin/saveBorrowBookBorRet.do",
		layout : "form",
		bodyStyle : "padding:5px;",
		id : "BookBorrowForm",
		border : false,
		defaultType : "textfield",
		items : [ {
			name : "bookBorRet.recordId",
			id : "recordId",
			xtype : "hidden",
			value : this.recordId == null ? "" : this.recordId
		}, {
			name : "bookId",
			id : "bookId",
			xtype : "hidden",
			value : this.bookId == null ? "" : this.bookId
		}, {
			name : "bookBorRet.bookSnId",
			id : "bookSnId",
			xtype : "hidden"
		}, {
			xtype : "container",
			height : 32,
			layout : "hbox",
			layoutConfigs : {
				align : "middle"
			},
			defaults : {
				margins : "0 2 0 2"
			},
			items : [ {
				xtype : "label",
				text : "借出图书名称:",
				width : 100
			}, {
				xtype : "textfield",
				name : "bookBorRet.bookName",
				id : "bookName",
				allowBlank : false,
				blankText : "借出图书名称不能为空",
				readOnly : true,
				width : 200
			}, {
				xtype : "button",
				text : "选择图书",
				iconCls : "menu-book-manage",
				handler : function() {
					BookSelector.getView(function(e, d) {
						var c = Ext.getCmp("bookName");
						c.setValue(d);
						var b = Ext.getCmp("borrowIsbn").getStore();
						b.reload( {
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
				handler : function() {
					var b = Ext.getCmp("bookName");
					b.setValue("");
				}
			} ]
		}, {
			xtype : "container",
			height : 32,
			layout : "hbox",
			layoutConfigs : {
				align : "middle"
			},
			defaults : {
				margins : "0 2 0 2"
			},
			items : [ {
				xtype : "label",
				text : "借出图书的ISBN:",
				width : 100
			}, {
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
				store : new Ext.data.SimpleStore( {
					url : __ctxPath + "/admin/getBorrowSnBookSn.do",
					fields : [ "bookSnId", "bookSn" ]
				}),
				displayField : "bookSn",
				valueField : "bookSnId",
				listeners : {
					select : function(d, b, c) {
						Ext.getCmp("bookSnId").setValue(d.value);
					},
					focus : function(e, b, c) {
						var d = Ext.getCmp("bookName").getValue();
						if (d == "") {
							Ext.ux.Toast.msg("提示信息", "请先选择图书");
						}
					}
				}
			} ]
		}, {
			xtype : "container",
			height : 32,
			layout : "hbox",
			layoutConfigs : {
				align : "middle"
			},
			defaults : {
				margins : "0 2 0 2"
			},
			items : [ {
				xtype : "label",
				text : "借阅人:",
				width : 100
			}, {
				name : "bookBorRet.fullname",
				id : "fullname",
				xtype : "textfield",
				readOnly : true,
				allowBlank : false,
				width : 200
			}, {
				xtype : "button",
				text : "选择人员",
				iconCls : "btn-user-sel",
				handler : function() {
					UserSelector.getView(function(c, b) {
						Ext.getCmp("fullname").setValue(b);
					}, true).show();
				}
			}, {
				xtype : "button",
				text : "清除记录",
				iconCls : "reset",
				handler : function() {
					Ext.getCmp("fullname").setValue("");
				}
			} ]
		}, {
			fieldLabel : "应还时间",
			name : "bookBorRet.returnTime",
			id : "returnTime",
			xtype : "datefield",
			format : "Y-m-d",
			editable : false,
			allowBlank : false,
			width : 200,
			blankText : "应还时间不能为空"
		} ]
	});
	if (this.recordId != null && this.recordId != "undefined") {
		a.getForm().load(
				{
					deferredRender : false,
					url : __ctxPath + "/admin/getBookBorRet.do?recordId="
							+ this.recordId,
					waitMsg : "正在载入数据...",
					success : function(d, e) {
						var c = e.result.data.returnTime;
						var b = Ext.getCmp("returnTime");
						b.setValue(new Date(getDateFromFormat(c,
								"yyyy-MM-dd HH:mm:ss")));
					},
					failure : function(b, c) {
					}
				});
	}
	return a;
};