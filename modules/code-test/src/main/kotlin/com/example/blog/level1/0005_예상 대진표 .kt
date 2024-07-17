package com.example.blog.level1


fun main(args: Array<String>) {
    val n = 8
    val a = 2
    val b = 3
    val result = Solution5().solution(n, a, b)

    println("result : $result")
}

private class Solution5 {

    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 0

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        println("Hello Kotlin")

        val retry = compare(n, a, b, 0)

        answer = retry + 1

        return answer
    }

    fun compare(n: Int, a: Int, b: Int, retry: Int): Int {
        val divide = if (n == 2) n else n / 2

        if ((a < b && a % 2 != 0 && a + 1 == b) || (b < a && b % 2 != 0 && b + 1 == a)) {
            return retry
        } else {
            val divideA = (a / 2) + (a % 2)
            val divideB = b / 2 + (b % 2)
            return compare(divide, divideA, divideB, retry + 1)
        }
    }
}