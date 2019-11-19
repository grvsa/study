<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--        Menu    --%>
        <%@include file="/WEB-INF/jspf/menu.jsp" %>
        <h1>Каталог товаров</h1>
        <ul>
            <c:forEach var="i" begin="0" end="9" step="1">
                <li>
                      <c:url var="productUrl" value="product">
                          <c:param name="id" value="${i}"></c:param>
                    </c:url>
                    <a href="${productUrl}">Товар #${i}</a>
                </li>
            </c:forEach>
        </ul>

        <%--        Footer  --%>
        <%@include file="/WEB-INF/jspf/footer.jsp"%>
    </body>
</html>