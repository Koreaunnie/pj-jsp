<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<style>
    .navbar {
        display: flex;
    }

    .navbar div {
        padding: 5px 10px;
        border: 1px solid #000;
    }

    .navbar div:nth-child(1) {
        border-radius: 10px 0 0 10px;
    }

    .navbar div:nth-child(2) {
        border-radius: 0 10px 10px 0;
    }

    .navbar div a {
        text-decoration: none;
    }
</style>

<div class="navbar">
    <div>
        <a href="/board/list">글 목록</a>
    </div>
    <div>
        <a href="/board/new">글 작성</a>
    </div>
</div>
