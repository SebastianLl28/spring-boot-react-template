package com.app.backend.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void cleanDB() {
    List<String> tables = jdbcTemplate.queryForList("SHOW TABLES", String.class);
    jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0");

    for (String table : tables) {
      jdbcTemplate.execute("TRUNCATE TABLE " + table);
    }

    jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1");
  }
}
