Ext.ns("CheckBorrowFileListView");
CheckBorrowFileListView = Ext.extend(Ext.Panel, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		CheckBorrowFileListView.superclass.constructor.call(this, {
			layout : "fit",
			border : true,
			items : [ this.gridPanel ],
			frame : false
		});
	},
	initUIComponents : function() {
		var b = [ {
			name : "listId",
			type : "int"
		}, "recordId", "listType", "archFond", "afNo", "afName", "archRoll",
				"rollNo", "rolllName", "rollFile", "fileNo", "fileName" ];
		this.store = new Ext.data.Store( {
			proxy : new Ext.data.HttpProxy( {
				url : __ctxPath + "/arch/listCheckBorrowFileList.do",
				method : "GET"
			}),
			reader : new Ext.data.JsonReader( {
				root : "result",
				totalProperty : "totalCounts",
				remoteSort : false,
				idProperty : "listId",
				fields : b
			})
		});
		var a = new Ext.grid.ColumnModel( {
			columns : [ new Ext.grid.RowNumberer(), {
				header : "listId",
				dataIndex : "listId",
				hidden : true
			}, {
				header : "借阅单位",
				dataIndex : "listType"
			}, {
				header : "全宗ID",
				dataIndex : "archFond",
				hidden : true,
				renderer : function(c) {
					if (c) {
						return c.archFondId;
					}
				}
			}, {
				header : "全宗号",
				dataIndex : "archFond",
				renderer : function(c) {
					if (c) {
						return c.afNo;
					}
				}
			}, {
				header : "全宗名",
				dataIndex : "archFond",
				renderer : function(c) {
					if (c) {
						return c.afName;
					}
				}
			}, {
				header : "案卷ID",
				hidden : true,
				dataIndex : "archRoll",
				renderer : function(c) {
					if (c) {
						return c.rollId;
					}
				}
			}, {
				header : "案卷号",
				dataIndex : "archRoll",
				renderer : function(c) {
					if (c) {
						return c.rollNo;
					}
				}
			}, {
				header : "案卷名",
				dataIndex : "archRoll",
				renderer : function(c) {
					if (c) {
						return c.rolllName;
					}
				}
			}, {
				header : "文件ID",
				hidden : true,
				dataIndex : "rollFile",
				renderer : function(c) {
					if (c) {
						return c.rollFileId;
					}
				}
			}, {
				header : "文件号",
				dataIndex : "fileNo"
			}, {
				header : "文件题名",
				dataIndex : "fileName"
			} ],
			defaults : {
				sortable : true,
				menuDisabled : false
			}
		});
		this.topbar = new Ext.Toolbar( {
			items : [ {
				xtype : "tbtext",
				text : "借阅清单"
			}, {
				xtype : "tbseparator"
			} ]
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			frame : false,
			border : false,
			tbar : this.topbar,
			store : this.store,
			cm : a,
			viewConfig : {
				forceFit : true,
				autoFill : true
			},
			bbar : new HT.PagingBar( {
				store : this.store
			}),
			listeners : {}
		});
	},
	createFond : function() {
	},
	createRoll : function() {
	},
	createFile : function() {
	},
	removeByIds : function(a) {
	},
	removeSelRs : function() {
	}
});