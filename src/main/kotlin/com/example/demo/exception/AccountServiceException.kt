package com.example.demo.exception

class AccountServiceException(type: ErrorType, message: String? = null) : RuntimeException() {
    var code: Int = 0
    override var message: String = ""

    init {
        this.code = type.code
        this.message = message?: type.message
    }
}