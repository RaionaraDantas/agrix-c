package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * The type Farm service.
 */
@Service
public class FarmService {
  private FarmRepository farmRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Creat farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm creatFarm(@RequestBody Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Gets all farms service.
   *
   * @return the all farms service
   */
  public List<Farm> getAllFarmsService() {
    return farmRepository.findAll();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm getFarmById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Find all crops service list.
   *
   * @param id the id
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> findAllCropsService(Long id) throws FarmNotFoundException {
    Farm farm = getFarmById(id);
    return farm.getCrops();
  }
}
