<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/7/9
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h1>用户:${userName}的活动列表如下:</h1>
        <table>
                <tr>
                    <td>活动编号</td>
                    <td>活动名称</td>
                    <td>活动地点</td>
                </tr>
                <c:forEach items="${listActive}" var="active">
                    <tr>
                        <td>${active.activeId}</td>
                        <td>${active.activeName}</td>
                        <td>${active.activeAddress}</td>
                    </tr>
                </c:forEach>
        </table>
</body>
</html>
