<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>

    <style>
        button {
            margin: 10px;
            padding: 5px 10px;
            appearance: none;
            background-color: #1A1A1A;
            border: 1px solid #989797;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            box-sizing: border-box;
            cursor: pointer;
            transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
        }

        button:hover {
            background-color: transparent;
            color: #3B3B3B;
            box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
            transform: translateY(-1px);
        }
    </style>
</head>

<body>
<c:import url="/WEB-INF/fragment/navbar.jsp"/>

<h2>${board.id}번 게시물 수정</h2>

<form method="post">
    <div>제목
        <input type="text" name="title" value="${board.title}">
    </div>
    <div>본문
        <textarea name="content" id="" cols="30" rows="10">${board.content}</textarea>
    </div>
    <div>작성자
        <input type="text" name="writer" value="${board.writer}">
    </div>
    <div>
        <button>저장</button>
    </div>
</form>
</body>
</html>
