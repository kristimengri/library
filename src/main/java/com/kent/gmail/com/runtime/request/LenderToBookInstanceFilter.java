package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.BookInstance;
import com.kent.gmail.com.runtime.model.Lender;
import com.kent.gmail.com.runtime.validation.IdValid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

/** Object Used to List LenderToBookInstance */
@IdValid.List({
  @IdValid(
      field = "bookInstanceIds",
      fieldType = BookInstance.class,
      targetField = "bookInstances"),
  @IdValid(field = "lenderIds", fieldType = Lender.class, targetField = "lenders")
})
public class LenderToBookInstanceFilter extends BaseFilter {

  private Set<String> bookInstanceIds;

  @JsonIgnore private List<BookInstance> bookInstances;

  private OffsetDateTime dueBackEnd;

  private OffsetDateTime dueBackStart;

  private Set<String> lenderIds;

  @JsonIgnore private List<Lender> lenders;

  private OffsetDateTime lentEnd;

  private OffsetDateTime lentStart;

  private OffsetDateTime returnedEnd;

  private OffsetDateTime returnedStart;

  /**
   * @return bookInstanceIds
   */
  public Set<String> getBookInstanceIds() {
    return this.bookInstanceIds;
  }

  /**
   * @param bookInstanceIds bookInstanceIds to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setBookInstanceIds(Set<String> bookInstanceIds) {
    this.bookInstanceIds = bookInstanceIds;
    return (T) this;
  }

  /**
   * @return bookInstances
   */
  @JsonIgnore
  public List<BookInstance> getBookInstances() {
    return this.bookInstances;
  }

  /**
   * @param bookInstances bookInstances to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setBookInstances(
      List<BookInstance> bookInstances) {
    this.bookInstances = bookInstances;
    return (T) this;
  }

  /**
   * @return dueBackEnd
   */
  public OffsetDateTime getDueBackEnd() {
    return this.dueBackEnd;
  }

  /**
   * @param dueBackEnd dueBackEnd to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setDueBackEnd(OffsetDateTime dueBackEnd) {
    this.dueBackEnd = dueBackEnd;
    return (T) this;
  }

  /**
   * @return dueBackStart
   */
  public OffsetDateTime getDueBackStart() {
    return this.dueBackStart;
  }

  /**
   * @param dueBackStart dueBackStart to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setDueBackStart(OffsetDateTime dueBackStart) {
    this.dueBackStart = dueBackStart;
    return (T) this;
  }

  /**
   * @return lenderIds
   */
  public Set<String> getLenderIds() {
    return this.lenderIds;
  }

  /**
   * @param lenderIds lenderIds to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setLenderIds(Set<String> lenderIds) {
    this.lenderIds = lenderIds;
    return (T) this;
  }

  /**
   * @return lenders
   */
  @JsonIgnore
  public List<Lender> getLenders() {
    return this.lenders;
  }

  /**
   * @param lenders lenders to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setLenders(List<Lender> lenders) {
    this.lenders = lenders;
    return (T) this;
  }

  /**
   * @return lentEnd
   */
  public OffsetDateTime getLentEnd() {
    return this.lentEnd;
  }

  /**
   * @param lentEnd lentEnd to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setLentEnd(OffsetDateTime lentEnd) {
    this.lentEnd = lentEnd;
    return (T) this;
  }

  /**
   * @return lentStart
   */
  public OffsetDateTime getLentStart() {
    return this.lentStart;
  }

  /**
   * @param lentStart lentStart to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setLentStart(OffsetDateTime lentStart) {
    this.lentStart = lentStart;
    return (T) this;
  }

  /**
   * @return returnedEnd
   */
  public OffsetDateTime getReturnedEnd() {
    return this.returnedEnd;
  }

  /**
   * @param returnedEnd returnedEnd to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setReturnedEnd(OffsetDateTime returnedEnd) {
    this.returnedEnd = returnedEnd;
    return (T) this;
  }

  /**
   * @return returnedStart
   */
  public OffsetDateTime getReturnedStart() {
    return this.returnedStart;
  }

  /**
   * @param returnedStart returnedStart to set
   * @return LenderToBookInstanceFilter
   */
  public <T extends LenderToBookInstanceFilter> T setReturnedStart(OffsetDateTime returnedStart) {
    this.returnedStart = returnedStart;
    return (T) this;
  }
}
