MyBorrowRecordView = Ext.extend(Ext.Panel, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		MyBorrowRecordView.superclass.constructor.call(this, {
			id : "MyBorrowRecordView",
			title : "我的借阅",
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
			id : "MyBorrowRecordViewSearchPanel",
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
					}, {
						fieldLabel : "我:",
						name : "Q_appUser.userId_L_EQ",
						flex : 1,
						value : curUserInfo.userId,
						xtype : "hidden"
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
				iconCls : "btn-add",
				text : "添加",
				xtype : "button",
				scope : this,
				handler : this.createRs
			}, {
				iconCls : "btn-edit",
				text : "编辑",
				xtype : "button",
				scope : this,
				handler : this.editRs
			}, {
				iconCls : "btn-del",
				text : "删除",
				xtype : "button",
				scope : this,
				handler : this.removeSelRs
			} ]
		});
		this.fileRecord = Ext.data.Record.create( [ {
			name : "recordId",
			type : "int"
		}, "borrowDate", "borrowType", "borrowReason", "checkUserId",
				"checkUserName", "checkDate", "returnDate", "returnStatus",
				"borrowNum", "checkUserName", "viewFlag" ]);
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
		this.mystore.on("load", function(b, a, c) {
			Ext.each(a, function(d) {
				if (d.get("returnStatus") == 1) {
					d.set("viewFlag", false);
				} else {
					d.set("viewFlag", true);
				}
				d.commit(true);
			}, this);
			b.commitChanges();
		});
		this.gridPanel = new HT.GridPanel( {
			region : "center",
			tbar : this.topbar,
			rowActions : true,
			id : "MyBorrowRecordGrid",
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
					iconCls : "btn-showDetail",
					qtip : "查看",
					hideIndex : "viewFlag",
					style : "margin:0 3px 0 3px"
				}, {} ],
				listeners : {
					scope : this,
					"action" : this.onRowAction
				}
			}) ]
		});
	},
	createRs : function() {
		new BorrowRecordForm( {
			returnStatus : 0
		}).show();
	},
	removeSelRs : function() {
		$delGridRs( {
			url : __ctxPath + "/arch/multiDelBorrowRecord.do",
			grid : this.gridPanel,
			idName : "recordId"
		});
	},
	editRs : function(b) {
		var a = this.gridPanel.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要编辑的记录！");
			return;
		}
		new BorrowRecordForm( {
			recordId : a[0].data.recordId,
			returnStatus : a[0].data.returnStatus
		}).show();
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
		b.getSelectionModel().each(function(f) {
			var d = Ext.getCmp("centerTabPanel");
			var e = d.add(new MyBorrowFilePanel( {
				id : f.data.borrowNum,
				title : "我的借阅>>编号:" + f.data.borrowNum,
				recordId : f.data.recordId,
				borrowNum : f.data.borrowNum,
				showFlag : "view"
			}));
			d.activate(e);
		});
	},
	atcionViewDetail : function(c) {
		var a = Ext.getCmp("centerTabPanel");
		var b = a.add(new MyBorrowFilePanel( {
			id : c.data.borrowNum,
			title : "我的借阅>>编号:" + c.data.borrowNum,
			recordId : c.data.recordId,
			borrowNum : c.data.borrowNum,
			showFlag : "view"
		}));
		a.activate(b);
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-showDetail":
			this.atcionViewDetail.call(this, a);
			break;
		default:
			break;
		}
	}
});