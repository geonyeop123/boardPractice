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
    <script>
        $(document).ready(function(){
            // #####
            // # 변수 선언
            // #####

            const action_type = {
                "WRT" : "등록",
                "MOD" : "수정",
                "DEL" : "삭제",
                "REP" : "답글 등록"
            }
            const action = '${boardVO.action}';
            const msg = '${boardVO.msg}';
            // ERR_NoBoard -> NoBoard
            const code_name = msg.substring(4);
            // ERR_NoBoard -> ERR
            const code_type = msg.substring(0, 3);

            // 유효성 검사
            if(msg == "" || action == ""){
                alert("잘못된 접근입니다.");
                location.href="<c:url value='/'/>";
                return;
            }

            // 코드가 ERR일 시
            if(code_type == "ERR"){
                if(code_name == "NoBoard"){
                    alert("존재하지 않는 게시물입니다.");
                    location.href ='<c:url value="/board/list"/>?page=${boardVO.page}&pageSize=${boardVO.pageSize}';
                }else if(code_name == "Path"){
                    alert("올바른 경로로 접근하세요.");
                    location.href = "<c:url value='/'/>";
                }else if(code_name == "HaveRep"){
                    alert("답글이 있는 경우 삭제할 수 없습니다.");
                    location.href = '<c:url value="/board/write"/>?page=${boardVO.page}&pageSize=${boardVO.pageSize}&bno=${boardVO.bno}&action=MOD';
                // action 에러인 경우
                }else{
                    alert(action_type[code_name] + "도중 에러가 발생했습니다.");
                    location.href='<c:url value="/board/write"/>?page=${boardVO.page}&pageSize=${boardVO.pageSize}&bno=${boardVO.bno}&title=${boardVO.title}&content=${boardVO.content}&action=${boardVO.action == "DEL" ? "MOD" : boardVO.action}';
                }
            // 코드가 SUC일 시
            }else{
                alert("성공적으로 " + action_type[code_name] + "되었습니다.");
                // WRT일 시
                if(code_name == "WRT"){
                    location.href = "<c:url value='/board/list'/>";
                // MOD일 시
                }else if(code_name == "MOD"){
                    location.href = '<c:url value="/board/write"/>?page=${boardVO.page}&pageSize=${boardVO.pageSize}&bno=${boardVO.bno}&action=MOD';
                // DEL, REP일 시
                }else{
                    location.href = '<c:url value="/board/list"/>?page=${boardVO.page}&pageSize=${boardVO.pageSize}';
                }
            }
        })
    </script>
</body>
</html>
