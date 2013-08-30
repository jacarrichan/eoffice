ArchivesAttendView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchivesAttendView.superclass.constructor.call(this, {
			id : "ArchivesAttendView",
			title : "[ArchivesAttend]管理",
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
					name : "Q_archivesId_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "用户ID",
					name : "Q_userID_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "姓名",
					name : "Q_fullname_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "参与类型",
					name : "Q_attendType_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "执行时间",
					name : "Q_executeTime_S_LK",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.3,
				layout : "form",
				items : {
					fieldLabel : "备注",
					name : "Q_memo_S_LK",
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
			url : __ctxPath + "/archive/listArchivesAttend.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "attendId",
				type : "int"
			}, "archivesId", "userID", "fullname", "attendType", "executeTime",
					"memo" ]
		});
		this.store.setDefaultSort("attendId", "desc");
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
				header : "attendId",
				dataIndex : "attendId",
				hidden : true
			}, {
				header : "",
				dataIndex : "archivesId"
			}, {
				header : "用户ID",
				dataIndex : "userID"
			}, {
				header : "姓名",
				dataIndex : "fullname"
			}, {
				header : "参与类型",
				dataIndex : "attendType"
			}, {
				header : "执行时间",
				dataIndex : "executeTime"
			}, {
				header : "备注",
				dataIndex : "memo"
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
				text : "添加[ArchivesAttend]",
				xtype : "button",
				handler : this.createRecord
			}, {
				iconCls : "btn-del",
				text : "删除[ArchivesAttend]",
				xtype : "button",
				handler : this.delRecords,
				scope : this
			} ]
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ArchivesAttendGrid",
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
				new ArchivesAttendForm(e.data.attendId).show();
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
		new ArchivesAttendForm().show();
	},
	delByIds : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/archive/multiDelArchivesAttend.do",
					params : {
						ids : a
					},
					method : "POST",
					success : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "成功删除该[ArchivesAttend]！");
						Ext.getCmp("ArchivesAttendGrid").getStore().reload();
					},
					failure : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var c = Ext.getCmp("ArchivesAttendGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var d = Array();
		for ( var b = 0; b < a.length; b++) {
			d.push(a[b].data.attendId);
		}
		this.delByIds(d);
	},
	editRecord : function(a) {
		new ArchivesAttendForm( {
			attendId : a.data.attendId
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.attendId);
			break;
		case "btn-edit":
			this.editRecord(a);
			break;
		default:
			break;
		}
	}
});