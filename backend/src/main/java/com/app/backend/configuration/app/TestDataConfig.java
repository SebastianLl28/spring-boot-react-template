package com.app.backend.configuration.app;

import com.app.backend.service.TestDataPopulatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@Configuration
public class TestDataConfig {

  private TestDataPopulatingService testDataPopulatingService;

  @Autowired
  @DependsOn("flyway")
  public void setTestDataPopulatingService(TestDataPopulatingService testDataPopulatingService) {
    this.testDataPopulatingService = testDataPopulatingService;
  }

  @Bean(name = "populateTestData")
  @DependsOn("flyway")
  @Profile("test")
  public boolean populateTestData() {
    testDataPopulatingService.populateTestData();
    return true;
  }
}
