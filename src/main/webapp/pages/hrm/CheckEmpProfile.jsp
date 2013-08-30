<%@ page pageEncoding="UTF-8"%>
<%
	String basePath=request.getContextPath();
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.cyjt.core.util.AppUtil"%>

<%@page import="com.cyjt.oa.service.hrm.EmpProfileService"%>
<%@page import="com.cyjt.oa.model.hrm.EmpProfile"%>

<%
	String profileId = request.getParameter("profileId");
	EmpProfileService empProfileService = (EmpProfileService)AppUtil.getBean("empProfileService");
	EmpProfile empProfile = empProfileService.get(new Long(profileId));
	request.setAttribute("empProfile",empProfile);
%>
<table class="table-info" style="table-layout: fixed;" cellpadding="0" cellspacing="1" width="98%" align="center">
	
		<tr>
			<td rowspan="7" width="15%">
				&nbsp;<img title="${empProfile.fullname}" width="88" height="120" src="<%=request.getContextPath()%>/attachFiles/${empProfile.photo}">
			</td>
		</tr>
		<tr >
			<th width="15%">
				档案号
			</th >
			<td colspan="3">
					${empProfile.profileNo}
					<c:if test="${empProfile.approvalStatus==1}"><img title="通过审核" src="<%=basePath%>/images/flag/customer/effective.png"/></c:if>
				   <c:if test="${empProfile.approvalStatus==2}"><img title="没通过审核" src="<%=basePath%>/images/flag/customer/invalid.png"/></c:if>
			</td>
		</tr>
		
		<tr>
			<th width="15%">
				姓名
			</th>
			<td width="30%">
				${empProfile.fullname }
			</td>
			<th width="15%">
				宗教信仰
			</th>
			<td width="30%">
				${empProfile.religion }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				身份证号
			</th>
			<td>
				${empProfile.idCard }
			</td>
			<th width="20%">
				政治面貌
			</th>
			<td>
				${empProfile.party }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				出生日期
			</th>
			<td>
			<fmt:formatDate value="${empProfile.birthday}" pattern="yyyy-MM-dd"/>
			</td>
			<th width="20%">
				国籍
			</th>
			<td>
				${empProfile.nationality }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				性别
			</th>
			<td>
				${empProfile.sex }
			</td>
			<th width="20%">
				民族
			</th>
			<td>
				${empProfile.race }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				婚姻状况
			</th>
			<td>
				${empProfile.marriage }
			</td>
			<th width="20%">
				出生地
			</th>
			<td>
				${empProfile.birthPlace }
			</td>
		</tr>
</table>
<table class="table-info" style="table-layout: fixed;word-spacing: normal;" cellpadding="0" cellspacing="1" width="98%" align="center">
		<tr >
			<th width="15%">
				家庭地址
			</th>
			<td>
				${empProfile.address }
			</td>
			<th width="15%">
				电话号码
			</th>
			<td>
				${empProfile.phone }
			</td>
		</tr>
		<tr>
			<th width="15%">
				家庭邮编
			</th>
			<td>
				${empProfile.homeZip }
			</td>
			<th  width="20%">
				QQ号码
			</th>
			<td>
				${empProfile.qq }
			</td>
		</tr>
		<tr>
			
			<th width="20%">
				手机号
			</th>
			<td>
				${empProfile.mobile}
			</td>
			<th width="20%">
				电子邮箱
			</th>
			<td>
				${empProfile.email }
			</td>
		</tr>
		<tr>
			<th width="20%">
				学历
			</th>
			<td>
				${empProfile.eduDegree }
			</td>
			<th width="20%">
				毕业院校
			</th>
			<td>
				${empProfile.eduCollege }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				专业
			</th>
			<td>
				${empProfile.eduMajor }
			</td>
			<th width="20%">
				参加工作时间
			</th>
			<td>
				<fmt:formatDate value="${empProfile.startWorkDate}" pattern="yyyy-MM-dd"/>
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				教育背景
			</th>
			<td colspan="3">
				${empProfile.eduCase }
			</td>
		</tr>
		<tr>
			<th width="20%">
				职称
			</th>
			<td>
				${empProfile.designation }
			</td>
			<th width="20%">
				职位
			</th>
			<td>
				${empProfile.position }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				所属部门或公司
			</th>
			<td>
				${empProfile.depName }
			</td>
			<th width="20%">
				薪酬标准单名称
			</th>
			<td>
				${empProfile.standardName }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				开户银行
			</th>
			<td>
				${empProfile.openBank }
			</td>
			<th width="20%">
				薪酬标准号
			</th>
			<td>
				${empProfile.standardMiNo }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				银行账号
			</th>
			<td>
				${empProfile.bankNo }
			</td>
			<th width="20%">
				薪酬标准金额
			</th>
			<td>
				${empProfile.standardMoney }
			</td>
		</tr>
		
		<tr>
			<th width="80">
				培训情况
			</th>
			<td colspan="3">
				<div  style="width:700; overflow: auto;"><p>${empProfile.trainingCase }</p></div>
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				奖惩情况
			</th>
			<td colspan="3">
				${empProfile.awardPunishCase }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				工作经历
			</th>
			<td colspan="3">
				${empProfile.workCase }
			</td>
		</tr>
		<tr>
			<th width="20%">
				个人爱好
			</th>
			<td colspan="3">
				${empProfile.hobby }
			</td>
		</tr>
		
		<tr>
			<th width="20%">
				备注
			</th>
			<td colspan="3">
				${empProfile.memo }
			</td>
		</tr>
</table>
<c:if test="${empProfile.approvalStatus==1||empProfile.approvalStatus==2}">
<table class="table-info" style="table-layout: fixed;" cellpadding="0" cellspacing="1" width="98%" align="center">
 <tr>
		<th width="15%">
			建档人
		</th>
		<td>
			${empProfile.creator }
		</td>
		<th width="15%">
			建档时间
		</th>
		<td>
			<fmt:formatDate value="${empProfile.createtime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th width="15%">
			审核人
		</th>
		<td>
			${empProfile.checkName}
		</td>
		<th width="15%">
			审核时间
		</th>
		<td>
			<fmt:formatDate value="${empProfile.checktime}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th width="15%">
			审核意见
		</th>
		<td colspan="3">
			<c:if test="${empProfile.approvalStatus!=0}">${empProfile.opprovalOpinion }</c:if>
		</td>
	</tr>
</table>
</c:if>	
	
	