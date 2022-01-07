package com.hoangquangdev.Model;

import java.io.Serializable;

public class KhuVuc implements Serializable {
    protected String maKV;
    protected String tenKV;
    protected int img;

    public KhuVuc() {
    }

    public KhuVuc(String maKV, String tenKV, int img) {
        this.maKV = maKV;
        this.tenKV = tenKV;
        this.img = img;
    }

    public String getMaKV() {
        return maKV;
    }

    public void setMaKV(String maKV) {
        this.maKV = maKV;
    }

    public String getTenKV() {
        return tenKV;
    }

    public void setTenKV(String tenKV) {
        this.tenKV = tenKV;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
