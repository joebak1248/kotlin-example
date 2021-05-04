package com.example.demo.config

import com.example.demo.dto.BaseResponse
import com.example.demo.exception.AccountServiceException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {

    companion object : KLogging()

    @ExceptionHandler(AccountServiceException::class)
    @ResponseStatus(HttpStatus.OK)
    fun accountServiceException(exception: AccountServiceException): BaseResponse<Any> {
        logger.error("[ERROR] ${exception.code}:${exception.message}")

        val header = BaseResponse.Header(exception.code, exception.message)
        return BaseResponse(header)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.OK)
    fun handleException(exception: AccountServiceException): BaseResponse<Any> {
        logger.error("[ERROR] ${exception.code}:${exception.message}")

        val header = BaseResponse.Header(exception.code, exception.message)
        return BaseResponse(header)
    }
}