package com.taye.usersSession.recyclerview;

public class users {
    public String fullName,sex,phone,userName,email,password;
    public users(){

    }
    public users(String fullName, String userName, String phone, String email, String sex, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
