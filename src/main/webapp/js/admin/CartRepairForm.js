CartRepairForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		CartRepairForm.superclass.constructor.call(this, {
			layout : "fit",
			id : "CartRepairFormWin",
			title : "车辆维修详细信息",
			iconCls : "menu-car_repair",
			width : 600,
			height : 345,
			minWidth : 599,
			minHeight : 344,
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
			url : __ctxPath + "/admin/saveCartRepair.do",
			layout : "form",
			id : "CartRepairForm",
			frame : false,
			bodyStyle : "padding:5px;",
			defaults : {
				anchor : "100%,100%"
			},
			formId : "CartRepairFormId",
			defaultType : "textfield",
			items : [ {
				name : "cartRepair.repairId",
				id : "repairId",
				xtype : "hidden",
				value : this.repairId == null ? "" : this.repairId
			}, {
				xtype : "hidden",
				name : "cartRepair.carId",
				id : "carId"
			}, {
				xtype : "container",
				layout : "hbox",
				layoutConfigs : {
					align : "middle"
				},
				defaults : {
					margins : "0 2 0 0"
				},
				height : 26,
				items : [ {
					xtype : "label",
					text : "车牌号码:",
					width : 103
				}, {
					id : "carNo",
					xtype : "textfield",
					name : "carNo",
					editable : false,
					readOnly : true,
					allowBlank : false,
					width : 130
				}, {
					xtype : "button",
					iconCls : "btn-car",
					text : "选择车辆",
					handler : function() {
						CarSelector.getView(function(b, a) {
							Ext.getCmp("carId").setValue(b);
							Ext.getCmp("carNo").setValue(a);
						}, true).show();
					}
				}, {
					xtype : "button",
					text : "消除记录",
					iconCls : "reset",
					handler : function() {
						Ext.getCmp("carId").setValue("");
						Ext.getCmp("carNo").setValue("");
					}
				} ]
			}, {
				fieldLabel : "维护日期",
				name : "cartRepair.repairDate",
				id : "repairDate",
				xtype : "datefield",
				format : "Y-m-d",
				allowBlank : false,
				editable : false
			}, {
				xtype : "container",
				layout : "hbox",
				layoutConfigs : {
					align : "middle"
				},
				defaults : {
					margins : "0 2 0 0"
				},
				height : 30,
				items : [ {
					xtype : "label",
					text : "经办人:",
					width : 103
				}, {
					xtype : "textfield",
					name : "cartRepair.executant",
					id : "executant",
					editable : false,
					allowBlank : false,
					readOnly : true,
					width : 130
				}, {
					xtype : "button",
					iconCls : "btn-user-sel",
					text : "选择人员",
					handler : function() {
						UserSelector.getView(function(b, a) {
							Ext.getCmp("executant").setValue(a);
						}, true).show();
					}
				}, {
					xtype : "button",
					text : "清除纪录",
					iconCls : "reset",
					handler : function() {
						Ext.getCmp("executant").setValue("");
					}
				} ]
			}, {
				fieldLabel : "维修类型",
				name : "cartRepair.repairType",
				id : "repairType",
				xtype : "combo",
				mode : "local",
				allowBlank : false,
				editable : false,
				triggerAction : "all",
				store : [ [ "1", "保养" ], [ "2", "维修" ] ]
			}, {
				fieldLabel : "费用",
				name : "cartRepair.fee",
				id : "fee",
				xtype : "numberfield"
			}, {
				fieldLabel : "维护原因",
				name : "cartRepair.reason",
				id : "reason",
				allowBlank : false,
				xtype : "textarea"
			}, {
				fieldLabel : "备注",
				name : "cartRepair.notes",
				id : "notes",
				xtype : "textarea"
			} ]
		});
		if (this.repairId != null && this.repairId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath + "/admin/getCartRepair.do?repairId="
								+ this.repairId,
						waitMsg : "正在载入数据...",
						success : function(a, b) {
							Ext.getCmp("carNo").setValue(
									b.result.data.car.carNo);
							Ext.getCmp("repairDate").setValue(
									new Date(getDateFromFormat(
											b.result.data.repairDate,
											"yyyy-MM-dd HH:mm:ss")));
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
				var a = Ext.getCmp("CartRepairForm");
				if (a.getForm().isValid()) {
					a.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(b, c) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("CartRepairGrid").getStore().reload();
							Ext.getCmp("CartRepairFormWin").close();
						},
						failure : function(b, c) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : "ext-mb-error"
							});
							Ext.getCmp("CartRepairFormWin").close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				Ext.getCmp("CartRepairFormWin").close();
			}
		} ];
	}
});