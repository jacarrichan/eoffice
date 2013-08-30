var BookTypeForm = function(b) {
	this.typeId = b;
	var a = this.setup();
	var c = new Ext.Window(
			{
				id : "BookTypeFormWin",
				title : "图书类别详细信息",
				iconCls : "menu-book-type",
				height : 100,
				width : 300,
				modal : true,
				layout : "fit",
				border : false,
				buttonAlign : "center",
				items : [ this.setup() ],
				buttons : [
						{
							text : "保存",
							iconCls : "btn-save",
							handler : function() {
								var d = Ext.getCmp("bookTypeForm");
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
															if (Ext
																	.getCmp("BookTypeGrid") != null) {
																Ext
																		.getCmp(
																				"BookTypeGrid")
																		.getStore()
																		.reload();
															}
															if (Ext
																	.getCmp("leftBookManagePanel") != null) {
																Ext
																		.getCmp("leftBookManagePanel").root
																		.reload();
															}
															c.close();
														},
														failure : function(e, f) {
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
BookTypeForm.prototype.setup = function() {
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/admin/saveBookType.do",
		layout : "form",
		id : "bookTypeForm",
		bodyStyle : "padding:5px;",
		frame : false,
		defaults : {
			anchor : "95%,95%"
		},
		formId : "BookTypeFormId",
		defaultType : "textfield",
		items : [ {
			name : "bookType.typeId",
			id : "typeId",
			xtype : "hidden",
			value : this.typeId == null ? "" : this.typeId
		}, {
			fieldLabel : "图书类别名称",
			name : "bookType.typeName",
			id : "typeName",
			allowBlank : false,
			blankText : "图书类别不能为空"
		} ]
	});
	if (this.typeId != null && this.typeId != "undefined") {
		a.getForm().load( {
			deferredRender : false,
			url : __ctxPath + "/admin/getBookType.do?typeId=" + this.typeId,
			method : "post",
			waitMsg : "正在载入数据...",
			success : function(b, c) {
			},
			failure : function(b, c) {
			}
		});
	}
	return a;
};