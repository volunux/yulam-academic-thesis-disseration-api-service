package com.yulam.acalib.configuration.security;

import com.yulam.acalib.model.domain.Member;
import com.yulam.acalib.model.domain.Role;
import com.yulam.acalib.repository.jpa.MemberJpaRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final MemberJpaRepository repository;
  private final PasswordEncoder passwordEncoder;

  public JwtAuthenticationProvider(MemberJpaRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String emailAddress = authentication.getName();
    String password = authentication.getCredentials().toString();

    Member member = repository.findByEmailAddress(emailAddress);

    if (member != null) {
      if (passwordEncoder.matches(password, member.getPassword())) {
        return new UsernamePasswordAuthenticationToken(emailAddress, password, buildRoles(member.getRoles()));
      }
    }

    throw new BadCredentialsException("Username or password not correct");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
  
  private List<GrantedAuthority> buildRoles(Set<Role> roles) {
    return roles
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getCode()))
            .collect(Collectors.toList());
  }
}
