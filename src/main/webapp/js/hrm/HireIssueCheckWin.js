var HireIssueCheckWin = function(d) {
	var b = new Ext.Panel( {
		id : "HireIssueCheckPanel",
		border : false,
		autoLoad : {
			url : __ctxPath + "/hrm/loadHireIssue.do?hireId=" + d
		}
	});
	var a = new Ext.FormPanel( {
		id : "HireIssueCheckFormPanel",
		url : __ctxPath + "/hrm/checkHireIssue.do",
		formId : "HireIssueCheckFormPanelId",
		layout : "form",
		frame : false,
		border : false,
		items : [ {
			xtype : "hidden",
			name : "hireId",
			value : d
		}, {
			fieldLabel : "审核意见",
			xtype : "textarea",
			name : "checkOpinion",
			anchor : "98%"
		}, {
			name : "status",
			xtype : "hidden",
			id : "HireIssueCheckPanel.status"
		} ]
	});
	var c = new Ext.Window( {
		title : "招聘信息",
		id : "HireIssueChckeWin",
		iconCls : "menu-hireIssue",
		width : 500,
		x : 300,
		y : 50,
		autoHeight : true,
		buttonAlign : "center",
		items : [ b, a ],
		buttons : [ {
			text : "通过审核",
			iconCls : "btn-empProfile-pass",
			id : "PassHireIssueSb",
			handler : function() {
				Ext.getCmp("HireIssueCheckPanel.status").setValue(1);
				var e = Ext.getCmp("HireIssueCheckFormPanel");
				if (e.getForm().isValid()) {
					e.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(f, g) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("HireIssueGrid").getStore().reload();
							c.close();
						},
						failure : function(f, g) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
							c.close();
						}
					});
				}
			}
		}, {
			text : "不通过审核",
			iconCls : "btn-empProfile-notpass",
			id : "NotPassHireIssueSb",
			handler : function() {
				Ext.getCmp("HireIssueCheckPanel.status").setValue(2);
				var e = Ext.getCmp("HireIssueCheckFormPanel");
				if (e.getForm().isValid()) {
					e.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(f, g) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("HireIssueGrid").getStore().reload();
							c.close();
						},
						failure : function(f, g) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
							c.close();
						}
					});
				}
			}
		}, {
			text : "关闭",
			iconCls : "btn-close",
			handler : function() {
				c.close();
			}
		} ]
	});
	c.show();
};