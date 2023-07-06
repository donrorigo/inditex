package com.inditex.tech.infrastructure.adapter_api.contract;

import com.inditex.tech.application.ports.input.FindAllProductsUseCase;
import com.inditex.tech.application.ports.input.FindProductsStockFilterUseCase;
import com.inditex.tech.domain.entities.Product;
import com.inditex.tech.infrastructure.adapter_api.adapter.ApiAdapter;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(
    classes = TestApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public abstract class BaseContractTest {

  @LocalServerPort
  int port;
  @InjectMocks
  private ApiAdapter adapter;
  @Mock
  private FindAllProductsUseCase findAllProductsUseCase;
  @Mock
  private FindProductsStockFilterUseCase findProductsStockFilterUseCase;

  @BeforeEach
  void setUp() {
    RestAssured.port = this.port;
    RestAssuredMockMvc.standaloneSetup(adapter);

    Mockito.when(this.findAllProductsUseCase.execute())
        .thenReturn(Set.of(Product.builder().id(1L).build(), Product.builder().id(2L).build(),
            Product.builder().id(3L).build()));
    Mockito.when(this.findProductsStockFilterUseCase.execute())
        .thenReturn(Set.of(Product.builder().id(2L).build()));
  }

}
