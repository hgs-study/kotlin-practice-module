package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.util.fail
import com.group.libraryapp.util.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun saveUser(request: UserCreateRequest){
        val newUser = User(request.name, request.age)
        userRepository.save(newUser)
    }

    fun getUsers(): List<UserResponse>{
        return userRepository.findAll()
            .map {UserResponse(it)} // 람다에서 파라미터를 명시적으로 적어주지 않아도 it으로 사용 가능

            // 방법 1. .map{user -> UserResponse(user)}
            // 방법 2. .map(::UserResponse)
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest){
        val user = userRepository.findByIdOrThrow(request.id)
        user.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String){
        val user = userRepository.findByName(name) ?: fail()
        userRepository.delete(user)
    }

}