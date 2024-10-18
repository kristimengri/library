package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.LenderToBookInstance;
import com.kent.gmail.com.runtime.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List Lender */
@IdValid.List({
  @IdValid(
      field = "lenderLenderToBookInstancesIds",
      fieldType = LenderToBookInstance.class,
      targetField = "lenderLenderToBookInstanceses")
})
public class LenderFilter extends PersonFilter {

  private Set<String> blocked;

  private Set<String> lenderLenderToBookInstancesIds;

  @JsonIgnore private List<LenderToBookInstance> lenderLenderToBookInstanceses;

  /**
   * @return blocked
   */
  public Set<String> getBlocked() {
    return this.blocked;
  }

  /**
   * @param blocked blocked to set
   * @return LenderFilter
   */
  public <T extends LenderFilter> T setBlocked(Set<String> blocked) {
    this.blocked = blocked;
    return (T) this;
  }

  /**
   * @return lenderLenderToBookInstancesIds
   */
  public Set<String> getLenderLenderToBookInstancesIds() {
    return this.lenderLenderToBookInstancesIds;
  }

  /**
   * @param lenderLenderToBookInstancesIds lenderLenderToBookInstancesIds to set
   * @return LenderFilter
   */
  public <T extends LenderFilter> T setLenderLenderToBookInstancesIds(
      Set<String> lenderLenderToBookInstancesIds) {
    this.lenderLenderToBookInstancesIds = lenderLenderToBookInstancesIds;
    return (T) this;
  }

  /**
   * @return lenderLenderToBookInstanceses
   */
  @JsonIgnore
  public List<LenderToBookInstance> getLenderLenderToBookInstanceses() {
    return this.lenderLenderToBookInstanceses;
  }

  /**
   * @param lenderLenderToBookInstanceses lenderLenderToBookInstanceses to set
   * @return LenderFilter
   */
  public <T extends LenderFilter> T setLenderLenderToBookInstanceses(
      List<LenderToBookInstance> lenderLenderToBookInstanceses) {
    this.lenderLenderToBookInstanceses = lenderLenderToBookInstanceses;
    return (T) this;
  }
}
