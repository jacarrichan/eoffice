ContractEventView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ContractEventView.superclass.constructor.call(this, {
			id : "ContractEventView",
			title : "合同记录管理",
			region : "center",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel( {
			id : "ContractEventSearchForm",
			region : "north",
			height : 40,
			frame : false,
			border : false,
			layout : "hbox",
			layoutConfig : {
				padding : "5",
				align : "middle"
			},
			defaults : {
				xtype : "label",
				margins : {
					top : 0,
					right : 4,
					bottom : 4,
					left : 4
				}
			},
			items : [ {
				text : "请输入查询条件:"
			}, {
				text : "记录名称"
			}, {
				name : "Q_eventName_S_LK",
				xtype : "textfield"
			}, {
				text : "时间"
			}, {
				name : "Q_createTime_S_LK",
				xtype : "datefield",
				width : 110,
				format : "Y-m-d",
				editable : false
			}, {
				text : "经手人"
			}, {
				name : "Q_creator_S_LK",
				xtype : "textfield"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : this.search.createCallback(this)
			} ]
		});
		this.rowActions = new Ext.ux.grid.RowActions( {
			header : "管理",
			width : 80,
			actions : [ {
				iconCls : "btn-del",
				qtip : "删除",
				style : "margin:0 3px 0 3px"
			} ]
		});
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/hrm/listContractEvent.do",
			root : "result",
			fields : [ {
				name : "eventId",
				type : "int"
			}, "userContract", "eventName", "eventDescp", "createTime",
					"creator" ]
		});
		this.store.setDefaultSort("eventId", "desc");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		var b = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel( {
			columns : [ b, new Ext.grid.RowNumberer(), {
				header : "eventId",
				dataIndex : "eventId",
				hidden : true
			}, {
				header : "记录名称",
				dataIndex : "eventName"
			}, {
				header : "记录理由",
				dataIndex : "eventDescp"
			}, {
				header : "创建时间",
				dataIndex : "createTime"
			}, {
				header : "经手人",
				dataIndex : "creator"
			}, this.rowActions ],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ContractEventGrid",
			region : "center",
			stripeRows : true,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			autoHeight : true,
			cm : a,
			sm : b,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true,
				autoFill : true
			},
			bbar : new Ext.PagingToolbar( {
				pageSize : 25,
				store : this.store,
				displayInfo : true,
				displayMsg : "当前页记录索引{0}-{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
		this.rowActions.on("action", this.onRowAction, this);
	},
	search : function(c) {
		var a = Ext.getCmp("ContractEventSearchForm");
		if (a.getForm().isValid()) {
			var e = Ext.getCmp("ContractEventGrid");
			var b = e.getStore();
			var f = Ext.Ajax.serializeForm(a.getForm().getEl());
			var d = Ext.urlDecode(f);
			d.start = 0;
			d.limit = b.baseParams.limit;
			b.baseParams = d;
			e.getBottomToolbar().moveFirst();
		}
	},
	delByIds : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/hrm/multiDelContractEvent.do",
					params : {
						ids : a
					},
					method : "POST",
					success : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "成功删除该合同事件！");
						Ext.getCmp("ContractEventGrid").getStore().reload();
					},
					failure : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var c = Ext.getCmp("ContractEventGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var d = Array();
		for ( var b = 0; b < a.length; b++) {
			d.push(a[b].data.eventId);
		}
		this.delByIds(d);
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.eventId);
			break;
		default:
			break;
		}
	}
});