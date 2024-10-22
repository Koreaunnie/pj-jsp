<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<style>
    .paging {
        text-align: center;
        margin: 20px auto;
        width: 70%;
        border: 1px solid blue;
    }

    .paging a {
        text-decoration: none;
        border: 1px solid red;
    }

    .active {
        text-decoration-line: underline;
        font-weight: bold;
    }
</style>

<div class="paging">
    <c:if test="${currentPage > 1}">
        <a href="/board/list?page=1">&lt&lt 처음</a>
    </c:if>

    <c:if test="${not empty prevPage}">
        <a href="/board/list?page=${prevPage}">&lt 이전</a>
    </c:if>

    <c:forEach begin="${beginPage}" end="${endPage}" var="pageNumber">
        <span class="${currentPage == pageNumber ? 'active' : ''}">
            <a href="/board/list?page=${pageNumber}">${pageNumber}</a>
        </span>
    </c:forEach>

    <c:if test="${not empty nextPage}">
        <a href="/board/list?page=${nextPage}">다음 &gt</a>
    </c:if>

    <c:if test="${currentPage < lastPage}">
        <a href="/board/list?page=${lastPage}">끝 &gt&gt</a>
    </c:if>
</div>