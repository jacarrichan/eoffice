/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ux.SliderTip=Ext.extend(Ext.Tip,{minWidth:10,offsets:[0,-10],init:function(a){a.on("dragstart",this.onSlide,this);a.on("drag",this.onSlide,this);a.on("dragend",this.hide,this);a.on("destroy",this.destroy,this);},onSlide:function(a){this.show();this.body.update(this.getText(a));this.doAutoWidth();this.el.alignTo(a.thumb,"b-t?",this.offsets);},getText:function(a){return String(a.getValue());}});