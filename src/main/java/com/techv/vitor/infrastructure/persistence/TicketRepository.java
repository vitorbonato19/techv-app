package com.techv.vitor.infrastructure.persistence;

import com.techv.vitor.infrastructure.entity.TicketEntity;
import com.techv.vitor.infrastructure.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    public Page<TicketEntity> findAll(Pageable pageable);
}
