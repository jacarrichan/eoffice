DepreTypeForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		DepreTypeForm.superclass.constructor.call(this, {
			layout : "fit",
			id : "DepreTypeFormWin",
			title : "折旧类型详细信息",
			iconCls : "menu-depre-type",
			width : 400,
			height : 240,
			minWidth : 399,
			minHeight : 239,
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
			url : __ctxPath + "/admin/saveDepreType.do",
			layout : "form",
			id : "DepreTypeForm",
			frame : false,
			border : false,
			bodyStyle : "padding:5px;",
			defaults : {
				anchor : "98%,98%"
			},
			defaultType : "textfield",
			items : [
					{
						name : "depreType.depreTypeId",
						id : "depreTypeId",
						xtype : "hidden",
						value : this.depreTypeId == null ? ""
								: this.depreTypeId
					},
					{
						fieldLabel : "分类名称",
						name : "depreType.typeName",
						id : "typeName",
						allowBlank : false
					},
					{
						xtype : "container",
						height : 28,
						layout : "hbox",
						layoutConfigs : {
							align : "middle"
						},
						defaults : {
							margins : "0 2 0 2"
						},
						items : [ {
							xtype : "label",
							text : "折旧周期:",
							width : 100
						}, {
							xtype : "numberfield",
							name : "depreType.deprePeriod",
							id : "deprePeriod",
							width : 220,
							allowBlank : false
						}, {
							xtype : "label",
							text : "月"
						} ]
					},
					{
						fieldLabel : "折旧计算方法",
						hiddenName : "depreType.calMethod",
						id : "calMethod",
						xtype : "combo",
						mode : "local",
						editable : false,
						allowBlank : false,
						triggerAction : "all",
						store : [ [ "1", "平均年限法" ], [ "2", "工作量法" ],
								[ "3", "双倍余额递减法" ], [ "4", "年数总和法" ] ]
					}, {
						fieldLabel : "类型说明",
						name : "depreType.typeDesc",
						id : "typeDesc",
						xtype : "textarea"
					} ]
		});
		if (this.depreTypeId != null && this.depreTypeId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath + "/admin/getDepreType.do?depreTypeId="
								+ this.depreTypeId,
						waitMsg : "正在载入数据...",
						success : function(a, b) {
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
				var a = Ext.getCmp("DepreTypeForm");
				if (a.getForm().isValid()) {
					a.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(b, c) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("DepreTypeGrid").getStore().reload();
							Ext.getCmp("DepreTypeFormWin").close();
						},
						failure : function(b, c) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : "ext-mb-error"
							});
							Ext.getCmp("DepreTypeFormWin").close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				Ext.getCmp("DepreTypeFormWin").close();
			}
		} ];
	}
});