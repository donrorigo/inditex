package com.inditex.tech.adapter_repository_postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
@Entity
@Table(name = "product", schema = "public")
public final class ProductEntity {
  @Id
  private Long id;
  @Column(name = "sequence", nullable = false)
  private Integer sequence;
  @OneToMany(mappedBy = "product", targetEntity = SizeEntity.class)
  private Set<SizeEntity> sizes;
}
