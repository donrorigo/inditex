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
  - In the `execute()` method:
    - **Retrieving products from the repository**: The time complexity of this operation is assumed to be O(1) because it depends on the implementation that we choose. It depends on the
    database chosen and the way we have to extract data.
    - **Filtering products**: The `filter()` operation in the stream iterates through each product and applies the productIsVisible() method as a predicate. The time complexity of this operation is O(n), where n is the number of products. 
    - **Collecting products**: The `collect()` operation collects the filtered products into a TreeSet using Collectors.toCollection(). The TreeSet maintains a sorted order based on the sequence of the products, so the time complexity of this operation is O(n log n), where n is the number of products. 
  
  - In the `productIsVisible()` method:
    - **Filtering sizes**: The `filter()` operation in the stream iterates through each size and applies the predicate size -> size.isBackSoon() || size.doWeHaveStock(). The time complexity of this operation depends on the number of sizes associated with the product and can be approximated as O(m), where m is the number of sizes. 
    - **Mapping sizes**: The `map()` operation transforms each size to a boolean value using Size::isSpecial. This operation has a time complexity of O(m). 
    - **Collecting sizes**: The `collect()` operation collects the mapped boolean values into an unmodifiable Set. The time complexity of this operation is O(m). 
    - Considering these operations, the time complexity of the productIsVisible() method can be approximated as O(m), where m is the number of sizes associated with the product.

In summary, the overall time complexity of the updated algorithm can be approximated as `O(n log n + m)`, where n is the number of products and m is the number of sizes associated with each product.

> Note: The TreeSet used in the collect() operation has an additional space complexity of O(n) to store the sorted set of products.

## Architecture Overview

Here you can find a simple diagram that explains the number of input and output ports that we have in the microservice.

![inditex.drawio.png](assets%2Finditex.drawio.png)

## Usage

You need to go to the path `main/src/test/java/com/inditex/tech/main/contract/TestApplication.java` to run the microservice. As it is working with testcontainers
you must have installed and running Docker in your machine.

## Testing

- **Unit testing**: You have an integration testing example in the application layer (I didn't create all test cases in every class of the project because
of time). Here you will find tests with JUnit5 and Mockito.
- **Contract testing**: You can find the integration with Spring Cloud contract in the adapter-api module. Here you will find the tests associated with this type of testing.
- **Integration testing**: You can find the integration testing using Testcontainers in the main module, here I'm checking if the microservice is connecting well with the database, 
and also it's checking the microservice correct functionality.

