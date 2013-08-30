/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ux.grid.filter.StringFilter=Ext.extend(Ext.ux.grid.filter.Filter,{iconCls:"ux-gridfilter-text-icon",emptyText:"Enter Filter Text...",selectOnFocus:true,width:125,init:function(a){Ext.applyIf(a,{enableKeyEvents:true,iconCls:this.iconCls,listeners:{scope:this,keyup:this.onInputKeyUp}});this.inputItem=new Ext.form.TextField(a);this.menu.add(this.inputItem);this.updateTask=new Ext.util.DelayedTask(this.fireUpdate,this);},getValue:function(){return this.inputItem.getValue();},setValue:function(a){this.inputItem.setValue(a);this.fireEvent("update",this);},isActivatable:function(){return this.inputItem.getValue().length>0;},getSerialArgs:function(){return{type:"string",value:this.getValue()};},validateRecord:function(a){var b=a.get(this.dataIndex);if(typeof b!="string"){return(this.getValue().length===0);}return b.toLowerCase().indexOf(this.getValue().toLowerCase())>-1;},onInputKeyUp:function(c,b){var a=b.getKey();if(a==b.RETURN&&c.isValid()){b.stopEvent();this.menu.hide(true);return;}this.updateTask.delay(this.updateBuffer);}});