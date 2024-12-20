/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIew;

import VIew.NilaiView;
import VIew.GuruView;
import VIew.SiswaView;
import javax.swing.JOptionPane;
import model.Jurusan;
import model.Nilai;

/**
 *
 * @author TUF GAMING F15
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Data
     */
    public Menu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        itemMenuBar = new javax.swing.JMenuBar();
        itemUtility = new javax.swing.JMenu();
        itemLogout = new javax.swing.JMenuItem();
        itemChangePass = new javax.swing.JMenuItem();
        itemExit = new javax.swing.JMenuItem();
        itemDataMaster = new javax.swing.JMenu();
        dataSekolah = new javax.swing.JMenuItem();
        dataNilai = new javax.swing.JMenuItem();
        dataSiswa = new javax.swing.JMenuItem();
        dataJurusan = new javax.swing.JMenuItem();
        dataGuru = new javax.swing.JMenuItem();
        menuCabeka = new javax.swing.JMenuItem();
        itemMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        itemUtility.setText("Utility");

        itemLogout.setText("Logout");
        itemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLogoutActionPerformed(evt);
            }
        });
        itemUtility.add(itemLogout);

        itemChangePass.setText("Ganti Password");
        itemUtility.add(itemChangePass);

        itemExit.setText("Keluar");
        itemUtility.add(itemExit);

        itemMenuBar.add(itemUtility);

        itemDataMaster.setText("Data Master");
        itemDataMaster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemDataMasterMousePressed(evt);
            }
        });

        dataSekolah.setText("Data Sekolah");
        dataSekolah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataSekolahActionPerformed(evt);
            }
        });
        itemDataMaster.add(dataSekolah);

        dataNilai.setText("Data Nilai");
        dataNilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataNilaiMouseClicked(evt);
            }
        });
        dataNilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataNilaiActionPerformed(evt);
            }
        });
        itemDataMaster.add(dataNilai);

        dataSiswa.setText("Data Siswa");
        dataSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataSiswaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dataSiswaMousePressed(evt);
            }
        });
        dataSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataSiswaActionPerformed(evt);
            }
        });
        itemDataMaster.add(dataSiswa);

        dataJurusan.setText("Data Jurusan");
        dataJurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataJurusanActionPerformed(evt);
            }
        });
        itemDataMaster.add(dataJurusan);

        dataGuru.setText("Data Guru");
        dataGuru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dataGuruMousePressed(evt);
            }
        });
        dataGuru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataGuruActionPerformed(evt);
            }
        });
        itemDataMaster.add(dataGuru);

        menuCabeka.setText("Data Cabeka");
        menuCabeka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCabekaActionPerformed(evt);
            }
        });
        itemDataMaster.add(menuCabeka);

        itemMenuBar.add(itemDataMaster);

        itemMenu.setText("Laporan");
        itemMenuBar.add(itemMenu);

        setJMenuBar(itemMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dataSekolahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSekolahActionPerformed
        // TODO add your handling code here:
        GuruView sklh = new GuruView();
        sklh.setVisible(true);
    }//GEN-LAST:event_dataSekolahActionPerformed

    private void itemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemLogoutActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        setExtendedState(MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void dataSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSiswaActionPerformed
        // TODO add your handling code here:
        SiswaView sis = new SiswaView();
        sis.setVisible(true);
    }//GEN-LAST:event_dataSiswaActionPerformed

    private void dataSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataSiswaMouseClicked
        // TODO add your handling code here:
        SiswaView sis = new SiswaView();
        sis.setVisible(true);
    }//GEN-LAST:event_dataSiswaMouseClicked

    private void dataNilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataNilaiActionPerformed
        // TODO add your handling code here:
        NilaiView nilai = new NilaiView();
        nilai.setVisible(true);
    }//GEN-LAST:event_dataNilaiActionPerformed

    private void itemDataMasterMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemDataMasterMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemDataMasterMousePressed

    private void dataSiswaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataSiswaMousePressed
        // TODO add your handling code here:
        SiswaView sis = new SiswaView();
        sis.setModal(true);
        sis.setVisible(true);
    }//GEN-LAST:event_dataSiswaMousePressed

    private void dataJurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataJurusanActionPerformed
        // TODO add your handling code here:
        JurusanView jur = new JurusanView();
        jur.setVisible(true);
    }//GEN-LAST:event_dataJurusanActionPerformed

    private void dataGuruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataGuruActionPerformed
        // TODO add your handling code here:
        GuruView gru = new GuruView();
        gru.setVisible(true);
    }//GEN-LAST:event_dataGuruActionPerformed

    private void dataGuruMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataGuruMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_dataGuruMousePressed

    private void dataNilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataNilaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dataNilaiMouseClicked

    private void menuCabekaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCabekaActionPerformed
        // TODO add your handling code here:
        CabekaView cab = new CabekaView();
        cab.setVisible(true);
    }//GEN-LAST:event_menuCabekaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem dataGuru;
    private javax.swing.JMenuItem dataJurusan;
    private javax.swing.JMenuItem dataNilai;
    private javax.swing.JMenuItem dataSekolah;
    private javax.swing.JMenuItem dataSiswa;
    private javax.swing.JMenuItem itemChangePass;
    private javax.swing.JMenu itemDataMaster;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JMenu itemMenu;
    private javax.swing.JMenuBar itemMenuBar;
    private javax.swing.JMenu itemUtility;
    private javax.swing.JMenuItem menuCabeka;
    // End of variables declaration//GEN-END:variables
}
