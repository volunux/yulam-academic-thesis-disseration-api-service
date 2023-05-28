package com.yulam.acalib.service.impl;

import com.yulam.acalib.model.domain.Member;
import com.yulam.acalib.model.security.UserDetailsImpl;
import com.yulam.acalib.repository.jpa.MemberJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Primary
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final MemberJpaRepository repository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
    Member member = repository.findByEmailAddress(emailAddress);

    if (member == null) {
      throw new UsernameNotFoundException(emailAddress);
    }
    return UserDetailsImpl.build(member);
  }
}
