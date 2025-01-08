package com.tutorial.uswatunhasanah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    public Button tambahButton;
    public Button ubahButton;
    public Button hapusButton;
    public Button resetButton;
    @FXML
    private TextField nameField;

    @FXML
    private TextField jurusanField;

    @FXML
    private TextField angkatanField;

    @FXML
    private TextField ipkField;

    @FXML
    private TableView<Mahasiswa> tableView;

    @FXML
    private TableColumn<Mahasiswa, String> namaColumn;

    @FXML
    private TableColumn<Mahasiswa, String> jurusanColumn;

    @FXML
    private TableColumn<Mahasiswa, String> angkatanColumn;

    @FXML
    private TableColumn<Mahasiswa, String> ipkColumn;

    private ObservableList<Mahasiswa> mahasiswaList;

    @FXML
    public void initialize() {
        mahasiswaList = FXCollections.observableArrayList();

        // Map table columns to Mahasiswa properties
        namaColumn.setCellValueFactory(data -> data.getValue().namaProperty());
        jurusanColumn.setCellValueFactory(data -> data.getValue().jurusanProperty());
        angkatanColumn.setCellValueFactory(data -> data.getValue().angkatanProperty());
        ipkColumn.setCellValueFactory(data -> data.getValue().ipkProperty());

        tableView.setItems(mahasiswaList);

        // Listener untuk mengisi TextField saat baris di tabel dipilih
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fillFields(newValue);
            }
        });
    }

    @FXML
    public void btnTambah(ActionEvent event) {
        String nama = nameField.getText();
        String jurusan = jurusanField.getText();
        String angkatan = angkatanField.getText();
        String ipk = ipkField.getText();

        System.out.println("Tambah data mahasiswa: ");
        System.out.println("Nama: " + nama);
        System.out.println("Jurusan: " + jurusan);
        System.out.println("Angkatan: " + angkatan);
        System.out.println("IPK: " + ipk);

        // Validasi IPK
        try {
            double ipkValue = Double.parseDouble(ipk);
            if (ipkValue < 0 || ipkValue > 4) {
                throw new NumberFormatException("IPK harus di antara 0 dan 4.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "IPK tidak valid! Harus berupa angka antara 0 dan 4.", Alert.AlertType.ERROR);
            return; // Stop eksekusi lebih lanjut jika IPK tidak valid
        }

        if (!nama.isEmpty() && !jurusan.isEmpty() && !angkatan.isEmpty() && !ipk.isEmpty()) {
            Mahasiswa mahasiswa = new Mahasiswa(nama, jurusan, angkatan, ipk);
            mahasiswaList.add(mahasiswa);

            // Tampilkan informasi yang ditambahkan
            System.out.println("Data mahasiswa ditambahkan: " + mahasiswa);

            resetFields();
        } else {
            showAlert("Error", "Semua field harus diisi!", Alert.AlertType.ERROR);
        }
    }



    @FXML
    public void btnUbah(ActionEvent event) {
        Mahasiswa selectedMahasiswa = tableView.getSelectionModel().getSelectedItem();
        if (selectedMahasiswa != null) {
            String nama = nameField.getText();
            String jurusan = jurusanField.getText();
            String angkatan = angkatanField.getText();
            String ipk = ipkField.getText();

            // Validasi IPK
            try {
                // Cek apakah IPK valid
                double ipkValue = Double.parseDouble(ipk);
                if (ipkValue < 0 || ipkValue > 4) {
                    throw new NumberFormatException("IPK harus di antara 0 dan 4.");
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "IPK tidak valid! Harus berupa angka antara 0 dan 4.", Alert.AlertType.ERROR);
                return; // Stop eksekusi lebih lanjut jika IPK tidak valid
            }

            // Pastikan semua field diisi
            if (!nama.isEmpty() && !jurusan.isEmpty() && !angkatan.isEmpty() && !ipk.isEmpty()) {
                // Update data mahasiswa yang dipilih
                selectedMahasiswa.setNama(nama);
                selectedMahasiswa.setJurusan(jurusan);
                selectedMahasiswa.setAngkatan(angkatan);
                selectedMahasiswa.setIpk(ipk);

                tableView.refresh(); // Refresh tabel untuk menampilkan perubahan
                resetFields();
            } else {
                showAlert("Error", "Semua field harus diisi!", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Error", "Pilih mahasiswa yang ingin diubah!", Alert.AlertType.ERROR);
        }
    }


    @FXML
    public void btnHapus(ActionEvent event) {
        Mahasiswa selectedMahasiswa = tableView.getSelectionModel().getSelectedItem();
        if (selectedMahasiswa != null) {
            mahasiswaList.remove(selectedMahasiswa);
        } else {
            showAlert("Error", "Pilih mahasiswa yang ingin dihapus!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void btnReset(ActionEvent event) {
        // Menghapus semua data di ObservableList (ini akan mengosongkan tabel)
        mahasiswaList.clear();

        // Mengosongkan semua TextField
        resetFields();

        // Jika Anda ingin memberi tahu pengguna bahwa data telah direset, Anda bisa menggunakan alert:
        // showAlert("Info", "Data telah direset.", Alert.AlertType.INFORMATION);
    }


    private void resetFields() {
        nameField.clear();
        jurusanField.clear();
        angkatanField.clear();
        ipkField.clear();
        tableView.getSelectionModel().clearSelection(); // Hapus seleksi di tabel
    }

    private void fillFields(Mahasiswa mahasiswa) {
        nameField.setText(mahasiswa.getNama());
        jurusanField.setText(mahasiswa.getJurusan());
        angkatanField.setText(mahasiswa.getAngkatan());
        ipkField.setText(mahasiswa.getIpk());
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
