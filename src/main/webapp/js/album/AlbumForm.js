AlbumForm = Ext.extend(Ext.Window, {
	formPanel : null,
	displayPanel : null,
	constructor : function(a) {
		Ext.applyIf(this, a);
		this.initUIComponents();
		AlbumForm.superclass.constructor.call(this, {
			layout : "fit",
			id : "AlbumFormWin",
			iconCls : "menu-Album",
			title : "详细信息",
			width : 880,
			height : 510,
			items : [this.formPanel,this.displayPanel],
			maximizable : true,
			border : false,
			modal : true,
			plain : true,
			buttonAlign : "center",
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		this.formPanel = new Ext.FormPanel({
			url : __ctxPath + "/album/saveAlbum.do",
			layout : "form",
			id : "AlbumForm",
			bodyStyle : "padding:5px;",
			frame : false,
			width : 480,
			formId : "AlbumFormId",
			defaultType : "textfield",
			items : [ {
				name : "album.albumId",
				id : "albumId",
				xtype : "hidden",
				value : this.albumId == null ? "" : this.albumId
			}, {
				fieldLabel : "相册名称",
				name : "album.name",
				id : "name",
				allowBlank : false,
				blankText : "请填写相册名称",
				anchor : "98%"
			}, {
				name : "album.userId",
				id : "userId",
				xtype : "hidden",
				allowBlank : false,
				blankText : "请填写用户ID",
				anchor : "98%"
			}, {
				fieldLabel : "封面图片",
				name : "album.facePicPath",
				id : "facePicPath",
				allowBlank : false,
				blankText : "请填写封面图片",
				anchor : "98%"
			} ]
		});
		this.displayPanel = new Ext.FormPanel({
			id : "displayPhoto",
			xtype : "panel",
			width : 230,
			height : 435,
			title : "个人照片",
			html : '<img src="' + __ctxPath
					+ '/images/default_image_male.jpg"/>'
		});
		if (this.albumId != null && this.albumId != "undefined") {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + "/album/getAlbum.do?albumId=" + this.albumId,
				waitMsg : "正在载入数据...",
				success : function(b, c) {
					Ext.ux.Toast.msg("编辑", "" + this.albumId);
				},
				failure : function(a, b) {
					Ext.ux.Toast.msg("编辑", "载入失败");
				}
			});
		}
		this.buttons = [ {
			text : "上传",
			iconCls : "btn-upload",
			handler : function() {
				AlbumForm.uploadPhotoBtn();
			}
		}, {
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var a = Ext.getCmp("AlbumForm");
				if (a.getForm().isValid()) {
					a.getForm().submit({
						method : "post",
						waitMsg : "正在提交数据...",
						success : function(b, c) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							Ext.getCmp("AlbumGrid").getStore().reload();
							Ext.getCmp("AlbumFormWin").close();
						},
						failure : function(b, c) {
							Ext.MessageBox.show({
								title : "操作信息",
								msg : "信息保存出错，请联系管理员！",
								buttons : Ext.MessageBox.OK,
								icon : "ext-mb-error"
							});
							Ext.getCmp("AlbumFormWin").close();
						}
					});
				}
			}
		}, {
			text : "取消",
			iconCls : "btn-cancel",
			handler : function() {
				Ext.getCmp("AlbumFormWin").close();
			}
		} ];
	}
});
AlbumForm.uploadPhotoBtn = function(){
	var a = Ext.getCmp("facePicPath");
	var b = App.createUploadDialog({
		file_cat : "album/appUser",
		callback : uploadUserPhoto,
		permitted_extensions : [ "jpg" ]
	});
	b.show("queryBtn");
};
function uploadUserPhoto(b) {
	var a = Ext.getCmp("facePicPath");
	var c = Ext.getCmp("displayPhoto");
	a.setValue(b[0].filepath);
	c.body.update('<img src="' + __ctxPath + "/attachFiles/" + b[0].filepath
			+ '"  width="100%" height="100%"/>');
}