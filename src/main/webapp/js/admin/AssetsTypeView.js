Ext.ns("AssetsTypeView");
var AssetsTypeView = function() {
	return new Ext.Panel(
			{
				id : "AssetsTypeView",
				title : "[AssetsType]列表",
				autoScroll : true,
				items : [
						new Ext.FormPanel(
								{
									id : "AssetsTypeSearchForm",
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
									items : [
											{
												text : "请输入查询条件:"
											},
											{
												text : "分类名称"
											},
											{
												xtype : "textfield",
												name : "Q_typeName_S_LK"
											},
											{
												xtype : "button",
												text : "查询",
												iconCls : "search",
												handler : function() {
													var a = Ext
															.getCmp("AssetsTypeSearchForm");
													var b = Ext
															.getCmp("AssetsTypeGrid");
													if (a.getForm().isValid()) {
														a
																.getForm()
																.submit(
																		{
																			waitMsg : "正在提交查询",
																			url : __ctxPath
																					+ "/admin/listAssetsType.do",
																			success : function(
																					d,
																					e) {
																				var c = Ext.util.JSON
																						.decode(e.response.responseText);
																				b
																						.getStore()
																						.loadData(
																								c);
																			}
																		});
													}
												}
											} ]
								}), this.setup() ]
			});
};
AssetsTypeView.prototype.setup = function() {
	return this.grid();
};
AssetsTypeView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "assetsTypeId",
							dataIndex : "assetsTypeId",
							hidden : true
						},
						{
							header : "分类名称",
							dataIndex : "typeName"
						},
						{
							header : "管理",
							dataIndex : "assetsTypeId",
							width : 50,
							sortable : false,
							renderer : function(h, g, e, k, f) {
								var j = e.data.assetsTypeId;
								var i = '<button title="删除" value=" " class="btn-del" onclick="AssetsTypeView.remove(' + j + ')">&nbsp;</button>';
								i += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="AssetsTypeView.edit(' + j + ')">&nbsp;</button>';
								return i;
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
		id : "AssetsTypeGrid",
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
			AssetsTypeView.edit(e.data.assetsTypeId);
		});
	});
	return c;
};
AssetsTypeView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/admin/listAssetsType.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "assetsTypeId",
				type : "int"
			}, "typeName" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("assetsTypeId", "desc");
	return a;
};
AssetsTypeView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "AssetsTypeFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : [ {
			iconCls : "btn-add",
			text : "添加[AssetsType]",
			xtype : "button",
			handler : function() {
				new AssetsTypeForm();
			}
		}, {
			iconCls : "btn-del",
			text : "删除[AssetsType]",
			xtype : "button",
			handler : function() {
				var d = Ext.getCmp("AssetsTypeGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.assetsTypeId);
				}
				AssetsTypeView.remove(e);
			}
		} ]
	});
	return a;
};
AssetsTypeView.remove = function(b) {
	var a = Ext.getCmp("AssetsTypeGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/admin/multiDelAssetsType.do",
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
AssetsTypeView.edit = function(a) {
	new AssetsTypeForm(a);
};