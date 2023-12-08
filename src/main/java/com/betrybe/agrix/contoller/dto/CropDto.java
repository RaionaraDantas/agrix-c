package com.betrybe.agrix.contoller.dto;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(
        Long id,
        String name,
        float plantedArea,
        Long farmId,
        LocalDate plantedDate,
        LocalDate harvestDate
) {
  /**
   * From entity crop dto.
   *
   * @param crop the crop
   * @return the crop dto
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId(),
            crop.getPlantedDate(),
            crop.getHarvestDate()
    );
  }

  /**
   * Tocrop crop.
   *
   * @return the crop
   */
  public Crop tocrop() {
    Crop crop = new Crop();
    crop.setName(name);
    crop.setId(id);
    crop.setPlantedArea(plantedArea);
    return crop;
  }
}
