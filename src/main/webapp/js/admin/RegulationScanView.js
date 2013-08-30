RegulationScanView = Ext.extend(Ext.Panel, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		RegulationScanView.superclass.constructor.call(this, {
			id : "RegulationScanView",
			title : "规章制度",
			region : "center",
			iconCls : "menu-regulation",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new HT.SearchPanel( {
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
				labelWidth : 75,
				xtype : "label"
			},
			items : [ {
				text : "标题"
			}, {
				name : "Q_subject_S_EQ",
				xtype : "textfield"
			}, {
				text : "日期  从"
			}, {
				name : "Q_issueDate_D_GE",
				xtype : "datefield",
				format : "Y-m-d"
			}, {
				text : "至"
			}, {
				name : "Q_issueDate_D_LE",
				xtype : "datefield",
				format : "Y-m-d"
			}, {
				text : "发布部门"
			}, {
				name : "Q_issueDep_S_EQ",
				xtype : "textfield"
			}, {
				text : "关键字"
			}, {
				name : "Q_keywords_S_EQ",
				xtype : "textfield"
			}, {
				text : "查询",
				xtype : "button",
				scope : this,
				iconCls : "btn-search",
				handler : this.search
			}, {
				text : "重置",
				xtype : "button",
				scope : this,
				iconCls : "btn-reset",
				handler : this.reset
			} ]
		});
		this.topbar = new Ext.Toolbar( {
			items : []
		});
		this.gridPanel = new HT.GridPanel( {
			region : "center",
			tbar : this.topbar,
			height : 500,
			rowActions : true,
			id : "RegulationGrid",
			url : __ctxPath + "/admin/scanRegulation.do",
			fields : [ {
				name : "regId",
				type : "int"
			}, "subject", "issueDate", "issueUserId", "issueFullname",
					"issueDepId", "issueDep", "recDeps", "recDepIds",
					"recUsers", "recUserIds", "keywords", "status",
					"globalType" ],
			columns : [ {
				header : "regId",
				dataIndex : "regId",
				hidden : true
			}, {
				header : "类型",
				dataIndex : "globalType",
				renderer : function(a) {
					if (a != null) {
						return a.typeName;
					} else {
						return "";
					}
				}
			}, {
				header : "标题",
				dataIndex : "subject"
			}, {
				header : "发布日期",
				dataIndex : "issueDate",
				renderer : function(a) {
					if (a != null) {
						return a.substring(0, 10);
					} else {
						return "";
					}
				}
			}, {
				header : "发布人",
				dataIndex : "issueFullname"
			}, {
				header : "发布部门",
				dataIndex : "issueDep"
			}, {
				header : "接收部门范围",
				dataIndex : "recDeps"
			}, {
				header : "接收人范围",
				dataIndex : "recUsers"
			}, {
				header : "关键字",
				dataIndex : "keywords"
			}, {
				header : "状态",
				dataIndex : "status",
				renderer : function(a) {
					if (a != null && a == 1) {
						return '<font color="green">生效</font>';
					} else {
						return '<font color="red">草稿</font>';
					}
				}
			}, new Ext.ux.grid.RowActions( {
				header : "管理",
				width : 100,
				actions : [ {
					iconCls : "btn-suggest-scan",
					qtip : "查看",
					stype : "margin:0 3px 0 3px"
				} ],
				listeners : {
					scope : this,
					"action" : this.onRowAction
				}
			}) ]
		});
		this.gridPanel.addListener("rowdblclick", this.rowClick);
	},
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	search : function() {
		$search( {
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	},
	rowClick : function(b, a, c) {
		b.getSelectionModel().each(function(d) {
			new RegulationScanWin( {
				regId : d.data.regId
			}).show();
		});
	},
	scanRs : function(a) {
		new RegulationScanWin( {
			regId : a.data.regId
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-suggest-scan":
			this.scanRs.call(this, a);
			break;
		default:
			break;
		}
	}
});