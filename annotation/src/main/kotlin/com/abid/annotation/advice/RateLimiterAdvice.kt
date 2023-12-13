package com.abid.annotation.advice

import com.abid.annotation.exception.ToManyRequestException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

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
@ControllerAdvice
class RateLimiterAdvice {

    @ExceptionHandler(ToManyRequestException::class)
    fun handleToManyRequest(exception: ToManyRequestException): ResponseEntity<Void> {
        val headers = HttpHeaders()
        headers.set("X-rate-Limit", "true")
        return ResponseEntity
            .status(HttpStatus.TOO_MANY_REQUESTS)
            .headers(headers)
            .build()
    }
}