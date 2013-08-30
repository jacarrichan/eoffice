ArchHastenForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchHastenForm.superclass.constructor.call(this, {
			id : "ArchHastenFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 200,
			width : 400,
			maximizable : true,
			title : "催办信息",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			url : __ctxPath + "/archive/hastenArchives.do",
			id : "ArchHastenForm",
			defaults : {
				anchor : "98%,98%"
			},
			items : [
					{
						xtype : "hidden",
						name : "archivesId",
						value : this.archivesId
					},
					{
						fieldLabel : "催办内容",
						name : "content",
						xtype : "textarea",
						value : "你有[" + this.archivesNo + "--"
								+ this.activityName + "]任务,请速去办理！"
					}, {
						xtype : "hidden",
						name : "activityName",
						value : this.activityName
					} ]
		});
		this.buttons = [ {
			text : "发送",
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
				success : function(c, d) {
					Ext.ux.Toast.msg("操作信息", "成功发送催办信息！");
					b.close();
				},
				failure : function(c, e) {
					var d = e.result.message;
					Ext.MessageBox.show( {
						title : "操作信息",
						msg : d != "" ? d : "信息保存出错，请联系管理员！",
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					b.close();
				}
			});
		}
	}
});