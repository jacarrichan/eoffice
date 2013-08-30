Ext.ns("DocPrivilegeView");
var DocPrivilegeView = function() {
};
DocPrivilegeView.prototype.getView = function() {
	return new Ext.Panel( {
		id : "DocPrivilegeView",
		title : "权限列表",
		layout : "anchor",
		region : "center",
		autoScroll : true,
		items : [ new Ext.FormPanel( {
			id : "DocPrivilegeSearchForm",
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
				text : "名称"
			}, {
				xtype : "textfield",
				name : "Q_udrName_S_LK"
			}, {
				text : "属性"
			}, {
				xtype : "combo",
				anchor : "95%",
				hiddenName : "Q_flag_SN_EQ",
				id : "title",
				mode : "local",
				editable : false,
				triggerAction : "all",
				store : [ [ "1", "用户" ], [ "2", "部门" ], [ "3", "角色" ] ]
			}, {
				xtype : "button",
				text : "查询",
				iconCls : "search",
				handler : function() {
					var a = Ext.getCmp("DocPrivilegeSearchForm");
					var b = Ext.getCmp("DocPrivilegeGrid");
					if (a.getForm().isValid()) {
						$search( {
							searchPanel : a,
							gridPanel : b
						});
					}
				}
			}, {
				xtype : "button",
				iconCls : "btn-reseted",
				text : "重置",
				handler : function() {
					Ext.getCmp("DocPrivilegeSearchForm").getForm().reset();
				}
			} ]
		}), this.setup() ]
	});
};
DocPrivilegeView.prototype.setup = function() {
	return this.grid();
};
DocPrivilegeView.prototype.setFolderId = function(a) {
	this.folderId = a;
	DocPrivilegeView.folderId = a;
};
DocPrivilegeView.prototype.getFolderId = function() {
	return this.folderId;
};
DocPrivilegeView.prototype.grid = function() {
	var h = new Ext.grid.CheckboxSelectionModel();
	var f = function(n, k) {
		if (k.className && k.className.indexOf("x-grid3-cc-" + this.id) != -1) {
			n.stopEvent();
			var j = this.grid.getView().findRowIndex(k);
			var l = this.grid.getView().findCellIndex(k);
			var i = this.grid.store.getAt(j);
			var m = this.grid.colModel.getDataIndex(l);
			if (isGranted("_DocPrivilegeEdit")) {
				if (m != "rightR") {
					var n = {
						grid : this.grid,
						record : i,
						field : m,
						originalValue : i.data[this.dataIndex],
						value : !i.data[this.dataIndex],
						row : j,
						column : l,
						cancel : false
					};
					if (this.grid.fireEvent("validateedit", n) !== false
							&& !n.cancel) {
						delete n.cancel;
						i.set(this.dataIndex, !i.data[this.dataIndex]);
						this.grid.fireEvent("afteredit", n);
					}
				} else {
					Ext.ux.Toast.msg("信息提示", "可读为基本权限！");
				}
			} else {
				Ext.ux.Toast.msg("信息提示", "你没有修改的权限！");
			}
		}
	};
	var d = new Ext.grid.CheckColumn( {
		id : "read",
		header : "可读",
		dataIndex : "rightR",
		width : 55,
		onMouseDown : f
	});
	var g = new Ext.grid.CheckColumn( {
		header : "可修改",
		dataIndex : "rightU",
		width : 55,
		onMouseDown : f
	});
	var e = new Ext.grid.CheckColumn( {
		header : "可删除",
		dataIndex : "rightD",
		width : 55,
		onMouseDown : f
	});
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						h,
						new Ext.grid.RowNumberer(),
						{
							header : "privilegeId",
							dataIndex : "privilegeId",
							hidden : true
						},
						{
							header : "名称",
							dataIndex : "udrName"
						},
						{
							header : "文件夹",
							dataIndex : "folderName"
						},
						{
							header : "属性",
							dataIndex : "flag",
							renderer : function(k, j, i) {
								if (k == 1) {
									return '<img title="员工" src="' + __ctxPath + '/images/flag/user.jpg"/>';
								}
								if (k == 2) {
									return '<img title="部门" src="' + __ctxPath + '/images/flag/department.jpg"/>';
								}
								if (k == 3) {
									return '<img title="角色" src="' + __ctxPath + '/images/flag/role.jpg"/>';
								}
							}
						},
						d,
						g,
						e,
						{
							header : "管理",
							dataIndex : "privilegeId",
							width : 50,
							renderer : function(l, k, i, o, j) {
								var n = i.data.privilegeId;
								var m = "";
								if (isGranted("_DocPrivilegeDel")) {
									m = '<button title="删除" value=" " class="btn-del" onclick="DocPrivilegeView.remove(' + n + ')">&nbsp;</button>';
								}
								return m;
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
	var c = new Ext.grid.EditorGridPanel( {
		id : "DocPrivilegeGrid",
		tbar : this.topbar(this),
		trackMouseOver : true,
		store : b,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		autoHeight : true,
		cm : a,
		sm : h,
		plugins : [ d, g, e ],
		clicksToEdit : 1,
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
	c.addListener("afteredit", function(i) {
		Ext.Ajax.request( {
			url : __ctxPath + "/document/changeDocPrivilege.do",
			params : {
				field : i.field,
				fieldValue : i.value,
				privilegeId : i.record.data.privilegeId
			},
			success : function() {
			},
			failure : function() {
				Ext.Msg.show( {
					title : "错误提示",
					msg : "修改数据发生错误,操作将被回滚!",
					fn : function() {
						i.record.set(i.field, i.originalValue);
					},
					buttons : Ext.Msg.OK,
					icon : Ext.Msg.ERROR
				});
			}
		});
	});
	return c;
};
DocPrivilegeView.prototype.store = function() {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/document/listDocPrivilege.do"
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "privilegeId",
				type : "int"
			}, {
				name : "folderName",
				mapping : "folderName"
			}, "rightR", "rightU", "rightD", "udrId", "udrName", "flag" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("privilegeId", "desc");
	return a;
};
DocPrivilegeView.prototype.topbar = function(b) {
	var a = new Ext.Toolbar( {
		id : "DocPrivilegeFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : []
	});
	if (isGranted("_DocPrivilegeAdd")) {
		a.add(new Ext.Button( {
			iconCls : "btn-add",
			text : "添加文件夹权限",
			handler : function() {
				var c = b.getFolderId();
				if (c != null && c > 0) {
					new DocFolderSharedForm(null, c);
				} else {
					Ext.ux.Toast.msg("提示", "请选择文件夹!");
				}
			}
		}));
	}
	if (isGranted("_DocPrivilegeDel")) {
		a.add(new Ext.Button( {
			iconCls : "btn-del",
			text : "删除文件夹权限",
			handler : function() {
				var e = Ext.getCmp("DocPrivilegeGrid");
				var c = e.getSelectionModel().getSelections();
				if (c.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var f = Array();
				for ( var d = 0; d < c.length; d++) {
					f.push(c[d].data.privilegeId);
				}
				DocPrivilegeView.remove(f);
			}
		}));
	}
	return a;
};
DocPrivilegeView.remove = function(b) {
	var a = Ext.getCmp("DocPrivilegeGrid");
	Ext.Msg.confirm("信息确认", "您确认要删除该记录吗？", function(c) {
		if (c == "yes") {
			Ext.Ajax.request( {
				url : __ctxPath + "/document/multiDelDocPrivilege.do",
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
DocPrivilegeView.edit = function(a) {
	new DocPrivilegeForm(a);
};