package com.abid.annotation.service

import com.abid.annotation.core.config.RateLimiterConfig
import com.abid.annotation.limiter.RateLimiterClient
import com.abid.annotation.limiter.impl.GuavaRateLimiterClient
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

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

class RateLimiterService(private val config: RateLimiterConfig) {
    private val rateLimiterMap = HashMap<String, RateLimiterClient>()
    private val guavaRateLimiterClient: RateLimiterClient = GuavaRateLimiterClient()
    private lateinit var sharedRateLimiter: RateLimiterClient

    @PostConstruct
    fun build() {
        sharedRateLimiter = when (config.limiterClient) {
            RateLimiterClient.Type.GUAVA -> createSharedRateLimiterClient()
        }
    }

    private fun createSharedRateLimiterClient(): RateLimiterClient {
        return when (config.limiterClient) {
            RateLimiterClient.Type.GUAVA -> guavaRateLimiterClient.create(
                config.maxLimitForSharedRateLimiter,
                config.onLimitReachedPolicy
            )
        }
    }

    fun createRateLimiterClient(name: String, limit: Int, useEndpointRate: Boolean): RateLimiterClient {
        val rate = if (useEndpointRate) limit else config.maxLimitForRateLimiter
        return rateLimiterMap.getOrPut(name) { guavaRateLimiterClient.create(rate, config.onLimitReachedPolicy) }

    }

    fun applySharedLimit() = sharedRateLimiter.limit()


    fun applyLimit(name: String, limit: Int, useRate: Boolean) = createRateLimiterClient(name, limit, useRate).limit()


}