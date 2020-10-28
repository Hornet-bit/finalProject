<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create u own test</title>
</head>
<body>
<script language="javascript">

    var x = 0;
    var ans = 0;

    function addInput() {
        if (x < 10) {
            var str = '<fieldset>' +
                '<input type="text" name="question' + (x + 1) + '" placeholder="Вопрос" autocomplete="off">  ' +
                '<input type="text" name="answer' + (ans + 1) + '" placeholder="name" autocomplete="off">' +
                '<button type="button" name="button' + (x + 1) + '" id="button' + (x + 1) + '" onclick="makeField(id)">Добавить вариант ответа</button>' +
                '<div id="input' + (x + 1) + '"></div>' +
                '</fieldset>';

            document.getElementById('input' + x).innerHTML = str;
            x++;
        } else {
            alert('STOP it!');
        }
    }

    function makeField(id) {
        input = document.createElement('input');
        input.name = "answer" + ans + "";
        input.placeholder = input.name;
        input.autocomplete="off";

        const butt = document.getElementById(id);
        butt.insertAdjacentElement("beforebegin", input);
        ans++;
    }

</script>
<h1>Создай свой новый тест прямо тут!</h1>

<form action="controller">

<%--        <input type="text" name="q0" placeholder="Вопрос">--%>
<%--        <input type="text" name="a0" placeholder="ответ">--%>
        <div id="input0"></div>

    <fieldset>
        <div class="add" onclick="addInput()">+</div>
    </fieldset>

    <input type="hidden" name="command" value="CREATE_TEST">
    <input type="submit" value="создать тест">

</form>



</body>
</html>
