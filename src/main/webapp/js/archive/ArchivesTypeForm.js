ArchivesTypeForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		if (a == null) {
			a = {};
		}
		Ext.apply(this, a);
		this.initComponents();
		ArchivesTypeForm.superclass.constructor.call(this, {
			id : "ArchivesTypeFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 200,
			width : 400,
			title : "公文分类详细信息",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initComponents : function() {
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/archive/saveArchivesType.do",
			id : "ArchivesTypeForm",
			defaults : {
				anchor : "98%,98%"
			},
			formId : "ArchivesTypeFormId",
			defaultType : "textfield",
			items : [ {
				name : "archivesType.typeId",
				id : "typeId",
				xtype : "hidden",
				value : this.typeId == null ? "" : this.typeId
			}, {
				fieldLabel : "类型名称",
				name : "archivesType.typeName",
				id : "typeName",
				allowBlank : false
			}, {
				fieldLabel : "类型描述",
				name : "archivesType.typeDesc",
				id : "typeDesc",
				xtype : "textarea"
			} ]
		});
		if (this.typeId != null && this.typeId != "undefined") {
			this.formPanel.getForm().load(
					{
						deferredRender : false,
						url : __ctxPath + "/archive/getArchivesType.do?typeId="
								+ this.typeId,
						waitMsg : "正在载入数据...",
						success : function(a, b) {
						},
						failure : function(a, b) {
						}
					});
		}
		this.buttons = [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var a = Ext.getCmp("ArchivesTypeForm");
				if (a.getForm().isValid()) {
					a.getForm().submit( {
						method : "POST",
						waitMsg : "正在提交数据...",
						success : function(b, d) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							var c = Ext.getCmp("archivesTypeTree");
							if (c != null) {
								c.root.reload();
							}
							Ext.getCmp("ArchivesTypeFormWin").close();
						},
						failure : function(b, c) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
							Ext.getCmp("ArchivesTypeFormWin").close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				Ext.getCmp("ArchivesTypeFormWin").close();
			}
		} ];
	}
});