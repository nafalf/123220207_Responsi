package View.Mahasiswa;

import Controller.ControllerMahasiswa;
import Model.Mahasiswa.ModelMahasiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditData extends JFrame {
    // Membuat sebuah instance bernama controller dari class "ControllerMahasiswa".
    ControllerMahasiswa controller;
    
    JLabel header = new JLabel("Edit Mahasiswa");
    JLabel labelInputNama = new JLabel("Nama");
    JLabel labelInputNIM = new JLabel("NIM");
    JLabel labelInputAngkatan = new JLabel("Angkatan");
    JTextField inputNama = new JTextField();
    JTextField inputNIM = new JTextField();
    JTextField inputAngkatan = new JTextField();
    JButton tombolEdit = new JButton("Edit Mahasiswa");
    JButton tombolKembali = new JButton("Kembali");

    public EditData(ModelMahasiswa mahasiswa) {
        setTitle("Edit Mahasiswa");
        setVisible(true);
        setSize(480, 240);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(header);
        add(labelInputNama);
        add(labelInputNIM);
        add(labelInputAngkatan);
        add(inputNama);
        add(inputNIM);
        add(inputAngkatan);
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelInputNama.setBounds(20, 32, 440, 24);
        inputNama.setBounds(18, 56, 440, 36);
        labelInputNIM.setBounds(20, 96, 440, 24);
        inputNIM.setBounds(18, 120, 440, 36);
        labelInputAngkatan.setBounds(20, 96, 440, 24);
        inputAngkatan.setBounds(18, 120, 440, 36);
        tombolKembali.setBounds(20, 160, 215, 40);
        tombolEdit.setBounds(240, 160, 215, 40);
        
        // Masukkin nama dan nim yang didapat dari halaman sebelumnya.
        inputNama.setText(mahasiswa.getNama());
        inputNIM.setText(mahasiswa.getNim());
        inputAngkatan.setText(mahasiswa.getAngkatan());
        
        controller = new ControllerMahasiswa(this);

        /* 
          Memberikan event handling untuk tombol kembali,
          Ketika tombol kembali diklik, maka akan kembali ke halaman ViewData().
         */
        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewData();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.editMahasiswa(mahasiswa.getId());
            }
        });
    }
    
    /*
      Membuat sebuah getter untuk mengambil nilai dari form "inputNama".
      Kenapa perlu getter? karena nama yang diinput user akan digunakan di controller.
      Kita tidak bisa langsung mengambil isi dari input nama karena variabel "inputNama"
      memiliki modifier "default", yang artinya variabel tersebut tidak dapat diakses
      di package yang berbeda. Sebagaimana sturktur folder kita, 
      file ControllerMahasiswa.java dan file EditData.java 
      berada pada package yang berbeda.
    */
    public String getInputNama() {
        return inputNama.getText();
    }
    
    public String getInputNIM() {
        return inputNIM.getText();
    }

    public String getInputAngkatan() {
        return inputAngkatan.getText();
    }
}
