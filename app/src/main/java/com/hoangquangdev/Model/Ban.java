package com.hoangquangdev.Model;

import java.io.Serializable;

public class Ban implements Serializable {
    private String maKV;
    private String maBan;
    private String tenBan;
    private int trangThai;
    private int chek ;

    public Ban() {
    }

    public Ban(String maKV, String maBan, String tenBan, int trangThai,int chek) {
        this.maKV = maKV;
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.trangThai = trangThai;
        this.chek = chek;
    }

    public String getMaKV() {
        return maKV;
    }

    public void setMaKV(String maKV) {
        this.maKV = maKV;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getChek() {
        return chek;
    }

    public void setChek(int chek) {
        this.chek = chek;
    }
}
