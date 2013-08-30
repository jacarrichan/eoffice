ConfSummaryView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ConfSummaryView.superclass.constructor.call(this, {
			id : "ConfSummaryView",
			title : "会议纪要管理",
			iconCls : "menu-conf-summary",
			region : "center",
			bodyStyle : "padding : 5px 5px 5px 5px",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel( {
			id : "ConfSummary.searchPanel",
			layout : "form",
			region : "north",
			width : "98%",
			height : 100,
			keys : {
				key : Ext.EventObject.ENTER,
				fn : this.search.createCallback(this),
				scope : this
			},
			items : [
					{
						layout : "hbox",
						region : "center",
						border : false,
						layoutConfig : {
							padding : "5px",
							align : "left"
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
							style : "padding-right : 25px;",
							text : "会议议题："
						}, {
							width : "20%",
							name : "Q_confId.confTopic_S_LK",
							xtype : "textfield",
							maxLength : 156,
							maxLengthText : "会议议题输入字符不能超过156个长度！"
						}, {
							text : "纪要内容："
						}, {
							width : "20%",
							xtype : "textfield",
							name : "Q_sumContent_S_LK",
							maxlength : 4000,
							maxLengthText : "会议纪要内容输入字符不能超过4000个长度！"
						}, {
							text : "纪要人："
						}, {
							width : "20%",
							name : "Q_creator_S_LK",
							xtype : "textfield",
							maxLength : 156,
							maxLengthText : "会议纪要人输入字符不能超过156个长度！"
						} ]
					},
					{
						layout : "hbox",
						region : "center",
						border : false,
						layoutConfig : {
							padding : "5px",
							align : "left"
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
							text : "创建纪要时间："
						}, {
							width : "20%",
							xtype : "datetimefield",
							name : "Q_createtime_D_GE",
							format : "Y-m-d"
						}, {
							text : "至"
						}, {
							width : "20%",
							xtype : "datetimefield",
							name : "Q_createtime_D_LE",
							format : "Y-m-d"
						}, {
							text : "纪要状态："
						}, {
							width : "20%",
							xtype : "combo",
							hiddenName : "Q_status_SN_EQ",
							store : [ [ "0", "待发送" ], [ "1", "发送" ] ],
							editable : false,
							triggerAction : "all",
							mode : "local"
						} ]
					},
					{
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
								left : 4,
								top : 0,
								right : 4,
								bottom : 0
							}
						},
						items : [
								{
									xtype : "label",
									text : " ",
									style : "padding-left:40%;"
								},
								{
									text : "查询",
									iconCls : "search",
									handler : this.search.createCallback(this)
								},
								{
									text : "重置",
									iconCls : "btn-reset",
									handler : function() {
										Ext.getCmp("ConfSummary.searchPanel")
												.getForm().reset();
									}
								} ]
					} ]
		});
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/admin/listConfSummary.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "sumId",
				type : "int"
			}, "confId", "createtime", "creator", "sumContent", "status" ]
		});
		this.store.setDefaultSort("sumId", "desc");
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
			}, {
				iconCls : "btn-showDetail",
				qtip : "查看",
				style : "margin:0 3px 0 3px"
			} ]
		});
		var b = new Ext.grid.CheckboxSelectionModel();
		var a = new Ext.grid.ColumnModel( {
			columns : [ b, new Ext.grid.RowNumberer(), {
				header : "sumId",
				dataIndex : "sumId",
				hidden : true
			}, {
				header : "会议议题",
				dataIndex : "confId",
				renderer : function(c) {
					return c == null ? "为空" : c.confTopic;
				}
			}, {
				header : "创建日期",
				dataIndex : "createtime"
			}, {
				header : "纪要人",
				dataIndex : "creator"
			}, {
				header : "纪要内容",
				dataIndex : "sumContent"
			}, this.rowActions ],
			defaults : {
				sortable : true,
				menuDisabled : true,
				width : 100
			}
		});
		this.topbar = new Ext.Toolbar( {
			height : 30,
			bodyStyle : "text-align:left",
			items : [ {
				iconCls : "btn-del",
				text : "删除",
				xtype : "button",
				handler : this.delRecords,
				scope : this
			}, {
				iconCls : "btn-edit",
				text : "编辑",
				xtype : "button",
				handler : this.edit,
				scope : this
			} ]
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ConfSummaryGrid",
			region : "center",
			stripeRows : true,
			tbar : this.topbar,
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
				displayMsg : "当前页记录索引{0}-{1}， 共{2}条记录",
				emptyMsg : "当前没有记录"
			})
		});
		this.gridPanel.addListener("rowdblclick", function(d, c, f) {
			d.getSelectionModel().each(function(e) {
				if (e.data.status == 1) {
					Ext.MessageBox.show( {
						title : "操作提示",
						msg : "对不起，该数据已经发送，不可以编辑，请谅解！",
						buttons : Ext.MessageBox.OK,
						icon : "ext-mb-error"
					});
					return;
				}
				new ConfSummaryForm( {
					sumId : e.data.sumId
				}).show();
			});
		});
		this.rowActions.on("action", this.onRowAction, this);
	},
	search : function(a) {
		if (a.searchPanel.getForm().isValid()) {
			a.searchPanel.getForm().submit( {
				waitMsg : "正在提交查询",
				url : __ctxPath + "/admin/listConfSummary.do",
				success : function(c, d) {
					var b = Ext.util.JSON.decode(d.response.responseText);
					a.gridPanel.getStore().loadData(b);
				}
			});
		}
	},
	createRecord : function() {
		new ConfSummaryForm().show();
	},
	delByIds : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/admin/multiDelConfSummary.do",
					params : {
						ids : a
					},
					method : "POST",
					success : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "成功删除该会议纪要信息 ！");
						Ext.getCmp("ConfSummaryGrid").getStore().reload();
					},
					failure : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var c = Ext.getCmp("ConfSummaryGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var d = Array();
		for ( var b = 0; b < a.length; b++) {
			d.push(a[b].data.sumId);
		}
		this.delByIds(d);
	},
	edit : function() {
		var b = Ext.getCmp("ConfSummaryGrid");
		var a = b.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要编辑的记录！");
			return;
		}
		if (a[0].data.status == 1) {
			Ext.MessageBox.show( {
				title : "操作信息",
				msg : "对不起，该数据已经发送，不可以编辑，请原谅！",
				buttons : Ext.MessageBox.OK,
				icon : "ext-mb-error"
			});
			return;
		}
		new ConfSummaryForm( {
			sumId : a[0].data.sumId
		}).show();
	},
	editRecord : function(a) {
		if (a.data.status == 1) {
			Ext.MessageBox.show( {
				title : "操作提示",
				msg : "对不起，该数据已经发送，不可以编辑，请谅解！",
				buttons : Ext.MessageBox.OK,
				icon : "ext-mb-error"
			});
			return;
		}
		new ConfSummaryForm( {
			sumId : a.data.sumId
		}).show();
	},
	showDetail : function(a) {
		ConfSummaryDetailForm.show(a);
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.sumId);
			break;
		case "btn-showDetail":
			this.showDetail(a.data.sumId);
			break;
		case "btn-edit":
			this.editRecord(a);
			break;
		default:
			break;
		}
	}
});