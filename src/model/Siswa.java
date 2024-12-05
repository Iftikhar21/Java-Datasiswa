package model;

import java.util.Date;

public class Siswa {
    private String nis;
    private String nama;
    private String tempatlLahir;
    private Date tanggalLahir;
    private String alamat;
    private String jenKel;
    private String telp;
    private String ibuKandung;
    private String ayahKandung;
    private String user;
    private String photo;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatlLahir() {
        return tempatlLahir;
    }

    public void setTempatlLahir(String tempatlLahir) {
        this.tempatlLahir = tempatlLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        try { // exception - perangkat kesalahan (error)
            this.tanggalLahir = tanggalLahir;
        }
        catch (Exception e) {
            System.out.println("Error : " + e);
            this.tanggalLahir = null;
        }
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenKel() {
        return jenKel;
    }

    public void setJenKel(String jenKel) {
        this.jenKel = jenKel;
    }

    public String getIbuKandung() {
        return ibuKandung;
    }

    public void setIbuKandung(String ibuKandung) {
        this.ibuKandung = ibuKandung;   
    }

    public String getAyahKandung() {
        return ayahKandung;
    }

    public void setAyahKandung(String ayahKandung) {
        this.ayahKandung = ayahKandung;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}