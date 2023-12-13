package com.abid.annotation.core.config

import com.abid.annotation.limiter.RateLimiterClient

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
class RateLimiterConfig {
    var maxLimitForSharedRateLimiter: Int = Int.MAX_VALUE
    var maxLimitForRateLimiter: Int = Int.MAX_VALUE
    var onLimitReachedPolicy: LimitReachedPolicy = LimitReachedPolicy.THROTTLE
    var limiterClient: RateLimiterClient.Type = RateLimiterClient.Type.GUAVA
}

enum class LimitReachedPolicy {
    THROTTLE, TO_MANY_REQUESTS, EXCEPTION
}