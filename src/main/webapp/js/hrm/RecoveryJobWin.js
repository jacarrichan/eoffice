RecoveryJobWin = Ext.extend(Ext.Window, {
	constructor : function(a) {
		if (a == null) {
			a = {};
		}
		Ext.apply(this, a);
		this.initComponents();
		RecoveryJobWin.superclass.constructor.call(this, {
			id : "RecoveryJobWin",
			iconCls : "menu-job",
			title : "职位恢复",
			border : false,
			width : 720,
			modal : true,
			height : 450,
			layout : "column",
			items : [ this.searchPanel, this.gridPanel ],
			bottons : this.buttons,
			buttonAlign : "center"
		});
	},
	typeId : null,
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	initComponents : function() {
		this.buttons = [ {
			text : "取消",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
		this.searchPanel = new Ext.FormPanel( {
			columnWidth : 1,
			height : 35,
			region : "north",
			frame : false,
			border : false,
			layout : "hbox",
			layoutConfig : {
				padding : "5",
				align : "middle"
			},
			defaults : {
				style : "padding:0px 5px 0px 5px;",
				border : false,
				anchor : "98%,98%",
				labelWidth : 70,
				xtype : "label"
			},
			items : [ {
				text : "职位名称"
			}, {
				name : "Q_jobName_S_LK",
				xtype : "textfield"
			}, {
				text : "所属部门"
			}, {
				name : "Q_depId_S_LK",
				xtype : "textfield"
			}, {
				text : "备注"
			}, {
				name : "Q_memo_S_LK",
				xtype : "textfield"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : this.search.createCallback(this)
			}, {
				name : "Q_delFlag_SN_EQ",
				width : 80,
				xtype : "hidden",
				value : 1
			} ]
		});
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/hrm/listJob.do",
			baseParams : {
				"Q_delFlag_SN_EQ" : 1
			},
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "jobId",
				type : "int"
			}, "jobName", "department", "memo" ]
		});
		this.store.setDefaultSort("jobId", "desc");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		var b = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel( {
			columns : [ b, new Ext.grid.RowNumberer(), {
				header : "jobId",
				dataIndex : "jobId",
				hidden : true
			}, {
				header : "职位名称",
				dataIndex : "jobName"
			}, {
				header : "所属部门",
				dataIndex : "department",
				renderer : function(c) {
					return c.depName;
				}
			}, {
				header : "备注",
				dataIndex : "memo"
			} ],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		this.topbar = new Ext.Toolbar( {
			height : 30,
			bodyStyle : "text-align:left",
			items : [ {
				iconCls : "btn-empProfile-recovery",
				text : "恢复职位",
				xtype : "button",
				handler : this.recoveryRecord
			} ]
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "RecoveryJobGrid",
			height : 300,
			columnWidth : 1,
			stripeRows : true,
			tbar : this.topbar,
			autoScroll : true,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			cm : a,
			sm : b,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true,
				autoFill : true,
				forceFit : true
			},
			bbar : new Ext.PagingToolbar( {
				pageSize : 25,
				store : this.store,
				displayInfo : true,
				displayMsg : "当前页记录索引{0}-{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
	},
	search : function(a) {
		if (a.searchPanel.getForm().isValid()) {
			$search( {
				searchPanel : a.searchPanel,
				gridPanel : a.gridPanel
			});
		}
	},
	recoveryRecord : function() {
		var c = Ext.getCmp("RecoveryJobGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.Msg.alert("信息", "请选择要恢复的职位记录！");
			return;
		}
		var d = Array();
		for ( var b = 0; b < a.length; b++) {
			d.push(a[b].data.jobId);
		}
		Ext.Msg.confirm("信息确认", "您确认要恢复该职位记录吗？", function(e) {
			if (e == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/hrm/recoveryJob.do",
					params : {
						ids : d
					},
					method : "POST",
					success : function(f, g) {
						Ext.ux.Toast.msg("操作信息", "成功恢复职位！");
						Ext.getCmp("JobGrid").getStore().reload();
						Ext.getCmp("RecoveryJobGrid").getStore().reload();
					},
					failure : function(f, g) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	cancel : function(a) {
		a.close();
	}
});