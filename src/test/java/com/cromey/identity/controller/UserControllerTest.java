package com.cromey.identity.controller;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cromey.identity.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
public class UserControllerTest {

  //@MockBean
  //private UserService userService;
  
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void whenPostRequestToUsersAndInvalidValidUser_thenBadRequestResponse() throws Exception {
    
    String body = "{}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(body, headers);

    ResponseEntity<String> response = restTemplate.postForEntity("/users", entity, String.class);

    String expectedJson = "{\"password\":\"Password is mandatory\",\"userName\":\"UserName is mandatory\",\"email\":\"Email is mandatory\"}";
    System.out.print(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    JSONAssert.assertEquals(expectedJson, response.getBody(), false);
    
  }
  
  @Test
  public void whenPostRequestToUsersAndValidValidUser_thenCreatedResponse() throws Exception {
    
    String body = "{\n" + 
        "  \"email\": \"paul.cromey@gmail.com\",\n" + 
        "  \"userName\": \"paulcromey\",\n" + 
        "  \"password\": \"password\",\n" + 
        "  \"dateOfBirth\": \"2019-03-19\"\n" + 
        "}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(body, headers);

    ResponseEntity<String> response = restTemplate.postForEntity("/users", entity, String.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    
  }

}
