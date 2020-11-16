package by.epamtc.birukov.entity;

import java.util.Objects;

public class AuthenticationData {
    private String username;
    private String userRole;
    private int id;

    public AuthenticationData() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationData that = (AuthenticationData) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(userRole, that.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, userRole, id);
    }

    @Override
    public String toString() {
        return "AuthenticationData{" +
                "username='" + username + '\'' +
                ", userRole='" + userRole + '\'' +
                ", id=" + id +
                '}';
    }
}
