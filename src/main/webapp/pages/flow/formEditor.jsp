<%@ page pageEncoding="UTF-8" %>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.cyjt.oa.service.flow.FormDefService"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="com.cyjt.oa.model.flow.FormDef"%><html>
	<head>
		<title>在线流程表单设计器</title>
		<%
		String basePath=request.getContextPath();
		//加载表单的设计的数据		
		String formDefId=request.getParameter("formDefId");
		String projectData="";
		
		if(!"undefined".equals(formDefId) && StringUtils.isNotEmpty(formDefId)){
			FormDefService formDefService=(FormDefService)AppUtil.getBean("formDefService");
			FormDef formDef=formDefService.get(new Long(formDefId));
			if(StringUtils.isNotEmpty(formDef.getExtDef())){
				projectData=formDef.getExtDef();
			}
		}
		%>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/formeditor/css/designer.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/formeditor/css/icons.css" />
		<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>
		<script type="text/javascript" src="<%=basePath%>/js/formeditor/deploy/ext-all-3dev.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/formeditor/deploy/designer-debug.js"></script>
		<script type="text/javascript">
		
		//定义一个全局变量，方便把值传至父窗口
		var xdsProject=null;

		//项目的数据
		var projectData='<%=projectData%>';

		Ext.onReady(function() {
					Ext.BLANK_IMAGE_URL=__ctxPath+'/ext3/resources/images/default/s.gif';
					Ext.QuickTips.init();
					Ext.QuickTips.getQuickTip().el.setZIndex(70000);
					
					var d = new Ext.Toolbar({
								items : [xds.actions.newAction,xds.actions.openAction,'-',
										xds.actions.saveAction,xds.actions.saveAsAction,
										xds.actions.openAction,'-',
										xds.actions.newCmpAction,
										xds.actions.deleteCmpAction,'-',
										xds.actions.viewJson,'-',xds.actions.help,{
											id : "csep",
											xtype : "tbseparator",
											hidden : true
										}]
							});
					var c = new xds.Toolbox();
					var b = new xds.Inspector();
					xds.props = new xds.ConfigEditor();
					xds.east = new Ext.Panel({
								id : "east",
								width : 240,
								region : "east",
								minWidth : 150,
								split : true,
								margins : "0",
								cmargins : "2 1 1 5",
								baseCls : "x-plain",
								layout : "border",
								items : [b, xds.props]
							});
					var e = new xds.Canvas();
					xds.on("componentselect", function(j) {
								if (j.component) {
									var i = xds.active;
									xds.props.enable();
									xds.active = j;
									xds.props.refresh();
									if (!i || j.topNode != i.topNode) {
										e.setComponent(j.topNode);
									}
								} else {
									xds.props.disable();
									xds.active = null;
									xds.props.refresh();
								}
							});
					xds.on("componentchanged", function() {
								if (xds.active) {
									e.setComponent(xds.active.topNode);
								}
							});
					
					var g = new Ext.Toolbar({
								id : "status",
								region : "south",
								height : 24,
								items : [{
											id : "xdstatus",
											xtype : "tbtext",
											text : "&nbsp;"
										}]
							});
					/*		
					var northPanel=new Ext.Panel({
						height:26,
						border:false,
						region:'north',
						tbar:new Ext.Toolbar({
										height:30,
										items:[
										{
											text:'查看源代码',
											handler:function(){
												xds.project.viewJoson();
											}
										},
										{
											text:'查看Form代码',
											handler:function(){
												var jsonSource=xds.project.getFormJsonCode();
												alert(jsonSource);
											}
										},
										'-',
										{
											text:'Json form xml',
											iconCls:'btn-save',
											handler:function(){
												alert(xds.project.getFormItems());
											}
										},
										'-',
										{
											text:'getData()',
											iconCls:'',
											handler:function(){
												alert(Ext.util.JSON.encode(xds.project.getData()));
											}
										}
										]
									})
						
					});
					*/
							
					var a = new Ext.Viewport({
								layout : "border",
								items : [xds.east, c, e]
					});

					xds.status = Ext.getCmp("xdstatus");					
					//创建一个空白的页面
					
					//创建一个项目，若项目的数据不为空，则加载该项目，否则创建一个空白的表单项目
					if(projectData.trim()==''){
						xds.project = new xds.Project();
						xds.fireEvent("componentevent", {
										type :"new",
										parentId :null,
										component : new xds.types.Form()
									});
					}else{
						xds.project = new xds.Project(Ext.decode(projectData));
						xds.project.open();
					}
					//转给全局变量，方便外部窗口调用
					xdsProject=xds.project;
					//新建一个MyForm
					xds.fireEvent("init");
					var f;
					var h = function() {
						if (f) {
							for (var k = 0, j = f.length; k < j; k++) {
								d.remove(f[k].itemId);
							}
							f = null;
						}
					};
					xds.on("componentselect", function(m) {
								var k = d.items.get("csep");
								if (m.component) {
									xds.actions.deleteCmpAction.enable();
									h();
									var n = m.component.getActions();
									if (n) {
										k.show();
										for (var l = 0, j = n.length; l < j; l++) {
											d.add(n[l]);
										}
										d.doLayout();
									} else {
										k.hide();
									}
									f = n;
								} else {
									xds.actions.deleteCmpAction.disable();
									k.hide();
									h();
								}
							});
				});
		</script>
		
	</head>
	<body>
	</body>
</html>