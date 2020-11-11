<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Один тест:</h1>

<c:out value="${test.name}"/>
<c:out value="${test.description}"/>


<%--<c:out value="${test.getQuestion(1).getTextQuestion()}" default="нет данных"/>--%>
<p/>
<c:out value="количество вопросов ${test.getCountOfQuestion()} " default=" упс, ни одного вопроса..."/>
<p/>
<c:forEach var="i" begin="0" end="${test.getCountOfQuestion() - 1}" step="1">
    <p/>

    <c:out value="${test.getQuestion(i).getTextQuestion()}" default="нет данных"/>
    <%--    <c:out value="${test.getQuestion(i).getCountOfAnswer()}" default="нет данных"/>--%>
    <%--    внутренний цикл, который выводит все ответы--%>

    <c:forEach var="k" begin="0" end="${test.getQuestion(i).getCountOfAnswer() - 1}" step="1">
        <fieldset>
            <c:out value="${test.getQuestion(i).getAnswer(k).getTextAnswer()}" default="нет данных"/>
            <c:out value="${test.getQuestion(i).getAnswer(k).isRightAnswer()}" default="нет данных"/>
            <c:if test="${test.getQuestion(i).getAnswer(k).isRightAnswer()}">
<%--                окрасить в зелёный--%>
                <c:out value="правильно"/>
            </c:if>
        </fieldset>
    </c:forEach>

</c:forEach>
<%--${fn:length(test.questionList)}--%>
<p/>
<%--<c:forEach var="tempTest" varStatus="counter" items="${test.getQuestion(counter).getTextQuestion()}" >--%>
<%--    <c:out value="aa"/>--%>
<%--&lt;%&ndash;    <c:out value="${tempTest.getQuestion(counter).getTextQuestion()}"/>&ndash;%&gt;--%>
<%--</c:forEach>--%>


</body>
</html>
