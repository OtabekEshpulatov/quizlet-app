<%@ page import="com.nooglers.utils.ApplicationUtils" %>
<%@ page import="com.nooglers.domains.test.Question" %>
<%@ page import="com.nooglers.dto.SolveQuestionDto" %>
<%@ page import="com.nooglers.domains.test.Variant" %><%--
  Created by IntelliJ IDEA.
  User: otash
  Date: 2/15/23
  Time: 1:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/fragments/css.jsp" %>
<html>
<head>
    <title>Test</title>
    <style>
        .buttons {
            display: inline-block;
        }
    </style>
</head>
<body>


<div>

        <%final SolveQuestionDto question = ( SolveQuestionDto ) request.getAttribute("question");%>


    <%--                <div class="card" style="width: 18rem;">--%>
    <%--                    <div class="card-body">--%>
    <%--                        <h5 class="card-title">Card title</h5>--%>
    <%--                        <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>--%>
    <%--                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the--%>
    <%--                            card's content.</p>--%>
    <%--                        <a href="#" class="card-link">Card link</a>--%>
    <%--                        <a href="#" class="card-link">Another link</a>--%>
    <%--                    </div>--%>
    <%--                </div>--%>

    <div class="card" style="width: 40rem; margin:20px 50%">
        <form method="post" action="/test">

            <input type="hidden" name="questionId" value="${question.id()}">
            <label>
                <%if ( question.quizType().equals("TRUE_FALSE") ) {%>
                <div class="card-body">
                    <%
                        if ( question.variants().size() > 1 ) {
                            final Variant incorrectVar = question.variants().get(0);
                    %>
                    <div class="card-title">
                        <p><i><b>Term</b></i>:   <%=incorrectVar.getTerm()%>
                        </p>
                        <p><i><b>Definition</b></i>:   <%=question.definition()%>
                        </p>
                        <div class="card-text">
                            <input type="radio" name="value" value="true">
                            True <input type="radio" name="value" value="false">False
                        </div>
                    </div>
                    <%}%>
                </div>

                <%} else if ( question.quizType().equals("WRITING") ) {%>

                <div class="card-body">
                    <div class="card-title">
                        <p><%=question.definition()%>
                    </div>
                    <div class="card-text">
                        <input type="text" placeholder="your answer" name="value">
                    </div>
                </div>


                <%} else if ( question.quizType().equals("TEST") ) {%>

                <div class="card-body">
                    <div class="card-title"><%=question.definition()%>
                    </div>
                    <%
                        for ( Variant variant : question.variants() ) {%>
                    <div class="card-text">
                        <input type="radio" name="value" value="<%=variant.getId()%>"> <%=variant.getTerm()%>
                    </div>
                    <%}%>

                </div>
                <%}%>


            </label>
            <div class="buttons">
                <button href="/test" type="submit" class="btn-success">Next</button>
            </div>
        </form>

        <form method="post" action="/test/finish">
            <div class="card-header">
                <input type="hidden" name="questionId" value="${question.id()}">
                <button class="btn-danger">Finish</button>
            </div>
        </form>
    </div>


</body>
</html>
