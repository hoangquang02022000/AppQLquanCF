package com.hoangquangdev.Model;

import java.io.Serializable;
import java.io.SerializablePermission;

public class Hoadon implements Serializable {
    private int idHoadon;
    private int idKV;
    private int idBan;
    private int idSanpham;
    private String tenSampam;
    private String size;
    private String topping;
    private int soLuong;
    private double giaSanpham;
    private String img;

    public Hoadon() {
    }

    public Hoadon(int idHoadon, int idKV, int idBan, int idSanpham, String tenSampam, String size, String topping, int soLuong, double giaSanpham, String img) {
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

    public int getIdHoadon() {
        return idHoadon;
    }

    public void setIdHoadon(int idHoadon) {
        this.idHoadon = idHoadon;
    }

    public int getIdKV() {
        return idKV;
    }

    public void setIdKV(int idKV) {
        this.idKV = idKV;
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public int getIdSanpham() {
        return idSanpham;
    }

    public void setIdSanpham(int idSanpham) {
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