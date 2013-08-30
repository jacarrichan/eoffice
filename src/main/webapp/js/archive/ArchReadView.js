ArchReadView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchReadView.superclass.constructor.call(this, {
			id : "ArchReadView",
			iconCls : "menu-arch-reader",
			title : "收文阅读待办",
			region : "center",
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
				text : "公文标题"
			}, {
				name : "Q_subject_S_LK",
				anchor : "100%",
				xtype : "textfield"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : this.search.createCallback(this)
			} ]
		});
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/archive/listArchDispatch.do",
			baseParams : {
				"Q_archUserType_SN_EQ" : 0
			},
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "dispatchId",
				type : "int"
			}, "archivesId", "dispatchTime", "userId", "fullname", "isRead",
					"subject", "readFeedback", "isUndertake", "archives" ]
		});
		this.store.setDefaultSort("dispatchId", "desc");
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
		var b = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel( {
			columns : [ b, new Ext.grid.RowNumberer(), {
				header : "dispatchId",
				dataIndex : "dispatchId",
				hidden : true
			}, {
				header : "公文字号",
				dataIndex : "archives",
				renderer : function(c) {
					if (c != null) {
						return c.archivesNo;
					}
				}
			}, {
				header : "公文标题",
				dataIndex : "archives",
				renderer : function(c) {
					if (c != null) {
						return c.subject;
					}
				}
			}, {
				header : "发布时间",
				dataIndex : "archives",
				renderer : function(c) {
					if (c != null && c != "") {
						return c.issueDate.substring(0, 10);
					}
				}
			}, {
				header : "来文机关或部门",
				dataIndex : "archives",
				renderer : function(c) {
					if (c != null) {
						return c.issueDep;
					}
				}
			}, this.rowActions ],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ArchReadGrid",
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
		new ArchReadForm( {
			dispatchId : a.data.dispatchId
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