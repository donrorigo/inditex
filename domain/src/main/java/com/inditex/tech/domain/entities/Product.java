package com.inditex.tech.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
@Builder
public final class Product {
	private final Long id;
	@Builder.Default private Integer sequence = 0;
	@Builder.Default private Set<Size> sizes = new HashSet<>();
}
