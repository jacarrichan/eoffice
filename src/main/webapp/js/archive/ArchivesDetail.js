ArchivesDetail = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchivesDetail.superclass.constructor.call(this, {
			id : "ArchivesDetailWin",
			layout : "fit",
			items : this.displayPanel,
			modal : true,
			height : 400,
			iconCls : "menu-arch-detail",
			width : 600,
			maximizable : true,
			title : "公文详细信息",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.displayPanel = new Ext.Panel( {
			id : "ArchivesDetailDisplayPanel",
			autoScroll : true,
			height : 220,
			border : false,
			autoLoad : {
				url : __ctxPath + "/pages/archive/archivedetail.jsp?archiveId="
						+ this.archivesId + "&rand=" + Math.random(),
				nocache : true
			}
		});
		this.buttons = [ {
			text : "关闭",
			iconCls : "btn-close",
			handler : this.cancel.createCallback(this)
		} ];
	},
	cancel : function(a) {
		a.close();
	}
});