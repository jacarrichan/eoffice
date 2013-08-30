BoardTypeForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponent();
		BoardTypeForm.superclass.constructor.call(this, {
			layout : "fit",
			id : "BoardTypeWindow",
			closeAction : "close",
			title : "编辑/新增会议类型",
			iconCls : "menu-confernece_boardType",
			width : 350,
			height : 200,
			border : false,
			modal : true,
			resizable : false,
			items : [ this.formPanel ],
			buttonAlign : "center",
			keys : {
				key : Ext.EventObject.ENTER,
				fn : this.save.createCallback(this.formPanel, this),
				scope : this
			}
		});
	},
	initUIComponent : function() {
		this.formPanel = new Ext.FormPanel( {
			id : "BoardTypeFormPanel",
			layout : "form",
			border : true,
			bodyStyle : "padding:5,5,5,5",
			defaultType : "textfield",
			url : __ctxPath + "/admin/saveBoardType.do",
			items : [ {
				xtype : "hidden",
				name : "boardType.typeId",
				value : this.typeId
			}, {
				name : "boardType.typeName",
				fieldLabel : "会议类型名称",
				width : 200,
				allowBlank : false,
				blankText : "会议类型名称不能为空，请输入！",
				maxLength : 128,
				maxLengthText : "会议类型名称不能超哥哥128个字符长度！"
			}, {
				name : "boardType.typeDesc",
				xtype : "textarea",
				fieldLabel : "会议类型描述",
				width : 200,
				height : 70,
				allowBlank : false,
				blankText : "会议类型描述不能为空，请输入！",
				maxLength : 4000,
				maxLengthText : "会议类型描述不能超过4000个字符串长度！"
			} ]
		});
		if (this.typeId != null && this.typeId != ""
				&& this.typeId != "undefined") {
			this.formPanel.loadData( {
				url : __ctxPath + "/admin/getBoardType.do?typeId="
						+ this.typeId,
				root : "data",
				preName : "boardType",
				waitMsg : "数据正在加载中，请稍后...",
				success : function(a, b) {
				},
				failure : function() {
					Ext.ux.Toast.msg("操作提示", "对不起，数据加载失败！");
				}
			});
		}
		this.buttons = [ {
			text : "保存",
			iconCls : "btn-save",
			handler : this.save.createCallback(this.formPanel, this)
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
	},
	cancel : function(a) {
		a.close();
	},
	save : function(a, b) {
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				url : __ctxPath + "/admin/saveBoardType.do",
				method : "post",
				waitMsg : "数据正在提交，请稍后...",
				success : function(c, d) {
					Ext.ux.Toast.msg("操作提示", "恭喜您，您提交的数据操作成功！");
					Ext.getCmp("BoardTypeGridPanel").getStore().reload( {
						params : {
							start : 0,
							limit : 25
						}
					});
					b.close();
				},
				failure : function() {
					Ext.ux.Toast.msg("操作提示", "对不起，您提交的数据失败，请重新提交！");
				}
			});
		}
	}
});