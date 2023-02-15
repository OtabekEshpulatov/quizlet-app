<%--
  Created by IntelliJ IDEA.
  User: otash
  Date: 2/15/23
  Time: 7:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div class="modal fade" id="generateQuiz" aria-hidden="true" aria-labelledby="UpdateStudentModalLabel"
     tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="UpdateStudentModalLabel">Update Student</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form method="post" action="/students/update/">
                    <input type="hidden" id="u_id" name="id"/>
                    <div class="mb-3">
                        <label for="u_firstName" class="form-label">Student First Name</label>
                        <input type="text" class="form-control" id="u_firstName" name="firstName">
                    </div>
                    <div class="mb-3">
                        <label for="u_lastName" class="form-label">Student Last Name</label>
                        <input type="text" class="form-control" id="u_lastName" name="lastName">
                    </div>

                    <div class="mb-3">
                        <label for="u_age" class="form-label">Student Age</label>
                        <input type="number" class="form-control" id="u_age" name="age">
                    </div>
                    <button type="submit" class="btn btn-success">Update Student</button>
                </form>
            </div>
        </div>
    </div>
</div>


</body>
</html>
