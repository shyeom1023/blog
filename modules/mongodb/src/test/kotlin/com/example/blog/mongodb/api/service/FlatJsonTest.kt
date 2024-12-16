package com.example.blog.mongodb.api.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.bson.Document
import org.bson.json.JsonParseException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class FlatJsonTest {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    fun sampleJson(): String {
        // JSON 문자열을 Document로 변환
        val json = """
            {
                "_id": "vehicle_test5",
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

        return json
    }

    fun flattenJson(
        node: JsonNode,
        parentKey: String = "",
        result: MutableMap<String, Any> = mutableMapOf()
    ): Map<String, Any> {
        for ((key, value) in node.fields()) {
            val newKey = if (parentKey.isEmpty()) key else "${parentKey}-${key}"
            when {
                value.isObject -> flattenJson(value, newKey, result) // 중첩된 객체를 재귀적으로 처리
                value.isValueNode -> result[newKey] = value.asText() // 값인 경우 결과에 추가
            }
        }
        return result
    }

    fun unflattenJson(flattenedMap: Map<String, Any>): JsonNode {
        val mapper = ObjectMapper()
        val rootNode = mapper.createObjectNode()

        for ((key, value) in flattenedMap) {
            var currentNode = rootNode
            val keys = key.split("-")

            for (i in keys.indices) {
                val part = keys[i]
                if (i == keys.size - 1) {
                    // 마지막 키에 값 설정
                    when (value) {
                        is String -> currentNode.put(part, value)
                        is Int -> currentNode.put(part, value)
                        is Boolean -> currentNode.put(part, value)
                        else -> currentNode.put(part, value.toString())
                    }
                } else {
                    // 중간 노드 생성 또는 이동
                    if (!currentNode.has(part)) {
                        currentNode.set<ObjectNode>(part, mapper.createObjectNode())
                    }
                    currentNode = currentNode.get(part) as ObjectNode
                }
            }
        }

        return rootNode
    }


    @Test
    fun `json to flat Test`() {
        val json = sampleJson()

        val mapper = ObjectMapper()
        val rootNode = mapper.readTree(json)

        val flattenedJson = flattenJson(rootNode)

        // Map을 JSON 형식으로 변환
        val flattenedJsonAsString = mapper.writeValueAsString(flattenedJson)

        // JSON 형태로 출력
        println(flattenedJsonAsString)
    }

    @Test
    fun `flatten and unflatten test`() {
        val json = sampleJson()

        val mapper = ObjectMapper()
        val rootNode = mapper.readTree(json)

        // JSON을 플랫 구조로 변환
        val flattenedJson = flattenJson(rootNode)
        println("Flattened JSON: ${mapper.writeValueAsString(flattenedJson)}")

        // 플랫 구조를 다시 중첩된 JSON으로 복원
        val unflattenedJson = unflattenJson(flattenedJson)
        println("Unflattened JSON: ${mapper.writerWithDefaultPrettyPrinter().writeValueAsString(unflattenedJson)}")
    }

    @Test
    fun `flat data mongo save test`() {
        val json = sampleJson()

        val mapper = ObjectMapper()
        val rootNode = mapper.readTree(json)

        val flattenedJson = flattenJson(rootNode)

        try {
            val flattenedJsonAsString = mapper.writeValueAsString(flattenedJson)
            val document = Document.parse(flattenedJsonAsString)
            mongoTemplate.save(document, "vehicles")
        } catch (e: JsonParseException) {
            println("Failed to parse JSON: ${e.message}")
        }
    }


}
