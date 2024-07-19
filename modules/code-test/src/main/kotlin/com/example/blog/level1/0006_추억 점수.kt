package com.example.blog.level1

fun main(args: Array<String>) {
    val name: Array<String> = arrayOf("may", "kein", "kain", "radi")
    val yearning: IntArray = intArrayOf(5, 10, 1, 3)
    val photo: Array<Array<String>> = arrayOf(
        arrayOf("may", "kein", "kain", "radi"),
        arrayOf("may", "kein", "brin", "deny"),
        arrayOf("kon", "kain", "may", "coni")
    )
    val result = Solution6().solution(name, yearning, photo)

//    println("result : $result")

    result.forEach { println(it) }
}

private class Solution6 {

    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        var answer: IntArray = intArrayOf()

        val map = mutableMapOf<String, Int>()
        for (i in 0..name.size - 1) {
            map[name[i]] = yearning[i]
        }

        val result = mutableListOf<Int>()

        photo.forEach { list ->
            var sum = 0
            list.forEach {
                val yearn = map[it] ?: 0
                sum += yearn
            }
            result.add(sum)
        }

        answer = result.toIntArray()

        return answer
    }
}