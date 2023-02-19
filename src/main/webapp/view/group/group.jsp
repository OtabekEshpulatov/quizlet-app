<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Class</title>
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
    <%--          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">--%>
    <%--    <link rel="stylesheet" href="/css/bootstrap.min.css">--%>
    <%@ include file="/utils/header.jsp" %>
</head>
<body>

<div class="container" style="margin-top: 20px">


    <div class="row">
        <div class="col">
            <div class="collapse multi-collapse" id="multiCollapseExample1">
                <div class="card card-body">
                    <a href="/group/update?groupId=${group.getId()}" class="btn btn-warning">
                        Update
                    </a>
                    <a href="/group/delete?groupId=${group.getId()}" class="btn btn-danger">
                        Delete
                    </a>
                    <a href="/group/member?groupId=${group.getId()}" class="btn btn-success">
                        Add member
                    </a>
                    <a href="/group/set?groupId=${group.getId()}" class="btn btn-success">
                        Add/Remove Set
                    </a>


                </div>
            </div>
        </div>
    </div>
</div>
</div>

<%--<div class="row">--%>
<%--    <div class="col">--%>
<%--        <div class="collapse multi-collapse" id="multiCollapseExample1">--%>
<%--            <div class="card card-body">--%>
<%--                <a href="/group/update?groupId=${group.getId()}" class="btn btn-warning">--%>
<%--                    Update--%>
<%--                </a>--%>
<%--                <a href="/group/delete?groupId=${group.getId()}" class="btn btn-danger">--%>
<%--                    Delete--%>
<%--                </a>--%>
<%--                <a href="/group/member?groupId=${group.getId()}" class="btn btn-success">--%>
<%--                    Add member--%>
<%--                </a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"--%>
<%--        crossorigin="anonymous"></script>--%>
</body>
</html>
<%--<%@ include file="/fragments/js.jsp" %>--%>