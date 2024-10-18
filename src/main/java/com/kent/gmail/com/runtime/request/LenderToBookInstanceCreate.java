package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.BookInstance;
import com.kent.gmail.com.runtime.model.Lender;
import com.kent.gmail.com.runtime.validation.Create;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;
import java.time.OffsetDateTime;

/** Object Used to Create LenderToBookInstance */
@IdValid.List({
  @IdValid(
      field = "bookInstanceId",
      fieldType = BookInstance.class,
      targetField = "bookInstance",
      groups = {Update.class, Create.class}),
  @IdValid(
      field = "lenderId",
      fieldType = Lender.class,
      targetField = "lender",
      groups = {Create.class, Update.class})
})
public class LenderToBookInstanceCreate extends BaseCreate {

  @JsonIgnore private BookInstance bookInstance;

  private String bookInstanceId;

  private OffsetDateTime dueBack;

  @JsonIgnore private Lender lender;

  private String lenderId;

  private OffsetDateTime lent;

  private OffsetDateTime returned;

  /**
   * @return bookInstance
   */
  @JsonIgnore
  public BookInstance getBookInstance() {
    return this.bookInstance;
  }

  /**
   * @param bookInstance bookInstance to set
   * @return LenderToBookInstanceCreate
   */
  public <T extends LenderToBookInstanceCreate> T setBookInstance(BookInstance bookInstance) {
    this.bookInstance = bookInstance;
    return (T) this;
  }

  /**
   * @return bookInstanceId
   */
  public String getBookInstanceId() {
    return this.bookInstanceId;
  }

  /**
   * @param bookInstanceId bookInstanceId to set
   * @return LenderToBookInstanceCreate
   */
  public <T extends LenderToBookInstanceCreate> T setBookInstanceId(String bookInstanceId) {
    this.bookInstanceId = bookInstanceId;
    return (T) this;
  }

  /**
   * @return dueBack
   */
  public OffsetDateTime getDueBack() {
    return this.dueBack;
  }

  /**
   * @param dueBack dueBack to set
   * @return LenderToBookInstanceCreate
   */
  public <T extends LenderToBookInstanceCreate> T setDueBack(OffsetDateTime dueBack) {
    this.dueBack = dueBack;
    return (T) this;
  }

  /**
   * @return lender
   */
  @JsonIgnore
  public Lender getLender() {
    return this.lender;
  }

  /**
   * @param lender lender to set
   * @return LenderToBookInstanceCreate
   */
  public <T extends LenderToBookInstanceCreate> T setLender(Lender lender) {
    this.lender = lender;
    return (T) this;
  }

  /**
   * @return lenderId
   */
  public String getLenderId() {
    return this.lenderId;
  }

  /**
   * @param lenderId lenderId to set
   * @return LenderToBookInstanceCreate
   */
  public <T extends LenderToBookInstanceCreate> T setLenderId(String lenderId) {
    this.lenderId = lenderId;
    return (T) this;
  }

  /**
   * @return lent
   */
  public OffsetDateTime getLent() {
    return this.lent;
  }

  /**
   * @param lent lent to set
   * @return LenderToBookInstanceCreate
   */
  public <T extends LenderToBookInstanceCreate> T setLent(OffsetDateTime lent) {
    this.lent = lent;
    return (T) this;
  }

  /**
   * @return returned
   */
  public OffsetDateTime getReturned() {
    return this.returned;
  }

  /**
   * @param returned returned to set
   * @return LenderToBookInstanceCreate
   */
  public <T extends LenderToBookInstanceCreate> T setReturned(OffsetDateTime returned) {
    this.returned = returned;
    return (T) this;
  }
}
