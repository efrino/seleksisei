package com.efrino.seleksisei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.efrino.seleksisei.model.Proyek;
import com.efrino.seleksisei.service.ProyekService;


@RestController
@RequestMapping("/proyek")
public class ProyekController {

    @Autowired
    private ProyekService proyekService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proyek addProyek(@RequestBody Proyek proyek) {
        return proyekService.addProyek(proyek);
    }

    @GetMapping
    public List<Proyek> getAllProyek() {
        return proyekService.getAllProyek();
    }

    @GetMapping("/{id}")
    public Proyek getProyekById(@PathVariable Long id) {
        return proyekService.getProyekById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable Long id, @RequestBody Proyek proyekDetails) {
        Proyek updatedProyek = proyekService.updateProyek(id, proyekDetails);
        return ResponseEntity.ok(updatedProyek);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProyek(@PathVariable Long id) {
        proyekService.deleteProyek(id);
    }
    
}
