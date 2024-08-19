package com.example.blog.codeBility.lesson3

import kotlin.math.abs

class TapeEquilibrium {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = solution(
                intArrayOf(1000, 1, 1, 1, 1000)

            )
            println("result: $result")
        }

        fun solution(A: IntArray): Int {
            // Implement your solution here
            if (A.isEmpty()) return 0

            val sum = A.sum()
            var b = 0
            var a = 0
            var min = Int.MAX_VALUE

            for (i in 0 until A.size - 1) {
                a += A[i]
                b = sum - a
                val diff = abs(a - b)
//                println(diff)
                if (min > diff) {
                    min = diff
                }
            }

            return min
        }


    }
}