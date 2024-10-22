<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<style>
    .navbar {
        display: flex;
    }

    .navbar .button {
        padding: 5px 10px;
        border: 1px solid #989797;
    }

    .button {
        margin: 10px;
        appearance: none;
        background-color: #1A1A1A;
        text-decoration: none;
        border-radius: 5px;
        box-sizing: border-box;
        cursor: pointer;
        transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
        touch-action: manipulation;
        will-change: transform;
    }

    .button a {
        color: #fff;
        text-decoration: none;
    }

    .button:hover {
        background-color: transparent;
        box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
        transform: translateY(-2px);
    }

    .button:hover a {
        color: #3B3B3B;
    }

    .success {
        color: darkblue;
    }

    .warning {
        color: crimson;
    }

    .edited {
        color: blueviolet;
    }
</style>

<div class="navbar">
    <div class="button">
        <a href="/board/list">글 목록</a>
    </div>
    <div class="button">
        <a href="/board/new">글 작성</a>
    </div>
</div>

<c:if test="${not empty message}">
    <div class="${message.type}">
        <h5>
                ${message.text}
        </h5>
    </div>
</c:if>
