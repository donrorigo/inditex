package com.inditex.tech.application.ports.output;

import com.inditex.tech.domain.entities.Product;

import java.util.Collection;

public interface ProductRepository {

	Collection<Product> findProducts();
}
