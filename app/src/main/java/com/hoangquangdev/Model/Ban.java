package com.hoangquangdev.Model;

import java.io.Serializable;

public class Ban implements Serializable {
    private int maKV;
    private int maBan;
    private String tenBan;
    private int trangThai;

    public Ban() {
    }

    public Ban(int maKV, int maBan, String tenBan, int trangThai) {
        this.maKV = maKV;
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.trangThai = trangThai;
    }

    public int getMaKV() {
        return maKV;
    }

    public void setMaKV(int maKV) {
        this.maKV = maKV;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
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
}
