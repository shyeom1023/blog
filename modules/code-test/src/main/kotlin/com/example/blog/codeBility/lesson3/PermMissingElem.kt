package com.example.blog.codeBility.lesson3

class PermMissingElem {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = solution(intArrayOf(1))
            println(result)
        }

        fun solution(A: IntArray): Int {
            // Implement your solution here

            var unpaired = 0

            val list = A.toMutableList()
            list.add(0)

            list.forEachIndexed { i, it ->
                unpaired = unpaired xor it xor i + 1
            }

            return unpaired
        }

    }
}