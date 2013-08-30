CheckBorrowRecordView = Ext.extend(Ext.Panel, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		CheckBorrowRecordView.superclass.constructor.call(this, {
			id : "CheckBorrowRecordView",
			title : "借阅审批",
			region : "center",
			layout : "border",
			defaults : {
				anchor : "96%,96%"
			},
			listeners : {
				"afterlayout" : function(b) {
					b.search();
				}
			},
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new HT.SearchPanel( {
			id : "CheckBorrowRecordViewSearchPanel",
			layout : "form",
			region : "north",
			defaults : {
				anchor : "96%"
			},
			colNums : 6,
			items : [
					{
						fieldLabel : "借阅目的",
						name : "Q_borrowReason_S_LK",
						flex : 1,
						editable : true,
						lazyInit : false,
						forceSelection : false,
						xtype : "diccombo",
						itemName : "借阅目的"
					},
					{
						fieldLabel : "借阅状态",
						hiddenName : "Q_returnStatus_SN_EQ",
						flex : 1,
						xtype : "combo",
						mode : "local",
						value : "0",
						editable : false,
						triggerAction : "all",
						store : [ [ "", "全部" ], [ "0", "申请" ], [ "1", "通过" ],
								[ "-1", "驳回" ], [ "2", "归还" ] ]
					}, {
						fieldLabel : "借阅时间  从",
						name : "Q_borrowDate_D_GE",
						flex : 1,
						xtype : "datefield",
						format : "Y-m-d"
					}, {
						fieldLabel : "到",
						name : "Q_borrowDate_D_LE",
						flex : 1,
						xtype : "datefield",
						format : "Y-m-d"
					}, {
						fieldLabel : "应还日期  从",
						name : "Q_returnDate_D_GE",
						flex : 1,
						xtype : "datefield",
						format : "Y-m-d"
					}, {
						fieldLabel : "到",
						name : "Q_returnDate_D_LE",
						flex : 1,
						xtype : "datefield",
						format : "Y-m-d"
					} ],
			buttons : [ {
				text : "查询",
				scope : this,
				iconCls : "btn-search",
				handler : this.search
			}, {
				text : "重置",
				scope : this,
				iconCls : "btn-reset",
				handler : this.reset
			} ]
		});
		this.topbar = new Ext.Toolbar( {
			items : [ {
				iconCls : "btn-ok",
				text : "审批",
				xtype : "button",
				scope : this,
				handler : this.check
			} ]
		});
		this.fileRecord = Ext.data.Record.create( [ {
			name : "recordId",
			type : "int"
		}, "borrowDate", "borrowType", "borrowReason", "checkUserId",
				"checkUserName", "checkDate", "returnDate", "returnStatus",
				"borrowNum", "checkUserName" ]);
		this.memoryProxy = new Ext.data.HttpProxy( {
			url : __ctxPath + "/arch/listBorrowRecord.do"
		});
		this.jsonReader = new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			idProperty : "rollFileId"
		}, this.fileRecord);
		this.mystore = new Ext.data.Store( {
			proxy : this.memoryProxy,
			reader : this.jsonReader
		});
		this.gridPanel = new HT.GridPanel( {
			region : "center",
			tbar : this.topbar,
			rowActions : true,
			id : "CheckBorrowRecordGrid",
			viewConfig : {
				forceFit : true,
				autoFill : true
			},
			defaults : {
				anchor : "96%"
			},
			store : this.mystore,
			columns : [ {
				header : "recordId",
				dataIndex : "recordId",
				hidden : true
			}, {
				header : "借阅编号",
				dataIndex : "borrowNum"
			}, {
				header : "借阅人",
				dataIndex : "checkUserName"
			}, {
				header : "借阅日期",
				width : 60,
				dataIndex : "borrowDate"
			}, {
				header : "应还日期",
				width : 60,
				dataIndex : "returnDate"
			}, {
				header : "借阅方式",
				width : 60,
				dataIndex : "borrowType"
			}, {
				header : "借阅目的",
				width : 60,
				dataIndex : "borrowReason"
			}, {
				header : "归还状态",
				width : 60,
				dataIndex : "returnStatus",
				renderer : function(a) {
					switch (a) {
					case 0:
						return "申请";
						break;
					case 1:
						return "通过";
						break;
					case -1:
						return "驳回";
						break;
					case 2:
						return "归还";
						break;
					}
				}
			}, new Ext.ux.grid.RowActions( {
				header : "管理",
				width : 100,
				actions : [ {
					iconCls : "btn-ok",
					qtip : "审批",
					style : "margin:0 3px 0 3px"
				}, {} ],
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
			new CheckBorrowRecordForm( {
				recordId : d.data.recordId,
				returnStatus : d.data.returnStatus,
				borrowNum : d.data.borrowNum
			}).show();
		});
	},
	check : function() {
		var a = this.gridPanel.getSelectionModel().getSelections()[0];
		new CheckBorrowRecordForm( {
			recordId : a.data.recordId,
			returnStatus : a.data.returnStatus,
			borrowNum : a.data.borrowNum
		}).show();
	},
	editRs : function(a) {
		new CheckBorrowRecordForm( {
			recordId : a.data.recordId,
			returnStatus : a.data.returnStatus,
			borrowNum : a.data.borrowNum
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-ok":
			this.editRs.call(this, a);
			break;
		default:
			break;
		}
	}
});