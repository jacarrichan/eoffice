Ext.ns("DepreRecordView");
DepreRecordView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		DepreRecordView.superclass.constructor.call(this, {
			id : "DepreRecordView",
			title : "固定资产折旧记录列表",
			iconCls : "menu-depRecord",
			region : "center",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel( {
			id : "DepreRecordSearchForm",
			region : "north",
			height : 40,
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
				text : "请输入查询条件:"
			}, {
				text : "资产名称"
			}, {
				xtype : "textfield",
				name : "Q_fixedAssets.assetsName_S_LK"
			}, {
				text : "折旧时间:从"
			}, {
				xtype : "datefield",
				name : "Q_calTime_D_GE",
				format : "Y-m-d",
				editable : false
			}, {
				text : "到:"
			}, {
				xtype : "datefield",
				name : "Q_calTime_D_LE",
				format : "Y-m-d",
				editable : false
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var b = Ext.getCmp("DepreRecordSearchForm");
					var c = Ext.getCmp("DepreRecordGrid");
					if (b.getForm().isValid()) {
						$search( {
							searchPanel : b,
							gridPanel : c
						});
					}
				}
			} ]
		});
		this.store = new Ext.data.Store( {
			proxy : new Ext.data.HttpProxy( {
				url : __ctxPath + "/admin/listDepreRecord.do"
			}),
			reader : new Ext.data.JsonReader( {
				root : "result",
				totalProperty : "totalCounts",
				id : "id",
				fields : [ {
					name : "recordId",
					type : "int"
				}, {
					name : "assets",
					mapping : "fixedAssets.assetsName"
				}, "workCapacity", "depreAmount", "calTime", {
					name : "workGrossUnit",
					mapping : "fixedAssets.workGrossUnit"
				}, {
					name : "depType",
					mapping : "fixedAssets.depreType.typeName"
				} ]
			}),
			remoteSort : true
		});
		this.store.setDefaultSort("recordId", "desc");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		var a = new Ext.grid.ColumnModel( {
			columns : [ new Ext.grid.RowNumberer(), {
				header : "recordId",
				dataIndex : "recordId",
				hidden : true
			}, {
				header : "资产名称",
				dataIndex : "assets"
			}, {
				header : "折算类型",
				dataIndex : "depType"
			}, {
				header : "工作量",
				id : "workCapacity",
				dataIndex : "workCapacity",
				renderer : function(f, d, b, g, c) {
					if (f != null) {
						var e = b.data.workGrossUnit;
						return f + " " + e;
					} else {
						return "";
					}
				}
			}, {
				header : "折旧值",
				dataIndex : "depreAmount"
			}, {
				header : "计算时间",
				dataIndex : "calTime"
			} ],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "DepreRecordGrid",
			store : this.store,
			region : "center",
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			autoHeight : true,
			cm : a,
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			bbar : new Ext.PagingToolbar( {
				pageSize : 25,
				store : this.store,
				displayInfo : true,
				displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
	}
});