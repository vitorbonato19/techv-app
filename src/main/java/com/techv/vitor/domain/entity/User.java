package com.techv.vitor.domain.entity;

import com.techv.vitor.entity.Roles;
import com.techv.vitor.entity.Sector;
import com.techv.vitor.infrastructure.entity.RoleEntity;
import com.techv.vitor.infrastructure.entity.TicketEntity;

import java.util.List;
import java.util.Set;

public record User(String username,
                   String email,
                   String password,
                   String zipcode,
                   Set<RoleEntity> role,
                   List<TicketEntity> tickets) {
}
