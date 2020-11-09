package by.epamtc.birukov.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {

    public Question() {
    }

    private List<Answer> answerList = new ArrayList<>();

    private String content;
    private String textQuestion;
    private int countOfAnswer;

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public void setCountOfAnswer(int countOfAnswer) {
        this.countOfAnswer = countOfAnswer;
    }

    public void setAnswer(Answer answer){
        answerList.add(answer);
    }

    public Answer getAnswer(int index){
        return answerList.get(index);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }
}
