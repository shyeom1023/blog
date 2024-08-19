package com.example.blog.codeBility.lesson2

fun main(args: Array<String>) {
    val result = solution(intArrayOf(9, 3, 9, 3, 9, 7, 9))
    println(result)
}

fun solution(A: IntArray): Int {
    // Implement your solution here

    val map = mutableMapOf<Int, Int>()

    A.forEachIndexed { i, it ->
        if (map.get(it) != null) {
            map.remove(it)
        } else {
            map.put(it, i)
        }
    }

    return map.keys.maxOrNull() ?: 0
}