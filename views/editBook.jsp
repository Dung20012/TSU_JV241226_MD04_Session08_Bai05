<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Sửa Sách</title></head>
<body>
<h2>Sửa Sách</h2>

<form:form modelAttribute="book" method="post">
  <form:hidden path="id"/>

  <p>Tên sách: <form:input path="title"/></p>
  <p>Tác giả: <form:input path="author"/></p>
  <p>Giá: <form:input path="price"/></p>

  <p>
    <button type="submit">Lưu</button>
    <a href="${pageContext.request.contextPath}/books">Hủy</a>
  </p>
</form:form>

</body>
</html>
