/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ns("Ext.ux.form");Ext.ux.form.SearchField=Ext.extend(Ext.form.TwinTriggerField,{initComponent:function(){Ext.ux.form.SearchField.superclass.initComponent.call(this);this.on("specialkey",function(a,b){if(b.getKey()==b.ENTER){this.onTrigger2Click();}},this);},validationEvent:false,validateOnBlur:false,trigger1Class:"x-form-clear-trigger",trigger2Class:"x-form-search-trigger",hideTrigger1:true,width:180,hasSearch:false,paramName:"query",onTrigger1Click:function(){if(this.hasSearch){this.el.dom.value="";var a={start:0};this.store.baseParams=this.store.baseParams||{};this.store.baseParams[this.paramName]="";this.store.reload({params:a});this.triggers[0].hide();this.hasSearch=false;}},onTrigger2Click:function(){var a=this.getRawValue();if(a.length<1){this.onTrigger1Click();return;}var b={start:0};this.store.baseParams=this.store.baseParams||{};this.store.baseParams[this.paramName]=a;this.store.reload({params:b});this.hasSearch=true;this.triggers[0].show();}});