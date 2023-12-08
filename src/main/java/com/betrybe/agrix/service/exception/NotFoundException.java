package com.betrybe.agrix.service.exception;

/**
 * The type Not found exception.
 */
public abstract class NotFoundException extends Exception {
  /**
   * Instantiates a new Not found exception.
   *
   * @param message the message
   */
  public NotFoundException(String message) {
    super(message);
  }
}
