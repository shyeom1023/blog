package com.example.blog.codeBility.lesson4

class FrogRiverOne {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = solution(5, intArrayOf(1,3,1,4,2,3,5,4))
            println(result)
        }

        fun solution(X: Int, A: IntArray): Int {
            // Implement your solution here

            val set = mutableSetOf<Int>()


            A.forEachIndexed { index, it ->
                if (X >= it) {
                    set.add(it)
                }

                if (set.size == X) {
                    return index
                }
            }
            return -1
        }

    }
}