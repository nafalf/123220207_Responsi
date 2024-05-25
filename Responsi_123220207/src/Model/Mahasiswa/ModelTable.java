package Model.Mahasiswa;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {

    // Berfungsi untuk menyimpan daftar mahasiswa
    List<ModelMahasiswa> daftarMahasiswa;

    String kolom[] = {"ID", "Nama", "NIM", "Angkatan"};

    public ModelTable(List<ModelMahasiswa> daftarMahasiswa) {
        this.daftarMahasiswa = daftarMahasiswa;
    }

    // Method untuk mengambil jumlah baris dari tabel
    @Override
    public int getRowCount() {
        return daftarMahasiswa.size();
    }

    // Method untuk mengambil jumlah kolom dari tabel
    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return daftarMahasiswa.get(rowIndex).getId();
            case 1:
                return daftarMahasiswa.get(rowIndex).getNama();
            case 2:
                return daftarMahasiswa.get(rowIndex).getNim();
            case 3:
                return daftarMahasiswa.get(rowIndex).getAngkatan();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}
