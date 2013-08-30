RegulationScanWin = Ext.extend(Ext.Window, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		RegulationScanWin.superclass.constructor.call(this, {
			id : "RegulationScanWinWin",
			layout : "fit",
			items : this.displayPanel,
			modal : true,
			height : 577,
			width : 1000,
			iconCls : "btn-suggest-scan",
			maximizable : true,
			title : "规章制度详细信息",
			buttonAlign : "center",
			buttons : [ {
				text : "取消",
				iconCls : "btn-cancel",
				scope : this,
				handler : this.cancel
			} ]
		});
	},
	initUIComponents : function() {
		this.displayPanel = new Ext.Panel( {
			flex : 1,
			id : "CheckEmpProfileFormPanel",
			autoScroll : true,
			border : false,
			autoLoad : {
				url : __ctxPath + "/pages/admin/displayRegulation.jsp?regId="
						+ this.regId
			}
		});
	},
	cancel : function() {
		this.close();
	}
});