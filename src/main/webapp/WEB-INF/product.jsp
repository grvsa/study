<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--        Menu    --%>
        <%@include file="/WEB-INF/jspf/menu.jsp" %>
        <h1>Страница товара</h1>
        <h3>Товар #
        <%=
            request.getParameter("id")
        %>
        </h3>
        <%--        Footer  --%>
        <%@include file="/WEB-INF/jspf/footer.jsp"%>
    </body>
</html>
