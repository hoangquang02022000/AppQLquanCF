package com.hoangquangdev.Model;

import java.io.Serializable;

public class User implements Serializable {
    private int idUser;
    private String nameUser;
    private String nameShop;
    private String numPhone;
    private String email;
    private String pass;
    private int quyen;

    public User() {
    }

    public User(int idUser, String nameUser, String nameShop, String numPhone, String email, String pass, int quyen) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.nameShop = nameShop;
        this.numPhone = numPhone;
        this.email = email;
        this.pass = pass;
        this.quyen = quyen;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nameUser='" + nameUser + '\'' +
                ", nameShop='" + nameShop + '\'' +
                ", numPhone='" + numPhone + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", quyen=" + quyen +
                '}';
    }
}
