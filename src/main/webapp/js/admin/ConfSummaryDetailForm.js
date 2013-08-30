Ext.ns("ConfSummaryDetailForm");
ConfSummaryDetailForm.show = function(a) {
	var b = new Ext.Window( {
		id : "confSummaryDetailForm",
		name : "confSummaryDetailForm",
		width : 500,
		heigth : 500,
		modal : true,
		autoScroll : true,
		maximizable : true,
		title : "纪要详细信息",
		iconCls : "menu-confSummary",
		layout : "form",
		region : "center",
		buttonAlign : "center",
		autoLoad : {
			url : __ctxPath + "/admin/confSummaryDetail.do?sumId=" + a
		},
		buttons : [ {
			text : "关闭",
			iconCls : "btn-close",
			handler : function() {
				b.close();
			}
		} ]
	});
	b.show();
};