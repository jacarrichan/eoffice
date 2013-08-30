NtkoSignPanel = Ext
		.extend(
				Ext.Panel,
				{
					ekeyObj : null,
					constructor : function(a) {
						Ext.applyIf(this, a);
						NtkoSignPanel.superclass.constructor.call(this, {
							border : false,
							layout : "fit"
						});
					},
					initComponent : function() {
						NtkoSignPanel.superclass.initComponent.call(this);
						this.ekeyObj = document.createElement("object");
						this.ekeyObj.classid = "clsid:97D0031E-4C58-4bc7-A9BA-872D5D572896";
						this.ekeyObj.codebase = __ctxPath
								+ "/js/core/ntkosign/ntkosigntoolv3.cab#version=3,0,2,0";
						this.ekeyObj.width = "100%";
						this.ekeyObj.height = "100%";
						var c = document.createElement("param");
						c.setAttribute("name", "BackColor");
						c.setAttribute("value", "16744576");
						this.ekeyObj.appendChild(c);
						c = document.createElement("param");
						c.setAttribute("name", "ForeColor");
						c.setAttribute("value", "16777215");
						this.ekeyObj.appendChild(c);
						c = document.createElement("param");
						c.setAttribute("name", "IsShowStatus");
						c.setAttribute("value", "-1");
						this.ekeyObj.appendChild(c);
						var a = document.createElement("SPAN");
						a.innerHTML = '<font color="red">不能装载印章管理控件。请在检查浏览器的选项中检查浏览器的安全设置。</font>';
						try {
							this.ekeyObj.appendChild(a);
						} catch (b) {
						}
					},
					afterRender : function() {
						NtkoSignPanel.superclass.afterRender.call(this);
						this.body.appendChild(this.ekeyObj);
						this.doLayout();
					},
					getEkeyObject : function() {
						this.ekeyObj.IsShowRect = false;
						return this.ekeyObj;
					},
					save : function(a, d, c, b) {
						this.ekeyObj.SignName = d;
						this.ekeyObj.SignUser = c;
						this.ekeyObj.Password = b;
						this.ekeyObj.IsUseUTF8URL = true;
						this.ekeyObj.IsUseUTF8Data = true;
						return this.ekeyObj.SaveToURL(__fullPath
								+ "/file-upload", "FileName", "fileId=" + a
								+ "&&file_cat=sealSign", d + ".esp", 0);
					},
					openFormURL : function(a, b) {
						this.ekeyObj.OpenFromURL(__ctxPath
								+ "/file-download?fileId=" + a, b);
						this.ekeyObj.IsShowRect = false;
						return this.ekeyObj;
					}
				});