package com.inditex.tech.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Size {
	@EqualsAndHashCode.Include private final Long id;
	@EqualsAndHashCode.Include private final Long productId;
	@Builder.Default private boolean backSoon = false;
	@Builder.Default private boolean special = false; // it depends on product if this attribute can be modifiable or not
	@Builder.Default private Optional<Stock> stock = Optional.empty();

	/**
	 * Checks whether there is stock available.
	 *
	 * @return {@code true} if there is stock available, {@code false} otherwise.
	 */
	public Boolean doWeHaveStock(){
		return this.stock.map(presentStock -> presentStock.getQuantity() > 0).orElse(false);
	}


}
