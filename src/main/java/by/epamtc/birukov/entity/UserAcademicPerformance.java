package by.epamtc.birukov.entity;

import java.util.List;
import java.util.Map;

public class UserAcademicPerformance {

    private int id;
    private String username;

    private List<String> testNames;
    private Map<String, Integer> testResult;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getTestNames() {
        return testNames;
    }

    public void setTestName(String name) {
        testNames.add(name);
    }

    public void setMark(String test, int mark){
        testResult.put(test, mark);
    }

    public int getMarkByTest(String test){
        return testResult.get(test);
    }

}
