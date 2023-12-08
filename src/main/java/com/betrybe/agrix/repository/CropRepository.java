package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Crop repository.
 */
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
