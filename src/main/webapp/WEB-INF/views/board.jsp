<%--
  Created by IntelliJ IDEA.
  User: yeop
  Date: 2022/04/09
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>BOARD</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/reset.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
    <div class="header">
        <div class="logo">LOGO</div>
        <div class="navi">
            <a class="home navi_contents" href='<c:url value="/"/>'><div>HOME</div></a>
            <a class="board navi_contents active" href='<c:url value="/board/list"/>'><div>BOARD</div></a>
            <a class="login navi_contents" href="#"><div>LOGIN</div></a>
        </div>
    </div>
    <div class="mainContainer">
        <div class="titleContainer">
            <h1 class="title bold">${boardVO.action=="MOD" ? "글 수정" : (boardVO.action=="WRT" ? "글 작성" : "게시물")}</h1>
        </div>
        <form id="form">
            <div class="contentsContainer">
                <ul>
                    <li style="display: none">
                        <input type="text" name="bno" value="${boardVO.bno}"/>
                        <input type="text" name="page" value="${boardVO.page}"/>
                        <input type="text" name="pageSize" value="${boardVO.pageSize}"/>
                        <input type="text" id="actionInput" name="action" value="${boardVO.action}"/>
                    </li>
                    <li>
                        <p>제목</p>
                        <input type="text" id="title" name="title" value="${boardVO.title}" />
                    </li>
                    <li>
                        <p>내용</p>
                        <textarea id="content" name="content" >${boardVO.content}</textarea>
                    </li>
                    <li class="buttonContainer">
                        <button type="button" id="list">목록</button>
                        <button type="button" id="write">등록</button>
                        <c:if test='${boardVO.action=="MOD"}'>
                            <button type="button" id="delete">삭제</button>
                        </c:if>
                    </li>
                </ul>
            </div>
        </form>
    </div>
    <script>
        $(document).ready(function(){
            const msg = '${message}';
            if(msg=="MOD_ERR") alert("수정 도중 에러가 발생하였습니다.");
            if(msg=="MOD_OK") alert("성공적으로 수정되었습니다.");

            $("#write").on("click", function(){
                const title = $("#title").val().trim();
                const content = $("#content").val().trim();
                if(title == "" || content == "") {
                    alert("제목 혹은 본문 내용은 필수입니다.");
                    return;
                }
                let form = $("#form");
                form.attr('action', '<c:url value="/board/proc"/>');
                form.attr('method', 'post');
                form.submit();
            })

            $("#list").on("click", function(){
                location.href='<c:url value="/board/list?page=${boardVO.page}&pageSize=${boardVO.pageSize}"/>';
            })

            $("#delete").on("click", function(){
                let form = $("#form");
                if(confirm("정말로 삭제하시겠습니까?")){
                    $("#actionInput").val("DEL");
                    form.attr('action', '<c:url value="/board/proc"/>');
                    form.attr('method', 'post');
                    form.submit();
                }
            })
        })
    </script>
</body>
</html>
