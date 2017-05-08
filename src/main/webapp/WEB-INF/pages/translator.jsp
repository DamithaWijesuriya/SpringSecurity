
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${pageContext.request.method=='POST'}">
    <c:forEach var="item" items="${list}">
        <c:if test="${item!=param.item}">
            <c:if test="${list2!=''}">
                <c:set var="list2" value="${list2},"/>
            </c:if>
        </c:if>
    </c:forEach>
</c:if>
<html>
<head>
    <title>Translater</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            alert("test");
            /*$.ajax({
             type: "GET",
             url:"sendAllLanguages",
             dataType: "json",
             success: function (data) {
             var $el = $("#Fromlangs");
             var $tl = $("#Tolangs");
             $el.empty();
             $tl.empty();
             $.each(data, function(value, key) {
             $el.append($("<option></option>").attr("value", value).text(key));
             $tl.append($("<option></option>").attr("value", value).text(key));
             });
             }
             });*/
            $("button").click(function(e) {
                var fromLang =$("#fromLanguage").val();
                var toLang =$("#toLanguage").val();
                var textToTranslate =$("#textToTranslate").val();
                $.ajax({
                    type: "GET",
                    url:"convert",
                    data: {
                        fromLang: fromLang,
                        toLang: toLang,
                        text: textToTranslate
                    },
                    dataType: "json",
                    success: function (reply) {
                        $("#translated").val("");
                        var input = $("#translated");
                        input.val(input.val() + reply.text);
                    }
                });
            });
        });
    </script>
</head>



<body>
<table>
    <tr>
        <td>InputText:</td>
        <td><input type='text' name='InputText'></td>
    </tr>
    <tr>
        <td>OutPutText:</td>
        <td><input type='text' id="translated" name='OutPutText'/></td>
    </tr>
    <tr>
        <td colspan='2'><button>Translate</button></td>
    </tr>
    <select name="fromLanguage">
        <c:forEach items="${languages}" var="language">
            <option value="<c:out value="${language.key}" />"><c:out value="${language.value}"/></option>
        </c:forEach>
    </select>
    <select name="toLanguage">
        <c:forEach items="${languages}" var="languages">
            <option value="<c:out value="${languages.key}" />"><c:out value="${languages.value}"/></option>
        </c:forEach>
    </select>


</table>
</body>
</html>
