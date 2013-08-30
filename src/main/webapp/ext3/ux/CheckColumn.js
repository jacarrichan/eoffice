/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ns("Ext.ux.grid");Ext.ux.grid.CheckColumn=function(a){Ext.apply(this,a);if(!this.id){this.id=Ext.id();}this.renderer=this.renderer.createDelegate(this);};Ext.ux.grid.CheckColumn.prototype={init:function(a){this.grid=a;this.grid.on("render",function(){var b=this.grid.getView();b.mainBody.on("mousedown",this.onMouseDown,this);},this);},onMouseDown:function(d,c){if(Ext.fly(c).hasClass(this.createId())){d.stopEvent();var b=this.grid.getView().findRowIndex(c);var a=this.grid.store.getAt(b);a.set(this.dataIndex,!a.data[this.dataIndex]);}},renderer:function(b,c,a){c.css+=" x-grid3-check-col-td";return String.format('<div class="x-grid3-check-col{0} {1}">&#160;</div>',b?"-on":"",this.createId());},createId:function(){return"x-grid3-cc-"+this.id;}};Ext.preg("checkcolumn",Ext.ux.grid.CheckColumn);Ext.grid.CheckColumn=Ext.ux.grid.CheckColumn;