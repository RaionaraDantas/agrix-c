package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Fertilizer repository.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}
