package com.trial.rectifyapp.Signupinput;

/**
 * Created by student on 05-04-2018.
 */

public class User {

    private String Email, Password;

    public User(){

    }

    public User(String email, String password) {
        Email=email;
        Password=password;

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

}
