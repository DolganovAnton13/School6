package com.yugorsk.school6.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Login {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String login;
    private String password;
    private int admin;

    public Login(String login, String password, int admin) {
        this.login = login;
        this.password = password;
        this.admin = admin;
    }

    public Login(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
