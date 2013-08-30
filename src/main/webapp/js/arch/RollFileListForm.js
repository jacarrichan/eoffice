RollFileListForm = Ext.extend(Ext.Window, {
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		RollFileListForm.superclass.constructor.call(this, {
			id : "RollFileListFormWin",
			layout : "fit",
			items : this.formPanel,
			modal : true,
			height : 350,
			width : 500,
			maximizable : true,
			title : "附件详细信息",
			buttonAlign : "center",
			buttons : [ {
				text : "确定",
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
			layout : "form",
			bodyStyle : "padding:10px",
			border : false,
			autoScroll : true,
			defaults : {
				anchor : "96%,96%"
			},
			defaultType : "displayfield",
			labelWidth : 60,
			labelPad : 3,
			items : [ {
				fieldLabel : "主键ID",
				name : "rollFileList.listId",
				xtype : "hidden",
				value : this.listId == null ? "" : this.listId
			}, {
				fieldLabel : "卷ID",
				name : "rollFileList.rollFileId",
				allowBlank : false,
				xtype : "hidden",
				value : this.rollFileId == null ? "" : this.rollFileId
			}, {
				fieldLabel : "附ID",
				id : "rollFileList.fileAttach.fileId",
				name : "rollFileList.fileId",
				allowBlank : false,
				xtype : "hidden"
			}, {
				fieldLabel : "文件名称",
				id : "rollFileList.fileAttach.fileName",
				name : "rollFileList.fileAttach.fileName",
				allowBlank : false,
				xtype : "displayfield",
				width : 261
			}, {
				fieldLabel : "文件路径",
				xtype : "displayfield",
				readOnly : true,
				id : "rollFileList.fileAttach.filePath",
				name : "rollFileList.fileAttach.filePath",
				allowBlank : false,
				maxLength : 256
			}, {
				fieldLabel : "文件类型",
				xtype : "displayfield",
				readOnly : true,
				name : "rollFileList.fileAttach.ext",
				maxLength : 128
			}, {
				fieldLabel : "下载次数",
				readOnly : true,
				name : "rollFileList.downLoads",
				value : 0,
				xtype : "displayfield"
			}, {
				fieldLabel : "序号",
				name : "rollFileList.sn",
				xtype : "displayfield"
			}, {
				fieldLabel : "概要",
				name : "rollFileList.shortDesc",
				xtype : "displayfield"
			}, {
				xtype : "compositefield",
				msgTarget : "side",
				border : true,
				fieldLabel : "录入人",
				items : [ {
					readOnly : true,
					name : "rollFileList.fileAttach.creator",
					value : curUserInfo.fullname,
					readOnly : true,
					xtype : "displayfield",
					maxLength : 128
				}, {
					xtype : "displayfield",
					value : "录入时间:"
				}, {
					readOnly : true,
					name : "rollFileList.fileAttach.createtime",
					xtype : "displayfield",
					format : "Y-m-d",
					readOnly : true,
					value : new Date().format("Y-m-d")
				} ]
			} ]
		});
		if (this.listId != null && this.listId != "undefined") {
			this.formPanel.loadData( {
				url : __ctxPath + "/arch/getRollFileList.do?listId="
						+ this.listId,
				root : "data",
				preName : "rollFileList"
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
	}
});