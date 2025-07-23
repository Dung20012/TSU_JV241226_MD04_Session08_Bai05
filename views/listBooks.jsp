<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Danh sách sách</title>
</head>
<body>
<h2>Danh sách sách</h2>

<form action="/books" method="get">
    Tìm kiếm: <input type="text" name="search" value="${search}"/>
    Sắp xếp:
    <select name="sort">
        <option value="ASC" ${sort == 'ASC' ? 'selected' : ''}>Giá tăng dần</option>
        <option value="DESC" ${sort == 'DESC' ? 'selected' : ''}>Giá giảm dần</option>
    </select>
    <button type="submit">Tìm</button>
</form>

<table border="1">
    <tr>
        <th>Tiêu đề</th>
        <th>Tác giả</th>
        <th>Giá</th>
        <th>Danh mục</th>
    </tr>
    <c:forEach items="${books}" var="b">
        <tr>
            <td>${b.id}</td>
            <td>${b.title}</td>
            <td>${b.author}</td>
            <td>${b.price}</td>
            <td>${b.category.cateName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/books/${b.id}/edit">Sửa</a> |
                <a href="${pageContext.request.contextPath}/books/${b.id}/delete"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Phân trang -->
<div>
    <c:forEach begin="1" end="${totalPages}" var="i">
        <a href="?page=${i}&search=${search}&sort=${sort}">${i}</a>
    </c:forEach>
</div>

<a href="/books/add">Thêm sách mới</a>
</body>
</html>
