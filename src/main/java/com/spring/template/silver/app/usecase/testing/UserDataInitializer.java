package com.spring.template.silver.app.usecase.testing;

import com.spring.template.silver.app.infrastructure.entity.AccountEntity;
import com.spring.template.silver.app.infrastructure.entity.RoleEntity;
import com.spring.template.silver.app.infrastructure.enums.AccountStatus;
import com.spring.template.silver.app.infrastructure.jpa.AccountRepository;
import com.spring.template.silver.app.infrastructure.jpa.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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
    List<RoleEntity> allRoles = roleRepository.findAll();

    if (userRepository.findByEmail("superadmin@gmail.com").isEmpty()) {
      AccountEntity adminUser = AccountEntity.builder()
          .email("superadmin@gmail.com")
          .passwordHash(passwordEncoder.encode("admin@123"))
          .fullName("Admin")
          .status(AccountStatus.ACTIVE)
          .roles(allRoles)
          .build();
      userRepository.save(adminUser);
    }

    if (userRepository.findByEmail("datpd.dev@gmail.com").isEmpty()) {
      AccountEntity adminUser = AccountEntity.builder()
          .email("datpd.dev@gmail.com")
          .passwordHash(passwordEncoder.encode("admin@123"))
          .fullName("Silver")
          .status(AccountStatus.ACTIVE)
          .roles(allRoles)
          .build();
      userRepository.save(adminUser);
    }
  }

}
