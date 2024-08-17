package com.efrino.seleksisei.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efrino.seleksisei.exception.ResourceNotFoundException;
import com.efrino.seleksisei.model.Lokasi;
import com.efrino.seleksisei.model.Proyek;
import com.efrino.seleksisei.repository.LokasiRepository;
import com.efrino.seleksisei.repository.ProyekRepository;

@Service
public class LokasiService {

    @Autowired
    private LokasiRepository lokasiRepository;

    @Autowired
    private ProyekRepository proyekRepository;

    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    public Lokasi getLokasiById(Long lokasiId) {
        return lokasiRepository.findById(lokasiId)
            .orElseThrow(() -> new ResourceNotFoundException("Lokasi not found for this id :: " + lokasiId));
    }

    public Lokasi addLokasi(Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    public Lokasi updateLokasi(Long lokasiId, Lokasi lokasiDetails) {
        Lokasi lokasi = getLokasiById(lokasiId);

        lokasi.setNamaLokasi(lokasiDetails.getNamaLokasi());
        lokasi.setNegara(lokasiDetails.getNegara());
        lokasi.setProvinsi(lokasiDetails.getProvinsi());
        lokasi.setKota(lokasiDetails.getKota());

        return lokasiRepository.save(lokasi);
    }

    public void deleteLokasi(Long lokasiId) {
        Lokasi lokasi = getLokasiById(lokasiId);

        // Remove location from associated projects
        if (!lokasi.getProyekSet().isEmpty()) {
            for (Proyek proyek : lokasi.getProyekSet()) {
                proyek.removeLokasi(lokasi);
                proyekRepository.save(proyek);
            }
        }

        lokasiRepository.delete(lokasi);
    }
}
