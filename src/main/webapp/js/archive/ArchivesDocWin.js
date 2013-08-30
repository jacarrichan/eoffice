ArchivesDocWin = Ext.extend(Ext.Window, {
	formPanel : null,
	displayPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchivesDocWin.superclass.constructor.call(this, {
			id : "ArchivesDocWinWin",
			iconCls : "menu-attachment",
			layout : "form",
			items : [ this.docGridPanel ],
			modal : true,
			height : 400,
			width : 600,
			maximizable : true,
			title : "公文正文",
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/archive/listArchivesDoc.do?archivesId="
					+ this.archivesId,
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "docId",
				type : "int"
			}, "fileAttach", "creator", "creatorId", "menderId", "mender",
					"docName", "docStatus", "curVersion", "docPath",
					"updatetime", "createtime" ]
		});
		this.store.setDefaultSort("docId", "desc");
		if (this.archivesId != null && this.archivesId != ""
				&& this.archivesId != "undefined") {
			this.store.load();
		}
		this.toolbar = new Ext.Toolbar( {
			height : 30,
			items : [ {
				text : "删除附件文档",
				iconCls : "btn-del",
				scope : this,
				handler : this.deleteArchiveDoc
			}, "-", {
				text : "查看修改文档",
				iconCls : "menu-archive-issue-manage",
				scope : this,
				handler : this.detailArchivesDoc
			} ]
		});
		var a = new Ext.grid.CheckboxSelectionModel( {
			singleSelect : true
		});
		this.docGridPanel = new Ext.grid.EditorGridPanel( {
			columnWidth : 0.96,
			border : true,
			id : "archiveDocGridPanel",
			autoHeight : true,
			store : this.store,
			tbar : this.toolbar,
			sm : a,
			columns : [ new Ext.grid.RowNumberer(), a, {
				dataIndex : "docId",
				hidden : true
			}, {
				dataIndex : "fileAttach",
				hidden : true,
				renderer : function(b) {
					return b.fileName;
				}
			}, {
				dataIndex : "docStatus",
				hidden : true
			}, {
				dataIndex : "menderId",
				hidden : true
			}, {
				dataIndex : "creatorId",
				hidden : true
			}, {
				dataIndex : "docName",
				width : 150,
				header : "文档名称"
			}, {
				dataIndex : "docPath",
				header : "文档路径",
				width : 300
			}, {
				dataIndex : "curVersion",
				header : "当前版本",
				renderer : function(b) {
					return "第" + b + "版";
				}
			} ]
		});
		this.buttons = [ {
			text : "关闭",
			iconCls : "btn-cancel",
			handler : this.cancel.createCallback(this)
		} ];
	},
	cancel : function(a) {
		a.close();
	},
	deleteArchiveDoc : function() {
		var e = Ext.getCmp("archiveDocGridPanel");
		var b = e.getSelectionModel().getSelections();
		if (b.length == 0) {
			Ext.Msg.alert("信息", "请选择要查看的文档！");
			return;
		}
		var a = b[0];
		var c = e.getStore();
		var d = a.data.docId;
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(f) {
			if (f == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/archive/multiDelArchivesDoc.do",
					params : {
						ids : d
					},
					method : "POST",
					success : function(g, h) {
						Ext.ux.Toast.msg("操作信息", "成功删除该文档附件！");
						c.remove(a);
					},
					failure : function(g, h) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	detailArchivesDoc : function() {
		var f = Ext.getCmp("archiveDocGridPanel");
		var c = f.getSelectionModel().getSelections();
		if (c.length == 0) {
			Ext.Msg.alert("信息", "请选择要查看的文档！");
			return;
		}
		var b = c[0];
		var a = b.data.fileAttach.fileId;
		var g = b.data.docPath;
		var e = b.data.docId;
		var d = f.getStore();
		var h = function(i) {
			d.reload();
		};
		new ArchivesDocForm( {
			docId : e,
			fileId : a,
			docPath : g,
			callback : h
		}).show();
	}
});