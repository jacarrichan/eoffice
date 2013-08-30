MyBorrowFileSlaveListGrid = Ext.extend(Ext.grid.GridPanel, {
	viewConfig : null,
	startIndex : 0,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		MyBorrowFileSlaveListGrid.superclass.constructor.call(this, {
			tbar : this.topbar,
			store : this.store,
			plugins : this.rowActions,
			trackMouseOver : true,
			autoScroll : true,
			disableSelection : false,
			loadMask : true,
			frame : false,
			cm : this.cm,
			autoExpandColumn : "shortDesc",
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			listeners : {}
		});
	},
	initUIComponents : function() {
		this.topbar = new Ext.Toolbar( {
			items : [ {
				xtype : "tbtext",
				text : "附件"
			}, {
				xtype : "tbseparator"
			} ]
		});
		this.store = new Ext.data.Store( {
			proxy : new Ext.data.HttpProxy( {
				url : __ctxPath + "/arch/listRollFileList.do"
			}),
			autoLoad : false,
			reader : new Ext.data.JsonReader( {
				root : "result",
				totalProperty : "totalCounts",
				id : "listId",
				fields : [ {
					name : "listId",
					type : "int"
				}, "downLoads", "sn", "shortDesc", "rollFileId", "fileAttach" ]
			}),
			remoteSort : true
		});
		this.store.setDefaultSort("sn", "asc");
		this.store.on("load", function(b, a) {
			this.viewConfig = [];
			Ext.each(a, function(c) {
				this.viewConfig.push( {
					fileName : c.data.fileAttach.fileName,
					filePath : c.data.fileAttach.filePath
				});
			}, this);
		}, this);
		this.rowActions = new Ext.ux.grid.RowActions( {
			header : "管理",
			width : 15,
			actions : [ {
				iconCls : "btn-readdocument",
				qtip : "预览",
				style : "margin:0 3px 0 3px"
			}, {
				iconCls : "btn-downLoad",
				qtip : "下载",
				style : "margin:0 3px 0 3px"
			} ]
		});
		this.rowActions.on("action", this.onRowAction, this);
		this.cm = new Ext.grid.ColumnModel( {
			columns : [ {
				header : "listId",
				dataIndex : "listId",
				hidden : true
			}, {
				header : "序号",
				width : 5,
				dataIndex : "sn"
			}, {
				header : "文件名称",
				width : 20,
				dataIndex : "fileAttach",
				renderer : function(a) {
					if (a) {
						return a.fileName;
					}
				}
			}, {
				header : "概要",
				dataIndex : "shortDesc"
			}, {
				header : "下载次数",
				width : 10,
				dataIndex : "downLoads"
			}, this.rowActions ],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 40
			}
		});
	},
	downLoad : function(a) {
		window.open(__ctxPath + "/attachFiles/" + a.data.fileAttach.filePath);
		a.data.downLoads = a.data.downLoads + 1;
		var c = a.data["fileAttach"];
		Ext.apply(c, {
			createtime : new Date(c.createtime).format("Y-m-d")
		});
		var e = {};
		Ext.apply(e, {
			rollFileId : this.rollFileId
		});
		Ext.apply(a.data, {
			rollFile : e
		});
		a.markDirty();
		var d = [];
		d.push(a.data);
		var b = this.store;
		Ext.Ajax.request( {
			url : __ctxPath + "/arch/updateDownLoadRollFile.do",
			method : "POST",
			success : function(f, g) {
				b.reload();
			},
			failure : function(f, g) {
			},
			params : {
				rollFileId : this.rollFileId,
				params : Ext.encode(d)
			}
		});
	},
	sn : function(a, c, b) {
	},
	viewFile : function(a) {
		new ViewFileWindow( {
			viewConfig : this.viewConfig,
			startIndex : a
		}).show();
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-readdocument":
			this.viewFile.call(this, e);
			break;
		case "btn-downLoad":
			this.downLoad.call(this, a);
			break;
		}
	}
});