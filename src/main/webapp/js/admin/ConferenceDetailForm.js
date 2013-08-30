Ext.ns("ConferenceDetailForm");
ConferenceDetailForm.show = function(b) {
	var a = new Ext.Window( {
		title : "会议详细信息",
		iconCls : "menu-conference",
		modal : true,
		width : 750,
		height : 580,
		layout : "form",
		region : "center",
		autoScroll : true,
		maximizable : true,
		buttonAlign : "center",
		autoLoad : {
			url : __ctxPath + "/admin/conferenceDetail.do?confId=" + b
		},
		buttons : [ {
			xtype : "button",
			iconCls : "btn-close",
			text : "关闭",
			handler : function() {
				a.close();
			}
		} ]
	});
	Ext.Ajax.request( {
		url : __ctxPath + "/admin/allowViewConfPrivilege.do",
		params : {
			confId : b
		},
		method : "post",
		waitMsg : "数据正在提交，请稍后...",
		success : function(c, d) {
			var e = Ext.util.JSON.decode(c.responseText);
			if (e.success) {
				a.show();
			} else {
				Ext.MessageBox.show( {
					title : "操作信息",
					msg : e.msg,
					buttons : Ext.MessageBox.OK,
					icon : "ext-mb-error"
				});
			}
		}
	});
};