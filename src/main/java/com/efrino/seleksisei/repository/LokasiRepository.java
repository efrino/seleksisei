package com.efrino.seleksisei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efrino.seleksisei.model.Lokasi;


@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {
}
