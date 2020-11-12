<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Прохождение теста</h1>


<p/>
<c:out value="количество вопросов ${test.getCountOfQuestion()} " default=" упс, ни одного вопроса..."/>
<p/>
<c:forEach var="i" begin="0" end="${test.getCountOfQuestion() - 1}" step="1">
    <p/>

    <c:out value="${test.getQuestion(i).getTextQuestion()}" default="нет данных"/>

    <c:if test="${test.getQuestion(i).getCountOfAnswer() > 1}">

        <c:forEach var="k" begin="0" end="${test.getQuestion(i).getCountOfAnswer() - 1}" step="1">

                    <fieldset>
                        <c:out value="${test.getQuestion(i).getAnswer(k).getTextAnswer()}" default="нет данных"/>
                        <input type="checkbox" name="q${i}a${k}">
            <%--            <c:if test="${test.getQuestion(i).getAnswer(k).isRightAnswer()}">--%>
            <%--                &lt;%&ndash;                окрасить в зелёный&ndash;%&gt;--%>
            <%--                <c:out value="правильно"/>--%>
            <%--            </c:if>--%>
                    </fieldset>

        </c:forEach>
    </c:if>

    <c:if test="${test.getQuestion(i).getCountOfAnswer() == 1}">

        <input type="text" name="q${i}" autocomplete="off" placeholder="введите ответ">

    </c:if>

</c:forEach>


</body>
</html>
