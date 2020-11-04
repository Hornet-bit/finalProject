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
//попробую удалить все х+1

            var str = '<fieldset id="fld' + x + '">' +
                '<input type="text" id="id_question' + (x) + '" name="question' + (x) + '" placeholder="Вопрос'+x+'" autocomplete="off">  ' +
                '<input type="text" id="id_answer' + (ans) + '" name="q' + (x) + 'a' + (ans) + '" placeholder="name" autocomplete="off">' +
                '<input type="checkbox" name="cq'+x+'a'+ans+'">' +
                '<button type="button" name="button' + (x) + '" id="' + (x) + '" onclick="createAnswer(id) ">Добавить вариант ответа</button>' +
                '<input type="hidden" id="id_free_q' + (x) + '" name="free_q' + (x) + '" value="1">' +
                '<p></p><button type="button" id="bt' + (x) + '" onclick="delete_elem(id)">Удалить вопрос</button>' +
                '</fieldset>' +
                '<div id="input' + (x + 1) + '"></div>';


            document.getElementById('input' + x).innerHTML = str;
            // сохранение
            ans = 0;
            x++;
            document.getElementById("hiddenId").value = x;


        } else {
            alert('STOP it!');
        }
    }


    function generate(id) {
        var qst = "q" + id;
        var ans = "a" + document.getElementById("id_free_q" + (id)).value;

        let next_ans = Number(document.getElementById("id_free_q" + (id)).value);
        next_ans++;

        document.getElementById("id_free_q" + (id)).value = next_ans;


        var result = qst + ans;

        return result;
    }

    function createAnswer(id) {
        var new_value = generate(id);
        input = document.createElement('input');
        input.name = new_value;
        input.placeholder = new_value;
        input.autocomplete = "off";

        const butt = document.getElementById(id);
        butt.insertAdjacentElement("beforebegin", input);


        check = document.createElement('input');
        check.type = "checkbox";
        check.name = "c" + new_value;


        butt.insertAdjacentElement("beforebegin", check);
        // alert(check.name)

    }

    function delete_elem(id) {
        document.getElementById(id).parentElement.remove();

    }

</script>


<h1>Создай свой новый тест прямо тут!</h1>


<form action="controller">

    <div id="input0"></div>
    <fieldset>
        <button type="button" class="add" onclick="addInput()">Добавить вопрос</button>
    </fieldset>

    <input type="hidden" id="hiddenId" name="count"/>
    <input type="hidden" name="command" value="CREATE_TEST">
    <input type="submit" value="создать тест">

</form>


</body>
</html>
