package nz.net.osnz.app.model

import nz.net.osnz.app.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * @author Kefeng Deng (deng@51any.com)
 */
class MyUserDetails implements UserDetails {

  /**
   * User model object
   */
  User user

  MyUserDetails(User user) {
    this.user = user
  }

  @Override
  Collection<? extends GrantedAuthority> getAuthorities() {
    return null
  }

  @Override
  String getPassword() {
    return user.password
  }

  @Override
  String getUsername() {
    return user.username
  }

  @Override
  boolean isAccountNonExpired() {
    return true
  }

  @Override
  boolean isAccountNonLocked() {
    return true
  }

  @Override
  boolean isCredentialsNonExpired() {
    return true
  }

  @Override
  boolean isEnabled() {
    return user.active
  }

}
