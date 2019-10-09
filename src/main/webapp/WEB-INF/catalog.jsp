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
            <%for (int i = 0; i < 9; i++) { %>
                <li>
                      <c:url var="productUrl" value="product">
                          <c:param name="id" value=""></c:param>
                    </c:url>
                    <a href="${productUrl}<%=i%>">Товар #<%=i%></a>
                </li>
            <% } %>
        </ul>

        <%--        Footer  --%>
        <%@include file="/WEB-INF/jspf/footer.jsp"%>
    </body>
</html>