GoodsApplyCheck = Ext.extend(Ext.Window, {
	constructor : function(a) {
		Ext.apply(this, a);
		this.initUICmps();
		GoodsApplyCheck.superclass.constructor.call(this, {
			title : "办公用品申请审批",
			width : 600,
			height : 380,
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
						url : __ctxPath + "/admin/checkGoodsApply.do",
						params : {
							applyId : a.applyId,
							approvalStatus : 1
						},
						success : function(d, e) {
							var c = Ext.util.JSON.decode(d.responseText);
							Ext.ux.Toast.msg("操作信息", "成功提交：" + c.message);
							Ext.getCmp("GoodsApplyGrid").getStore().reload();
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
						url : __ctxPath + "/admin/checkGoodsApply.do",
						params : {
							applyId : a.applyId,
							approvalStatus : 2
						},
						success : function(d, e) {
							var c = Ext.util.JSON.decode(d.responseText);
							Ext.ux.Toast.msg("操作信息", "成功提交:" + c.message);
							Ext.getCmp("GoodsApplyGrid").getStore().reload();
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
				fieldLabel : "申请日期",
				id : "applyDate"
			}, {
				fieldLabel : "办公用品名称",
				id : "goodsName"
			}, {
				fieldLabel : "申请数量",
				id : "useCounts"
			}, {
				fieldLabel : "申请人",
				id : "proposer"
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
			$request( {
				url : __ctxPath + "/admin/getGoodsApply.do?applyId="
						+ this.applyId,
				success : function(b, c) {
					var d = Ext.util.JSON.decode(b.responseText).data;
					Ext.getCmp("applyDate").setText(d.applyDate);
					Ext.getCmp("goodsName").setText(d.officeGoods.goodsName);
					Ext.getCmp("useCounts").setText(d.useCounts);
					Ext.getCmp("proposer").setText(d.proposer);
					var e = "";
					if (d.approvalStatus == 0) {
						e = "未审批";
					} else {
						if (d.approvalStatus == 1) {
							e = "通过审批";
						} else {
							e = "未通过审批";
						}
					}
					Ext.getCmp("approvalStatus").setText(e);
					Ext.getCmp("notes").setText(d.notes);
				},
				failure : function(b, c) {
					Ext.ux.Toast.msg("编辑", "载入失败");
				}
			});
		}
	}
});