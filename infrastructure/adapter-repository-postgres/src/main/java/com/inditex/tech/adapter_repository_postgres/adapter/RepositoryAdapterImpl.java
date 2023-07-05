package com.inditex.tech.adapter_repository_postgres.adapter;

import com.inditex.tech.adapter_repository_postgres.mapper.RepositoryMapper;
import com.inditex.tech.adapter_repository_postgres.repositories.JpaProductRepository;
import com.inditex.tech.application.ports.output.ProductRepository;
import com.inditex.tech.domain.entities.Product;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepositoryAdapterImpl implements ProductRepository {

  private final JpaProductRepository productRepository;
  private final RepositoryMapper mapper;

  @Override
  public Collection<Product> findProducts() {
    return this.productRepository.findAll().stream().map(mapper::map).toList();
  }


}
