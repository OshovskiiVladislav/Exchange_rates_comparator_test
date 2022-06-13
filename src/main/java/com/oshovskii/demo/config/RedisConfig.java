package com.oshovskii.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.oshovskii.demo.repositories")
public class RedisConfig {
}
