package by.epamtc.birukov.entity;

public class Answer {

    public Answer() {
    }

    private String textAnswer;
    private boolean isRightAnswer;

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public boolean isRightAnswer() {
        return isRightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        isRightAnswer = rightAnswer;
    }
}
