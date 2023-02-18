<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Class update page</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container col-lg-10 col-md-8 col-sm-6">
    <form method="post" action="/group/update?groupId=${group.getId()}"
          style="width: 60%;margin-left: 20%;margin-top: 5%">
        <div class="mb-3">
            <label for="exampleInputName" class="form-label">Enter class name</label>
            <input type="text" class="form-control" id="exampleInputName" aria-describedby="emailHelp" name="classname"
                   required value="${group.getName()}">
        </div>
        <div class="mb-3">
            <label for="exampleInputSchoolName" class="form-label">School name</label>
            <input type="text" class="form-control" id="exampleInputSchoolName"
                   name="schoolname" required value="${group.getSchoolName()}">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control" id="description" name="description"
                   placeholder="Optional" value="${group.getDescription()}">
        </div>
        <c:if test="${group.isPermissionToUpdateSets()}">
        <div class="mb-3 form-check">
            <input type="checkbox" name="updatepermission" class="form-check-input" checked id="exampleCheckUpdate">
            <label class="form-check-label" for="exampleCheckUpdate">Permission to update</label>
        </div>
        </c:if>
        <c:if test="${!group.isPermissionToUpdateSets()}">
        <div class="mb-3 form-check">
            <input type="checkbox" name="updatepermission" class="form-check-input"  id="exampleCheckUpdate">
            <label class="form-check-label" for="exampleCheckUpdate">Permission to update</label>
        </div>
        </c:if>
        <c:if test="${group.isPermissionToInvite()}">
            <div class="mb-3 form-check">
                <input type="checkbox" name="invitepermission" class="form-check-input" checked id="exampleCheckInvite">
                <label class="form-check-label" for="exampleCheckInvite">Permission to invite</label>
            </div>
        </c:if>
        <c:if test="${!group.isPermissionToInvite()}">
            <div class="mb-3 form-check">
                <input type="checkbox" name="invitepermission" class="form-check-input" id="exampleCheckInvite">
                <label class="form-check-label" for="exampleCheckInvite">Permission to invite</label>
            </div>
        </c:if>
        <button type="submit" class="btn btn-warning">Submit</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
