/*
 * Ext JS Library 3.1.1
 * Copyright(c) 2006-2010 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.ns("Ext.ux.tree");Ext.ux.tree.XmlTreeLoader=Ext.extend(Ext.tree.TreeLoader,{XML_NODE_ELEMENT:1,XML_NODE_TEXT:3,processResponse:function(b,d,g){var c=b.responseXML;var a=c.documentElement||c;try{d.beginUpdate();d.appendChild(this.parseXml(a));d.endUpdate();if(typeof g=="function"){g(this,d);}}catch(f){this.handleFailure(b);}},parseXml:function(b){var a=[];Ext.each(b.childNodes,function(f){if(f.nodeType==this.XML_NODE_ELEMENT){var c=this.createNode(f);if(f.childNodes.length>0){var e=this.parseXml(f);if(typeof e=="string"){c.attributes.innerText=e;}else{c.appendChild(e);}}a.push(c);}else{if(f.nodeType==this.XML_NODE_TEXT){var d=f.nodeValue.trim();if(d.length>0){return a=d;}}}},this);return a;},createNode:function(b){var a={tagName:b.tagName};Ext.each(b.attributes,function(c){a[c.nodeName]=c.nodeValue;});this.processAttributes(a);return Ext.ux.tree.XmlTreeLoader.superclass.createNode.call(this,a);},processAttributes:Ext.emptyFn});Ext.ux.XmlTreeLoader=Ext.ux.tree.XmlTreeLoader;