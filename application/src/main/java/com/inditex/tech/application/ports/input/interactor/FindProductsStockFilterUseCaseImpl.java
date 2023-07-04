package com.inditex.tech.application.ports.input.interactor;

import com.inditex.tech.application.ports.input.FindProductsStockFilterUseCase;
import com.inditex.tech.application.ports.output.ProductRepository;
import com.inditex.tech.domain.entities.Product;
import com.inditex.tech.domain.entities.Size;
import java.util.Comparator;
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
		final var products = this.repository.findProducts();
		return products.stream()
				.filter(this::productIsVisible)
				.sorted(Comparator.comparingInt(Product::getSequence))
				.toList();
	}

	/**
	 * Checks if the specified product has any available stock, or it's back soon.
	 * Also, in the special sizes cases, it only returns {@code true} if there are
	 * special and non-special sizes.
	 *
	 * @param product the product to check
	 * @return {@code true} if the product has stock, {@code false} otherwise
	 */
	private boolean productIsVisible(final Product product) {

		final var sizes = product.getSizes()
				.stream()
				.filter(size -> size.isBackSoon() || size.getStock().isPresent())
				.map(Size::isSpecial)
				.distinct()
				.toList();

		return sizes.stream().anyMatch(special -> special.equals(false)) || sizes.size() > 1L;
	}

}
