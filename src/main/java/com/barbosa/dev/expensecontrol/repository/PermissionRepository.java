package com.barbosa.dev.expensecontrol.repository;

import com.barbosa.dev.expensecontrol.enums.RoleName;
import com.barbosa.dev.expensecontrol.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByName(RoleName roleName);

    Boolean existsByName(RoleName roleName);
}
