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
            <input type="hidden" name="bno" value="${boardVO.bno}"/>
            <input type="hidden" name="page" value="${boardVO.page}"/>
            <input type="hidden" name="pageSize" value="${boardVO.pageSize}"/>
            <input type="hidden" id="actionInput" name="action" value="${boardVO.action == "DEL" ? "MOD" : boardVO.action}"/>
            <input type="hidden" id="title" name="title" value="${boardVO.boardDTO.title}" />
            <input type="hidden" id="content" name="content" value="${boardVO.boardDTO.content}" />
        </div>
    </form>
    <script>
        $(document).ready(function(){

            // #####
            // # 변수 선언
            // #####

            // 경로 이동을 위한 form
            const form = $("#form");
            // action 값 가져오기
            const action = '${boardVO.action}';
            // msg 값 가져오기
            const msg = '${boardVO.msg}';
            // 유효성 검사
            if(msg == ""){
                alert("잘못된 접근입니다.");
                location.href="<c:url value='/'/>";
            }
            // code 가져오기 ex) ERR_NoBoard -> NoBoard
            const code = msg.substring(4);
            // result 가져오기 ex) ERR_NoBoard -> ERR_
            const result = msg.substring(0, 4);
            // url을 담기 위한 변수
            let url;

            // action 명시
            const action_type = {
                "WRT" : "등록",
                "MOD" : "수정",
                "DEL" : "삭제",
                "REP" : "답글 등록"
            }
            // err 메시지 명시
            const err_msg = {
                "Action" : "도중 에러가 발생하였습니다.",
                "NoBoard" : "존재하지 않는 게시물입니다.",
                "HaveRep" : "답글이 있는 경우 삭제할 수 없습니다.",
                "Path" : "올바른 경로로 접근하세요"
            }
            // alert 작업
            // 성공코드일 시
            if(result == "SUC_"){
                alert("성공적으로 " + action_type[action] + "되었습니다.");
            // 에러코드일 시
            }else{
                // result가 action중 한개일 시
                if(result in action_type){
                    alert(action_type[result] + err_msg["Action"]);
                // 이외 에러일 시
                }else{
                    alert(err_msg[code]);
                }
            }
            // url 작업
            // 코드가 ERR일 시
            if(code == "ERR_"){
                // NoBoard일 시
                if(result == "NoBoard"){
                    url = '<c:url value="/board/list"/>?page=${boardVO.page}&pageSize=${boardVO.pageSize}';
                // Path일 시
                }else if(result == "Path"){
                    url = "<c:url value='/'/>";
                // Action 혹은 HaveRep인 경우
                }else{
                    url = '<c:url value="/board/write"/>';
                }
            // 코드가 SUC일 시
            }else{
                // WRT일 시
                if(result == "WRT"){
                    url = "<c:url value='/board/list'/>";
                // MOD일 시
                }else if(result == "MOD"){
                    url = '<c:url value="/board/write"/>';
                // DEL, REP일 시
                }else{
                    url = '<c:url value="/board/list"/>?page=${boardVO.page}&pageSize=${boardVO.pageSize}';
                }
            }
            location.
            // GET요청
            form.attr('action', url);
            form.attr('method', 'GET');
            form.submit();
        })
    </script>
</body>
</html>
