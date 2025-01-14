package com.app.backend;

import com.app.backend.presentation.dto.AuthenticationRequest;
import com.app.backend.presentation.dto.AuthenticationResponse;
import com.app.backend.service.TestDataPopulatingService;
import com.app.backend.service.TestService;
import com.app.backend.util.AppConstant;
import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {
    BackendApplication.class})
public class AppTestSuite {

  @Autowired
  private TestService testService;

  @Autowired
  private TestDataPopulatingService testDataPopulatingService;

//  @Autowired
//  private ApplicationContext context;


  @Value("${local.server.port}")
  private int port;

  protected String baseContext = "http://localhost:";

  protected String secureBaseVersion = AppConstant.SECURE_BASE_ENDPOINT;

  protected String publicBaseVersion = AppConstant.PUBLIC_BASE_ENDPOINT;

  protected WebTestClient webTestClient;

  protected WebClient webClient;

  protected URI userUrl;

  @BeforeEach
  public void setUp() throws Exception {

    String baseUrl = baseContext + port + secureBaseVersion;

    String publicBaseUrl = baseContext + port + publicBaseVersion;

    this.userUrl = new URI(baseUrl + "/user");

    testService.cleanDB();

    testDataPopulatingService.populateTestData();

    this.webClient = WebClient.builder()
        .filter((request, next) -> {
          ClientRequest filtered = ClientRequest.from(request)
              .headers(headers -> headers.setBearerAuth(getBearerToken())).build();
          return next.exchange(filtered);
        })
        .build();
  }


  private String getBearerToken() {

    AuthenticationResponse authenticationResponse = WebClient.builder()
        .build().post()
        .uri(baseContext + port + publicBaseVersion + "/auth/login")
        .bodyValue(new AuthenticationRequest("admin@admin.com", "123456"))
        .retrieve()
        .toEntity(AuthenticationResponse.class)
        .mapNotNull(response -> response == null ? null : response.getBody())
        .block();

    if (authenticationResponse == null) {
      throw new RuntimeException("Authentication failed");
    }

    return authenticationResponse.getJwt();
  }
}
