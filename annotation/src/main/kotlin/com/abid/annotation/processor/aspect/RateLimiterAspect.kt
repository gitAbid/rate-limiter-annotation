package com.abid.annotation.processor.aspect

import com.abid.annotation.core.annotation.RateLimit
import com.abid.annotation.service.RateLimiterService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
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
@Aspect
@Component
class RateLimiterAspect(private val rateLimiterService: RateLimiterService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Around("@annotation(com.abid.annotation.core.annotation.RateLimit)")
    fun rateLimit(joinPoint: ProceedingJoinPoint): Any {
        val methodSignature = joinPoint.signature as MethodSignature
        val key = methodSignature.method.toGenericString()
        val annotation: RateLimit = methodSignature.method.getAnnotation(RateLimit::class.java)
        rateLimiterService.applyLimit(key, annotation.limit, annotation.useLimit)
        logger.trace("Rate limit applied")
        return joinPoint.proceed()
    }


    @Around("@annotation(com.abid.annotation.core.annotation.SharedRateLimit)")
    fun sharedRateLimit(joinPoint: ProceedingJoinPoint): Any {
        rateLimiterService.applySharedLimit()
        logger.trace("Shared Rate limit applied")
        return joinPoint.proceed()
    }
}

