package Controller;

import Model.Mahasiswa.*;
import View.Mahasiswa.*;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerMahasiswa {

    ViewData halamanTable;
    InputData halamanInput;
    EditData halamanEdit;

    InterfaceDAOMahasiswa daoMahasiswa;

    // Membuat variabel "daftarMahasiswa" untuk menyimpan data mahasiswa yg diambil dari DB.
    List<ModelMahasiswa> daftarMahasiswa;
    
    
    /*
      Kalo temen-temen liat di sini, ada 3 fungsi contructor yang masing-masing memiliki
      parameter yang berbeda. Nah, hal ini disebut dengan "function overloading",
      yaitu sebuah fungsi yang memiliki nama sama tetapi memiliki parameter yang berbeda.
      
      Mengapa kita butuh "function overloading"?
      Karena dalam hal ini, controller mahasiswa akan digunakan pada 3 halaman atau 3 view yang berbeda, 
      yaitu Halaman Table, Halaman Input, dan Halaman Edit.
    */
    public ControllerMahasiswa(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoMahasiswa = new DAOMahasiswa();
    }
    
    public ControllerMahasiswa(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoMahasiswa = new DAOMahasiswa();
    }
    
    public ControllerMahasiswa(EditData halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoMahasiswa = new DAOMahasiswa();
    }

    public void showAllMahasiswa() {
        daftarMahasiswa = daoMahasiswa.getAll();

        ModelTable table = new ModelTable(daftarMahasiswa);

        // Mengisi tabel yang berada pada halaman Table Mahasiswa
        halamanTable.getTableMahasiswa().setModel(table);
    }

    public void insertMahasiswa() {
        try {
            // Membuat "mahasiswa baru" yang isinya masih kosong
            ModelMahasiswa mahasiswaBaru = new ModelMahasiswa();
            
            String nama = halamanInput.getInputNama();
            String nim = halamanInput.getInputNIM();
            String angkatan = halamanInput.getInputAngkatan();

            /*
              Mengecek apakah input dari nama atau nim kosong/tidak.
              Jika kosong, maka buatlah sebuah exception.
             */
            if ("".equals(nama) || "".equals(nim) || "".equals(angkatan)) {
                throw new Exception("Nama, NIM, Angkatan tidak boleh kosong!");
            }
            
            // Mengisi nama dan nim dari "mahasiswa baru" yang dibuat tadi.
            mahasiswaBaru.setNama(nama);
            mahasiswaBaru.setNim(nim);
            mahasiswaBaru.setAngkatan(angkatan);
            
            // Memasukkan "mahasiswa baru" ke dalam database.
            daoMahasiswa.insert(mahasiswaBaru);
            
            // Menampilkan pop-up ketika berhasil mengedit data
            JOptionPane.showMessageDialog(null, "Mahasiswa baru berhasil ditambahkan.");
            
            // Terakhir, program akan pindah ke halaman Table Mahasiswa()
            halamanInput.dispose();
            new ViewData();
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editMahasiswa(int id) {
        try {
            ModelMahasiswa mahasiswaYangMauDiedit = new ModelMahasiswa();
                        
            String nama = halamanEdit.getInputNama();
            String nim = halamanEdit.getInputNIM();
            String angkatan = halamanEdit.getInputAngkatan();
            if ("".equals(nama) || "".equals(nim) || "".equals(angkatan)) {
                throw new Exception("Nama, NIM, Angkatan tidak boleh kosong!");
            }
            
            // Mengisi id, nama dan nim dari "mahasiswa baru" yang dibuat tadi.
            mahasiswaYangMauDiedit.setId(id);
            mahasiswaYangMauDiedit.setNama(nama);
            mahasiswaYangMauDiedit.setNim(nim);
            mahasiswaYangMauDiedit.setAngkatan(angkatan);
            
            // Memasukkan "mahasiswa baru" ke dalam database.
            daoMahasiswa.update(mahasiswaYangMauDiedit);

            // Menampilkan pop-up ketika berhasil mengedit data
            JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil diubah.");

            // Terakhir, program akan pindah ke halaman Table Mahasiswa()
            halamanEdit.dispose();
            new ViewData();
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteMahasiswa(Integer baris) {
        // Mengambil id dan nama berdasarkan baris yang dipilih
        Integer id = (int) halamanTable.getTableMahasiswa().getValueAt(baris, 0);
        String nama = halamanTable.getTableMahasiswa().getValueAt(baris, 1).toString();

        // Membuat Pop-Up untuk mengonfirmasi apakah ingin menghapus data
        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        // Jika user memilih opsi "yes", maka hapus data.
        if (input == 0) {
            /* 
              Memanggil method delete() untuk menghaous data dari DB
              berdasarkan id yang dipilih.
            */
            daoMahasiswa.delete(id);
            
            // Menampilkan pop-up jika berhasil menghapus.
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            // Memanggil method "showAllMahasiswa()" untuk merefresh table.
            showAllMahasiswa();
        }
    }
}
