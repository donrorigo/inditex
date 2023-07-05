package com.inditex.tech.infrastructure.adapter_repository.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SizeEntity {
  private final Long id;
  private final Long productId;
  private boolean backSoon;
  private boolean special;
}
