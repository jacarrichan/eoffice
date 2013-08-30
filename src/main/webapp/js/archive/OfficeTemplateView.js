var OfficeTemplateView = function(a, b, f, e) {
	this.docPath = b;
	this.readOnly = f == null ? false : f;
	this.docPanel = new NtkOfficePanel( {
		showToolbar : false,
		height : 420
	});
	var c = new Ext.Button( {
		iconCls : "btn-save",
		text : "保存",
		scope : this,
		handler : function() {
			var g = this.docPanel.saveDoc( {
				fileId : a,
				doctype : "doc"
			});
			if (g && g.success) {
				if (e != null) {
					e.call(this, g.fileId, g.filePath);
				}
				d.close();
			}
		}
	});
	var d = new Ext.Window( {
		title : "公文模板详细信息",
		iconCls : "menu-archive-template",
		height : 500,
		width : 700,
		maximizable : true,
		modal : true,
		items : this.docPanel.panel,
		buttonAlign : "center",
		buttons : [ c, {
			iconCls : "btn-cancel",
			text : "关闭",
			handler : function() {
				d.close();
			}
		} ]
	});
	if (this.readOnly) {
		c.setVisible(false);
		c.setDisabled(true);
	}
	d.show();
	if (a != null && a != "") {
		this.docPanel.openDoc(a);
	}
};