package com.inditex.tech.domain.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Stock {
	@Builder.Default private Integer quantity = 0;
}
