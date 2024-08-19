package com.example.blog.lesson1

class BinaryGap {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = BinaryGap().solution(10)
            println(solution)
        }
    }

    fun solution(N: Int): Int {
        // Implement your solution here

        var isSum = false
        var tempSum = 0
        var maxSum = 0

        val binary = N.toString(2)

        println(binary)

        binary.forEachIndexed { index, it ->
            if ('1'.equals(it) && !isSum) {
                isSum = true
            } else if ('1'.equals(it) && isSum) {
                if (tempSum > maxSum) {
                    maxSum = tempSum - 1
                }
                tempSum = 0
            }

            if (isSum) {
                tempSum += 1
            }
        }

        return maxSum
    }
}