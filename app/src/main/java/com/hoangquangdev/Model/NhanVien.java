package com.hoangquangdev.Model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int idnhanVien;
    private String tenNV;
    private String chucvu; //0,1,2
    public String nsinh;
    public String gtinh;
    public String phone;
    public String email;
    public String dchi;
    public String taiKhoan;
    public String matKhau;
    public String img;

    public NhanVien() {
    }

    public NhanVien(int idnhanVien, String tenNV, String chucvu, String nsinh, String gtinh, String phone, String email, String dchi, String taiKhoan, String matKhau, String img) {
        this.idnhanVien = idnhanVien;
        this.tenNV = tenNV;
        this.chucvu = chucvu;
        this.nsinh = nsinh;
        this.gtinh = gtinh;
        this.phone = phone;
        this.email = email;
        this.dchi = dchi;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.img = img;
    }

    public int getIdnhanVien() {
        return idnhanVien;
    }

    public void setIdnhanVien(int idnhanVien) {
        this.idnhanVien = idnhanVien;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getNsinh() {
        return nsinh;
    }

    public void setNsinh(String nsinh) {
        this.nsinh = nsinh;
    }

    public String getGtinh() {
        return gtinh;
    }

    public void setGtinh(String gtinh) {
        this.gtinh = gtinh;
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
        return dchi;
    }

    public void setDchi(String dchi) {
        this.dchi = dchi;
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

    @Override
    public String toString() {
        return "NhanVien{" +
                "idnhanVien=" + idnhanVien +
                ", tenNV='" + tenNV + '\'' +
                ", chucvu='" + chucvu + '\'' +
                ", nsinh='" + nsinh + '\'' +
                ", gtinh='" + gtinh + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dchi='" + dchi + '\'' +
                ", taiKhoan='" + taiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}