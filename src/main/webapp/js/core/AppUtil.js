Ext.ns("App");
Ext.ns("AppUtil");
var jsCache = new Array();
function strToDom(a) {
	if (window.ActiveXObject) {
		var b = new ActiveXObject("Microsoft.XMLDOM");
		b.async = "false";
		b.loadXML(a);
		return b;
	} else {
		if (document.implementation && document.implementation.createDocument) {
			var c = new DOMParser();
			var b = c.parseFromString(a, "text/xml");
			return b;
		}
	}
}
function newView(viewName, params) {
	var str = "new " + viewName;
	if (params != null) {
		str += "(params);";
	} else {
		str += "();";
	}
	return eval(str);
}
function $ImportJs(viewName, callback, params) {
	var b = jsCache[viewName];
	if (b != null) {
		var view = newView(viewName, params);
		callback.call(this, view);
	} else {
		var jsArr = eval("App.importJs." + viewName);
		if (jsArr == undefined || jsArr.length == 0) {
			try {
				var view = newView(viewName, params);
				callback.call(this, view);
			} catch (e) {
			}
			return;
		}
		ScriptMgr.load({
			scripts : jsArr,
			callback : function() {
				jsCache[viewName] = 0;
				var view = newView(viewName, params);
				callback.call(this, view);
			}
		});
	}
}
function $ImportSimpleJs(a, b) {
	ScriptMgr.load({
		scripts : a,
		callback : function() {
			if (b) {
				b.call(this);
			}
		}
	});
}
App.getContentPanel = function() {
	var a = Ext.getCmp("centerTabPanel");
	return a;
};
App.createUploadDialog = function(b) {
	var a = {
		file_cat : "others",
		url : __ctxPath + "/file-upload",
		reset_on_hide : false,
		upload_autostart : false,
		modal : true
	};
	Ext.apply(a, b);
	var c = new Ext.ux.UploadDialog.Dialog(a);
	return c;
};
App.createUploadDialog2 = function(b) {
	var a = {
		file_cat : "others",
		url : __ctxPath + "/file-upload",
		reset_on_hide : false,
		upload_autostart : false,
		modal : true
	};
	Ext.apply(a, b);
	var c = new Ext.ux.UploadDialog.Dialog(a);
	return c;
};
function uniqueArray(e) {
	e = e || [];
	var b = {};
	for ( var d = 0; d < e.length; d++) {
		var c = e[d];
		if (typeof (b[c]) == "undefined") {
			b[c] = 1;
		}
	}
	e.length = 0;
	for ( var d in b) {
		e[e.length] = d;
	}
	return e;
}
function setCookie(b, d, a, f, c, e) {
	document.cookie = b + "=" + escape(d)
			+ ((a) ? "; expires=" + a.toGMTString() : "")
			+ ((f) ? "; path=" + f : "") + ((c) ? "; domain=" + c : "")
			+ ((e) ? "; secure" : "");
}
function getCookie(b) {
	var d = b + "=";
	var e = document.cookie.indexOf(d);
	if (e == -1) {
		return null;
	}
	var a = document.cookie.indexOf(";", e + d.length);
	if (a == -1) {
		a = document.cookie.length;
	}
	var c = document.cookie.substring(e + d.length, a);
	return unescape(c);
}
function deleteCookie(a, c, b) {
	if (getCookie(a)) {
		document.cookie = a + "=" + ((c) ? "; path=" + c : "")
				+ ((b) ? "; domain=" + b : "")
				+ "; expires=Thu, 01-Jan-70 00:00:01 GMT";
	}
}
String.prototype.trim = function() {
	return (this.replace(/^[\s\xA0]+/, "").replace(/[\s\xA0]+$/, ""));
};
function $request(a) {
	Ext.Ajax.request({
		url : a.url,
		params : a.params,
		method : a.method == null ? "POST" : a.method,
		success : function(b, c) {
			if (a.success != null) {
				a.success.call(this, b, c);
			}
		},
		failure : function(b, c) {
			Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
			if (a.success != null) {
				a.failure.call(this, b, c);
			}
		}
	});
}
function asynReq() {
	var a = Ext.Ajax.getConnectionObject().conn;
	a.open("GET", url, false);
	a.send(null);
}
AppUtil.addPrintExport = function(a) {
};
AppUtil.removeTab = function(a) {
	var b = App.getContentPanel();
	var c = b.getItem(a);
	if (c != null) {
		b.remove(c, true);
	}
};
AppUtil.activateTab = function(a) {
	var b = App.getContentPanel();
	b.activate(a);
};
Ext.override(Ext.layout.ContainerLayout, {
	setContainer : function(a) {
		this.container = a;
	}
});
Ext.override(Ext.BoxComponent, {
	setSize : function(b, d) {
		if (typeof b == "object") {
			d = b.height, b = b.width;
		}
		if (Ext.isDefined(b) && Ext.isDefined(this.minWidth)
				&& (b < this.minWidth)) {
			b = this.minWidth;
		}
		if (Ext.isDefined(d) && Ext.isDefined(this.minHeight)
				&& (d < this.minHeight)) {
			d = this.minHeight;
		}
		if (Ext.isDefined(b) && Ext.isDefined(this.maxWidth)
				&& (b > this.maxWidth)) {
			b = this.maxWidth;
		}
		if (Ext.isDefined(d) && Ext.isDefined(this.maxHeight)
				&& (d > this.maxHeight)) {
			d = this.maxHeight;
		}
		if (!this.boxReady) {
			this.width = b, this.height = d;
			return this;
		}
		if (this.cacheSizes !== false && this.lastSize
				&& this.lastSize.width == b && this.lastSize.height == d) {
			return this;
		}
		this.lastSize = {
			width : b,
			height : d
		};
		var c = this.adjustSize(b, d), f = c.width, a = c.height, e;
		if (f !== undefined || a !== undefined) {
			e = this.getResizeEl();
			if (!this.deferHeight && f !== undefined && a !== undefined) {
				e.setSize(f, a);
			} else {
				if (!this.deferHeight && a !== undefined) {
					e.setHeight(a);
				} else {
					if (f !== undefined) {
						e.setWidth(f);
					}
				}
			}
			this.onResize(f, a, b, d);
		}
		return this;
	},
	onResize : function(d, b, a, c) {
		this.fireEvent("resize", this, d, b, a, c);
	}
});
Ext.override(Ext.Container, {
	getCmpByName : function(b) {
		var a = function(c, f) {
			var e = c.items;
			if (e != null) {
				for ( var g = 0; g < e.getCount(); g++) {
					var d = e.get(g);
					var h = a(d, f);
					if (h != null) {
						return h;
					}
					if (f == d.name || (d.getName && f == d.getName())) {
						return d;
						break;
					}
				}
			}
			return null;
		};
		return a(this, b);
	},
	onResize : function(d, b, a, c) {
		Ext.Container.superclass.onResize.apply(this, arguments);
		if ((this.rendered && this.layout && this.layout.monitorResize)
				&& !this.suspendLayoutResize) {
			this.layout.onResize();
		}
	},
	canLayout : function() {
		var a = this.getVisibilityEl();
		return a && !a.isStyle("display", "none");
	},
	doLayout : function(f, e) {
		var j = this.rendered, h = e || this.forceLayout, d, b, a, g;
		if (!this.canLayout() || this.collapsed) {
			this.deferLayout = this.deferLayout || !f;
			if (!h) {
				return;
			}
			f = f && !this.deferLayout;
		} else {
			delete this.deferLayout;
		}
		d = (f !== true && this.items) ? this.items.items : [];
		for (b = 0, a = d.length; b < a; b++) {
			if ((g = d[b]).layout) {
				g.suspendLayoutResize = true;
			}
		}
		if (j && this.layout) {
			this.layout.layout();
		}
		for (b = 0; b < a; b++) {
			if ((g = d[b]).doLayout) {
				g.doLayout(false, h);
			}
		}
		if (j) {
			this.onLayout(f, h);
		}
		this.hasLayout = true;
		delete this.forceLayout;
		for (b = 0; b < a; b++) {
			if ((g = d[b]).layout) {
				delete g.suspendLayoutResize;
			}
		}
	}
});
Ext.override(Ext.Panel, {
	onResize : Ext.Panel.prototype.onResize
			.createSequence(Ext.Container.prototype.onResize)
});
Ext.override(Ext.Viewport, {
	fireResize : function(a, b) {
		this.onResize(a, b, a, b);
	}
});
Ext.useShims = true;