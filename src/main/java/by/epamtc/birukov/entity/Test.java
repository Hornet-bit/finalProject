package by.epamtc.birukov.entity;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public Test() {
    }

    private List<Question> questionList = new ArrayList<>();

    private String name;
    private String description;
    private int resultTest;


    public void setQuestion(Question question){
        questionList.add(question);
    }

    public Question getQuestion(int index){
        return questionList.get(index);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResultTest() {
        return resultTest;
    }

    public void setResultTest(int resultTest) {
        this.resultTest = resultTest;
    }
}