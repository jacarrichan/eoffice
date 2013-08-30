/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ns("Ext.ux.layout");Ext.ux.layout.CenterLayout=Ext.extend(Ext.layout.FitLayout,{setItemSize:function(b,a){this.container.addClass("ux-layout-center");b.addClass("ux-layout-center-item");if(b&&a.height>0){if(b.width){a.width=b.width;}b.setSize(a);}}});Ext.Container.LAYOUTS["ux.center"]=Ext.ux.layout.CenterLayout;