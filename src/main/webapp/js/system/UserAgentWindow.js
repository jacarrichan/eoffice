UserAgentWindow = Ext
		.extend(
				Ext.Window,
				{
					constructor : function(a) {
						Ext.apply(this, a);
						this.initUICmps();
						UserAgentWindow.superclass.constructor
								.call(
										this,
										{
											title : "用户" + a.username
													+ "代办账号设置",
											modal : true,
											iconCls : "btn-super",
											maximizable : true,
											buttonAlign : "center",
											width : 600,
											height : 400,
											layout : "border",
											items : [ this.searchPanel,
													this.centerPanel,
													this.southPanel ],
											buttons : [
													{
														xtype : "button",
														iconCls : "btn-ok",
														scope : this,
														text : "保存",
														handler : function() {
															var h = this;
															var f = Ext
																	.getCmp("selectedUserGrid");
															var e = this.userId;
															var c = f
																	.getStore();
															var g = c
																	.getCount();
															var b = "";
															for ( var d = 0; d < g; d++) {
																if (d > 0) {
																	b += ",";
																}
																b += c.getAt(d).data.grantUId;
															}
															$request( {
																url : __ctxPath
																		+ "/system/saveAgentAppUser.do",
																params : {
																	grantUIds : b,
																	userId : e
																},
																success : function(
																		i, j) {
																	Ext.ux.Toast
																			.msg(
																					"操作信息",
																					"成功保存代办账号~~");
																	h.close();
																}
															});
														}
													}, {
														xtype : "button",
														iconCls : "btn-cancel",
														text : "取消",
														scope : this,
														handler : function() {
															this.close();
														}
													} ]
										});
					},
					initUICmps : function() {
						this.searchPanel = new Ext.FormPanel( {
							height : 38,
							region : "north",
							layout : "hbox",
							bodyStyle : "padding:6px 2px 2px 2px",
							layoutConfigs : {
								align : "middle"
							},
							defaultType : "label",
							defaults : {
								margins : "0 4 0 4"
							},
							items : [
									{
										text : "用户姓名"
									},
									{
										xtype : "textfield",
										name : "fullname",
										width : 260
									},
									{
										xtype : "button",
										text : "查询",
										iconCls : "btn-search",
										scope : this,
										handler : function() {
											var g = this.searchPanel;
											var j = Ext.getCmp("contactGrid");
											var h = j.getStore();
											var k = Ext.Ajax.serializeForm(g
													.getForm().getEl());
											var i = Ext.urlDecode(k);
											i.start = 0;
											i.limit = h.baseParams.limit;
											i.userId = this.userId;
											h.baseParams = i;
											j.getBottomToolbar().moveFirst();
										}
									} ]
						});
						var d = new Ext.data.JsonStore( {
							url : __ctxPath + "/system/unAgentAppUser.do",
							root : "result",
							totalProperty : "totalCounts",
							baseParams : {
								userId : this.userId,
								start : 0,
								limit : 25
							},
							remoteSort : true,
							fields : [ {
								name : "userId",
								type : "int"
							}, "fullname", "title" ]
						});
						d.load();
						var f = new Ext.grid.CheckboxSelectionModel();
						var c = new Ext.grid.ColumnModel(
								{
									columns : [
											f,
											new Ext.grid.RowNumberer(),
											{
												header : "用户名",
												dataIndex : "fullname",
												renderer : function(h, i, g) {
													var j = g.data.title;
													if (j == 1) {
														return '<img src="'
																+ __ctxPath
																+ '/images/flag/man.png"/>&nbsp;'
																+ h;
													} else {
														return '<img src="'
																+ __ctxPath
																+ '/images/flag/women.png"/>&nbsp;'
																+ h;
													}
												},
												width : 60
											} ],
									defaults : {
										sortable : true,
										menuDisabled : true,
										width : 120
									}
								});
						this.centerPanel = new Ext.grid.EditorGridPanel( {
							title : "用户列表",
							autoScroll : true,
							id : "contactGrid",
							region : "center",
							height : 370,
							width : 240,
							autoWidth : false,
							store : d,
							shim : true,
							trackMouseOver : true,
							disableSelection : false,
							loadMask : true,
							cm : c,
							sm : f,
							viewConfig : {
								forceFit : true,
								enableRowBody : false,
								showPreview : false
							},
							bbar : new Ext.PagingToolbar( {
								pageSize : 25,
								store : d,
								displayInfo : true,
								displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
						});
						var b = new Ext.data.JsonStore( {
							url : __ctxPath + "/system/agentAppUser.do",
							root : "result",
							totalProperty : "totalCounts",
							baseParams : {
								userId : this.userId
							},
							remoteSort : true,
							fields : [ "grantUId", "grantFullname",
									"grantTitle" ]
						});
						b.load();
						var e = new Ext.grid.CheckboxSelectionModel();
						var a = new Ext.grid.EditorGridPanel(
								{
									id : "selectedUserGrid",
									region : "center",
									title : "代理账号",
									height : 400,
									width : 200,
									autoScroll : true,
									store : b,
									trackMouseOver : true,
									sm : e,
									columns : [
											e,
											new Ext.grid.RowNumberer(),
											{
												header : "用户名",
												dataIndex : "grantFullname",
												renderer : function(h, i, g) {
													var j = g.data.grantTitle;
													if (j == "1") {
														return '<img src="'
																+ __ctxPath
																+ '/images/flag/man.png"/>&nbsp;'
																+ h;
													} else {
														return '<img src="'
																+ __ctxPath
																+ '/images/flag/women.png"/>&nbsp;'
																+ h;
													}
												}
											} ]
								});
						this.southPanel = new Ext.Panel(
								{
									width : 200,
									region : "east",
									layout : "border",
									border : false,
									items : [
											new Ext.Panel(
													{
														width : 35,
														height : 430,
														region : "west",
														layout : {
															type : "vbox",
															pack : "center",
															align : "stretch"
														},
														defaults : {
															margins : "0 0 5 0"
														},
														items : [
																{
																	xtype : "button",
																	iconCls : "add-all",
																	text : "",
																	handler : function() {
																		var p = Ext
																				.getCmp("contactGrid");
																		var g = Ext
																				.getCmp("selectedUserGrid");
																		var q = g
																				.getStore();
																		var t = p
																				.getSelectionModel()
																				.getSelections();
																		for ( var k = 0; k < t.length; k++) {
																			var l = t[k].data.userId;
																			var r = t[k].data.fullname;
																			var o = t[k].data.title;
																			var n = false;
																			for ( var h = 0; h < q
																					.getCount(); h++) {
																				if (q
																						.getAt(h).data.grantUId == l) {
																					n = true;
																					break;
																				}
																			}
																			if (!n) {
																				var s = {
																					grantUId : l,
																					grantFullname : r,
																					grantTitle : o
																				};
																				var m = new q.recordType(
																						s);
																				g
																						.stopEditing();
																				q
																						.add(m);
																			}
																			for ( var h = t.length - 1; h >= 0; h--) {
																				p
																						.stopEditing();
																				p
																						.getStore()
																						.remove(
																								t[h]);
																			}
																		}
																	}
																},
																{
																	xtype : "button",
																	text : "",
																	iconCls : "rem-all",
																	handler : function() {
																		var q = Ext
																				.getCmp("contactGrid");
																		var l = q
																				.getStore();
																		var g = Ext
																				.getCmp("selectedUserGrid");
																		var r = g
																				.getStore();
																		var u = g
																				.getSelectionModel()
																				.getSelections();
																		for ( var k = 0; k < u.length; k++) {
																			var m = u[k].data.grantUId;
																			var s = u[k].data.grantFullname;
																			var p = u[k].data.grantTitle;
																			var o = false;
																			for ( var h = 0; h < l
																					.getCount(); h++) {
																				if (l
																						.getAt(h).data.userId == m) {
																					o = true;
																					break;
																				}
																			}
																			if (!o) {
																				var t = {
																					userId : m,
																					fullname : s,
																					title : p
																				};
																				var n = new l.recordType(
																						t);
																				q
																						.stopEditing();
																				l
																						.add(n);
																			}
																		}
																		for ( var h = u.length - 1; h >= 0; h--) {
																			g
																					.stopEditing();
																			r
																					.remove(u[h]);
																		}
																	}
																} ]
													}), a ]
								});
					}
				});