package com.app.backend.configuration.app;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@Configuration
public class FlywayConfig {

  @Bean(name = "flyway")
  @Profile("test")
  public Flyway flyway(DataSource dataSource) {
    Flyway flyway = Flyway.configure().dataSource(dataSource).locations("classpath:db/migration")
        .cleanDisabled(false).load();

    flyway.clean();

    flyway.migrate();

    return flyway;
  }

  @Bean(name = "flyway")
  @DependsOn("dataSource")
  @Profile("dev")
  public Flyway devFlyway(DataSource dataSource) {
    Flyway flyway = Flyway.configure().dataSource(dataSource).locations("classpath:db/migration")
        .load();

    flyway.migrate();

    return flyway;
  }
}
