LeaderReadView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		LeaderReadView.superclass.constructor.call(this, {
			id : "LeaderReadView",
			title : "领导批示待办",
			region : "center",
			iconCls : "menu-arch-leader",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel( {
			height : 40,
			region : "north",
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
				text : "来文机关或部门"
			}, {
				name : "Q_archives.issueDep_S_LK",
				xtype : "textfield",
				anchor : "100%"
			}, {
				text : "文件标题"
			}, {
				name : "Q_archives.subject_S_LK",
				xtype : "textfield",
				anchor : "100%"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : this.search.createCallback(this)
			} ]
		});
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/archive/listLeaderRead.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "readId",
				type : "int"
			}, "archives" ]
		});
		this.store.setDefaultSort("readId", "desc");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		this.rowActions = new Ext.ux.grid.RowActions( {
			header : "管理",
			width : 80,
			actions : [ {
				iconCls : "btn-readdocument",
				qtip : "查看",
				style : "margin:0 3px 0 3px"
			} ]
		});
		var c = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel( {
			columns : [ c, new Ext.grid.RowNumberer(), {
				header : "readId",
				dataIndex : "readId",
				hidden : true
			}, {
				header : "文件标题",
				dataIndex : "archives",
				renderer : function(d) {
					if (d != null) {
						return d.subject;
					}
				}
			}, {
				header : "发文字号",
				dataIndex : "archives",
				renderer : function(d) {
					if (d != null) {
						return d.archivesNo;
					}
				}
			}, {
				header : "来文机关或部门",
				dataIndex : "archives",
				renderer : function(d) {
					if (d != null) {
						return d.issueDep;
					}
				}
			}, {
				header : "发布日期",
				dataIndex : "archives",
				renderer : function(d) {
					if (d != null && d != "") {
						return d.issueDate.substring(0, 10);
					}
				}
			}, this.rowActions ],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		var b = this.store;
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "LeaderReadGrid",
			region : "center",
			stripeRows : true,
			store : b,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			autoHeight : true,
			cm : a,
			sm : c,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true,
				autoFill : true,
				forceFit : true
			},
			bbar : new Ext.PagingToolbar( {
				pageSize : 25,
				store : b,
				displayInfo : true,
				displayMsg : "当前页记录索引{0}-{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
		this.rowActions.on("action", this.onRowAction, this);
	},
	search : function(a) {
		if (a.searchPanel.getForm().isValid()) {
			$search( {
				searchPanel : a.searchPanel,
				gridPanel : a.gridPanel
			});
		}
	},
	editRecord : function(a) {
		new LeaderReadForm( {
			leadId : a.data.readId
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-readdocument":
			this.editRecord(a);
			break;
		default:
			break;
		}
	}
});