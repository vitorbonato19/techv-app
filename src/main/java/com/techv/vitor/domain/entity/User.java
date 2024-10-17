package com.techv.vitor.domain.entity;

import com.techv.vitor.entity.Roles;
import com.techv.vitor.entity.Sector;

import java.util.Set;

public record User(String username,
                   String email,
                   String password,
                   String zipcode,
                   Set<Role> role) {
}
