package com.hoangquangdev.Model;

import java.io.Serializable;

public class Hoadon implements Serializable {
    private String idHoadon;
    private String idKV;
    private String idBan;
    private String idSanpham;
    private String tenSampam;
    private String size;
    private String topping;
    private int soLuong;
    private double giaSanpham;
    private String img;

    public Hoadon() {
    }

    public Hoadon(String idHoadon, String idKV, String idBan, String idSanpham, String tenSampam, String size, String topping, int soLuong, double giaSanpham, String img) {
        this.idHoadon = idHoadon;
        this.idKV = idKV;
        this.idBan = idBan;
        this.idSanpham = idSanpham;
        this.tenSampam = tenSampam;
        this.size = size;
        this.topping = topping;
        this.soLuong = soLuong;
        this.giaSanpham = giaSanpham;
        this.img = img;
    }

    public String getIdHoadon() {
        return idHoadon;
    }

    public void setIdHoadon(String idHoadon) {
        this.idHoadon = idHoadon;
    }

    public String getIdKV() {
        return idKV;
    }

    public void setIdKV(String idKV) {
        this.idKV = idKV;
    }

    public String getIdBan() {
        return idBan;
    }

    public void setIdBan(String idBan) {
        this.idBan = idBan;
    }

    public String getIdSanpham() {
        return idSanpham;
    }

    public void setIdSanpham(String idSanpham) {
        this.idSanpham = idSanpham;
    }

    public String getTenSampam() {
        return tenSampam;
    }

    public void setTenSampam(String tenSampam) {
        this.tenSampam = tenSampam;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaSanpham() {
        return giaSanpham;
    }

    public void setGiaSanpham(double giaSanpham) {
        this.giaSanpham = giaSanpham;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}