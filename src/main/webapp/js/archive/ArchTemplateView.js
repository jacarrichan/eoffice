ArchTemplateView = Ext.extend(Ext.Panel, {
	typeId : null,
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchTemplateView.superclass.constructor.call(this, {
			title : "公文模板管理",
			region : "center",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel( {
			layout : "column",
			region : "north",
			height : 40,
			bodyStyle : "padding:6px 10px 6px 10px",
			border : false,
			defaults : {
				border : false
			},
			items : [ {
				columnWidth : 0.6,
				layout : "form",
				items : {
					fieldLabel : "模板名称",
					name : "Q_tempName_S_LK",
					anchor : "96%,96%",
					xtype : "textfield"
				}
			}, {
				columnWidth : 0.2,
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
			url : __ctxPath + "/archive/listArchTemplate.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "templateId",
				type : "int"
			}, "archivesType", "tempName", "tempPath", "fileId" ]
		});
		this.store.setDefaultSort("templateId", "desc");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		var b = [];
		if (this.allowEdit) {
			if (isGranted("_ArchviesTempDel")) {
				b.push( {
					iconCls : "btn-del",
					qtip : "删除",
					style : "margin:0 3px 0 3px"
				});
			}
			if (isGranted("_ArchivesTempEdit")) {
				b.push( {
					iconCls : "btn-edit",
					qtip : "编辑",
					style : "margin:0 3px 0 3px"
				});
			}
		}
		if (isGranted("_ArchiveTypeTempQuery")) {
			b.push( {
				iconCls : "btn-readdocument",
				qtip : "查看",
				style : "margin:0 3px 0 3px"
			});
		}
		this.rowActions = new Ext.ux.grid.RowActions( {
			header : "管理",
			width : 80,
			actions : b
		});
		var c = null;
		if (this.singleSelect) {
			c = new Ext.grid.CheckboxSelectionModel( {
				singleSelect : true
			});
		} else {
			c = new Ext.grid.CheckboxSelectionModel();
		}
		var a = new Ext.grid.ColumnModel( {
			columns : [ c, new Ext.grid.RowNumberer(), {
				header : "templateId",
				dataIndex : "templateId",
				hidden : true
			}, {
				header : "所属类型",
				dataIndex : "archivesType",
				renderer : function(d) {
					if (d != null) {
						return d.typeName;
					}
				}
			}, {
				header : "模板名称",
				dataIndex : "tempName"
			}, {
				header : "文件路径",
				hidden : true,
				dataIndex : "tempPath"
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
		if (isGranted("_ArchivesTempAdd")) {
			this.topbar.add( {
				iconCls : "btn-add",
				text : "添加公文模板",
				xtype : "button",
				handler : this.createRecord
			});
		}
		if (isGranted("_ArchviesTempDel")) {
			this.topbar.add( {
				iconCls : "btn-del",
				text : "删除公文模板",
				xtype : "button",
				handler : this.delRecords,
				scope : this
			});
		}
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ArchTemplateGrid",
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
		this.gridPanel.addListener("rowdblclick", function(f, d, g) {
			f.getSelectionModel().each(function(e) {
				new ArchTemplateView(e.data.templateId).show();
			});
		});
		this.rowActions.on("action", this.onRowAction, this);
	},
	search : function(a) {
		if (a.searchPanel.getForm().isValid()) {
			a.searchPanel.getForm().submit( {
				waitMsg : "正在提交查询",
				url : __ctxPath + "/archive/listArchTemplate.do",
				params : {
					"Q_archivesType.typeId_L_EQ" : a.typeId
				},
				success : function(c, d) {
					var b = Ext.util.JSON.decode(d.response.responseText);
					a.gridPanel.getStore().loadData(b);
				}
			});
		}
	},
	createRecord : function() {
		new ArchTemplateForm().show();
	},
	delByIds : function(b, a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(c) {
			if (c == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/archive/multiDelArchTemplate.do",
					params : {
						ids : b
					},
					method : "POST",
					success : function(d, e) {
						Ext.ux.Toast.msg("操作信息", "成功删除所选公文分类！");
						a.getStore().reload();
					},
					failure : function(d, e) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var a = this.gridPanel.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var c = Array();
		for ( var b = 0; b < a.length; b++) {
			c.push(a[b].data.templateId);
		}
		this.delByIds(c, this.gridPanel);
	},
	editRecord : function(a) {
		new ArchTemplateForm( {
			templateId : a.data.templateId
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.templateId, c);
			break;
		case "btn-edit":
			this.editRecord(a);
			break;
		case "btn-readdocument":
			new OfficeTemplateView(a.data.fileId, a.data.tempPath);
			break;
		default:
			break;
		}
	},
	setTypeId : function(a) {
		if (a == 0) {
			this.typeId = null;
			return;
		}
		this.typeId = a;
	}
});