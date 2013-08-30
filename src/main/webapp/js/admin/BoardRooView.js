Ext.ns("BoardRooView");
BoardRooView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		BoardRooView.superclass.constructor.call(this, {
			id : "BoardRooView",
			title : "会议室管理",
			iconCls : "menu-conference_boardRoom",
			region : "center",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel( {
			id : "BoardRooSearchForm",
			layout : "form",
			region : "north",
			width : "98%",
			height : 70,
			keys : {
				key : Ext.EventObject.ENTER,
				fn : this.search.createCallback(this),
				scope : this
			},
			items : [ {
				border : false,
				layout : "hbox",
				region : "center",
				layoutConfig : {
					padding : "5",
					align : "middle"
				},
				defaults : {
					xtype : "label",
					margins : {
						top : 0,
						right : 4,
						bottom : 0,
						left : 4
					}
				},
				items : [ {
					text : "请输入搜索条件："
				}, {
					text : "会议室名称："
				}, {
					name : "Q_roomName_S_EQ",
					xtype : "textfield",
					width : "20%",
					maxLength : 128,
					maxLengthtext : "会议室名称不能超过128个字符长度！"
				}, {
					text : "描述："
				}, {
					name : "Q_roomDesc_S_LK",
					xtype : "textfield",
					width : "20%",
					maxLength : 4000,
					maxLengthText : "会议室名称不能超过4000个字符长度！"
				}, {
					text : "容纳人数"
				}, {
					name : "Q_containNum_L_LE",
					xtype : "numberfield",
					width : "20%",
					maxLength : 6,
					maxLengthText : "会议室容量不能超过十万人！"
				} ]
			}, {
				layout : "hbox",
				region : "center",
				border : false,
				layoutConfig : {
					padding : "5px",
					align : "middle"
				},
				defaults : {
					xtype : "button",
					margins : {
						top : 0,
						right : 4,
						bottom : 0,
						left : 4
					}
				},
				items : [ {
					xtype : "label",
					text : " ",
					style : "padding-left:40%;"
				}, {
					text : "搜索",
					iconCls : "btn-search",
					handler : this.search.createCallback(this)
				}, {
					text : "重置",
					iconCls : "btn-reset",
					handler : this.reset
				} ]
			} ]
		});
		this.store = new Ext.data.Store( {
			proxy : new Ext.data.HttpProxy( {
				url : __ctxPath + "/admin/listBoardRoo.do"
			}),
			reader : new Ext.data.JsonReader( {
				root : "result",
				totalProperty : "totalCounts",
				id : "id",
				fields : [ {
					name : "roomId",
					type : "int"
				}, "roomName", "roomDesc", "containNum" ]
			}),
			remoteSort : false
		});
		this.store.setDefaultSort("containNum", "ASC");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		this.rowActions = new Ext.ux.grid.RowActions( {
			header : "管理",
			width : 80,
			actions : [ {
				iconCls : "btn-del",
				qtip : "删除",
				style : "margin:0 3px 0 3px"
			}, {
				iconCls : "btn-edit",
				qtip : "编辑",
				style : "margin:0 3px 0 3px"
			} ]
		});
		var b = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel( {
			columns : [ b, new Ext.grid.RowNumberer(), {
				header : "roomId",
				dataIndex : "roomId",
				hidden : true
			}, {
				header : "会议室名称",
				dataIndex : "roomName"
			}, {
				header : "描述",
				dataIndex : "roomDesc"
			}, {
				header : "容纳人数(单位：个)",
				dataIndex : "containNum"
			}, this.rowActions ],
			defaults : {
				sortable : true,
				menuDisabled : true,
				width : 100
			}
		});
		this.topbar = new Ext.Toolbar( {
			id : "BoardRootopBar",
			height : 30,
			bodyStyle : "text-align:right",
			menuAlign : "center",
			items : []
		});
		this.topbar.add(new Ext.Button( {
			iconCls : "btn-del",
			text : "删除",
			handler : function() {
				var e = Ext.getCmp("BoardRooGrid");
				var c = e.getSelectionModel().getSelections();
				if (c.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var f = new Array();
				for ( var d = 0; d < c.length; d++) {
					f.push(c[d].data.roomId);
				}
				BoardRooView.remove(f);
			}
		}));
		this.topbar.add(new Ext.Button( {
			iconCls : "btn-edit",
			text : "编辑",
			handler : function() {
				var d = Ext.getCmp("BoardRooGrid");
				var c = d.getSelectionModel().getSelections();
				if (c.length == 0) {
					Ext.ux.Toast.msg("编辑提示", "请选择要修改的记录！");
					return;
				}
				new BoardRooForm( {
					roomId : c[0].data.roomId
				}).show();
			}
		}));
		this.topbar.add(new Ext.Button( {
			iconCls : "btn-add",
			text : "新增",
			handler : function() {
				new BoardRooForm().show();
			}
		}));
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "BoardRooGrid",
			tbar : this.topbar,
			region : "center",
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			autoHeight : true,
			cm : a,
			sm : b,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true,
				autoFill : true
			},
			bbar : new Ext.PagingToolbar( {
				pageSize : 25,
				store : this.store,
				displayInfo : true,
				displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
		this.gridPanel.addListener("rowdblclick", function(d, c, f) {
			d.getSelectionModel().each(function(e) {
				if (isGranted("_BoardRooEdit")) {
					new BoardRooForm( {
						roomId : e.data.roomId
					}).show();
				}
			});
		});
		this.rowActions.on("action", this.onRowAction, this);
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.roomId);
			break;
		case "btn-edit":
			this.editRecord(a);
			break;
		default:
			break;
		}
	},
	delByIds : function(b) {
		var a = Ext.getCmp("BoardRooGrid");
		Ext.MessageBox.confirm("操作提示", "您真的要删除这条数据吗？", function(c) {
			if (c == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/admin/delBoardRoo.do",
					method : "post",
					params : {
						roomId : b
					},
					success : function() {
						Ext.ux.Toast.msg("操作提示", "成功删除所选记录！");
						a.getStore().reload( {
							params : {
								start : 0,
								limit : 25
							}
						});
					},
					failure : function() {
						Ext.ux.Toast.msg("操作提示", "对不起，删除数据操作失败！");
					}
				});
			}
		});
	},
	editRecord : function(a) {
		new BoardRooForm( {
			roomId : a.data.roomId
		}).show();
	},
	reset : function() {
		Ext.getCmp("BoardRooSearchForm").getForm().reset();
	},
	search : function(a) {
		if (a.searchPanel.getForm().isValid()) {
			a.searchPanel.getForm().submit( {
				url : __ctxPath + "/admin/listBoardRoo.do",
				method : "post",
				waitMsg : "数据正在提交，请稍后...",
				success : function(c, d) {
					var b = Ext.util.JSON.decode(d.response.responseText);
					a.gridPanel.getStore().loadData(b);
				},
				failure : function() {
					Ext.ux.Toast.msg("操作提示", "对不起，数据提交失败！");
				}
			});
		}
	}
});
BoardRooView.add = function(a) {
	new BoardRooForm().show();
};
BoardRooView.remove = function(b) {
	if (b != "") {
		var a = Ext.getCmp("BoardRooGrid");
		Ext.MessageBox.confirm("操作提示", "您真的要删除这条数据吗？", function(c) {
			if (c == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/admin/multiDelBoardRoo.do",
					method : "post",
					params : {
						ids : b
					},
					success : function() {
						Ext.ux.Toast.msg("操作提示", "数据删除操作成功！");
						a.getStore().reload( {
							params : {
								start : 0,
								limit : 25
							}
						});
					},
					failure : function() {
						Ext.ux.Toast.msg("操作提示", "对不起，数据删除失败！");
					}
				});
			}
		});
	} else {
		Ext.ux.Toast.msg("温馨提示", "对不起，请选择要删除的数据！");
	}
};