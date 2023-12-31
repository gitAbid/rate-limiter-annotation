package com.abid.annotation.limiter

import com.abid.annotation.core.config.LimitReachedPolicy

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
interface RateLimiterClient {
    enum class Type {
        GUAVA
    }

    fun type(): Type
    fun create(limit: Int, onLimitReachedPolicy: LimitReachedPolicy): RateLimiterClient
    fun limit()
}