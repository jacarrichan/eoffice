CarApplyForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		CarApplyForm.superclass.constructor.call(this, {
			layout : "fit",
			id : "CarApplyFormWin",
			title : "车辆申请详细信息",
			iconCls : "menu-car_apply",
			width : 600,
			height : 480,
			minWidth : 599,
			minHeight : 479,
			items : this.formPanel,
			maximizable : true,
			border : false,
			modal : true,
			plain : true,
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel( {
			url : __ctxPath + "/admin/saveCarApply.do",
			layout : "form",
			id : "CarApplyForm",
			frame : false,
			border : true,
			defaults : {
				width : 400,
				anchor : "98%,98%"
			},
			bodyStyle : "padding-top:5px;padding-left:5px;",
			formId : "CarApplyFormId",
			defaultType : "textfield",
			items : [ {
				name : "carApply.applyId",
				id : "applyId",
				xtype : "hidden",
				value : this.applyId == null ? "" : this.applyId
			}, {
				xtype : "hidden",
				id : "carId",
				name : "carApply.carId"
			}, {
				xtype : "hidden",
				id : "userId",
				name : "carApply.userId",
				value : curUserInfo.userId
			}, {
				xtype : "container",
				layout : "column",
				style : "padding-left:0px;margin-bottom:4px;",
				items : [ {
					xtype : "label",
					style : "padding-left:0px;",
					text : "车牌号码:",
					width : 105
				}, {
					xtype : "textfield",
					name : "carNo",
					id : "carNo",
					allowBlank : false,
					editable : false,
					readOnly : true,
					blankText : "不能为空!",
					width : 320
				}, {
					xtype : "button",
					iconCls : "btn-car",
					text : "选择车辆",
					handler : function() {
						CarSelector.getView(function(b, a) {
							Ext.getCmp("carNo").setValue(a);
							Ext.getCmp("carId").setValue(b);
						}, true, 1).show();
					}
				}, {
					xtype : "button",
					text : "清除记录",
					iconCls : "reset",
					handler : function() {
						Ext.getCmp("carNo").setValue("");
						Ext.getCmp("carId").setValue("");
					}
				} ]
			}, {
				xtype : "container",
				style : "padding-left:0px;margin-bottom:4px;",
				id : "depContainer",
				layout : "column",
				items : [ {
					xtype : "label",
					style : "padding-left:0px;",
					text : "用车部门:",
					width : 105
				}, {
					xtype : "textfield",
					name : "carApply.department",
					id : "department",
					value : curUserInfo.depName,
					allowBlank : false,
					editable : false,
					readOnly : true,
					width : 320
				}, {
					xtype : "button",
					iconCls : "btn-dep-sel",
					text : "选择部门",
					handler : function() {
						DepSelector.getView(function(b, a) {
							Ext.getCmp("department").setValue(a);
						}).show();
					}
				}, {
					xtype : "button",
					text : "清除记录",
					iconCls : "reset",
					handler : function() {
						Ext.getCmp("department").setValue("");
					}
				} ]
			}, {
				xtype : "container",
				style : "padding-left:0px;margin-bottom:4px;",
				layout : "column",
				items : [ {
					xtype : "label",
					style : "padding-left:0px;",
					text : "用车人:",
					width : 105
				}, {
					xtype : "textfield",
					name : "carApply.userFullname",
					id : "userFullname",
					value : curUserInfo.fullname,
					allowBlank : false,
					editable : false,
					readOnly : true,
					width : 320
				}, {
					xtype : "button",
					iconCls : "btn-user-sel",
					text : "选择人员",
					handler : function() {
						UserSelector.getView(function(b, a) {
							Ext.getCmp("userFullname").setValue(a);
						}, true).show();
					}
				}, {
					xtype : "button",
					text : "清除记录",
					iconCls : "reset",
					handler : function() {
						Ext.getCmp("userFullname").setValue("");
					}
				} ]
			}, {
				xtype : "container",
				layout : "column",
				style : "padding-left:0px;margin-bottom:4px;",
				items : [ {
					xtype : "label",
					style : "padding-left:0px;",
					text : "申请人:",
					width : 105
				}, {
					xtype : "textfield",
					name : "carApply.proposer",
					id : "proposer",
					editable : false,
					allowBlank : false,
					readOnly : true,
					value : curUserInfo.fullname,
					width : 320
				}, {
					xtype : "button",
					iconCls : "btn-user-sel",
					text : "选择人员",
					handler : function() {
						UserSelector.getView(function(b, a) {
							Ext.getCmp("proposer").setValue(a);
							Ext.getCmp("userId").setValue(b);
						}, true, true).show();
					}
				}, {
					xtype : "button",
					text : "清除记录",
					iconCls : "reset",
					handler : function() {
						Ext.getCmp("proposer").setValue("");
					}
				} ]
			}, {
				fieldLabel : "原因",
				name : "carApply.reason",
				id : "reason",
				allowBlank : false,
				xtype : "textarea"
			}, {
				fieldLabel : "开始时间",
				name : "carApply.startTime",
				id : "startTime",
				xtype : "datetimefield",
				format : "Y-m-d H:i:s",
				allowBlank : false,
				editable : false
			}, {
				fieldLabel : "结束时间",
				name : "carApply.endTime",
				id : "endTime",
				xtype : "datetimefield",
				format : "Y-m-d H:i:s"
			}, {
				fieldLabel : "里程(公里)",
				name : "carApply.mileage",
				id : "mileage",
				xtype : "numberfield"
			}, {
				fieldLabel : "油耗(升)",
				name : "carApply.oilUse",
				id : "oilUse",
				xtype : "numberfield"
			}, {
				fieldLabel : "备注",
				name : "carApply.notes",
				id : "notes",
				xtype : "textarea"
			} ]
		});
		if (this.applyId != null && this.applyId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath + "/admin/getCarApply.do?applyId="
								+ this.applyId,
						waitMsg : "正在载入数据...",
						success : function(b, c) {
							Ext.getCmp("carNo").setValue(
									c.result.data.car.carNo);
							Ext.getCmp("carId").setValue(
									c.result.data.car.carId);
							Ext.getCmp("applyDate").setValue(
									new Date(getDateFromFormat(
											c.result.data.applyDate,
											"yyyy-MM-dd HH:mm:ss")));
							Ext.getCmp("startTime").setValue(
									new Date(getDateFromFormat(
											c.result.data.startTime,
											"yyyy-MM-dd HH:mm:ss")));
							var a = c.result.data.endTime;
							if (a != null && a != "") {
								Ext.getCmp("endTime").setValue(
										new Date(getDateFromFormat(a,
												"yyyy-MM-dd HH:mm:ss")));
							}
						},
						failure : function(a, b) {
							Ext.ux.Toast.msg("编辑", "载入失败");
						}
					});
		}
		this.buttons = [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var a = Ext.getCmp("CarApplyForm");
				if (a.getForm().isValid()) {
					a.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(b, c) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("CarApplyGrid").getStore().reload();
							Ext.getCmp("CarApplyFormWin").close();
						},
						failure : function(b, c) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : "ext-mb-error"
							});
							Ext.getCmp("CarApplyFormWin").close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				Ext.getCmp("CarApplyFormWin").close();
			}
		} ];
	}
});