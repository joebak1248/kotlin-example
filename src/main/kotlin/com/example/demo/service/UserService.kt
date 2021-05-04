package com.example.demo.service

import com.example.demo.domain.User
import com.example.demo.dto.UserDTO
import com.example.demo.exception.AccountServiceException
import com.example.demo.exception.ErrorType
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getUser(userId: Int): User {
        return userRepository.findById(userId)
            .orElseThrow { throw AccountServiceException(ErrorType.USER_NOT_FOUND) }
    }

    @Transactional
    fun changeUser(userDTO: UserDTO) : User {
        val user = userRepository.findById(userDTO.id)
            .orElseThrow { throw AccountServiceException(ErrorType.USER_NOT_FOUND) }

        user.name = userDTO.name
        user.address = userDTO.address
        userRepository.save(user)

        return user
    }

    fun saveUser(userDTO: UserDTO): User {
        val newUser = User(userDTO.id, userDTO.name, userDTO.address);
        userRepository.save(newUser)
        return newUser
    }
}
