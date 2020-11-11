package by.epamtc.birukov.entity;

import java.util.ArrayList;
import java.util.List;

public class RunTest {
    private int idUser;
    private int idTest;
    private List<Integer> listOfStudents = new ArrayList<>();

    private String startDate;
    private String endDate;

    public RunTest() {
    }

    public int getStudent(int index){
        return listOfStudents.get(index);
    }

    public void setStudent(int id){
        listOfStudents.add(id);
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
