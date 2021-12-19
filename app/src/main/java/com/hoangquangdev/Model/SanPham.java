package com.hoangquangdev.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int maSP;
    private String tenSP;

    private int loaiSP;
    private Double giaSp;
    private String imgSP;

    public SanPham() {
    }

    public SanPham(int maSP, String tenSP, int loaiSP, Double giaSp, String imgSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.giaSp = giaSp;
        this.imgSP = imgSP;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(int loaiSP) {
        this.loaiSP = loaiSP;
    }

    public Double getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(Double giaSp) {
        this.giaSp = giaSp;
    }

    public String getImgSP() {
        return imgSP;
    }

    public void setImgSP(String imgSP) {
        this.imgSP = imgSP;
    }

    @Override
    public String toString() {
        return "ID: "+this.getMaSP() +" Name: "+this.getTenSP();
    }
}
