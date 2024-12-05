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
import javax.swing.table.DefaultTableModel;
import model.Cabeka;
import model.Guru;
import model.Guru;
import model.Mapel;
import model.Nilai;
import model.Siswa;

/**
 *
 * @author TUF GAMING F15
 */
public class CabekaDAO {
    Connection kon;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<Siswa> listSiswa;
    ArrayList<Guru> listGuru;
    ArrayList<Cabeka> listCabeka;
    Siswa siswa;
    Guru guru;
    Cabeka cabeka;
    
    public CabekaDAO(){
        kon = getConnection();
    }
    
    public String getNamaSiswa(String nis) {
        String nama = null;
        try {
            ps = kon.prepareStatement("SELECT * FROM siswa WHERE nis = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, nis);
            rs = ps.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                nama = rs.getString("nama");
            }
        }
        catch (SQLException e) {
            System.out.println(e);
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
    
    public String getNamaGuru(String nik) {
        String nama = null;
        try {
            ps = kon.prepareStatement("SELECT * FROM guru WHERE nik = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, nik);
            rs = ps.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                nama = rs.getString("nama");
            }
        }
        catch (SQLException e) {
            System.out.println(e);
            nama = "";
        }
        return nama;
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
    
    public Cabeka getCabekaByNis(String nis) {
        Cabeka cabeka = new Cabeka();
        try {
            String query = "SELECT * FROM cabekaview WHERE nis = ?";
            ps = kon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, nis);
            rs = ps.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                cabeka.setNis(rs.getString("nis"));
                cabeka.setTgl(rs.getDate("tgl"));                
                cabeka.setProblem(rs.getString("permasalahan"));                
                cabeka.setSolution(rs.getString("solusi"));
                cabeka.setKehadiran(rs.getString("kehadiran"));
                cabeka.setKdGuruBk(rs.getString("kode_guru"));                
                cabeka.setNamaSis(rs.getString("nama_siswa"));
                cabeka.setPetugasBk(rs.getString("nama_guru_bk"));
            }
        }
        catch (SQLException e) {
            cabeka = new Cabeka();
            System.out.println("Nilai tidak ditemukan " + e);
        }
        return cabeka;
    }
    
    public ArrayList<Cabeka> getListCabeka(String[] args) {
        String query = "SELECT * FROM cabekaview WHERE nis = '" + args[0].trim() + "'";
        if (!args[1].isEmpty())
            query += " AND tgl = '" + args[1].trim() + "'";

        try {
            listCabeka = new ArrayList<>();
            ps = kon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();

            while (rs.next()) {
                cabeka = new Cabeka();
                cabeka.setNis(rs.getString("nis"));
                cabeka.setTgl(rs.getDate("tgl"));
                cabeka.setProblem(rs.getString("permasalahan"));
                cabeka.setSolution(rs.getString("solusi"));
                cabeka.setKehadiran(rs.getString("kehadiran"));
                cabeka.setKdGuruBk(rs.getString("petugas_bk"));

                siswa = new Siswa();
                siswa.setNama(rs.getString("nama_siswa"));
                cabeka.setSiswa(siswa);

                guru = new Guru();
                guru.setNama(rs.getString("nama_guru"));
                cabeka.setGuru(guru);

                listCabeka.add(cabeka);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return listCabeka;
    }
    
     public DefaultTableModel getModelAllCabeka(String[] args) {
        listCabeka = getListCabeka(args);

        Object[][] dataTable = new Object[listCabeka.size()][7];

        for (int i = 0; i < listCabeka.size(); i++) {
            dataTable[i][0] = listCabeka.get(i).getNis();
            dataTable[i][1] = listCabeka.get(i).getSiswa().getNama(); 
            dataTable[i][2] = listCabeka.get(i).getTgl();
            dataTable[i][3] = listCabeka.get(i).getProblem();
            dataTable[i][4] = listCabeka.get(i).getSolution();
            dataTable[i][5] = listCabeka.get(i).getKehadiran();
            dataTable[i][6] = listCabeka.get(i).getGuru().getNama();
        }

        String[] colNames = {"NIS", "Nama Siswa", "Tanggal", "Permasalahan", "Solusi", "Kehadiran", "Nama Guru"};

        DefaultTableModel model = new DefaultTableModel(dataTable, colNames);
        return model;
    }
     
     public Cabeka getSingleCabeka(Cabeka cabeka) {
        this.cabeka = new Cabeka();
        try {
            String query = "SELECT * FROM cabekaview WHERE nis = ? AND tgl = ?";
            ps = kon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ps.setString(1, cabeka.getNis());
            ps.setDate(2, cabeka.getTgl());

            rs = ps.executeQuery();
            rs.beforeFirst();

            if (rs.next()) {
                this.cabeka.setNis(rs.getString("nis"));
                this.cabeka.setTgl(rs.getDate("tgl"));
                this.cabeka.setKdGuruBk(rs.getString("petugas_bk"));
                this.cabeka.setProblem(rs.getString("permasalahan"));
                this.cabeka.setSolution(rs.getString("solusi"));
                this.cabeka.setKehadiran(rs.getString("kehadiran"));

                siswa = new Siswa();
                siswa.setNama(rs.getString("nama_siswa"));

                guru = new Guru();
                guru.setNama(rs.getString("nama_guru"));

                this.cabeka.setSiswa(siswa);
                this.cabeka.setGuru(guru);
            } else {
                this.cabeka = null;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            this.cabeka = null;
        }
        return this.cabeka;
    }
     
     public void saveCabeka(Cabeka cabeka) {
        String query = "SELECT * FROM cabeka WHERE nis = ? AND tgl = ?";
        try {
            ps = kon.prepareStatement(query);
            ps.setString(1, cabeka.getNis());
            ps.setDate(2, cabeka.getTgl());
            rs = ps.executeQuery();

            if (rs.next()) {
                query = "UPDATE cabeka SET permasalahan = ?, solusi = ?, kehadiran = ?, petugas_bk = ? WHERE nis = ? AND tgl = ?";
            } else {
                query = "INSERT INTO cabeka (permasalahan, solusi, kehadiran, petugas_bk, nis, tgl) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
            }

            ps = kon.prepareStatement(query);
            ps.setString(1, cabeka.getProblem());
            ps.setString(2, cabeka.getSolution());
            ps.setString(3, cabeka.getKehadiran());
            ps.setString(4, cabeka.getKdGuruBk());
            
            ps.setString(5, cabeka.getNis());
            ps.setDate(6, cabeka.getTgl());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
     
     public void deleteCabeka(String nis, Date tgl) {
        String query = "DELETE FROM cabeka WHERE nis = ? AND tgl = ?";
        try {
            ps = kon.prepareStatement(query);
            ps.setString(1, nis);
            ps.setDate(2, tgl);

            ps.executeUpdate();
            System.out.println("Cabeka berhasil dihapus");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
//    public DefaultTableModel getModelCabeka(String nis, String tgl) {
//        DefaultTableModel modelNilai = new DefaultTableModel();
//        
//        
//        try {         
//            listGuru = new ArrayList<>(); //buat objek Array list siswa           
//            ps = kon.prepareStatement("SELECT * FROM nilaiview WHERE nis=? "
//                    + "and semester = ? Order By kode_mapel", TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ps.setString(1, nis);
//            ps.setString(2, smt);
//            rs = ps.executeQuery();
//            rs.beforeFirst(); //menuju ke sebelum record pertama
//            while (rs.next()) { //jika ada record
//                nilai = new Nilai(); // buat objek siswa
//                nilai.setNis(rs.getString("nis"));
//                nilai.setNama(rs.getString("nama"));
//                nilai.setMapel(rs.getString("mapel"));
//                nilai.setNilai(rs.getDouble("nilai"));
//                nilai.setGuru(rs.getString("Pengampu"));
//                nilai.setKdMapel(rs.getString("kode_mapel"));
//                nilai.setKdGuru(rs.getString("kode_guru"));                
//                listNilai.add(nilai); //tambahkan data siswa ke listSiswa
//            }
//        } catch (SQLException se) {
//            System.out.println("Error : "+se);
//            listNilai = null;
//        }
//        
//        Object[][] dataTabel = new Object [listNilai.size()][5];
//        String jenkel;
//        for(int i = 0; i < listNilai.size(); i++) {
//            dataTabel[i][0] = listNilai.get(i).getKdMapel();
//            dataTabel[i][1] = listNilai.get(i).getMapel();
//            dataTabel[i][2] = listNilai.get(i).getNilai();
//            dataTabel[i][3] = listNilai.get(i).getKdGuru();
//            dataTabel[i][4] = listNilai.get(i).getGuru();            
//        } 
//        String[] colNames = {"Kode","Matapelajaran","Nilai","Kode","Guru Pengampu"};
//        modelNilai = new DefaultTableModel(dataTabel, colNames);
//        return modelNilai;
//    }
}
