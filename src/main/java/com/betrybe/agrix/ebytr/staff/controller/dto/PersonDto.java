package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * The type Person dto.
 */
public record PersonDto(
        Long id,
        String username,
        Role role
) {
  /**
   * From entity person dto.
   *
   * @param person the person
   * @return the person dto
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
            person.getId(),
            person.getUsername(),
            person.getRole()
    );
  }

  /**
   * To entity person.
   *
   * @return the person
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setRole(role);
    return person;
  }
}
