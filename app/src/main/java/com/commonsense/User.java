package com.commonsense;

public class User {

    public String full_name,phone,dob,gender,email,password;

    public User(String full_name, String phone, String dob, String gender, String email, String password) {
        this.full_name = full_name;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
