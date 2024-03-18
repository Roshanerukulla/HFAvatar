package com.hf.avatar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hf.avatar.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    // You can add custom query methods if needed
}
