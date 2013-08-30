Ext.ns("DepartmentView");
var DepartmentView = function() {
	return this.setup();
};
DepartmentView.prototype.setup = function() {
	var e;
	var k = this.initData();
	var d = new Ext.grid.CheckboxSelectionModel();
	var l = new Ext.grid.ColumnModel(
			{
				columns : [
						d,
						new Ext.grid.RowNumberer(),
						{
							header : "userId",
							dataIndex : "userId",
							hidden : true
						},
						{
							header : "状态",
							dataIndex : "status",
							width : 30,
							renderer : function(n) {
								var o = "";
								if (n == "1") {
									o += '<img title="激活" src="'
											+ __ctxPath
											+ '/images/flag/customer/effective.png"/>';
								} else {
									o += '<img title="禁用" src="'
											+ __ctxPath
											+ '/images/flag/customer/invalid.png"/>';
								}
								return o;
							}
						},
						{
							header : "账号",
							dataIndex : "username",
							width : 60
						},
						{
							header : "用户名",
							dataIndex : "fullname",
							width : 60
						},
						{
							header : "邮箱",
							dataIndex : "email",
							width : 120
						},
						{
							header : "所属部门",
							dataIndex : "department",
							renderer : function(n) {
								if (n == null) {
									return "";
								} else {
									return n.depName;
								}
							},
							width : 60
						},
						{
							header : "所在职位",
							dataIndex : "position",
							width : 60
						},
						{
							header : "入职时间",
							dataIndex : "accessionTime",
							width : 100
						},
						{
							header : "管理",
							dataIndex : "userId",
							sortable : false,
							width : 100,
							renderer : function(r, q, o, u, p) {
								var t = o.data.userId;
								var n = o.data.username;
								var s = "";
								if (isGranted("_AppUserDel") && t != 1) {
									s += '<button title="删除" value=" " class="btn-del" onclick="DepartmentView.remove('
											+ t + ')"></button>';
								}
								if (isGranted("_AppUserEdit") && t != 1) {
									s += '&nbsp;<button title="编辑" value=" " class="btn-edit" onclick="AppUserView.edit('
											+ t + ",'" + n + "')\"></button>";
								}
								if (isGranted("_UserSubAdd") && t != 1) {
									s += '&nbsp;<button title="下属管理" value=" " class="menu-subuser" onclick="AppUserView.add('
											+ t + ",'" + n + "')\"></button>";
								}
								return s;
							}
						} ],
				defaults : {
					sortable : true,
					menuDisabled : true,
					width : 100
				},
				listeners : {
					hiddenchange : function(n, o, p) {
						saveConfig(o, p);
					}
				}
			});
	var c = new Ext.grid.GridPanel({
		region : "center",
		id : "UserView",
		height : 800,
		title : "账号基本信息",
		store : k,
		shim : true,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		cm : l,
		sm : d,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		bbar : new Ext.PagingToolbar({
			pageSize : 25,
			store : k,
			displayInfo : true,
			displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
			emptyMsg : "当前没有记录"
		})
	});
	c.addListener("rowdblclick", g);
	function g(o, n, p) {
		o.getSelectionModel().each(function(q) {
			new UserSubWindow({
				userId : q.data.userId
			}).show();
		});
	}
	k.load({
		params : {
			start : 0,
			limit : 25
		}
	});
	var f = new Ext.tree.TreePanel({
		region : "west",
		id : "treePanel",
		title : "部门信息显示",
		collapsible : true,
		autoScroll : true,
		split : true,
		height : 800,
		width : 180,
		tbar : new Ext.Toolbar({
			items : [ {
				xtype : "button",
				iconCls : "btn-refresh",
				text : "刷新",
				handler : function() {
					f.root.reload();
				}
			}, {
				xtype : "button",
				text : "展开",
				iconCls : "btn-expand",
				handler : function() {
					f.expandAll();
				}
			}, {
				xtype : "button",
				text : "收起",
				iconCls : "btn-collapse",
				handler : function() {
					f.collapseAll();
				}
			} ]
		}),
		loader : new Ext.tree.TreeLoader({
			url : __ctxPath + "/system/listDepartment.do"
		}),
		root : new Ext.tree.AsyncTreeNode({
			expanded : true
		}),
		rootVisible : false,
		listeners : {
			"click" : DepartmentView.clickNode
		}
	});
	if (isGranted("_DepartmentAdd") || isGranted("_DepartmentEdit")
			|| isGranted("_DepartmentDel")) {
		f.on("contextmenu", b, f);
	}
	var h = new Ext.menu.Menu({
		id : "DepartmentTreeMenu",
		items : []
	});
	if (isGranted("_DepartmentAdd")) {
		h.add({
			text : "新建部门",
			iconCls : "btn-add",
			scope : this,
			handler : m
		});
	}
	if (isGranted("_DepartmentEdit")) {
		h.add({
			text : "修改部门信息",
			iconCls : "btn-edit",
			scope : this,
			handler : i
		});
	}
	if (isGranted("_DepartmentDel")) {
		h.add({
			text : "删除部门",
			iconCls : "btn-delete",
			scope : this,
			handler : j
		});
	}
	function b(n, o) {
		e = new Ext.tree.TreeNode({
			id : n.id,
			text : n.text
		});
		h.showAt(o.getXY());
	}
	function m() {
		var n = e.id;
		var o = Ext.getCmp("departmentForm");
		if (o == null) {
			if (n > 0) {
				new DepartmentForm({
					nodeId : n
				}).show();
			} else {
				new DepartmentForm({
					nodeId : 0
				}).show();
			}
		}
	}
	function j() {
		var n = e.id;
		var o = Ext.getCmp("treePanel");
		if (n > 0) {
			Ext.Msg.confirm("删除操作", "你确定删除部门?", function(p) {
				if (p == "yes") {
					Ext.Ajax.request({
						url : __ctxPath + "/system/removeDepartment.do?depId="
								+ n,
						success : function(q, s) {
							var r = Ext.util.JSON.decode(q.responseText);
							if (r.success == false) {
								Ext.ux.Toast.msg("操作信息", r.message);
							} else {
								Ext.ux.Toast.msg("操作信息", "删除成功!");
							}
							o.root.reload();
						},
						failure : function(q, r) {
						}
					});
				}
			});
		} else {
			Ext.ux.Toast.msg("警告", "总公司不能被删除");
		}
	}
	function i() {
		var n = e.id;
		if (n > 0) {
			var o = Ext.getCmp("departmentForm");
			if (o == null) {
				new DepartmentForm().show();
				o = Ext.getCmp("departmentForm");
			}
			o.form.load({
				url : __ctxPath + "/system/detailDepartment.do",
				params : {
					depId : n
				},
				method : "post",
				deferredRender : true,
				layoutOnTabChange : true,
				success : function() {
				},
				failure : function() {
					Ext.ux.Toast.msg("编辑", "载入失败");
				}
			});
		} else {
			Ext.ux.Toast.msg("警告", "总公司不能修改！");
		}
	}
	var a = new Ext.Panel({
		id : "DepartmentView",
		title : "部门信息",
		closable : true,
		iconCls : "menu-department",
		layout : "border",
		items : [ f, c ]
	});
	return a;
};
DepartmentView.prototype.initData = function() {
	var a = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : __ctxPath + "/system/selectAppUser.do"
		}),
		reader : new Ext.data.JsonReader({
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "userId",
				type : "int"
			}, "username", "fullname", "email", "department", "title",
					"position", {
						name : "accessionTime"
					}, {
						name : "status",
						type : "int"
					} ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("id", "desc");
	return a;
};
DepartmentView.remove = function(a) {
	Ext.Msg.confirm("删除操作", "你确定要删除该用户吗?", function(b) {
		if (b == "yes") {
			Ext.Ajax.request({
				url : __ctxPath + "/system/multiDelAppUser.do",
				method : "post",
				params : {
					ids : _ids
				},
				success : function(d) {
					var c = Ext.util.JSON.decode(d.responseText);
					if (c.msg == "") {
						Ext.ux.Toast.msg("操作信息", "用户删除成功");
					} else {
						Ext.ux.Toast.msg("操作信息", c.msg);
					}
					UserView.grid.getStore().reload();
				},
				failure : function() {
					Ext.ux.Toast.msg("操作信息", "用户删除失败");
				}
			});
		}
	});
};
DepartmentView.clickNode = function(b) {
	if (b != null) {
		var c = Ext.getCmp("UserView");
		var a = c.getStore();
		a.url = __ctxPath + "/system/selectAppUser.do";
		a.baseParams = {
			depId : b.id
		};
		a.params = {
			start : 0,
			limit : 25
		};
		a.reload({
			params : {
				start : 0,
				limit : 25
			}
		});
	}
};