package com.hoangquangdev.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Detail_Buil implements Serializable {
    private String date;
    private String id_b;
    private String idKV;
    private String idBan;
    private ArrayList<Mon_order> mList_mon_order;
    private double tongBuild;

    public Detail_Buil() {
    }

    public Detail_Buil(String date, String id_b, String idKV, String idBan, ArrayList<Mon_order> mon_order, double tongBuild) {
        this.date = date;
        this.id_b = id_b;
        this.idKV = idKV;
        this.idBan = idBan;
        this.mList_mon_order = mon_order;
        this.tongBuild = tongBuild;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId_b() {
        return id_b;
    }

    public void setId_b(String id_b) {
        this.id_b = id_b;
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

    public ArrayList<Mon_order> getmList_mon_order() {
        return mList_mon_order;
    }

    public void setmList_mon_order(ArrayList<Mon_order> mList_mon_order) {
        this.mList_mon_order = mList_mon_order;
    }

    public double getTongBuild() {
        return tongBuild;
    }

    public void setTongBuild(double tongBuild) {
        this.tongBuild = tongBuild;
    }
}
