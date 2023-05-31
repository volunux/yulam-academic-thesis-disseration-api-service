package com.yulam.acalib.model.mapper;

import com.yulam.acalib.model.domain.MemberStatus;
import com.yulam.acalib.model.view.MemberStatusView;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemberStatusMapper {

  private MemberStatusMapper() {

  }

  public static MemberStatusView toMemberStatusView(@NotNull MemberStatus memberStatus) {
    return MemberStatusView.builder()
            .id(memberStatus.getId())
            .title(memberStatus.getTitle())
            .description(memberStatus.getDescription())
            .createdOn(memberStatus.getCreatedOn())
            .updatedOn(memberStatus.getUpdatedOn())
            .build();
  }

  public static List<MemberStatusView> toMemberStatusViews(List<MemberStatus> memberStatuses) {
    if (memberStatuses != null && !memberStatuses.isEmpty()) {
      return memberStatuses
              .stream()
              .map(MemberStatusMapper::toMemberStatusView)
              .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

}
