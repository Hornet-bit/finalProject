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
        for (int i = 0; i < countOfQuestion; i++) {

//            test.setQuestion();
            String nameQuestionParam = "question" + i;
            String valueQuestionParam = request.getParameter(nameQuestionParam);

            if (valueQuestionParam == null) {
                continue;
            }
            Question question = new Question();
            question.setTextQuestion(valueQuestionParam);
            question = addAnswerToQuestion(request, i, question);

            test.setQuestion(question);


        }
        return test;

    }

    private Question addAnswerToQuestion(HttpServletRequest request, int questionNum, Question question) {
        //todo вынести magic в константы

        for (int j = 0; j < 6; j++) {
            String paramNameAnswer = NAME_QUESTION_IN_REQUEST + questionNum + NAME_ANSWER_IN_REQUEST + j;
            String isAnswerTrue = NAME_CHECKBOX_IN_REQUEST + paramNameAnswer;

            Answer answer = new Answer();
            String valueAnswer = request.getParameter(paramNameAnswer);
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
