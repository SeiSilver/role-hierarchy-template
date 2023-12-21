package com.spring.template.silver.app.infrastructure.jpa;

import com.spring.template.silver.app.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>, AccountCustomRepository {

  Optional<AccountEntity> findByEmail(String email);
  
}
