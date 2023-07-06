package com.inditex.tech.main.contract;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Import(TestContainersConfiguration.class)
class ApplicationTest {

  @Test
  @DisplayName("Check if we receive the correct number of products without any filtering")
  void getAll() {
    when().request("GET", "/products").then().statusCode(200).body("", hasItems(1, 2, 3, 4, 5));
  }

  @Test
  @DisplayName("Check if we receive the correct number of products when we apply stock to false")
  void stockToFalse() {
    when().request("GET", "/products?stock=false").then().statusCode(200).body("", hasItems(1, 2, 3, 4, 5));
  }

  @Test
  @DisplayName("Check if we receive the correct number of products when we apply stock to true")
  void stockToTrue() {
    when().request("GET", "/products?stock=true").then().statusCode(200).body("", hasItems(5, 1, 3));
  }

}
