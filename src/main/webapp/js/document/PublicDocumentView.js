Ext.ns("PublicDocumentView");
var PublicDocumentView = function() {
};
PublicDocumentView.prototype.getView = function() {
	return new Ext.Panel({
		id : "PublicDocumentView",
		title : "公共文档列表",
		autoScroll : true,
		region : "center",
		anchor : "100%",
		items : [ new Ext.FormPanel({
			id : "PublicDocumentSearchForm",
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
				text : "文档名称"
			}, {
				xtype : "textfield",
				name : "document.docName",
				width : 90
			}, {
				text : "创建时间 从"
			}, {
				xtype : "datefield",
				format : "Y-m-d",
				name : "from"
			}, {
				text : "至"
			}, {
				xtype : "datefield",
				format : "Y-m-d",
				name : "to"
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "btn-search",
				handler : function() {
					var a = Ext.getCmp("PublicDocumentSearchForm");
					var b = Ext.getCmp("PublicDocumentGrid");
					if (a.getForm().isValid()) {
						$search({
							searchPanel : a,
							gridPanel : b
						});
					}
				}
			} ]
		}), this.setup() ]
	});
};
PublicDocumentView.prototype.setFolderId = function(a) {
	this.folderId = a;
	PublicDocumentView.folderId = a;
};
PublicDocumentView.prototype.getFolderId = function() {
	return this.folderId;
};
PublicDocumentView.prototype.setup = function() {
	return this.grid();
};
PublicDocumentView.prototype.grid = function() {
	var e = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						e,
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
							header : "创建 人",
							dataIndex : "fullname",
							width : 120
						},
						{
							header : "修改时间",
							dataIndex : "createtime"
						},
						{
							header : "文件夹",
							dataIndex : "forlderName"
						},
						{
							header : "附件",
							dataIndex : "haveAttach",
							renderer : function(k, h, f) {
								if (k == "" || k == "0") {
									return "无附件";
								} else {
									var j = f.data.attachFiles;
									var l = "";
									for ( var g = 0; g < j.length; g++) {
										l += '<a href="#" onclick="FileAttachDetail.show('
												+ j[g].fileId
												+ ');" class="attachment">'
												+ j[g].fileName + "</a>";
										l += "&nbsp;";
									}
									return l;
								}
							}
						},
						{
							header : "管理",
							dataIndex : "docId",
							width : 50,
							renderer : function(i, h, f, l, g) {
								var k = f.data.docId;
								var j = '<button title="查看" value="" class="btn-readdocument" onclick="PublicDocumentView.detail('
										+ k + ')">&nbsp;</button>';
								if (isGranted("__ALL")) {
									j += '<button title="删除" value="" class="btn-del" onclick="PublicDocumentView.remove('
											+ k + ')">&nbsp;</button>';
								}
								return j;
							}
						} ],
				defaults : {
					menuDisabled : false,
					width : 100
				}
			});
	var b = this.store();
	b.load({
		params : {
			start : 0,
			limit : 25
		}
	});
	var d = new Ext.grid.GridPanel({
		id : "PublicDocumentGrid",
		store : b,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		autoHeight : true,
		maxHeight : 600,
		cm : a,
		sm : e,
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
	d.addListener("rowdblclick", function(g, f, h) {
		g.getSelectionModel().each(function(i) {
			PublicDocumentView.detail(i.data.docId);
		});
	});
	if (isGranted("__ALL")) {
		var c = Ext.getCmp("PublicDocumentSearchForm");
		c.add(new Ext.Button({
			text : "删除",
			iconCls : "btn-del",
			handler : function() {
				var h = Ext.getCmp("PublicDocumentGrid");
				var f = h.getSelectionModel().getSelections();
				if (f.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var j = Array();
				for ( var g = 0; g < f.length; g++) {
					j.push(f[g].data.docId);
				}
				PublicDocumentView.remove(j);
			}
		}));
	}
	return d;
};
PublicDocumentView.prototype.store = function() {
	var a = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + "/document/publicListDocument.do"
		}),
		reader : new Ext.data.JsonReader({
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "docId",
				type : "int"
			}, {
				name : "forlderName",
				mapping : "docFolder.folderName"
			}, "fullname", "docName", "content", "createtime", "haveAttach",
					"attachFiles", "isShared" ]
		}),
		remoteSort : true
	});
	return a;
};
PublicDocumentView.remove = function(b) {
	var a = Ext.getCmp("PublicDocumentGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request({
				url : __ctxPath + "/document/multiDelDocument.do",
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
PublicDocumentView.detail = function(a) {
	Ext.Ajax.request({
		url : __ctxPath + "/document/rightDocument.do",
		params : {
			docId : a
		},
		method : "POST",
		success : function(e, i) {
			var j = Ext.util.JSON.decode(e.responseText);
			var h = j.rightM;
			var d = j.rightD;
			var b = j.docName;
			if (a != null) {
				var g = Ext.getCmp("centerTabPanel");
				var c = Ext.getCmp("PulicDocumentDetail");
				if (c == null) {
					c = new PublicDocumentDetail({
						docId : a,
						docName : b
					});
					g.add(c);
					g.activate(c);
				} else {
					g.remove("PulicDocumentDetail");
					c = new PublicDocumentDetail({
						docId : a,
						docName : b
					});
					g.add(c);
					g.activate(c);
				}
			}
			if (isGranted("__ALL")) {
				h = 1;
				d = 1;
			}
			if (h == 1) {
				var f = Ext.getCmp("PublicDocumentTopBar");
				f.add(new Ext.Button({
					iconCls : "btn-add",
					text : "修改公共文档 ",
					xtype : "button",
					handler : function() {
						var l = Ext.getCmp("centerTabPanel");
						var k = Ext.getCmp("NewPublicDocumentForm");
						l.remove("PulicDocumentDetail");
						if (k == null) {
							k = new NewPublicDocumentForm(a, b + "-文档信息");
							l.add(k);
							l.activate(k);
						} else {
							l.remove("NewPublicDocumentForm");
							k = new NewPublicDocumentForm(a, b + "-文档信息");
							l.add(k);
							l.activate(k);
						}
					}
				}));
				f.doLayout(true);
			}
			if (d == 1) {
				var f = Ext.getCmp("PublicDocumentTopBar");
				f.add(new Ext.Button({
					iconCls : "btn-del",
					text : "删除公共文档",
					xtype : "button",
					handler : function() {
						Ext.Msg.confirm("信息确认", "您确认要删除该文档吗？", function(k) {
							if (k == "yes") {
								Ext.Ajax.request({
									url : __ctxPath
											+ "/document/multiDelDocument.do",
									params : {
										ids : a
									},
									method : "post",
									success : function() {
										var m = Ext.getCmp("centerTabPanel");
										m.remove("PulicDocumentDetail");
										Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
										var l = Ext
												.getCmp("PublicDocumentGrid");
										l.getStore().reload({
											params : {
												start : 0,
												limit : 25
											}
										});
									}
								});
							}
						});
					}
				}));
				f.doLayout(true);
			}
		},
		failure : function(b, c) {
		},
		scope : this
	});
};