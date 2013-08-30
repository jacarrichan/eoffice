Ext.ns("CheckStandSalaryItemView");
var CheckStandSalaryItemView = function(a) {
	return this.setup(a);
};
CheckStandSalaryItemView.prototype.setup = function(a) {
	return this.grid(a);
};
CheckStandSalaryItemView.prototype.grid = function(d) {
	var a = new Ext.grid.ColumnModel( {
		columns : [
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
					renderer : function(e) {
						return '<img src="' + __ctxPath
								+ '/images/flag/customer/rmb.png"/>' + e;
					}
				}, {
					header : "所属工资组成ID",
					dataIndex : "salaryItemId",
					hidden : true
				} ],
		defaults : {
			sortable : true,
			menuDisabled : false,
			width : 100
		}
	});
	var b = this.store(d);
	b.load( {
		params : {
			start : 0,
			limit : 25
		}
	});
	var c = new Ext.grid.GridPanel( {
		id : "CheckStandSalaryItemGrid",
		store : b,
		height : 200,
		trackMouseOver : true,
		autoScroll : true,
		disableSelection : false,
		loadMask : true,
		cm : a,
		viewConfig : {
			forceFit : true,
			enableRowBody : false,
			showPreview : false
		}
	});
	return c;
};
CheckStandSalaryItemView.prototype.store = function(b) {
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