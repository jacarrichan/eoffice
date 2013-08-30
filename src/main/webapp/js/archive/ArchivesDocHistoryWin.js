ArchivesDocHistoryWin = Ext.extend(Ext.Window, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	buttons : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchivesDocHistoryWin.superclass.constructor.call(this, {
			id : "ArchivesDocHistoryWin",
			iconCls : "menu-archive-history",
			title : "版本管理",
			layout : "border",
			modal : true,
			height : 400,
			border : false,
			width : 600,
			bottons : this.bottons,
			buttonAlign : "center",
			items : [ this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/archive/listDocHistory.do",
			root : "result",
			baseParams : {
				"Q_archivesDoc.docId_L_EQ" : this.docId
			},
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "historyId",
				type : "int"
			}, "archivesDoc", "fileAttach", "docName", "path", "version",
					"updatetime", "mender" ]
		});
		this.store.setDefaultSort("historyId", "desc");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		var b = new Ext.grid.CheckboxSelectionModel( {
			singleSelect : true
		});
		var a = new Ext.grid.ColumnModel( {
			columns : [ b, new Ext.grid.RowNumberer(), {
				header : "historyId",
				dataIndex : "historyId",
				hidden : true
			}, {
				header : "所属附件",
				dataIndex : "fileAttach",
				renderer : function(c) {
					return c.fileName;
				}
			}, {
				header : "文档名称",
				dataIndex : "docName"
			}, {
				header : "版本",
				dataIndex : "version"
			}, {
				header : "路径",
				dataIndex : "path"
			}, {
				header : "更新时间",
				dataIndex : "updatetime"
			}, {
				header : "修改人",
				dataIndex : "mender"
			} ],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "DocHistoryGrid",
			region : "center",
			stripeRows : true,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
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
				new DocHistoryForm(e.data.historyId).show();
			});
		});
		this.buttons = [ {
			iconCls : "btn-archive-history",
			text : "直接覆盖",
			xtype : "button",
			handler : this.copy.createCallback(this.gridPanel, this),
			scope : this
		}, {
			iconCls : "btn-archive-copy",
			text : "修改覆盖",
			xtype : "button",
			handler : this.modify.createCallback(this.gridPanel, this),
			scope : this
		}, {
			iconCls : "btn-del",
			text : "关闭",
			xtype : "button",
			handler : this.colseWIn.createCallback(this),
			scope : this
		} ];
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
		new DocHistoryForm().show();
	},
	copy : function(a, b) {
		var c = a.getSelectionModel().getSelections();
		var d = b.callback;
		Ext.Ajax.request( {
			url : __ctxPath + "/archive/copyArchivesDoc.do",
			params : {
				historyId : c[0].data.historyId
			},
			scope : this,
			method : "post",
			success : function(f) {
				var e = Ext.util.JSON.decode(f.responseText);
				d.call(this, e.data);
				b.close();
				Ext.ux.Toast.msg("操作信息", "success");
			},
			failure : function() {
				Ext.ux.Toast.msg("操作信息", "操作出错,请联系管理员");
			}
		});
	},
	colseWIn : function(a) {
		a.close();
	},
	modify : function(a, f) {
		var d = a.getSelectionModel().getSelections();
		if (d.length == 0) {
			Ext.Msg.alert("信息", "请选择要查看的文档！");
			return;
		}
		var e = d[0];
		var j = e.data.path;
		var b = e.data.archivesDoc.docId;
		var h = Ext.getCmp("archiveDocGrid").getStore();
		var c = e.data.fileAttach.fileId;
		var g = Ext.getCmp("ArchivesDraftWin");
		var i = function(k) {
			h.reload();
			f.close();
		};
		new ArchivesDocForm( {
			docId : b,
			docPath : j,
			fileId : c,
			callback : i
		}).show();
	}
});