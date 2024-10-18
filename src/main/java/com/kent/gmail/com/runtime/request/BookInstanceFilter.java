package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.LenderToBookInstance;
import com.kent.gmail.com.runtime.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List BookInstance */
@IdValid.List({
  @IdValid(
      field = "bookInstanceLenderToBookInstancesIds",
      fieldType = LenderToBookInstance.class,
      targetField = "bookInstanceLenderToBookInstanceses")
})
public class BookInstanceFilter extends BaseFilter {

  private Set<Boolean> blocked;

  private Set<String> bookInstanceLenderToBookInstancesIds;

  @JsonIgnore private List<LenderToBookInstance> bookInstanceLenderToBookInstanceses;

  private Set<String> serialNumber;

  /**
   * @return blocked
   */
  public Set<Boolean> getBlocked() {
    return this.blocked;
  }

  /**
   * @param blocked blocked to set
   * @return BookInstanceFilter
   */
  public <T extends BookInstanceFilter> T setBlocked(Set<Boolean> blocked) {
    this.blocked = blocked;
    return (T) this;
  }

  /**
   * @return bookInstanceLenderToBookInstancesIds
   */
  public Set<String> getBookInstanceLenderToBookInstancesIds() {
    return this.bookInstanceLenderToBookInstancesIds;
  }

  /**
   * @param bookInstanceLenderToBookInstancesIds bookInstanceLenderToBookInstancesIds to set
   * @return BookInstanceFilter
   */
  public <T extends BookInstanceFilter> T setBookInstanceLenderToBookInstancesIds(
      Set<String> bookInstanceLenderToBookInstancesIds) {
    this.bookInstanceLenderToBookInstancesIds = bookInstanceLenderToBookInstancesIds;
    return (T) this;
  }

  /**
   * @return bookInstanceLenderToBookInstanceses
   */
  @JsonIgnore
  public List<LenderToBookInstance> getBookInstanceLenderToBookInstanceses() {
    return this.bookInstanceLenderToBookInstanceses;
  }

  /**
   * @param bookInstanceLenderToBookInstanceses bookInstanceLenderToBookInstanceses to set
   * @return BookInstanceFilter
   */
  public <T extends BookInstanceFilter> T setBookInstanceLenderToBookInstanceses(
      List<LenderToBookInstance> bookInstanceLenderToBookInstanceses) {
    this.bookInstanceLenderToBookInstanceses = bookInstanceLenderToBookInstanceses;
    return (T) this;
  }

  /**
   * @return serialNumber
   */
  public Set<String> getSerialNumber() {
    return this.serialNumber;
  }

  /**
   * @param serialNumber serialNumber to set
   * @return BookInstanceFilter
   */
  public <T extends BookInstanceFilter> T setSerialNumber(Set<String> serialNumber) {
    this.serialNumber = serialNumber;
    return (T) this;
  }
}
