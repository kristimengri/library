package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.model.LenderToBookInstance;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceCreate;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceFilter;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.LenderToBookInstanceService;
import com.kent.gmail.com.runtime.validation.Create;
import com.kent.gmail.com.runtime.validation.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("LenderToBookInstance")
@Tag(name = "LenderToBookInstance")
public class LenderToBookInstanceController {

  @Autowired private LenderToBookInstanceService lenderToBookInstanceService;

  @PostMapping("createLenderToBookInstance")
  @Operation(summary = "createLenderToBookInstance", description = "Creates LenderToBookInstance")
  public LenderToBookInstance createLenderToBookInstance(
      @Validated(Create.class) @RequestBody LenderToBookInstanceCreate lenderToBookInstanceCreate,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return lenderToBookInstanceService.createLenderToBookInstance(
        lenderToBookInstanceCreate, securityContext);
  }

  @DeleteMapping("{id}")
  @Operation(summary = "deleteLenderToBookInstance", description = "Deletes LenderToBookInstance")
  public LenderToBookInstance deleteLenderToBookInstance(
      @PathVariable("id") String id, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return lenderToBookInstanceService.deleteLenderToBookInstance(id, securityContext);
  }

  @PostMapping("getAllLenderToBookInstances")
  @Operation(summary = "getAllLenderToBookInstances", description = "lists LenderToBookInstances")
  public PaginationResponse<LenderToBookInstance> getAllLenderToBookInstances(
      @Valid @RequestBody LenderToBookInstanceFilter lenderToBookInstanceFilter,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return lenderToBookInstanceService.getAllLenderToBookInstances(
        lenderToBookInstanceFilter, securityContext);
  }

  @PutMapping("updateLenderToBookInstance")
  @Operation(summary = "updateLenderToBookInstance", description = "Updates LenderToBookInstance")
  public LenderToBookInstance updateLenderToBookInstance(
      @Validated(Update.class) @RequestBody LenderToBookInstanceUpdate lenderToBookInstanceUpdate,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return lenderToBookInstanceService.updateLenderToBookInstance(
        lenderToBookInstanceUpdate, securityContext);
  }
}
