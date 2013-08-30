PictureForm=Ext.extend(Ext.Window,{
	formPanel:null,constructor :function (a) {
		Ext.applyIf(this,a);
		this.initUIComponents();
		PictureForm.superclass.constructor .call(this,{
			layout:"fit",id:"PictureFormWin",iconCls:"menu-Picture",title:"详细信息",width:880,height:510,items:this.formPanel,maximizable:true,border:false,modal:true,plain:true,buttonAlign:"center",buttons:this.buttons
		});
	},initUIComponents:function () {
		this.formPanel=new Ext.FormPanel({
			url:__ctxPath+"/picture/savePicture.do",layout:"form",id:"PictureForm",bodyStyle:"padding:5px;",frame:false,width:480,formId:"PictureFormId",defaultType:"textfield",items:[{
				name:"Picture.picId",id:"picId",xtype:"hidden",value:this.picId==null?"":this.picId
			},
			{
				fieldLabel:"图片ID",name:"picture.picId",id:"picId",allowBlank:false,blankText:"请填写图片ID",anchor:"98%"
			},
			{
				fieldLabel:"图片标题",name:"picture.title",id:"title",allowBlank:false,blankText:"请填写图片标题",anchor:"98%"
			},
			{
				fieldLabel:"图片路径",name:"picture.filePath",id:"filePath",allowBlank:false,blankText:"请填写图片路径",anchor:"98%"
			},
			{
				fieldLabel:"所属相册ID",name:"picture.albumId",id:"albumId",allowBlank:false,blankText:"请填写所属相册ID",anchor:"98%"
			}
			]
		});
		if(this.picId!=null&&this.picId!="undefined") {
			this.formPanel.getForm().load({
				deferredRender:false,url:__ctxPath+"picture/getPicture.do?picId="+this.picId,waitMsg:"正在载入数据...",success:function (b,c) {
					//Ext.ux.Toast.msg("编辑","dddddddddd"+this.picId);
				},failure:function (a,b) {
					Ext.ux.Toast.msg("编辑","载入失败");
				}
			});
		}this.buttons=[{
			text:"保存",iconCls:"btn-save",handler:function () {
				var a=Ext.getCmp("PictureForm");
				if(a.getForm().isValid()) {
					a.getForm().submit({
						method:"post",waitMsg:"正在提交数据...",success:function (b,c) {
							Ext.ux.Toast.msg("操作信息","成功保存信息！");
							Ext.getCmp("PictureGrid").getStore().reload();
							Ext.getCmp("PictureFormWin").close();
						},failure:function (b,c) {
							Ext.MessageBox.show({
								title:"操作信息",msg:"信息保存出错，请联系管理员！",buttons:Ext.MessageBox.OK,icon:"ext-mb-error"
							});
							Ext.getCmp("PictureFormWin").close();
						}
					});
				}
			}
		},{
			text:"取消",iconCls:"btn-cancel",handler:function () {
				Ext.getCmp("PictureFormWin").close();
			}
		}];
	}
});
