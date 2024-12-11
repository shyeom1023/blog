package com.example.blog.mongodb.api.service

import com.example.blog.mongodb.api.data.document.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class VehicleServiceTest {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    // 테스트용 데이터 세팅
    @BeforeEach
    fun setUp() {
        // MongoDB에 미리 차량 데이터를 삽입합니다.
        val vehicle = Vehicle(
            id = "vehicle_test2",
            chassis = Chassis(
                axle = Axle(
                    row2 = Row2(
                        wheel = Wheel(
                            right = Right(
                                speed = 120,
                                brake = Brake(
                                    fluidLevel = 801,
                                    isFluidLevelLow = false,
                                    padWear = 10,
                                    isBrakesWorn = false
                                )
                            )
                        )
                    )
                )
            )
        )
        mongoTemplate.save(vehicle, "vehicles")
    }

    @Test
    fun `Verify data exists`() {
        val count = mongoTemplate.count(Query(), "vehicles")
        println("Total documents in 'vehicles' collection: $count")
    }

    @Test
    fun `MongoTemplate ID 조회 테스트`() {
        val vehicleId = "vehicle_test"
        val query = Query(Criteria.where("id").`is`(vehicleId))
        val result = mongoTemplate.findOne(query, Vehicle::class.java)
        println(result)
    }
}
