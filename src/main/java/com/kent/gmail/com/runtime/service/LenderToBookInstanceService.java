package com.kent.gmail.com.runtime.service;

import com.kent.gmail.com.runtime.data.LenderToBookInstanceRepository;
import com.kent.gmail.com.runtime.model.LenderToBookInstance;
import com.kent.gmail.com.runtime.model.LenderToBookInstance_;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceCreate;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceFilter;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class LenderToBookInstanceService {

  @Autowired private LenderToBookInstanceRepository repository;

  @Autowired private BaseService baseService;

  /**
   * @param lenderToBookInstanceCreate Object Used to Create LenderToBookInstance
   * @param securityContext
   * @return created LenderToBookInstance
   */
  public LenderToBookInstance createLenderToBookInstance(
      LenderToBookInstanceCreate lenderToBookInstanceCreate, UserSecurityContext securityContext) {
    LenderToBookInstance lenderToBookInstance =
        createLenderToBookInstanceNoMerge(lenderToBookInstanceCreate, securityContext);
    lenderToBookInstance = this.repository.merge(lenderToBookInstance);
    return lenderToBookInstance;
  }

  /**
   * @param lenderToBookInstanceCreate Object Used to Create LenderToBookInstance
   * @param securityContext
   * @return created LenderToBookInstance unmerged
   */
  public LenderToBookInstance createLenderToBookInstanceNoMerge(
      LenderToBookInstanceCreate lenderToBookInstanceCreate, UserSecurityContext securityContext) {
    LenderToBookInstance lenderToBookInstance = new LenderToBookInstance();
    lenderToBookInstance.setId(UUID.randomUUID().toString());
    updateLenderToBookInstanceNoMerge(lenderToBookInstance, lenderToBookInstanceCreate);

    return lenderToBookInstance;
  }

  /**
   * @param lenderToBookInstanceCreate Object Used to Create LenderToBookInstance
   * @param lenderToBookInstance
   * @return if lenderToBookInstance was updated
   */
  public boolean updateLenderToBookInstanceNoMerge(
      LenderToBookInstance lenderToBookInstance,
      LenderToBookInstanceCreate lenderToBookInstanceCreate) {
    boolean update =
        baseService.updateBaseNoMerge(lenderToBookInstance, lenderToBookInstanceCreate);

    if (lenderToBookInstanceCreate.getLender() != null
        && (lenderToBookInstance.getLender() == null
            || !lenderToBookInstanceCreate
                .getLender()
                .getId()
                .equals(lenderToBookInstance.getLender().getId()))) {
      lenderToBookInstance.setLender(lenderToBookInstanceCreate.getLender());
      update = true;
    }

    if (lenderToBookInstanceCreate.getReturned() != null
        && (!lenderToBookInstanceCreate.getReturned().equals(lenderToBookInstance.getReturned()))) {
      lenderToBookInstance.setReturned(lenderToBookInstanceCreate.getReturned());
      update = true;
    }

    if (lenderToBookInstanceCreate.getDueBack() != null
        && (!lenderToBookInstanceCreate.getDueBack().equals(lenderToBookInstance.getDueBack()))) {
      lenderToBookInstance.setDueBack(lenderToBookInstanceCreate.getDueBack());
      update = true;
    }

    if (lenderToBookInstanceCreate.getLent() != null
        && (!lenderToBookInstanceCreate.getLent().equals(lenderToBookInstance.getLent()))) {
      lenderToBookInstance.setLent(lenderToBookInstanceCreate.getLent());
      update = true;
    }

    if (lenderToBookInstanceCreate.getBookInstance() != null
        && (lenderToBookInstance.getBookInstance() == null
            || !lenderToBookInstanceCreate
                .getBookInstance()
                .getId()
                .equals(lenderToBookInstance.getBookInstance().getId()))) {
      lenderToBookInstance.setBookInstance(lenderToBookInstanceCreate.getBookInstance());
      update = true;
    }

    return update;
  }

  /**
   * @param lenderToBookInstanceUpdate
   * @param securityContext
   * @return lenderToBookInstance
   */
  public LenderToBookInstance updateLenderToBookInstance(
      LenderToBookInstanceUpdate lenderToBookInstanceUpdate, UserSecurityContext securityContext) {
    LenderToBookInstance lenderToBookInstance =
        lenderToBookInstanceUpdate.getLenderToBookInstance();
    if (updateLenderToBookInstanceNoMerge(lenderToBookInstance, lenderToBookInstanceUpdate)) {
      lenderToBookInstance = this.repository.merge(lenderToBookInstance);
    }
    return lenderToBookInstance;
  }

  /**
   * @param lenderToBookInstanceFilter Object Used to List LenderToBookInstance
   * @param securityContext
   * @return PaginationResponse<LenderToBookInstance> containing paging information for
   *     LenderToBookInstance
   */
  public PaginationResponse<LenderToBookInstance> getAllLenderToBookInstances(
      LenderToBookInstanceFilter lenderToBookInstanceFilter, UserSecurityContext securityContext) {
    List<LenderToBookInstance> list =
        listAllLenderToBookInstances(lenderToBookInstanceFilter, securityContext);
    long count =
        this.repository.countAllLenderToBookInstances(lenderToBookInstanceFilter, securityContext);
    return new PaginationResponse<>(list, lenderToBookInstanceFilter.getPageSize(), count);
  }

  /**
   * @param lenderToBookInstanceFilter Object Used to List LenderToBookInstance
   * @param securityContext
   * @return List of LenderToBookInstance
   */
  public List<LenderToBookInstance> listAllLenderToBookInstances(
      LenderToBookInstanceFilter lenderToBookInstanceFilter, UserSecurityContext securityContext) {
    return this.repository.listAllLenderToBookInstances(
        lenderToBookInstanceFilter, securityContext);
  }

  public LenderToBookInstance deleteLenderToBookInstance(
      String id, UserSecurityContext securityContext) {
    LenderToBookInstance lenderToBookInstance =
        this.repository.getByIdOrNull(
            LenderToBookInstance.class, LenderToBookInstance_.id, id, securityContext);
    ;
    if (lenderToBookInstance == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "LenderToBookInstance not found");
    }

    this.repository.remove(lenderToBookInstance);

    return lenderToBookInstance;
  }

  public <T extends LenderToBookInstance, I> List<T> listByIds(
      Class<T> c,
      SingularAttribute<? super T, I> idField,
      Set<I> ids,
      UserSecurityContext securityContext) {
    return repository.listByIds(c, idField, ids, securityContext);
  }

  public <T extends LenderToBookInstance, I> T getByIdOrNull(
      Class<T> c,
      SingularAttribute<? super T, I> idField,
      I id,
      UserSecurityContext securityContext) {
    return repository.getByIdOrNull(c, idField, id, securityContext);
  }

  public <T extends LenderToBookInstance, I> T getByIdOrNull(
      Class<T> c, SingularAttribute<? super T, I> idField, I id) {
    return repository.getByIdOrNull(c, idField, id);
  }

  public <T extends LenderToBookInstance, I> List<T> listByIds(
      Class<T> c, SingularAttribute<? super T, I> idField, Set<I> ids) {
    return repository.listByIds(c, idField, ids);
  }

  public <T> T merge(T base) {
    return this.repository.merge(base);
  }

  public void massMerge(List<?> toMerge) {
    this.repository.massMerge(toMerge);
  }
}
