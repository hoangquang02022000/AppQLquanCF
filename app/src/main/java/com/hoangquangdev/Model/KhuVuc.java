package com.hoangquangdev.Model;

import java.io.Serializable;

public class KhuVuc implements Serializable {
    private int maKV;
    private String tenKV;
    private int img;

    public KhuVuc() {
    }

    public KhuVuc(int maKV, String tenKV, int img) {
        this.maKV = maKV;
        this.tenKV = tenKV;
        this.img = img;
    }

    public int getMaKV() {
        return maKV;
    }

    public void setMaKV(int maKV) {
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
