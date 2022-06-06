<%--
  Created by IntelliJ IDEA.
  User: yeop
  Date: 2022/06/06
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .fileDrop{
            width:100%;
            height:200px;
            border : 1px dotted blue;
        }
        small{
            margin-left : 3px;
            font-weight : bold;
            color : gray;
        }
    </style>
</head>
<body>
    <h3> AJAX File Upload </h3>
    <div class="fileDrop"></div>

    <div class="uploadedList"></div>

    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script>
        $(".fileDrop").on("dragenter dragover", function(event){
            event.preventDefault();
        });

        $(".fileDrop").on("drop", function(event){
            event.preventDefault();

            let files = event.originalEvent.dataTransfer.files;

            let file = files[0];

            let formData = new FormData();
            formData.append("file", file);

            $.ajax({
                url : "/uploadAjax",
                data : formData,
                dataType : 'text',
                processData : false,
                contentType : false,
                type : "POST",
                success : function(data){
                    let str = "";

                    if(checkImageType(data)){
                        str = "<div>"
                            + "<img src='displayFile?fileName="+data+"'/>" + data
                            + "</div>";
                    }else{
                        str = "<div>"
                            + data +
                            + "</div>";
                    }
                    $(".uploadedList").append(str);
                }
            });
        });

        function checkImageType(fileName){
            let pattern = /jpg$|gif$png$|jpeg$|i;

            return fileName.match(pattern);
        }
    </script>
</body>
</html>
