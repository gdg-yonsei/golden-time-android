package com.next.goldentime.usecase.user

import com.next.goldentime.repository.user.User
import com.next.goldentime.repository.user.UserRepository

class UserUseCase(private val userRepository: UserRepository) {
    fun watchProfile() = userRepository.watchUser()
    fun watchName() = userRepository.watchName()

    suspend fun setUser(user: User) = userRepository.setUser(user)
}