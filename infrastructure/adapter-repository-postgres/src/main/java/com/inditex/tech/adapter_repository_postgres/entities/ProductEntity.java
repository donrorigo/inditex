package com.inditex.tech.adapter_repository_postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "product")
public final class ProductEntity {
  @Id
  private final Long id;

  @Column(name = "sequence", nullable = false)
  private Integer sequence;

  @OneToMany
  private Set<SizeEntity> sizes;
}
