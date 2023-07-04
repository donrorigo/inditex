package com.inditex.tech.application.ports.input.interactor;

import com.inditex.tech.application.ports.input.FindProductsStockFilterUseCase;
import com.inditex.tech.application.ports.output.ProductRepository;
import com.inditex.tech.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindProductsStockFilterUseCaseImpl implements FindProductsStockFilterUseCase {

	private final ProductRepository repository;

	@Override
	public Collection<Product> execute () {
		return this.repository.findProducts();
	}

}
