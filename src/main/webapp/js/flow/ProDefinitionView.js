Ext.ns("ProDefinitionView");
var ProDefinitionView = function(a) {
	this.isManager = a;
};
ProDefinitionView.prototype.setTypeId = function(a) {
	this.typeId = a;
	ProDefinitionView.typeId = a;
};
ProDefinitionView.prototype.getView = function() {
	var b = this.typeId;
	var a = new Ext.FormPanel({
		height : 40,
		frame : false,
		border : false,
		layout : "hbox",
		layoutConfig : {
			padding : "5",
			pack : "center",
			align : "middle"
		},
		defaults : {
			margins : "4 4 4 4"
		},
		items : [ {
			xtype : "label",
			text : "请输入查询条件:"
		}, {
			text : "流程的名称",
			xtype : "label"
		}, {
			xtype : "textfield",
			name : "Q_name_S_LK",
			width : 150
		}, {
			xtype : "button",
			text : "查询",
			iconCls : "search",
			scope : this,
			handler : function() {
				var c = this.gridPanel;
				if (a.getForm().isValid()) {
					$search({
						searchPanel : a,
						gridPanel : c
					});
				}
			}
		} ]
	});
	this.gridPanel = this.setup();
	return new Ext.Panel({
		title : "流程列表",
		layout : "anchor",
		region : "center",
		autoScroll : true,
		items : [ a, this.gridPanel ]
	});
};
ProDefinitionView.prototype.setup = function() {
	var d = this.isManager;
	var f = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						f,
						new Ext.grid.RowNumberer(),
						{
							header : "defId",
							dataIndex : "defId",
							hidden : true
						},
						{
							header : "分类名称",
							dataIndex : "proType",
							renderer : function(g) {
								if (g != null) {
									return g.typeName;
								}
							}
						},
						{
							header : "流程的名称",
							dataIndex : "name"
						},
						{
							header : "描述",
							dataIndex : "description"
						},
						{
							header : "创建时间",
							dataIndex : "createtime"
						},
						{
							header : "工作流id",
							dataIndex : "deployId",
							hidden : "true"
						},
						{
							header : "管理",
							dataIndex : "defId",
							renderer : function(n, m, j, l, o) {
								var h = j.data.defId;
								var g = j.data.name;
								var i = j.data.deployId;
								var k = "";
								if (d) {
									if (isGranted("_FlowDel")) {
										k = '<button title="删除" value=" " class="btn-del" onclick="ProDefinitionView.remove('
												+ h + ')"></button>';
									}
									if (isGranted("_FlowEdit")) {
										k += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="ProDefinitionView.edit('
												+ h + ')"></button>';
									}
								}
								if (i != null) {
									k += '&nbsp;<button title="查看" class="btn-preview" onclick="ProDefinitionView.view('
											+ h + ",'" + g + "')\"></button>";
									if (d) {
										if (isGranted("_FlowSetting")) {
											k += '&nbsp;<button title="流程设置" class="btn-setting" onclick="ProDefinitionView.setting('
													+ ""
													+ h
													+ ",'"
													+ g
													+ "')\"></button>";
										}
									}
								}
								k += '&nbsp;<button title="新建流程" class="btn-newFlow" onclick="ProDefinitionView.newFlow('
										+ "" + h + ",'" + g + "')\"></button>";
								return k;
							}
						} ],
				defaults : {
					sortable : true,
					menuDisabled : false
				}
			});
	var e = d ? this.topbar() : null;
	var b = this.store();
	b.load({
		params : {
			start : 0,
			limit : 25
		}
	});
	var c = new Ext.grid.GridPanel({
		layout : "fit",
		id : "ProDefinitionGrid" + (d ? "" : "0"),
		tbar : e,
		store : b,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		autoHeight : true,
		cm : a,
		sm : f,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		bbar : new Ext.PagingToolbar({
			pageSize : 25,
			store : b,
			displayInfo : true,
			displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
			emptyMsg : "当前没有记录"
		})
	});
	c.addListener("rowdblclick", function(h, g, i) {
		h.getSelectionModel().each(function(j) {
			if (d) {
				if (isGranted("_FlowEdit")) {
					ProDefinitionView.edit(j.data.defId);
				}
			}
		});
	});
	return c;
};
ProDefinitionView.prototype.store = function() {
	var a = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + "/flow/listProDefinition.do",
			params : {
				typeId : this.typeId == null ? 0 : this.typeId
			}
		}),
		reader : new Ext.data.JsonReader({
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "defId",
				type : "int"
			}, "proType", "name", "description", "createtime", "deployId" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("defId", "desc");
	return a;
};
ProDefinitionView.prototype.topbar = function() {
	var a = new Ext.Toolbar({
		height : 30,
		items : []
	});
	a.add(new Ext.Button({
		iconCls : "btn-flow-design",
		text : "设计新流程",
		handler : function() {
			new FlowDesignerWindow().show();
		}
	}));
	if (isGranted("_FlowAdd")) {
		a.add(new Ext.Button({
			iconCls : "btn-add",
			text : "发布流程",
			handler : function() {
				new ProDefinitionForm(null, ProDefinitionView.typeId);
			}
		}));
	}
	if (isGranted("_FlowDel")) {
		a.add(new Ext.Button({
			iconCls : "btn-del",
			text : "删除流程",
			handler : function() {
				var d = Ext.getCmp("ProDefinitionGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.defId);
				}
				ProDefinitionView.remove(e);
			}
		}));
	}
	return a;
};
ProDefinitionView.remove = function(b) {
	var a = Ext.getCmp("ProDefinitionGrid");
	Ext.Msg.confirm("信息确认", "注意：删除该流程定义，该流程下的所有相关数据也一并删除，\n并不能恢复，您确认要删除该记录吗？",
			function(c) {
				if (c == "yes") {
					Ext.Ajax.request({
						url : __ctxPath + "/flow/multiDelProDefinition.do",
						params : {
							ids : b
						},
						method : "post",
						success : function() {
							Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
							a.getStore().reload({
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
ProDefinitionView.edit = function(a) {
	new ProDefinitionForm(a);
};
ProDefinitionView.view = function(a, b) {
	var d = App.getContentPanel();
	var c = d.getItem("ProDefinitionDetail" + a);
	if (c == null) {
		c = new ProDefinitionDetail(a, b);
		d.add(c);
	}
	d.activate(c);
};
ProDefinitionView.setting = function(a, c) {
	var d = App.getContentPanel();
	var b = d.getItem("ProDefinitionSetting" + a);
	if (b == null) {
		b = new ProDefinitionSetting(a, c);
		d.add(b);
	}
	d.activate(b);
};
ProDefinitionView.newFlow = function(a, c) {
	var d = App.getContentPanel();
	var b = d.getItem("ProcessRunStart" + a);
	if (b == null) {
		b = new ProcessRunStart({
			id : "ProcessRunStart" + a,
			defId : a,
			flowName : c
		});
		d.add(b);
	}
	d.activate(b);
};