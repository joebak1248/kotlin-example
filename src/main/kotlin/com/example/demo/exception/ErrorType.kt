package com.example.demo.exception

enum class ErrorType(val code: Int, val message: String) {
    USER_NOT_FOUND(code = 100, message = "User not found.")
}