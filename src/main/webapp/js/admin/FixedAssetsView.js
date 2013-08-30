Ext.ns("FixedAssetsView");
var FixedAssetsView = function() {
};
FixedAssetsView.prototype.setTypeId = function(a) {
	this.typeId = a;
	FixedAssetsView.typeId = a;
};
FixedAssetsView.prototype.getTypeId = function() {
	return this.typeId;
};
FixedAssetsView.prototype.getView = function() {
	return new Ext.Panel( {
		id : "FixedAssetsView",
		title : "固定资产列表",
		layout : "anchor",
		region : "center",
		autoScroll : true,
		items : [ new Ext.FormPanel( {
			id : "FixedAssetsSearchForm",
			height : 40,
			frame : false,
			border : false,
			layout : "hbox",
			layoutConfig : {
				padding : "5",
				align : "middle"
			},
			defaults : {
				xtype : "label",
				margins : {
					top : 0,
					right : 4,
					bottom : 4,
					left : 4
				}
			},
			items : [ {
				text : "查询条件:"
			}, {
				text : "资产名称"
			}, {
				xtype : "textfield",
				name : "Q_assetsName_S_LK"
			}, {
				text : "所属部门"
			}, {
				xtype : "textfield",
				name : "Q_beDep_S_LK"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var a = Ext.getCmp("FixedAssetsSearchForm");
					var b = Ext.getCmp("FixedAssetsGrid");
					if (a.getForm().isValid()) {
						$search( {
							searchPanel : a,
							gridPanel : b
						});
					}
				}
			} ]
		}), this.setup() ]
	});
};
FixedAssetsView.prototype.setup = function() {
	return this.grid();
};
FixedAssetsView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "assetsId",
							dataIndex : "assetsId",
							hidden : true
						},
						{
							header : "资产编号",
							dataIndex : "assetsNo"
						},
						{
							header : "资产名称",
							dataIndex : "assetsName"
						},
						{
							header : "资产类别",
							dataIndex : "assetsTypeName"
						},
						{
							header : "折旧类型",
							dataIndex : "depType",
							renderer : function(e) {
								return e.typeName;
							}
						},
						{
							header : "开始折旧日期",
							dataIndex : "startDepre"
						},
						{
							header : "资产值",
							dataIndex : "assetValue"
						},
						{
							header : "资产当前值",
							dataIndex : "assetCurValue"
						},
						{
							header : "管理",
							dataIndex : "assetsId",
							width : 50,
							sortable : false,
							renderer : function(i, h, f, l, g) {
								var k = f.data.assetsId;
								var e = f.data.depType.calMethod;
								var j = "";
								if (isGranted("_Depreciate")) {
									j = '<button title="开始折算" value=" " class="btn-pred" onclick="FixedAssetsView.depreciate('
											+ k
											+ ","
											+ e
											+ ')">&nbsp;</button>';
								}
								if (isGranted("_FixedAssetsDel")) {
									j += '&nbsp;<button title="删除" value=" " class="btn-del" onclick="FixedAssetsView.remove(' + k + ')">&nbsp;</button>';
								}
								if (isGranted("_FixedAssetsEdit")) {
									j += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="FixedAssetsView.edit(' + k + ')">&nbsp;</button>';
								}
								return j;
							}
						} ],
				defaults : {
					sortable : true,
					menuDisabled : false,
					width : 100
				}
			});
	var b = this.store();
	b.load( {
		params : {
			start : 0,
			limit : 25
		}
	});
	var c = new Ext.grid.GridPanel( {
		id : "FixedAssetsGrid",
		tbar : this.topbar(),
		store : b,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		autoHeight : true,
		cm : a,
		sm : d,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		bbar : new Ext.PagingToolbar( {
			pageSize : 25,
			store : b,
			displayInfo : true,
			displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
			emptyMsg : "当前没有记录"
		})
	});
	c.addListener("rowdblclick", function(g, f, h) {
		g.getSelectionModel().each(function(e) {
			if (isGranted("_FixedAssetsEdit")) {
				FixedAssetsView.edit(e.data.assetsId);
			}
		});
	});
	return c;
};
FixedAssetsView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/admin/listFixedAssets.do"
		}),
		reader : new Ext.data.JsonReader(
				{
					root : "result",
					totalProperty : "totalCounts",
					id : "id",
					fields : [ {
						name : "assetsId",
						type : "int"
					}, "assetsNo", "assetsName", "model", {
						name : "assetsTypeName",
						mapping : "assetsType.typeName"
					}, "manufacturer", "manuDate", "buyDate", "beDep",
							"custodian", "notes", "remainValRate", {
								name : "depType",
								mapping : "depreType"
							}, "startDepre", "intendTerm", "intendWorkGross",
							"workGrossUnit", "assetValue", "assetCurValue",
							"depreRate" ]
				}),
		remoteSort : true
	});
	a.setDefaultSort("assetsId", "desc");
	return a;
};
FixedAssetsView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "FixedAssetsFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : []
	});
	if (isGranted("_FixedAssetsAdd")) {
		a.add(new Ext.Button( {
			iconCls : "btn-add",
			text : "创建固定资产项",
			handler : function() {
				new FixedAssetsForm().show();
				Ext.getCmp("intendTermContainer").hide();
				Ext.getCmp("WorkGrossPanel").hide();
				Ext.getCmp("FixedAssetsForm").remove("assetCurValue");
				Ext.getCmp("depreTypeId").getStore().reload();
				Ext.getCmp("assetsTypeId").getStore().reload();
				Ext.getCmp("FixedAssetsForm").remove("assetsNoContainer");
			}
		}));
	}
	if (isGranted("_FixedAssetsDel")) {
		a.add(new Ext.Button( {
			iconCls : "btn-del",
			text : "删除固定资产项",
			handler : function() {
				var d = Ext.getCmp("FixedAssetsGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.assetsId);
				}
				FixedAssetsView.remove(e);
			}
		}));
	}
	return a;
};
FixedAssetsView.remove = function(b) {
	var a = Ext.getCmp("FixedAssetsGrid");
	Ext.Msg.confirm("信息确认", "将折算记录一起删除，您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelFixedAssets.do",
				params : {
					ids : b
				},
				method : "post",
				success : function() {
					Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
					a.getStore().reload( {
						params : {
							start : 0,
							limit : 25
						}
					});
				}
			});
		}
	});
};
FixedAssetsView.edit = function(a) {
	new FixedAssetsForm( {
		assetsId : a
	}).show();
	Ext.getCmp("intendTermContainer").hide();
	Ext.getCmp("WorkGrossPanel").hide();
	Ext.getCmp("depreTypeId").getStore().reload();
	Ext.getCmp("assetsTypeId").getStore().reload();
};
FixedAssetsView.depreciate = function(b, a) {
	if (a == 2) {
		new WorkGrossWin(b);
	} else {
		if (a == 1 || a == 3 || a == 4) {
			Ext.Msg.confirm("操作提示", "你决定开始折算了吗？", function(c) {
				if (c == "yes") {
					Ext.Ajax.request( {
						url : __ctxPath + "/admin/depreciateDepreRecord.do",
						params : {
							ids : b
						},
						method : "post",
						success : function(e, f) {
							var d = Ext.util.JSON.decode(e.responseText);
							if (d.success) {
								Ext.ux.Toast.msg("信息提示", "成功产生折旧记录！");
							} else {
								Ext.ux.Toast.msg("信息提示", d.message);
							}
						},
						failure : function(e, f) {
							var d = Ext.util.JSON.decode(e.responseText);
							Ext.ux.Toast.msg("信息提示", d.message);
						}
					});
				}
			});
		} else {
			Ext.ux.Toast.msg("信息提示", "抱歉，该类型的折算方法待实现！");
		}
	}
};