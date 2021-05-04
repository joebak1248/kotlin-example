package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("header", "body")
class BaseResponse<T>(val header: Header, val body: T?= null) {
    companion object {
        private val successHeader = Header(200, "ok")
        private val simpleSuccess = BaseResponse<Any>(successHeader)

        // 성공, 바디없음
        fun success() = simpleSuccess
        // 성공, 커스텀 바디
        fun success(body: Any) : BaseResponse<Any> = BaseResponse(successHeader, body)
    }

    class Header(val code: Int, val message: Any)

}