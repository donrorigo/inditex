package com.inditex.tech.application.ports.input.interactor;

import com.inditex.tech.application.ports.output.ProductRepository;
import com.inditex.tech.domain.entities.Product;
import com.inditex.tech.domain.entities.Size;
import com.inditex.tech.domain.entities.Stock;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("(FindProductsStockFilterUseCaseImplTest) -> The test should return the correct number of products")
class FindProductsStockFilterUseCaseImplTest {

  @InjectMocks
  private FindProductsStockFilterUseCaseImpl useCase;
  @Mock
  private ProductRepository repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("...when we have stocks in both")
  void bothProductsHaveStock() {

    // given
    final var productA = Product.builder()
        .id(1L)
        .sequence(1)
        .sizes(
            Set.of(Size.builder().stock(Optional.of(Stock.builder()
                .build())).build()))
        .build();

    final var productB = Product.builder()
        .id(2L)
        .sizes(
            Set.of(Size.builder().stock(Optional.of(Stock.builder()
                .build())).build()))
        .build();

    final List<Product> products = List.of(productA, productB);

    // when
    Mockito.when(this.repository.findProducts()).thenReturn(products);

    // then
    final var storedProducts = assertDoesNotThrow(() -> this.useCase.execute());
    assertFalse(storedProducts.isEmpty(), "The products are coming empty");
    assertEquals(2, storedProducts.size(),
        "We are not receiving the correct number of products from database");
    assertEquals(List.of(2L,1L), storedProducts.stream().map(Product::getId).toList());
  }

  @Test
  @DisplayName("...when we have one product with stock and one product that is back soon")
  void oneProductHasStockAndOtherIsBackSoon() {

    // given
    final var productA = Product.builder()
        .id(1L)
        .sizes(
            Set.of(Size.builder().stock(Optional.of(Stock.builder()
                .build())).build()))
        .build();

    final var productB = Product.builder()
        .id(2L)
        .sizes(
            Set.of(Size.builder().backSoon(true).build()))
        .build();

    final List<Product> products = List.of(productA, productB);

    // when
    Mockito.when(this.repository.findProducts()).thenReturn(products);

    // then
    final var storedProducts = assertDoesNotThrow(() -> this.useCase.execute());
    assertFalse(storedProducts.isEmpty(), "The products are coming empty");
    assertEquals(2, storedProducts.size(),
        "We are not receiving the correct number of products from database");
  }

  @Test
  @DisplayName("...when we don't have stock in any product")
  void anyProductHasStock() {

    // given
    final var productA = Product.builder()
        .id(1L)
        .sequence(1)
        .sizes(
            Set.of(Size.builder().build()))
        .build();

    final var productB = Product.builder()
        .id(2L)
        .sizes(
            Set.of(Size.builder().build()))
        .build();

    final List<Product> products = List.of(productA, productB);

    // when
    Mockito.when(this.repository.findProducts()).thenReturn(products);

    // then
    final var storedProducts = assertDoesNotThrow(() -> this.useCase.execute());
    assertTrue(storedProducts.isEmpty(), "The products are coming empty");
  }

  @Test
  @DisplayName("...when we have a product with special size (with back soon) and stocks")
  void productWithSpecialSize() {

    // given
    final var productA = Product.builder()
        .id(1L)
        .sizes(
            Set.of(Size.builder().backSoon(true).special(true).build(),
                Size.builder().stock(Optional.of(Stock.builder().build())).build()))
        .build();

    final var productB = Product.builder()
        .id(2L)
        .sizes(
            Set.of(Size.builder().build()))
        .build();

    final List<Product> products = List.of(productA, productB);

    // when
    Mockito.when(this.repository.findProducts()).thenReturn(products);

    // then
    final var storedProducts = assertDoesNotThrow(() -> this.useCase.execute());
    assertFalse(storedProducts.isEmpty(), "The products are coming empty");
    assertEquals(1, storedProducts.size(),
        "We are not receiving the correct number of products from database");
  }

  @Test
  @DisplayName("...when we have a product with special size (with back soon) and stocks")
  void productWithSpecialSizeAndBothWithBackSoon() {

    // given
    final var productA = Product.builder()
        .id(1L)
        .sizes(
            Set.of(Size.builder().backSoon(true).special(true).build(),
                Size.builder().backSoon(true).build()))
        .build();

    final var productB = Product.builder()
        .id(2L)
        .sizes(
            Set.of(Size.builder().build()))
        .build();

    final List<Product> products = List.of(productA, productB);

    // when
    Mockito.when(this.repository.findProducts()).thenReturn(products);

    // then
    final var storedProducts = assertDoesNotThrow(() -> this.useCase.execute());
    assertFalse(storedProducts.isEmpty(), "The products are coming empty");
    assertEquals(1, storedProducts.size(),
        "We are not receiving the correct number of products from database");
  }
}