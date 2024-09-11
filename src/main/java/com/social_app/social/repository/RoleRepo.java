package com.social_app.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social_app.social.models.RoleModel;

public interface RoleRepo extends JpaRepository<RoleModel, Long> {

}
