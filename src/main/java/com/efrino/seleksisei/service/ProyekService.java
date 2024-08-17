package com.efrino.seleksisei.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efrino.seleksisei.exception.ResourceNotFoundException;
import com.efrino.seleksisei.model.Lokasi;
import com.efrino.seleksisei.model.Proyek;
import com.efrino.seleksisei.repository.LokasiRepository;
import com.efrino.seleksisei.repository.ProyekRepository;

@Service
public class ProyekService {

    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    public Proyek getProyekById(Long proyekId) {
        return proyekRepository.findById(proyekId)
            .orElseThrow(() -> new ResourceNotFoundException("Proyek not found for this id :: " + proyekId));
    }

    @Transactional
    public Proyek addProyek(Proyek proyek) {
        if (proyek.getLokasiSet() != null) {
            proyek.getLokasiSet().forEach(lokasi -> {
                Optional<Lokasi> lokasiEntity = lokasiRepository.findById(lokasi.getId());
                if (lokasiEntity.isPresent()) {
                    lokasi.setId(lokasiEntity.get().getId()); // Ensure the location is correctly referenced
                } else {
                    throw new ResourceNotFoundException("Lokasi not found for this id :: " + lokasi.getId());
                }
            });
        }
        return proyekRepository.save(proyek);
    }

    @Transactional
    public Proyek updateProyek(Long proyekId, Proyek proyekDetails) {
        Proyek proyek = getProyekById(proyekId);
    
        proyek.setNamaProyek(proyekDetails.getNamaProyek());
        proyek.setClient(proyekDetails.getClient());
        proyek.setTglMulai(proyekDetails.getTglMulai());
        proyek.setTglSelesai(proyekDetails.getTglSelesai());
        proyek.setPimpinanProyek(proyekDetails.getPimpinanProyek());
        proyek.setKeterangan(proyekDetails.getKeterangan());
    
        // Update lokasiSet
        if (proyekDetails.getLokasiSet() != null) {
            Set<Lokasi> updatedLokasiSet = new HashSet<>();
            proyekDetails.getLokasiSet().forEach(lokasi -> {
                Optional<Lokasi> lokasiEntity = lokasiRepository.findById(lokasi.getId());
                if (lokasiEntity.isPresent()) {
                    updatedLokasiSet.add(lokasiEntity.get());
                } else {
                    throw new ResourceNotFoundException("Lokasi not found for this id :: " + lokasi.getId());
                }
            });
            proyek.setLokasiSet(updatedLokasiSet);
        }
    
        return proyekRepository.save(proyek);
    }
    


    @Transactional
    public void deleteProyek(Long proyekId) {
        Proyek proyek = getProyekById(proyekId);
        proyekRepository.delete(proyek);
    }
}
