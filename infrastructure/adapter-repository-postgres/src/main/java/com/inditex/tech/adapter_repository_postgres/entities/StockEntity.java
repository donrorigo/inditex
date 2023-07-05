package com.inditex.tech.adapter_repository_postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "stock", schema = "public")
public class StockEntity {
  @Id
  @GeneratedValue
  @Column(name = "size_id")
  private Long id;
  @Column(name = "quantity", nullable = false)
  @Min(0)
  private Integer quantity;
}
