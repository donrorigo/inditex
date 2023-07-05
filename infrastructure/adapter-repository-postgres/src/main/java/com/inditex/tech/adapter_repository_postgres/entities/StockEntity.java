package com.inditex.tech.adapter_repository_postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "stock", schema = "public")
public class StockEntity {
  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "quantity", nullable = false)
  @Min(0)
  private Integer quantity;
  @OneToOne
  @JoinColumn(name = "size_id")
  private SizeEntity size;
}
