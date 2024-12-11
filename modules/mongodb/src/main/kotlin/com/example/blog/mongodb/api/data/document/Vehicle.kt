package com.example.blog.mongodb.api.data.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "vehicles")
data class Vehicle(
    @Id
    val id: String? = null,
    val chassis: Chassis
)

data class Chassis(
    val axle: Axle
)

data class Axle(
    val row2: Row2
)

data class Row2(
    val wheel: Wheel
)

data class Wheel(
    val right: Right
)

data class Right(
    val speed: Long,
    val brake: Brake
)

data class Brake(

    val fluidLevel: Int,
    val isFluidLevelLow: Boolean,
    val padWear: Int,
    val isBrakesWorn: Boolean

)
