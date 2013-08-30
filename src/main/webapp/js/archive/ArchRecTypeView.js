ArchRecTypeView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchRecTypeView.superclass.constructor.call(this, {
			id : "ArchRecTypeView",
			title : "来文分类管理",
			iconCls : "menu-arch-rec-type",
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
				text : "分类名称"
			}, {
				name : "Q_typeName_S_LK",
				xtype : "textfield",
				width : 100
			}, {
				text : "部门名称"
			}, {
				name : "Q_department.depName_S_LK",
				xtype : "textfield",
				width : 100
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : this.search.createCallback(this)
			} ]
		});
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/archive/listArchRecType.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "recTypeId",
				type : "int"
			}, "typeName", {
				name : "depName",
				mapping : "department.depName"
			} ]
		});
		this.store.setDefaultSort("recTypeId", "desc");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		var b = new Array();
		if (isGranted("_ArchRecTypeDel")) {
			b.push( {
				iconCls : "btn-del",
				qtip : "删除",
				style : "margin:0 3px 0 3px"
			});
		}
		if (isGranted("_ArchRecTypeEdit")) {
			b.push( {
				iconCls : "btn-edit",
				qtip : "编辑",
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
				header : "recTypeId",
				dataIndex : "recTypeId",
				hidden : true
			}, {
				header : "分类名称",
				dataIndex : "typeName"
			}, {
				header : "所属部门",
				dataIndex : "depName"
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
		if (isGranted("_ArchRecTypeAdd")) {
			this.topbar.add( {
				iconCls : "btn-add",
				text : "添加分类",
				xtype : "button",
				handler : this.createRecord
			});
		}
		if (isGranted("_ArchRecTypeDel")) {
			this.topbar.add( {
				iconCls : "btn-del",
				text : "删除分类",
				xtype : "button",
				handler : this.delRecords,
				scope : this
			});
		}
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ArchRecTypeGrid",
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
				new ArchRecTypeForm( {
					recTypeId : e.data.recTypeId
				}).show();
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
		new ArchRecTypeForm().show();
	},
	delByIds : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/archive/multiDelArchRecType.do",
					params : {
						ids : a
					},
					method : "POST",
					success : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "成功删除该！");
						Ext.getCmp("ArchRecTypeGrid").getStore().reload();
					},
					failure : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var c = Ext.getCmp("ArchRecTypeGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var d = Array();
		for ( var b = 0; b < a.length; b++) {
			d.push(a[b].data.recTypeId);
		}
		this.delByIds(d);
	},
	editRecord : function(a) {
		new ArchRecTypeForm( {
			recTypeId : a.data.recTypeId
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.recTypeId);
			break;
		case "btn-edit":
			this.editRecord(a);
			break;
		default:
			break;
		}
	}
});