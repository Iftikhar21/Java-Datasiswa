/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author TUF GAMING F15
 */
public class Cabeka {
    private String nis;
    private Date tgl;
    private String problem;
    private String solution;
    private String kehadiran;
    private String kdGuruBk;
    private String petugasBk;
    private String namaSis;
    Guru guru;
    Siswa siswa;

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }

    public Siswa getSiswa() {
        return siswa;
    }

    public void setSiswa(Siswa siswa) {
        this.siswa = siswa;
    }
    
    public String getKdGuruBk() {
        return kdGuruBk;
    }

    public void setKdGuruBk(String kdGuruBk) {
        this.kdGuruBk = kdGuruBk;
    }


    public String getNamaSis() {
        return namaSis;
    }

    public void setNamaSis(String namaSis) {
        this.namaSis = namaSis;
    }
    

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(String kehadiran) {
        this.kehadiran = kehadiran;
    }

    public String getPetugasBk() {
        return petugasBk;
    }

    public void setPetugasBk(String petugasBk) {
        this.petugasBk = petugasBk;
    }
    
}
