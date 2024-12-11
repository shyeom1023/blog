package com.example.blog.mongodb.api.data

import com.example.blog.mongodb.api.data.document.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByName(name: String): List<User>
}
