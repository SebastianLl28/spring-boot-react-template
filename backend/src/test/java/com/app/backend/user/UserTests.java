package com.app.backend.user;

import com.app.backend.AppTestSuite;
import com.app.backend.persistence.entity.User;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTests extends AppTestSuite {

  @Test
  public void crudUserTest() {

    User user = webClient.get()
        .uri(userUrl + "/1")
        .retrieve()
        .toEntity(User.class)
        .block().getBody();

    Assertions.assertNotNull(user);

    user.setUsername("admin@admin.com");

    List<User> users = webClient.get()
        .uri(userUrl)
        .retrieve()
        .toEntityList(User.class)
        .block().getBody();

    Assertions.assertNotNull(users);

    Assertions.assertNotEquals(0, users.size());

    User requestUser = new User();
    requestUser.setUsername("test@test.com");
    requestUser.setPassword("password");

    webClient.post()
        .uri(userUrl)
        .bodyValue(requestUser)
        .retrieve()
        .toBodilessEntity()
        .block();

    User savedUser = webClient.get()
        .uri(userUrl + "/2")
        .retrieve()
        .toEntity(User.class)
        .block().getBody();

    Assertions.assertNotNull(savedUser);
    Assertions.assertEquals(requestUser.getUsername(), savedUser.getUsername());
    Assertions.assertNotEquals(requestUser.getPassword(), savedUser.getPassword());

    requestUser.setUsername("update@test.com");

    webClient.put()
        .uri(userUrl + "/2")
        .bodyValue(requestUser)
        .retrieve()
        .toBodilessEntity()
        .block();

    user = webClient.get()
        .uri(userUrl + "/2")
        .retrieve()
        .toEntity(User.class)
        .block().getBody();

    Assertions.assertEquals(requestUser.getUsername(), user.getUsername());

    webClient.delete()
        .uri(userUrl + "/2")
        .retrieve()
        .toBodilessEntity()
        .block();

    boolean userNotFound = false;

    try {
      webClient.get().uri(userUrl + "/2")
          .retrieve()
          .toBodilessEntity()
          .block();
    } catch (Exception e) {
      userNotFound = true;
    }

    Assertions.assertTrue(userNotFound);

  }
}
