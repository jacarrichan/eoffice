/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ns("Ext.ux.form");Ext.ux.form.SpinnerField=Ext.extend(Ext.form.NumberField,{actionMode:"wrap",deferHeight:true,autoSize:Ext.emptyFn,onBlur:Ext.emptyFn,adjustSize:Ext.BoxComponent.prototype.adjustSize,constructor:function(c){var b=Ext.copyTo({},c,"incrementValue,alternateIncrementValue,accelerate,defaultValue,triggerClass,splitterClass");var d=this.spinner=new Ext.ux.Spinner(b);var a=c.plugins?(Ext.isArray(c.plugins)?c.plugins.push(d):[c.plugins,d]):d;Ext.ux.form.SpinnerField.superclass.constructor.call(this,Ext.apply(c,{plugins:a}));},getResizeEl:function(){return this.wrap;},getPositionEl:function(){return this.wrap;},alignErrorIcon:function(){if(this.wrap){this.errorIcon.alignTo(this.wrap,"tl-tr",[2,0]);}},validateBlur:function(){return true;}});Ext.reg("spinnerfield",Ext.ux.form.SpinnerField);Ext.form.SpinnerField=Ext.ux.form.SpinnerField;