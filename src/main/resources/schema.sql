CREATE TABLE lokasi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_lokasi VARCHAR(255),
    negara VARCHAR(255),
    provinsi VARCHAR(255),
    kota VARCHAR(255)
);

CREATE TABLE proyek (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_proyek VARCHAR(255),
    client VARCHAR(255),
    tgl_mulai TIMESTAMP,
    tgl_selesai TIMESTAMP,
    pimpinan_proyek VARCHAR(255),
    keterangan TEXT
);

CREATE TABLE proyek_lokasi (
    proyek_id INT,
    lokasi_id INT,
    PRIMARY KEY (proyek_id, lokasi_id),
    FOREIGN KEY (proyek_id) REFERENCES proyek(id),
    FOREIGN KEY (lokasi_id) REFERENCES lokasi(id)
);
