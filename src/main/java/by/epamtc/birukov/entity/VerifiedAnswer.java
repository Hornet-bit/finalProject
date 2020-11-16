package by.epamtc.birukov.entity;

import java.util.Objects;

public class VerifiedAnswer {

    private String textQuestion;
    private boolean rightAnswer;

    public VerifiedAnswer() {
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerifiedAnswer that = (VerifiedAnswer) o;
        return rightAnswer == that.rightAnswer &&
                Objects.equals(textQuestion, that.textQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textQuestion, rightAnswer);
    }


    @Override
    public String toString() {
        return "VerifiedAnswer{" +
                "textQuestion='" + textQuestion + '\'' +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}
