package com.abid.rate.limiter.example.config

import com.abid.annotation.core.annotation.EnableRateLimiting
import com.abid.annotation.core.config.LimitReachedPolicy
import com.abid.annotation.core.config.RateLimiterConfig
import com.abid.annotation.limiter.RateLimiterClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order

/**
 * (C) Copyright 2005-2023 hSenid Software International (Pvt) Limited.
 * All Rights Reserved.
 * <p/>
 * These materials are unpublished, proprietary, confidential source code of
 * hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 * of hSenid Software International (Pvt) Limited.
 * <p/>
 * hSenid Software International (Pvt) Limited retains all title to and intellectual
 * property rights in these materials.
 */
@Configuration
@EnableRateLimiting
class Config {

    @Bean
    @Order
    fun rateLimitConfig() = RateLimiterConfig().apply {
        maxLimitForSharedRateLimiter = 10
        maxLimitForRateLimiter = 5
        onLimitReachedPolicy = LimitReachedPolicy.TO_MANY_REQUESTS
        limiterClient = RateLimiterClient.Type.GUAVA
    }
}