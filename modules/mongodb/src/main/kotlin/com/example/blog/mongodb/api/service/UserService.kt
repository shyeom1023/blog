package com.example.blog.mongodb.api.service

import com.example.blog.mongodb.api.data.UserRepository
import com.example.blog.mongodb.api.data.document.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: String): User? = userRepository.findById(id).orElse(null)

    fun getUsersByName(name: String): List<User> = userRepository.findByName(name)

    fun createUser(user: User): User = userRepository.save(user)

    fun updateUser(id: String, updatedUser: User): User? {
        val existingUser = userRepository.findById(id).orElse(null)
        return if (existingUser != null) {
            val newUser = existingUser.copy(
                name = updatedUser.name,
                email = updatedUser.email
            )
            userRepository.save(newUser)
        } else {
            null
        }
    }

    fun deleteUser(id: String): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}
