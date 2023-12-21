package com.spring.template.silver.app.infrastructure.entity;

import com.spring.template.silver.app.infrastructure.enums.AccountStatus;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EntityListeners(AuditingEntityListener.class)
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String email;

  private String passwordHash;

  private String fullName;

  private String avatarUrl;

  @Enumerated(EnumType.STRING)
  private AccountStatus status;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "account_role",
      joinColumns = @JoinColumn(name = "account_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<RoleEntity> roles;

  @LastModifiedDate
  private ZonedDateTime modifiedAt;

  @CreatedDate
  private ZonedDateTime createdAt;

  @Override
  public String toString() {
    return "AccountEntity{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", passwordHash='" + passwordHash + '\'' +
        ", fullName='" + fullName + '\'' +
        ", avatarUrl='" + avatarUrl + '\'' +
        ", status=" + status +
        ", roles=" + roles +
        ", modifiedAt=" + modifiedAt +
        ", createdAt=" + createdAt +
        '}';
  }
}