package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.RoleNotFoundException;
import com.yulam.acalib.model.domain.Role;
import com.yulam.acalib.model.dto.RoleDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.RoleJpaRepository;
import com.yulam.acalib.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

  private final RoleJpaRepository roleJpaRepository;

  public RoleServiceImpl(RoleJpaRepository roleJpaRepository) {
    this.roleJpaRepository = roleJpaRepository;
  }

  @Override
  public Role getRole(Integer id) {
    return roleJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("Role does not exists or cannot be found. ID: %d", id);
              return new RoleNotFoundException(msg);
            });
  }

  @Override
  public List<Role> getRoles() {
    return roleJpaRepository.findAll();
  }

  @Override
  public Role saveRole(RoleDto dto) {
    Role Role = dto.toRole();
    return roleJpaRepository.save(Role);
  }

  @Override
  public Role updateRole(Integer id, RoleDto dto) {
    getRole(id);
    Role Role = dto.toRole();
    Role.setId(id);
    return roleJpaRepository.save(Role);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<Role> roles = dto
            .getIds()
            .stream()
            .map(id -> Role.
                    builder()
                    .id(id)
                    .build())
            .collect(Collectors.toList());

    roleJpaRepository.deleteAll(roles);
  }

  @Override
  public void deleteAllRole() {
    roleJpaRepository.deleteAll();
  }

  @Override
  public boolean isRoleExists(Integer id) {
    System.out.println("Checking role id : " + id);
    return roleJpaRepository.findById(id).isPresent();
  }

}
