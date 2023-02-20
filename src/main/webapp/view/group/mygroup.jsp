<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mygroup</title>
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
    <%--          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">--%>
    <%--    <link rel="stylesheet" href="/css/bootstrap.min.css">--%>
    <%@ include file="/utils/header.jsp" %>
</head>
<body>


<div class="container" style="margin-top: 20px">

    <%--            <p>layer3</p>--%>
    <%--            <div class="collapse multi-collapse" id="multiCollapseExample1">--%>

    <td>${i}</td>
    <td>${group.getSchoolName()}</td>
    <td>${group.getName()}</td>
    <td>${group.getUsers().size()+1}</td>

    <div class="card card-body">
        <a href="/group/update?groupId=${group.getId()}" class="btn btn-warning m-3">
            Update
        </a>
        <a href="/group/delete?groupId=${group.getId()}" class="btn btn-danger m-3">
            Delete
        </a>
        <a href="/group/member?groupId=${group.getId()}" class="btn btn-success m-3">
            Add member
        </a>
        <a href="/group/set?groupId=${group.getId()}" class="btn btn-success m-3">
            Add/Remove Set
        </a>
    </div>
</div>

</body>
</html>
<%--<%@ include file="/fragments/js.jsp" %>--%>