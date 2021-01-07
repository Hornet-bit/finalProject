package by.epamtc.birukov.entity;

import java.util.Objects;

public class Subject {

    public Subject() {
    }

    private String name;
    private String description;
    private int index;

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return index == subject.index &&
                Objects.equals(name, subject.name) &&
                Objects.equals(description, subject.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, index);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", index=" + index +
                '}';
    }
}
