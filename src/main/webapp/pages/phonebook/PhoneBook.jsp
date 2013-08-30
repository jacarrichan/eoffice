<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
</head>
<body>
       <table class="table-info" width="460">
         <tr ><td colspan="6">基本资料</td></tr>
         <tr >
           <td>
			  联系人编号:</td><td><s:property value="phoneBook.phoneId" />
           </td>
            <td>
			  姓名:</td><td><s:property value="phoneBook.fullname" />
            </td>
            <td>
			  妮称:</td><td><s:property value="phoneBook.nickName" />
            </td>
            </tr>
            <tr> 
            <td>
			  称谓:</td><td><s:property value="phoneBook.title" />
            </td>
            <td>
			  生日:</td><td><fmt:formatDate value="${phoneBook.birthday}" type="date"/>
            </td>
            <td>
                                    共享人:</td><td><s:property value="phoneBook.appUser.fullname" />
            </td>
         </tr>
	    <tr><td colspan="6"> 联系方式</td></tr>
         <tr>
          <td>手机:</td><td><s:property value="phoneBook.mobile" />
          </td>
        
         <td>QQ:</td><td><s:property value="phoneBook.qqNumber" />
          </td>
         <td>MSN:</td><td><s:property value="phoneBook.msn" />
          </td></tr>
           <tr>
          <td>Email:</td><td colspan="5"><s:property value="phoneBook.email" />
          </td>
         </tr>      
         <tr><td colspan="6">公司情况</td></tr>
         <tr>
          <td> 公司名称:</td><td colspan="5"><s:property value="phoneBook.companyName" />
          </td>
         </tr>
         <tr>
          <td>公司地址:</td><td colspan="5"><s:property value="phoneBook.companyAddress" />
          </td>
         </tr>
         <tr>
           <td> 职位:</td><td><s:property value="phoneBook.duty" />
          </td>
          <td>公司传真:</td><td><s:property value="phoneBook.companyFax" />
          </td>
           <td>公司电话:</td><td><s:property value="phoneBook.companyPhone" />
          </td>
         </tr>
         <tr><td colspan="6">家庭情况</td></tr>
          <tr><td>家庭地址:</td><td colspan="5"><s:property value="phoneBook.homeAddress" />
          </td>
         </tr>
         <tr><td>家庭邮编:</td><td><s:property value="phoneBook.homeZip" />
          </td>
          <td>配偶:</td><td><s:property value="phoneBook.spouse" />
          </td>
          <td>子女:</td><td><s:property value="phoneBook.childs" />
          </td>
         </tr>
        <tr><td colspan="6">备注</td></tr>
        <tr><td colspan="6"><s:property value="phoneBook.note" /></td></tr>
      </table>
  </body>
 </html>