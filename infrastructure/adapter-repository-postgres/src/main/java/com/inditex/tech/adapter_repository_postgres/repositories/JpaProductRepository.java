package com.inditex.tech.adapter_repository_postgres.repositories;

import com.inditex.tech.adapter_repository_postgres.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

}
