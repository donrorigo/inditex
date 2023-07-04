package com.inditex.tech.domain.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Stock {
	private final Size size;
	private Integer quantity;
}
