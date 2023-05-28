package com.yulam.acalib.configuration.cache;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@AllArgsConstructor
public class CacheConfig {

  private final CacheCredentials credentials;

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    configuration.setHostName(credentials.getHost());
    configuration.setPort(credentials.getPort());
    configuration.setPassword(RedisPassword.of(credentials.getPassword()));
    configuration.setUsername(credentials.getUsername());

    return new JedisConnectionFactory(configuration);
  }

  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
    configuration.entryTtl(Duration.ofMinutes(credentials.getTtl()));
    configuration.disableCachingNullValues();
    configuration.usePrefix();
    configuration.prefixCacheNameWith(credentials.getPrefix());

    return configuration;
  }

  @Bean
  public StringRedisSerializer stringSerializer() {
        return new StringRedisSerializer();
    }

  @Bean
  public GenericJackson2JsonRedisSerializer jackson2JsonSerializer() {
    return new GenericJackson2JsonRedisSerializer();
  }

  @Bean
  public JdkSerializationRedisSerializer jdkSerializer() {
    return new JdkSerializationRedisSerializer();
  }

  @Bean
  @Primary
  public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    template.setKeySerializer(stringSerializer());
    template.setValueSerializer(jackson2JsonSerializer());
    template.setHashValueSerializer(jdkSerializer());
    template.setEnableTransactionSupport(true);

    return template;
  }

  @Bean
  public RedisCacheManager cacheManager() {
    return RedisCacheManager
            .builder(jedisConnectionFactory())
            .cacheDefaults(cacheConfiguration())
            .transactionAware()
            .build();
  }
}
