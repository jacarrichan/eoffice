ArchFondForm = Ext.extend(Ext.Window, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchFondForm.superclass.constructor.call(this, {
			id : "ArchFondFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 400,
			autoDestroy : true,
			width : 800,
			maximizable : true,
			title : "全宗详细信息",
			buttonAlign : "center",
			buttons : [ {
				text : "保存",
				iconCls : "btn-save",
				scope : this,
				handler : this.save
			}, {
				text : "重置",
				iconCls : "btn-reset",
				scope : this,
				handler : this.reset
			}, {
				text : "关闭",
				iconCls : "btn-cancel",
				scope : this,
				handler : this.cancel
			} ]
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel( {
			layout : "column",
			bodyStyle : "padding:10px;",
			border : false,
			autoScroll : true,
			id : "ArchFondForm",
			defaults : {
				border : false,
				anchor : "98%,98%"
			},
			items : [
					{
						name : "archFond.archFondId",
						xtype : "hidden",
						value : this.archFondId == null ? "" : this.archFondId
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "全宗号",
							width : 100,
							xtype : "textfield",
							id : "ArchFondForm.archFond.afNo",
							name : "archFond.afNo",
							allowBlank : false,
							maxLength : 64
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "全宗名",
							width : 100,
							name : "archFond.afName",
							allowBlank : false,
							xtype : "textfield",
							maxLength : 128
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "全宗分类",
							width : 100,
							name : "archFond.typeName",
							xtype : "combo",
							mode : "local",
							editable : false,
							triggerAction : "all",
							value : this.typeName == null ? "" : this.typeName,
							store : new Ext.data.JsonStore( {
								url : __ctxPath + "/system/subGlobalType.do",
								baseParams : {
									parentId : 0,
									catKey : "AR_FD"
								},
								autoLoad : true,
								autoShow : true,
								root : "result",
								idProperty : "proTypeId",
								fields : [ "proTypeId", "typeName" ]
							}),
							valueField : "proTypeId",
							displayField : "typeName",
							listeners : {
								scope : this,
								"select" : function(c, a, b) {
									Ext.getCmp("ArchFondForm").getForm()
											.findField("archFond.proTypeId")
											.setValue(a.get("proTypeId"));
								}
							}
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "案卷数",
							width : 100,
							name : "archFond.caseNums",
							xtype : "numberfield"
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "状态",
							width : 100,
							hiddenName : "archFond.status",
							xtype : "combo",
							mode : "local",
							editable : false,
							triggerAction : "all",
							store : [ [ "0", "草稿" ], [ "1", "启用" ],
									[ "-1", "禁用" ] ]
						}
					}, {
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "开放形式",
							name : "archFond.openStyle",
							width : 100,
							editable : true,
							lazyInit : false,
							forceSelection : false,
							xtype : "diccombo",
							itemName : "全宗开放形式"
						}
					}, {
						columnWidth : 1,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "全宗概述",
							width : 604,
							name : "archFond.shortDesc",
							xtype : "textarea",
							maxLength : 4000
						}
					}, {
						columnWidth : 1,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "全宗描述",
							width : 604,
							name : "archFond.descp",
							xtype : "textarea",
							maxLength : 65535
						}
					}, {
						columnWidth : 1,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "全宗整理描述",
							name : "archFond.clearupDesc",
							xtype : "textarea",
							width : 604,
							maxLength : 4000
						}
					}, {
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "创建时间",
							width : 100,
							name : "archFond.createTime",
							xtype : "datefield",
							format : "Y-m-d",
							readOnly : true,
							value : new Date()
						}
					}, {
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "更新时间",
							width : 100,
							name : "archFond.updateTime",
							xtype : "datefield",
							format : "Y-m-d",
							readOnly : true,
							value : new Date()
						}
					}, {
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "创建人",
							width : 100,
							xtype : "textfield",
							name : "archFond.creatorName",
							value : curUserInfo.fullname,
							readOnly : true,
							maxLength : 32
						}
					}, {
						columnWidth : 1,
						fieldLabel : "创建人ID",
						name : "archFond.creatorId",
						value : curUserInfo.userId,
						readOnly : true,
						xtype : "hidden"
					}, {
						columnWidth : 1,
						fieldLabel : "全宗分类id",
						id : "archFond.proTypeId",
						name : "archFond.proTypeId",
						readOnly : true,
						value : this.proTypeId == null ? "" : this.proTypeId,
						xtype : "hidden",
						maxLength : 128
					} ]
		});
		if (this.archFondId != null && this.archFondId != "undefined") {
			this.formPanel.loadData( {
				url : __ctxPath + "/arch/getArchFond.do?archFondId="
						+ this.archFondId,
				root : "data",
				preName : "archFond"
			});
		}
	},
	reset : function() {
		this.formPanel.getForm().reset();
	},
	cancel : function() {
		this.close();
	},
	save : function() {
		if (this.archFondId == null || this.archFondId == "undefined") {
			var a = true;
			Ext.Ajax.request( {
				url : __ctxPath + "/arch/listArchFond.do",
				method : "POST",
				async : false,
				success : function(b, c) {
					var d = Ext.decode(b.responseText);
					if (d.result.length > 0) {
						a = false;
					}
				},
				failure : function(b, c) {
					Ext.MessageBox.show( {
						title : "操作信息",
						msg : "信息保存出错，请联系管理员！",
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
				},
				params : {
					Q_afNo_S_EQ : this.formPanel.getForm().findField(
							"ArchFondForm.archFond.afNo").getValue()
				}
			});
			if (!a) {
				Ext.ux.Toast.msg("操作信息", "全宗号重覆，不能保存！");
				return;
			}
		}
		$postForm( {
			formPanel : this.formPanel,
			scope : this,
			url : __ctxPath + "/arch/saveArchFond.do",
			callback : function(b, d) {
				var c = Ext.getCmp("ArchFondGrid");
				if (c != null) {
					c.getStore().reload();
				}
				this.close();
			}
		});
	}
});