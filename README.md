## Introduction

First of all, thank you for given the opportunity to participate in this selection process. It was a pleasure seeing the technical 
challenge that you have created for it. My main idea here was to build the simplest project that I could do, I have chosen a hexagonal architecture
for the implementation of the main use case. This use case is based in the filtering of products based in two casuistic:

1. There are products that have sizes marked as `back_soon`. This products cannot be filtered, if any size of the product is marked with this flag, the products
must be in the result of the response.
2. There are products that are marked as special. We can find products with `special` sizes, in these cases we must find stock or sizes marked as back soon in both sizes; 
special and non-special sizes. If we cannot find any record in these both cases, the product is going to be filtered. 

## Features

I have created a really simple API based on the product resource. We can apply the requested filter passing a non-required query parameter. So, based on this we can find two use cases:
* The basic one, where we don't filter any product.
* The stock filtered one, where we are filtering the non-stock products.

> You can find these two use cases in the application module (it's contained in the **input** port). 

## Technologies

The project is based in a hexagonal architecture implemented using **Java 17** using the last **Spring Boot 3.1.1** version available (you can see the version applied in the parent pom, this is the
last version available at 05/07/2023). Also, I have applied the **OpenAPI** specification for the controller creation (you can see that I have used the
`openapi-generator-maven-plugin` for the model and interface generation).

Also, I have implemented the repository port without choosing the proper database, it's implemented directly using the `.csv` files. But, in the future we can improve this
module and choose the correct database. In my opinion, as we have relations and we need to ensure the ACID principles, we could proceed with a classic **PostgresQL**.

## Getting Started

As I read in the documentation that you passed to me, you ask for two things:

- **Data structures**: The most important development here is how the domain has been declared and built. I have chosen a set
for the sizes because the set option doesn't allow duplicates. Also, we don't want to have an insertion order in these sizes. So, in my opinion, this 
structure is the best for this domain.
- **Temporal Complexity**: The temporal complexity of the `FindProductsStockFilterUseCase` implementation can be analyzed by examining the time complexity of each operation performed within the algorithm. 
  1. **Finding products**: The time complexity of `this.repository.findProducts()` depends on the implementation of the `findProducts()` method in the `repository` object. This will vary in the future, as soon as we choose the correct database.
  2. **Filtering products**: The `filter` operation in the `productIsVisible` method iterates through each product and checks if it meets the visibility criteria. The time complexity of this operation is O(n), where n is the number of products.
  3. **Sorting products**: The `sorted` operation in the `execute` method sorts the filtered products based on their sequence. The time complexity of this operation is O(n log n), where n is the number of filtered products.
  4. **Converting to List**: The `toList` operation collects the sorted products into a List. This operation has a time complexity of O(n), where n is the number of sorted products.
  5. **Checking product visibility**: The `productIsVisible` method performs various operations on the sizes of the product. Let's analyze them one by one:

      - Filtering sizes: The `filter` operation in `product.getSizes().stream()` iterates through each size and filters out the ones that are back soon or have stock. The time complexity of this operation is O(m), where m is the number of sizes in a product.

      - Mapping sizes: The `map` operation in `map(Size::isSpecial)` applies the `isSpecial` method to each size. The time complexity of this operation is O(m), where m is the number of sizes in a product.

      - Distinct sizes: The `distinct` operation eliminates duplicate special size values. The time complexity of this operation is O(m), where m is the number of distinct sizes.

      - Converting to List: The `toList` operation collects the distinct sizes into a List. This operation has a time complexity of O(m), where m is the number of distinct sizes.

      - Any match or size check: The `anyMatch` operation and the `sizes.size()` check both have a time complexity of O(m), where m is the number of distinct sizes.

  > Overall, the algorithm has a time complexity of `O(n + n log n + m)`, where **n** is the number of products and **m** is the average number of sizes per product. 

## Architecture Overview

Here you can find a simple diagram that explains the number of input and output ports that we have in the microservice.

![inditex.drawio.png](assets%2Finditex.drawio.png)

## Usage

This project has been built using `Amazon Corretto 17.0.7` in its `Intel` distribution. Also, the maven version used was the `3.8.1`.

## Testing

The testing that have been applied was unitary using `JUnit5` and `Mockito`. 

