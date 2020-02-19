<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>All Products</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
</head>

<body>
    <form:form action="addproduct" modelAttribute="product">
        <legend>Характеристики продукта</legend>
        Title: <form:input path="title" />
        <br>
        Price: <form:input path="price" />
        <br>
        <input type="submit" value="Submit">
    </form:form>
</body>
</html>