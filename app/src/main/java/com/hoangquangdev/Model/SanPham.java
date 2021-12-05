package com.hoangquangdev.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String maSP;
    private String tenSP;
    private int loaiSP;
    private Double giaSp;
    private int imgSP;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, int loaiSP, Double giaSp, int imgSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.giaSp = giaSp;
        this.imgSP = imgSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
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

    public int getImgSP() {
        return imgSP;
    }

    public void setImgSP(int imgSP) {
        this.imgSP = imgSP;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP='" + maSP + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", loaiSP=" + loaiSP +
                ", giaSp=" + giaSp +
                ", imgSP=" + imgSP +
                '}';
    }
}
