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

<div class="col-lg-10 col-md-8 col-sm-6" style="margin-top: 30px">
    <table class="table">
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
        <%int i = 0;%>
        <c:forEach items="${classes}" var="class">
            <tr>
                <th scope="row"><%=i++%>
                </th>
                <td>${class.getSchoolName()}</td>
                <td>${class.getClassName()}</td>
                <td>${class.getUsers().size()}</td>
                <td>
                    <a href="/class/update?classId=${class.getId()}" class="btn btn-warning">
                        Update
                    </a>
                    ||
                    <a href="/class/delete?classId=${class.getId()}" class="btn btn-danger">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-success" href="/class/add">Create class</a>--%>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
