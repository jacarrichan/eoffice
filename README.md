eoffice
=======

java  struts2 hibernate  jbpm 办公管理系统
----------------------------------------------------------------------
#导入用户数据，便于登录
	导入tool目录里面的[joffice21-user.sql];


运行方法：mvn tomcat:run -Dmaven.test.skip=tue

登录账号:admin/admin
如果想更改密码,则执行:com.palmelf.test.system.AppUserDaoTestCase类的resetPassword这个方法

-------------------------
#清空数据库关闭约束
	SET FOREIGN_KEY_CHECKS=0;
	
	
	
	demo
---------


![日程管理](https://raw.githubusercontent.com/jacarrichan/eoffice/master/demo.jpg "")