<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JWT测试Demon</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        function doQuery(){
            document.forms[0].action="${ctxPath}/showActive";
            document.forms[0].submit();
        }
        function doInsert(activeId){
            console.log(activeId);
            document.forms[0].action = "${ctxPath}/doInsert"
            document.forms[0].submit();
        }
        function doDelete(activeId){
            console.log(activeId);
            if(window.confirm('你确定要删除吗')){
                $.ajax({
                    url : "${ctxPath}/doDelete",
                    type : "post",
                    data : {
                        "activeId" : activeId
                    },
                    dataType : "json",
                    success : function(data) {
                        debugger
                        if (data.code==200) {
                            alert(data.msg);
                            window.location.reload();
                            //goBack();
                        } else {
                            myAlertWarn(data.msg);
                        }
                        unblockUI(); //关闭遮罩
                    }
                });
            }

        }


    </script>
</head>
<body >
<form  method="post">
    <input type="hidden" name="userName" id="userName" value="${ltUser.userName}">
    <input type="hidden" name="userId" id="userId" value="${ltUser.userId}">
    <input type="hidden" name="passWord" id="passWord" value="${ltUser.passWord}">
    <input type="button" value="查询" onclick="doQuery()">
    <input type="button" value="增加" onclick="doInsert()">
    <%--<form action="/showActive" method="post">
        <center>
            <table>
                    <input type="hidden" name="userId" id="userId" value="${ltUser.userId}">
                     <input type="hidden" name="userName" id="userName" value="${ltUser.userName}">
                    活动名称:<input type="text" name="activeName" id="activeName">
                    活动地址:<input type="text" name="activeAddress" id="activeAddress">
            </table>
            <input type="submit" value="查询">
        </center>
    </form>--%>

    <h1>用户:${userName}的活动列表如下:</h1>
    <table border="1" cellspacing="0" cellpadding="0" >
        <tr>
            <td>活动编号</td>
            <td>活动名称</td>
            <td>活动地点</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${listActive}" var="active">
            <tr>
                <td>${active.activeId}</td>
                <td>${active.activeName}</td>
                <td>${active.activeAddress}</td>
                <td>
                    <a href="javascript:void(0)" onclick="doDelete('${active.activeId}')" class="list-btn-4">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</form>
</body>
</html>