ConferenceView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ConferenceView.superclass.constructor.call(this, {
			id : "ConferenceView",
			title : "会议信息管理",
			region : "center",
			layout : "border",
			items : [ this.searchPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new Ext.FormPanel( {
			layout : "form",
			region : "north",
			width : "98%",
			height : 120,
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
					text : "会议信息:",
					style : "padding-right : 65px;padding-left : 0px;"
				}, {
					text : "会议标题:"
				}, {
					xtype : "textfield",
					name : "Q_confTopic_S_LK",
					width : 200,
					maxLength : 256,
					maxLengthText : "会议标题输入长度不能超过256个字符！"
				}, {
					style : "padding-right : 13px;",
					text : "会议类型："
				}, {
					width : 200,
					xtype : "combo",
					hiddenName : "Q_confProperty_S_LK",
					fieldLabel : "会议类型",
					valueField : "typeId",
					displayField : "typeName",
					mode : "local",
					editable : false,
					triggerAction : "all",
					forceSelection : true,
					store : new Ext.data.SimpleStore( {
						url : __ctxPath + "/admin/getTypeAllConference.do",
						autoLoad : true,
						fields : [ "typeId", "typeName" ]
					})
				}, {
					style : "padding-right : 5px;",
					text : "会议内容："
				}, {
					xtype : "textfield",
					name : "Q_confContent_S_LK",
					width : 200,
					maxLength : 4000,
					maxLengthText : "会议内容长度不能超过4000个字符！"
				} ]
			}, {
				border : false,
				layout : "hbox",
				region : "center",
				layoutConfg : {
					align : "left"
				},
				defaults : {
					xtype : "label",
					margins : {
						left : 4,
						right : 4,
						top : 0,
						bottom : 0
					}
				},
				items : [ {
					text : "会议室信息:",
					style : "padding-right : 40px;padding-left : 0px;"
				}, {
					text : "会议室名称："
				}, {
					xtype : "textfield",
					name : "Q_roomName_S_LK",
					width : 200,
					maxLength : 156,
					maxLengthText : "会议室输入字符不能超过156长度！"
				}, {
					text : "会议室类型："
				}, {
					width : 200,
					xtype : "combo",
					hiddenName : "Q_roomId_L_EQ",
					fieldLabel : "会议室类型",
					valueField : "roomId",
					displayField : "roomName",
					mode : "local",
					editable : false,
					triggerAction : "all",
					forceSelection : true,
					store : new Ext.data.SimpleStore( {
						url : __ctxPath + "/admin/getBoardrooConference.do",
						autoLoad : true,
						fields : [ "roomId", "roomName" ]
					})
				}, {
					text : "会议室地址:"
				}, {
					width : 200,
					xtype : "textfield",
					name : "Q_roomLocation_S_LK",
					maxLength : 156,
					maxLengthText : "会议室地址输入字符不能超过156个字符！"
				} ]
			}, {
				layout : "hbox",
				region : "center",
				border : false,
				layoutConfig : {
					padding : "5px",
					align : "center"
				},
				defaults : {
					xtype : "label",
					margins : {
						left : 4,
						top : 0,
						right : 4,
						bottom : 0
					}
				},
				items : [ {
					style : "padding-right : 125px;",
					text : "会议时间:"
				}, {
					xtype : "datefield",
					name : "Q_startTime_D_GE",
					format : "Y-m-d",
					width : 90
				}, {
					text : "至"
				}, {
					xtype : "datefield",
					name : "Q_endTime_D_LE",
					format : "Y-m-d",
					width : 90
				} ]
			}, {
				layout : "hbox",
				region : "center",
				border : false,
				layoutConfig : {
					padding : "5px",
					align : "center"
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
				items : [ {
					xtype : "label",
					style : "padding-left:40%;",
					text : " "
				}, {
					iconCls : "search",
					text : "搜索",
					handler : this.search.createCallback(this)
				}, {
					iconCls : "btn-reset",
					style : "padding-right:40%;",
					text : "重置",
					handler : this.reset.createCallback(this)
				} ]
			} ]
		});
		this.store = new Ext.data.JsonStore( {
			url : __ctxPath + "/admin/listConference.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "confId",
				type : "int"
			}, "confTopic", "compereName", "roomName", "roomLocation",
					"attendUsersName", "startTime", "endTime", "status" ]
		});
		this.store.setDefaultSort("confId", "desc");
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
				header : "confId",
				dataIndex : "confId",
				hidden : true
			}, {
				header : "会议议题",
				dataIndex : "confTopic"
			}, {
				header : "主持人",
				dataIndex : "compereName"
			}, {
				header : "与会人员",
				dataIndex : "attendUsersName"
			}, {
				header : "开始时间",
				dataIndex : "startTime"
			}, {
				header : "结束时间",
				dataIndex : "endTime"
			}, {
				header : "会议室名称",
				dataIndex : "roomName"
			}, {
				header : "会议地址",
				dataIndex : "roomLocation"
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
			}, {
				text : "会议申请",
				iconCls : "btn-add",
				xtype : "button",
				handler : function() {
					new ConferenceForm().show();
				}
			} ]
		});
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "ConferenceGrid",
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
				new ConferenceForm( {
					confId : e.data.confId
				}).show();
			});
		});
		this.rowActions.on("action", this.onRowAction, this);
	},
	search : function(a) {
		if (a.searchPanel.getForm().isValid()) {
			$search( {
				searchPanel : a.searchPanel,
				gridPanel : a.gridPanel
			});
		}
	},
	reset : function(a) {
		a.searchPanel.getForm().reset();
	},
	createRecord : function() {
		new ConferenceForm().show();
	},
	delByIds : function(a) {
		Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(b) {
			if (b == "yes") {
				Ext.Ajax.request( {
					url : __ctxPath + "/admin/multiDelConference.do",
					params : {
						ids : a
					},
					method : "POST",
					success : function(c, d) {
						Ext.ux.Toast.msg("操作信息", "成功删除该会议信息！");
						Ext.getCmp("ConferenceGrid").getStore().reload();
					},
					failure : function(c, d) {
						Ext.Msg.alert("操作信息", "操作出错，请联系管理员！");
					}
				});
			}
		});
	},
	delRecords : function() {
		var c = Ext.getCmp("ConferenceGrid");
		var a = c.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var d = Array();
		for ( var b = 0; b < a.length; b++) {
			d.push(a[b].data.confId);
		}
		this.delByIds(d);
	},
	edit : function() {
		var b = Ext.getCmp("ConferenceGrid");
		var a = b.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("操作提示", "请选择要编辑的记录！");
			return;
		}
		var c = a[0].data.confId;
		Ext.Ajax.request( {
			url : __ctxPath + "/admin/allowUpdaterConfPrivilege.do",
			params : {
				confId : c
			},
			waitMsg : "数据正在提交，请稍后...",
			method : "post",
			success : function(d, f) {
				var e = Ext.util.JSON.decode(d.responseText);
				if (e.success) {
					new ConferenceForm( {
						confId : c
					}).show();
				} else {
					Ext.MessageBox.show( {
						title : "操作信息",
						msg : e.msg,
						buttons : Ext.MessageBox.OK,
						icon : "ext-mb-error"
					});
				}
			}
		});
	},
	showDetail : function(a) {
		ConferenceDetailForm.show(a);
	},
	editRecord : function(a) {
		var b = a.data.confId;
		Ext.Ajax.request( {
			url : __ctxPath + "/admin/allowUpdaterConfPrivilege.do",
			params : {
				confId : b
			},
			waitMsg : "数据正在提交，请稍后...",
			method : "post",
			success : function(c, e) {
				var d = Ext.util.JSON.decode(c.responseText);
				if (d.success) {
					new ConferenceForm( {
						confId : b
					}).show();
				} else {
					Ext.MessageBox.show( {
						title : "操作信息",
						msg : d.msg,
						buttons : Ext.MessageBox.OK,
						icon : "ext-mb-error"
					});
				}
			}
		});
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-del":
			this.delByIds(a.data.confId);
			break;
		case "btn-showDetail":
			this.showDetail(a.data.confId);
			break;
		case "btn-edit":
			this.editRecord(a);
			break;
		}
	}
});