<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>게시판 > 게시글</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>

<body>
<c:import url="/WEB-INF/fragment/navbar.jsp"/>

<%-- 수정 / 삭제 권한 --%>
<c:set value="${sessionScope.loggedInMember.id == board.writer}" var="hashAccess"/>


<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-md-9 col-lg-6">

            <h2 class="my-3">${board.id}번 게시글</h2>

            <div class="mb-3">
                <label for="inputTitle1" class="form-label">제목</label>
                <input id="inputTitle1" class="form-control" type="text" value="${board.title}" readonly>
            </div>

            <div class="mb-3">
                <label for="textareaContent1" class="form-label">본문</label>
                <textarea class="form-control" id="textareaContent1" rows="10" readonly>${board.content}</textarea>
            </div>

            <div class="mb-3">
                <label for="inputWriter1" class="form-label">작성자</label>
                <input id="inputWriter1" class="form-control" type="text" value="${board.writer}" readonly>
            </div>

            <div class="mb-3">
                <label for="inputDate1" class="form-label">작성일시</label>
                <input id="inputDate1" class="form-control" type="text" value="${board.inserted}" readonly>
            </div>

            <c:if test="${hashAccess}">
                <button class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal1">
                    <i class="fa-solid fa-trash-can"></i>
                    삭제
                </button>
            </c:if>

            <button class="btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#editConfirmModal1">
                <i class="fa-solid fa-pen"></i>
                수정
            </button>


            <c:if test="${hashAccess}">
                <form id="deleteForm1" action="/board/delete" method="post">
                    <input type="hidden" name="id" value="${board.id}">
                </form>
            </c:if>
        </div>
    </div>
</div>

<!-- 삭제 button modal -->
<c:if test="${hashAccess}">
    <div class="modal fade" id="deleteConfirmModal1" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">삭제 확인</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                        ${board.id}번 게시물을 삭제하시겠습니까?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
                    <button form="deleteForm1" class="btn btn-danger">
                        <i class="fa-solid fa-trash-can"></i>
                        삭제
                    </button>
                </div>
            </div>
        </div>
    </div>
</c:if>

<!-- 수정 button modal -->
<div class="modal fade" id="editConfirmModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">수정 확인</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ${board.id}번 게시물을 수정하시겠습니까?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
                <a class="btn btn-dark" href="/board/edit?id=${board.id}" type="button">
                    <i class="fa-solid fa-pen"></i>
                    수정
                </a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
</body>
</html>