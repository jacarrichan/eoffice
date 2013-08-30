Ext.ns("AlbumView");
AlbumView=Ext.extend(Ext.Panel,{
	searchPanel:null,gridPanel:null,store:null,topbar:null,constructor :function (a) {
		Ext.applyIf(this,a);
		this.initUIComponents();
		AlbumView.superclass.constructor .call(this,{
			id:"AlbumView",title:"相册信息列表",iconCls:"menu-role",region:"center",layout:"border",items:[this.searchPanel,this.gridPanel]
		});
	},initUIComponents:function () {
		this.searchPanel=new Ext.FormPanel({
			height:35,region:"north",frame:false,layoutConfig:{
				padding:"5",align:"middle"
			},id:"AlbumSearchForm",layout:"hbox",defaults:{
				xtype:"label",border:false,margins:{
					top:2,right:4,bottom:2,left:4
				}
			},items: [{
				text: "请输入查询条件:"
			},
			{
				text: "相册名称"
			},
			{
				xtype: "textfield",
				name: "Q_name_S_LK"
			},
			{
				xtype:"button",text:"查询",iconCls:"search",handler:function () {
					var c=Ext.getCmp("AlbumSearchForm");
					var d=Ext.getCmp("AlbumGrid");
					if(c.getForm().isValid()) {
						$search({
							searchPanel:c,gridPanel:d
						});
					}
				}
			},{
				xtype:"button",text:"重置",iconCls:"reset",handler:function () {
					var c=Ext.getCmp("AlbumSearchForm");
					c.getForm().reset();
				}
			}]
		});
		this.store=new Ext.data.Store({
			
			proxy:new Ext.data.HttpProxy({
				url:__ctxPath+"/album/listAlbum.do"
			}),reader:new Ext.data.JsonReader({
				root:"result",totalProperty:"totalCounts",id:"albumId",fields:[{
					name:"albumId",type:"int"
				},"name","userId","facePicPath"]
			}),remoteSort:true
		});
		this.store.setDefaultSort("albumId","desc");
		this.store.load({
			params:{
				start:0,limit:25
			}
		});
		var b=new Ext.grid.CheckboxSelectionModel();
		var a=new Ext.grid.ColumnModel({
			columns:[b,new Ext.grid.RowNumberer(),{
				header:"相册ID",dataIndex:"albumId",width:200
			},{
				header:"相册名称",dataIndex:"name",width:200
			},{
				header:"用户ID",dataIndex:"userId",width:200
			},{
				header:"封面图片",dataIndex:"facePicPath",width:200
			},{
				header:"管理",dataIndex:"albumId",width:80,renderer:function (j,i,e,g,k) {
					var c=e.data.albumId;
					var f="";
					if(c!=-1) {
						if(isGranted("_AlbumDel")) {
							f='<button title="删除" value="albumId" class="btn-del" onclick="AlbumView.remove('+c+')"></button>';
						}if(isGranted("_AlbumEdit")) {
							f+='&nbsp;<button title="编辑'+c+'" value="albumId" class="btn-edit" onclick="AlbumView.edit('+c+')"></button>';
						}
					}return f;
				}
			}],defaults:{
				sortable:true,menuDisabled:false,width:100
			}
		});
		this.gridPanel=new Ext.grid.GridPanel({
			id:"AlbumGrid",region:"center",tbar:this.topbar(),store:this.store,trackMouseOver:true,disableSelection:false,loadMask:true,autoHeight:true,cm:a,sm:b,viewConfig:{
				forceFit:true,enableRowBody:false,showPreview:false
			},bbar:new Ext.PagingToolbar({
				pageSize:25,store:this.store,displayInfo:true,displayMsg:"当前显示从{0}至{1}， 共{2}条记录",emptyMsg:"当前没有记录"
			})
		});
		this.gridPanel.addListener("rowdblclick",function (d,c,f) {
			d.getSelectionModel().each(function (e) {
				AlbumView.edit(e.data.albumId);
			});
		});
	}
});
AlbumView.prototype.topbar=function () {
	var a=new Ext.Toolbar({
		id:"AlbumFootBar",height:30,bodyStyle:"text-align:left",items:[]
	});
	if(isGranted("_AlbumAdd")) {
		a.add(new Ext.Button({
			iconCls:"btn-add",text:"添加相册信息",handler:function () {
				new AlbumForm().show();
			}
		}));
	}if(isGranted("_AlbumDel")) {
		a.add(new Ext.Button({
			iconCls:"btn-del",text:"删除相册信息",handler:function () {
				var d=Ext.getCmp("AlbumGrid");
				var b=d.getSelectionModel().getSelections();
				if(b.length==0) {
					Ext.ux.Toast.msg("信息","请选择要删除的记录！");
					return ;
				}var e=Array();
				var f="";
				for(var c=0;c<b.length;c++) {
					if(b[c].data.isDefaultIn=="0"&&b[c].data.albumId!=-1) {
						e.push(b[c].data.albumId);
					}else {
						f+=b[c].data.roleName+",";
					}
				}if(f=="") {
					AlbumView.remove(e);
				}else {
					Ext.ux.Toast.msg("信息",f+"不能被删除！");
				}
			}
		}));
	}return a;
};
AlbumView.remove=function (b) {
	var a=Ext.getCmp("AlbumGrid");
	Ext.Msg.confirm("信息确认","您确认要删除该记录吗？",function (c) {
		if(c=="yes") {
			Ext.Ajax.request({
				url:__ctxPath+"/album/multiDelAlbum.do",params:{
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

AlbumView.edit=function (a) {
	new AlbumForm({
		albumId:a
	}).show();
};
