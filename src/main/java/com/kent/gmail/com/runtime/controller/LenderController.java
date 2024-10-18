package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.model.Lender;
import com.kent.gmail.com.runtime.request.LenderCreate;
import com.kent.gmail.com.runtime.request.LenderFilter;
import com.kent.gmail.com.runtime.request.LenderUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.LenderService;
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
@RequestMapping("Lender")
@Tag(name = "Lender")
public class LenderController {

  @Autowired private LenderService lenderService;

  @PostMapping("createLender")
  @Operation(summary = "createLender", description = "Creates Lender")
  public Lender createLender(
      @Validated(Create.class) @RequestBody LenderCreate lenderCreate,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return lenderService.createLender(lenderCreate, securityContext);
  }

  @DeleteMapping("{id}")
  @Operation(summary = "deleteLender", description = "Deletes Lender")
  public Lender deleteLender(@PathVariable("id") String id, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return lenderService.deleteLender(id, securityContext);
  }

  @PostMapping("getAllLenders")
  @Operation(summary = "getAllLenders", description = "lists Lenders")
  public PaginationResponse<Lender> getAllLenders(
      @Valid @RequestBody LenderFilter lenderFilter, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return lenderService.getAllLenders(lenderFilter, securityContext);
  }

  @PutMapping("updateLender")
  @Operation(summary = "updateLender", description = "Updates Lender")
  public Lender updateLender(
      @Validated(Update.class) @RequestBody LenderUpdate lenderUpdate,
      Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return lenderService.updateLender(lenderUpdate, securityContext);
  }
}
