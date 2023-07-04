package com.inditex.tech.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
public final class Product {
	private final Long id;
	private Integer sequence;
	private Set<Size> sizes;
}
