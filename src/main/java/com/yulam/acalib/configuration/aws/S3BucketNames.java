package com.yulam.acalib.configuration.aws;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aws.s3.bucket.name")
public class S3BucketNames {

  private String acalibMaterial;
  private String memberAvatar;
  private String countryMapLogo;
  private String stateMapLogo;
}
