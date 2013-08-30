var StandSalaryForm = function(a) {
	this.standardId = a;
	return new Ext.Panel( {
		id : "StandSalaryForm",
		iconCls : "menu-development",
		tbar : this.initToolbar(),
		border : false,
		title : "薪酬标准详细信息",
		items : [ this.setup(a) ]
	});
};
StandSalaryForm.prototype.setup = function(c) {
	var b = new StandSalaryItemView(c);
	var a = new Ext.FormPanel(
			{
				id : "StandSalaryFormPanel",
				url : __ctxPath + "/hrm/saveStandSalary.do",
				layout : "form",
				bodyStyle : "padding:5px 10px 10px 10px;",
				formId : "StandSalaryFormId",
				defaultType : "textfield",
				border : false,
				reader : new Ext.data.JsonReader( {
					root : "data"
				}, [ {
					name : "standSalaryForm.standardId",
					mapping : "standardId"
				}, {
					name : "standSalaryForm.setdownTime",
					mapping : "setdownTime"
				}, {
					name : "standSalaryForm.framer",
					mapping : "framer"
				}, {
					name : "standSalaryForm.checkName",
					mapping : "checkName"
				}, {
					name : "standSalaryForm.checkTime",
					mapping : "checkTime"
				}, {
					name : "standSalaryForm.checkOpinion",
					mapping : "checkOpinion"
				}, {
					name : "standSalaryForm.standardNo",
					mapping : "standardNo"
				}, {
					name : "standSalaryForm.standardName",
					mapping : "standardName"
				}, {
					name : "standSalaryForm.totalMoney",
					mapping : "totalMoney"
				}, {
					name : "standSalaryForm.memo",
					mapping : "memo"
				} ]),
				items : [
						{
							name : "standSalary.standardId",
							id : "standSalaryForm.standardId",
							xtype : "hidden",
							value : this.standardId == null ? ""
									: this.standardId
						},
						{
							name : "standSalary.setdownTime",
							id : "standSalaryForm.setdownTime",
							xtype : "hidden"
						},
						{
							name : "standSalary.framer",
							id : "standSalaryForm.framer",
							xtype : "hidden"
						},
						{
							name : "standSalary.checkName",
							id : "standSalaryForm.checkName",
							xtype : "hidden"
						},
						{
							name : "standSalary.checkTime",
							id : "standSalaryForm.checkTime",
							xtype : "hidden"
						},
						{
							name : "standSalary.checkOpinion",
							id : "standSalaryForm.checkOpinion",
							xtype : "hidden"
						},
						{
							name : "deleteItemIds",
							id : "deleteItemIds",
							xtype : "hidden"
						},
						{
							xtype : "fieldset",
							title : "薪酬信息",
							anchor : "100%",
							layout : "form",
							items : [
									{
										xtype : "container",
										layout : "column",
										style : "padding-left:0px;",
										items : [
												{
													xtype : "container",
													defaultType : "textfield",
													style : "padding-left:0px;",
													columnWidth : 0.5,
													defaults : {
														anchor : "100%,100%"
													},
													layout : "form",
													items : [
															{
																fieldLabel : "标准编号",
																allowBlank : false,
																blankText : "标准编号不能为空!",
																name : "standSalary.standardNo",
																id : "standSalaryForm.standardNo"
															},
															{
																fieldLabel : "标准名称",
																xtype : "textfield",
																name : "standSalary.standardName",
																allowBlank : false,
																blankText : "标准名称不能为空!",
																id : "standSalaryForm.standardName"
															} ]
												},
												{
													xtype : "container",
													columnWidth : 0.5,
													style : "padding-left:0px;",
													defaults : {
														anchor : "100%,100%"
													},
													layout : "form",
													items : [
															{
																id : "getStandardNoButton",
																xtype : "button",
																autoWidth : true,
																text : "系统生成",
																iconCls : "btn-system-setting",
																handler : function() {
																	Ext.Ajax
																			.request( {
																				url : __ctxPath
																						+ "/hrm/numberStandSalary.do",
																				success : function(
																						e) {
																					var d = Ext.util.JSON
																							.decode(e.responseText);
																					Ext
																							.getCmp(
																									"standSalaryForm.standardNo")
																							.setValue(
																									d.standardNo);
																				}
																			});
																}
															},
															{
																fieldLabel : "薪资总额",
																name : "standSalary.totalMoney",
																id : "standSalaryForm.totalMoney",
																xtype : "textfield",
																readOnly : true,
																anchor : "100%"
															} ]
												} ]
									}, {
										fieldLabel : "备注",
										name : "standSalary.memo",
										id : "standSalaryForm.memo",
										xtype : "textarea",
										anchor : "99%"
									} ]
						}, b ]
			});
	if (this.standardId != null && this.standardId != "undefined") {
		a.getForm()
				.load(
						{
							deferredRender : false,
							url : __ctxPath
									+ "/hrm/getStandSalary.do?standardId="
									+ this.standardId,
							waitMsg : "正在载入数据...",
							success : function(d, e) {
								Ext.getCmp("getStandardNoButton").disable();
								Ext.getCmp("standSalaryForm.standardNo")
										.getEl().dom.readOnly = true;
							},
							failure : function(d, e) {
							}
						});
	}
	return a;
};
StandSalaryForm.prototype.initToolbar = function() {
	var a = new Ext.Toolbar( {
		width : "100%",
		height : 30,
		items : [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				StandSalaryForm.saveStandSalary();
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				var b = Ext.getCmp("centerTabPanel");
				b.remove("StandSalaryForm");
			}
		} ]
	});
	return a;
};
StandSalaryForm.saveStandSalary = function() {
	StandSalaryItemView.onCalcTotalMoney();
	var c = Ext.getCmp("StandSalaryFormPanel");
	var b = Ext.getCmp("StandSalaryItemGrid").getStore();
	var d = [];
	for (i = 0, cnt = b.getCount(); i < cnt; i += 1) {
		var a = b.getAt(i);
		if (a.data.itemId == "" || a.data.itemId == null) {
			a.set("itemId", -1);
		}
		if (a.dirty) {
			d.push(a.data);
		}
	}
	if (c.getForm().isValid()) {
		c.getForm().submit( {
			method : "post",
			params : {
				data : Ext.encode(d)
			},
			waitMsg : "正在提交数据...",
			success : function(e, f) {
				Ext.ux.Toast.msg("操作信息", "成功保存信息！");
			},
			failure : function(e, f) {
				Ext.MessageBox.show( {
					title : "操作信息",
					msg : f.result.msg,
					buttons : Ext.MessageBox.OK,
					icon : "ext-mb-error"
				});
				Ext.getCmp("standSalaryForm.standardNo").setValue("");
			}
		});
	}
};