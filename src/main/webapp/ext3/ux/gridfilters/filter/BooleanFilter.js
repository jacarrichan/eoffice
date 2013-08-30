/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ux.grid.filter.BooleanFilter=Ext.extend(Ext.ux.grid.filter.Filter,{defaultValue:false,yesText:"Yes",noText:"No",init:function(a){var c=Ext.id();this.options=[new Ext.menu.CheckItem({text:this.yesText,group:c,checked:this.defaultValue===true}),new Ext.menu.CheckItem({text:this.noText,group:c,checked:this.defaultValue===false})];this.menu.add(this.options[0],this.options[1]);for(var b=0;b<this.options.length;b++){this.options[b].on("click",this.fireUpdate,this);this.options[b].on("checkchange",this.fireUpdate,this);}},getValue:function(){return this.options[0].checked;},setValue:function(a){this.options[a?0:1].setChecked(true);},getSerialArgs:function(){var a={type:"boolean",value:this.getValue()};return a;},validateRecord:function(a){return a.get(this.dataIndex)==this.getValue();}});