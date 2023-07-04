package com.inditex.tech.infrastructure.adapter_api.adapter;

import com.inditex.tech.application.ports.input.FindAllProductsUseCase;
import com.inditex.tech.application.ports.input.FindProductsStockFilterUseCase;
import com.inditex.tech.infrastructure.adapter_api.ProductsApi;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiAdapter implements ProductsApi {

  private final FindProductsStockFilterUseCase findProductsStockFilterUseCase;
  private final FindAllProductsUseCase findAllProductsUseCase;

  @Override
  public ResponseEntity<List<Integer>> _productsGet(final Boolean stock) {
    final var products = Optional.ofNullable(stock).map(queryPresent -> this.findProductsStockFilterUseCase.execute()).orElse(this.findAllProductsUseCase.execute()).stream().map(product -> product.getId().intValue()).toList();
    return ResponseEntity.ok(products);
  }
}
