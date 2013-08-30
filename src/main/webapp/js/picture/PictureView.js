Ext.ns("PictureView");
PictureView=Ext.extend(Ext.Panel,{
	searchPanel:null,gridPanel:null,store:null,topbar:null,constructor :function (a) {
		Ext.applyIf(this,a);
		this.initUIComponents();
		PictureView.superclass.constructor .call(this,{
			id:"PictureView",title:"地区信息列表",iconCls:"menu-role",region:"center",layout:"border",items:[this.searchPanel,this.gridPanel]
		});
	},initUIComponents:function () {
		this.searchPanel=new Ext.FormPanel({
			height:35,region:"north",frame:false,layoutConfig:{
				padding:"5",align:"middle"
			},id:"PictureSearchForm",layout:"hbox",defaults:{
				xtype:"label",border:false,margins:{
					top:2,right:4,bottom:2,left:4
				}
			},items: [{
				text: "请输入查询条件:"
			},
			{
				text: "图片标题"
			},
			{
				xtype: "textfield",
				name: "Q_title_S_LK"
			},
			{
				text: "所属相册ID"
			},
			{
				xtype: "textfield",
				name: "Q_albumId_S_LK"
			},
			{
				xtype:"button",text:"查询",iconCls:"search",handler:function () {
					var c=Ext.getCmp("PictureSearchForm");
					var d=Ext.getCmp("PictureGrid");
					if(c.getForm().isValid()) {
						$search({
							searchPanel:c,gridPanel:d
						});
					}
				}
			},{
				xtype:"button",text:"重置",iconCls:"reset",handler:function () {
					var c=Ext.getCmp("PictureSearchForm");
					c.getForm().reset();
				}
			}]
		});
		this.store=new Ext.data.Store({
			proxy:new Ext.data.HttpProxy({
				url:__ctxPath+"picture/listPicture.do"
			}),reader:new Ext.data.JsonReader({
				root:"result",totalProperty:"totalCounts",id:"picId",fields:[{
					name:"picId",type:"int"
				},"areaName","areaType"]
			}),remoteSort:true
		});
		this.store.setDefaultSort("picId","desc");
		this.store.load({
			params:{
				start:0,limit:25
			}
		});
		var b=new Ext.grid.CheckboxSelectionModel();
		var a=new Ext.grid.ColumnModel({
			columns:[b,new Ext.grid.RowNumberer(),{
				header:"图片ID",dataIndex:"picId",width:200
			},{
				header:"图片标题",dataIndex:"title",width:200
			},{
				header:"图片路径",dataIndex:"filePath",width:200
			},{
				header:"所属相册ID",dataIndex:"albumId",width:200
			},{
				header:"管理",dataIndex:"picId",width:80,renderer:function (j,i,e,g,k) {
					var c=e.data.picId;
					var f="";
					if(c!=-1) {
						if(isGranted("_PictureDel")) {
							f='<button title="删除" value=" " class="btn-del" onclick="PictureView.remove('+c+')"></button>';
						}if(isGranted("_PictureEdit")) {
							f+='&nbsp;<button title="编辑'+c+'" value=" " class="btn-edit" onclick="PictureView.edit('+c+')"></button>';
						}
					}return f;
				}
			}],defaults:{
				sortable:true,menuDisabled:false,width:100
			}
		});
		this.gridPanel=new Ext.grid.GridPanel({
			id:"PictureGrid",region:"center",tbar:this.topbar(),store:this.store,trackMouseOver:true,disableSelection:false,loadMask:true,autoHeight:true,cm:a,sm:b,viewConfig:{
				forceFit:true,enableRowBody:false,showPreview:false
			},bbar:new Ext.PagingToolbar({
				pageSize:25,store:this.store,displayInfo:true,displayMsg:"当前显示从{0}至{1}， 共{2}条记录",emptyMsg:"当前没有记录"
			})
		});
		this.gridPanel.addListener("rowdblclick",function (d,c,f) {
			d.getSelectionModel().each(function (e) {
				PictureView.edit(e.data.picId);
			});
		});
	}
});
PictureView.prototype.topbar=function () {
	var a=new Ext.Toolbar({
		id:"PictureFootBar",height:30,bodyStyle:"text-align:left",items:[]
	});
	if(isGranted("_PictureAdd")) {
		a.add(new Ext.Button({
			iconCls:"btn-add",text:"添加地区信息",handler:function () {
				new PictureForm().show();
			}
		}));
	}if(isGranted("_PictureDel")) {
		a.add(new Ext.Button({
			iconCls:"btn-del",text:"删除地区信息",handler:function () {
				var d=Ext.getCmp("PictureGrid");
				var b=d.getSelectionModel().getSelections();
				if(b.length==0) {
					Ext.ux.Toast.msg("信息","请选择要删除的记录！");
					return ;
				}var e=Array();
				var f="";
				for(var c=0;c<b.length;c++) {
					if(b[c].data.isDefaultIn=="0"&&b[c].data.picId!=-1) {
						e.push(b[c].data.picId);
					}else {
						f+=b[c].data.roleName+",";
					}
				}if(f=="") {
					PictureView.remove(e);
				}else {
					Ext.ux.Toast.msg("信息",f+"不能被删除！");
				}
			}
		}));
	}return a;
};
PictureView.remove=function (b) {
	var a=Ext.getCmp("PictureGrid");
	Ext.Msg.confirm("信息确认","您确认要删除该记录吗？",function (c) {
		if(c=="yes") {
			Ext.Ajax.request({
				url:__ctxPath+"picture/multiDelPicture.do",params:{
					ids:b
				},method:"post",success:function () {
					Ext.ux.Toast.msg("信息","成功删除所选记录！");
					a.getStore().reload({
						params:{
							start:0,limit:25
						}
					});
				}
			});
		}
	});
};

PictureView.edit=function (a) {
	new PictureForm({
		picId:a
	}).show();
};
