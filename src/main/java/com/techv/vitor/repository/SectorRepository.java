package com.techv.vitor.repository;

import com.techv.vitor.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    Sector findByName(String name);

    @Query(nativeQuery = true, value = "select count(*) from centraltechv.sectors")
    long count();

    @Query(nativeQuery = true, value = "select id from centraltechv.sectors")
    long findId(String name);
}
