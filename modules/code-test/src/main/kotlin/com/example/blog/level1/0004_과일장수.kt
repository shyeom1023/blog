package com.example.blog.level1


fun main(args: Array<String>) {
    val k = 4
    val m = 3
    val score = intArrayOf(4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2)
    val result = Solution4().solution(k, m, score)

    println("result : $result")
}

private class Solution4 {

    fun solution(k: Int, m: Int, score: IntArray): Int {
        var answer: Int = 0

        val sortedScore = score.sortedByDescending { it }
        val boxNum = (sortedScore.size / m)

        var result = 0

        for (i in 0 until boxNum) {
            var minScore: Int = k
            for (j in 0 until m) {
                val item = sortedScore[(j) + (i * m)]
                minScore = item.coerceAtMost(minScore)
            }
            result += minScore * m
        }

        answer = result

        return answer
    }
}