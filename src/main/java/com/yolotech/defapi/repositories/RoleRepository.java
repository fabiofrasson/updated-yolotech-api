package com.yolotech.defapi.repositories;

import com.yolotech.defapi.domain.Category;
import com.yolotech.defapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {}
