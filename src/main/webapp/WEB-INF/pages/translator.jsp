<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 5/3/17
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${pageContext.request.method=='POST'}">
    <c:forEach var="item" items="${list}">
        <c:if test="${item!=param.item}">
            <c:if test="${list2!=''}">
                <c:set var="list2" value="${list2}," />
            </c:if>
            </c:if>
    </c:forEach>
    </c:if>
<html>
<head>
    <title>Translater</title>
</head>
<body>
<table>
    <tr>
        <td>InputText:</td>
        <td><input type='text' name='InputText'></td>
    </tr>
    <tr>
        <td>OutPutText:</td>
        <td><input type='text' name='OutPutText' /></td>
    </tr>
    <tr>
        <td colspan='2'><input name="submit" type="submit"
                               value="submit" /></td>
    </tr>
    <select name="toLanguage">
        <c:forEach items="${languages}" var="language">
            <option value="<c:out value="${language}" />"><c:out value="${language}"/></option>
        </c:forEach>
    </select>


</table>
</body>
</html>
