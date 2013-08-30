Ext.ns("BoardTypeView");
BoardTypeView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponent();
		BoardTypeView.superclass.constructor.call(this, {
			id : "BoardTypeView",
			title : "会议类型管理",
			iconCls : "menu-confernece_boardType",
			layout : "border",
			region : "center",
			bodyStyle : "padding : 5px 5px 5px 5px",
			items : [ this.searchPanel, this.gridPanel ],
			buttonAlign : "center"
		});
	},
	initUIComponent : function() {
		this.searchPanel = new Ext.FormPanel( {
			id : "boardTypeSearchPanel",
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
				layout : "hbox",
				region : "center",
				border : false,
				layoutConfig : {
					padding : "5px",
					align : "middle"
				},
				defaults : {
					xtype : "label",
					margins : {
						top : 0,
						left : 4,
						bottom : 0,
						right : 4
					}
				},
				items : [ {
					text : "请输入要查询的条件："
				}, {
					text : "会议室名称："
				}, {
					xtype : "textfield",
					name : "Q_typeName_S_EQ",
					width : "20%",
					maxLength : 128,
					maxLengthText : "会议室名称不能超过128个字符长度！"
				}, {
					text : "会议类型描述："
				}, {
					xtype : "textfield",
					name : "Q_typeDesc_S_LK",
					width : "20%",
					maxLength : 4000,
					maxLengthText : "会议类型描述不能超过4000个字符长度！"
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
						left : 4,
						bottom : 0,
						right : 4
					}
				},
				items : [ {
					xtype : "label",
					text : " ",
					style : "padding-left : 40%;"
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
				url : __ctxPath + "/admin/listBoardType.do"
			}),
			reader : new Ext.data.JsonReader( {
				root : "result",
				totalProperty : "totalCounts",
				id : "id",
				fields : [ {
					name : "typeId",
					type : "int"
				}, "typeName", "typeDesc" ]
			}),
			remoteSort : false
		});
		this.store.setDefaultSort("typeId", "ASC");
		this.store.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
		var b = new Ext.grid.CheckboxSelectionModel();
		this.rowActions = new Ext.ux.grid.RowActions( {
			header : "管理",
			width : 80,
			actions : [ {
				iconCls : "btn-del",
				qtip : "删除",
				style : "margin : 0 3px 0 3px"
			}, {
				iconCls : "btn-edit",
				qtip : "编辑",
				style : "margin : 0 3px 0 3px"
			} ]
		});
		var a = new Ext.grid.ColumnModel( {
			columns : [ b, new Ext.grid.RowNumberer(), {
				header : "typeId",
				dataIndex : "typeId",
				hidden : true
			}, {
				header : "会议类型名称",
				dataIndex : "typeName"
			}, {
				header : "会议类型描述",
				dataIndex : "typeDesc"
			}, this.rowActions ],
			defaults : {
				sortable : true,
				menuDisabled : true,
				width : 100
			}
		});
		this.topbar = new Ext.Toolbar( {
			id : "BoardTypeToolBar",
			height : 30,
			bodyStyle : "text-align:center,align:right",
			menuAlign : "center",
			items : []
		});
		this.topbar.add(new Ext.Button( {
			iconCls : "btn-del",
			text : "删除",
			handler : function() {
				var c = Ext.getCmp("BoardTypeGridPanel").getSelectionModel()
						.getSelections();
				var e = new Array();
				for ( var d = 0; d < c.length; d++) {
					e.push(c[d].data.typeId);
				}
				BoardTypeView.del(e);
			}
		}));
		this.topbar.add(new Ext.Button( {
			iconCls : "btn-edit",
			text : "编辑",
			handler : function() {
				var c = Ext.getCmp("BoardTypeGridPanel").getSelectionModel()
						.getSelections();
				if (c.length > 0) {
					BoardTypeView.edit(c[0].data.typeId);
				} else {
					Ext.ux.Toast.msg("操作提示", "对不起，请选择操作数据！");
				}
			}
		}));
		this.topbar.add(new Ext.Button( {
			iconCls : "btn-add",
			text : "新增",
			handler : function() {
				new BoardTypeForm().show();
			}
		}));
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "BoardTypeGridPanel",
			region : "center",
			layout : "form",
			bodyStyle : "padding : 5px 5px 5px 5px",
			tbar : this.topbar,
			loadMask : true,
			autoHeight : true,
			store : this.store,
			plugins : this.rowActions,
			cm : a,
			sm : b,
			viewConfig : {
				forceFit : true,
				autoFill : true
			},
			bbar : new Ext.PagingToolbar( {
				pageSize : 25,
				store : this.store,
				displayInfo : true,
				displayMsg : "当前显示从{0}至{1}，共有{2}条记录",
				emptyMsg : "对不起，当前没有数据！"
			})
		});
		this.gridPanel.addListener("rowdblclick", function(c, f, d) {
			c.getSelectionModel().each(function(e) {
				BoardTypeView.edit(e.data.typeId);
			});
		});
		this.rowActions.on("action", this.onRowActions, this);
	},
	onRowActions : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delById(a.data.typeId);
			break;
		case "btn-edit":
			this.editById(a.data.typeId);
			break;
		}
	},
	delById : function(a) {
		Ext.MessageBox.confirm("操作提示", "您真的要删除该数据吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/admin/delBoardType.do",
					params : {
						typeId : a
					},
					method : "post",
					waitMsg : "数据正在提交，请稍后...",
					success : function() {
						Ext.ux.Toast.msg("操作提示", "恭喜您，数据删除成功！");
						Ext.getCmp("BoardTypeGridPanel").getStore().reload( {
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
	},
	editById : function(a) {
		new BoardTypeForm( {
			typeId : a
		}).show();
	},
	reset : function() {
		Ext.getCmp("boardTypeSearchPanel").getForm().reset();
	},
	search : function(a) {
		if (a.searchPanel.getForm().isValid()) {
			a.searchPanel.getForm().submit( {
				url : __ctxPath + "/admin/listBoardType.do",
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
BoardTypeView.edit = function(a) {
	new BoardTypeForm( {
		typeId : a
	}).show();
};
BoardTypeView.validateSelected = function() {
	var a = Ext.getCmp("BoardTypeGridPanel");
	var b = a.getSelectionModel().getSelections();
	if (b.length > 0) {
		return true;
	} else {
		Ext.ux.Toast.msg("操作提示", "对不起，请选择操作数据！");
		return false;
	}
};
BoardTypeView.del = function(b) {
	if (BoardTypeView.validateSelected()) {
		var a = Ext.getCmp("BoardTypeGridPanel");
		Ext.MessageBox.confirm("删除提示", "您真的要删除您选中的数据吗？", function(c) {
			if (c == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/admin/multiDelBoardType.do",
					method : "post",
					params : {
						typeIds : b
					},
					success : function(d, e) {
						Ext.ux.Toast.msg("操作提示", "恭喜您，操作成功！");
						a.getStore().reload( {
							params : {
								start : 0,
								limit : 25
							}
						});
						Ext.getCmp("BoardTypeView").close();
					},
					failure : function(d, e) {
						Ext.ux.Toast.msg("操作提示", "对不起，您提示的数据失败！");
						Ext.getCmp("BoardTypeView").close();
					}
				});
			} else {
				return;
			}
		});
	}
};