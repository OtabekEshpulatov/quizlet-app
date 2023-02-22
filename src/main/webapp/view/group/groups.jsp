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
    <div class="col-lg-10 col-md-8 col-sm-6" style="margin-top: 30px">
        <table class="table" style="margin: auto;text-align: center;justify-content: center">
            <thead>
            <tr>
                <th scope="col">T/r</th>
                <th scope="col">School name</th>
                <th scope="col">Class name</th>
                <th scope="col">Members</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="i" value="${1}"/>
            <c:if test="${groups.size()>0}">
                <c:forEach items="${groups}" var="group">
                    <tr>
                        <td>${i}</td>
                        <td>${group.getSchoolName()}</td>
                        <td>${group.getName()}</td>
                        <td>
                            <a href="/group/member/remove?groupId=${group.getId()}">
                                    ${group.getUsers().size()}
                            </a>
                        </td>
                        <td>
                            <a class="btn btn-primary" href="/mygroup?gid=${group.getId()}"
                               role="button" aria-expanded="false">More</a>
                        </td>
                    </tr>
                    <c:set var="i" value="${i+1}"/>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div style="margin-left: 74.5%;margin-top: 20px">
            <a class="btn btn-success" href="/group/add" style="margin-left: 5px">Create class</a>
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