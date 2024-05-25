package View.Dosen;

import Controller.ControllerDosen;
import Model.Dosen.ModelDosen;
import java.awt.event.*;
import javax.swing.*;

public class EditData extends JFrame {
    // Membuat sebuah instance bernama controller dari class "ControllerDosen".
    ControllerDosen controller;
    
    JLabel header = new JLabel("Edit Dosen");
    JLabel labelInputNama = new JLabel("Nama");
    JLabel labelInputNo_hp = new JLabel("No_hp");
    JLabel labelInputEmail = new JLabel("Email");
    JTextField inputNama = new JTextField();
    JTextField inputNo_hp = new JTextField();
    JTextField inputEmail = new JTextField();
    JButton tombolEdit = new JButton("Edit Dosen");
    JButton tombolKembali = new JButton("Kembali");

    public EditData(ModelDosen dosen) {
        setTitle("Edit Dosen");
        setVisible(true);
        setSize(480, 240);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(header);
        add(labelInputNama);
        add(labelInputNo_hp);
        add(labelInputEmail);
        add(inputNama);
        add(inputNo_hp);
        add(inputEmail);
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelInputNama.setBounds(20, 32, 440, 24);
        inputNama.setBounds(18, 56, 440, 36);
        labelInputNo_hp.setBounds(20, 96, 440, 24);
        inputNo_hp.setBounds(18, 120, 440, 36);
        labelInputEmail.setBounds(20, 96, 440, 24);
        inputEmail.setBounds(18, 120, 440, 36);
        tombolKembali.setBounds(20, 160, 215, 40);
        tombolEdit.setBounds(240, 160, 215, 40);
        
        // Masukkin nama dan nidn yang didapat dari halaman sebelumnya.
        inputNama.setText(dosen.getNama());
        inputNo_hp.setText(dosen.getNo_hp());
        inputEmail.setText(dosen.getEmail());
        
        controller = new ControllerDosen(this);

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
                controller.editDosen(dosen.getId());
            }
        });
    }
    
    
    public String getInputNama() {
        return inputNama.getText();
    }
    
    public String getInputNo_hp() {
        return inputNo_hp.getText();
    } 
    
    public String getInputEmail() {
        return inputEmail.getText();
    } 
}
