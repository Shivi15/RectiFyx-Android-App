package com.trial.rectifyapp;

/**
 * Created by student on 01-04-2018.
 */

class User {
    private static String Email, Password, Name;

    public User() {
    }

    public User(String email, String password, String name) {
       Email=email;
        Password=password;
        Name=name;

    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public static String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

