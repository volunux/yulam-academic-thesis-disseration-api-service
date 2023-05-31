package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.MemberStatusNotFoundException;
import com.yulam.acalib.model.domain.MemberStatus;
import com.yulam.acalib.model.dto.MemberStatusDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.MemberStatusJpaRepository;
import com.yulam.acalib.service.MemberStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberStatusServiceImpl implements MemberStatusService {

  private final MemberStatusJpaRepository memberStatusJpaRepository;

  public MemberStatusServiceImpl(MemberStatusJpaRepository memberStatusJpaRepository) {
    this.memberStatusJpaRepository = memberStatusJpaRepository;
  }

  @Override
  public MemberStatus getMemberStatus(Integer id) {
    return memberStatusJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("Member Status does not exists or cannot be found. ID: %d", id);
              return new MemberStatusNotFoundException(msg);
            });
  }

  @Override
  public List<MemberStatus> getMemberStatuses() {
    return memberStatusJpaRepository.findAll();
  }

  @Override
  public MemberStatus saveMemberStatus(MemberStatusDto dto) {
    MemberStatus MemberStatus = dto.toMemberStatus();
    return memberStatusJpaRepository.save(MemberStatus);
  }

  @Override
  public MemberStatus updateMemberStatus(Integer id, MemberStatusDto dto) {
    getMemberStatus(id);
    MemberStatus MemberStatus = dto.toMemberStatus();
    MemberStatus.setId(id);
    return memberStatusJpaRepository.save(MemberStatus);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<MemberStatus> countries = dto
            .getIds()
            .stream()
            .map(id -> MemberStatus.
                    builder()
                    .id(id)
                    .build())
            .collect(Collectors.toList());

    memberStatusJpaRepository.deleteAll(countries);
  }

  @Override
  public void deleteAllMemberStatus() {
    memberStatusJpaRepository.deleteAll();
  }

  @Override
  public boolean isMemberStatusExists(Integer id) {
    System.out.println("Checking memberStatus id : " + id);
    return memberStatusJpaRepository.findById(id).isPresent();
  }

}
