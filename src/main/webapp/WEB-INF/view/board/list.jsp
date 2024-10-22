<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>게시판 > 목록</title>

    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            text-align: center;
        }

        tr, th, td {
            border: 1px solid #000;
        }

        th:nth-child(2) {
            width: 50%;
        }
    </style>
</head>

<body>

<c:import url="/WEB-INF/fragment/navbar.jsp"/>

<h2>게시물 목록</h2>
<table>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일시</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${boardList}" var="board">
        <tr>
            <td>${board.id}</td>
            <td>
                <a href="/board/view?id=${board.id}">
                        ${board.title}
                </a>
            </td>
            <td>${board.writer}</td>
            <td>${board.inserted}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:import url="/WEB-INF/fragment/paging.jsp"/>
</body>
</html>