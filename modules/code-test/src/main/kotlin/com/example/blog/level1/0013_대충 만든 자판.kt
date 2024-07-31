package com.example.blog.level1

fun main(args: Array<String>) {
    val keymap: Array<String> = arrayOf("AA")
    val targets: Array<String> = arrayOf("B")
    val result = Solution13().solution(keymap, targets)

//    println("result : $result")

    result.forEach { println(it) }
}

private class Solution13 {

    fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()

        val map = mutableMapOf<String, Int>()
        keymap.forEach { key ->
            key.forEachIndexed { index, c ->
                if (map[c.toString()] == null) {
                    map[c.toString()] = index + 1
                } else {
                    if (map[c.toString()]!! > index) {
                        map[c.toString()] = index + 1
                    }
                }
            }
        }

        val result = mutableListOf<Int>()

        targets.forEach {
            var sum: Int = 0
            for (c in it) {
                if (map[c.toString()] == null) {
                    sum = -1
                    break
                } else {
                    sum += map[c.toString()]!!
                }
            }
            result.add(sum)
        }

        answer = result.toIntArray()

        return answer
    }
}