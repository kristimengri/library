package com.kent.gmail.com.runtime.request;

/** Object Used to Create Lender */
public class LenderCreate extends PersonCreate {

  private String blocked;

  /**
   * @return blocked
   */
  public String getBlocked() {
    return this.blocked;
  }

  /**
   * @param blocked blocked to set
   * @return LenderCreate
   */
  public <T extends LenderCreate> T setBlocked(String blocked) {
    this.blocked = blocked;
    return (T) this;
  }
}
