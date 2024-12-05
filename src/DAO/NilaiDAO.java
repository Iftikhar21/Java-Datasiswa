/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static Connection.connection.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Guru;
import model.Mapel;
import model.Nilai;
import model.Siswa;

/**
 *
 * @author TUF GAMING F15
 */
public class NilaiDAO {
    Connection kon;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<Siswa> listSiswa;
    ArrayList<Mapel> listMapel;
    ArrayList<Guru> listGuru;
    ArrayList<Nilai> listNilai;
    Siswa siswa;
    Mapel mapel;
    Guru guru;
    Nilai nilai;
    
    public NilaiDAO(){
        kon = getConnection();
    }
    
    public String getNamaSiswa(String nis){
        String nama = null;
        try {
            ps = kon.prepareStatement("SELECT nama FROM siswa WHERE nis = ?");
            ps.setString(1, nis);
            rs = ps.executeQuery();
            rs.beforeFirst();
            if (rs.next()){
                nama = rs.getString("nama");
            }
        } catch (SQLException e){
            System.out.println("Error : " + e);
            nama = "";
        }
        return nama;
    }
    
    public DefaultTableModel getLookSiswa(){
        DefaultTableModel modelSiswa = new DefaultTableModel();
        try {
            listSiswa = new ArrayList<>();
            ps =  kon.prepareStatement("SELECT nis, nama FROM siswa", TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                siswa = new Siswa();
                siswa.setNis(rs.getString("nis"));
                siswa.setNama(rs.getString("nama"));
                listSiswa.add(siswa);
            }
        } catch (SQLException e){
            System.out.println("Error : " + e);
            listSiswa = null;
        }
        Object[][] dataTabel = new Object [listSiswa.size()][2];
        String jenkel;
        for (int i = 0; i < listSiswa.size(); i++){
            dataTabel[i][0] = listSiswa.get(i).getNis();            
            dataTabel[i][1] = listSiswa.get(i).getNama();
        }
        String [] colNames = {"NIS", "Nama Siswa"};
        modelSiswa = new DefaultTableModel(dataTabel,colNames);
        return modelSiswa;
    }
    
     public String getMapel(String nis){
        String mapel = null;
        try {
            ps = kon.prepareStatement("SELECT mapel FROM matapelajaran WHERE kode = ?");
            ps.setString(1, nis);
            rs = ps.executeQuery();
            rs.beforeFirst();
            if (rs.next()){
                mapel = rs.getString("nama");
            }
        } catch (SQLException e){
            System.out.println("Error : " + e);
            mapel = "";
        }
        return mapel;
    }
     
    public DefaultTableModel getLookMapel(){
        DefaultTableModel modelMapel = new DefaultTableModel();
        try {
            listMapel = new ArrayList<>();
            ps =  kon.prepareStatement("SELECT kode, mapel, JmlJam FROM matapelajaran", TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                mapel = new Mapel();
                mapel.setKode(rs.getString("kode"));
                mapel.setMapel(rs.getString("mapel"));
                mapel.setJmlJam(rs.getString("JmlJam"));
                listMapel.add(mapel);
            }
        } catch (SQLException e){
            System.out.println("Error : " + e);
            listMapel = null;
        }
        Object[][] dataTabel = new Object [listMapel.size()][3];
        String jenkel;
        for (int i = 0; i < listMapel.size(); i++){
            dataTabel[i][0] = listMapel.get(i).getKode();            
            dataTabel[i][1] = listMapel.get(i).getMapel();
            dataTabel[i][2] = listMapel.get(i).getJmlJam();
        }
        String [] colNames = {"Kode Mapel", "Nama Mapel", "Jumlah Jam"};
        modelMapel = new DefaultTableModel(dataTabel,colNames);
        return modelMapel;
    }
    
