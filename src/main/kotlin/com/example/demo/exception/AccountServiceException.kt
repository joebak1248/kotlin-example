package com.example.demo.exception

class AccountServiceException : RuntimeException {
    var code: Int = 0
    override var message: String = ""

    constructor(type: ErrorType, message: String?= null) {
        this.code = type.code
        this.message = message?: type.message
    }
}