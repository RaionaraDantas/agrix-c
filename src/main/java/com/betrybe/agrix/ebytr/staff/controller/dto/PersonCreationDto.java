package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * The type Person creation dto.
 */
public record PersonCreationDto(
        Long id,
        String username,
        Role role,
        String password
) {

  /**
   * To entity person.
   *
   * @return the person
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setRole(role);
    person.setPassword(password);
    return person;
  }
}
