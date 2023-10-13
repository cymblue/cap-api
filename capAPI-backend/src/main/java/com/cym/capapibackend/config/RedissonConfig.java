package com.cym.capapibackend.config;


import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "spring.client")
@Data
public class RedissonConfig {
    private String host;

    private String port;

    private Integer database;

    private String password;
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+host+":"+port)
                .setDatabase(database)
                .setPassword(password);
        RedissonClient redissonClient = Redisson.create();
        return redissonClient;

    }
}
