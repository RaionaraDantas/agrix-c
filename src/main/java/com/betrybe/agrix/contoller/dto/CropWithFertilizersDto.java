package com.betrybe.agrix.contoller.dto;

import com.betrybe.agrix.entity.Crop;
import java.util.List;

/**
 * The type Crop with fertilizers dto.
 */
public record CropWithFertilizersDto(
        Long id, String name, List<FertilizerDto> fertilizers
) {

  /**
   * From entity crop with fertilizers dto.
   *
   * @param crop the crop
   * @return the crop with fertilizers dto
   */
  public static CropWithFertilizersDto fromEntity(Crop crop) {
    return new CropWithFertilizersDto(
         crop.getId(),
         crop.getName(),
         crop.getFertilizers().stream()
                    .map(FertilizerDto::fromEntity)
                    .toList()
    );
  }
}
