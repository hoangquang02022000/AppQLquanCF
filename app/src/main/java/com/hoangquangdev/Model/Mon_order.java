package com.hoangquangdev.Model;

import java.io.Serializable;

public class Mon_order implements Serializable {
    private String id_sp;
    private String ten_sp;
    private String img;
    private int sluong;
    private double gia_sp;
    private String size;
    private String topping ;

    public Mon_order() {
    }

    public Mon_order(String id_sp, String ten_sp, String img, int sluong, double gia_sp, String size, String topping) {
        this.id_sp = id_sp;
        this.ten_sp = ten_sp;
        this.img = img;
        this.sluong = sluong;
        this.gia_sp = gia_sp;
        this.size = size;
        this.topping = topping;
    }

    public String getId_sp() {
        return id_sp;
    }

    public void setId_sp(String id_sp) {
        this.id_sp = id_sp;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSluong() {
        return sluong;
    }

    public void setSluong(int sluong) {
        this.sluong = sluong;
    }

    public double getGia_sp() {
        return gia_sp;
    }

    public void setGia_sp(double gia_sp) {
        this.gia_sp = gia_sp;
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

    @Override
    public String toString() {
        return "Mon_order{" +
                "id_sp='" + id_sp + '\'' +
                ", ten_sp='" + ten_sp + '\'' +
                ", img='" + img + '\'' +
                ", sluong=" + sluong +
                ", gia_sp=" + gia_sp +
                ", size='" + size + '\'' +
                ", topping='" + topping + '\'' +
                '}';
    }
}
