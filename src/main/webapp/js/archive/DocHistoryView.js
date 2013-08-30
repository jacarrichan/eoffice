DocHistoryView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		DocHistoryView.superclass.constructor.call(this, {
			id : "DocHistoryView",
			iconCls : "menu-archive-history",
			title : "版本管理",
			region : "center",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel( {
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
				text : "文档名称"
			}, {
				name : "Q_docName_S_LK",
				xtype : "textfield"
			}, {
				text : "更新时间"
			}, {
				name : "Q_updatetime_S_LK",
				xtype : "textfield"
			}, {
				text : "修改人"
			}, {
				name : "Q_mender_S_LK",
				xtype : "textfield"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : this.search.createCallback(this)
			} ]
		});
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/archive/listDocHistory.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "historyId",
				type : "int"
			}, "archivesDoc", "fileAttach", "docName", "path", "version",
					"updatetime", "mender" ]
		});
		this.store.setDefaultSort("historyId", "desc");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		var b = [];
		if (isGranted("_DocHistoryDel")) {
			b.push( {
				iconCls : "btn-del",
				qtip : "删除",
				style : "margin:0 3px 0 3px"
			});
		}
		this.rowActions = new Ext.ux.grid.RowActions( {
			header : "管理",
			width : 80,
			actions : b
		});
		var c = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel( {
			columns : [ c, new Ext.grid.RowNumberer(), {
				header : "historyId",
				dataIndex : "historyId",
				hidden : true
			}, {
				header : "所属公文主版本",
				dataIndex : "archivesDoc",
				renderer : function(d) {
					return d.docName;
				}
			}, {
				header : "所属附件",
				dataIndex : "fileAttach",
				renderer : function(d) {
					return d.fileName;
				}
			}, {
				header : "文档名称",
				dataIndex : "docName"
			}, {
				header : "版本",
				dataIndex : "version"
			}, {
				header : "路径",
				dataIndex : "path"
			}, {
				header : "更新时间",
				dataIndex : "updatetime",
				renderer : function(d) {
					return d.substring(0, 10);
				}
			}, {
				header : "修改人",
				dataIndex : "mender"
			}, this.rowActions ],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		this.topbar = new Ext.Toolbar( {
			height : 30,
			bodyStyle : "text-align:left",
			items : []
		});
		if (isGranted("_DocHistoryDel")) {
			this.topbar.add( {
				iconCls : "btn-del",
				text : "删除历史版本",
				xtype : "button",
				handler : this.delRecords,
				scope : this
			});
		}
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "DocHistoryGrid",
			region : "center",
			stripeRows : true,
			tbar : this.topbar,
			store : this.store,
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
	createRecord : function() {
		new DocHistoryForm().show();
	},
	delByIds : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/archive/multiDelDocHistory.do",
					params : {
						ids : a
					},
					method : "POST",
					success : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "成功删除该[DocHistory]！");
						Ext.getCmp("DocHistoryGrid").getStore().reload();
					},
					failure : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var c = Ext.getCmp("DocHistoryGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var d = Array();
		for ( var b = 0; b < a.length; b++) {
			d.push(a[b].data.historyId);
		}
		this.delByIds(d);
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.historyId);
			break;
		default:
			break;
		}
	}
});