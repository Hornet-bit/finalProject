package by.epamtc.birukov.entity;

import java.util.ArrayList;
import java.util.List;

public class RunTest {
    private int idTest;
    private List<Integer> listOfStudents = new ArrayList<>();

    private String startDate;
    private String endDate;

    public RunTest() {
    }

    public int getIdUser(int index){
        return listOfStudents.get(index);
    }

    public void setStudent(int id){
        listOfStudents.add(id);
    }

    public int getSize(){
        return listOfStudents.size();
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
