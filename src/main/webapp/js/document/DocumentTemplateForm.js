DocumentTemplateForm = Ext.extend(Ext.Window, {
	docPanel : null,
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUI();
		DocumentTemplateForm.superclass.constructor.call(this, {
			width : 700,
			title : "模板详细信息",
			height : 540,
			iconCls : "menu-template",
			shim : false,
			modal : true,
			layout : "fit",
			maximizable : true,
			buttonAlign : "center",
			buttons : this.buttons,
			items : [ this.formPanel ]
		});
	},
	initUI : function() {
		Ext.useShims = true;
		this.docPanel = new NtkOfficePanel({
			showToolbar : false,
			height : 420
		});
		this.formPanel = new Ext.FormPanel({
			url : __ctxPath + "/document/savePaintTemplate.do",
			layout : "form",
			bodyStyle : "padding:10px",
			border : false,
			autoScroll : true,
			defaults : {
				anchor : "100%,100%"
			},
			defaultType : "textfield",
			items : [ {
				name : "paintTemplate.ptemplateId",
				xtype : "hidden",
				value : this.ptemplateId == null ? "" : this.ptemplateId
			}, {
				name : "paintTemplate.fileId",
				xtype : "hidden"
			}, {
				fieldLabel : "模板名称",
				name : "paintTemplate.templateName",
				allowBlank : false,
				maxLength : 64
			}, this.docPanel.panel, {
				fieldLabel : "是否启用",
				name : "paintTemplate.isActivate",
				allowBlank : false,
				xtype : "hidden",
				value : 0
			}, {
				xtype : "hidden",
				fieldLabel : "模板文件",
				name : "paintTemplate.path",
				readOnly : true,
				anchor : "98%,98%"
			} ]
		});
		if (this.ptemplateId != null && this.ptemplateId != "undefined") {
			var b = this.docPanel;
			var a = this.formPanel;
			this.formPanel.loadData({
				url : __ctxPath + "/document/getPaintTemplate.do?ptemplateId="
						+ this.ptemplateId,
				root : "data",
				preName : "paintTemplate",
				success : function(e, g) {
					var d = Ext.util.JSON.decode(e.responseText).data;
					var f = d.fileAttach;
					if (f != null) {
						var c = f.fileId;
						a.getCmpByName("paintTemplate.fileId").setValue(c);
						b.openDoc(c);
					}
				}
			});
		}
		this.buttons = [ {
			xtype : "button",
			text : "保存修改",
			iconCls : "btn-save",
			scope : this,
			handler : this.saveTemplate
		}, {
			xtype : "button",
			text : "关闭",
			iconCls : "btn-cancel",
			scope : this,
			handler : function() {
				this.docPanel.closeDoc();
				this.close();
			}
		} ];
	},
	saveTemplate : function() {
		var g = this;
		var h = this.formPanel;
		if (h.getForm().isValid()) {
			var a = h.getCmpByName("paintTemplate.templateName").getValue();
			var c = this.docPanel.saveDoc({
				docName : a,
				fileId : this.fileId,
				doctype : "doc"
			});
			if (c && c.success) {
				var e = this.docPanel;
				var d = h.getCmpByName("paintTemplate.fileId");
				var f = h.getCmpByName("paintTemplate.path");
				var b = c.fileId;
				var i = c.filePath;
				d.setValue(b);
				f.setValue(i);
				h.getForm().submit({
					method : "post",
					success : function(j, l) {
						Ext.ux.Toast.msg("操作信息", "成功信息保存！");
						var k = Ext.getCmp("PaintTemplateGrid");
						if (k != null && k != undefined) {
							k.getStore().reload();
						}
						e.closeDoc();
						g.close();
					},
					failure : function(j, k) {
						Ext.MessageBox.show({
							title : "操作信息",
							msg : "信息保存出错，请联系管理员！",
							buttons : Ext.MessageBox.OK,
							icon : "ext-mb-error"
						});
						e.closeDoc();
						g.close();
					}
				});
			} else {
				Ext.MessageBox.show({
					title : "操作信息",
					msg : "保存信息失败，请联系管理员！",
					buttons : Ext.MessageBox.OK,
					icon : "ext-mb-error"
				});
			}
		}
	}
});