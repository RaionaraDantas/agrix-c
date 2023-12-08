package com.betrybe.agrix.contoller;

import com.betrybe.agrix.contoller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
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
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Creat fertilizer response entity.
   *
   * @param fertilizer the fertilizer
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Fertilizer> creatFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer savedFertilizer = fertilizerService.creatFertilizer(fertilizer);
    return new ResponseEntity<>(savedFertilizer, HttpStatus.CREATED);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping
  @Secured("ROLE_ADMIN")
  public ResponseEntity<List<Fertilizer>> getAllFertilizers() {
    List<Fertilizer> getAllFertilizers = fertilizerService.getAllFertilizesService();
    return new ResponseEntity<>(getAllFertilizers, HttpStatus.OK);
  }

  /**
   * Findby id fertilizer dto.
   *
   * @param id the id
   * @return the fertilizer dto
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{fertilizerId}")
  public FertilizerDto findbyId(@PathVariable("fertilizerId") long id)
          throws FertilizerNotFoundException {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(id);
    return FertilizerDto.fromEntity(fertilizer);
  }
}
