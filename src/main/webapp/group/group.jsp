<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Class</title>
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
    <%--          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
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
            <%--            <%int i = 0;%>--%>
            <c:if test="${classes.size()>0}">
                <c:forEach items="${classes}" var="group">
                    <tr>
                        <td>1</td>
                        <td>${group.getSchoolName()}</td>
                        <td>${group.getName()}</td>
                        <td>${group.getUsers().size()+1}</td>
                        <td>
                            <a href="/group/update?groupId=${group.getId()}" class="btn btn-warning">
                                Update
                            </a>
                            ||
                            <a href="/group/delete?groupId=${group.getId()}" class="btn btn-danger">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div style="margin-left: 80%;margin-top: 20px">
            <a class="btn btn-success" href="#">List add</a>
            <a class="btn btn-success" href="/group/member/add">Add member</a>
            <a class="btn btn-success" href="/group/add">Create class</a>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
