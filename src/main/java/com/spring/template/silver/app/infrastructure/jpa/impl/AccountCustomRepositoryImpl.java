package com.spring.template.silver.app.infrastructure.jpa.impl;

import com.spring.template.silver.app.infrastructure.entity.AccountEntity;
import com.spring.template.silver.app.infrastructure.jpa.AccountCustomRepository;
import com.spring.template.silver.app.infrastructure.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;

@Repository
@RequiredArgsConstructor
public class AccountCustomRepositoryImpl implements AccountCustomRepository {

  private final EntityManager entityManager;

  @Override
  public void update(Long id, UserUpdateRequest dto) {
    Assert.notNull(id, "Id should not be null");
    Assert.notNull(dto, "Update object should not be null");

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaUpdate<AccountEntity> criteriaUpdate = criteriaBuilder
        .createCriteriaUpdate(AccountEntity.class);
    Root<AccountEntity> root = criteriaUpdate.from(AccountEntity.class);

    if (StringUtils.hasText(dto.getFullName())) {
      criteriaUpdate.set(AccountEntity.Fields.fullName, dto.getFullName());
    }

    criteriaUpdate.set(AccountEntity.Fields.modifiedAt, ZonedDateTime.now());
    criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));
    entityManager.createQuery(criteriaUpdate).executeUpdate();

  }
}
