package com.kent.gmail.com.runtime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.OffsetDateTime;

@Entity
public class LenderToBookInstance extends Base {

  @ManyToOne(targetEntity = Lender.class)
  private Lender lender;

  @ManyToOne(targetEntity = BookInstance.class)
  private BookInstance bookInstance;

  private OffsetDateTime lent;

  private OffsetDateTime dueBack;

  private OffsetDateTime returned;

  /**
   * @return lender
   */
  @ManyToOne(targetEntity = Lender.class)
  public Lender getLender() {
    return this.lender;
  }

  /**
   * @param lender lender to set
   * @return LenderToBookInstance
   */
  public <T extends LenderToBookInstance> T setLender(Lender lender) {
    this.lender = lender;
    return (T) this;
  }

  /**
   * @return bookInstance
   */
  @ManyToOne(targetEntity = BookInstance.class)
  public BookInstance getBookInstance() {
    return this.bookInstance;
  }

  /**
   * @param bookInstance bookInstance to set
   * @return LenderToBookInstance
   */
  public <T extends LenderToBookInstance> T setBookInstance(BookInstance bookInstance) {
    this.bookInstance = bookInstance;
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
   * @return LenderToBookInstance
   */
  public <T extends LenderToBookInstance> T setLent(OffsetDateTime lent) {
    this.lent = lent;
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
   * @return LenderToBookInstance
   */
  public <T extends LenderToBookInstance> T setDueBack(OffsetDateTime dueBack) {
    this.dueBack = dueBack;
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
   * @return LenderToBookInstance
   */
  public <T extends LenderToBookInstance> T setReturned(OffsetDateTime returned) {
    this.returned = returned;
    return (T) this;
  }
}
