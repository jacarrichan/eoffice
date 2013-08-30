InStockForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		InStockForm.superclass.constructor.call(this, {
			layout : "fit",
			id : "InStockFormWin",
			title : "入库单详细信息",
			iconCls : "menu-instock",
			width : 500,
			height : 260,
			items : this.formPanel,
			maximizable : true,
			modal : true,
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel( {
			url : __ctxPath + "/admin/saveInStock.do",
			id : "InStockForm",
			border : false,
			formId : "InStockFormId",
			bodyStyle : "padding : 5px;",
			items : [ {
				name : "inStock.buyId",
				id : "buyId",
				xtype : "hidden",
				value : this.buyId == null ? "" : this.buyId
			}, {
				name : "inStock.goodsId",
				xtype : "hidden",
				id : "goodsId"
			}, {
				xtype : "container",
				layout : "column",
				border : false,
				style : "padding-left:2px;margin-bottom:4px;",
				items : [ {
					xtype : "label",
					text : "商品名称:",
					style : "margin-top:2px;",
					width : 106
				}, {
					xtype : "textfield",
					name : "inStock.officeGoods.goodsName",
					id : "goodsName",
					allowBlank : false,
					readOnly : true,
					width : 220
				}, {
					xtype : "button",
					text : "选择商品",
					iconCls : "btn-select",
					handler : function() {
						GoodsSelector.getView(function(d, b) {
							var a = Ext.getCmp("goodsId");
							a.setValue(d);
							var c = Ext.getCmp("goodsName");
							c.setValue(b);
						}, true).show();
					}
				}, {
					xtype : "button",
					text : " 清除记录",
					iconCls : "reset",
					handler : function() {
						var a = Ext.getCmp("goodsId");
						a.setValue("");
						var b = Ext.getCmp("goodsName");
						b.setValue("");
					}
				} ]
			}, {
				xtype : "container",
				border : false,
				id : "InStockFormContainer",
				defaultType : "textfield",
				style : "padding-left:3px;",
				layout : "form",
				defaults : {
					widht : 300,
					anchor : "100%,100%"
				},
				items : [ {
					fieldLabel : " 供应商",
					name : "inStock.providerName",
					id : "providerName"
				}, {
					fieldLabel : " 入库单号",
					name : "inStock.stockNo",
					id : "stockNo",
					allowBlank : false,
					xtype : "textfield",
					readOnly : true
				}, {
					fieldLabel : "价格",
					name : "inStock.price",
					allowBlank : false,
					id : "price",
					xtype : "numberfield"
				}, {
					fieldLabel : " 总数",
					name : "inStock.inCounts",
					allowBlank : false,
					id : "inCounts",
					xtype : "numberfield"
				}, {
					fieldLabel : " 统计",
					name : "inStock.amount",
					allowBlank : false,
					id : "amount"
				}, {
					fieldLabel : " 进货日期",
					name : "inStock.inDate",
					allowBlank : false,
					editable : false,
					id : "inDate",
					value : new Date(),
					xtype : "datefield",
					format : "Y-m-d",
					width : 126
				} ]
			}, {
				xtype : "container",
				layout : "column",
				style : "padding-left:2px;margin-bottom:4px;",
				border : false,
				items : [ {
					xtype : "label",
					text : "购买人:",
					style : "margin-top:2px;",
					width : 106
				}, {
					xtype : "textfield",
					name : "inStock.buyer",
					id : "buyer",
					value : curUserInfo.fullname,
					allowBlank : false,
					readOnly : true,
					width : 220
				}, {
					xtype : "button",
					text : "选择人员",
					iconCls : "btn-users",
					handler : function() {
						UserSelector.getView(function(c, b) {
							var a = Ext.getCmp("buyer");
							a.setValue(b);
						}, false).show();
					}
				}, {
					xtype : "button",
					text : " 清除记录",
					iconCls : "reset",
					handler : function() {
						var a = Ext.getCmp("buyer");
						a.setValue("");
					}
				} ]
			} ]
		});
		if (this.buyId != null && this.buyId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath + "/admin/getInStock.do?buyId="
								+ this.buyId,
						method : "post",
						waitMsg : "正在载入数据...",
						success : function(e, g) {
							var f = g.result.data.inDate;
							var b = g.result.data.officeGoods.goodsId;
							var a = g.result.data.officeGoods.goodsName;
							var d = Ext.getCmp("inDate");
							var c = Ext.getCmp("goodsId");
							var h = Ext.getCmp("goodsName");
							c.setValue(b);
							h.setValue(a);
							d.setValue(new Date(getDateFromFormat(f,
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
				var a = Ext.getCmp("InStockForm");
				if (a.getForm().isValid()) {
					a.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(b, c) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("InStockGrid").getStore().reload();
							Ext.getCmp("InStockFormWin").close();
						},
						failure : function(b, c) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : "ext-mb-error"
							});
							Ext.getCmp("InStockFormWin").close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				Ext.getCmp("InStockFormWin").close();
			}
		} ];
	}
});