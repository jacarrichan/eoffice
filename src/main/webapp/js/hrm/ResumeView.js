Ext.ns("ResumeView");
var ResumeView = function() {
	return new Ext.Panel( {
		id : "ResumeView",
		title : "简历列表",
		iconCls : "menu-resume",
		autoScroll : true,
		items : [ new Ext.FormPanel( {
			id : "ResumeSearchForm",
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
				text : "姓名"
			}, {
				xtype : "textfield",
				name : "Q_fullname_S_LK"
			}, {
				text : "应聘职位"
			}, {
				xtype : "textfield",
				name : "Q_position_S_LK"
			}, {
				text : "状态"
			}, {
				xtype : "textfield",
				name : "Q_status_S_EQ",
				xtype : "combo",
				editable : false,
				mode : "local",
				triggerAction : "all",
				store : [ "通过", "未通过", "准备安排面试", "通过面试" ]
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var a = Ext.getCmp("ResumeSearchForm");
					var b = Ext.getCmp("ResumeGrid");
					if (a.getForm().isValid()) {
						$search( {
							searchPanel : a,
							gridPanel : b
						});
					}
				}
			}, {
				xtype : "button",
				text : "重置",
				iconCls : "btn-reseted",
				handler : function() {
					var a = Ext.getCmp("ResumeSearchForm");
					a.getForm().reset();
				}
			} ]
		}), this.setup() ]
	});
};
ResumeView.prototype.setup = function() {
	return this.grid();
};
ResumeView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "resumeId",
							dataIndex : "resumeId",
							hidden : true
						},
						{
							header : "姓名",
							dataIndex : "fullname"
						},
						{
							header : "年龄",
							dataIndex : "age"
						},
						{
							header : "性别",
							dataIndex : "sex"
						},
						{
							header : "应聘职位",
							dataIndex : "position"
						},
						{
							header : "电话",
							dataIndex : "phone"
						},
						{
							header : "手机",
							dataIndex : "mobile"
						},
						{
							header : "邮箱",
							dataIndex : "email"
						},
						{
							header : "专业",
							dataIndex : "eduMajor"
						},
						{
							header : "状态",
							dataIndex : "status"
						},
						{
							header : "登记人",
							dataIndex : "registor"
						},
						{
							header : "登记时间",
							dataIndex : "regTime"
						},
						{
							header : "管理",
							dataIndex : "resumeId",
							width : 80,
							sortable : false,
							renderer : function(h, g, e, k, f) {
								var j = e.data.resumeId;
								var i = "";
								if (isGranted("_ResumeDel")) {
									i += '<button title="删除" value=" " class="btn-del" onclick="ResumeView.remove(' + j + ')">&nbsp;&nbsp;</button>';
								}
								if (isGranted("_ResumeEdit")) {
									i += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="ResumeView.edit(' + j + ')">&nbsp;&nbsp;</button>';
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
		id : "ResumeGrid",
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
			if (isGranted("_ResumeEdit")) {
				ResumeView.edit(e.data.resumeId);
			}
		});
	});
	return c;
};
ResumeView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/hrm/listResume.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "resumeId",
				type : "int"
			}, "fullname", "age", "birthday", "address", "zip", "sex",
					"position", "phone", "mobile", "email", "hobby",
					"religion", "party", "nationality", "race", "birthPlace",
					"eduCollege", "eduDegree", "eduMajor", "startWorkDate",
					"idNo", "photo", "status", "memo", "registor", "regTime",
					"workCase", "trainCase", "projectCase" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("resumeId", "desc");
	return a;
};
ResumeView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "ResumeFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : []
	});
	if (isGranted("_ResumeAdd")) {
		a.add(new Ext.Button( {
			iconCls : "btn-add",
			text : "添加简历",
			handler : function() {
				var b = Ext.getCmp("centerTabPanel");
				var c = Ext.getCmp("ResumeFormPanel");
				if (c != null) {
					b.remove("ResumeFormPanel");
				}
				c = new ResumeForm();
				b.add(c);
				b.activate(c);
			}
		}));
	}
	if (isGranted("_ResumeDel")) {
		a.add(new Ext.Button( {
			iconCls : "btn-del",
			text : "删除简历",
			handler : function() {
				var d = Ext.getCmp("ResumeGrid");
				var b = d.getSelectionModel().getSelections();
				if (b.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var e = Array();
				for ( var c = 0; c < b.length; c++) {
					e.push(b[c].data.resumeId);
				}
				ResumeView.remove(e);
			}
		}));
	}
	return a;
};
ResumeView.remove = function(b) {
	var a = Ext.getCmp("ResumeGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/hrm/multiDelResume.do",
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
ResumeView.edit = function(c) {
	var a = Ext.getCmp("centerTabPanel");
	var b = Ext.getCmp("ResumeFormPanel");
	if (b != null) {
		a.remove("ResumeFormPanel");
	}
	b = new ResumeForm(c);
	a.add(b);
	a.activate(b);
};