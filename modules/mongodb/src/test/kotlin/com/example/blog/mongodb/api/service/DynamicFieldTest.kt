package com.example.blog.mongodb.api.service

import org.bson.Document
import org.bson.json.JsonParseException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class DynamicFieldTest {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    // 테스트용 데이터 세팅
    @BeforeEach
    fun setUp() {
        jsonTypeSave()
    }

    fun jsonTypeSave() {
        // JSON 문자열을 Document로 변환
        val json = """
            {
                "_id": "vehicle_test4",
                "chassis": {
                    "axle": {
                        "row2": {
                            "wheel": {
                                "right": {
                                    "speed": 120,
                                    "brake": {
                                        "fluidLevel": 801,
                                        "isFluidLevelLow": false,
                                        "padWear": 10,
                                        "isBrakesWorn": false
                                    }
                                }
                            }
                        }
                    }
                }
            }
        """

        try {
            val document = Document.parse(json)  // JSON 문자열을 Document로 변환
            mongoTemplate.save(document, "vehicles")
        } catch (e: JsonParseException) {
            println("Failed to parse JSON: ${e.message}")
        }
    }

    fun documentTypeSave() {
        // MongoDB에서 사용하는 특수 타입을 사용하여 데이터 생성
        val vehicle = Document("_id", "vehicle_test3")
            .append(
                "chassis", Document(
                    "axle", Document(
                        "row2", Document(
                            "wheel", Document(
                                "right", Document("speed", 120)
                                    .append(
                                        "brake", Document("fluidLevel", 801)
                                            .append("isFluidLevelLow", false)
                                            .append("padWear", 10)
                                            .append("isBrakesWorn", false)

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
        val vehicleId = "vehicle_test4"
        val query = Query(Criteria.where("_id").`is`(vehicleId))
        val result = mongoTemplate.findOne(query, Document::class.java, "vehicles")
        println(result)
    }

    @Test
    fun `Test retrieving specific path dynamically using Aggregation`() {
        val vehicleId = "vehicle_test4"
        // 조회할 path 지정
        val path = "chassis.axle.row2.wheel.right"
        val fields = path.split('.')

        // MongoDB에서 _id가 vehicle_test4인 데이터 조회
        val aggregation = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("_id").`is`(vehicleId)),  // ID로 필터링
            Aggregation.project().and(path).`as`(fields.last())
                .andExclude("_id")  // 동적 경로를 이용한 프로젝션
        )

        // Aggregation 실행
        val result = mongoTemplate.aggregate(aggregation, "vehicles", Document::class.java)
        println(result.mappedResults.firstOrNull()?.get(fields.last()))
    }

    @Test
    fun `복잡한 수정 로직`() {
        // 업데이트할 조건 (_id가 "vehicle_test4"인 문서)
        val query = Query(Criteria.where("_id").`is`("vehicle_test4"))

        // 업데이트할 필드와 값 ($set 연산 사용)
        val update = Update().set("chassis.axle.row2.wheel.right.brake.isFluidLevelLow", true)

        // 업데이트 실행
        val result = mongoTemplate.updateFirst(query, update, "vehicles")

        // 결과 확인 (성공적으로 업데이트되었는지)
        println("Matched count: ${result.matchedCount}, Modified count: ${result.modifiedCount}")
    }

    @Test
    fun `복잡한 추가 로직`() {
        // 업데이트할 조건 (_id가 "vehicle_test4"인 문서)
        val query = Query(Criteria.where("_id").`is`("vehicle_test4"))

        // 업데이트할 필드와 값 ($set 연산 사용)
        val update = Update().set("chassis.axle.row2.wheel.right.brake.isSpin", true)

        // 업데이트 실행
        val result = mongoTemplate.updateFirst(query, update, "vehicles")

        // 결과 확인 (성공적으로 업데이트되었는지)
        println("Matched count: ${result.matchedCount}, Modified count: ${result.modifiedCount}")
    }

    @Test
    fun `brake 데이터를 right에서 left로 이동`() {
        val vehicleId = "vehicle_test4"
        val query = Query(Criteria.where("_id").`is`("vehicle_test4"))

        // MongoDB에서 _id가 vehicle_test4인 데이터 조회
        val aggregation = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("_id").`is`(vehicleId)),  // ID로 필터링
            Aggregation.project().and("chassis.axle.row2.wheel.right.brake").`as`("brake")
                .andExclude("_id")  // 동적 경로를 이용한 프로젝션
        )

        // Aggregation 실행
        val result = mongoTemplate.aggregate(aggregation, "vehicles", Document::class.java)

        // brake 데이터를 새로운 경로에 복사
        val setUpdate = Update().set(
            "chassis.axle.row2.wheel.left.brake",
            result.mappedResults.firstOrNull()?.get("brake")
        )

        // 기존 경로에서 brake 데이터 삭제
        val unsetUpdate = Update().unset("chassis.axle.row2.wheel.right.brake")

        // 데이터를 이동시키기 위해 순차적으로 업데이트 수행
        mongoTemplate.updateFirst(query, setUpdate, "vehicles")
        mongoTemplate.updateFirst(query, unsetUpdate, "vehicles")
    }

    @Test
    fun `brake 데이터를 right에서 left로 이동2`() {
        val query = Query(Criteria.where("_id").`is`("vehicle_test4"))

        // brake 데이터를 left 경로로 복사
        val setUpdate = Update().set(
            "chassis.axle.row2.wheel.left.brake",
            mongoTemplate.findOne(query, Document::class.java, "vehicles")
                ?.getEmbedded(listOf("chassis", "axle", "row2", "wheel", "right", "brake"), Document::class.java)
        )

        // 기존 경로의 brake 데이터를 제거
        val unsetUpdate = Update().unset("chassis.axle.row2.wheel.right.brake")

        // 데이터를 이동시키기 위해 순차적으로 업데이트 수행
        mongoTemplate.updateFirst(query, setUpdate, "vehicles")
        mongoTemplate.updateFirst(query, unsetUpdate, "vehicles")
    }


}
