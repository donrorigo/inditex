package com.inditex.tech.adapter_repository_postgres.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "size", schema = "public")
public class SizeEntity {
  @Id
  private Long id;
  @Column(name = "back_soon", nullable = false)
  private boolean backSoon;
  @Column(name = "special", nullable = false)
  private boolean special;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id")
  private StockEntity stock;
  @ManyToOne(targetEntity = ProductEntity.class)
  private ProductEntity product;
}
