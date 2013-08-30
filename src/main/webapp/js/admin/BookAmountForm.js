var BookAmountForm = function(c) {
	this.bookId = c;
	var a = this.setup();
	var b = new Ext.Window(
			{
				id : "BookAmountFormWin",
				title : "增加图书数量",
				autoHeight : true,
				width : 300,
				modal : true,
				layout : "anchor",
				plain : true,
				bodyStyle : "padding:5px;",
				buttonAlign : "center",
				items : [ this.setup() ],
				buttons : [
						{
							text : "保存",
							iconCls : "btn-save",
							handler : function() {
								var d = Ext.getCmp("bookAmountForm");
								if (d.getForm().isValid()) {
									d
											.getForm()
											.submit(
													{
														method : "post",
														waitMsg : "正在提交数据...",
														success : function(e, f) {
															Ext.ux.Toast.msg(
																	"操作信息",
																	"成功保存信息！");
															b.close();
															Ext
																	.getCmp(
																			"amount")
																	.setValue(
																			f.result.data.amount);
															Ext
																	.getCmp(
																			"leftAmount")
																	.setValue(
																			f.result.data.leftAmount);
															Ext.Ajax
																	.request( {
																		url : __ctxPath
																				+ "/admin/getSnBookSn.do?bookId="
																				+ c,
																		method : "post",
																		success : function(
																				h) {
																			var g = Ext.util.JSON
																					.decode(h.responseText);
																			var k = Ext
																					.getCmp("bookSnPanel");
																			k.body
																					.update("");
																			for ( var j = 0; j < g.length; j++) {
																				Ext.DomHelper
																						.append(
																								k.body,
																								"<div>"
																										+ g[j][1]
																										+ '&nbsp;<img class="img-delete" src="'
																										+ __ctxPath
																										+ '/images/system/delete.gif" alt="删除该本图书" onclick="#"/></div>');
																			}
																		}
																	});
														},
														failure : function(e, f) {
															Ext.MessageBox
																	.show( {
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
BookAmountForm.prototype.setup = function() {
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/admin/updateAmountBook.do?bookId=" + this.bookId,
		layout : "form",
		id : "bookAmountForm",
		frame : true,
		defaults : {
			anchor : "95%,95%"
		},
		formId : "BookAmountFormId",
		defaultType : "textfield",
		items : [ {
			fieldLabel : "增加的图书数量",
			name : "addAmount",
			id : "addAmount",
			allowBlank : false,
			blankText : "增加的图书数量不能为空"
		} ]
	});
	return a;
};