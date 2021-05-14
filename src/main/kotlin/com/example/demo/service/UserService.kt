package com.example.demo.service

import com.example.demo.domain.User
import com.example.demo.dto.UserDTO
import com.example.demo.exception.AccountServiceException
import com.example.demo.exception.ErrorType
import com.example.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun getUser(userId: Int): User {
        return userRepository.findById(userId)
            .orElseThrow {
                throw AccountServiceException(ErrorType.USER_NOT_FOUND)
            }
    }

    @Transactional
    fun changeUser(userDTO: UserDTO) : User {
        return userRepository.findById(userDTO.id)
            .orElseThrow {
                throw AccountServiceException(ErrorType.USER_NOT_FOUND)
            }.apply {
                this.name = userDTO.name
                this.address = userDTO.address
            }.run {
                userRepository.save(this)
            }
    }

    fun saveUser(userDTO: UserDTO): User {
        return User(name = userDTO.name, address = userDTO.address)
            .run {
                userRepository.save(this)
            }
    }
}
