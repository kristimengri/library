package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.LenderToBookInstance;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;

/** Object Used to Update LenderToBookInstance */
@IdValid.List({
  @IdValid(
      field = "id",
      fieldType = LenderToBookInstance.class,
      targetField = "lenderToBookInstance",
      groups = {Update.class})
})
public class LenderToBookInstanceUpdate extends LenderToBookInstanceCreate {

  private String id;

  @JsonIgnore private LenderToBookInstance lenderToBookInstance;

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id id to set
   * @return LenderToBookInstanceUpdate
   */
  public <T extends LenderToBookInstanceUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return lenderToBookInstance
   */
  @JsonIgnore
  public LenderToBookInstance getLenderToBookInstance() {
    return this.lenderToBookInstance;
  }

  /**
   * @param lenderToBookInstance lenderToBookInstance to set
   * @return LenderToBookInstanceUpdate
   */
  public <T extends LenderToBookInstanceUpdate> T setLenderToBookInstance(
      LenderToBookInstance lenderToBookInstance) {
    this.lenderToBookInstance = lenderToBookInstance;
    return (T) this;
  }
}
