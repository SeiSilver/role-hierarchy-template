package com.spring.template.silver.app.usecase.testing;

import com.spring.template.silver.app.infrastructure.entity.AccountEntity;
import com.spring.template.silver.app.infrastructure.entity.RoleEntity;
import com.spring.template.silver.app.infrastructure.enums.AccountStatus;
import com.spring.template.silver.app.infrastructure.enums.RoleType;
import com.spring.template.silver.app.infrastructure.jpa.AccountRepository;
import com.spring.template.silver.app.infrastructure.jpa.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Configuration
@AllArgsConstructor
@Profile({"local"})
public class UserDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

  private final AccountRepository userRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    createDefaultUserData();
  }

  private void createDefaultUserData() {
    Optional<RoleEntity> adminRole = roleRepository.findByRoleName(RoleType.ROLE_ADMIN);

    if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
      AccountEntity adminUser = AccountEntity.builder()
        .email("admin@gmail.com")
        .passwordHash(passwordEncoder.encode("123456"))
        .fullName("Admin")
        .status(AccountStatus.ACTIVE)
        .roles(List.of(adminRole.get()))
        .build();
      userRepository.save(adminUser);
    }

    Optional<RoleEntity> userRole = roleRepository.findByRoleName(RoleType.ROLE_ADMIN);

    if (userRepository.findByEmail("user@gmail.com").isEmpty()) {
      AccountEntity user = AccountEntity.builder()
        .email("user@gmail.com")
        .passwordHash(passwordEncoder.encode("123456"))
        .fullName("User")
        .status(AccountStatus.ACTIVE)
        .roles(List.of(userRole.get()))
        .build();
      userRepository.save(user);
    }

    Optional<RoleEntity> staffRole = roleRepository.findByRoleName(RoleType.ROLE_STAFF);

    if (userRepository.findByEmail("staff@gmail.com").isEmpty()) {
      AccountEntity staff = AccountEntity.builder()
        .email("staff@gmail.com")
        .passwordHash(passwordEncoder.encode("123456"))
        .fullName("Staff")
        .status(AccountStatus.ACTIVE)
        .roles(List.of(staffRole.get()))
        .build();
      userRepository.save(staff);
    }

    Optional<RoleEntity> moderatorRole = roleRepository.findByRoleName(RoleType.ROLE_MODERATOR);

    if (userRepository.findByEmail("moderator@gmail.com").isEmpty()) {
      AccountEntity moderator = AccountEntity.builder()
        .email("moderator@gmail.com")
        .passwordHash(passwordEncoder.encode("123456"))
        .fullName("Moderator")
        .status(AccountStatus.ACTIVE)
        .roles(List.of(moderatorRole.get()))
        .build();
      userRepository.save(moderator);
    }

  }

}
