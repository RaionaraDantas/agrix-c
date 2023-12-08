package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Crop service.
 */
@Service
public class CropService {
  private CropRepository cropRepository;
  private FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository       the crop repository
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Create crop.
   *
   * @param crop the crop
   * @return the crop
   */
  public Crop create(Crop crop) {
    return cropRepository.save(crop);
  }

  /**
   * Gets all crops service.
   *
   * @return the all crops service
   */
  public List<Crop> getAllCropsService() {
    return cropRepository.findAll();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop getCropById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
  }

  /**
   * Find by harvest date between service list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  public List<Crop> findByHarvestDateBetweenService(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Associate crop and fertilizer crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop
   * @throws FertilizerNotFoundException the fertilizer not found exception
   * @throws CropNotFoundException       the crop not found exception
   */
  public Crop associateCropAndFertilizer(long cropId, long fertilizerId)
          throws FertilizerNotFoundException, CropNotFoundException {
    Crop crop = getCropById(cropId);
    Fertilizer fertilizer = fertilizerRepository
            .findById(fertilizerId).orElseThrow(FertilizerNotFoundException:: new);

    crop.getFertilizers().add(fertilizer);
    return cropRepository.save(crop);
  }
}
