package DAO;



import static Connection.connection.getConnection;
import model.Guru;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;
import javax.swing.table.DefaultTableModel;

public class GuruDAO {
    Connection kon;
    PreparedStatement ps;
    ResultSet rs;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    ArrayList<Guru> listGuru;
    Guru guru;

    public GuruDAO() {
        kon = getConnection();
    }

    public ArrayList<Guru> getListGuru() {
        try {
            listGuru = new ArrayList<>();
            ps = kon.prepareStatement("SELECT * FROM guru", TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            rs.beforeFirst();

            while (rs.next()) {
                guru = new Guru();
                guru.setNik(rs.getString("nik"));
                guru.setNama(rs.getString("nama"));
                guru.setJenKel(rs.getString("jenkel"));
                guru.setTanggalLahir(rs.getDate("tglLahir"));
                guru.setTempatlLahir(rs.getString("tmplahir"));
                guru.setAlamat(rs.getString("Alamat"));
                guru.setTelp(rs.getString("Telpon"));
                guru.setUser(rs.getString("User"));

                listGuru.add(guru);
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return listGuru;
    }

    public Guru cariGuru (String nik) {
        guru = new Guru();
        String sql = "SELECT * FROM guru WHERE nik = ?";
        try (PreparedStatement ps = kon.prepareStatement(sql)) {
            ps.setString(1, nik);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    guru.setNik(rs.getString("nik"));
                    guru.setNama(rs.getString("nama"));
                    guru.setJenKel(rs.getString("jenkel"));
                    guru.setTanggalLahir(rs.getDate("tglLahir"));
                    guru.setTempatlLahir(rs.getString("tmplahir"));
                    guru.setAlamat(rs.getString("Alamat"));
                    guru.setTelp(rs.getString("Telpon"));
                    guru.setUser(rs.getString("User"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return guru;
    }

    public void inputGuru(){
        String nik = JOptionPane.showInputDialog("NIK :");
        String nama = JOptionPane.showInputDialog("Nama :");
        String jenkel = JOptionPane.showInputDialog("Jenis Kelamin {L/P} :");
        String tglLahir = JOptionPane.showInputDialog("Tanggal Lahir (YYYY-MM-DD):");
        Date tgl = Date.valueOf(tglLahir);
        String tmpLahir = JOptionPane.showInputDialog("Tempat Lahir :");
        String alamat = JOptionPane.showInputDialog("Alamat :");
        String telp = JOptionPane.showInputDialog("No. Telepon :");
        String user = JOptionPane.showInputDialog("Nama User :");
        tambahGuru(nik, nama, jenkel, tmpLahir, tgl, alamat, telp, user);
    }

    public void tambahGuru(String nik, String nama, String jenkel, String tmplahir,
                            Date tglLahir, String alamat, String telepon, String user){
        String qry = "insert into guru (nik,nama,jenkel,tglLahir,tmpLahir,"
                + "Alamat,Telpon,User) values (?,?,?,?,?,?,?,?)";
        try {
            ps = kon.prepareStatement(qry);
            ps.setString(1, nik);
            ps.setString(2, nama);
            ps.setString(3, jenkel);
            ps.setDate(4, tglLahir);
            ps.setString(5, tmplahir);
            ps.setString(6, alamat);
            ps.setString(7, telepon);
            ps.setString(8,user);
            ps.executeUpdate();

        } catch (SQLException e){
            System.out.println("Error : " + e);
        }
    }

    public void hapusData(String nik) {
        String qry = "DELETE FROM guru WHERE nik=?";
        try {
            ps = kon.prepareStatement(qry);
            ps.setString(1, nik);
            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0 ) {
                System.out.println("Data guru berhasil di hapus");
            } else {
                System.out.println("Guru dengan NIK tersebut tidak ada!: " + nik);
            }
        } catch (SQLException e){
            System.out.println("Error: " + e);
        }
    }

    public void hapusGuru() {
        String nik = JOptionPane.showInputDialog("Masukkan NIK guru yang ingin dihapus:");
        hapusData(nik);
    }

    public void updateData() {
        String nikLama = JOptionPane.showInputDialog("Masukkan NIK guru yang ingin diupdate datanya");
        Guru dataGuru = cariGuru(nikLama);
        if (dataGuru.getNik() == null) {
            System.out.println("Tidak ada Guru dengan NIK: " + nikLama);
            return;
        }

        String nikBaru = JOptionPane.showInputDialog("Masukkan NIK baru (Sebelumnya: " + dataGuru.getNik() + "):");
        String nama = JOptionPane.showInputDialog("Masukkan Nama baru (Sebelumnya: " + dataGuru.getNama() + "):");
        String jenkel = JOptionPane.showInputDialog("Jenis Kelamin baru L/P (Sebelumnya: " + dataGuru.getJenKel() + "):");
        String tglLahir = JOptionPane.showInputDialog("Tanggal Lahir baru (YYYY-MM-DD) (Sebelumnya: " + dataGuru.getTanggalLahir() + "):");
        Date tgl = Date.valueOf(tglLahir);
        String tmpLahir = JOptionPane.showInputDialog("Tempat Lahir baru (Sebelumnya: " + dataGuru.getTempatlLahir() + "):");
        String alamat = JOptionPane.showInputDialog("Alamat baru (Sebelumnya: " + dataGuru.getAlamat() + "):");
        String telp = JOptionPane.showInputDialog("Nomor Telepon baru (Sebelumnya: " + dataGuru.getTelp() + "):");
        String user = JOptionPane.showInputDialog("Nama User baru (Sebelumnya: " + dataGuru.getUser() + "):");

        updateGuru(nikLama, nikBaru, nama, jenkel, tmpLahir, tgl, alamat, telp, user);
    }

    public void updateGuru(String nikLama, String nikBaru, String nama, String jenkel, String tmplahir,
                            Date tglLahir, String alamat, String telepon, String user) {
        String qry = "UPDATE guru SET nik = ?, nama = ?, jenkel = ?, tglLahir = ?, tmpLahir = ?," +
                "Alamat = ?, Telpon = ?, User = ?  WHERE nik = ?";
        try {
            ps = kon.prepareStatement(qry);
            ps.setString(1, nikBaru);  // NIK baru
            ps.setString(2, nama);
            ps.setString(3, jenkel);
            ps.setDate(4, tglLahir);
            ps.setString(5, tmplahir);
            ps.setString(6, alamat);
            ps.setString(7, telepon);
            ps.setString(8, user);
            ps.setString(9, nikBaru);  // NIK lama sebagai kondisi WHERE

            ps.executeUpdate();
            System.out.println("Data siswa dengan NIK: " + nikLama + " telah diupdate ke NIK baru: " + nikBaru);

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
    }

    public void tampilkanData(){
        System.out.println("Biodata Guru");
        for (int i = 0; i < getListGuru().size(); i++) {
            System.out.println("\nGuru Ke-" + (i + 1));
            System.out.println("NIK\t\t\t\t: " + getListGuru().get(i).getNik());
            System.out.println("Nama\t\t\t: " + getListGuru().get(i).getNama());
            System.out.println("Jenis Kelamin\t: " + getListGuru().get(i).getJenKel());
            System.out.println("Tanggal Lahir\t: " + sdf.format(getListGuru().get(i).getTanggalLahir()));
            System.out.println("Tempat Lahir\t: " + getListGuru().get(i).getTempatlLahir());
            System.out.println("Alamat\t\t\t: " + getListGuru().get(i).getAlamat());
            System.out.println("Telepon\t\t\t: " + getListGuru().get(i).getTelp());
            System.out.println("User\t\t\t: " + getListGuru().get(i).getUser());
        }
    }
    
    public DefaultTableModel getModelGuru(){
        Object[][] dataTable = new Object[getListGuru().size()][9];
        String jenkel;
        
        for (int i = 0; i < getListGuru().size(); i++) {
            dataTable[i][0] = getListGuru().get(i).getNik();
            dataTable[i][1] = getListGuru().get(i).getNama();
            jenkel = "L".equals(getListGuru().get(i).getJenKel()) ? "Laki-Laki" : "Perempuan";
            dataTable[i][2] = jenkel;
            dataTable[i][3] = getListGuru().get(i).getTanggalLahir();
            dataTable[i][4] = getListGuru().get(i).getTempatlLahir();
            dataTable[i][5] = getListGuru().get(i).getAlamat();
            dataTable[i][6] = getListGuru().get(i).getTelp();
            dataTable[i][7] = getListGuru().get(i).getUser();                        
            dataTable[i][8] = getListGuru().get(i).getPhoto();

        }
        String[] colNames = {"NIK", "Nama", "Jenis Kelamin", "Tanggal Lahir", "Tempat Lahir", "Alamat", "Telepon", "User", "Photo"};
        DefaultTableModel model = new DefaultTableModel(dataTable, colNames);
        
        return model;
    }
    
    public Guru getGuruByNik(String nik) {
        Guru guru = new Guru();
        try {
            String query = "SELECT * FROM guru WHERE nik = ?";
            ps = kon.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, nik);
            rs = ps.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                guru.setNik(rs.getString("nik"));
                guru.setNama(rs.getString("nama"));
                guru.setJenKel(rs.getString("jenkel"));
                guru.setTanggalLahir(rs.getDate("tglLahir"));
                guru.setTempatlLahir(rs.getString("tmpLahir"));
                guru.setAlamat(rs.getString("Alamat"));
                guru.setTelp(rs.getString("Telpon"));
                guru.setUser(rs.getString("User"));
                guru.setPhoto(rs.getString("photo"));
            }
        }
        catch (SQLException e) {
            guru = new Guru();
            System.out.println("Guru tidak ditemukan " + e);
        }
        return guru;
    }
    
    public void simpanData(Guru guru){
        String qry;
        try {
            ps = kon.prepareStatement("SELECT * FROM guru where nik = ?");
            ps.setString(1, guru.getNik());
            rs = ps.executeQuery();
            if (!rs.next()){
                qry = "INSERT INTO guru (nama, jenkel, tglLahir, tmpLahir, Alamat, Telpon, User, photo, nik) "
                        + "values (?,?,?,?,?,?,?,?,?)";
            } else {
                qry = "UPDATE guru SET nama = ?, jenkel = ?, tglLahir = ?, tmpLahir = ?, Alamat = ?, Telpon = ?, "
                        + "User = ?, photo = ? WHERE nik = ?";
            }
            ps = kon.prepareStatement(qry);
            ps.setString(1, guru.getNama());            
            ps.setString(2, guru.getJenKel());
            String tgl = sdf.format(guru.getTanggalLahir());
            ps.setDate(3, Date.valueOf(tgl));                        
            ps.setString(4, guru.getTempatlLahir());
            ps.setString(5, guru.getAlamat());
            ps.setString(6, guru.getTelp());            
            ps.setString(7, guru.getUser());
            ps.setString(8, guru.getPhoto());
            ps.setString(9, guru.getNik());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error hu : " + e);
        }
    }
    
    public void DeleteGuru(String nik){
        String qry = "DELETE FROM guru WHERE nik = ?";
        try {
            ps = kon.prepareStatement(qry);
            ps.setString(1, nik);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main (String[] args) {
        GuruDAO gru = new GuruDAO();
        System.out.println(gru.getGuruByNik("001"));
        
//        while (true) {
//            String pilihan = JOptionPane.showInputDialog(
//                    "PIlih operasi: \n" + "1.Tambah Guru\n" + "2.Tampilkan semua data Guru\n" +
//                            "3.Update Data Guru\n" + "4.Delete Data Guru\n" + "5.Exit\n"
//            );
//
//            switch (pilihan) {
//                case "1":
//                    gru.inputGuru();
//                    break;
//                case "2":
//                    gru.tampilkanData();
//                    break;
//                case "3":
//                    gru.updateData();
//                    break;
//                case "4":
//                    gru.hapusGuru();
//                    break;
//                case "5":
//                    System.exit(0);
//                    break;
//            }
//        }
    }
}