package com.inditex.tech.adapter_repository_postgres.mapper;

import com.inditex.tech.adapter_repository_postgres.entities.ProductEntity;
import com.inditex.tech.adapter_repository_postgres.entities.SizeEntity;
import com.inditex.tech.adapter_repository_postgres.entities.StockEntity;
import com.inditex.tech.domain.entities.Product;
import com.inditex.tech.domain.entities.Size;
import com.inditex.tech.domain.entities.Stock;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RepositoryMapper {

  Product map(ProductEntity entity);

  @Mapping(target = "productId", source = "product.id")
  Size map(SizeEntity entity);

  Stock map(StockEntity entity);

  default Optional<Stock> wrapOptional(StockEntity entity) {
    return Optional.ofNullable(map(entity));
  }

}
