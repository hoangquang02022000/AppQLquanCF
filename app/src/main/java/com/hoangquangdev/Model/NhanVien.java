package com.hoangquangdev.Model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int idNhanVien;
    private String tenNV;
    private int chucvu; //0,1,2
    public String Nsinh;
    public String Gtinh;
    public String phone;
    public String email;
    public String Dchi;
    public String taiKhoan;
    public String matKhau;
    public String img;

    public NhanVien() {
    }

    public NhanVien(int idNhanVien, String tenNV, int chucvu, String nsinh, String gtinh, String phone, String email, String dchi, String taiKhoan, String matKhau, String img) {
        this.idNhanVien = idNhanVien;
        this.tenNV = tenNV;
        this.chucvu = chucvu;
        Nsinh = nsinh;
        Gtinh = gtinh;
        this.phone = phone;
        this.email = email;
        Dchi = dchi;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.img = img;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getChucvu() {
        return chucvu;
    }

    public void setChucvu(int chucvu) {
        this.chucvu = chucvu;
    }

    public String getNsinh() {
        return Nsinh;
    }

    public void setNsinh(String nsinh) {
        Nsinh = nsinh;
    }

    public String getGtinh() {
        return Gtinh;
    }

    public void setGtinh(String gtinh) {
        Gtinh = gtinh;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDchi() {
        return Dchi;
    }

    public void setDchi(String dchi) {
        Dchi = dchi;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
