package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.Role;
import com.yulam.acalib.model.dto.RoleDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;

import java.util.List;

public interface RoleService {

  Role getRole(Integer id);

  List<Role> getRoles();

  Role saveRole(RoleDto dto);

  Role updateRole(Integer id, RoleDto dto);

  void deleteMany(DeleteIdsDto ids);

  void deleteAllRole();

  boolean isRoleExists(Integer id);
}
