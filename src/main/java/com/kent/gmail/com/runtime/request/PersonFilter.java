package com.kent.gmail.com.runtime.request;

import java.util.Set;

/** Object Used to List Person */
public class PersonFilter extends BaseFilter {

  private Set<String> address;

  private Set<String> birthdate;

  private Set<String> email;

  private Set<String> phoneNumber;

  private Set<String> socialSecurityNumber;

  /**
   * @return address
   */
  public Set<String> getAddress() {
    return this.address;
  }

  /**
   * @param address address to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setAddress(Set<String> address) {
    this.address = address;
    return (T) this;
  }

  /**
   * @return birthdate
   */
  public Set<String> getBirthdate() {
    return this.birthdate;
  }

  /**
   * @param birthdate birthdate to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setBirthdate(Set<String> birthdate) {
    this.birthdate = birthdate;
    return (T) this;
  }

  /**
   * @return email
   */
  public Set<String> getEmail() {
    return this.email;
  }

  /**
   * @param email email to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setEmail(Set<String> email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return phoneNumber
   */
  public Set<String> getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * @param phoneNumber phoneNumber to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setPhoneNumber(Set<String> phoneNumber) {
    this.phoneNumber = phoneNumber;
    return (T) this;
  }

  /**
   * @return socialSecurityNumber
   */
  public Set<String> getSocialSecurityNumber() {
    return this.socialSecurityNumber;
  }

  /**
   * @param socialSecurityNumber socialSecurityNumber to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setSocialSecurityNumber(Set<String> socialSecurityNumber) {
    this.socialSecurityNumber = socialSecurityNumber;
    return (T) this;
  }
}
