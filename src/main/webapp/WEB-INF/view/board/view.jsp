<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>게시판 > 게시글</title>

    <style>
        .buttonWrap {
            display: flex;
        }

        button {
            margin: 10px;
            padding: 5px 10px;
            background-color: #1A1A1A;
            color: #fff;
            border: 1px solid #989797;
            text-decoration: none;
            border-radius: 5px;
            box-sizing: border-box;
            cursor: pointer;
            transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
        }

        button a {
            color: #fff;
            text-decoration: none;
        }

        button:hover {
            background-color: transparent;
            color: #3B3B3B;
            box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
            transform: translateY(-2px);
        }

        button:hover a {
            color: #3B3B3B;
        }
    </style>
</head>

<body>
<c:import url="/WEB-INF/fragment/navbar.jsp"/>
<h2>${board.id}번 게시글</h2>

<div>제목
    <input type="text" value="${board.title}" readonly>
</div>
<div>본문
    <textarea id="" cols="30" rows="10" readonly>${board.content}</textarea>
</div>
<div>작성자
    <input type="text" value="${board.writer}" readonly>
</div>
<div>작성일시
    <input type="text" value="${board.inserted}" readonly>
</div>

<div class="buttonWrap">
    <div>
        <form action="/board/delete" method="post">
            <input type="hidden" name="id" value="${board.id}">
            <button>삭제</button>
        </form>
    </div>

    <div>
        <button>
            <a href="/board/edit?id=${board.id}" type="button">수정</a>
        </button>
    </div>
</div>

</body>
</html>
