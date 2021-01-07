package by.epamtc.birukov.controller;

import by.epamtc.birukov.entity.Answer;
import by.epamtc.birukov.entity.Question;
import by.epamtc.birukov.entity.Test;

import javax.servlet.http.HttpServletRequest;

public class ParserQuestion {
    private static final String NAME_QUESTION_IN_REQUEST = "q";
    private static final String NAME_ANSWER_IN_REQUEST = "a";
    private static final String NAME_CHECKBOX_IN_REQUEST = "c";

    public Test getTest(HttpServletRequest request) {

        int countOfQuestion = Integer.parseInt(request.getParameter("count"));

        Test test = new Test();
        test.setName(request.getParameter("test_name"));
        test.setDescription(request.getParameter("test_description"));
        test.setSubjectName(request.getParameter("subj_name"));


        for (int i = 0, k = 1; i < countOfQuestion; i++) {

            String nameQuestionParam = "question" + i;
            String valueQuestionParam = request.getParameter(nameQuestionParam);

            if (valueQuestionParam == null) {
                continue;
            }

            Question question = new Question();
            question.setTextQuestion(valueQuestionParam);
            question = addAnswerToQuestion(request, i, question);

            test.setQuestion(question);

            test.setCountOfQuestion(k);
            k++;


        }
        return test;

    }

    private Question addAnswerToQuestion(HttpServletRequest request, int questionNum, Question question) {
        //todo вынести magic в константы
//todo ограничить количество
        for (int j = 0; j < 6; j++) {
            String paramNameAnswer = NAME_QUESTION_IN_REQUEST + questionNum + NAME_ANSWER_IN_REQUEST + j;
            String isAnswerTrue = NAME_CHECKBOX_IN_REQUEST + paramNameAnswer;

            Answer answer = new Answer();
            String valueAnswer = request.getParameter(paramNameAnswer);

            if (valueAnswer == null) {
                break;
            }

            //поля после значения null не будут обработаны
            question.setCountOfAnswer(j);
            boolean rightAnswer;

            if (request.getParameter(isAnswerTrue) != null) {
                rightAnswer = true;
            } else {
                rightAnswer = false;
            }

            answer.setTextAnswer(valueAnswer);
            answer.setRightAnswer(rightAnswer);

            question.setAnswer(answer);
        }
        return question;
    }
}
