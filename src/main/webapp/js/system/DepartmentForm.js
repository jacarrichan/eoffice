DepartmentForm = Ext.extend(Ext.Window, {
	formPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		DepartmentForm.superclass.constructor.call(this, {
			layout : "fit",
			id : "DepartmentFormWin",
			title : "部门信息",
			iconCls : "menu-department",
			width : 400,
			height : 170,
			minWidth : 399,
			minHeight : 169,
			items : this.formPanel,
			border : false,
			modal : true,
			plain : true,
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.form.FormPanel({
			frame : false,
			id : "departmentForm",
			bodyStyle : "padding : 5px;",
			layout : "form",
			defaultType : "textfield",
			url : __ctxPath + "/system/addDepartment.do",
			defaultType : "textfield",
			reader : new Ext.data.JsonReader({
				root : "data"
			}, [ {
				name : "depId",
				mapping : "depId"
			}, {
				name : "depName",
				mapping : "depName"
			}, {
				name : "depDesc",
				mapping : "depDesc"
			}, {
				name : "parentId",
				mapping : "parentId"
			} ]),
			defaults : {
				anchor : "95%,95%",
				allowBlank : false,
				selectOnFocus : true,
				msgTarget : "side"
			},
			items : [ {
				xtype : "hidden",
				name : "department.depId",
				id : "depId"
			}, {
				xtype : "hidden",
				name : "department.parentId",
				id : "parentId",
				value : this.nodeId
			}, {
				xtype : "hidden",
				name : "department.path",
				id : "path"
			}, {
				fieldLabel : "部门名",
				name : "department.depName",
				blankText : "部门名为必填!",
				id : "depName"
			}, {
				fieldLabel : "部门描述",
				xtype : "textarea",
				name : "department.depDesc",
				blankText : "部门描述为必填!",
				id : "depDesc"
			} ]
		});
		this.buttons = [ {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var a = Ext.getCmp("treePanel");
				if (Ext.getCmp("departmentForm").getForm().isValid()) {
					Ext.getCmp("departmentForm").getForm().submit({
						waitMsg : "正在提交部门信息",
						success : function(b, c) {
							Ext.ux.Toast.msg("操作信息", "添加部门成功！");
							Ext.getCmp("DepartmentFormWin").close();
							a.root.reload();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-del",
			handler : function() {
				Ext.getCmp("DepartmentFormWin").close();
			}
		} ];
	}
});