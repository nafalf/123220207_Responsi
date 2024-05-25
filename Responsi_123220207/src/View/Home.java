package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author rafli
 */
public class Home extends JFrame implements ActionListener {
    JLabel header = new JLabel("Selamat Datang!");
    JLabel subheader = new JLabel("Silakan masuk untuk melanjutkan.");
    JLabel labelUsername = new JLabel("Username");
    JLabel labelPassword = new JLabel("Password");
    JLabel labelJenisKelamin = new JLabel("Jenis Kelamin");

    JTextField inputUsername = new JTextField();
    JTextField inputPassword = new JTextField();

    JRadioButton pilihLaki = new JRadioButton("Laki-laki");
    
    // Parameter "true" memberikan nilai default pada radio button
    JRadioButton pilihPerempuan = new JRadioButton("Perempuan");

    JButton tombolLogin = new JButton("Login");

    public Home() {
        setVisible(true);
        setSize(480, 480);
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        add(header);
        add(subheader);
        add(labelUsername);
        add(labelPassword);
        add(labelJenisKelamin);
        add(inputUsername);
        add(inputPassword);
        add(pilihLaki);
        add(pilihPerempuan);
        add(tombolLogin);

        header.setFont(header.getFont().deriveFont(20.0f));
        header.setBounds(20, 20, 440, 24);
        subheader.setBounds(20, 50, 440, 12);

        labelUsername.setBounds(20, 84, 440, 12);
        inputUsername.setBounds(18, 100, 440, 32);

        labelPassword.setBounds(20, 150, 440, 12);
        inputPassword.setBounds(18, 166, 440, 32);

        ButtonGroup pilihJenisKelamin = new ButtonGroup();
        pilihJenisKelamin.add(pilihLaki);
        pilihJenisKelamin.add(pilihPerempuan);

        labelJenisKelamin.setBounds(20, 216, 440, 12);

        pilihLaki.setBounds(14, 232, 128, 32);
        pilihPerempuan.setBounds(224, 232, 128, 32);

        tombolLogin.setBounds(14, 280, 452, 36);
        tombolLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Mengambil nilai dari username dan password yang diinput user di textfield
            String username = inputUsername.getText();
            String password = inputPassword.getText();
            
            // Mengambil nilai dari jenis kelamin yang dipilih
            String jenisKelamin = pilihLaki.isSelected() ? "l" : "p";
            
            // Memberikan error handling jika username atau password kosong
            if (username.isEmpty() || password.isEmpty()) {
                throw new Exception("Jangan kosong, Gus!");
            }
            
            // Pindah ke halaman Home
            new Menu(username, jenisKelamin);
            
            // Hapus halaman saat ini (Login)
            this.dispose();
        } catch (Exception error) {
            // Menampilkan error dalam bentuk popup
            JOptionPane.showMessageDialog(this, error.getMessage());
        }
    }
}
