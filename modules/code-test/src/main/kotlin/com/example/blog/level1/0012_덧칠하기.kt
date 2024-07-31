package com.example.blog.level1

fun main(args: Array<String>) {
    val n: Int = 10
    val m: Int = 3
    val section: IntArray = intArrayOf(1, 3, 6, 7)
    val result = Solution12().solution(n, m, section)

    println("result : $result")

//    result.forEach { println(it) }
}

private class Solution12 {

    fun solution(n: Int, m: Int, section: IntArray): Int {
        var answer: Int = 0
        var startLen = section[0]
        var pCnt = 0

        section.forEachIndexed { i, it ->
            if (it > startLen + m - 1) {
                startLen = it
                pCnt += 1
            }

            if (i == section.size - 1 && it <= startLen + m - 1) {
                pCnt += 1
            }
        }

        answer = pCnt

        return answer
    }
}