package com.abid.annotation.limiter.impl

import com.abid.annotation.core.config.LimitReachedPolicy
import com.abid.annotation.exception.ToManyRequestException
import com.abid.annotation.limiter.RateLimiterClient
import com.google.common.util.concurrent.RateLimiter

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
class GuavaRateLimiterClient : RateLimiterClient {
    private lateinit var rateLimiter: RateLimiter
    private var onLimitReachedPolicy: LimitReachedPolicy = LimitReachedPolicy.THROTTLE
    override fun type() = RateLimiterClient.Type.GUAVA

    override fun create(limit: Int, onLimitReachedPolicy: LimitReachedPolicy): RateLimiterClient {
        this.rateLimiter = RateLimiter.create(limit.toDouble())
        this.onLimitReachedPolicy = onLimitReachedPolicy
        return this
    }

    override fun limit() {
        when (onLimitReachedPolicy) {
            LimitReachedPolicy.THROTTLE -> rateLimiter.acquire()
            LimitReachedPolicy.TO_MANY_REQUESTS -> {
                val tryAcquire = rateLimiter.tryAcquire()
                println("TO_MANY_REQUESTS -> $tryAcquire")
                if (!tryAcquire) throw ToManyRequestException()
            }

            LimitReachedPolicy.EXCEPTION -> {
                val tryAcquire = rateLimiter.tryAcquire()
                println("EXCEPTION -> $tryAcquire")

                if (!tryAcquire) throw ToManyRequestException()
            }
        }
    }
}