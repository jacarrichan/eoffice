LeaderReadForm = Ext.extend(Ext.Window, {
	formPanel : null,
	displayPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		LeaderReadForm.superclass.constructor.call(this, {
			id : "LeaderReadFormWin",
			layout : "form",
			items : this.displayPanel,
			modal : true,
			height : 400,
			width : 600,
			iconCls : "menu-arch-leader",
			maximizable : true,
			title : "领导指示意见",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.displayPanel = new Ext.Panel( {
			id : "ArchivesHandleDisplayPanel",
			autoScroll : true,
			height : 380,
			border : false,
			autoLoad : {
				url : __ctxPath + "/pages/archive/leaderreader.jsp?leaderId="
						+ this.leadId
			}
		});
		this.formPanel = new Ext.FormPanel( {
			layout : "form",
			bodyStyle : "padding:10px 10px 10px 10px",
			border : false,
			autoScroll : false,
			url : __ctxPath + "/archive/saveLeaderRead.do",
			id : "LeaderReadForm",
			defaults : {
				anchor : "98%,98%"
			},
			items : [ {
				name : "leaderRead.readId",
				id : "readId",
				xtype : "hidden",
				value : this.leadId
			}, {
				fieldLabel : "审批意见",
				name : "leaderOpinion",
				xtype : "textarea",
				id : "leaderOpinion"
			}, {
				fieldLabel : "0=尚未批",
				name : "isPass",
				xtype : "hidden",
				id : "isPass"
			} ]
		});
		this.buttons = [ {
			text : "关闭",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
	},
	cancel : function(a) {
		a.close();
	},
	pass : function(a, b) {
		Ext.getCmp("isPass").setValue(1);
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("LeaderReadGrid");
					if (d != null) {
						d.getStore().reload();
					}
					b.close();
				},
				failure : function(c, d) {
					Ext.MessageBox.show( {
						title : "操作信息",
						msg : "信息保存出错，请联系管理员！",
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					b.close();
				}
			});
		}
	},
	notpass : function(a, b) {
		Ext.getCmp("isPass").setValue(2);
		if (a.getForm().isValid()) {
			a.getForm().submit( {
				method : "POST",
				waitMsg : "正在提交数据...",
				success : function(c, e) {
					Ext.ux.Toast.msg("操作信息", "成功保存信息！");
					var d = Ext.getCmp("LeaderReadGrid");
					if (d != null) {
						d.getStore().reload();
					}
					b.close();
				},
				failure : function(c, d) {
					Ext.MessageBox.show( {
						title : "操作信息",
						msg : "信息保存出错，请联系管理员！",
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					b.close();
				}
			});
		}
	}
});