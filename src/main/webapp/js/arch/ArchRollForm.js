ArchRollForm = Ext.extend(Ext.Window, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		ArchRollForm.superclass.constructor.call(this, {
			id : "ArchRollFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 450,
			width : 800,
			maximizable : true,
			title : "案卷详细信息",
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
			id : "ArchRollForm",
			layout : "column",
			bodyStyle : "padding:10px",
			border : false,
			autoScroll : true,
			defaults : {
				border : false,
				anchor : "98%,98%"
			},
			items : [
					{
						name : "archRoll.rollId",
						xtype : "hidden",
						value : this.rollId == null ? "" : this.rollId
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "案卷号",
							width : 100,
							xtype : "textfield",
							id : "ArchRollForm.archRoll.rollNo",
							name : "archRoll.rollNo",
							allowBlank : false,
							maxLength : 64
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "案卷名称",
							width : 100,
							xtype : "textfield",
							name : "archRoll.rolllName",
							allowBlank : false,
							maxLength : 128
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "目录号",
							width : 100,
							xtype : "textfield",
							name : "archRoll.catNo",
							maxLength : 64
						}
					},
					{
						columnWidth : 1,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "主题词",
							width : 604,
							name : "archRoll.keyWords",
							xtype : "textarea",
							maxLength : 512
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "开放形式",
							width : 100,
							name : "archRoll.openStyle",
							editable : true,
							lazyInit : false,
							forceSelection : false,
							xtype : "diccombo",
							itemName : "案卷开放形式"
						}
					},
					{
						columnWidth : 0.67,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "状态",
							width : 100,
							hiddenName : "archRoll.status",
							xtype : "combo",
							value : "1",
							mode : "local",
							editable : false,
							triggerAction : "all",
							store : [ [ "1", "正常" ], [ "0", "销毁" ] ]
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "制作单位",
							width : 100,
							xtype : "textfield",
							name : "archRoll.editCompany",
							maxLength : 128
						}
					},
					{
						columnWidth : 0.67,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "制作部门",
							width : 100,
							xtype : "textfield",
							name : "archRoll.editDep",
							maxLength : 128
						}
					},
					{
						columnWidth : 1,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "备考表说明",
							width : 604,
							name : "archRoll.decp",
							xtype : "textarea",
							maxLength : 65535
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "全宗号",
							width : 100,
							xtype : "textfield",
							name : "archRoll.afNo",
							allowBlank : false,
							xtype : "combo",
							mode : "local",
							editable : false,
							triggerAction : "all",
							store : new Ext.data.JsonStore( {
								url : __ctxPath + "/arch/listArchFond.do",
								autoLoad : true,
								autoShow : true,
								root : "result",
								idProperty : "archFondId",
								fields : [ "archFondId", "afNo" ]
							}),
							valueField : "archFondId",
							displayField : "afNo",
							listeners : {
								scope : this,
								"select" : function(c, a, b) {
									Ext.getCmp("ArchRollForm").getForm()
											.findField("archRoll.archFondId")
											.setValue(a.get("archFondId"));
								}
							}
						}
					},
					{
						columnWidth : 0.67,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "案卷分类",
							width : 100,
							name : "archRoll.typeName",
							xtype : "combo",
							mode : "local",
							editable : false,
							triggerAction : "all",
							value : this.typeName == null ? "" : this.typeName,
							store : new Ext.data.JsonStore( {
								url : __ctxPath + "/system/subGlobalType.do",
								baseParams : {
									parentId : 0,
									catKey : "AR_RL"
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
									Ext.getCmp("ArchRollForm").getForm()
											.findField("archRoll.proTypeId")
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
							fieldLabel : "保管期限",
							width : 100,
							name : "archRoll.timeLimit",
							editable : true,
							lazyInit : false,
							forceSelection : false,
							xtype : "diccombo",
							itemName : "案卷保管期限"
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "起始日期",
							width : 100,
							name : "archRoll.startTime",
							xtype : "datefield",
							format : "Y-m-d",
							value : new Date()
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "结束日期",
							width : 100,
							name : "archRoll.endTime",
							xtype : "datefield",
							format : "Y-m-d",
							value : new Date()
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "立卷人",
							width : 100,
							xtype : "textfield",
							name : "archRoll.author",
							maxLength : 32
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "立卷时间",
							width : 100,
							name : "archRoll.setupTime",
							xtype : "datefield",
							format : "Y-m-d",
							value : new Date()
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "检查人",
							width : 100,
							xtype : "textfield",
							name : "archRoll.checker",
							maxLength : 32
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "录入人",
							width : 100,
							xtype : "textfield",
							name : "archRoll.creatorName",
							value : curUserInfo.fullname,
							readOnly : true,
							maxLength : 32
						}
					},
					{
						columnWidth : 0.67,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "录入时间",
							width : 100,
							name : "archRoll.createTime",
							xtype : "datefield",
							format : "Y-m-d",
							readOnly : true,
							value : new Date()
						}
					},
					{
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "案卷分类ID",
							width : 100,
							value : this.proTypeId == null ? ""
									: this.proTypeId,
							id : "archRoll.proTypeId",
							name : "archRoll.proTypeId",
							xtype : "hidden"
						}
					}, {
						columnWidth : 0.33,
						layout : "form",
						labelWidth : 100,
						items : {
							fieldLabel : "全宗ID",
							width : 100,
							id : "archRoll.archFondId",
							name : "archRoll.archFondId",
							xtype : "hidden"
						}
					} ]
		});
		if (this.rollId != null && this.rollId != "undefined") {
			this.formPanel.loadData( {
				url : __ctxPath + "/arch/getArchRoll.do?rollId=" + this.rollId,
				root : "data",
				preName : "archRoll"
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
		if (this.rollId == null || this.rollId == "undefined") {
			var a = true;
			Ext.Ajax.request( {
				url : __ctxPath + "/arch/listArchRoll.do",
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
					Q_rollNo_S_EQ : this.formPanel.getForm().findField(
							"ArchRollForm.archRoll.rollNo").getValue()
				}
			});
			if (!a) {
				Ext.ux.Toast.msg("操作信息", "案卷号重覆，不能保存！");
				return;
			}
		}
		$postForm( {
			formPanel : this.formPanel,
			scope : this,
			url : __ctxPath + "/arch/saveArchRoll.do",
			callback : function(b, d) {
				var c = Ext.getCmp("ArchRollGrid");
				if (c != null) {
					c.getStore().reload();
				}
				this.close();
			}
		});
	}
});