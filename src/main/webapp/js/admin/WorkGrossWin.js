var WorkGrossWin = function(c) {
	this.assetsId = c;
	var a = this.setup();
	var b = new Ext.Window(
			{
				id : "WorkGrossWin",
				title : "当前折算工作量",
				iconCls : "menu-assets",
				width : 300,
				border : false,
				region : "west",
				items : [ a ],
				buttons : [
						{
							text : "保存",
							iconCls : "btn-save",
							handler : function() {
								var d = Ext.getCmp("workCapacityForm");
								if (d.getForm().isValid()) {
									d
											.getForm()
											.submit(
													{
														method : "post",
														waitMsg : "正在提交数据...",
														success : function(e, f) {
															b.close();
															Ext.Msg
																	.confirm(
																			"操作信息",
																			"成功保存信息！需要再执行下一周期的折算吗？",
																			function(
																					g) {
																				if (g == "yes") {
																					new WorkGrossWin(
																							c);
																				}
																			});
														},
														failure : function(e, f) {
															Ext.MessageBox
																	.show( {
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
};
WorkGrossWin.prototype.setup = function() {
	var a = new Ext.form.FormPanel( {
		id : "workCapacityForm",
		layout : "form",
		url : __ctxPath + "/admin/depreciateDepreRecord.do",
		frame : false,
		items : [ {
			xtype : "hidden",
			name : "ids",
			id : "ids",
			value : this.assetsId
		}, {
			xtype : "hidden",
			id : "recordId",
			name : "depreRecord.recordId"
		}, {
			fieldLabel : "开始折算时间",
			xtype : "textfield",
			name : "lastDepreDate",
			id : "lastDepreDate",
			anchor : "100%",
			editable : false
		}, {
			fieldLabel : "折算时间",
			xtype : "textfield",
			name : "cruCalDate",
			anchor : "100%",
			id : "cruCalDate",
			editable : false
		}, {
			layout : "column",
			xtype : "container",
			style : "padding-left:0px;",
			items : [ {
				xtype : "label",
				style : "padding-left:0px;",
				text : "该段时间工作量",
				width : 105
			}, {
				xtype : "textfield",
				id : "workCapacity",
				name : "depreRecord.workCapacity",
				anchor : "100%",
				allowBlank : false
			}, {
				xtype : "label",
				id : "unit"
			} ]
		} ]
	});
	if (this.assetsId != null && this.assetsId != ""
			&& this.assetsId != "undefind") {
		this.loadData(this.assetsId);
	}
	return a;
};
WorkGrossWin.prototype.loadData = function(a) {
	Ext.Ajax.request( {
		url : __ctxPath + "/admin/workDepreRecord.do",
		params : {
			ids : a
		},
		method : "post",
		success : function(d, e) {
			var b = Ext.util.JSON.decode(d.responseText);
			var c = b.cruCalTime;
			var i = b.lastCalTime;
			var f = b.defPerWorkGross;
			if (!b.success) {
				var g = b.message;
				var h = Ext.getCmp("WorkGrossWin");
				if (h != null && h != "undefind") {
					h.close();
					Ext.ux.Toast.msg("提示", g);
				}
			} else {
				var h = Ext.getCmp("WorkGrossWin");
				if (h != null && h != "undefind") {
					h.show();
				}
				Ext.getCmp("cruCalDate").setValue(c);
				Ext.getCmp("lastDepreDate").setValue(i);
				if (f != null && f != "undefind") {
					Ext.getCmp("workCapacity").setValue(f);
				}
				Ext.getCmp("unit").setText(b.workGrossUnit);
			}
		},
		failure : function(c, d) {
			var b = Ext.util.JSON.decode(c.responseText);
			Ext.ux.Toast.msg("提示", b.message);
			return null;
		}
	});
};