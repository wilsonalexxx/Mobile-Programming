package com.example.dropnote.model;

public abstract class activity {
    // Atribute
    private long id_activity;
    private long id_user;
    private String nama_activity, tanggal_activity, time_activity, location_activity, description_activity, status_activity;

    public activity(){

    }

    public activity(long id_activity, long id_user, String nama_activity, String tanggal_activity, String time_activity, String location_activity, String description_activity, String status_activity) {
        this.id_activity = id_activity;
        this.id_user = id_user;
        this.nama_activity = nama_activity;
        this.tanggal_activity = tanggal_activity;
        this.time_activity = time_activity;
        this.location_activity = location_activity;
        this.description_activity = description_activity;
        this.status_activity = status_activity;
    }

    public long getId_activity() {
        return id_activity;
    }

    public void setId_activity(int id_activity) {
        this.id_activity = id_activity;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama_activity() {
        return nama_activity;
    }

    public void setNama_activity(String nama_activity) {
        this.nama_activity = nama_activity;
    }

    public String getTanggal_activity() {
        return tanggal_activity;
    }

    public void setTanggal_activity(String tanggal_activity) {
        this.tanggal_activity = tanggal_activity;
    }

    public String getTime_activity() {
        return time_activity;
    }

    public void setTime_activity(String time_activity) {
        this.time_activity = time_activity;
    }

    public String getLocation_activity() {
        return location_activity;
    }

    public void setLocation_activity(String location_activity) {
        this.location_activity = location_activity;
    }

    public String getDescription_activity() {
        return description_activity;
    }

    public void setDescription_activity(String description_activity) {
        this.description_activity = description_activity;
    }

    public String getStatus_activity() {
        return status_activity;
    }

    public void setStatus_activity(String status_activity) {
        this.status_activity = status_activity;
    }
}
