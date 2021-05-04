package com.example.demo.controller

import com.example.demo.domain.User
import com.example.demo.dto.BaseResponse
import com.example.demo.dto.BaseResponse.Companion.success
import com.example.demo.dto.UserDTO
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController (
    val userService: UserService
) {
    @GetMapping("/{id}")
    fun getUser(@PathVariable(name = "id") userId: Int) : BaseResponse<Any> {
        val user: User = userService.getUser(userId)
        return success(UserDTO(user.id, user.name, user.address))
    }

    @PutMapping
    fun updateUser(@RequestBody userDTO: UserDTO) : BaseResponse<Any> {
        val user: User = userService.changeUser(userDTO)
        return success(UserDTO(user.id, user.name, user.address))
    }

    @PostMapping
    fun saveUser(@RequestBody userDTO: UserDTO) : BaseResponse<Any> {
        val user: User = userService.saveUser(userDTO)
        return success(UserDTO(user.id, user.name, user.address))
    }
}