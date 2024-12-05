package DAO;

import static Connection.connection.getConnection;
import model.Siswa;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;
import javax.swing.table.DefaultTableModel;


public class SiswaDAO {
    Connection kon;
    PreparedStatement ps;
    ResultSet rs;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    ArrayList<Siswa> listSiswa;
    Siswa siswa;

    public SiswaDAO() {
        kon = getConnection();
    }
    
//    public DefaultTableModel getModel(){
//        Object[][] dataTabel = new Object[] 
//    }

    public ArrayList<Siswa> getListSiswa() {
        try {
            listSiswa = new ArrayList<>();
            ps = kon.prepareStatement("SELECT * FROM siswa", TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.beforeFirst();

            while (rs.next()) {
                siswa = new Siswa();
                siswa.setNis(rs.getString("nis"));
                siswa.setNama(rs.getString("nama"));
                siswa.setJenKel(rs.getString("jenkel"));
                siswa.setTempatlLahir(rs.getString("tmplahir"));
                siswa.setTanggalLahir(rs.getDate("tglLahir"));
                siswa.setAlamat(rs.getString("alamat"));
                siswa.setAyahKandung(rs.getString("ayah"));
                siswa.setIbuKandung(rs.getString("ibu"));
                siswa.setTelp(rs.getString("telepon"));
                siswa.setUser(rs.getString("user"));
                siswa.setPhoto(rs.getString("photo"));

                listSiswa.add(siswa);
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return listSiswa;
    }

    public Siswa cariSiswa (String nis) {
        siswa = new Siswa();
        String sql = "SELECT * FROM siswa WHERE nis = ?";
        try (PreparedStatement ps = kon.prepareStatement(sql)) {
            ps.setString(1, nis);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    siswa.setNis(rs.getString("nis"));
                    siswa.setNama(rs.getString("nama"));
                    siswa.setJenKel(rs.getString("jenkel"));
                    siswa.setTempatlLahir(rs.getString("tmplahir"));
                    siswa.setTanggalLahir(rs.getDate("tglLahir"));
                    siswa.setAlamat(rs.getString("alamat"));
                    siswa.setTelp(rs.getString("telepon"));
                    siswa.setAyahKandung(rs.getString("ayah"));
                    siswa.setIbuKandung(rs.getString("ibu"));
                    siswa.setUser(rs.getString("user"));
                    siswa.setPhoto(rs.getString("photo"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return siswa;
    }

    public void inputData(){
        String nis = JOptionPane.showInputDialog("NIS :");
        String nama = JOptionPane.showInputDialog("NAMA :");
        String jenkel = JOptionPane.showInputDialog("Jenis Kelamin {L/P} :");
        String tmpLahir = JOptionPane.showInputDialog("Tempat Lahir :");
        String tglLahir = JOptionPane.showInputDialog("Tanggal Lahir (YYYY-MM-DD):");
        Date tgl = Date.valueOf(tglLahir);
        String alamat = JOptionPane.showInputDialog("Alamat :");
        String telp = JOptionPane.showInputDialog("No. Telepon :");
        String ayahKandung = JOptionPane.showInputDialog("Nama Ayah Kandung : ");
        String ibuKandung = JOptionPane.showInputDialog("Nama Ibu Kandung : ");
        String user = JOptionPane.showInputDialog("Nama User :");
        tambahSiswa(nis, nama, jenkel, tmpLahir, tgl, alamat, telp, ayahKandung, ibuKandung, user);
    }

    public void tambahSiswa(String nis, String nama, String jenkel, String tmplahir,
                            Date tglLahir, String alamat, String telepon, String ayah, String ibu, String user){
        String qry = "insert into siswa (nis,nama,jenkel,tmplahir,tglLahir,"
                + "alamat,telepon,ayah,ibu,user) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = kon.prepareStatement(qry);
            ps.setString(1, nis);
            ps.setString(2, nama);
            ps.setString(3, jenkel);
            ps.setString(4, tmplahir);
            ps.setDate(5, tglLahir);
            ps.setString(6, alamat);
            ps.setString(7, telepon);
            ps.setString(8, ayah);
            ps.setString(9, ibu);
            ps.setString(10,user);
            ps.executeUpdate();

        } catch (SQLException e){
            System.out.println("Error : " + e);
        }
    }

    public void hapusData(String nis) {
        String qry = "DELETE FROM siswa WHERE nis=?";
        try {
            ps = kon.prepareStatement(qry);
            ps.setString(1, nis);
            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0 ) {
                System.out.println("Data siswa berhasil di apus");
            } else {
                System.out.println("Siswa dengan nis tersebut tidak ada!: " + nis);
            }
        } catch (SQLException e){
            System.out.println("Eror: " + e);
        }
    }

    public void hapusSiswa() {
        String nis = JOptionPane.showInputDialog("Masukkan Nis siswa yang ingin dihapus:");
        hapusData(nis);
    }


    public void updateData() {
        String nisLama = JOptionPane.showInputDialog("Masukkan NIS siswa yang ingin diupdate datanya");
        Siswa datasiswa = cariSiswa(nisLama);
        if (datasiswa.getNis() == null) {
            System.out.println("Tidak ada siswa dengan NIS: " + nisLama);
            return;
        }

        String nisBaru = JOptionPane.showInputDialog("Masukkan NIS baru (Sebelumnya: " + datasiswa.getNis() + "):");
        String nama = JOptionPane.showInputDialog("Masukkan nama baru (Sebelumnya: " + datasiswa.getNama() + "):");
        String jenkel = JOptionPane.showInputDialog("Jenis kelamin baru L/P (Sebelumnya: " + datasiswa.getJenKel() + "):");
        String tmpLahir = JOptionPane.showInputDialog("Tempat lahir baru (Sebelumnya: " + datasiswa.getTempatlLahir() + "):");
        String tglLahir = JOptionPane.showInputDialog("Tanggal lahir baru (YYYY-MM-DD) (Sebelumnya: " + datasiswa.getTanggalLahir() + "):");
        Date tgl = Date.valueOf(tglLahir);
        String alamat = JOptionPane.showInputDialog("Alamat baru (Sebelumnya: " + datasiswa.getAlamat() + "):");
        String telp = JOptionPane.showInputDialog("Nomor telepon baru (Sebelumnya: " + datasiswa.getTelp() + "):");
        String ayahKandung = JOptionPane.showInputDialog("Nama Ayah baru (Sebelumnya: " + datasiswa.getAyahKandung() + "):");
        String ibuKandung = JOptionPane.showInputDialog("Nama Ibu baru (Sebelumnya: " + datasiswa.getIbuKandung() + "):");
        String user = JOptionPane.showInputDialog("Nama User Baru (Sebelumnya: "+ datasiswa.getUser() + "):");

        updateSiswa(nisLama, nisBaru, nama, jenkel, tmpLahir, tgl, alamat, telp, ayahKandung, ibuKandung, user);
    }

    public void updateSiswa(String nisLama, String nisBaru, String nama, String jenkel, String tmplahir,
                            Date tglLahir, String alamat, String telepon, String ayah, String ibu, String user) {
        String qry = "UPDATE siswa SET nis = ?, nama = ?, jenkel = ?, tmplahir = ?, tglLahir = ?, " +
                "alamat = ?, telepon = ?, ayah = ?, ibu = ?, user = ?  WHERE nis = ?";
        try {
            ps = kon.prepareStatement(qry);
            ps.setString(1, nisBaru);  // NIS baru
            ps.setString(2, nama);
            ps.setString(3, jenkel);
            ps.setString(4, tmplahir);
            ps.setDate(5, tglLahir);
            ps.setString(6, alamat);
            ps.setString(7, telepon);
            ps.setString(8, ayah);
            ps.setString(9, ibu);
            ps.setString(10, user);
            ps.setString(11, nisLama);  // NIS lama sebagai kondisi WHERE

            ps.executeUpdate();
            System.out.println("Data siswa dengan NIS: " + nisLama + " telah diupdate ke NIS baru: " + nisBaru);

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
    }

    public void tampilkanData(){
        System.out.println("Biodata Siswa");
        for (int i = 0; i < getListSiswa().size(); i++) {
            System.out.println("\nSiswa Ke-" + (i + 1));
            System.out.println("NIS\t\t\t\t: " + getListSiswa().get(i).getNis());
            System.out.println("NAMA\t\t\t: " + getListSiswa().get(i).getNama());
            System.out.println("Jenis Kelamin\t: " + getListSiswa().get(i).getJenKel());
            System.out.println("Tempat Lahir\t: " + getListSiswa().get(i).getTempatlLahir());
            System.out.println("Tanggal Lahir\t: " + getListSiswa().get(i).getTanggalLahir());
            System.out.println("Alamat\t\t\t: " + getListSiswa().get(i).getAlamat());
            System.out.println("Ayah\t\t\t: " + getListSiswa().get(i).getAyahKandung());
            System.out.println("Ibu\t\t\t\t: " + getListSiswa().get(i).getIbuKandung());
            System.out.println("Telepon\t\t\t: " + getListSiswa().get(i).getTelp());
            System.out.println("User\t\t\t: " + getListSiswa().get(i).getUser());
        }
    }
    
    public DefaultTableModel getModelSiswa() {
        Object[][] dataTable = new Object[getListSiswa().size()][11];
        String jenkel;
        
        for (int i = 0; i < getListSiswa().size(); i++) {
            dataTable[i][0] = getListSiswa().get(i).getNis();
            dataTable[i][1] = getListSiswa().get(i).getNama();
            dataTable[i][2] = getListSiswa().get(i).getTempatlLahir();
            dataTable[i][3] = getListSiswa().get(i).getTanggalLahir();
            dataTable[i][4] = getListSiswa().get(i).getAlamat();
            jenkel = "L".equals(getListSiswa().get(i).getJenKel()) ? "Laki-Laki" : "Perempuan";
            dataTable[i][5] = jenkel;
            dataTable[i][6] = getListSiswa().get(i).getTelp();
            dataTable[i][7] = getListSiswa().get(i).getIbuKandung();
            dataTable[i][8] = getListSiswa().get(i).getAyahKandung();
            dataTable[i][9] = getListSiswa().get(i).getUser();
            dataTable[i][10] = getListSiswa().get(i).getPhoto();
        }
        String[] colNames = {"NIS", "Nama", "Tempat Lahir", "Tanggal Lahir", "Alamat", "Jenis Kelamin", "Telepon", "Ibu", "Ayah", "User", "Photo"};
        DefaultTableModel model = new DefaultTableModel(dataTable, colNames);
        
        return model;
    }
    
    public Siswa getSiswaByNis(String nis) {
        Siswa siswa = new Siswa();
        try {
            String query = "SELECT * FROM siswa WHERE nis = ?";
            ps = kon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, nis);
            rs = ps.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                siswa.setNis(rs.getString("nis"));
                siswa.setNama(rs.getString("nama"));
                siswa.setJenKel(rs.getString("jenkel"));
                siswa.setTempatlLahir(rs.getString("tmplahir"));
                siswa.setTanggalLahir(rs.getDate("tgllahir"));
                siswa.setAlamat(rs.getString("alamat"));
                siswa.setAyahKandung(rs.getString("ayah"));
                siswa.setIbuKandung(rs.getString("ibu"));
                siswa.setTelp(rs.getString("telepon"));
                siswa.setUser(rs.getString("user"));                
                siswa.setPhoto(rs.getString("photo"));
            }
        }
        catch (SQLException e) {
            siswa = new Siswa();
            System.out.println("Siswa tidak ditemukan " + e);
        }
        return siswa;
    }
    
    public void simpanData(Siswa siswa){
        String qry;
        try {
            ps = kon.prepareStatement("SELECT * FROM siswa where nis = ?");
            ps.setString(1, siswa.getNis());
            rs = ps.executeQuery();
            if (!rs.next()){
                qry = "INSERT INTO siswa (nama, jenkel, tmplahir, tglLahir, alamat, telepon, ayah, ibu, user, photo, nis) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?)";
            } else {
                qry = "UPDATE siswa set nama = ?, jenkel = ?, tmplahir = ?, tglLahir = ?, alamat = ?, telepon = ?, "
                        + "ayah = ?, ibu = ?, user = ?, photo = ? where nis = ?";
            }
            ps = kon.prepareStatement(qry);
            ps.setString(1, siswa.getNama());            
            ps.setString(2, siswa.getJenKel());
            ps.setString(3, siswa.getTempatlLahir());
            String tgl = sdf.format(siswa.getTanggalLahir());
            ps.setDate(4, Date.valueOf(tgl));
            ps.setString(5, siswa.getAlamat());
            ps.setString(6, siswa.getTelp());
            ps.setString(7, siswa.getAyahKandung());
            ps.setString(8, siswa.getIbuKandung());            
            ps.setString(9, siswa.getUser());
            ps.setString(10, siswa.getPhoto());
            ps.setString(11, siswa.getNis());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error : " + e);
        }
    }
    
    public void DeleteSiswa(String nis){
        String qry = "DELETE FROM siswa WHERE nis = ?";
        try {
            ps = kon.prepareStatement(qry);
            ps.setString(1, nis);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main(String[] args) {
        SiswaDAO sis = new SiswaDAO();
//        System.out.println(sis.getSiswaByNis("001"));
        Siswa siswa = new Siswa();
        siswa.setNis("004");
        siswa.setNama("Iftikhar");
        siswa.setTanggalLahir(Date.valueOf("2007-08-21"));
        sis.simpanData(siswa);
    }
}