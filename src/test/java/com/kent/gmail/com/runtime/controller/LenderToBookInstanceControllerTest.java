package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.AppInit;
import com.kent.gmail.com.runtime.model.BookInstance;
import com.kent.gmail.com.runtime.model.Lender;
import com.kent.gmail.com.runtime.model.LenderToBookInstance;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceCreate;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceFilter;
import com.kent.gmail.com.runtime.request.LenderToBookInstanceUpdate;
import com.kent.gmail.com.runtime.request.LoginRequest;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppInit.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class LenderToBookInstanceControllerTest {

  private LenderToBookInstance testLenderToBookInstance;
  @Autowired private TestRestTemplate restTemplate;

  @Autowired private Lender lender;

  @Autowired private BookInstance bookInstance;

  @BeforeAll
  private void init() {
    ResponseEntity<Object> authenticationResponse =
        this.restTemplate.postForEntity(
            "/login",
            new LoginRequest().setUsername("admin@flexicore.com").setPassword("admin"),
            Object.class);
    String authenticationKey =
        authenticationResponse.getHeaders().get(HttpHeaders.AUTHORIZATION).stream()
            .findFirst()
            .orElse(null);
    restTemplate
        .getRestTemplate()
        .setInterceptors(
            Collections.singletonList(
                (request, body, execution) -> {
                  request.getHeaders().add("Authorization", "Bearer " + authenticationKey);
                  return execution.execute(request, body);
                }));
  }

  @Test
  @Order(1)
  public void testLenderToBookInstanceCreate() {
    LenderToBookInstanceCreate request = new LenderToBookInstanceCreate();

    request.setLenderId(this.lender.getId());

    request.setReturned(OffsetDateTime.now());

    request.setDueBack(OffsetDateTime.now());

    request.setLent(OffsetDateTime.now());

    request.setBookInstanceId(this.bookInstance.getId());

    ResponseEntity<LenderToBookInstance> response =
        this.restTemplate.postForEntity(
            "/LenderToBookInstance/createLenderToBookInstance",
            request,
            LenderToBookInstance.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testLenderToBookInstance = response.getBody();
    assertLenderToBookInstance(request, testLenderToBookInstance);
  }

  @Test
  @Order(2)
  public void testListAllLenderToBookInstances() {
    LenderToBookInstanceFilter request = new LenderToBookInstanceFilter();
    ParameterizedTypeReference<PaginationResponse<LenderToBookInstance>> t =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<LenderToBookInstance>> response =
        this.restTemplate.exchange(
            "/LenderToBookInstance/getAllLenderToBookInstances",
            HttpMethod.POST,
            new HttpEntity<>(request),
            t);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<LenderToBookInstance> body = response.getBody();
    Assertions.assertNotNull(body);
    List<LenderToBookInstance> LenderToBookInstances = body.getList();
    Assertions.assertNotEquals(0, LenderToBookInstances.size());
    Assertions.assertTrue(
        LenderToBookInstances.stream()
            .anyMatch(f -> f.getId().equals(testLenderToBookInstance.getId())));
  }

  public void assertLenderToBookInstance(
      LenderToBookInstanceCreate request, LenderToBookInstance testLenderToBookInstance) {
    Assertions.assertNotNull(testLenderToBookInstance);

    if (request.getLenderId() != null) {

      Assertions.assertNotNull(testLenderToBookInstance.getLender());
      Assertions.assertEquals(request.getLenderId(), testLenderToBookInstance.getLender().getId());
    }

    if (request.getReturned() != null) {
      Assertions.assertEquals(
          request.getReturned().atZoneSameInstant(ZoneId.systemDefault()),
          testLenderToBookInstance.getReturned().atZoneSameInstant(ZoneId.systemDefault()));
    }

    if (request.getDueBack() != null) {
      Assertions.assertEquals(
          request.getDueBack().atZoneSameInstant(ZoneId.systemDefault()),
          testLenderToBookInstance.getDueBack().atZoneSameInstant(ZoneId.systemDefault()));
    }

    if (request.getLent() != null) {
      Assertions.assertEquals(
          request.getLent().atZoneSameInstant(ZoneId.systemDefault()),
          testLenderToBookInstance.getLent().atZoneSameInstant(ZoneId.systemDefault()));
    }

    if (request.getBookInstanceId() != null) {

      Assertions.assertNotNull(testLenderToBookInstance.getBookInstance());
      Assertions.assertEquals(
          request.getBookInstanceId(), testLenderToBookInstance.getBookInstance().getId());
    }
  }

  @Test
  @Order(3)
  public void testLenderToBookInstanceUpdate() {
    LenderToBookInstanceUpdate request =
        new LenderToBookInstanceUpdate().setId(testLenderToBookInstance.getId());
    ResponseEntity<LenderToBookInstance> response =
        this.restTemplate.exchange(
            "/LenderToBookInstance/updateLenderToBookInstance",
            HttpMethod.PUT,
            new HttpEntity<>(request),
            LenderToBookInstance.class);
    Assertions.assertEquals(200, response.getStatusCodeValue());
    testLenderToBookInstance = response.getBody();
    assertLenderToBookInstance(request, testLenderToBookInstance);
  }
}
