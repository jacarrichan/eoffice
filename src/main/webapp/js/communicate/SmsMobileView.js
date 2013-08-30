SmsMobileView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(b) {
		Ext.applyIf(this, b);
		this.initUIComponents();
		SmsMobileView.superclass.constructor.call(this, {
			id : "SmsMobileView",
			title : "手机短信管理",
			iconCls : "menu-mobile",
			region : "center",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel({
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
				text : "发送时间"
			}, {
				name : "Q_sendTime_S_LK",
				xtype : "textfield"
			}, {
				text : "收信人"
			}, {
				name : "Q_recipients_S_LK",
				xtype : "textfield"
			}, {
				text : "收信号码"
			}, {
				name : "Q_phoneNumber_S_LK",
				xtype : "textfield"
			}, {
				text : "状态"
			}, {
				hiddenName : "status",
				xtype : "combo",
				editable : false,
				mode : "local",
				triggerAction : "all",
				store : [ [ "1", "已发送" ], [ "0", "待发送" ] ],
				value : 1
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : this.search.createCallback(this)
			} ]
		});
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/communicate/listSmsMobile.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "smsId",
				type : "int"
			}, "sendTime", "recipients", "phoneNumber", "userId", "userName",
					"smsContent", "status" ]
		});
		this.store.setDefaultSort("smsId", "desc");
		this.store.load({
			params : {
				start : 0,
				limit : 25
			}
		});
		this.rowActions = new Ext.ux.grid.RowActions({
			header : "管理",
			width : 80,
			actions : [ {
				iconCls : "btn-del",
				qtip : "删除",
				style : "margin:0 3px 0 3px"
			} ]
		});
		var c = new Ext.grid.CheckboxSelectionModel();
		var d = new Ext.grid.ColumnModel({
			columns : [ c, new Ext.grid.RowNumberer(), {
				header : "smsId",
				dataIndex : "smsId",
				hidden : true
			}, {
				header : "发送时间",
				dataIndex : "sendTime",
				renderer : function(a) {
					return a.substring(0, 10);
				}
			}, {
				header : "收信人",
				dataIndex : "recipients"
			}, {
				header : "收信号码",
				dataIndex : "phoneNumber"
			}, {
				header : "发信人",
				dataIndex : "userName"
			}, {
				header : "短信内容",
				dataIndex : "smsContent"
			}, {
				header : "状态",
				dataIndex : "status",
				renderer : function(a) {
					if (a == 1) {
						return '<font color="green">已发送</font>';
					} else {
						return '<font color="red">待发送</font>';
					}
				}
			}, this.rowActions ],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : "text-align:left",
			items : [ {
				iconCls : "btn-add",
				text : "系统内短信",
				xtype : "button",
				handler : this.innerRecord
			}, {
				iconCls : "btn-add",
				text : "系统外短信",
				xtype : "button",
				handler : this.outsideRecord
			}, {
				iconCls : "btn-del",
				text : "删除短信",
				xtype : "button",
				handler : this.delRecords,
				scope : this
			} ]
		});
		this.gridPanel = new Ext.grid.GridPanel({
			id : "SmsMobileGrid",
			region : "center",
			stripeRows : true,
			tbar : this.topbar,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			height : 700,
			cm : d,
			sm : c,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true,
				autoFill : true,
				forceFit : true
			},
			bbar : new Ext.PagingToolbar({
				pageSize : 25,
				store : this.store,
				displayInfo : true,
				displayMsg : "当前页记录索引{0}-{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
		this.gridPanel.addListener("rowdblclick", function(b, e, a) {
			b.getSelectionModel().each(function(f) {
				new SmsMobileForm({
					smsId : f.data.smsId
				}).show();
			});
		});
		this.rowActions.on("action", this.onRowAction, this);
	},
	search : function(b) {
		if (b.searchPanel.getForm().isValid()) {
			$search({
				searchPanel : b.searchPanel,
				gridPanel : b.gridPanel
			});
		}
	},
	innerRecord : function() {
		new SmsMobileForm({
			isInner : true
		}).show();
	},
	outsideRecord : function() {
		new SmsMobileForm().show();
	},
	delByIds : function(b) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(a) {
			if (a == "yes") {
				Ext.Ajax.request({
					url : __ctxPath + "/communicate/multiDelSmsMobile.do",
					params : {
						ids : b
					},
					method : "POST",
					success : function(f, e) {
						Ext.ux.Toast.msg("操作信息", "成功删除该[SmsMobile]！");
						Ext.getCmp("SmsMobileGrid").getStore().reload();
					},
					failure : function(f, e) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var h = Ext.getCmp("SmsMobileGrid");
		var f = h.getSelectionModel().getSelections();
		if (f.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var g = Array();
		for ( var e = 0; e < f.length; e++) {
			g.push(f[e].data.smsId);
		}
		this.delByIds(g);
	},
	editRecord : function(b) {
		new SmsMobileForm({
			smsId : b.data.smsId
		}).show();
	},
	onRowAction : function(j, g, i, h, f) {
		switch (i) {
		case "btn-del":
			this.delByIds(g.data.smsId);
			break;
		case "btn-edit":
			this.editRecord(g);
			break;
		default:
			break;
		}
	}
});