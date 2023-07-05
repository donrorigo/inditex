package com.inditex.tech.adapter_repository_postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockEntity {
  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "quantity", nullable = false)
  private Integer quantity;
  @OneToOne
  private SizeEntity size;
}
