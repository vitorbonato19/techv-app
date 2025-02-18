package com.techv.vitor.service;

import com.techv.vitor.entity.Sector;
import com.techv.vitor.repository.RoleRepository;
import com.techv.vitor.repository.SectorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @PostConstruct
    public void insert() {
        if(sectorRepository.count() == 0) {
            sectorRepository.save(new Sector(null, "TI"));
            sectorRepository.save(new Sector(null, "ADM"));
            sectorRepository.save(new Sector(null, "MASTER"));
        }
    }
}
