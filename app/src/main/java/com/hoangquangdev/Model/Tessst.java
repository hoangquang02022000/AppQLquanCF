package com.hoangquangdev.Model;

import java.io.Serializable;

public class Tessst implements Serializable {
    private String ngay;
    private double gia;

    public Tessst() {
    }

    public Tessst(String ngay, double gia) {
        this.ngay = ngay;
        this.gia = gia;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "tessst{" +
                "ngay='" + ngay + '\'' +
                ", gia=" + gia +
                '}';
    }
}
