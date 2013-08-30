Ext.ns("AppointmentView");
var AppointmentView = function() {
	return new Ext.Panel(
			{
				id : "AppointmentView",
				title : "约会列表",
				iconCls : "menu-appointment",
				autoScroll : true,
				items : [
						new Ext.FormPanel(
								{
									id : "AppointmentSearchForm",
									frame : false,
									border : false,
									layout : "form",
									bodyStyle : "padding:6px 0px 4px 8px",
									layoutConfig : {
										padding : "6px 6px 4px 8px",
										align : "middle"
									},
									items : [ {
										layout : "column",
										border : false,
										defaults : {
											border : false,
											margins : {
												top : 2,
												right : 4,
												bottom : 2,
												left : 4
											}
										},
										items : [
												{
													columnWidth : 0.4,
													layout : "form",
													border : false,
													defaults : {
														anchor : "98%,98%"
													},
													items : [
															{
																xtype : "datetimefield",
																fieldLabel : "开始时间",
																name : "Q_startTime_D_GT",
																format : "Y-m-d H:i:s",
																width : 220
															},
															{
																xtype : "datetimefield",
																fieldLabel : "结束时间",
																name : "Q_endTime_D_LT",
																format : "Y-m-d H:i:s",
																width : 220
															} ]
												},
												{
													columnWidth : 0.4,
													layout : "form",
													border : false,
													defaults : {
														anchor : "98%,98%"
													},
													items : [
															{
																xtype : "textfield",
																name : "Q_subject_S_LK",
																fieldLabel : "标题"
															},
															{
																xtype : "textfield",
																name : "Q_content_S_LK",
																fieldLabel : "内容"
															} ]
												},
												{
													border : false,
													columnWidth : 0.2,
													defaults : {
														margins : {
															top : 2,
															right : 4,
															bottom : 2,
															left : 4
														}
													},
													items : [
															{
																xtype : "button",
																text : "查询",
																iconCls : "search",
																handler : function() {
																	var a = Ext
																			.getCmp("AppointmentSearchForm");
																	var b = Ext
																			.getCmp("AppointmentGrid");
																	if (a
																			.getForm()
																			.isValid()) {
																		a
																				.getForm()
																				.submit(
																						{
																							waitMsg : "正在提交查询",
																							url : __ctxPath
																									+ "/task/listAppointment.do",
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
															},
															{
																xtype : "button",
																text : "重置",
																iconCls : "btn-reseted",
																handler : function() {
																	var a = Ext
																			.getCmp("AppointmentSearchForm");
																	a
																			.getForm()
																			.reset();
																}
															} ]
												} ]
									} ]
								}), this.setup() ]
			});
};
AppointmentView.prototype.setup = function() {
	return this.grid();
};
AppointmentView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "appointId",
							dataIndex : "appointId",
							hidden : true
						},
						{
							header : "主题",
							dataIndex : "subject"
						},
						{
							header : "开始时间",
							dataIndex : "startTime"
						},
						{
							header : "结束时间",
							dataIndex : "endTime"
						},
						{
							header : "地点",
							dataIndex : "location"
						},
						{
							header : "管理",
							dataIndex : "appointId",
							width : 50,
							sortable : false,
							renderer : function(h, g, e, k, f) {
								var j = e.data.appointId;
								var i = '<button title="删除" value=" " class="btn-del" onclick="AppointmentView.remove(' + j + ')">&nbsp;</button>';
								i += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="AppointmentView.edit(' + j + ')">&nbsp;</button>';
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
		id : "AppointmentGrid",
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
	AppUtil.addPrintExport(c);
	c.addListener("rowdblclick", function(g, f, h) {
		g.getSelectionModel().each(function(e) {
			AppointmentView.edit(e.data.appointId);
		});
	});
	return c;
};
AppointmentView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/task/listAppointment.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "appointId",
				type : "int"
			}, "userId", "subject", "startTime", "endTime", "content", "notes",
					"location", "inviteEmails" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("appointId", "desc");
	return a;
};
AppointmentView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "AppointmentFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : [ {
			iconCls : "btn-add",
			text : "添加约会",
			xtype : "button",
			handler : function() {
				new AppointmentForm();
			}
		}, {
			iconCls : "btn-del",
			text : "删除约会",
			xtype : "button",
			handler : function() {
				var d = Ext.getCmp("AppointmentGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.appointId);
				}
				AppointmentView.remove(e);
			}
		} ]
	});
	return a;
};
AppointmentView.remove = function(b) {
	var a = Ext.getCmp("AppointmentGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/task/multiDelAppointment.do",
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
AppointmentView.edit = function(a) {
	new AppointmentForm(a);
};