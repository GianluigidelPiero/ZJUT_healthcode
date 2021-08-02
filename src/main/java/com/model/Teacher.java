package com.model;

import java.io.Serializable;

public class Teacher implements Serializable
{
    private String teacher_name;
    private String id;
    private String teacher_id;
    private String dno;
    private String role;
    private String color;
    private int days;
    private String password;
    private int idd;

    public Teacher() {}

    public Teacher(String teacher_name, String id, String teacher_id, String dno, String role, String color, int days, String password,int idd)
    {
        this.teacher_name = teacher_name;
        this.id = id;
        this.teacher_id = teacher_id;
        this.dno = dno;
        this.role = role;
        this.color = color;
        this.days = days;
        this.password = password;
        this.idd=idd;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getId() {
        return id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public String getDno() {
        return dno;
    }

    public String getRole() {
        return role;
    }

    public String getColor() {
        return color;
    }

    public int getDays() {
        return days;
    }

    public String getPassword() {
        return password;
    }

    public int getIdd() {
        return idd;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_name='" + teacher_name + '\'' +
                ", id='" + id + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", dno='" + dno + '\'' +
                ", role='" + role + '\'' +
                ", color='" + color + '\'' +
                ", days=" + days +
                ", password='" + password + '\'' +
                ", idd=" + idd +
                '}';
    }
}
