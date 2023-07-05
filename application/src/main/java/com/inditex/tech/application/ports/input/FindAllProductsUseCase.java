package com.inditex.tech.application.ports.input;

import com.inditex.tech.domain.entities.Product;
import java.util.Collection;

public interface FindAllProductsUseCase {

  Collection<Product> execute();
}
