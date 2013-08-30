var HireIssueForm = function(b) {
	this.hireId = b;
	var a = this.setup();
	var c = new Ext.Window( {
		id : "HireIssueFormWin",
		title : "招聘信息详细信息",
		width : 550,
		iconCls : "menu-hireIssue",
		height : 480,
		modal : true,
		layout : "anchor",
		plain : true,
		border : false,
		bodyStyle : "padding:5px;",
		buttonAlign : "center",
		items : [ this.setup() ],
		buttons : [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var d = Ext.getCmp("HireIssueForm");
				if (d.getForm().isValid()) {
					d.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("HireIssueGrid").getStore().reload();
							c.close();
						},
						failure : function(e, f) {
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
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				c.close();
			}
		} ]
	});
	c.show();
};
HireIssueForm.prototype.setup = function() {
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/hrm/saveHireIssue.do",
		layout : "form",
		id : "HireIssueForm",
		frame : false,
		border : false,
		defaults : {
			width : 500,
			anchor : "98%,98%"
		},
		formId : "HireIssueFormId",
		defaultType : "textfield",
		reader : new Ext.data.JsonReader( {
			root : "data"
		}, [ {
			name : "HireIssueForm.hireId",
			mapping : "hireId"
		}, {
			name : "HireIssueForm.regFullname",
			mapping : "regFullname"
		}, {
			name : "HireIssueForm.regDate",
			mapping : "regDate"
		}, {
			name : "HireIssueForm.modifyFullname",
			mapping : "modifyFullname"
		}, {
			name : "HireIssueForm.modifyDate",
			mapping : "modifyDate"
		}, {
			name : "HireIssueForm.checkFullname",
			mapping : "checkFullname"
		}, {
			name : "HireIssueForm.checkOpinion",
			mapping : "checkOpinion"
		}, {
			name : "HireIssueForm.checkDate",
			mapping : "checkDate"
		}, {
			name : "HireIssueForm.status",
			mapping : "status"
		}, {
			name : "HireIssueForm.startDate",
			mapping : "startDate"
		}, {
			name : "HireIssueForm.endDate",
			mapping : "endDate"
		}, {
			name : "HireIssueForm.memo",
			mapping : "memo"
		}, {
			name : "HireIssueForm.title",
			mapping : "title"
		}, {
			name : "HireIssueForm.hireCount",
			mapping : "hireCount"
		}, {
			name : "HireIssueForm.jobName",
			mapping : "jobName"
		}, {
			name : "HireIssueForm.jobCondition",
			mapping : "jobCondition"
		} ]),
		items : [ {
			name : "hireIssue.hireId",
			id : "HireIssueForm.hireId",
			xtype : "hidden",
			value : this.hireId == null ? "" : this.hireId
		}, {
			xtype : "hidden",
			name : "hireIssue.regFullname",
			id : "HireIssueForm.regFullname"
		}, {
			xtype : "hidden",
			name : "hireIssue.regDate",
			id : "HireIssueForm.regDate"
		}, {
			xtype : "hidden",
			name : "hireIssue.modifyFullname",
			id : "HireIssueForm.modifyFullname"
		}, {
			xtype : "hidden",
			name : "hireIssue.modifyDate",
			id : "HireIssueForm.modifyDate"
		}, {
			xtype : "hidden",
			name : "hireIssue.checkFullname",
			id : "HireIssueForm.checkFullname"
		}, {
			xtype : "hidden",
			name : "hireIssue.checkOpinion",
			id : "HireIssueForm.checkOpinion"
		}, {
			xtype : "hidden",
			name : "hireIssue.checkDate",
			id : "HireIssueForm.checkDate"
		}, {
			xtype : "hidden",
			name : "hireIssue.status",
			id : "HireIssueForm.status"
		}, {
			fieldLabel : "标题",
			name : "hireIssue.title",
			id : "HireIssueForm.title",
			allowBlank : false
		}, {
			fieldLabel : "开始时间",
			name : "hireIssue.startDate",
			id : "HireIssueForm.startDate",
			xtype : "datefield",
			format : "Y-m-d",
			allowBlank : false
		}, {
			fieldLabel : "结束时间",
			name : "hireIssue.endDate",
			id : "HireIssueForm.endDate",
			xtype : "datefield",
			format : "Y-m-d",
			allowBlank : false
		}, {
			fieldLabel : "招聘人数",
			name : "hireIssue.hireCount",
			id : "HireIssueForm.hireCount",
			xtype : "numberfield",
			allowBlank : false
		}, {
			fieldLabel : "招聘职位",
			name : "hireIssue.jobName",
			id : "HireIssueForm.jobName",
			allowBlank : false
		}, {
			fieldLabel : "招聘条件",
			name : "hireIssue.jobCondition",
			id : "HireIssueForm.jobCondition",
			xtype : "htmleditor",
			allowBlank : false
		}, {
			fieldLabel : "备注",
			name : "hireIssue.memo",
			id : "HireIssueForm.memo",
			xtype : "textarea"
		} ]
	});
	if (this.hireId != null && this.hireId != "undefined") {
		a
				.getForm()
				.load(
						{
							deferredRender : false,
							url : __ctxPath + "/hrm/getHireIssue.do?hireId="
									+ this.hireId,
							waitMsg : "正在载入数据...",
							success : function(d, e) {
								var c = Ext.util.JSON
										.decode(e.response.responseText).data[0];
								var b = c.startDate;
								var f = c.endDate;
								Ext.getCmp("HireIssueForm.startDate").setValue(
										new Date(getDateFromFormat(b,
												"yyyy-MM-dd HH:mm:ss")));
								Ext.getCmp("HireIssueForm.endDate").setValue(
										new Date(getDateFromFormat(f,
												"yyyy-MM-dd HH:mm:ss")));
							},
							failure : function(b, c) {
							}
						});
	}
	return a;
};