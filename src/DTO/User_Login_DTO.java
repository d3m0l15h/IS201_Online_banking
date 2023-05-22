package DTO;

import java.util.Date;

public class User_Login_DTO {

    private int id;
    private String username, password, role;
    private int numberOfFailedLogin;
    private Date lastAccessTime;

    public User_Login_DTO() {

    }

    public User_Login_DTO(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public User_Login_DTO(int id, String username, String password, String role, int numberOfFailedLogin, Date lastAccessTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.numberOfFailedLogin = numberOfFailedLogin;
        this.lastAccessTime = lastAccessTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setNumberOfFailedLogin(int numberOfFailedLogin) {
        this.numberOfFailedLogin = numberOfFailedLogin;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getNumberOfFailedLogin() {
        return numberOfFailedLogin;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

}
