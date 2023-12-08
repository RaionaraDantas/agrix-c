package com.betrybe.agrix.contoller;

import com.betrybe.agrix.contoller.dto.CropDto;
import com.betrybe.agrix.contoller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   * @param cropService the crop service
   */
  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Creat farm response entity.
   *
   * @param farm the farm
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Farm> creatFarm(@RequestBody Farm farm) {
    Farm savedFarm = farmService.creatFarm(farm);
    return new ResponseEntity<>(savedFarm, HttpStatus.CREATED);
  }

  /**
   * Find all farms response entity.
   *
   * @return the response entity
   */
  @GetMapping
  @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER"})
  public ResponseEntity<List<Farm>> findAllFarms() {
    List<Farm> findAllFarms = farmService.getAllFarmsService();
    return new ResponseEntity<>(findAllFarms, HttpStatus.OK);
  }

  /**
   * Findby id farm dto.
   *
   * @param id the id
   * @return the farm dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public FarmDto findbyId(@PathVariable("id") long id) throws FarmNotFoundException {
    Farm farm = farmService.getFarmById(id);
    return FarmDto.fromEntity(farm);
  }

  /**
   * Creat crop response entity.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the response entity
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> creatCrop(
          @PathVariable("farmId") long farmId, @RequestBody Crop crop
  ) throws FarmNotFoundException {
    Farm farm = farmService.getFarmById(farmId);
    crop.setFarm(farm);
    Crop savedCrop = cropService.create(crop);
    return ResponseEntity.status(201).body(CropDto.fromEntity(savedCrop));
  }

  /**
   * All crops list.
   *
   * @param farmId the farm id
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> allCrops(@PathVariable("farmId") long farmId) throws FarmNotFoundException {
    List<Crop> findAllCrops = farmService.findAllCropsService(farmId);
    return findAllCrops.stream().map(CropDto::fromEntity).toList();
  }
}
