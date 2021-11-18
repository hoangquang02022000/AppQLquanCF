package com.hoangquangdev.Model;

import java.io.Serializable;

public class Ban implements Serializable {
    private String maBan;
    private String tenBan;
    private int trangThai;
    private  int imgBan;

    public Ban() {
    }

    public Ban(String maBan, String tenBan, int trangThai, int imgBan) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.trangThai = trangThai;
        this.imgBan = imgBan;
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

    public int getImgBan() {
        return imgBan;
    }

    public void setImgBan(int imgBan) {
        this.imgBan = imgBan;
    }
}
