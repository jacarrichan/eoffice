Ext.ns("HireIssueView");
var HireIssueView = function() {
	return new Ext.Panel( {
		id : "HireIssueView",
		title : "招聘信息列表",
		iconCls : "menu-hireIssue",
		autoScroll : true,
		items : [ new Ext.FormPanel( {
			id : "HireIssueSearchForm",
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
				text : "请输入查询条件:"
			}, {
				text : "招聘职位"
			}, {
				xtype : "textfield",
				name : "Q_jobName_S_LK"
			}, {
				text : "登记人"
			}, {
				xtype : "textfield",
				name : "Q_regFullname_S_LK"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var a = Ext.getCmp("HireIssueSearchForm");
					var b = Ext.getCmp("HireIssueGrid");
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
HireIssueView.prototype.setup = function() {
	return this.grid();
};
HireIssueView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "hireId",
							dataIndex : "hireId",
							hidden : true
						},
						{
							header : "标题",
							dataIndex : "title"
						},
						{
							header : "开始时间",
							dataIndex : "startDate",
							renderer : function(e) {
								return e.replace(/00:00:00/, "");
							}
						},
						{
							header : "结束时间",
							dataIndex : "endDate",
							renderer : function(e) {
								return e.replace(/00:00:00/, "");
							}
						},
						{
							header : "招聘人数",
							dataIndex : "hireCount"
						},
						{
							header : "招聘职位",
							dataIndex : "jobName"
						},
						{
							header : "登记人",
							dataIndex : "regFullname"
						},
						{
							header : "登记时间",
							dataIndex : "regDate"
						},
						{
							header : "审核状态",
							dataIndex : "status",
							renderer : function(e) {
								if (e == 0) {
									return "未审核";
								} else {
									if (e == 1) {
										return '<img title="通过审核" src="' + __ctxPath + '/images/flag/customer/effective.png"/>';
									} else {
										return '<img title="没通过审核" src="' + __ctxPath + '/images/flag/customer/invalid.png"/>';
									}
								}
							}
						},
						{
							header : "管理",
							dataIndex : "hireId",
							width : 100,
							sortable : false,
							renderer : function(i, h, f, l, g) {
								var k = f.data.hireId;
								var e = f.data.status;
								var j = "";
								if (e == 0) {
									if (isGranted("_HireIssueCheck")) {
										j = '<button title="审核" value=" " class="menu-goods-apply" onclick="HireIssueView.check(' + k + ')">&nbsp;&nbsp;</button>';
									}
								} else {
									if (isGranted("_HireIssueQuery")) {
										j += '&nbsp;<button title="查看" value=" " class="btn-readdocument" onclick="HireIssueView.display(' + k + ')">&nbsp;&nbsp;</button>';
									}
								}
								if (e != 1) {
									if (isGranted("_HireIssueEdit")) {
										j += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="HireIssueView.edit(' + k + ')">&nbsp;&nbsp;</button>';
									}
								}
								if (isGranted("_HireIssueDel")) {
									j += '<button title="删除" value=" " class="btn-del" onclick="HireIssueView.remove(' + k + ')">&nbsp;&nbsp;</button>';
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
		id : "HireIssueGrid",
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
		g.getSelectionModel().each(function(i) {
			var e = i.data.status;
			if (e != 1) {
				if (isGranted("_HireIssueEdit")) {
					HireIssueView.edit(i.data.hireId);
				}
			} else {
				if (isGranted("_HireIssueQuery")) {
					HireIssueView.display(i.data.hireId);
				}
			}
		});
	});
	return c;
};
HireIssueView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/hrm/listHireIssue.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "hireId",
				type : "int"
			}, "title", "startDate", "endDate", "hireCount", "jobName",
					"jobCondition", "regFullname", "regDate", "modifyFullname",
					"modifyDate", "checkFullname", "checkOpinion", "checkDate",
					"status", "memo" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("hireId", "desc");
	return a;
};
HireIssueView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "HireIssueFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : []
	});
	if (isGranted("_HireIssueAdd")) {
		a.add(new Ext.Button( {
			iconCls : "btn-add",
			text : "添加招聘信息",
			handler : function() {
				new HireIssueForm();
			}
		}));
	}
	if (isGranted("_HireIssueDel")) {
		a.add(new Ext.Button( {
			iconCls : "btn-del",
			text : "删除招聘信息",
			handler : function() {
				var d = Ext.getCmp("HireIssueGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.hireId);
				}
				HireIssueView.remove(e);
			}
		}));
	}
	return a;
};
HireIssueView.remove = function(b) {
	var a = Ext.getCmp("HireIssueGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/hrm/multiDelHireIssue.do",
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
HireIssueView.edit = function(a) {
	new HireIssueForm(a);
};
HireIssueView.check = function(a) {
	new HireIssueCheckWin(a);
};
HireIssueView.display = function(a) {
	new HireIssueCheckWin(a);
	Ext.getCmp("HireIssueChckeWin").remove("HireIssueCheckFormPanel");
	Ext.getCmp("NotPassHireIssueSb").hide();
	Ext.getCmp("PassHireIssueSb").hide();
};