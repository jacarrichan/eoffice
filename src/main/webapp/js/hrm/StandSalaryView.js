Ext.ns("StandSalaryView");
var StandSalaryView = function() {
	return new Ext.Panel(
			{
				id : "StandSalaryView",
				iconCls : "menu-goods-apply",
				title : "薪酬标准列表",
				autoScroll : true,
				items : [
						new Ext.FormPanel(
								{
									id : "StandSalarySearchForm",
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
												text : "标准编号"
											},
											{
												xtype : "textfield",
												width : 80,
												name : "Q_standardNo_S_LK"
											},
											{
												text : "标准名称"
											},
											{
												xtype : "textfield",
												width : 80,
												name : "Q_standardName_S_LK"
											},
											{
												text : "状态"
											},
											{
												xtype : "combo",
												width : 110,
												hiddenName : "Q_status_SN_EQ",
												editable : false,
												mode : "local",
												triggerAction : "all",
												store : [ [ "", "　" ],
														[ "0", "未审核" ],
														[ "1", "审核通过" ],
														[ "2", "审核未通过" ] ]
											},
											{
												xtype : "button",
												text : "查询",
												iconCls : "search",
												handler : function() {
													var a = Ext
															.getCmp("StandSalarySearchForm");
													var b = Ext
															.getCmp("StandSalaryGrid");
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
StandSalaryView.prototype.setup = function() {
	return this.grid();
};
StandSalaryView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "standardId",
							dataIndex : "standardId",
							hidden : true
						},
						{
							header : "标准编号",
							dataIndex : "standardNo"
						},
						{
							header : "标准名称",
							dataIndex : "standardName"
						},
						{
							header : "总金额",
							dataIndex : "totalMoney",
							renderer : function(e) {
								return '<img src="' + __ctxPath
										+ '/images/flag/customer/rmb.png"/>'
										+ e;
							}
						},
						{
							header : "制定日期",
							dataIndex : "setdownTime",
							renderer : function(e) {
								return e.substring(0, 10);
							}
						},
						{
							header : "状态",
							dataIndex : "status",
							renderer : function(e) {
								if (e == "0") {
									return "未审核";
								} else {
									if (e == "1") {
										return '<img title="通过审核" src="' + __ctxPath + '/images/flag/customer/effective.png"/>';
									} else {
										return '<img title="没通过审核" src="' + __ctxPath + '/images/flag/customer/invalid.png"/>';
									}
								}
							}
						},
						{
							header : "管理",
							dataIndex : "standardId",
							width : 100,
							sortable : false,
							renderer : function(h, g, e, k, f) {
								var j = e.data.standardId;
								var i = "";
								if (isGranted("_StandSalaryDel")) {
									i += '<button title="删除" value=" " class="btn-del" onclick="StandSalaryView.remove(' + j + ')">&nbsp;&nbsp;</button>';
								}
								if (isGranted("_StandSalaryEdit")) {
									i += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="StandSalaryView.edit(' + j + ')">&nbsp;&nbsp;</button>';
								}
								if (e.data.status != 1) {
									if (isGranted("_StandSalaryCheck")) {
										i += '&nbsp;<button title="审核" value=" " class="btn-salary-apply" onclick="StandSalaryView.check(' + j + ')">&nbsp;&nbsp;</button>';
									}
								}
								if (isGranted("_StandSalaryQuery")) {
									i += '&nbsp;<button title="操作纪录" value=" " class="btn-operation" onclick="StandSalaryView.operation(' + j + ')">&nbsp;&nbsp;</button>';
								}
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
		id : "StandSalaryGrid",
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
			displayMsg : "当前显示{0}至{1}， 共{2}条记录",
			emptyMsg : "当前没有记录"
		})
	});
	c.addListener("rowdblclick", function(g, f, h) {
		g.getSelectionModel().each(function(e) {
			if (isGranted("_StandSalaryEdit")) {
				StandSalaryView.edit(e.data.standardId);
			}
		});
	});
	return c;
};
StandSalaryView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/hrm/listStandSalary.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "standardId",
				type : "int"
			}, "standardNo", "standardName", "totalMoney", "setdownTime",
					"status" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("standardId", "desc");
	return a;
};
StandSalaryView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "StandSalaryFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : []
	});
	if (isGranted("_StandSalaryAdd")) {
		a.add( {
			iconCls : "btn-add",
			text : "添加标准",
			xtype : "button",
			handler : function() {
				var b = Ext.getCmp("centerTabPanel");
				var c = Ext.getCmp("StandSalaryForm");
				if (c == null) {
					c = new StandSalaryForm();
					b.add(c);
				} else {
					b.remove("StandSalaryForm");
					c = new StandSalaryForm();
					b.add(c);
				}
				b.activate(c);
			}
		});
	}
	if (isGranted("_StandSalaryDel")) {
		a.add( {
			iconCls : "btn-del",
			text : "删除标准",
			xtype : "button",
			handler : function() {
				var d = Ext.getCmp("StandSalaryGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.standardId);
				}
				StandSalaryView.remove(e);
			}
		});
	}
	return a;
};
StandSalaryView.remove = function(b) {
	var a = Ext.getCmp("StandSalaryGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/hrm/multiDelStandSalary.do",
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
StandSalaryView.edit = function(c) {
	var a = Ext.getCmp("centerTabPanel");
	var b = Ext.getCmp("StandSalaryForm");
	if (b == null) {
		b = new StandSalaryForm(c);
		a.add(b);
	} else {
		a.remove("StandSalaryForm");
		b = new StandSalaryForm(c);
		a.add(b);
	}
	a.activate(b);
};
StandSalaryView.check = function(a) {
	new CheckStandSalaryForm(a);
};
StandSalaryView.operation = function(a) {
	var b = new Ext.Window( {
		id : "CheckStandSalaryFormWin",
		title : "标准操作纪录",
		iconCls : "menu-goods-apply",
		width : 500,
		x : 300,
		y : 50,
		autoHeight : true,
		border : false,
		modal : true,
		layout : "anchor",
		plain : true,
		bodyStyle : "padding:5px;",
		buttonAlign : "center",
		items : [ new Ext.Panel( {
			autoScroll : true,
			autoHeight : true,
			border : false,
			autoLoad : {
				url : __ctxPath + "/pages/hrm/standOperation.jsp?standardId="
						+ a
			}
		}) ],
		buttons : [ {
			text : "关闭",
			iconCls : "btn-cancel",
			handler : function() {
				b.close();
			}
		} ]
	});
	b.show();
};