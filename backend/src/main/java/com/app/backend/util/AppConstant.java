package com.app.backend.util;

import org.springframework.data.domain.Sort;

public class AppConstant {

  public static final String API_VERSION_V1 = "/v1";

  public static final String SECURE_ENDPOINT_PREFIX = "/secure/api";

  public static final String PUBLIC_ENDPOINT_PREFIX = "/public/api";

  public static final String SECURE_BASE_ENDPOINT = SECURE_ENDPOINT_PREFIX + API_VERSION_V1;

  public static final String PUBLIC_BASE_ENDPOINT = PUBLIC_ENDPOINT_PREFIX + API_VERSION_V1;

  public static final int PAGE_SIZE = 10;

  public static final Sort DEFAULT_SORT = Sort.by(Sort.Order.desc("id"));

}
