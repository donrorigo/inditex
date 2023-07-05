package com.inditex.tech.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;


@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Product {
	@EqualsAndHashCode.Include private final Long id;
	@Builder.Default private Integer sequence = 0;
	@Builder.Default private Set<Size> sizes = new HashSet<>();
}
