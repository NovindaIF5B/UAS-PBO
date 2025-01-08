package com.tutorial.uswatunhasanah;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mahasiswa {
    private final StringProperty nama;
    private final StringProperty jurusan;
    private final StringProperty angkatan;
    private final StringProperty ipk;

    // Constructor
    public Mahasiswa(String nama, String jurusan, String angkatan, String ipk) {
        this.nama = new SimpleStringProperty(nama);
        this.jurusan = new SimpleStringProperty(jurusan);
        this.angkatan = new SimpleStringProperty(angkatan);
        this.ipk = new SimpleStringProperty(ipk);
    }

    // Getter dan Setter
    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public String getJurusan() {
        return jurusan.get();
    }

    public void setJurusan(String jurusan) {
        this.jurusan.set(jurusan);
    }

    public StringProperty jurusanProperty() {
        return jurusan;
    }

    public String getAngkatan() {
        return angkatan.get();
    }

    public void setAngkatan(String angkatan) {
        this.angkatan.set(angkatan);
    }

    public StringProperty angkatanProperty() {
        return angkatan;
    }

    public String getIpk() {
        return ipk.get();
    }

    public void setIpk(String ipk) {
        this.ipk.set(ipk);
    }

    public StringProperty ipkProperty() {
        return ipk;
    }

    // Override toString
    @Override
    public String toString() {
        return "Mahasiswa{" +
                "nama='" + nama.get() + '\'' +
                ", jurusan='" + jurusan.get() + '\'' +
                ", angkatan='" + angkatan.get() + '\'' +
                ", ipk='" + ipk.get() + '\'' +
                '}';
    }
}
