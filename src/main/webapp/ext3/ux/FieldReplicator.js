/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ns("Ext.ux");Ext.ux.FieldReplicator={init:function(a){a.replicator=this;a.enableKeyEvents=true;a.on("change",this.onChange,this);a.onKeyDown=a.onKeyDown.createInterceptor(this.onKeyDown);},onKeyDown:function(a){if((a.getKey()==Ext.EventObject.TAB)&&(String(this.startValue)!==String(this.getValue()))){if(a.shiftKey){this.focusPrev=true;}else{if(!a.shiftKey&&!a.altKey){this.focusNext=true;}}}},onChange:function(d,i,g){d.startValue=i;var h=d.ownerCt,a,e=d.previousSibling(),b=d.nextSibling();if(Ext.isEmpty(i)){if(!Ext.isEmpty(g)){if((e&&(e.replicator===this))||(b&&(b.replicator===this))){a=d.findParentBy(function(c){return !Ext.isDefined(c.ownerCt);});h.remove(d);a.doLayout();}}}else{if(Ext.isEmpty(g)){b=new d.constructor(d.cloneConfig());h.insert(h.items.indexOf(d)+1,b);h.doLayout();a=d.findParentBy(function(c){return !Ext.isDefined(c.ownerCt);});a.doLayout();}}if(d.focusPrev){delete d.focusPrev;e.focus(false,true);}else{if(d.focusNext){delete d.focusNext;b.focus(false,true);}}}};