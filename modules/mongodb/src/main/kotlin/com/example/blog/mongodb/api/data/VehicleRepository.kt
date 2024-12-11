package com.example.blog.mongodb.api.data

import com.example.blog.mongodb.api.data.document.User
import com.example.blog.mongodb.api.data.document.Vehicle
import org.springframework.data.mongodb.repository.MongoRepository

interface VehicleRepository : MongoRepository<Vehicle, String> {

}
