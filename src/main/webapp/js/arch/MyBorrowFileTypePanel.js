Ext.ns("MyBorrowFileTypePanel");
MyBorrowFileTypePanel = Ext.extend(Ext.Panel, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		MyBorrowFileTypePanel.superclass.constructor.call(this, {
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
				"rollNo", "rolllName", "rollFile", "fileNo", "fileName", {
					name : "preview",
					type : "boolean"
				} ];
		this.store = new Ext.data.Store( {
			proxy : new Ext.data.HttpProxy( {
				url : __ctxPath + "/arch/listCheckBorrowFileList.do",
				method : "POST"
			}),
			reader : new Ext.data.JsonReader( {
				root : "result",
				totalProperty : "totalCounts",
				remoteSort : false,
				idProperty : "listId",
				fields : b
			})
		});
		this.store.on("load", function(d, c) {
			Ext.each(c, function(e) {
				if (e.get("listType") == "文件") {
					e.set("preview", false);
				} else {
					e.set("preview", true);
				}
				e.commit();
			});
			d.commitChanges();
		});
		this.rowActions = new Ext.ux.grid.RowActions( {
			header : "管理",
			width : 80,
			actions : [ {
				iconCls : "btn-showDetail",
				qtip : "查看详细",
				style : "margin:0 3px 0 3px"
			}, {
				iconCls : "btn-readdocument",
				qtip : "预览附件",
				style : "margin:0 3px 0 3px",
				hideIndex : "preview"
			} ]
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
				dataIndex : "rollFile",
				renderer : function(c) {
					if (c) {
						return c.fileNo;
					}
				}
			}, {
				header : "文件题名",
				dataIndex : "rollFile",
				renderer : function(c) {
					if (c) {
						return c.fileName;
					}
				}
			}, this.rowActions ],
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
			store : this.store,
			cm : a,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true,
				autoFill : true
			},
			bbar : new HT.PagingBar( {
				store : this.store
			}),
			listeners : {}
		});
	}
});