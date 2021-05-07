package com.example.demo.service

import com.example.demo.domain.User
import com.example.demo.dto.UserDTO
import com.example.demo.exception.AccountServiceException
import com.example.demo.exception.ErrorType
import com.example.demo.repository.UserRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class UserServiceTest {

    companion object {
        val userDTO = UserDTO(1, "changeName", "changeAddress")
    }

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userService: UserService


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findUser() {
        val expectedUser = User("name", "address")
        whenever(userRepository.findById(any()))
            .thenReturn(Optional.of(expectedUser))

        val foundUser: User = userService.getUser(1)

        Assertions.assertEquals(expectedUser, foundUser)
    }

    @Test
    fun `find User, but User not found`() {
        whenever(userRepository.findById(any())).thenReturn(Optional.empty())

        val exception = Assertions.assertThrows(AccountServiceException::class.java) {
            userService.getUser(1)
        }

        Assertions.assertEquals(ErrorType.USER_NOT_FOUND.code, exception.code)
        Assertions.assertEquals(ErrorType.USER_NOT_FOUND.message, exception.message)
    }

    @Test
    fun changeUser() {
        val user = User("name", "address")
        whenever(userRepository.findById(userDTO.id)).thenReturn(Optional.of(user))

        userService.changeUser(userDTO)
        verify(userRepository).save(any())
    }

    @Test
    fun `change User, but User not found`() {
        whenever(userRepository.findById(any())).thenReturn(Optional.empty())

        val exception = Assertions.assertThrows(AccountServiceException::class.java) {
            userService.changeUser(userDTO)
        }

        Assertions.assertEquals(ErrorType.USER_NOT_FOUND.code, exception.code)
        Assertions.assertEquals(ErrorType.USER_NOT_FOUND.message, exception.message)
    }

    @Test
    fun saveNewUser() {
        userService.saveUser(userDTO)
        verify(userRepository).save(any())
    }
}