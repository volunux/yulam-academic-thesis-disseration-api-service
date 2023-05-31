package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.MemberStatus;
import com.yulam.acalib.model.dto.MemberStatusDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;

import java.util.List;

public interface MemberStatusService {

  MemberStatus getMemberStatus(Integer id);

  List<MemberStatus> getMemberStatuses();

  MemberStatus saveMemberStatus(MemberStatusDto dto);

  MemberStatus updateMemberStatus(Integer id, MemberStatusDto dto);

  void deleteMany(DeleteIdsDto ids);

  void deleteAllMemberStatus();

  boolean isMemberStatusExists(Integer id);
}
