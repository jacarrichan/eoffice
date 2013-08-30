var BookForm = function(c) {
	this.bookId = c;
	var a = this.setup();
	var b = new Ext.Window( {
		id : "BookFormWin",
		title : "图书详细信息",
		iconCls : "menu-book-manage",
		width : 500,
		height : 320,
		modal : true,
		layout : "fit",
		buttonAlign : "center",
		items : [ this.setup() ],
		buttons : [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var d = Ext.getCmp("BookForm");
				if (d.getForm().isValid()) {
					d.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("BookGrid").getStore().reload();
							b.close();
						},
						failure : function(e, f) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : "ext-mb-error"
							});
							b.close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				b.close();
			}
		} ]
	});
	b.show();
};
BookForm.prototype.setup = function() {
	var b = __ctxPath + "/admin/treeBookType.do?method=1";
	var c = new TreeSelector("bookTypeSelect", b, "图书类别", "typeId");
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/admin/saveBook.do",
		layout : "form",
		id : "BookForm",
		border : false,
		defaults : {
			anchor : "100%,100%"
		},
		bodyStyle : "padding:5px;",
		defaultType : "textfield",
		items : [ {
			name : "book.bookId",
			id : "bookId",
			xtype : "hidden",
			value : this.bookId == null ? "" : this.bookId
		}, {
			name : "book.typeId",
			id : "typeId",
			xtype : "hidden"
		}, {
			name : "book.leftAmount",
			id : "leftAmount",
			xtype : "hidden"
		}, {
			xtype : "label"
		}, c, {
			fieldLabel : "书名",
			name : "book.bookName",
			id : "bookName",
			allowBlank : false,
			blankText : "书名不能为空"
		}, {
			fieldLabel : "作者",
			name : "book.author",
			id : "author",
			allowBlank : false,
			blankText : "作者不能为空"
		}, {
			fieldLabel : "ISBN号",
			name : "book.isbn",
			id : "isbn",
			allowBlank : false,
			blankText : "ISBN号不能为空"
		}, {
			fieldLabel : "出版社",
			name : "book.publisher",
			id : "publisher"
		}, {
			fieldLabel : "图书价格",
			name : "book.price",
			id : "price",
			xtype : "numberfield",
			nanText : "只能输入数字",
			allowBlank : false,
			blankText : "价格不能为空"
		}, {
			fieldLabel : "存放地点",
			name : "book.location",
			id : "location",
			allowBlank : false,
			blankText : "存放地点不能为空"
		}, {
			xtype : "container",
			layout : "hbox",
			layoutConfigs : {
				align : "middle"
			},
			defaults : {
				margins : "0 2 0 0"
			},
			id : "amoutContainer",
			defaultType : "textfield",
			height : 26,
			items : [ {
				xtype : "label",
				text : "数量:",
				width : 105
			}, {
				name : "book.amount",
				id : "amount",
				xtype : "numberfield",
				allowDecimals : false,
				nanText : "只能输入数字",
				allowBlank : false,
				blankText : "数量不能为空",
				minValue : 1,
				minText : "图书数量必须大于0"
			}, {
				xtype : "button",
				id : "bookAmoutButton",
				text : "增加数量",
				iconCls : "btn-select",
				width : 80,
				handler : function() {
					var d = Ext.getCmp("bookId").getValue();
					new BookAmountForm(d);
				}
			} ]
		}, {
			xtype : "container",
			layout : "hbox",
			layoutConfigs : {
				align : "middle"
			},
			defaults : {
				margins : "0 2 0 0"
			},
			defaultType : "textfield",
			height : 26,
			items : [ {
				xtype : "label",
				text : "所属部门:",
				width : 105
			}, {
				name : "book.department",
				id : "department",
				allowBlank : false,
				blankText : "所属部门不能为空"
			}, {
				xtype : "button",
				text : "选择",
				iconCls : "btn-select",
				width : 80,
				handler : function() {
					DepSelector.getView(function(e, f) {
						var d = Ext.getCmp("department");
						d.setValue(f);
					}, true).show();
				}
			} ]
		}, {
			xtype : "container",
			id : "bookSnContainer",
			layout : "form",
			items : [ {
				id : "bookSnPanel",
				fieldLabel : "图书标签",
				xtype : "panel",
				frame : false,
				height : 80,
				autoScroll : true,
				html : ""
			} ]
		} ]
	});
	if (this.bookId != null && this.bookId != "undefined") {
		a
				.getForm()
				.load(
						{
							deferredRender : false,
							url : __ctxPath + "/admin/getBook.do?bookId="
									+ this.bookId,
							waitMsg : "正在载入数据...",
							success : function(i, j) {
								var h = Ext.getCmp("typeId");
								var e = j.result.data.bookType.typeName;
								var g = Ext.getCmp("bookTypeSelect");
								g.setValue(e);
								var k = j.result.data.bookId;
								var d = j.result.data.leftAmount;
								var f = Ext.getCmp("leftAmount");
								f.setValue(d);
								Ext.Ajax
										.request( {
											url : __ctxPath
													+ "/admin/getSnBookSn.do?bookId="
													+ k,
											method : "post",
											success : function(m) {
												var l = Ext.util.JSON
														.decode(m.responseText);
												var o = Ext
														.getCmp("bookSnPanel");
												for ( var n = 0; n < l.length; n++) {
													Ext.DomHelper
															.append(
																	o.body,
																	"<div>"
																			+ l[n][1]
																			+ '&nbsp;<img class="img-delete" src="'
																			+ __ctxPath
																			+ '/images/system/delete.gif" alt="删除该本图书" onclick="removeBookSn(this,'
																			+ l[n][0]
																			+ ","
																			+ k
																			+ ')"/></div>');
												}
											}
										});
							},
							failure : function(d, e) {
							}
						});
	}
	return a;
};
function removeBookSn(c, a, b) {
	Ext.Msg
			.confirm(
					"信息确认",
					"把借阅归还记录一起删除，您确认要删除该书吗？",
					function(d) {
						if (d == "yes") {
							var e = Ext.get(c.parentNode);
							e.remove();
							Ext.Ajax
									.request( {
										url : __ctxPath
												+ "/admin/multiDelBookSn.do",
										params : {
											ids : a
										},
										method : "post",
										success : function() {
											Ext.ux.Toast.msg("信息提示",
													"成功删除所选记录！");
											Ext.Ajax
													.request( {
														url : __ctxPath
																+ "/admin/updateAmountAndLeftAmountBook.do?bookId="
																+ b,
														method : "post",
														success : function(g) {
															var f = Ext.util.JSON
																	.decode(g.responseText);
															Ext
																	.getCmp(
																			"amount")
																	.setValue(
																			f.data.amount);
															Ext
																	.getCmp(
																			"leftAmount")
																	.setValue(
																			f.data.leftAmount);
														}
													});
										}
									});
						}
					});
}