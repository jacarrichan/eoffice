ArchivesDepView = Ext.extend(Ext.Panel, {
	constructor : function(a) {
		if (a == null) {
			a = {};
		}
		Ext.apply(this, a);
		this.initComponents();
		ArchivesDepView.superclass.constructor.call(this, {
			id : "ArchivesDepView",
			title : "[ArchivesDep]管理",
			region : "center",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	typeId : null,
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	initComponents : function() {
		this.searchPanel = new Ext.FormPanel( {
			layout : "column",
			region : "north",
			bodyStyle : "padding:6px 10px 6px 10px",
			border : false,
			defaults : {
				border : false,
				anchor : "98%,98%"
			},
			items : [ {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "自编号",
					name : "Q_signNo_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "收文部门",
					name : "Q_depId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "所属公文",
					name : "Q_archivesId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "公文标题",
					name : "Q_subject_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "所属收文分类",
					name : "Q_typeId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "签收状态",
					name : "Q_status_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "签收日期",
					name : "Q_signTime_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "签收人",
					name : "Q_signFullname_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "办理结果反馈",
					name : "Q_handleFeedback_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "主送、抄送",
					name : "Q_isMain_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					xtype : "button",
					text : "查询",
					iconCls : "search",
					handler : this.search.createCallback(this)
				}
			} ]
		});
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/archive/listArchivesDep.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "archDepId",
				type : "int"
			}, "signNo", "depId", "archivesId", "subject", "typeId", "status",
					"signTime", "signFullname", "handleFeedback", "isMain" ]
		});
		this.store.setDefaultSort("archDepId", "desc");
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
				iconCls : "btn-del",
				qtip : "删除",
				style : "margin:0 3px 0 3px"
			}, {
				iconCls : "btn-edit",
				qtip : "编辑",
				style : "margin:0 3px 0 3px"
			} ]
		});
		var b = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel( {
			columns : [ b, new Ext.grid.RowNumberer(), {
				header : "archDepId",
				dataIndex : "archDepId",
				hidden : true
			}, {
				header : "自编号",
				dataIndex : "signNo"
			}, {
				header : "收文部门",
				dataIndex : "depId"
			}, {
				header : "所属公文",
				dataIndex : "archivesId"
			}, {
				header : "公文标题",
				dataIndex : "subject"
			}, {
				header : "所属收文分类",
				dataIndex : "typeId"
			}, {
				header : "签收状态",
				dataIndex : "status"
			}, {
				header : "签收日期",
				dataIndex : "signTime"
			}, {
				header : "签收人",
				dataIndex : "signFullname"
			}, {
				header : "办理结果反馈",
				dataIndex : "handleFeedback"
			}, {
				header : "主送、抄送",
				dataIndex : "isMain"
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
			items : [ {
				iconCls : "btn-add",
				text : "添加[ArchivesDep]",
				xtype : "button",
				handler : this.createRecord
			}, {
				iconCls : "btn-del",
				text : "删除[ArchivesDep]",
				xtype : "button",
				handler : this.delRecord
			} ]
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ArchivesDepGrid",
			region : "center",
			stripeRows : true,
			tbar : this.topbar,
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
		this.gridPanel.addListener("rowdblclick", function(d, c, f) {
			d.getSelectionModel().each(function(e) {
				new ArchivesDepForm(e.data.archDepId).show();
			});
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
		new ArchivesDepForm().show();
	},
	delRecord : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/archive/multiDelArchivesDep.do",
					params : {
						ids : a.data.archDepId
					},
					method : "POST",
					success : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "成功删除该公文分类！");
						Ext.getCmp("ArchivesDepGrid").getStore().reload();
					},
					failure : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	editRecord : function(a) {
		new ArchivesDepForm( {
			archDepId : a.data.archDepId
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delRecord(a);
			break;
		case "btn-edit":
			this.editRecord(a);
			break;
		default:
			break;
		}
	}
});