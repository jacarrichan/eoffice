var DepreWin = function(c) {
	var a = new Ext.form.FormPanel( {
		id : "workCapacityForm",
		layout : "form",
		url : __ctxPath + "/admin/depreciateDepreRecord.do",
		style : "padding-top:7px;",
		frame : true,
		items : [ {
			xtype : "hidden",
			name : "ids",
			value : c
		}, {
			xtype : "hidden",
			name : "depreRecord.recordId"
		}, {
			fieldLabel : "当前折旧工作量",
			xtype : "textfield",
			name : "depreRecord.workCapacity",
			anchor : "100%",
			allowBlank : false
		} ]
	});
	var b = new Ext.Window( {
		id : "depreWin",
		title : "按工作量折算",
		region : "west",
		width : 500,
		height : 350,
		layout : "form",
		plain : true,
		bodyStyle : "padding:5px;",
		buttonAlign : "center",
		items : [ this.setup(c), a ],
		buttons : [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var d = Ext.getCmp("workCapacityForm");
				if (d.getForm().isValid()) {
					d.getForm().submit( {
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(e, f) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							b.close();
						},
						failure : function(e, f) {
							Ext.MessageBox.show( {
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : "ext-mb-error"
							});
							b.close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				b.close();
			}
		} ]
	});
	b.show();
};
DepreWin.prototype.setup = function(a) {
	return this.grid(a);
};
DepreWin.prototype.grid = function(d) {
	var a = new Ext.grid.ColumnModel( {
		columns : [ new Ext.grid.RowNumberer(), {
			header : "recordId",
			dataIndex : "recordId",
			hidden : true
		}, {
			header : "资产名称",
			dataIndex : "assets"
		}, {
			header : "工作量",
			id : "workCapacity",
			dataIndex : "workCapacity"
		}, {
			header : "折旧后值",
			dataIndex : "depreAmount"
		}, {
			header : "计算时间",
			dataIndex : "calTime"
		} ],
		defaults : {
			sortable : true,
			menuDisabled : false,
			width : 100
		}
	});
	var b = this.initData(d);
	b.load( {
		params : {
			start : 0,
			limit : 6
		}
	});
	var c = new Ext.grid.GridPanel( {
		title : "折旧记录",
		store : b,
		trackMouseOver : true,
		disableSelection : false,
		loadMask : true,
		height : 223,
		cm : a,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		},
		bbar : new Ext.PagingToolbar( {
			pageSize : 6,
			store : b,
			displayInfo : true,
			displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
			emptyMsg : "当前没有记录"
		})
	});
	return c;
};
DepreWin.prototype.initData = function(b) {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/admin/listDepreRecord.do"
		}),
		baseParams : {
			"Q_fixedAssets.assetsId_L_EQ" : b
		},
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "recordId",
				type : "int"
			}, {
				name : "assets",
				mapping : "fixedAssets.assetsName"
			}, "workCapacity", "depreAmount", "calTime" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("recordId", "desc");
	return a;
};