<%--
  Created by IntelliJ IDEA.
  User: otash
  Date: 2/14/23
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <link rel="stylesheet" href="/utils/css/header.css">
</head>

<body>


<ul>
    <li><a class="active" href="/home">Home</a></li>
    <li><a href="/library">Your library</a></li>
    <li><a href="/create">Create</a></li>
    <%if ( session.getAttribute("remember_me") != null ) {%>
    <li><a href="/logout">Create</a>LogOut</li>
    <%} else {%>
    <li><a href="/logout">LogOut</a></li>
    <%}%>

    <li>
        <form action="/search" target="_blank">
            <input type="search"><br><br>
            <button>Search</button>
        </form>
    </li>

</ul>

</body>
</html>
