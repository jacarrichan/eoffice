ArchivesDraftView = Ext
		.extend(
				Ext.Panel,
				{
					defId : null,
					setDefId : function(a) {
						this.defId = a;
					},
					constructor : function(a) {
						Ext.applyIf(this, a);
						ArchivesDraftView.superclass.constructor.call(this, {
							layout : "hbox",
							layoutConfig : {
								padding : "5",
								pack : "center",
								align : "middle"
							},
							title : "公文发文拟稿",
							id : "ArchivesDraftView",
							iconCls : "menu-archive-draft",
							items : []
						});
						this.addFlowStartPanel(this);
					},
					addFlowStartPanel : function(topPanel) {
						var archivesId = topPanel.archivesId;
						Ext.Ajax
								.request( {
									scope : this,
									url : __ctxPath
											+ "/archive/getFlowArchFlowConf.do?flowType="
											+ 0,
									success : function(response, options) {
										var rec = Ext.util.JSON
												.decode(response.responseText);
										if (rec.success) {
											$request( {
												url : __ctxPath
														+ "/flow/getProcessActivity.do",
												params : {
													defId : rec.defId
												},
												success : function(resp,
														options) {
													var panel = null;
													eval("panel= new ("
															+ resp.responseText
															+ ")();");
													panel.defId = rec.defId;
													panel.archivesId = archivesId;
													topPanel.add(panel);
													topPanel.doLayout();
													if (archivesId != null
															&& archivesId != "undefined") {
														panel.formPanel
																.getForm()
																.load(
																		{
																			deferredRender : false,
																			url : __ctxPath
																					+ "/archive/getIssueArchives.do?archivesId="
																					+ archivesId,
																			waitMsg : "正在载入数据...",
																			success : function(
																					form,
																					action) {
																				var archiveDocGrid = Ext
																						.getCmp("archiveDocGrid");
																				if (archiveDocGrid) {
																					archiveDocGrid
																							.getStore().proxy.conn.url = __ctxPath
																							+ "/archive/listArchivesDoc.do?archivesId="
																							+ archivesId;
																					archiveDocGrid
																							.getStore()
																							.load();
																				}
																			},
																			failure : function(
																					form,
																					action) {
																			}
																		});
													}
												}
											});
										} else {
											Ext.ux.Toast.msg("操作信息",
													"你尚未定义发文流程~");
										}
									}
								});
					}
				});