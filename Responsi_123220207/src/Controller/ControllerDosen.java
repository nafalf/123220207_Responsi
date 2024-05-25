package Controller;

import Model.Dosen.*;
import View.Dosen.*;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerDosen {

    ViewData halamanTable;
    InputData halamanInput;
    EditData halamanEdit;

    InterfaceDAODosen daoDosen;

    // Membuat variabel "daftarDosen" untuk menyimpan data dosen yg diambil dari DB.
    List<ModelDosen> daftarDosen;

    public ControllerDosen(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoDosen = new DAODosen();
    }

    public ControllerDosen(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoDosen = new DAODosen();
    }

    public ControllerDosen(EditData halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoDosen = new DAODosen();
    }

    public void showAllDosen() {
        /*
          Mengambil daftar dosen dari database, 
          kemudian disimpan ke dalam variabel bernama list.
         */
        daftarDosen = daoDosen.getAll();

        ModelTable table = new ModelTable(daftarDosen);

        halamanTable.getTableDosen().setModel(table);
    }

    public void insertDosen() {
        try {
            // Membuat "dosen baru" yang isinya masih kosong
            ModelDosen dosenBaru = new ModelDosen();

            /*
              Mengambil input nama dan nidn menggunakan getter yang telah dibuat di view
              Nilai dari input kemudian disimpan ke dalam variabel "nama" dan "nidn".
             */
            String nama = halamanInput.getInputNama();
            String no_hp = halamanInput.getInputNo_hp();
            String email = halamanInput.getInputEmail();

            /*
              Mengecek apakah input dari nama atau no_hp,  kosong/tidak.
              Jika kosong, maka buatlah sebuah exception.
             */
            if ("".equals(nama) || "".equals(no_hp) || "".equals(email)) {
                throw new Exception("Nama, No Hp dan Email tidak boleh kosong!");
            }

            // Mengisi nama dan nidn dari "dosen baru" yang dibuat tadi.
            dosenBaru.setNama(nama);
            dosenBaru.setNo_hp(no_hp);
            dosenBaru.setEmail(email);

            // Memasukkan "dosen baru" ke dalam database.
            daoDosen.insert(dosenBaru);

            // Menampilkan pop-up ketika berhasil mengedit data
            JOptionPane.showMessageDialog(null, "Dosen baru berhasil ditambahkan.");

            // Terakhir, program akan pindah ke halaman Table Dosen
            halamanInput.dispose();
            new ViewData();
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void editDosen(int id) {
        try {
            ModelDosen dosenYangMauDiedit = new ModelDosen();

            String nama = halamanEdit.getInputNama();
            String no_hp = halamanEdit.getInputNo_hp();
            String email = halamanEdit.getInputEmail();

            /*
              Mengecek apakah input dari nama atau nidn kosong/tidak.
              Jika kosong, maka buatlah sebuah exception.
             */
            if ("".equals(nama) || "".equals(no_hp) || "".equals(email)) {
                throw new Exception("Nama, No Hp, Email tidak boleh kosong!");
            }

            // Mengisi id, nama dan nidn dari "dosen baru" yang dibuat tadi.
            dosenYangMauDiedit.setId(id);
            dosenYangMauDiedit.setNama(nama);
            dosenYangMauDiedit.setNo_hp(no_hp);
            dosenYangMauDiedit.setEmail(email);

            // Memasukkan "dosen baru" ke dalam database.
            daoDosen.update(dosenYangMauDiedit);

            // Menampilkan pop-up ketika berhasil mengedit data
            JOptionPane.showMessageDialog(null, "Data dosen berhasil diubah.");

            // Terakhir, program akan pindah ke halaman Table Dosen
            halamanEdit.dispose();
            new ViewData();
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteDosen(Integer baris) {
        // Mengambil id dan nama berdasarkan baris yang dipilih
        Integer id = (int) halamanTable.getTableDosen().getValueAt(baris, 0);
        String nama = halamanTable.getTableDosen().getValueAt(baris, 1).toString();

        // Membuat Pop-Up untuk mengonfirmasi apakah ingin menghapus data
        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );

        // Jika user memilih opsi "yes", maka hapus data.
        if (input == 0) {
            daoDosen.delete(id);

            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllDosen();
        }
    }
}
