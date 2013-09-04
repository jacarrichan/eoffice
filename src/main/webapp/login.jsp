<%@page pageEncoding="UTF-8"%>
<%@page import="com.palmelf.core.util.AppUtil"%>

<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="shortcut icon" href="images/favicon.ico"/>
		<title>欢迎登录<%=AppUtil.getCompanyName()%>协同办公系统</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/login.css" />
	</head>
	<body>
		<div class="body_out_work">
			<div class="work_big_pic">
				<div class="work_table_out">
					<table width="507" border="0" height="264" cellspacing="0"
						cellpadding="0" class="work_table">
						<tr>
							<td colspan="2" height="66">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="202" width="248">
								&nbsp;
							</td>
							<td height="202">
								<!--表单部分begin-->
								<form action="<%=request.getContextPath()%>/login.do" method="post">
									<table width="200" border="0" cellspacing="0" cellpadding="0"
										class="work_form_table">
										<tr>
											<td colspan="2">
												<input name="username" type="text" value="" class="input1" />
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<input name="password" type="password" value="" class="input1" />
											</td>
										</tr>
										<tr>
											<td>
												<input type="text" name="checkCode" id="checkCode" value="" class="input2" />
											</td>
											<td>
												<img width="65" height="20" src="<%=request.getContextPath()%>/CaptchaImg" />
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<input type="image" src="images/login_sub.jpg"
													class="lgoin_sub" />
											</td>
										</tr>
									</table>
								</form>
								<!--表单部分end-->
							</td>
						</tr>
					</table>
					<div class="copyright">
						版权所有：XXXXXXX有限公司
					</div>
				</div>
			</div>
		</div>
	</body>
</html>