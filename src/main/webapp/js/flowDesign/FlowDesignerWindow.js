FlowDesignerWindow = Ext
		.extend(
				Ext.Window,
				{
					constructor : function(c) {
						Ext.applyIf(this, c);
						var d = '<APPLET codebase="." id="processEditor" ARCHIVE="' + __ctxPath + '/js/flowDesign/workflow.jar" ' + 'code="com.palmelf.jbpm.designer.ProcessApplet.class" width="100%" height="100%"></APPLET>';
						var b = new Ext.Panel( {
							columnWidth : 0.79,
							height : 485,
							html : d
						});
						var a = new Ext.FormPanel(
								{
									url : __ctxPath
											+ "/flow/defSaveProDefinition.do",
									columnWidth : 0.19,
									layout : "anchor",
									border : true,
									bodyStyle : "margin:6px 0px 0px 0px;background-color:#DFE8F6",
									defaults : {
										anchor : "100%"
									},
									items : [
											{
												name : "proDefinition.defId",
												id : "defId",
												xtype : "hidden",
												value : this.defId == null ? ""
														: this.defId
											},
											{
												xtype : "hidden",
												id : "proTypeId",
												name : "proDefinition.proTypeId"
											},
											{
												text : "流程分类",
												xtype : "label"
											},
											new TreeSelector(
													"proTypeTreeSelector",
													__ctxPath
															+ "/flow/listProType.do",
													"流程分类", "proTypeId", false),
											{
												text : "流程的名称",
												xtype : "label"
											},
											{
												name : "proDefinition.name",
												xtype : "textfield",
												allowBlank : false,
												id : "name"
											},
											{
												text : "描述",
												xtype : "label"
											},
											{
												xtype : "textarea",
												height : 200,
												name : "proDefinition.description",
												id : "description"
											},
											{
												xtype : "hidden",
												height : 200,
												name : "proDefinition.drawDefXml",
												id : "drawDefXml"
											} ]
								});
						FlowDesignerWindow.superclass.constructor
								.call(
										this,
										{
											id : "flowDesignerWindow",
											title : "在线流程设计",
											iconCls : "btn-flow-design",
											defaults : {
												border : false
											},
											layout : "column",
											height : 560,
											width : 860,
											modal : true,
											maximizable : true,
											items : [ b, a ],
											buttonAlign : "center",
											buttons : [
													{
														text : "保存",
														scope : this,
														iconCls : "btn-save",
														handler : function() {
															document.getElementById("drawDefXml").value = document.getElementById("processEditor").getData();
															if (a.getForm().isValid()) {
																a.getForm().submit({
																					method : "POST",
																					waitMsg : "正在提交数据...",
																					success : function(e,h) {
																						var g = Ext.getCmp("ProDefinitionGrid");
																						if (g != null) {
																							g.getStore().reload();
																						}
																						var f = Ext.getCmp("ProDefinitionGrid0");
																						if (f != null) {
																							f.getStore().reload();
																						}
																						Ext.getCmp("flowDesignerWindow").close();
																					},
																					failure : function(e,f) {
																						Ext.MessageBox.show( {
																									title : "操作信息",
																									msg : "信息保存出错，请联系管理员！",
																									buttons : Ext.MessageBox.OK,
																									icon : "ext-mb-error"
																								});
																						Ext.getCmp("flowDesignerWindow").close();
																					}
																				});
															}
														}
													},
													{
														text : "取消",
														scope : this,
														iconCls : "btn-cancel",
														handler : function() {
															Ext
																	.getCmp(
																			"flowDesignerWindow")
																	.close();
														}
													} ]
										});
					}
				});