package com.spring.template.silver.app.infrastructure.jpa;

import com.spring.template.silver.app.infrastructure.enums.RoleType;
import com.spring.template.silver.app.infrastructure.entity.RoleEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

  Optional<RoleEntity> findByRoleName(RoleType roleType);

  @Query("SELECT u.roles FROM AccountEntity u WHERE u.id = ?1")
  List<RoleEntity> findRolesByAccountId(Integer userId);

}
