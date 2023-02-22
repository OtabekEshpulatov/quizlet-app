<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: elshod
  Date: 22/02/23
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/utils/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<form method="post" action="/class/module/add">--%>
<%--    <input type="hidden" name="groupId" value="${groupId}">--%>
<div class="container col-md-8 col-lg-10 col-sm-6">
    <c:forEach items="${modules}" var="module">
        <div class="col-lg-4 col-md-5 col-sm-8" style="margin-top: 5px">
            <div class="card" style="height: 200px; width: 200px">
                <div class="card-header">
                        ${module.getName()}
                </div>
                <div class="card-body">
                    <p class="card-text">${module.getDescription()}</p>
                    <a href="/class/module/add?groupId=${groupId}&moduleId=${module.getId()}"
                       class="btn btn-outline-info">Add</a>
<%--                    <c:if test="${isUpdateAble} || ${isOwner}">--%>
<%--                        <a href="/group/module/remove?groupId=${groupId}&moduleId=${module.getId()}"--%>
<%--                           class="btn btn-success m-3">--%>
<%--                            Remove--%>
<%--                        </a>--%>
<%--                    </c:if>--%>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<%--</form>--%>
</body>
</html>