    public String getGuru(String nik) {
        String guru = null;
        try {
            ps =  kon.prepareStatement("SELECT nama FROM guru WHERE nik = ?");
            ps.setString(1, nik);
            rs = ps.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                guru = rs.getString("nama");
            }
        } catch (SQLException e){
            System.out.println("Error : " + e);
            guru = "";
        }
        return guru;
    }
    
    public DefaultTableModel getLookguru(){
        DefaultTableModel modelGuru = new DefaultTableModel();
        try {
            listGuru = new ArrayList<>();
            ps =  kon.prepareStatement("SELECT nik, nama FROM guru", TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                guru = new Guru();
                guru.setNik(rs.getString("nik"));
                guru.setNama(rs.getString("nama"));
                listGuru.add(guru);
            }
        } catch (SQLException e){
            System.out.println("Error : " + e);
            listGuru = null;
        }
        Object[][] dataTabel = new Object [listGuru.size()][2];
        String jenkel;
        for (int i = 0; i < listGuru.size(); i++){
            dataTabel[i][0] = listGuru.get(i).getNik();            
            dataTabel[i][1] = listGuru.get(i).getNama();
        }
        String [] colNames = {"NIK", "Nama Guru"};
        modelGuru = new DefaultTableModel(dataTabel,colNames);
        return modelGuru;
    }
    
    public DefaultTableModel getModelNilai(String nis, String smt) {
        DefaultTableModel modelNilai = new DefaultTableModel();
        
        
        try {         
            listNilai = new ArrayList<>(); //buat objek Array list siswa           
            ps = kon.prepareStatement("SELECT * FROM nilaiview WHERE nis=? "
                    + "and semester = ? Order By kode_mapel", TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, nis);
            ps.setString(2, smt);
            rs = ps.executeQuery();
            rs.beforeFirst(); //menuju ke sebelum record pertama
            while (rs.next()) { //jika ada record
                nilai = new Nilai(); // buat objek siswa
                nilai.setNis(rs.getString("nis"));
                nilai.setNama(rs.getString("nama"));
                nilai.setMapel(rs.getString("mapel"));
                nilai.setNilai(rs.getDouble("nilai"));
                nilai.setGuru(rs.getString("Pengampu"));
                nilai.setKdMapel(rs.getString("kode_mapel"));
                nilai.setKdGuru(rs.getString("kode_guru"));                
                listNilai.add(nilai); //tambahkan data siswa ke listSiswa
            }
        } catch (SQLException se) {
            System.out.println("Error : "+se);
            listNilai = null;
        }
        
        Object[][] dataTabel = new Object [listNilai.size()][5];
        String jenkel;
        for(int i = 0; i < listNilai.size(); i++) {
            dataTabel[i][0] = listNilai.get(i).getKdMapel();
            dataTabel[i][1] = listNilai.get(i).getMapel();
            dataTabel[i][2] = listNilai.get(i).getNilai();
            dataTabel[i][3] = listNilai.get(i).getKdGuru();
            dataTabel[i][4] = listNilai.get(i).getGuru();            
        } 
        String[] colNames = {"Kode","Matapelajaran","Nilai","Kode","Guru Pengampu"};
        modelNilai = new DefaultTableModel(dataTabel, colNames);
        return modelNilai;
    }
    
    public DefaultTableModel getLookNilai() {
        DefaultTableModel modelSiswa = new DefaultTableModel();
        try {
            ps = kon.prepareStatement("SELECT * FROM nilaiview", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.beforeFirst();
            while(rs.next()) {
                nilai = new Nilai();
                nilai.setNis(rs.getString("nis"));
                nilai.setNama(rs.getString("nama"));
                nilai.setSmt(rs.getString("semester"));
                nilai.setNilai(rs.getDouble("nilai"));
                nilai.setKdMapel(rs.getString("kode_mapel"));
                nilai.setMapel(rs.getString("mapel"));
                nilai.setKdGuru(rs.getString("kode_guru"));
                nilai.setGuru(rs.getString("Pengampu"));
                listNilai.add(nilai);
            }
        }catch (SQLException se) {
                System.out.println("Error : "+se);
                listNilai = null;
        }
        Object[][] dataTabel = new Object [listNilai.size()][8];
        String jenkel;
        for(int i = 0; i < listNilai.size(); i++) {
            dataTabel[i][0] = listNilai.get(i).getNis();
            dataTabel[i][1] = listNilai.get(i).getNama();
            dataTabel[i][2] = listNilai.get(i).getSmt();
            dataTabel[i][3] = listNilai.get(i).getNilai();
            dataTabel[i][4] = listNilai.get(i).getKdMapel();
            dataTabel[i][5] = listNilai.get(i).getMapel();
            dataTabel[i][6] = listNilai.get(i).getKdGuru();
            dataTabel[i][7] = listNilai.get(i).getKdGuru();
        }
        String[] colNames = {"NIS", "Nama Siswa", "Semester", "Nilai", "Kode Mapel", "Mapel", "Kode Guru", "Guru"};
        modelSiswa = new DefaultTableModel(dataTabel, colNames);
        return modelSiswa;
    }
    
    public Nilai getSiswaByNis(String nis) {
        Nilai nilai = new Nilai();
        try {
            String query = "SELECT * FROM nilaiview WHERE nis = ?";
            ps = kon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, nis);
            rs = ps.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                nilai.setNis(rs.getString("nis"));
                nilai.setNama(rs.getString("nama"));
                nilai.setSmt(rs.getString("semester"));
                nilai.setNilai(rs.getDouble("nilai"));
                nilai.setKdMapel(rs.getString("kode_mapel"));
                nilai.setMapel(rs.getString("mapel"));
                nilai.setKdGuru(rs.getString("kode_guru"));
                nilai.setPengampu(rs.getString("Pengampu"));
            }
        }
        catch (SQLException e) {
            nilai = new Nilai();
            System.out.println("Nilai tidak ditemukan " + e);
        }
        return nilai;
    }
    
    public void SimpanNilai(Nilai nilai){
        String qry;
        try {
            ps = kon.prepareStatement("SELECT * FROM nilai WHERE nis = ? AND semester = ? AND kode_mapel = ?");
            ps.setString(1, nilai.getNis());            
            ps.setString(2, nilai.getSmt());
            ps.setString(3, nilai.getKdMapel());
            rs = ps.executeQuery();
            
            if (rs.next()){
                 qry = "UPDATE nilai SET nilai = ? WHERE nis = ? AND semester = ? AND kode_mapel = ? AND kode_guru = ?";
            } else {
                qry = "INSERT INTO nilai (nilai, nis, semester, kode_mapel, kode_guru) VALUES (?,?,?,?,?)";
            }
            ps = kon.prepareStatement(qry);
            ps.setDouble(1, nilai.getNilai());
            ps.setString(2, nilai.getNis());
            ps.setString(3, nilai.getSmt());
            ps.setString(4, nilai.getKdMapel());
            ps.setString(5, nilai.getKdGuru());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error : " + e);
        }
    }
    
    public void DeleteNilai(String nis, String smt, String kdMapel){
        try {
            ps = kon.prepareStatement("DELETE FROM nilai WHERE nis = ? AND semester = ? AND kode_mapel = ?");
            ps.setString(1, nis);                        
            ps.setString(2, smt);
            ps.setString(3, kdMapel);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
    }
}

