<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Thêm sách</title>
</head>
<body>
<h2>Thêm sách mới</h2>

<form:form action="/books/add" method="post" modelAttribute="book">
  <p>Tiêu đề: <form:input path="title"/></p>
  <p>Tác giả: <form:input path="author"/></p>
  <p>Giá: <form:input path="price" type="number" step="0.01"/></p>
  <p>Danh mục:
    <form:select path="category.id">
      <form:options items="${categories}" itemValue="id" itemLabel="cateName"/>
    </form:select>
  </p>
  <p><input type="submit" value="Thêm sách"/></p>
</form:form>

<a href="/books">Quay lại</a>
</body>
</html>
