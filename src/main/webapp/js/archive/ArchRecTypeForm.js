ArchRecTypeForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchRecTypeForm.superclass.constructor.call(this, {
			id : "ArchRecTypeFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 200,
			iconCls : "menu-arch-rec-type",
			width : 400,
			maximizable : true,
			title : "来文分类详细信息",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		var a = __ctxPath + "/system/listDepartment.do?opt=appUser";
		var b = new TreeSelector("ArchRecTypeForm.depName", a, "所属部门", "depId",
				false);
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/archive/saveArchRecType.do",
			id : "ArchRecTypeForm",
			defaults : {
				anchor : "98%,98%"
			},
			defaultType : "textfield",
			items : [ {
				name : "archRecType.recTypeId",
				id : "recTypeId",
				xtype : "hidden",
				value : this.recTypeId == null ? "" : this.recTypeId
			}, {
				xtype : "hidden",
				name : "archRecType.depId",
				id : "depId"
			}, {
				fieldLabel : "分类名称",
				allowBlank : false,
				name : "archRecType.typeName",
				id : "typeName"
			}, b ]
		});
		if (this.recTypeId != null && this.recTypeId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath
								+ "/archive/getArchRecType.do?recTypeId="
								+ this.recTypeId,
						waitMsg : "正在载入数据...",
						success : function(c, d) {
							var e = d.result.data.department;
							Ext.getCmp("ArchRecTypeForm.depName").setValue(
									e.depName);
							Ext.getCmp("depId").setValue(e.depId);
						},
						failure : function(c, d) {
						}
					});
		}
		this.buttons = [ {
			text : "保存",
			iconCls : "btn-save",
			handler : this.save.createCallback(this.formPanel, this)
		}, {
			text : "重置",
			iconCls : "btn-reset",
			handler : this.reset.createCallback(this.formPanel)
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
	},
	reset : function(a) {
		a.getForm().reset();
	},
	cancel : function(a) {
		a.close();
	},
	save : function(a, b) {
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("ArchRecTypeGrid");
					if (d != null) {
						d.getStore().reload();
					}
					b.close();
				},
				failure : function(c, d) {
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
});