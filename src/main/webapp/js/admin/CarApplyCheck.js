CarApplyCheck = Ext.extend(Ext.Window, {
	constructor : function(a) {
		Ext.apply(this, a);
		this.initUICmps();
		CarApplyCheck.superclass.constructor.call(this, {
			title : "车辆使用申请审批",
			width : 600,
			height : 420,
			autoScroll : true,
			bodyStyle : "background-color: white",
			maximizable : true,
			modal : true,
			items : [ this.formPanel ],
			buttonAlign : "center",
			buttons : [ {
				xtype : "button",
				text : "通过",
				iconCls : "btn-ok",
				scope : this,
				handler : function() {
					var b = this;
					$request( {
						url : __ctxPath + "/admin/checkCarApply.do",
						params : {
							applyId : a.applyId,
							approvalStatus : 1
						},
						success : function(d, e) {
							var c = Ext.util.JSON.decode(d.responseText);
							Ext.ux.Toast.msg("操作信息", "成功提交：" + c.message);
							Ext.getCmp("CarApplyGrid").getStore().reload();
							b.close();
						}
					});
				}
			}, {
				xtype : "button",
				text : "未通过",
				iconCls : "btn-delete_copy",
				scope : this,
				handler : function() {
					var b = this;
					$request( {
						url : __ctxPath + "/admin/checkCarApply.do",
						params : {
							applyId : a.applyId,
							approvalStatus : 2
						},
						success : function(d, e) {
							var c = Ext.util.JSON.decode(d.responseText);
							Ext.ux.Toast.msg("操作信息", "成功提交:" + c.message);
							Ext.getCmp("CarApplyGrid").getStore().reload();
							b.close();
						}
					});
				}
			}, {
				xtype : "button",
				text : "关闭",
				iconCls : "btn-close",
				scope : this,
				handler : function() {
					this.close();
				}
			} ]
		});
	},
	initUICmps : function() {
		this.formPanel = new Ext.form.FormPanel( {
			xtype : "form",
			bodyStyle : "padding:8px",
			border : false,
			defaults : {
				xtype : "label"
			},
			items : [ {
				fieldLabel : "车牌号码",
				id : "carNo"
			}, {
				fieldLabel : "用车部门",
				id : "department"
			}, {
				fieldLabel : "用车人",
				id : "userFullname"
			}, {
				fieldLabel : "申请人",
				id : "proposer"
			}, {
				fieldLabel : "申请时间",
				id : "applyDate"
			}, {
				fieldLabel : "原因",
				id : "reason"
			}, {
				fieldLabel : "开始时间",
				id : "startTime"
			}, {
				fieldLabel : "结束时间",
				id : "endTime"
			}, {
				fieldLabel : "里程",
				id : "mileage"
			}, {
				fieldLabel : "油耗",
				id : "oilUse"
			}, {
				fieldLabel : "审批状态 ",
				id : "approvalStatus"
			}, {
				fieldLabel : "备注",
				id : "notes"
			} ]
		});
		var a = this.formPanel;
		if (this.applyId != null && this.applyId != "undefined") {
			try {
				$request( {
					url : __ctxPath + "/admin/getCarApply.do?applyId="
							+ this.applyId,
					method : "post",
					success : function(c, d) {
						var e = Ext.util.JSON.decode(c.responseText).data;
						Ext.getCmp("carNo").setText(e.car.carNo);
						Ext.getCmp("department").setText(e.department);
						Ext.getCmp("userFullname").setText(e.userFullname);
						Ext.getCmp("proposer").setText(e.proposer);
						Ext.getCmp("applyDate").setText(e.applyDate);
						Ext.getCmp("reason").setText(e.reason);
						Ext.getCmp("startTime").setText(e.startTime);
						Ext.getCmp("endTime").setText(e.endTime);
						Ext.getCmp("mileage").setText(e.mileage);
						Ext.getCmp("oilUse").setText(e.oilUse);
						var f = "";
						if (e.approvalStatus == 0) {
							f = "未审批";
						} else {
							if (e.approvalStatus == 1) {
								f = "通过审批";
							} else {
								f = "未通过审批";
							}
						}
						Ext.getCmp("approvalStatus").setText(f);
						Ext.getCmp("notes").setText(e.notes);
					},
					failure : function(c, d) {
						Ext.ux.Toast.msg("编辑", "载入失败");
					}
				});
			} catch (b) {
			}
		}
	}
});