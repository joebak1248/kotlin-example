package com.example.demo.dto

import io.swagger.annotations.ApiModelProperty

data class UserDTO (
    @ApiModelProperty(name = "Id", example = "1", position = 0)
    val id: Int,
    @ApiModelProperty(name = "이름", example = "네이버파이낸셜", position = 1)
    val name: String,
    @ApiModelProperty(name = "주소", example = "경기도 성남시", position = 2)
    val address: String
)