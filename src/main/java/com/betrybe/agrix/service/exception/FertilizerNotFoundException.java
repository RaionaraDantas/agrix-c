package com.betrybe.agrix.service.exception;

/**
 * The type Fertilizer not found exception.
 */
public class FertilizerNotFoundException extends NotFoundException {
  /**
   * Instantiates a new Fertilizer not found exception.
   */
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
