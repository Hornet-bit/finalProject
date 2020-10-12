package by.epamtc.birukov.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRegForm implements Serializable {

    private String name;
    private String password;
    private String email;

    public UserRegForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegForm that = (UserRegForm) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, email);
    }

    @Override
    public String toString() {
        return "UserRegForm{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
