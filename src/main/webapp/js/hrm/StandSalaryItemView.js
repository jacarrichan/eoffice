Ext.ns("StandSalaryItemView");
var StandSalaryItemView = function(a) {
	return this.setup(a);
};
StandSalaryItemView.prototype.setup = function(a) {
	return this.grid(a);
};
StandSalaryItemView.prototype.grid = function(d) {
	var e = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel(
			{
				columns : [
						e,
						new Ext.grid.RowNumberer(),
						{
							header : "itemId",
							dataIndex : "itemId",
							hidden : true
						},
						{
							header : "所属标准",
							dataIndex : "standardId",
							hidden : true
						},
						{
							header : "项目名称",
							dataIndex : "itemName"
						},
						{
							header : "金额",
							dataIndex : "amount",
							editor : new Ext.form.NumberField( {
								allowBlank : false
							}),
							renderer : function(f) {
								return '<img src="' + __ctxPath
										+ '/images/flag/customer/rmb.png"/>'
										+ f;
							}
						},
						{
							header : "所属工资组成ID",
							dataIndex : "salaryItemId",
							hidden : true
						},
						{
							header : "管理",
							dataIndex : "itemId",
							width : 50,
							sortable : false,
							renderer : function(i, h, f, l, g) {
								var k = f.data.itemId;
								var j = '<button title="删除" value=" " class="btn-del" onclick="StandSalaryItemView.remove(' + k + ')">&nbsp;&nbsp;</button>';
								return j;
							}
						} ],
				defaults : {
					sortable : true,
					menuDisabled : false,
					width : 100
				}
			});
	var b = this.store(d);
	if (d != "" && d != null && d != "undefined") {
		b.load( {
			params : {
				start : 0,
				limit : 25
			}
		});
	}
	var c = new Ext.grid.EditorGridPanel( {
		id : "StandSalaryItemGrid",
		iconCls : "menu-salary",
		title : "薪酬项目金额设置",
		tbar : this.topbar(),
		store : b,
		height : 220,
		trackMouseOver : true,
		autoScroll : true,
		disableSelection : false,
		loadMask : true,
		cm : a,
		sm : e,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		}
	});
	return c;
};
StandSalaryItemView.prototype.store = function(b) {
	var a = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : __ctxPath + "/hrm/listStandSalaryItem.do?standardId=" + b
		}),
		reader : new Ext.data.JsonReader( {
			root : "result",
			totalProperty : "totalCounts",
			id : "id",
			fields : [ {
				name : "itemId",
				type : "int"
			}, "standardId", "itemName", "amount", "salaryItemId" ]
		}),
		remoteSort : true
	});
	a.setDefaultSort("itemId", "desc");
	return a;
};
StandSalaryItemView.prototype.topbar = function() {
	var a = new Ext.Toolbar( {
		id : "StandSalaryItemFootBar",
		height : 30,
		bodyStyle : "text-align:left",
		items : [ {
			iconCls : "btn-add",
			text : "添加薪酬项目",
			xtype : "button",
			handler : function() {
				var c = Ext.getCmp("StandSalaryItemGrid").getStore();
				var d = "";
				for ( var b = 0; b < c.getCount(); b++) {
					d += c.getAt(b).get("salaryItemId") + ",";
				}
				SalaryItemSelector.getView(function(m, q, k) {
					var e = m.split(",");
					var l = q.split(",");
					var o = k.split(",");
					var f = Ext.getCmp("StandSalaryItemGrid");
					var n = f.getStore();
					var j = f.getStore().recordType;
					f.stopEditing();
					for ( var h = 0; h < e.length; h++) {
						var g = new j();
						g.set("salaryItemId", e[h]);
						g.set("itemName", l[h]);
						g.set("amount", o[h]);
						g.commit();
						n.insert(n.getCount(), g);
					}
					f.getView().refresh();
					f.startEditing(0, 0);
					StandSalaryItemView.onCalcTotalMoney();
				}, d).show();
			}
		} ]
	});
	return a;
};
StandSalaryItemView.remove = function(f) {
	var c = Ext.getCmp("StandSalaryItemGrid");
	c.stopEditing();
	var d = c.getSelectionModel().getSelections();
	for ( var b = 0, e; e = d[b]; b++) {
		c.getStore().remove(e);
	}
	var a = Ext.getCmp("deleteItemIds");
	a.setValue(a.getValue() + "," + f);
	c.getView().refresh();
	StandSalaryItemView.onCalcTotalMoney();
	c.startEditing(0, 0);
	alert(a.value);
};
StandSalaryItemView.edit = function(a) {
	new StandSalaryItemForm(a);
};
StandSalaryItemView.onCalcTotalMoney = function() {
	var b = Ext.getCmp("StandSalaryItemGrid").getStore();
	var c = 0;
	for ( var a = 0; a < b.getCount(); a++) {
		c += Number(b.getAt(a).get("amount"));
	}
	Ext.getCmp("standSalaryForm.totalMoney").setValue(c);
};