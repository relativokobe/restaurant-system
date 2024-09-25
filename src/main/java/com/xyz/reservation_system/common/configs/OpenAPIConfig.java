package com.xyz.reservation_system.common.configs;

import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  /**
   * Add custom user-id header to mimic something like a token
   *
   * @return GroupedOpenApi object config
   */
  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .group("add-user-id-header")
        .addOperationCustomizer(
            (operation, handlerMethod) -> {
              operation.addParametersItem(
                  new HeaderParameter()
                      .name("user-id")
                      .description("User ID")
                      .required(false) // Set as needed
                  );
              return operation;
            })
        .build();
  }
}
