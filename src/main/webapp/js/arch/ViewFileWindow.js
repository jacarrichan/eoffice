ViewFileWindow = Ext
		.extend(
				Ext.Window,
				{
					index : 0,
					constructor : function(a) {
						Ext.applyIf(this, a);
						this.index = this.startIndex;
						this.initUIComponents();
						ViewFileWindow.superclass.constructor
								.call(
										this,
										{
											id : "ViewFileWindowWin",
											region : "center",
											layout : "form",
											frame : true,
											border : true,
											modal : true,
											height : 600,
											width : 550,
											maximizable : true,
											title : "附件预览",
											buttonAlign : "center",
											buttons : [ {
												text : "",
												iconCls : "btn-top",
												scope : this,
												handler : this.up
											}, {
												text : "",
												iconCls : "btn-down",
												scope : this,
												handler : this.down
											} ],
											items : [
													{
														xtype : "iframepanel",
														id : "ViewFileWindow.iframepanel",
														frame : false,
														border : false,
														style : "margin:0 auto",
														loadMask : true,
														autoLoad : false,
														listeners : {
															afterrender : function(
																	b) {
															}
														}
													},
													{
														xtype : "panel",
														id : "ViewFileWindow.panel",
														height : 30,
														frame : false,
														border : false,
														autoLoad : false
													} ],
											listeners : {
												"afterrender" : function(g) {
													var f = Ext
															.getCmp("ViewFileWindow.iframepanel");
													var d = Ext
															.getCmp("ViewFileWindow.panel");
													var c = g.getInnerHeight();
													var i = d.getHeight();
													f.setHeight(c - i);
													if (g.viewConfig
															&& g.viewConfig.length > 0) {
														var e = g.viewConfig[g.index].fileName
																.lastIndexOf(".");
														var h = g.viewConfig[g.index].fileName.length;
														var b = g.viewConfig[g.index].fileName
																.substring(
																		(e + 1),
																		h);
														if (b == "png"
																|| b == "PNG"
																|| b == "gif"
																|| b == "GIF"
																|| b == "jpg"
																|| b == "JPG"
																|| b == "bmp"
																|| b == "BMP"
																|| b == "xml"
																|| b == "XML"
																|| b == "txt"
																|| b == "TXT"
																|| b == "html"
																|| b == "HTML") {
															f
																	.setSrc(__ctxPath
																			+ "/attachFiles/"
																			+ g.viewConfig[g.index].filePath);
														}
														d.body
																.update('<div align="center">'
																		+ this.viewConfig[this.index].fileName
																		+ "</div>"
																		+ '<div align="center">'
																		+ '<a href="'
																		+ __ctxPath
																		+ "/attachFiles/"
																		+ this.viewConfig[this.index].filePath
																		+ '" target="_blank">下载</a>'
																		+ "</div>");
													}
												}
											}
										});
					},
					initUIComponents : function() {
					},
					up : function() {
						this.index = this.index - 1;
						if (this.index >= 0) {
							var d = Ext.getCmp("ViewFileWindow.iframepanel");
							var c = this.viewConfig[this.index].fileName
									.lastIndexOf(".");
							var e = this.viewConfig[this.index].fileName.length;
							var b = this.viewConfig[this.index].fileName
									.substring((c + 1), e);
							if (b == "png" || b == "PNG" || b == "gif"
									|| b == "GIF" || b == "jpg" || b == "JPG"
									|| b == "bmp" || b == "BMP" || b == "xml"
									|| b == "XML" || b == "txt" || b == "TXT"
									|| b == "html" || b == "HTML") {
								d.setSrc(__ctxPath + "/attachFiles/"
										+ this.viewConfig[this.index].filePath);
							} else {
								d.resetFrame();
							}
							var a = Ext.getCmp("ViewFileWindow.panel");
							a.body.update('<div align="center">'
									+ this.viewConfig[this.index].fileName
									+ "</div>" + '<div align="center">'
									+ '<a href="' + __ctxPath + "/attachFiles/"
									+ this.viewConfig[this.index].filePath
									+ '" target="_blank">下载</a>' + "</div>");
						} else {
							this.index = 0;
							Ext.ux.Toast.msg("操作信息", "已是第一张！");
						}
					},
					down : function() {
						this.index = this.index + 1;
						if (this.index < this.viewConfig.length) {
							var d = Ext.getCmp("ViewFileWindow.iframepanel");
							var c = this.viewConfig[this.index].fileName
									.lastIndexOf(".");
							var e = this.viewConfig[this.index].fileName.length;
							var b = this.viewConfig[this.index].fileName
									.substring((c + 1), e);
							if (b == "png" || b == "PNG" || b == "gif"
									|| b == "GIF" || b == "jpg" || b == "JPG"
									|| b == "bmp" || b == "BMP" || b == "xml"
									|| b == "XML" || b == "txt" || b == "TXT"
									|| b == "html" || b == "HTML") {
								d.setSrc(__ctxPath + "/attachFiles/"
										+ this.viewConfig[this.index].filePath);
							} else {
								d.resetFrame();
							}
							var a = Ext.getCmp("ViewFileWindow.panel");
							a.body.update('<div align="center">'
									+ this.viewConfig[this.index].fileName
									+ "</div>" + '<div align="center">'
									+ '<a href="' + __ctxPath + "/attachFiles/"
									+ this.viewConfig[this.index].filePath
									+ '" target="_blank">下载</a>' + "</div>");
						} else {
							this.index = this.viewConfig.length - 1;
							Ext.ux.Toast.msg("操作信息", "已是最后一张！");
						}
					}
				});