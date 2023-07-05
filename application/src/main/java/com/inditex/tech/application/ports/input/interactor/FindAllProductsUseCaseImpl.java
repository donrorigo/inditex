package com.inditex.tech.application.ports.input.interactor;

import com.inditex.tech.application.ports.input.FindAllProductsUseCase;
import com.inditex.tech.application.ports.output.ProductRepository;
import com.inditex.tech.domain.entities.Product;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindAllProductsUseCaseImpl implements FindAllProductsUseCase {

  private final ProductRepository repository;

  @Override
  @Transactional(readOnly = true)
  public Collection<Product> execute() {
    return repository.findProducts();
  }
}
