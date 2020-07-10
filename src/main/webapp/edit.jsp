<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JWT测试Demon</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">

        /*function login(){
            var userName = $("#userName").val();
            var passWord = $("#passWord").val();
            var params = "userName="+userName+"&passWord="+passWord;
            //debugger
            $.ajax({
                'url' : '${pageContext.request.contextPath }/login?'+params,
		'success' : function(data){

			if(data.code == 200){
				var token = data.token;
				// local storage - 本地存储的数据。 长期有效的。
				// session storage - 会话存储的数据。 一次会话有效。
				var localStorage = window.localStorage; // 浏览器提供的存储空间。 根据key-value存储数据。
                //var localStorage = window.sessionStorage;
				localStorage.token = token;
                var userId = data.userId;
                debugger
                location.href= '${pageContext.request.contextPath }/okPage?'+userId;
				//debugger
				//alert(data.msg);
			}else{
				alert(data.msg);
			}
		}
	});
}

function setHeader(xhr){ // XmlHttpRequest
	xhr.setRequestHeader("Authorization",window.localStorage.token);
}

function testLocalStorage(){
	$.ajax({
		'url' : '${pageContext.request.contextPath}/testAll',
        'success' : function(data){
			if(data.code == 200){
				window.localStorage.token = data.token;
				//window.open("ok.jsp")
                location.href='${pageContext.request.contextPath }/ok';
				alert(data.data);
			}else{
				alert(data.msg);
			}
		},
        //debugger
    'beforeSend' : setHeader
	});
}*/

    </script>
</head>
<body >
<form action="/toInsert" method="post">
    <input type="hidden" name="userId" id="userId" value="${userId}">
    <center>
        <table>
            <caption>增加活动</caption>
            <tr>
                <td style="text-align: right; padding-right: 5px">
                    活动名：
                </td>
                <td style="text-align: left; padding-left: 5px">
                    <input type="text" name="activeName" id="activeName"/>
                </td>
            </tr>
            <tr>
                <td style="text-align: right; padding-right: 5px">
                    活动地点:
                </td>
                <td style="text-align: left; padding-left: 5px">
                    <input type="text" name="activeAddress" id="activeAddress"/>
                </td>
            </tr>
            <tr>

                <td style="text-align: right; padding-right: 5px" colspan="2">
                    <input type="submit" value="增加活动"/>
                </td>
            </tr>
        </table>
    </center>
</form>

</body>
</html>