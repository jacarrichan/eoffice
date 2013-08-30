Ext.ns("SearchDocument");
var SearchDocument = function(a) {
	return this.getView(a);
};
SearchDocument.prototype.getView = function(a) {
	return new Ext.Panel({
		id : "SearchDocument",
		title : "搜索文档",
		iconCls : "menu-document",
		border : false,
		style : "padding-bottom:10px;",
		autoScroll : true,
		items : [ {
			region : "center",
			anchor : "100%",
			items : [ new Ext.FormPanel({
				id : "ALLDocumentSearchForm",
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
					text : "请输入条件:"
				}, {
					xtype : "textfield",
					name : "content",
					width : 150
				}, {
					xtype : "button",
					text : "查询",
					iconCls : "search",
					handler : function() {
						var b = Ext.getCmp("ALLDocumentSearchForm");
						var c = Ext.getCmp("SearchDocumentGrid");
						if (b.getForm().isValid()) {
							$search({
								searchPanel : b,
								gridPanel : c
							});
						}
					}
				}, {
					xtype : "button",
					text : "重置",
					iconCls : "reset",
					handler : function() {
						var b = Ext.getCmp("ALLDocumentSearchForm");
						b.getForm().reset();
					}
				} ]
			}), this.setup(a) ]
		} ]
	});
};
SearchDocument.prototype.setup = function(a) {
	return this.grid(a);
};
SearchDocument.prototype.grid = function(d) {
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						new Ext.grid.RowNumberer(),
						{
							header : "docId",
							dataIndex : "docId",
							hidden : true
						},
						{
							header : "文档名称",
							dataIndex : "docName",
							width : 120
						},
						{
							header : "创建人",
							dataIndex : "fullname"
						},
						{
							header : "创建时间",
							dataIndex : "createtime"
						},
						{
							header : "属性",
							width : 40,
							dataIndex : "isShared",
							renderer : function(h, f, e) {
								var g = e.data.isPublic;
								if (h == 1) {
									return '<img src="'
											+ __ctxPath
											+ '/images/flag/shared.png" alt="共享" title="共享文档" />';
								} else {
									if (g == "0") {
										return '<img src="'
												+ __ctxPath
												+ '/images/flag/lock.png" alt="私有" title="私有文档" />';
									} else {
										return '<img src="'
												+ __ctxPath
												+ '/images/btn/flow/unlockTask.png" alt="公共" title="公共文档" />';
									}
								}
							}
						},
						{
							header : "附件",
							dataIndex : "haveAttach",
							renderer : function(j, g, e) {
								if (j == "" || j == "0") {
									return "无附件";
								} else {
									var h = e.data.attachFiles;
									var k = "";
									for ( var f = 0; f < h.length; f++) {
										k += '<a href="#" onclick="FileAttachDetail.show('
												+ h[f].fileId
												+ ');" class="attachment">'
												+ h[f].fileName + "</a>";
										k += "&nbsp;";
									}
									return k;
								}
							}
						} ],
				defaults : {
					sortable : true,
					menuDisabled : false,
					width : 100
				}
			});
	var b = this.store();
	b.load({
		params : {
			start : 0,
			limit : 25,
			content : d
		}
	});
	var c = new Ext.grid.GridPanel({
		id : "SearchDocumentGrid",
		store : b,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		autoHeight : true,
		maxHeight : 600,
		cm : a,
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
	c.addListener("rowdblclick", function(g, f, h) {
		g.getSelectionModel().each(function(k) {
			var l = k.data.docId;
			var j = k.data.docName;
			var i = Ext.getCmp("centerTabPanel");
			var e = Ext.getCmp("PulicDocumentDetail");
			if (e == null) {
				e = new PublicDocumentDetail({
					docId : l,
					docName : j
				});
				Ext.getCmp("PublicDocumentTopBar").hide();
				i.add(e);
				i.activate(e);
			} else {
				i.remove("PulicDocumentDetail");
				e = new PublicDocumentDetail({
					docId : l,
					docName : j
				});
				Ext.getCmp("PublicDocumentTopBar").hide();
				i.add(e);
				i.activate(e);
			}
		});
	});
	return c;
};
SearchDocument.prototype.store = function() {
	var a = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + "/document/searchDocument.do"
		}),
		reader : new Ext.data.JsonReader(
				{
					root : "result",
					totalProperty : "totalCounts",
					id : "id",
					fields : [ {
						name : "docId",
						type : "int"
					}, "docName", "fullname", {
						name : "isPublic",
						mapping : "docFolder.isShared"
					}, "content", "createtime", "haveAttach", "attachFiles",
							"isShared" ]
				}),
		remoteSort : true
	});
	a.setDefaultSort("docId", "desc");
	return a;
};