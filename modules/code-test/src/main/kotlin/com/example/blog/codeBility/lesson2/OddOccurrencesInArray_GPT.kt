package com.example.blog.codeBility.lesson2

class OddOccurrencesInArray_GPT {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = solution(intArrayOf(9, 3, 9, 3, 9, 7, 9))
            println(result)
        }

        fun solution(A: IntArray): Int {
            var unpaired = 0

            for (number in A) {
                unpaired = unpaired xor number
            }

            return unpaired
        }
    }

}
