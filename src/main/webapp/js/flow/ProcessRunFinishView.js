Ext.ns("ProcessRunFinishView");
var ProcessRunFinishView = function() {
	return new Ext.Panel( {
		id : "ProcessRunFinishView",
		title : "已办结的流程",
		iconCls : "menu-flowEnd",
		autoScroll : true,
		items : [ new Ext.FormPanel( {
			id : "ProcessRunFinishSearchForm",
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
				text : "标题"
			}, {
				xtype : "textfield",
				name : "Q_subject_S_LK"
			}, {
				text : "时间 从"
			}, {
				xtype : "datefield",
				name : "Q_createtime_D_GT",
				format : "Y-m-d"
			}, {
				text : " 至 "
			}, {
				xtype : "datefield",
				name : "Q_createtime_D_LT",
				format : "Y-m-d"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var a = Ext.getCmp("ProcessRunFinishSearchForm");
					var b = Ext.getCmp("ProcessRunFinishGrid");
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
ProcessRunFinishView.prototype.setup = function() {
	return this.grid();
};
ProcessRunFinishView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "runId",
							dataIndex : "runId",
							hidden : true
						},
						{
							header : "标题",
							dataIndex : "subject"
						},
						{
							header : "时间",
							dataIndex : "createtime",
							width : 60
						},
						{
							header : "流程状态",
							dataIndex : "runStatus",
							renderer : function(e) {
								if (e == 0) {
									return '<font color="red">草稿</font>';
								} else {
									if (e == 1) {
										return '<font color="green">正在运行</font>';
									} else {
										if (e == 2) {
											return '<font color="gray">结束</font>';
										}
									}
								}
							}
						},
						{
							header : "管理",
							dataIndex : "runId",
							width : 50,
							renderer : function(l, j, f, i, n) {
								var k = f.data.runId;
								var e = f.data.defId;
								var m = f.data.piId;
								var h = f.data.subject;
								var g = '&nbsp;<button type="button" title="审批明细" value=" " class="btn-flowView" onclick="ProcessRunFinishView.detail('
										+ k
										+ ","
										+ e
										+ ",'"
										+ m
										+ "','"
										+ h
										+ "')\"></button>";
								return g;
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
		id : "ProcessRunFinishGrid",
		store : b,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		autoHeight : true,
		tbar : new Ext.Toolbar(),
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
		g.getSelectionModel().each(
				function(e) {
					ProcessRunFinishView.detail(e.data.runId, e.data.defId,
							e.data.piId, e.data.subject);
				});
	});
	return c;
};
ProcessRunFinishView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/flow/listProcessRun.do?Q_runStatus_SN_EQ=2"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "runId",
				type : "int"
			}, "subject", "createtime", "defId", "piId", "runStatus" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("runId", "desc");
	return a;
};
ProcessRunFinishView.detail = function(d, a, c, b) {
	var f = App.getContentPanel();
	var e = f.getItem("ProcessRunDetail" + d);
	if (e == null) {
		e = new ProcessRunDetail(d, a, c, b);
		f.add(e);
	}
	f.activate(e);
};