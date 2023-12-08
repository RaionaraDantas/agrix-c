package com.betrybe.agrix.contoller.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Long id, String name, float size) {
  /**
   * From entity farm dto.
   *
   * @param farm the farm
   * @return the farm dto
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
            farm.getId(),
            farm.getName(),
            farm.getSize()
    );
  }

  /**
   * To farm farm.
   *
   * @return the farm
   */
  public Farm toFarm() {
    Farm farm = new Farm();
    farm.setName(name);
    farm.setSize(size);
    return farm;
  }
}
