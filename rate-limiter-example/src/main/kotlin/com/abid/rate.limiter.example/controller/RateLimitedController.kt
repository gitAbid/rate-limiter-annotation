package com.abid.rate.limiter.example.controller

import com.abid.annotation.core.annotation.RateLimit
import org.springframework.context.ApplicationContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

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

@RestController
class RateLimitedController(val context: ApplicationContext) {

    @GetMapping("/hello")
    @RateLimit
    fun index(): String {
        return "Hello World"
    }
}