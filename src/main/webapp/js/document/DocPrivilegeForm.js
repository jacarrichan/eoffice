var DocPrivilegeForm = function(c) {
	this.privilegeId = c;
	var a = this.setup();
	var b = new Ext.Window( {
		id : "DocPrivilegeFormWin",
		title : "文档权限详细信息",
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
				var d = Ext.getCmp("DocPrivilegeForm");
				if (d.getForm().isValid()) {
					d.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("DocPrivilegeGrid").getStore().reload();
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
			handler : function() {
				b.close();
			}
		} ]
	});
	b.show();
};
DocPrivilegeForm.prototype.setup = function() {
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/document/saveDocPrivilege.do",
		layout : "form",
		id : "DocPrivilegeForm",
		frame : true,
		defaults : {
			widht : 400,
			anchor : "100%,100%"
		},
		formId : "DocPrivilegeFormId",
		defaultType : "textfield",
		items : [ {
			name : "docPrivilege.privilegeId",
			id : "privilegeId",
			xtype : "hidden",
			value : this.privilegeId == null ? "" : this.privilegeId
		}, {
			fieldLabel : "",
			name : "docPrivilege.folderId",
			id : "folderId"
		}, {
			fieldLabel : "",
			name : "docPrivilege.docId",
			id : "docId"
		}, {
			fieldLabel : "权限",
			name : "docPrivilege.rights",
			id : "rights"
		}, {
			fieldLabel : "",
			name : "docPrivilege.udrId",
			id : "udrId"
		}, {
			fieldLabel : "",
			name : "docPrivilege.udrName",
			id : "udrName"
		}, {
			fieldLabel : "1=user",
			name : "docPrivilege.flag",
			id : "flag"
		} ]
	});
	if (this.privilegeId != null && this.privilegeId != "undefined") {
		a.getForm().load(
				{
					deferredRender : false,
					url : __ctxPath
							+ "/document/getDocPrivilege.do?privilegeId="
							+ this.privilegeId,
					waitMsg : "正在载入数据...",
					success : function(b, c) {
					},
					failure : function(b, c) {
						Ext.ux.Toast.msg("编辑", "载入失败");
					}
				});
	}
	return a;
};