/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ux.SlidingPager=Ext.extend(Object,{init:function(b){var a=b.items.indexOf(b.inputItem);Ext.each(b.items.getRange(a-2,a+2),function(d){d.hide();});var c=new Ext.Slider({width:114,minValue:1,maxValue:1,plugins:new Ext.ux.SliderTip({getText:function(d){return String.format("Page <b>{0}</b> of <b>{1}</b>",d.value,d.maxValue);}}),listeners:{changecomplete:function(e,d){b.changePage(d);}}});b.insert(a+1,c);b.on({change:function(d,e){c.setMaxValue(e.pages);c.setValue(e.activePage);}});}});