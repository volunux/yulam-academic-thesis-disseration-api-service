package com.yulam.acalib.configuration.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "yulam.acalib.cache")
public class CacheCredentials {

  @NotBlank
  private String host;

  @NotBlank
  private Integer port;

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  private String prefix;

  @NotBlank
  private Integer ttl;
}
