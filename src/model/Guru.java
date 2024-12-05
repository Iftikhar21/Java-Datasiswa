package model;

import java.util.Date;

public class Guru {
    private String nik;
    private String nama;
    private String jenKel;
    private Date tanggalLahir;
    private String tempatlLahir;
    private String alamat;
    private String telp;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    private String photo;


    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenKel() {
        return jenKel;
    }

    public void setJenKel(String jenKel) {
        this.jenKel = jenKel;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatlLahir() {
        return tempatlLahir;
    }

    public void setTempatlLahir(String tempatlLahir) {
        this.tempatlLahir = tempatlLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;
}
