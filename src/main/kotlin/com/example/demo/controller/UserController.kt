package com.example.demo.controller

import com.example.demo.domain.User
import com.example.demo.dto.BaseResponse
import com.example.demo.dto.BaseResponse.Companion.success
import com.example.demo.dto.UserDTO
import com.example.demo.service.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController (
    val userService: UserService
) {
    @ApiOperation(value = "조회 테스트", notes = "")
    @ApiResponses(
        ApiResponse(code = 200, message = "ok"),
        ApiResponse(code = 100, message = "Not found user.")
    )
    @GetMapping("/{id}")
    fun getUser(@PathVariable(name = "id") userId: Int) : BaseResponse<Any> {
        val user: User = userService.getUser(userId)
        return success(UserDTO(user.id, user.name, user.address))
    }

    @ApiOperation(value = "변경 테스트", notes = "")
    @ApiResponses(
        ApiResponse(code = 200, message = "ok"),
        ApiResponse(code = 100, message = "Not found user.")
    )
    @PutMapping
    fun updateUser(@RequestBody userDTO: UserDTO) : BaseResponse<Any> {
        val user: User = userService.changeUser(userDTO)
        return success(UserDTO(user.id, user.name, user.address))
    }

    @ApiOperation(value = "입력 테스트", notes = "")
    @ApiResponses(
        ApiResponse(code = 200, message = "ok"),
        ApiResponse(code = 101, message = "Duplicated request.")
    )
    @PostMapping
    fun saveUser(@RequestBody userDTO: UserDTO) : BaseResponse<Any> {
        val user: User = userService.saveUser(userDTO)
        return success(UserDTO(user.id, user.name, user.address))
    }
}