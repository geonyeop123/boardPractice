<%--
  Created by IntelliJ IDEA.
  User: yeop
  Date: 2022/04/09
  Time: 15:58
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
            <h1 class="title">게시판</h1>
<%--            <div class="searchContainer">--%>
<%--                <select>--%>
<%--                    <option value="A">제목 + 내용</option>--%>
<%--                    <option value="T">제목</option>--%>
<%--                    <option value="C">내용</option>--%>
<%--                </select>--%>
<%--            </div>--%>
            <div class="buttonContainer">
                <button id="write_btn" type="button">글쓰기</button>
            </div>
        </div>
        <div class="contentsContainer">
            <table class="board_table">
                <thead>
                <tr>
                    <th scope="cols">번호</th>
                    <th scope="cols">제목</th>
                    <th scope="cols">작성자</th>
                    <th scope="cols">작성일</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${boardVOList}" var="list">
                    <tr>
                        <th scope="row">${list.bno}</th>
                        <td class="table_title">
                            <a href='<c:url value="/board/write?page=${boardVOList.get(0).pageMaker.page}&pageSize=${boardVOList.get(0).pageMaker.pageSize}&bno=${list.bno}&action=MOD"/>'>
                                <c:if test="${list.step != 0}"><span class="reply_tag">${list.replyTag}Re :</span></c:if>
                                    ${list.title}
                            </a>
                        </td>
                        <td>${list.writer}</td>
                        <td><fmt:formatDate pattern="yyyy.mm.dd hh:mm" value="${list.regdate}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${boardVOList == null}">
                <div>게시물이 없습니다.</div>
            </c:if>
            <div class="page_wrap">
                <div class="page_nation">
                    <c:if test="${boardVOList.get(0).pageMaker.prev}">
                        <a class="arrow prev" href="/board/list?page=${boardVOList.get(0).pageMaker.startPage - 1}&pageSize=${boardVOList.get(0).pageMaker.pageSize}">&lt;</a>
                    </c:if>
                        <c:forEach var="i" begin="${boardVOList.get(0).pageMaker.startPage}" end="${boardVOList.get(0).pageMaker.endPage}">
                            <a class="${boardVOList.get(0).pageMaker.page == i ? "active" : ""}"href="/board/list?page=${i}&pageSize=${boardVOList.get(0).pageMaker.pageSize}">${i}</a>
                        </c:forEach>
                    <c:if test="${boardVOList.get(0).pageMaker.next}">
                        <a class="arrow next" href="/board/list?page=${boardVOList.get(0).pageMaker.endPage + 1}&pageSize=${boardVOList.get(0).pageMaker.pageSize}">&gt;</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function(){

            $("#write_btn").on("click",function(){
                location.href='<c:url value="/board/write?page=${boardVOList.get(0).pageMaker.page}&pageSize=${boardVOList.get(0).pageMaker.pageSize}&action=WRT"/>';
            })
        })
    </script>
</body>
</html>
