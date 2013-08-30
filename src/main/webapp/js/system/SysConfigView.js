Ext.ns("SysConfigView");
var SysConfigView = function() {
	return new Ext.Panel( {
		id : "SysConfigView",
		iconCls : "menu-system-setting",
		title : "系统配置",
		tbar : this.tbar(),
		autoScroll : true,
		items : [ this.setup() ]
	});
};
SysConfigView.prototype.tbar = function() {
	var a = new Ext.Toolbar();
	a.add(new Ext.Button( {
		text : "保存",
		iconCls : "btn-save",
		handler : function() {
			var b = Ext.getCmp("mailConfigForm");
			if (b.getForm().isValid()) {
				b.getForm().submit( {
					method : "post",
					waitMsg : "正在提交数据...",
					success : function(c, e) {
						Ext.ux.Toast.msg("操作信息", "成功信息保存！");
						var d = Ext.getCmp("centerTabPanel");
						d.remove("SysConfigView");
					},
					failure : function(c, d) {
						Ext.MessageBox.show( {
							title : "操作信息",
							msg : "信息保存出错，请联系管理员！",
							buttons : Ext.MessageBox.OK,
							icon : "ext-mb-error"
						});
					}
				});
			}
		}
	}));
	a.add(new Ext.Button( {
		text : "重置",
		iconCls : "btn-reseted",
		handler : function() {
			var b = Ext.getCmp("mailConfigForm");
			Ext.Ajax.request( {
				url : __ctxPath + "/system/loadSysConfig.do",
				success : function(c, e) {
					b.removeAll();
					var d = Ext.util.JSON.decode(c.responseText);
					b.add(d.data);
					b.doLayout();
				}
			});
		}
	}));
	return a;
};
SysConfigView.prototype.setup = function() {
	var a = new Ext.FormPanel( {
		id : "mailConfigForm",
		url : __ctxPath + "/system/saveSysConfig.do",
		defaultType : "textfield",
		bodyStyle : "padding-left:10%;",
		frame : false,
		border : false,
		layout : "form",
		items : []
	});
	Ext.Ajax.request( {
		url : __ctxPath + "/system/loadSysConfig.do",
		success : function(b, c) {
			var u = Ext.util.JSON.decode(b.responseText);
			var f = u.data.mailConfig;
			var g = [];
			for ( var n = 0; n < f.length; n++) {
				g.push( {
					xtype : "container",
					style : "padding-bottom:3px;",
					layout : "column",
					items : [ {
						xtype : "label",
						style : "font-weight:bold;",
						text : f[n].configName,
						width : 100
					}, {
						xtype : "textfield",
						width : 300,
						allowBlank : false,
						id : f[n].configKey,
						name : f[n].configKey,
						value : f[n].dataValue
					}, {
						xtype : "label",
						width : 200,
						text : f[n].configDesc
					} ]
				});
			}
			var k = {
				xtype : "fieldset",
				title : "系统邮件配置",
				width : 650,
				style : "padding-bottom:3px;",
				layout : "form",
				items : g
			};
			var j = u.data.adminConfig;
			var s = [];
			for ( var n = 0; n < j.length; n++) {
				s.push( {
					xtype : "container",
					style : "padding-bottom:3px;",
					layout : "column",
					items : [ {
						xtype : "label",
						style : "font-weight:bold;",
						text : j[n].configName,
						width : 100
					}, {
						xtype : "textfield",
						width : 300,
						allowBlank : false,
						id : j[n].configKey,
						name : j[n].configKey,
						value : j[n].dataValue
					}, {
						xtype : "label",
						width : 200,
						text : j[n].configDesc
					} ]
				});
			}
			var o = {
				xtype : "fieldset",
				title : "行政管理配置",
				width : 650,
				style : "padding-bottom:3px;",
				layout : "form",
				items : s
			};
			var t = u.data.codeConfig;
			var q = [];
			for ( var n = 0; n < t.length; n++) {
				q.push( {
					xtype : "container",
					style : "padding-bottom:3px;",
					layout : "column",
					items : [ {
						xtype : "label",
						style : "font-weight:bold;",
						text : t[n].configName,
						width : 100
					}, {
						xtype : "combo",
						mode : "local",
						editable : false,
						triggerAction : "all",
						store : [ [ "1", "开启验证码" ], [ "2", "屏蔽验证码" ] ],
						width : 300,
						allowBlank : false,
						hiddenName : t[n].configKey,
						value : t[n].dataValue
					}, {
						xtype : "label",
						width : 200,
						text : t[n].configDesc
					} ]
				});
			}
			var l = {
				xtype : "fieldset",
				title : "验证码配置",
				width : 650,
				style : "padding-bottom:3px;",
				layout : "form",
				items : q
			};
			var m = u.data.suggestConfig;
			var d = [ {
				xtype : "container",
				style : "padding-bottom:3px;",
				layout : "column",
				items : [ {
					xtype : "label",
					style : "font-weight:bold;",
					text : m[1].configName,
					width : 100
				}, {
					xtype : "textfield",
					editable : false,
					width : 300,
					allowBlank : false,
					id : m[1].configKey,
					name : m[1].configKey,
					value : m[1].dataValue
				}, {
					xtype : "button",
					text : "选择",
					iconCls : "btn-add",
					handler : function() {
						UserSelector.getView(function(x, w) {
							var i = Ext.getCmp(m[1].configKey);
							var v = Ext.getCmp(m[0].configKey);
							i.setValue(w);
							v.setValue(x);
						}, true).show();
					}
				}, {
					xtype : "label",
					width : 100,
					text : m[1].configDesc
				}, {
					xtype : "hidden",
					editable : false,
					width : 300,
					allowBlank : false,
					id : m[0].configKey,
					name : m[0].configKey,
					value : m[0].dataValue
				} ]
			} ];
			var e = {
				xtype : "fieldset",
				title : "意见箱配置",
				width : 650,
				style : "padding-bottom:3px;",
				layout : "form",
				items : d
			};
			var p = u.data.commentConfig;
			var r = [ {
				xtype : "container",
				style : "padding-bottom:3px;",
				layout : "column",
				items : [ {
					xtype : "label",
					style : "font-weight:bold;",
					text : p[0].configName,
					width : 100
				}, {
					xtype : "combo",
					mode : "local",
					editable : false,
					triggerAction : "all",
					store : [ [ "1", "开启评论审核" ], [ "2", "屏蔽评论审核" ] ],
					width : 300,
					allowBlank : false,
					hiddenName : p[0].configKey,
					value : p[0].dataValue
				}, {
					xtype : "label",
					width : 200,
					text : p[0].configDesc
				} ]
			} ];
			var h = {
				xtype : "fieldset",
				title : "新闻评论配置",
				width : 650,
				style : "padding-bottom:3px;",
				layout : "form",
				items : r
			};
			a.add(k);
			a.add(o);
			a.add(l);
			a.add(e);
			a.add(h);
			a.doLayout();
		}
	});
	return a;
};