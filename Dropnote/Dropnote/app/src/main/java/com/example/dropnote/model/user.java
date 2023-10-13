package com.example.dropnote.model;

public class user {
    // Atribute for user class
    private int id_user;
    private String nama,tanggal_lahir, email, password;

    // Constructor
    public user(){

    }

    public user(String nama, String tanggal_lahir, String email, String password) {
        this.nama = nama;
        this.tanggal_lahir = tanggal_lahir;
        this.email = email;
        this.password = password;
    }

    public user(int id_user, String nama, String tanggal_lahir, String email, String password) {
        this.id_user = id_user;
        this.nama = nama;
        this.tanggal_lahir = tanggal_lahir;
        this.email = email;
        this.password = password;
    }

    // Getter and Setter
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
