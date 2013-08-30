UserContractDetailWin = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.init();
		UserContractDetailWin.superclass.constructor.call(this, {
			title : "合同详情",
			id : "UserContractDetailWin",
			iconCls : "btn-archives-detail",
			layout : "form",
			modal : true,
			height : 430,
			width : 750,
			maximizable : true,
			border : false,
			autoScroll : true,
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
			autoHeight : true,
			autoScroll : true,
			autoLoad : {
				url : __ctxPath + "/pages/hrm/contractFile.jsp?contractId="
						+ this.contractId
			}
		});
	}
});