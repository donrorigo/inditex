package com.inditex.tech.infrastructure.adapter_repository.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockEntity {
  private final long sizeId;
  private Integer quantity;
}
