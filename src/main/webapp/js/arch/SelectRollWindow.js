SelectRollWindow = Ext.extend(Ext.Window, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		SelectRollWindow.superclass.constructor.call(this, {
			id : "SelectRollWindow",
			title : "案卷",
			region : "center",
			layout : "border",
			modal : true,
			width : 800,
			height : 600,
			buttonAlign : "center",
			buttons : [ {
				text : "选择",
				iconCls : "btn-save",
				scope : this,
				handler : this.save
			}, {
				text : "重置",
				iconCls : "btn-reset",
				scope : this,
				handler : this.reset
			}, {
				text : "关闭",
				iconCls : "btn-cancel",
				scope : this,
				handler : this.cancel
			} ],
			items : [ this.searchPanel, this.leftPanel, this.gridPanel ]
		});
	},
	initUIComponents : function() {
		this.searchPanel = new HT.SearchPanel( {
			layout : "form",
			region : "north",
			id : "SelectRollWindowlSearchPanel",
			colNums : 4,
			items : [ {
				fieldLabel : "全宗号",
				name : "Q_archFond.afNo_S_LK",
				flex : 1,
				xtype : "combo",
				mode : "local",
				editable : false,
				triggerAction : "all",
				store : new Ext.data.JsonStore( {
					url : __ctxPath + "/arch/listArchFond.do",
					autoLoad : true,
					autoShow : true,
					root : "result",
					fields : [ "afNo", "afNo" ]
				}),
				valueField : "afNo",
				displayField : "afNo"
			}, {
				fieldLabel : "案卷号",
				name : "Q_rollNo_S_LK",
				flex : 1,
				xtype : "textfield"
			}, {
				fieldLabel : "案卷名称",
				name : "Q_rolllName_S_LK",
				flex : 1,
				xtype : "textfield"
			}, {
				fieldLabel : "目录号",
				name : "Q_catNo_S_LK",
				flex : 1,
				xtype : "textfield"
			}, {
				fieldLabel : "保管期限",
				name : "Q_timeLimit_S_LK",
				editable : true,
				lazyInit : false,
				forceSelection : false,
				xtype : "diccombo",
				itemName : "保管期限"
			}, {
				fieldLabel : "开放形式",
				name : "Q_openStyle_S_LK",
				flex : 1,
				editable : true,
				lazyInit : false,
				forceSelection : false,
				xtype : "diccombo",
				itemName : "开放形式"
			}, {
				fieldLabel : "状态",
				hiddenName : "Q_status_SN_EQ",
				flex : 1,
				xtype : "combo",
				mode : "local",
				editable : false,
				value : "1",
				triggerAction : "all",
				store : [ [ "", "全部" ], [ "1", "正常" ], [ "0", "销毁" ] ]
			}, {
				fieldLabel : "案卷分类ID",
				id : "SelectRollWindow.proTypeId",
				name : "Q_globalType.proTypeId_L_EQ",
				flex : 1,
				xtype : "hidden"
			}, {
				fieldLabel : "案卷分类名称",
				id : "SelectRollWindow.typeName",
				name : "Q_typeName_S_LK",
				flex : 1,
				xtype : "hidden"
			} ],
			buttons : [ {
				text : "查询",
				scope : this,
				iconCls : "btn-search",
				handler : this.search
			}, {
				text : "重置",
				scope : this,
				iconCls : "btn-reset",
				handler : this.reset
			} ]
		});
		this.leftPanel = new Ext.Panel( {
			region : "west",
			layout : "anchor",
			collapsible : true,
			split : true,
			width : 200,
			items : [ {
				xtype : "treePanelEditor",
				id : "SelectRollWindowGlobalTypeTree",
				split : true,
				rootVisible : false,
				border : false,
				height : 380,
				autoScroll : true,
				scope : this,
				url : __ctxPath + "/system/treeGlobalType.do?catKey=AR_RL",
				onclick : function(c) {
					var a = c.id;
					var b = c.text;
					if (a == "0") {
						Ext.getCmp("SelectRollWindowlSearchPanel").getForm()
								.findField("SelectRollWindow.proTypeId")
								.setValue("");
						Ext.getCmp("SelectRollWindowlSearchPanel").getForm()
								.findField("SelectRollWindow.typeName")
								.setValue("");
					} else {
						Ext.getCmp("SelectRollWindowlSearchPanel").getForm()
								.findField("SelectRollWindow.proTypeId")
								.setValue(a);
						Ext.getCmp("SelectRollWindowlSearchPanel").getForm()
								.findField("SelectRollWindow.typeName")
								.setValue(b);
					}
					Ext.getCmp("SelectRollWindow").search();
				}
			} ]
		});
		this.gridPanel = new HT.GridPanel( {
			region : "center",
			id : "SelectRollWindowRollGrid",
			url : __ctxPath + "/arch/listArchRoll.do",
			fields : [ {
				name : "rollId",
				type : "int"
			}, "createTime", "updateTime", "creatorName", "creatorId",
					"status", "proTypeId", "typeName", "openStyle", "archFond",
					"rolllName", "rollNo", "catNo", "timeLimit", "startTime",
					"endTime", "author", "setupTime", "checker", "keyWords",
					"editCompany", "editDep", "decp" ],
			columns : [ {
				header : "rollId",
				dataIndex : "rollId",
				hidden : true
			}, {
				header : "全宗号",
				dataIndex : "archFond",
				renderer : function(a) {
					if (a) {
						return a.afNo;
					}
				}
			}, {
				header : "案卷号",
				dataIndex : "rollNo"
			}, {
				header : "案卷名称",
				dataIndex : "rolllName"
			} ]
		});
	},
	reset : function() {
		this.searchPanel.getForm().reset();
	},
	cancel : function() {
		this.close();
	},
	save : function() {
		var a = this.gridPanel.getSelectionModel().getSelections();
		if (a.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择记录！");
			return;
		}
		if (this.callBack != null) {
			this.close();
			this.callBack.call(this, a);
		}
	},
	search : function() {
		$search( {
			searchPanel : this.searchPanel,
			gridPanel : this.gridPanel
		});
	}
});