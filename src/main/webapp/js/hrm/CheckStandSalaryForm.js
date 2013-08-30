var CheckStandSalaryForm = function(c) {
	this.standardId = c;
	var a = this.setup(c);
	var b = new Ext.Window( {
		id : "CheckStandSalaryFormWin",
		title : "标准审核",
		iconCls : "menu-goods-apply",
		x : 270,
		y : 20,
		border : false,
		width : 500,
		autoHeight : true,
		modal : true,
		layout : "anchor",
		plain : true,
		bodyStyle : "padding:5px;",
		buttonAlign : "center",
		items : [ this.setup(c) ],
		buttons : [ {
			text : "审核通过",
			iconCls : "btn-salaryPayoff-pass",
			handler : function() {
				var d = Ext.getCmp("CheckStandSalaryForm");
				if (d.getForm().isValid()) {
					d.getForm().submit( {
						method : "post",
						params : {
							status : "1"
						},
						waitMsg : "正在提交数据...",
						success : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("StandSalaryGrid").getStore().reload();
							b.close();
						},
						failure : function(e, f) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
							b.close();
						}
					});
				}
			}
		}, {
			text : "审核未通过",
			iconCls : "reset",
			handler : function() {
				var d = Ext.getCmp("CheckStandSalaryForm");
				if (d.getForm().isValid()) {
					d.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("StandSalaryGrid").getStore().reload();
							b.close();
						},
						failure : function(e, f) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
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
CheckStandSalaryForm.prototype.setup = function(c) {
	var b = new CheckStandSalaryItemView(c);
	var a = new Ext.FormPanel( {
		url : __ctxPath + "/hrm/checkStandSalary.do",
		layout : "form",
		id : "CheckStandSalaryForm",
		formId : "CheckStandSalaryFormId",
		bodyStyle : "padding:0px 5px 5px 5px;",
		defaultType : "textfield",
		border : false,
		reader : new Ext.data.JsonReader( {
			root : "data"
		}, [ {
			name : "standSalary.standardId",
			mapping : "standardId"
		}, {
			name : "standSalary.standardNo",
			mapping : "standardNo"
		}, {
			name : "standSalary.standardName",
			mapping : "standardName"
		}, {
			name : "standSalary.totalMoney",
			mapping : "totalMoney"
		}, {
			name : "standSalary.framer",
			mapping : "framer"
		}, {
			name : "standSalary.memo",
			mapping : "memo"
		} ]),
		items : [ {
			name : "standSalary.standardId",
			id : "standSalary.standardId",
			xtype : "hidden",
			value : this.standardId == null ? "" : this.standardId
		}, {
			xtype : "fieldset",
			title : "薪酬信息",
			layout : "form",
			anchor : "98%",
			autoWidth : true,
			autoHeight : true,
			labelWidth : 70,
			defaultType : "textfield",
			items : [ {
				xtype : "container",
				style : "padding-left:0px;",
				defaultType : "textfield",
				layout : "column",
				autoWidth : true,
				height : 26,
				defaults : {
					width : 150
				},
				items : [ {
					xtype : "label",
					text : "标准编号:",
					style : "padding-left:0px;padding-top:3px;",
					width : 71
				}, {
					fieldLabel : "标准编号",
					readOnly : true,
					name : "standSalary.standardNo"
				}, {
					xtype : "label",
					text : "标准名称",
					style : "padding-left:0px;padding-top:3px;",
					width : 71
				}, {
					fieldLabel : "标准名称",
					readOnly : true,
					name : "standSalary.standardName"
				} ]
			}, {
				xtype : "container",
				style : "padding-left:0px;",
				layout : "column",
				defaultType : "textfield",
				autoWidth : true,
				height : 26,
				defaults : {
					width : 150
				},
				items : [ {
					xtype : "label",
					text : "薪资总额",
					style : "padding-left:0px;padding-top:3px;",
					width : 71
				}, {
					name : "standSalary.totalMoney",
					readOnly : true
				}, {
					xtype : "label",
					text : "标准制定人",
					style : "padding-left:0px;padding-top:3px;",
					width : 71
				}, {
					name : "standSalary.framer",
					readOnly : true,
					value : curUserInfo.fullname
				} ]
			}, {
				fieldLabel : "备注",
				name : "standSalary.memo",
				readOnly : true,
				width : 370,
				xtype : "textarea"
			}, {
				fieldLabel : "审核意见",
				name : "standSalary.checkOpinion",
				allowBlank : false,
				blankText : "审核意见不可为空!",
				width : 370,
				xtype : "textarea"
			} ]
		}, b ]
	});
	if (this.standardId != null && this.standardId != "undefined") {
		a.getForm().load(
				{
					deferredRender : false,
					url : __ctxPath + "/hrm/getStandSalary.do?standardId="
							+ this.standardId,
					waitMsg : "正在载入数据...",
					success : function(d, e) {
					},
					failure : function(d, e) {
					}
				});
	}
	return a;
};