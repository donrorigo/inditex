package com.inditex.tech.adapter_repository_postgres;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan("com.inditex.tech.adapter_repository_postgres")
@ComponentScan("com.inditex.tech.adapter_repository_postgres")
public class RepositoryAutoConfiguration {

}
