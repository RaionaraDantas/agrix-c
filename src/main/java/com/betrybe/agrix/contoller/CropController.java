package com.betrybe.agrix.contoller;

import com.betrybe.agrix.contoller.dto.CropDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")

public class CropController {
  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Find all crops response entity.
   *
   * @return the response entity
   */
  @GetMapping
  @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
  public ResponseEntity<List<CropDto>> findAllCrops() {
    List<Crop> getAllCropsService = cropService.getAllCropsService();
    List<CropDto> cropsList = getAllCropsService.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropsList);
  }

  /**
   * Findby id crop dto.
   *
   * @param id the id
   * @return the crop dto
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public CropDto findbyId(@PathVariable("id") long id) throws CropNotFoundException {
    Crop crop = cropService.getCropById(id);
    return CropDto.fromEntity(crop);
  }

  /**
   * Find by harvest date between controller list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  @GetMapping("/search")
  public List<CropDto> findByHarvestDateBetweenController(
          @RequestParam LocalDate start, @RequestParam LocalDate end
  ) {
    List<Crop> crops = cropService.findByHarvestDateBetweenService(start, end);
    return crops.stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Associate crop and fertilizers string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   * @throws FertilizerNotFoundException the fertilizer not found exception
   * @throws CropNotFoundException       the crop not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String associateCropAndFertilizers(
          @PathVariable long cropId, @PathVariable long fertilizerId
  )
          throws FertilizerNotFoundException, CropNotFoundException {
    Crop crop = cropService.associateCropAndFertilizer(cropId, fertilizerId);

    return "Fertilizante e plantação associados com sucesso!";
  }
}

