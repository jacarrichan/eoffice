NtkOfficePanel = function(conf) {
	var isFileOpen = false;
	conf.doctype = conf.doctype ? conf.doctype : "doc";
	var fileId = conf.fileId ? conf.fileId : "";
	var officeObj = document.createElement("object");
	var p = document.createElement("param");
	p = document.createElement("param");
	p.setAttribute("name", "ProductCaption");
	p.setAttribute("value", "商洛市人口和计划生育局");
	officeObj.appendChild(p);
	p = document.createElement("param");
	p.setAttribute("name", "ProductKey");
	p.setAttribute("value", "B34BE82AA7A9F844066BFE682F8BFE9530363C4A");
	officeObj.appendChild(p);
	officeObj.width = "100%";
	officeObj.height = "100%";
	officeObj.classid = "clsid:C9BC4DFF-4248-4a3c-8A49-63A7D317F404";
	officeObj.codebase = __ctxPath
			+ "/js/core/ntkoffice/OfficeControl.cab#version=5,0,1,6";
	var panelConf = {
		border : false,
		layout : "fit"
	};
	var saveFn = function(config) {
		fileId = config.fileId ? config.fileId : "";
		var type = conf.doctype;
		var result = null;
		officeObj.IsUseUTF8URL = true;
		officeObj.IsUseUTF8Data = true;
		if (type == "doc" || type == "xls") {
			result = officeObj.SaveToURL( __fullPath
					+ "/file-upload", "uploadDocument", "fileId=" + fileId
					+ "&&file_cat=uploadDocument", config.docName + "."
					+ conf.doctype, 0);
		} else {
			result = officeObj.SaveToURL(__fullPath + "/file-upload",
					"uploadDocument", "fileId=" + fileId
							+ "&&file_cat=uploadDocument", config.docName + "."
							+ conf.doctype, 0);
		}
		if (result) {
			var obj = Ext.util.JSON.decode(result);
			if (obj && obj.success) {
				fileId = obj.fileId;
			} else {
				obj = {
					success : false
				};
			}
			return obj;
		} else {
			return null;
		}
	};
	if (conf.unshowMenuBar) {
		officeObj.Menubar = false;
		officeObj.IsShowEditMenu = false;
		officeObj.FileNew = false;
		officeObj.FileOpen = false;
		officeObj.FileSave = false;
		officeObj.FileSaveAs = false;
	}
	if (conf.showToolbar) {
		var buttons = [];
		if (conf.doctype == "doc") {
			buttons
					.push( {
						text : "保留修改痕迹",
						iconCls : "btn-archive-save-trace",
						handler : function() {
							if (isFileOpen) {
								officeObj.ActiveDocument.Application.UserName = curUserInfo.fullname;
								officeObj.ActiveDocument.TrackRevisions = true;
							}
						}
					});
			buttons.push("-");
		}
		if (conf.doctype == "doc") {
			buttons.push( {
				text : "取消保留痕迹",
				iconCls : "btn-archive-cancel-trace",
				handler : function() {
					if (isFileOpen) {
						officeObj.ActiveDocument.TrackRevisions = false;
					}
				}
			});
			buttons.push("-");
		}
		if (conf.doctype == "doc") {
			buttons.push( {
				text : "清除痕迹",
				iconCls : "btn-archive-eraser",
				handler : function() {
					if (isFileOpen) {
						officeObj.ActiveDocument.AcceptAllRevisions();
					}
				}
			});
			buttons.push("-");
		}
		if (conf.doctype == "doc") {
			buttons.push( {
				text : "动态套红",
				iconCls : "",
				scope : this,
				handler : function() {
					var strHeader = "XXXXXXX公司";
					if (!isFileOpen) {
						return;
					}
					if (officeObj.doctype != 1) {
						return;
					}
					var i, cNum = 30;
					var lineStr = "";
					try {
						for (i = 0; i < cNum; i++) {
							lineStr += "_";
						}
						with (officeObj.ActiveDocument.Application) {
							Selection.HomeKey(6, 0);
							Selection.TypeText(strHeader);
							Selection.TypeParagraph();
							Selection.TypeText(lineStr);
							Selection.TypeText("★");
							Selection.TypeText(lineStr);
							Selection.TypeParagraph();
							Selection.HomeKey(6, 1);
							Selection.ParagraphFormat.Alignment = 1;
							with (Selection.Font) {
								NameFarEast = "宋体";
								Name = "宋体";
								Size = 12;
								Bold = false;
								Italic = false;
								Underline = 0;
								UnderlineColor = 0;
								StrikeThrough = false;
								DoubleStrikeThrough = false;
								Outline = false;
								Emboss = false;
								Shadow = false;
								Hidden = false;
								SmallCaps = false;
								AllCaps = false;
								Color = 255;
								Engrave = false;
								Superscript = false;
								Subscript = false;
								Spacing = 0;
								Scaling = 100;
								Position = 0;
								Kerning = 0;
								Animation = 0;
								DisableCharacterSpaceGrid = false;
								EmphasisMark = 0;
							}
							Selection.MoveDown(5, 3, 0);
						}
					} catch (err) {
						alert("错误：" + err.number + ":" + err.description);
					} finally {
					}
				}
			});
			buttons.push("-");
		}
		if (conf.doctype == "doc") {
			buttons
					.push( {
						text : "模板套红",
						iconCls : "",
						scope : this,
						handler : function() {
							if (isFileOpen) {
								new PaintTemplateSelector(
										{
											callback : function(name, path) {
												this.close();
												if (path != "") {
													var headFileURL = __ctxPath
															+ "/attachFiles/"
															+ path;
													if (officeObj.doctype != 1) {
														return;
													}
													try {
														officeObj.ActiveDocument.Application.Selection
																.HomeKey(6, 0);
														officeObj
																.addtemplatefromurl(headFileURL);
													} catch (err) {
													}
												}
											}
										}).show();
							}
						}
					});
			buttons.push("-");
		}
		if (conf.doctype == "doc" || conf.doctype == "xls") {
			buttons
					.push( {
						text : "手写签名",
						iconCls : "",
						scope : this,
						handler : function() {
							if (isFileOpen) {
								try {
									officeObj.DoHandSign2("ntko", "ntko", 0, 0,
											1, 100);
								} catch (err) {
								}
							}
						}
					});
			buttons.push("-");
		}
		if (conf.doctype == "doc" || conf.doctype == "xls") {
			buttons.push( {
				text : "盖章",
				iconCls : "",
				scope : this,
				handler : function() {
					new SealSelector( {
						callback : function(name, path, belongName) {
							this.close();
							if (path != "") {
								var signUrl = __ctxPath + "/attachFiles/"
										+ path;
								if (officeObj.doctype == 1
										|| officeObj.doctype == 2) {
									try {
										officeObj.AddSecSignFromURL(
												curUserInfo.fullname, signUrl,
												100, 100, "ntko", 3, 100, 1);
									} catch (error) {
									}
								}
							}
						}
					}).show();
				}
			});
			buttons.push("-");
		}
		panelConf.tbar = new Ext.Toolbar( {
			items : buttons
		});
	}
	Ext.applyIf(panelConf, conf);
	var panel = new Ext.Panel(panelConf);
	panel.on("afterrender",
			function() {
				panel.body.appendChild(officeObj);
				panel.doLayout();
				if (fileId != "") {
					officeObj.BeginOpenFromURL(__ctxPath + "/file-download?fileId="
							+ fileId);
					isFileOpen = true;
				} else {
					var fileType = "";
					switch (conf.doctype) {
					case "doc":
						fileType = "Word.Document";
						fileTypeSimple = "wrod";
						break;
					case "xls":
						fileType = "Excel.Sheet";
						fileTypeSimple = "excel";
						break;
					case "ppt":
						fileType = "PowerPoint.Show";
						fileTypeSimple = "ppt";
						break;
					case 4:
						fileType = "Visio.Drawing";
						break;
					case 5:
						fileType = "MSProject.Project";
						break;
					case 6:
						fileType = "WPS Doc";
						break;
					case 7:
						fileType = "Kingsoft Sheet";
						break;
					default:
						fileType = "Word.Document";
					}
					try {
						officeObj.CreateNew(fileType);
						isFileOpen = true;
					} catch (err) {
					}
				}
			});
	return {
		panel : panel,
		officeObj : officeObj,
		openDoc : function(inFileId) {
			fileId = inFileId;
			officeObj
					.OpenFromURL(__ctxPath + "/file-download?fileId=" + fileId);
		},
		setReadOnly : function() {
			officeObj.SetReadOnly(true, "");
		},
		openDoc2 : function(fileId, fileUrl) {
			fileId = fileId;
			try {
				officeObj.OpenFromURL(__ctxPath + "/attachFiles/" + fileUrl);
				isFileOpen = true;
			} catch (err) {
				isFileOpen = false;
			}
		},
		saveDoc : function(config) {
			return saveFn(config);
		},
		closeDoc : function() {
			isFileOpen = false;
			officeObj.Close();
		}
	};
};