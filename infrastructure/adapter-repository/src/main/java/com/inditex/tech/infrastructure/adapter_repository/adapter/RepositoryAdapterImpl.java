package com.inditex.tech.infrastructure.adapter_repository.adapter;

import com.inditex.tech.application.ports.output.ProductRepository;
import com.inditex.tech.domain.entities.Product;
import com.inditex.tech.domain.entities.Size;
import com.inditex.tech.domain.entities.Stock;
import com.inditex.tech.infrastructure.adapter_repository.utils.AdapterUtils;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepositoryAdapterImpl implements ProductRepository {

  @Override
  public Collection<Product> findProducts() {
    log.debug("Reading the csv files stored in classpath");
    final var stocks = AdapterUtils.readStocks();
    final var sizes = AdapterUtils.readSizes();
    final var products = AdapterUtils.readProducts();

    log.debug("Mapping the entities to the domain model");
    return products.stream().map(productEntity ->
            Product.builder()
                .id(productEntity.getId())
                .sequence(productEntity.getSequence())
                .sizes(sizes.stream()
                    .filter(sizeEntity -> sizeEntity.getProductId().equals(productEntity.getId()))
                    .map(sizeEntity -> Size.builder()
                        .id(sizeEntity.getId())
                        .backSoon(sizeEntity.isBackSoon())
                        .special(sizeEntity.isSpecial())
                        .stock(stocks.stream()
                            .filter(stockEntity -> sizeEntity.getId() == stockEntity.getSizeId())
                            .findAny()
                            .map(stockEntity -> Stock.builder().quantity(stockEntity.getQuantity())
                                .build()))
                        .build())
                    .collect(Collectors.toSet()))
                .build())
        .toList();
  }


}
