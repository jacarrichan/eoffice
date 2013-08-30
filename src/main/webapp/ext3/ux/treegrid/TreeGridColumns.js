/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
(function(){Ext.override(Ext.list.Column,{init:function(){if(!this.type){this.type="auto";}var a=Ext.data.SortTypes;if(typeof this.sortType=="string"){this.sortType=a[this.sortType];}if(!this.sortType){switch(this.type){case"string":this.sortType=a.asUCString;break;case"date":this.sortType=a.asDate;break;default:this.sortType=a.none;}}}});Ext.tree.Column=Ext.extend(Ext.list.Column,{});Ext.tree.NumberColumn=Ext.extend(Ext.list.NumberColumn,{});Ext.tree.DateColumn=Ext.extend(Ext.list.DateColumn,{});Ext.tree.BooleanColumn=Ext.extend(Ext.list.BooleanColumn,{});Ext.reg("tgcolumn",Ext.tree.Column);Ext.reg("tgnumbercolumn",Ext.tree.NumberColumn);Ext.reg("tgdatecolumn",Ext.tree.DateColumn);Ext.reg("tgbooleancolumn",Ext.tree.BooleanColumn);})();