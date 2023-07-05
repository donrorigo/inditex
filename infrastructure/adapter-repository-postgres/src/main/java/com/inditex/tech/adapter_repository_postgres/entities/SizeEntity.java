package com.inditex.tech.adapter_repository_postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "size")
public class SizeEntity {
  @Id
  private final Long id;
  @Column(name = "back_soon", nullable = false)
  private boolean backSoon;
  @Column(name = "special", nullable = false)
  private boolean special;
  @OneToOne
  private Optional<StockEntity> stock;
  @ManyToOne(fetch = FetchType.LAZY)
  private ProductEntity product;
}
