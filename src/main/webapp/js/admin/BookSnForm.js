var BookSnForm = function(a) {
	this.bookSnId = a;
	var b = this.setup();
	var c = new Ext.Window( {
		id : "BookSnFormWin",
		title : "图书Sn详细信息",
		width : 500,
		height : 420,
		modal : true,
		layout : "anchor",
		plain : true,
		bodyStyle : "padding:5px;",
		buttonAlign : "center",
		items : [ this.setup() ],
		buttons : [ {
			text : "保存",
			handler : function() {
				var d = Ext.getCmp("BookSnForm");
				if (d.getForm().isValid()) {
					d.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("BookSnGrid").getStore().reload();
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
			handler : function() {
				c.close();
			}
		} ]
	});
	c.show();
};
BookSnForm.prototype.setup = function() {
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/admin/saveBookSn.do",
		layout : "form",
		id : "BookSnForm",
		frame : true,
		defaults : {
			widht : 400,
			anchor : "100%,100%"
		},
		formId : "BookSnFormId",
		defaultType : "textfield",
		items : [
				{
					name : "bookSn.bookSnId",
					id : "bookSnId",
					xtype : "hidden",
					value : this.bookSnId == null ? "" : this.bookSnId
				},
				{
					fieldLabel : "图书ID",
					name : "bookSn.book.bookId",
					id : "bookId",
					allowBlank : false,
					blankText : "图书ID不能为空"
				},
				{
					fieldLabel : "图书SN号",
					name : "bookSn.bookSN",
					id : "bookSN",
					allowBlank : false,
					blankText : "图书SN不能为空"
				},
				{
					xtype : "label",
					text : "借阅状态:"
				},
				{
					hiddenName : "bookSn.status",
					id : "status",
					xtype : "combo",
					mode : "local",
					editable : false,
					triggerAction : "all",
					emptyText : "请选择",
					store : [ [ "0", "未借出" ], [ "1", "已借出" ], [ "2", "预订" ],
							[ "3", "注销" ] ]
				} ]
	});
	if (this.bookSnId != null && this.bookSnId != "undefined") {
		a.getForm().load( {
			deferredRender : false,
			url : __ctxPath + "/admin/getBookSn.do?bookSnId=" + this.bookSnId,
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