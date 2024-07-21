package com.example.blog.level1

fun main(args: Array<String>) {
    val food: IntArray = intArrayOf(1, 3, 4, 6)
    val result = Solution7().solution(food)

    println("result : $result")

//    result.forEach { println(it) }
}

private class Solution7 {

    fun solution(food: IntArray): String {
        var answer: String = ""

        val result = mutableListOf<Int>()
        for (i in 1..food.size) {
            if (food.size == i) {
                result.add(0)
            } else {
                val loop = food[i] / 2
                for (j in 0 until loop) {
                    result.add(i)
                }
            }
        }

        for (i in result.size - 2 downTo 0) {
            result.add(result[i])
        }

        answer = result.joinToString("", "")
        return answer
    }
}