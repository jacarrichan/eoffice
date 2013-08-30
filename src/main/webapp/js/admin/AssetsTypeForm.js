var AssetsTypeForm = function(a) {
	this.assetsTypeId = a;
	var b = this.setup();
	var c = new Ext.Window(
			{
				id : "AssetsTypeFormWin",
				title : "资产分类详细信息",
				iconCls : "assets-type",
				width : 300,
				height : 130,
				modal : true,
				layout : "fit",
				buttonAlign : "center",
				items : [ this.setup() ],
				buttons : [
						{
							text : "保存",
							iconCls : "btn-save",
							handler : function() {
								var d = Ext.getCmp("AssetsTypeForm");
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
															Ext
																	.getCmp("leftFixedAssetsManagePanel").root
																	.reload();
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
AssetsTypeForm.prototype.setup = function() {
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/admin/saveAssetsType.do",
		layout : "form",
		id : "AssetsTypeForm",
		bodyStyle : "padding:5px;",
		border : false,
		defaults : {
			width : 400,
			anchor : "98%,98%"
		},
		formId : "AssetsTypeFormId",
		defaultType : "textfield",
		items : [ {
			name : "assetsType.assetsTypeId",
			id : "assetsTypeId",
			xtype : "hidden",
			value : this.assetsTypeId == null ? "" : this.assetsTypeId
		}, {
			fieldLabel : "分类名称",
			name : "assetsType.typeName",
			id : "typeName",
			allowBlank : false
		} ]
	});
	if (this.assetsTypeId != null && this.assetsTypeId != "undefined") {
		a.getForm().load(
				{
					deferredRender : false,
					url : __ctxPath + "/admin/getAssetsType.do?assetsTypeId="
							+ this.assetsTypeId,
					waitMsg : "正在载入数据...",
					success : function(b, c) {
					},
					failure : function(b, c) {
					}
				});
	}
	return a;
};