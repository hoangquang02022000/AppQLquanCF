package com.hoangquangdev.Model;

import java.io.Serializable;

public class taiKhoan implements Serializable {
    private String tenDN;
    private String password;
    private String hoTen;
    private String ngSinh;
    private String diChi;
    private String email;
    private String sdt;


    public taiKhoan() {
    }

    public taiKhoan(String tenDN, String password, String hoTen, String ngSinh, String diChi, String email, String sdt) {
        this.tenDN = tenDN;
        this.password = password;
        this.hoTen = hoTen;
        this.ngSinh = ngSinh;
        this.diChi = diChi;
        this.email = email;
        this.sdt = sdt;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgSinh() {
        return ngSinh;
    }

    public void setNgSinh(String ngSinh) {
        this.ngSinh = ngSinh;
    }

    public String getDiChi() {
        return diChi;
    }

    public void setDiChi(String diChi) {
        this.diChi = diChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "taiKhoan{" +
                "tenDN='" + tenDN + '\'' +
                ", password='" + password + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", ngSinh='" + ngSinh + '\'' +
                ", diChi='" + diChi + '\'' +
                ", email='" + email + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}
