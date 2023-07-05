package com.inditex.tech.adapter_repository_postgres.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@Entity
@Table(name = "product", schema = "public")
public final class ProductEntity {
  @Id
  private Long id;
  @Column(name = "sequence", nullable = false)
  private Integer sequence;
  @OneToMany(targetEntity = SizeEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id")
  private List<SizeEntity> sizes;
}
