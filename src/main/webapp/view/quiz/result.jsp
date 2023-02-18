<jsp:useBean id="quizHistory" scope="request" type="com.nooglers.domains.test.QuizHistory"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.Duration" %>
<%@ page import="com.nooglers.utils.ApplicationUtils" %>
<%@ page import="com.nooglers.configs.ThreadSafeBeansContainer" %>
<%@ include file="/fragments/css.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: otash
  Date: 2/17/23
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Result</title>

</head>
<body>

<div class="container">

    <div class="text-info-emphasis">

        <c:set var="quizService" scope="application" value="${ThreadSafeBeansContainer.QUIZ_SERVICE.get()}"/>

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
        <jsp:useBean id="questions" scope="request" type="java.util.List"/>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Definition</th>
                <th scope="col">Term</th>
                <th scope="col">Your answer</th>
                <th scope="col">Test type</th>
                <th scope="col">Correct answer</th>
                <th scope="col">Correct</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${questions}" var="question">
                <c:set var="term" value="${quizService.getTerm(question)}"/>
                <c:set var="userAnswer" value="${quizService.getUserAnswer(question)}"/>

                <tr>
                    <th scope="row">${i}</th>
                    <td>${question.getDefinition()}</td>
                    <td>${question.getDisplayTerm()}</td>
                    <td>${userAnswer}</td>
                    <td>${question.getQuizType()}</td>
                    <td>${term}</td>
                    <td>${question.isCorrect()?"✅":"❌"}</td>
                </tr>
                <c:set var="i" scope="session" value="${i+1}"/>
            </c:forEach>

            <div>
                <a type="button" class="btn btn-success" href="/test">Try again</a>
                <a type="button" class="btn btn-warning" href="/home">Back</a>
            </div>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
