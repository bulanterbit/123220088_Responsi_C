import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewBuku extends JFrame {
    JLabel lNamaPenyewa, lJudulBuku, lJenisBuku, lNoTelp, lDurasiSewa;
    JLabel lTotalBiaya;
    JTextField tNamaPenyewa, tJudulBuku, tJenisBuku, tNoTelp, tDurasiSewa, tTotalBiaya;
    JButton bSimpan, bHapus, bEdit;
    JPanel panelForm, panelTombol;
    String DBurl = "jdbc:mysql://localhost/db_responsi";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;

    public ViewBuku() {
        setTitle("CobaDatabase!");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        lNamaPenyewa = new JLabel("Nama");
        lJudulBuku = new JLabel("Judul");
        lJenisBuku = new JLabel("Jenis");
        lNoTelp = new JLabel("No Telp");
        lDurasiSewa = new JLabel("Durasi Sewa");
        lTotalBiaya = new JLabel("Total Biaya");

        tNamaPenyewa = new JTextField(2);
        tJudulBuku = new JTextField(2);
        tJenisBuku = new JTextField(2);
        tNoTelp = new JTextField(2);
        tDurasiSewa = new JTextField(2);
        tTotalBiaya = new JTextField(2);

        bSimpan = new JButton("Simpan");
        bHapus = new JButton("Hapus");
        bEdit = new JButton("Edit");
        panelForm = new JPanel(new GridLayout(4, 2));
        panelTombol = new JPanel(new FlowLayout());
        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.CENTER);
        panelForm.add(lNamaPenyewa);
        panelForm.add(tNamaPenyewa);
        panelForm.add(lJudulBuku);
        panelForm.add(tJudulBuku);
        panelForm.add(lJenisBuku);
        panelForm.add(tJenisBuku);
        panelForm.add(lNoTelp);
        panelForm.add(tNoTelp);
        panelForm.add(lDurasiSewa);
        panelForm.add(tDurasiSewa);
        panelForm.add(lTotalBiaya);
        panelForm.add(tTotalBiaya);
        add(panelTombol, BorderLayout.SOUTH);
        panelTombol.add(bSimpan);
        panelTombol.add(bHapus);
        panelTombol.add(bEdit);

        bSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                masukkanData();
            }
        });

        bHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusData();
            }
        });

        bEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editData();
            }
        });
    }

    public void masukkanData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            statement = koneksi.createStatement();
            String query = "insert into sewa_buku values('" + tNamaPenyewa.getText() + "','" + tJudulBuku.getText() + "','" + tJenisBuku.getText() + "','" + tNoTelp.getText() + "','" + tDurasiSewa.getText() + "','" + tTotalBiaya.getText() + "')";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
            koneksi.close();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan!", "Hasil", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void hapusData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            statement = koneksi.createStatement();
            String query = "DELETE FROM sewa_buku WHERE Nama='" + tNamaPenyewa.getText() + "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
            koneksi.close();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!", "Hasil", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void editData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            statement = koneksi.createStatement();
            String query = "UPDATE sewa_buku SET Judul='" + tJudulBuku.getText() + "', Jenis='" + tJenisBuku.getText() + "', NoTelp='" + tNoTelp.getText() + "', Durasi='" + tDurasiSewa.getText() + "', Biaya='" + tTotalBiaya.getText() + "' WHERE Nama='" + tNamaPenyewa.getText() + "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
            koneksi.close();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah!", "Hasil", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewBuku();
    }
}
