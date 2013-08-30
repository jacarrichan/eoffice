ArchivesView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchivesView.superclass.constructor.call(this, {
			id : "ArchivesView",
			title : "[Archives]管理",
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
					fieldLabel : "公文类型",
					name : "Q_typeId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "公文类型名称",
					name : "Q_typeName_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "发文字号",
					name : "Q_archivesNo_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "发文机关或部门",
					name : "Q_issueDep_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "发文部门ID",
					name : "Q_depId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "文件标题",
					name : "Q_subject_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "发布日期",
					name : "Q_issueDate_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "公文状态",
					name : "Q_status_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "内容简介",
					name : "Q_shortContent_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "文件数",
					name : "Q_fileCounts_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "秘密等级",
					name : "Q_privacyLevel_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "紧急程度",
					name : "Q_urgentLevel_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "发文人",
					name : "Q_issuer_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "发文人ID",
					name : "Q_issuerId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "主题词",
					name : "Q_keywords_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "公文来源",
					name : "Q_sources_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "0=发文 1=收文",
					name : "Q_archType_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "",
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
		this.store = new Ext.data.JsonStore(
				{
					url : __ctxPath + "/archive/listArchives.do",
					root : "result",
					totalProperty : "totalCounts",
					remoteSort : true,
					fields : [ {
						name : "archivesId",
						type : "int"
					}, "typeId", "typeName", "archivesNo", "issueDep", "depId",
							"subject", "issueDate", "status", "shortContent",
							"fileCounts", "privacyLevel", "urgentLevel",
							"issuer", "issuerId", "keywords", "sources",
							"archType", "createtime" ]
				});
		this.store.setDefaultSort("archivesId", "desc");
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
				header : "archivesId",
				dataIndex : "archivesId",
				hidden : true
			}, {
				header : "公文类型",
				dataIndex : "typeId"
			}, {
				header : "公文类型名称",
				dataIndex : "typeName"
			}, {
				header : "发文字号",
				dataIndex : "archivesNo"
			}, {
				header : "发文机关或部门",
				dataIndex : "issueDep"
			}, {
				header : "发文部门ID",
				dataIndex : "depId"
			}, {
				header : "文件标题",
				dataIndex : "subject"
			}, {
				header : "发布日期",
				dataIndex : "issueDate"
			}, {
				header : "公文状态",
				dataIndex : "status"
			}, {
				header : "内容简介",
				dataIndex : "shortContent"
			}, {
				header : "文件数",
				dataIndex : "fileCounts"
			}, {
				header : "秘密等级",
				dataIndex : "privacyLevel"
			}, {
				header : "紧急程度",
				dataIndex : "urgentLevel"
			}, {
				header : "发文人",
				dataIndex : "issuer"
			}, {
				header : "发文人ID",
				dataIndex : "issuerId"
			}, {
				header : "主题词",
				dataIndex : "keywords"
			}, {
				header : "公文来源",
				dataIndex : "sources"
			}, {
				header : "0=发文 1=收文",
				dataIndex : "archType"
			}, {
				header : "",
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
				text : "添加[Archives]",
				xtype : "button",
				handler : this.createRecord
			}, {
				iconCls : "btn-del",
				text : "删除[Archives]",
				xtype : "button",
				handler : this.delRecords,
				scope : this
			} ]
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ArchivesGrid",
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
				new ArchivesForm(e.data.archivesId).show();
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
		new ArchivesForm().show();
	},
	delByIds : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/archive/multiDelArchives.do",
					params : {
						ids : a
					},
					method : "POST",
					success : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "成功删除该[Archives]！");
						Ext.getCmp("ArchivesGrid").getStore().reload();
					},
					failure : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var c = Ext.getCmp("ArchivesGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var d = Array();
		for ( var b = 0; b < a.length; b++) {
			d.push(a[b].data.archivesId);
		}
		this.delByIds(d);
	},
	editRecord : function(a) {
		new ArchivesForm( {
			archivesId : a.data.archivesId
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.archivesId);
			break;
		case "btn-edit":
			this.editRecord(a);
			break;
		default:
			break;
		}
	}
});