package com.example.demo.exception

enum class ErrorType(val code: Int, val message: String) {
    USER_NOT_FOUND(code = 100, message = "User not found."),
    DUPLICATED_REQUEST(code = 101, message = "Duplicated request.")
}