package com.app.backend.service;

import javax.sql.DataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

@Service
@DependsOn("flyway")
public class TestDataPopulatingService implements ApplicationContextAware {

  @Autowired
  private ApplicationContext ctx;

  public void populateTestData() {
    loadTestData("classpath:db/setup/test/UserSetup.sql");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.ctx = applicationContext;
  }

  private void loadTestData(String resourcePath) {
    Resource resource = ctx.getResource(resourcePath);
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
    databasePopulator.setSqlScriptEncoding("UTF-8");
    databasePopulator.execute((DataSource) ctx.getBean("dataSource"));
  }
}
