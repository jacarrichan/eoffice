/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.namespace("Ext.ux.grid.filter");Ext.ux.grid.filter.Filter=Ext.extend(Ext.util.Observable,{active:false,dataIndex:null,menu:null,updateBuffer:500,constructor:function(a){Ext.apply(this,a);this.addEvents("activate","deactivate","serialize","update");Ext.ux.grid.filter.Filter.superclass.constructor.call(this);this.menu=new Ext.menu.Menu();this.init(a);if(a&&a.value){this.setValue(a.value);this.setActive(a.active!==false,true);delete a.value;}},destroy:function(){if(this.menu){this.menu.destroy();}this.purgeListeners();},init:Ext.emptyFn,getValue:Ext.emptyFn,setValue:Ext.emptyFn,isActivatable:function(){return true;},getSerialArgs:Ext.emptyFn,validateRecord:function(){return true;},serialize:function(){var a=this.getSerialArgs();this.fireEvent("serialize",a,this);return a;},fireUpdate:function(){if(this.active){this.fireEvent("update",this);}this.setActive(this.isActivatable());},setActive:function(b,a){if(this.active!=b){this.active=b;if(a!==true){this.fireEvent(b?"activate":"deactivate",this);}}}});