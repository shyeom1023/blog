package com.example.blog.codeBility.lesson2

class CyclicRotation {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = CyclicRotation().solution(intArrayOf(), 3)
            solution.forEach { println(it) }
        }
    }

    fun solution(A: IntArray, K: Int): IntArray {
        // Implement your solution here

        if(A.isEmpty()) return A

        val circle = if (A.size <= K) K % A.size else K
        if (circle == 0) return A

        val result = IntArray(A.size)
        var aIndex = A.size - circle

        for (i in 0 until A.size) {
            if (aIndex == A.size) {
                aIndex = 0
            }
            result[i] = A.get(aIndex)
            aIndex++
        }

        return result
    }
}