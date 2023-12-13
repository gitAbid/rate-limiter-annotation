package com.abid.annotation.core.annotation

import com.abid.annotation.config.RateLimitConfiguration
import org.springframework.context.annotation.Import

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
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RateLimit(val limit: Int = Int.MAX_VALUE, val useLimit: Boolean = false)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SharedRateLimit


@Target(AnnotationTarget.CLASS)
@Import(RateLimitConfiguration::class)
annotation class EnableRateLimiting