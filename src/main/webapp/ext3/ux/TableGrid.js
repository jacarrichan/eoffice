/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ns("Ext.ux.grid");Ext.ux.grid.TableGrid=function(p,f){f=f||{};Ext.apply(this,f);var c=f.fields||[],a=f.columns||[];p=Ext.get(p);var k=p.insertSibling();var l=[],m=[];var e=p.query("thead th");for(var g=0,j;j=e[g];g++){var o=j.innerHTML;var b="tcol-"+g;l.push(Ext.applyIf(c[g]||{},{name:b,mapping:"td:nth("+(g+1)+")/@innerHTML"}));m.push(Ext.applyIf(a[g]||{},{"header":o,"dataIndex":b,"width":j.offsetWidth,"tooltip":j.title,"sortable":true}));}var d=new Ext.data.Store({reader:new Ext.data.XmlReader({record:"tbody tr"},l)});d.loadData(p.dom);var n=new Ext.grid.ColumnModel(m);if(f.width||f.height){k.setSize(f.width||"auto",f.height||"auto");}else{k.setWidth(p.getWidth());}if(f.remove!==false){p.remove();}Ext.applyIf(this,{"ds":d,"cm":n,"sm":new Ext.grid.RowSelectionModel(),autoHeight:true,autoWidth:false});Ext.ux.grid.TableGrid.superclass.constructor.call(this,k,{});};Ext.extend(Ext.ux.grid.TableGrid,Ext.grid.GridPanel);Ext.grid.TableGrid=Ext.ux.grid.TableGrid;