package com.inditex.tech.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public final class Size {
	private final Long id;
	private boolean backSoon;
	private boolean special; // it depends on product if this attribute can be modifiable or not
	@Builder.Default private Optional<Stock> stock = Optional.empty();
}
