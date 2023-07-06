package com.inditex.tech.application.ports.input.interactor;

import com.inditex.tech.application.ports.input.FindProductsStockFilterUseCase;
import com.inditex.tech.application.ports.output.ProductRepository;
import com.inditex.tech.domain.entities.Product;
import com.inditex.tech.domain.entities.Size;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindProductsStockFilterUseCaseImpl implements FindProductsStockFilterUseCase {

  private final ProductRepository repository;

  @Override
  @Transactional(readOnly = true)
  public Collection<Product> execute() {
    log.debug("Executing the use case for products that have stock");
    final var products = this.repository.findProducts();
    return products.stream()
        .filter(this::productIsVisible)
        .collect(Collectors.toCollection(TreeSet::new));
  }

  /**
   * Checks if the specified product has any available stock, or it's back soon. Also, in the
   * special sizes cases, it only returns {@code true} if there are special and non-special sizes.
   *
   * @param product the product to check
   * @return {@code true} if the product has stock, {@code false} otherwise
   */
  private boolean productIsVisible(final Product product) {
    log.debug("Checking if the product {} can be visible", product.getId());
    final var sizes = product.getSizes()
        .stream()
        .filter(size -> size.isBackSoon() || size.doWeHaveStock())
        .map(Size::isSpecial)
        .collect(Collectors.toUnmodifiableSet());

    return (sizes.stream().anyMatch(special -> special.equals(true))) ? sizes.size() > 1
        : !sizes.isEmpty();
  }

}
