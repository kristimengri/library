package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.Person;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;

/** Object Used to Update Person */
@IdValid.List({
  @IdValid(
      field = "id",
      fieldType = Person.class,
      targetField = "person",
      groups = {Update.class})
})
public class PersonUpdate extends PersonCreate {

  private String id;

  @JsonIgnore private Person person;

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return PersonUpdate
   */
  public <T extends PersonUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return person
   */
  @JsonIgnore
  public Person getPerson() {
    return this.person;
  }

  /**
   * @param person person to set
   * @return PersonUpdate
   */
  public <T extends PersonUpdate> T setPerson(Person person) {
    this.person = person;
    return (T) this;
  }
}
