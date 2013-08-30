ArchivesDetailWin = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.init();
		ArchivesDetailWin.superclass.constructor.call(this, {
			title : "公文详情",
			id : "ArchivesDetailWin",
			iconCls : "btn-archives-detail",
			layout : "fit",
			modal : true,
			height : 430,
			width : 750,
			maximizable : true,
			border : false,
			buttonAlign : "center",
			buttons : [ {
				text : "关闭",
				iconCls : "btn-del",
				handler : this.closePanel,
				scope : this
			} ],
			items : [ this.panel ]
		});
	},
	closePanel : function() {
		this.close();
	},
	init : function() {
		this.panel = new Ext.Panel( {
			autoScroll : true,
			autoLoad : {
				url : __ctxPath + "/pages/archive/archiveInfo.jsp?archivesId="
						+ this.archivesId + "&rand=" + Math.random()
			}
		});
	}
});