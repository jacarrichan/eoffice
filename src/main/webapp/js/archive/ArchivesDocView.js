ArchivesDocView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchivesDocView.superclass.constructor.call(this, {
			id : "ArchivesDocView",
			title : "[ArchivesDoc]管理",
			region : "center",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
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
					fieldLabel : "",
					name : "Q_fileId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "",
					name : "Q_archivesId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "拟稿人",
					name : "Q_creator_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "拟稿人ID",
					name : "Q_creatorId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "",
					name : "Q_menderId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "修改人",
					name : "Q_mender_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "文档名称",
					name : "Q_docName_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "文档状态",
					name : "Q_docStatus_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "当前版本",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "文档路径",
					name : "Q_docPath_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "更新时间",
					name : "Q_updatetime_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "创建时间",
					name : "Q_createtime_S_LK",
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
			url : __ctxPath + "/archive/listArchivesDoc.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "docId",
				type : "int"
			}, "fileId", "archivesId", "creator", "creatorId", "menderId",
					"mender", "docName", "docStatus", "curVersion", "docPath",
					"updatetime", "createtime" ]
		});
		this.store.setDefaultSort("docId", "desc");
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
				header : "docId",
				dataIndex : "docId",
				hidden : true
			}, {
				header : "",
				dataIndex : "fileId"
			}, {
				header : "",
				dataIndex : "archivesId"
			}, {
				header : "拟稿人",
				dataIndex : "creator"
			}, {
				header : "拟稿人ID",
				dataIndex : "creatorId"
			}, {
				header : "",
				dataIndex : "menderId"
			}, {
				header : "修改人",
				dataIndex : "mender"
			}, {
				header : "文档名称",
				dataIndex : "docName"
			}, {
				header : "文档状态",
				dataIndex : "docStatus"
			}, {
				header : "当前版本",
				dataIndex : "curVersion"
			}, {
				header : "文档路径",
				dataIndex : "docPath"
			}, {
				header : "更新时间",
				dataIndex : "updatetime"
			}, {
				header : "创建时间",
				dataIndex : "createtime"
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
				text : "添加[ArchivesDoc]",
				xtype : "button",
				handler : this.createRecord
			}, {
				iconCls : "btn-del",
				text : "删除[ArchivesDoc]",
				xtype : "button",
				handler : this.delRecords,
				scope : this
			} ]
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ArchivesDocGrid",
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
				new ArchivesDocForm(e.data.docId).show();
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
		new ArchivesDocForm().show();
	},
	delByIds : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/archive/multiDelArchivesDoc.do",
					params : {
						ids : a
					},
					method : "POST",
					success : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "成功删除该[ArchivesDoc]！");
						Ext.getCmp("ArchivesDocGrid").getStore().reload();
					},
					failure : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var c = Ext.getCmp("ArchivesDocGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var d = Array();
		for ( var b = 0; b < a.length; b++) {
			d.push(a[b].data.docId);
		}
		this.delByIds(d);
	},
	editRecord : function(a) {
		new ArchivesDocForm( {
			docId : a.data.docId
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.docId);
			break;
		case "btn-edit":
			this.editRecord(a);
			break;
		default:
			break;
		}
	}
});