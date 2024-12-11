package com.example.blog.mqtt.api

import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@RestController
class ProxyController {

    @GetMapping("/proxy")
    fun proxy(): String {
        val rest = RestTemplate()
        return rest.getForObject("http://localhost:3000/d-solo/edojuf6gzhw5cf/ec98a8-eb8f84-ec84bc-ec849c?orgId=1&refresh=5s&from=1688780718165&to=1720403118165&panelId=5")
    }
}


data class UrlRequest(
    val url: String
)
