<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete card</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<div class="container">
    <p class="wl-p">Delete a card</p>
    <div class="addcategory">
        <div class="category-name" style="font-size: 30px"><i>Do you agree to delete a card <b>${card.getTerm()}</b>?</i></div>
        <form method="get" action="/deletecard">
            <input type="hidden" name="cardId" value="${card.getId()}">
            <button type="submit" class="save-category-btn" onclick="alert('Card has been successfully deleted')">Yes,I agree</button>
            <a class="save-category-btn" style="color:black; background-color: lawngreen" href="/getcards">Back</a>
        </form>
    </div>
</div>
</body>
</html>
