package com.example.dropnote;

public class DataHolder {

    private static final DataHolder instance = new DataHolder();

    public static DataHolder getInstance(){
        return instance;
    }

    private String username;
    public String getUsername(){
        return username;
    }

    private String email;
    public String getEmail(){
        return email;
    }

    private String password;
    public String getPassword(){
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
