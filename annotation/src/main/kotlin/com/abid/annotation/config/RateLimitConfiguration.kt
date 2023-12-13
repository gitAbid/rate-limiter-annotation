package com.abid.annotation.config

import com.abid.annotation.core.config.RateLimiterConfig
import com.abid.annotation.service.RateLimiterService
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

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
@ComponentScan("com.abid.annotation")
class RateLimitConfiguration(private val context: ApplicationContext) {



    @Bean
    fun rateLimiterConfig() = runCatching { context.getBean(RateLimiterConfig::class.java) }
        .onFailure { RateLimiterConfig() }
        .getOrElse { RateLimiterConfig() }
        .let { RateLimiterService(it) }
}