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
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
    <form id="form" style="display: none">
        <div class="contentsContainer">
            <ul>
                <li style="display: none">
                    <input type="text" name="bno" value="${boardVO.bno}"/>
                    <input type="text" name="page" value="${boardVO.page}"/>
                    <input type="text" name="pageSize" value="${boardVO.pageSize}"/>
                    <input type="text" id="actionInput" name="action" value="${boardVO.action == "DEL" ? "MOD" : boardVO.action}"/>
                </li>
                <li>
                    <p>제목</p>
                    <input type="text" id="title" name="title" value="${boardVO.title}" />
                </li>
                <li>
                    <p>내용</p>
                    <textarea id="content" name="content" >${boardVO.content}</textarea>
                </li>
            </ul>
        </div>
    </form>
    <script>
        $(document).ready(function(){
            const msg = '${message}';

            if(msg=="WRT_OK"){
                alert("성공적으로 등록되었습니다.");
                location.href="<c:url value='/board/list'/>";
                return;
            }
            if(msg=="WRT_ERR") alert("등록 도중 에러가 발생하였습니다.");

            if(msg=="MOD_OK") alert("성공적으로 수정되었습니다.");

            if(msg=="MOD_ERR") alert("수정 도중 에러가 발생하였습니다.");

            if(msg=="DEL_OK"){
                alert("성공적으로 삭제되었습니다.");
                location.href='<c:url value="/board/list"/>?page=${boardVO.page}&pageSize=${boardVO.pageSize}';
                return;
            }

            if(msg=="DEL_ERR") alert("삭제 도중 에러가 발생하였습니다.");

            if(msg=="ERR_NOBOARD"){
                alert("존재하지 않는 게시물입니다.");
                location.href='<c:url value="/board/list"/>?page=${boardVO.page}&pageSize=${boardVO.pageSize}';
                return;
            }

            let form = $("#form");
            form.attr('action', '<c:url value="/board/write"/>');
            form.attr('method', 'GET');
            form.submit();
        })
    </script>
</body>
</html>
