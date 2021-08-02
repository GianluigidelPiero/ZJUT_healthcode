package com.model;

import java.io.Serializable;

public class Student implements Serializable
{
    private String stu_name;
    private String id;
    private String stu_id;
    private String mno;
    private String stu_class;
    private String color;
    private int days;
    private int idd;

    public Student() {}

    public Student(String stu_name, String id, String stu_id, String mno, String stu_class, String color, int days,int idd)
    {
        this.stu_name = stu_name;
        this.id = id;
        this.stu_id = stu_id;
        this.mno = mno;
        this.stu_class = stu_class;
        this.color = color;
        this.days = days;
        this.idd=idd;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getStu_name() {
        return stu_name;
    }

    public String getId() {
        return id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public String getMno() {
        return mno;
    }

    public String getStu_class() {
        return stu_class;
    }

    public String getColor() {
        return color;
    }

    public int getDays() {
        return days;
    }

    public int getIdd() {
        return idd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_name='" + stu_name + '\'' +
                ", id='" + id + '\'' +
                ", stu_id='" + stu_id + '\'' +
                ", mno='" + mno + '\'' +
                ", stu_class='" + stu_class + '\'' +
                ", color='" + color + '\'' +
                ", days=" + days +
                ", idd=" + idd +
                '}';
    }
}
