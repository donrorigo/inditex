package com.inditex.tech.infrastructure.adapter_repository.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.inditex.tech.domain.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class RepositoryAdapterImplTest {

  @InjectMocks
  private RepositoryAdapterImpl repositoryAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findProducts() {
    // given
    final var products = assertDoesNotThrow(() -> this.repositoryAdapter.findProducts());

    // then
    assertEquals(5, products.size(), "Incorrect number of products");
    assertEquals(1L, products.stream().findFirst().map(Product::getId).orElse(null), "Incorrect order in products");
    assertEquals(3, products.stream().findFirst().map(product -> product.getSizes().size()).orElse(null), "Incorrect number of sizes in product with identifier 1");
  }
}