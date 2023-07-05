package com.inditex.tech.infrastructure.adapter_repository.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ProductEntity {
  private final Long id;
  private Integer sequence;
}
