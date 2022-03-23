package com.example.myautomobileprojectkyj.vo;

public class MemberVo {
    private int seq;
    private String id;
    private String name;
    private String password;
    private int birthday;
    private String address;
    private int phonenum;

    public int getSeq() {
        return seq;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public int getPhonenum() {
        return phonenum;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhonenum(int phonenum) {
        this.phonenum = phonenum;
    }
}
