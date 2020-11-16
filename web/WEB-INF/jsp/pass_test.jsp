<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>Прохождение теста</h1>


<p/>
<c:out value="количество вопросов ${test.getCountOfQuestion()} " default=" упс, ни одного вопроса..."/>

<form action="controller" method="get">
    <p/>
    <c:forEach var="i" begin="0" end="${test.getCountOfQuestion() - 1}" step="1">
        <p/>

        <c:out value="${test.getQuestion(i).getTextQuestion()}" default="нет данных"/>

        <c:if test="${test.getQuestion(i).getCountOfAnswer() > 1}">

            <c:forEach var="k" begin="0" end="${test.getQuestion(i).getCountOfAnswer() - 1}" step="1">

                <fieldset>
                    <c:out value="${test.getQuestion(i).getAnswer(k).getTextAnswer()}" default="нет данных"/>

                    <input type="checkbox" name="check" id="id_q${i}a${k}" value="${i}_${test.getQuestion(i).getAnswer(k).getTextAnswer()}">

<%--                    <c:set var="choise" value="q${i}a${k}"/>--%>

                </fieldset>

            </c:forEach>
        </c:if>

        <c:if test="${test.getQuestion(i).getCountOfAnswer() == 1}">

            <input type="text" name="answer" autocomplete="off" placeholder="введите ответ">
        </c:if>

    </c:forEach>
    <input type="hidden" name="command" value="send_answers">
    <input type="submit" value="готово">
</form>


</body>
</html>
