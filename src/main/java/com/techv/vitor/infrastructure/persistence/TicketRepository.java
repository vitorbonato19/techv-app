package com.techv.vitor.infrastructure.persistence;

import com.techv.vitor.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public Page<Ticket> findAll(Pageable pageable);
}
