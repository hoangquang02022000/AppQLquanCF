package com.hoangquangdev.Model;

import java.io.Serializable;

public class Login implements Serializable {
    private String tenTK;
    private String pass;

    public Login() {
    }

    public Login(String tenTK, String pass) {
        this.tenTK = tenTK;
        this.pass = pass;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Login{" +
                "tenTK='" + tenTK + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
