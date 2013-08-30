RollFileListView = Ext.extend(Ext.grid.EditorGridPanel, {
	viewConfig : null,
	startIndex : 0,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		RollFileListView.superclass.constructor.call(this, {
			id : "RollFileListView",
			tbar : this.topbar,
			store : this.store,
			height : 200,
			plugins : this.rowActions,
			trackMouseOver : true,
			autoScroll : true,
			disableSelection : false,
			loadMask : true,
			clicksToEdit : 1,
			frame : false,
			cm : this.cm,
			sm : this.sm,
			autoExpandColumn : "shortDesc",
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			listeners : {
				"rowdblclick" : this.rowClick
			}
		});
	},
	initUIComponents : function() {
		this.topbar = new Ext.Toolbar( {
			items : [ {
				xtype : "tbtext",
				text : "附件"
			}, {
				xtype : "tbseparator"
			}, {
				iconCls : "btn-add",
				text : "添加",
				xtype : "button",
				scope : this,
				handler : this.createRs
			}, {
				iconCls : "btn-del",
				text : "删除",
				xtype : "button",
				scope : this,
				handler : this.removeSelRs
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
			}, {
				iconCls : "btn-up",
				qtip : "向上",
				style : "margin:0 3px 0 3px"
			}, {
				iconCls : "btn-last",
				qtip : "向下",
				style : "margin:0 3px 0 3px"
			} ]
		});
		this.rowActions.on("action", this.onRowAction, this);
		this.sm = new Ext.grid.CheckboxSelectionModel();
		this.cm = new Ext.grid.ColumnModel( {
			columns : [ this.sm, {
				header : "listId",
				dataIndex : "listId",
				hidden : true
			}, {
				header : "序号",
				width : 5,
				dataIndex : "sn",
				renderer : function(e, b, a, f, d, c) {
					a.data["sn"] = f + 1;
					c.commitChanges();
					return a.data["sn"];
				}
			}, {
				header : "文件名称",
				width : 20,
				dataIndex : "fileAttach",
				renderer : function(a) {
					return a.fileName;
				}
			}, {
				header : "概要",
				dataIndex : "shortDesc",
				editor : {
					allowBlank : false,
					xtype : "textarea"
				}
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
	rowClick : function(b, a, c) {
	},
	createRs : function() {
		App.createUploadDialog(
				{
					file_cat : "arch/upload",
					callback : function(g) {
						var e = Ext.getCmp("RollFileListView");
						var b = e.getStore();
						e.stopEditing();
						for (i = 0; i < g.length; i++) {
							var a = g[i].fileId;
							var h = g[i].filename;
							var d = g[i].filepath;
							var f = new b.recordType();
							f.data = {};
							f.data["downLoads"] = 0;
							f.data["shortDesc"] = "";
							f.data[""] = this.rollFileId == null ? ""
									: this.rollFileId;
							var c = {};
							Ext.applyIf(c, {
								fileId : a,
								fileName : h,
								filePath : d
							});
							f.data["fileAttach"] = c;
							b.insert(b.getCount(), f);
							f.markDirty();
						}
						b.commitChanges();
						e.getView().refresh();
						e.startEditing(0, 0);
					}
				}).show();
	},
	removeByIds : function(b, a) {
		Ext.Ajax.request( {
			url : __ctxPath + "/arch/multiDelRollFileList.do",
			params : {
				listIds : b,
				fileIds : a
			},
			method : "POST",
			success : function(c, d) {
				Ext.ux.Toast.msg("操作信息", "成功删除该明细！");
				Ext.getCmp("RollFileListView").getStore().reload();
			},
			failure : function(c, d) {
				Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
			}
		});
	},
	removeSelRs : function() {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(f) {
			if (f == "yes") {
				var e = Ext.getCmp("RollFileListView");
				var b = e.getStore();
				var a = e.getSelectionModel().getSelections();
				if (a.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var g = Array();
				var c = Array();
				for ( var d = 0; d < a.length; d++) {
					if (a[d].data.listId != "" && a[d].data.listId != null) {
						g.push(a[d].data.listId);
					} else {
						c.push(a[d].data.fileAttach.fileId);
					}
				}
				this.removeByIds(g, c);
			}
		}, this);
	},
	editRs : function(a) {
		var b = Ext.getCmp("RollFileListView");
		var a = b.getSelectionModel().getSelections();
		new RollFileListForm( {
			listId : a[0].data.listId
		}).show();
	},
	saveSelRs : function() {
	},
	downLoad : function(a) {
		window.open(__ctxPath + "/attachFiles/" + a.data.fileAttach.filePath);
		a.data.downLoads = a.data.downLoads + 1;
		Ext.getCmp("RollFileFormWin").save();
	},
	sn : function(e, h, f) {
		var b = e.getStore();
		var a = b.getAt(h);
		var d = b.getAt(f);
		if (a && d) {
			var g = a.get("sn");
			var c = d.get("sn");
			a.data.sn = c;
			d.data.sn = g;
			Ext.getCmp("RollFileFormWin").save("sn");
			e.getView().refresh();
		}
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
		case "btn-up":
			this.sn.call(this, c, e, e - 1);
			break;
		case "btn-last":
			this.sn.call(this, c, e, e + 1);
			break;
		default:
			break;
		}
	}
});