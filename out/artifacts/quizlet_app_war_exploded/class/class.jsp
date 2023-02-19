<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Class</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container col-lg-10 col-md-8 col-sm-6" style="margin-top: 5%">
    <div class="row g-3 align-items-center">
        <h1>Join or create class</h1>
        <div class="col-auto">
            <form method="post" action="/class">
                <input type="search" id="inputSearch" class="form-control" aria-describedby="searchHelpInline"
                       style="display: inline-block" name="search">
                <i style="margin-top:10px">Search by school name or class name</i>
                <button class="btn btn-secondary" type="submit" style="margin-top: 10px;margin-left: 50px">
                    Search
                </button>
            </form>
            <a href="/class/add">Create class</a>
        </div>
    </div>

    <div class="col-lg-10 col-md-8 col-sm-6" style="margin-top: 30px">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">School name</th>
                <th scope="col">Class name</th>
                <th scope="col">Members</th>
                <th scope="col">Join</th>
            </tr>
            </thead>
            <tbody>
            <%int i = 0;%>
            <c:forEach var="class" items="${classes}">
                <tr>
                    <th scope="row"><%=i++%>
                    </th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                    <td>@mdo</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
