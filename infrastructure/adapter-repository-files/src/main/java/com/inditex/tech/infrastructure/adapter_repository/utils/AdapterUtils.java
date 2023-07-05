package com.inditex.tech.infrastructure.adapter_repository.utils;

import com.inditex.tech.infrastructure.adapter_repository.model.ProductEntity;
import com.inditex.tech.infrastructure.adapter_repository.model.SizeEntity;
import com.inditex.tech.infrastructure.adapter_repository.model.StockEntity;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import org.springframework.util.ResourceUtils;

public final class AdapterUtils {

  private AdapterUtils(){
    throw new AssertionError();
  }

  public static List<ProductEntity> readProducts() {
    try (var products = Files.lines(ResourceUtils.getFile("classpath:product.csv").toPath(),
        StandardCharsets.UTF_8)) {
      return products.map(stockLine -> {
        final var row = stockLine.split(", ");
        return ProductEntity.builder().id(Long.parseLong(row[0]))
            .sequence(Integer.parseInt(row[1]))
            .build();
      }).toList();
    } catch (IOException e) {
      throw new RuntimeException(e); // TODO: Define a custom exception for error management
    }
  }

  public static List<SizeEntity> readSizes() {
    try (var sizes = Files.lines(ResourceUtils.getFile("classpath:size-1.csv").toPath(),
        StandardCharsets.UTF_8)) {
      return sizes
          .map(stockLine -> {
            final var row = stockLine.split(", ");
            return SizeEntity.builder().id(Long.parseLong(row[0])).productId(Long.parseLong(row[1]))
                .backSoon(Boolean.parseBoolean(row[2])).special(Boolean.parseBoolean(row[3]))
                .build();
          }).toList();
    } catch (IOException e) {
      throw new RuntimeException(e); // TODO: Define a custom exception for error management
    }
  }

  public static List<StockEntity> readStocks() {
    try (var stocks = Files.lines(ResourceUtils.getFile("classpath:stock.csv").toPath(),
        StandardCharsets.UTF_8)) {

      return stocks.map(stockLine -> {
        final var row = stockLine.split(", ");
        return StockEntity.builder().sizeId(Long.parseLong(row[0]))
            .quantity(Integer.parseInt(row[1])).build();
      }).toList();

    } catch (IOException e) {
      throw new RuntimeException(e); // TODO: Define a custom exception for error management
    }
  }

}
