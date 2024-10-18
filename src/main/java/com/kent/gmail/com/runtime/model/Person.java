package com.kent.gmail.com.runtime.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends Base {

  private String email;

  private String address;

  private String phoneNumber;

  private String birthdate;

  private String socialSecurityNumber;

  /**
   * @return email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * @param email email to set
   * @return Person
   */
  public <T extends Person> T setEmail(String email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return address
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * @param address address to set
   * @return Person
   */
  public <T extends Person> T setAddress(String address) {
    this.address = address;
    return (T) this;
  }

  /**
   * @return phoneNumber
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * @param phoneNumber phoneNumber to set
   * @return Person
   */
  public <T extends Person> T setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return (T) this;
  }

  /**
   * @return birthdate
   */
  public String getBirthdate() {
    return this.birthdate;
  }

  /**
   * @param birthdate birthdate to set
   * @return Person
   */
  public <T extends Person> T setBirthdate(String birthdate) {
    this.birthdate = birthdate;
    return (T) this;
  }

  /**
   * @return socialSecurityNumber
   */
  public String getSocialSecurityNumber() {
    return this.socialSecurityNumber;
  }

  /**
   * @param socialSecurityNumber socialSecurityNumber to set
   * @return Person
   */
  public <T extends Person> T setSocialSecurityNumber(String socialSecurityNumber) {
    this.socialSecurityNumber = socialSecurityNumber;
    return (T) this;
  }
}
