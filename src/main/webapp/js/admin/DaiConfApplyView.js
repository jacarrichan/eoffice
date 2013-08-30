DaiConfApplyView = Ext.extend(Ext.Panel, {
	searchPanel : null,
	gridPanel : null,
	store : null,
	topbar : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		DaiConfApplyView.superclass.constructor.call(this, {
			id : "DaiConfApplyView",
			title : "待审核会议查询",
			iconCls : "menu-daiConfApply",
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
					style : "padding-right:15px;",
					text : "会议标题:"
				}, {
					xtype : "textfield",
					name : "Q_confTopic_S_LK",
					width : "20%",
					maxLength : 256,
					maxLengthText : "会议标题输入长度不能超过256个字符！"
				}, {
					style : "padding-right : 15px;",
					text : "会议类型："
				}, {
					width : "20%",
					xtype : "combo",
					name : "Q_confProperty_S_LK",
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
					width : "20%",
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
					text : "会议室名称："
				}, {
					xtype : "textfield",
					name : "Q_roomName_S_LK",
					width : "20%",
					maxLength : 156,
					maxLengthText : "会议室输入字符不能超过156长度！"
				}, {
					text : "会议室类型："
				}, {
					width : "20%",
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
					width : "20%",
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
					style : "padding-right:15px;",
					text : "会议时间:"
				}, {
					xtype : "datefield",
					name : "Q_startTime_D_GE",
					format : "Y-m-d",
					width : "6%"
				}, {
					text : "至"
				}, {
					xtype : "datefield",
					name : "Q_endTime_D_LE",
					format : "Y-m-d",
					width : "6%"
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
			url : __ctxPath + "/admin/daiConfApplyConference.do",
			root : "result",
			totalProperty : "totalCounts",
			remoteSort : true,
			fields : [ {
				name : "confId",
				type : "int"
			}, "confTopic", "compereName", "roomName", "roomLocation",
					"attendUsersName", "checkName", "startTime", "endTime",
					"status" ]
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
				iconCls : "btn-apply",
				qtip : "审核",
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
				header : "审批人",
				dataIndex : "checkName"
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
		this.gridPanel = new Ext.grid.GridPanel( {
			id : "DaiConfApplyGrid",
			region : "center",
			stripeRows : true,
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
				ConferenceDetailForm.show(e.data.confId);
			});
		});
		this.rowActions.on("action", this.onRowAction, this);
	},
	search : function(a) {
		if (a.searchPanel.getForm().isValid()) {
			a.searchPanel.getForm().submit( {
				waitMsg : "正在提交查询",
				url : __ctxPath + "/admin/daiConfApplyConference.do",
				success : function(c, d) {
					var b = Ext.util.JSON.decode(d.response.responseText);
					a.gridPanel.getStore().loadData(b);
				}
			});
		}
	},
	reset : function(a) {
		a.searchPanel.getForm().reset();
	},
	showDetail : function(a) {
		ConferenceDetailForm.show(a);
	},
	apply : function(a) {
		var b = new Date();
		if (a.data.startTime > b.format("Y-m-d H:i:s")) {
			new ConfApplyForm( {
				confId : a.data.confId,
				confTopic : a.data.confTopic
			}).show();
		} else {
			Ext.MessageBox.show( {
				title : "操作信息",
				msg : "对不起，会议过期，不能审核！",
				buttons : Ext.MessageBox.OK,
				icon : "ext-mb-error"
			});
		}
	},
	removeById : function(a) {
		var b = new Date();
		if (a.data.startTime <= b.format("Y-m-d H:i:s")) {
			Ext.Msg.confirm("信息确认", "您确认要删除所选记录吗？", function(c) {
				if (c == "yes") {
					Ext.Ajax.request( {
						url : __ctxPath + "/admin/multiDelConference.do",
						params : {
							ids : a.data.confId
						},
						method : "POST",
						success : function(d, e) {
							Ext.ux.Toast.msg("操作信息", "成功删除该会议信息！");
							Ext.getCmp("DaiConfApplyGrid").getStore().reload();
						},
						failure : function(d, e) {
							Ext.Msg.alert("操作信息", "操作出错，请联系管理员！");
						}
					});
				}
			});
		} else {
			Ext.MessageBox.show( {
				title : "操作提示",
				msg : "对不起，该会议记录不能删除，请原谅！",
				buttons : Ext.MessageBox.OK,
				icon : "ext-mb-error"
			});
		}
	},
	onRowAction : function(c, a, d, e, b) {
		switch (d) {
		case "btn-apply":
			this.apply(a);
			break;
		case "btn-del":
			this.removeById(a);
			break;
		case "btn-showDetail":
			this.showDetail(a.data.confId);
			break;
		}
	}
});