StatisticsArchFileReportPanel = Ext
		.extend(
				Ext.Panel,
				{
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.initUIComponents();
						StatisticsArchFileReportPanel.superclass.constructor
								.call(this, {
									id : "StatisticsArchFileReportPanel",
									layoutConfig : {
										padding : "5",
										pack : "center",
										align : "middle"
									},
									layout : "form",
									items : [ this.searchFormPanel,
											this.fondGrid ],
									title : "文件统计",
									listeners : {
										"afterlayout" : function(b) {
										}
									}
								});
					},
					initUIComponents : function() {
						this.searchTypeComBox = new Ext.form.ComboBox(
								{
									hiddenName : "searchPath",
									store : new Ext.data.ArrayStore(
											{
												fields : [ "name", "path" ],
												data : [
														[ "案卷",
																"/arch/fileReportByRollArchReport.do" ],
														[ "保管期限",
																"/arch/fileReportByTimeLimitArchReport.do" ] ]
											}),
									value : "/arch/fileReportByRollArchReport.do",
									displayField : "name",
									valueField : "path",
									typeAhead : true,
									mode : "local",
									triggerAction : "all",
									forceSelection : true,
									listeners : {
										scope : this,
										"select" : function(d, b, c) {
											this.store.proxy.conn.url = __ctxPath
													+ d.getValue();
											this.store.load( {
												params : {
													itemName : "文件保管期限"
												}
											});
											var a = this.fondGrid
													.getColumnModel();
											a.setColumnHeader(0, b.get("name"));
										}
									}
								});
						this.searchFormPanel = new Ext.FormPanel( {
							layout : "table",
							width : 800,
							layoutConfig : {
								columns : 3
							},
							style : "padding:30px 100px 0px",
							border : true,
							bodyBorder : false,
							hideBorders : false,
							region : "north",
							autoScroll : false,
							defaults : {
								border : true,
								width : 300,
								height : 25,
								layout : "form",
								anchor : "100%,100%",
								xtype : "panel"
							},
							items : [ {
								colspan : 2,
								width : 300,
								items : {
									text : "统计方式:",
									xtype : "label"
								}
							}, {
								items : this.searchTypeComBox
							} ]
						});
						this.fields = [ "name", {
							name : "nums",
							type : "int"
						}, {
							name : "isTotal",
							type : "boolean"
						} ];
						this.columns = [ {
							header : "案卷号",
							renderer : function(c, b, a) {
								if (a.get("isTotal")) {
									b.attr = 'style="font-weight: bold"';
								}
								return c;
							},
							sortable : false,
							groupable : false,
							dataIndex : "name"
						}, {
							header : "总计",
							renderer : function(c, b, a) {
								if (a.get("isTotal")) {
									b.attr = 'style="font-weight: bold"';
								}
								return c;
							},
							sortable : false,
							groupable : false,
							dataIndex : "nums"
						} ];
						this.store = new Ext.data.Store(
								{
									proxy : new Ext.data.HttpProxy(
											{
												url : __ctxPath
														+ "/arch/fileReportByRollArchReport.do",
												method : "POST"
											}),
									reader : new Ext.data.JsonReader( {
										root : "result",
										remoteSort : false,
										fields : this.fields
									})
								});
						this.store.on("beforeload", function(a) {
							a.baseParams = {
								start : 0,
								limit : 100000
							};
						}, this);
						this.store.on("load", function(b, a, c) {
							var e = 0;
							Ext.each(a, function(f) {
								e += f.get("nums");
							}, this);
							var d = new b.recordType();
							d.data = {};
							d.data.name = "合计";
							d.data.nums = e;
							d.data.isTotal = true;
							b.add(d);
							b.commitChanges();
						}, this);
						this.store.load();
						this.cm = new Ext.grid.ColumnModel( {
							columns : this.columns,
							defaults : {
								sortable : false,
								menuDisabled : true
							}
						});
						this.fondGrid = new Ext.grid.GridPanel( {
							width : 800,
							style : "padding:0px 100px",
							stripeRows : true,
							frame : false,
							border : true,
							store : this.store,
							cm : this.cm,
							autoHeight : true,
							viewConfig : {
								forceFit : true,
								autoFill : true
							},
							listeners : {}
						});
					}
				});