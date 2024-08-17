package com.efrino.seleksisei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efrino.seleksisei.model.Proyek;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Long> {
}
