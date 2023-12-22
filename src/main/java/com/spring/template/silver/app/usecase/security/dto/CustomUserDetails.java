package com.spring.template.silver.app.usecase.security.dto;

import com.spring.template.silver.app.infrastructure.entity.AccountEntity;
import com.spring.template.silver.app.infrastructure.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record CustomUserDetails(AccountEntity accountEntity) implements UserDetails {

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getAuthorityList(accountEntity);
  }

  public static List<GrantedAuthority> getAuthorityList(AccountEntity accountEntity) {
    Set<GrantedAuthority> grantList = new HashSet<>();
    List<RoleEntity> roles = accountEntity.getRoles();
    for (RoleEntity role : roles) {
      grantList.add(new SimpleGrantedAuthority(role.getRoleName().name()));
      grantList.addAll(role.getPermissions().stream().map(
        i -> new SimpleGrantedAuthority(i.getPermissionName().name())
      ).toList());
    }
    return new ArrayList<>(grantList);
  }

  @Override
  public String getPassword() {
    return accountEntity.getPasswordHash();
  }

  @Override
  public String getUsername() {
    return accountEntity.getFullName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  @Override
  public AccountEntity accountEntity() {
    return accountEntity;
  }

}
