/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ux.grid.filter.ListFilter=Ext.extend(Ext.ux.grid.filter.Filter,{phpMode:false,init:function(a){this.dt=new Ext.util.DelayedTask(this.fireUpdate,this);if(this.menu){this.menu.destroy();}this.menu=new Ext.ux.menu.ListMenu(a);this.menu.on("checkchange",this.onCheckChange,this);},getValue:function(){return this.menu.getSelected();},setValue:function(a){this.menu.setSelected(a);this.fireEvent("update",this);},isActivatable:function(){return this.getValue().length>0;},getSerialArgs:function(){var a={type:"list",value:this.phpMode?this.getValue().join(","):this.getValue()};return a;},onCheckChange:function(){this.dt.delay(this.updateBuffer);},validateRecord:function(a){return this.getValue().indexOf(a.get(this.dataIndex))>-1;}});