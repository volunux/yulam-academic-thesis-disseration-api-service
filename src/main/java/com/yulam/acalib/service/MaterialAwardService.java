package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.MaterialAward;
import com.yulam.acalib.model.dto.MaterialAwardDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;

import java.util.List;

public interface MaterialAwardService {

  MaterialAward getMaterialAward(Integer id);

  List<MaterialAward> getFaculties();

  MaterialAward saveMaterialAward(MaterialAwardDto dto);

  MaterialAward updateMaterialAward(Integer id, MaterialAwardDto dto);

  void deleteMany(DeleteIdsDto ids);

  void deleteAllMaterialAward();

  boolean isMaterialAwardExists(Integer id);
}
