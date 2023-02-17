<jsp:useBean id="quizHistory" scope="request" type="com.nooglers.domains.test.QuizHistory"/>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.Duration" %>
<%@ page import="com.nooglers.utils.ApplicationUtils" %><%--
  Created by IntelliJ IDEA.
  User: otash
  Date: 2/17/23
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <style>
        .key {
            font-style: italic;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">

    <div class="text-bg-info">

        <p>Questions count: ${quizHistory.getTotalQuestionCount()}
        </p>
        <p>Correct answers count: ${quizHistory.getCorrectAnswerCount()}
        </p>
        <p>Finished at: ${ApplicationUtils.DATE_TIME_FORMATTER.format(quizHistory.getFinishedAt())}
        </p>
        <p>Time
            (minutes): ${Duration.ofHours(ChronoUnit.HOURS.between(quizHistory.getStartedAt() , quizHistory.getFinishedAt())).toMinutes()}
        </p>
        <p>
            Percentage: ${ApplicationUtils.DECIMAL_FORMATTER.format(quizHistory.getCorrectAnswerCount() * 100 / quizHistory.getTotalQuestionCount())}%
        </p>
    </div>


    <div class="text-body">
        <c:set var="i" scope="session" value="${1}"/>
        <c:forEach items="${questions}" var="question">
        <p>${i}.
            <span class="key">Definition: </span> ${question.getDefinition()}<br/>
            <span class="key">Term: </span> ${question.getCorrectAnswer()}<br/>
            <c:if test="${question.isCorrect()}">
                <span class="key"> Your answer: </span>${question.getCorrectAnswer()} ✅<br/>
            </c:if>
            <c:if test="${!question.isCorrect()}">
                <span class="key"> Your answer: </span>${question.getUserAnswer()==null?"did not answer":question.getUserAnswer()}
                ❌ <br/>
                <span class="key"> Correct answer: </span>${question.getCorrectAnswer()} ✅<br/>
            </c:if>
            <span class="key">Test type: </span> ${question.getQuizType()}<br/>
        </p>
        <c:set var="i" scope="session" value="${i+1}"/>
        </c:forEach>

    </div>

</div>


</body>
</html>
