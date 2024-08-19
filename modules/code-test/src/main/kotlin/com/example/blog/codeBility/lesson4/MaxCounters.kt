package com.example.blog.codeBility.lesson4

class MaxCounters {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = solution(5, intArrayOf(3, 4, 4, 6, 1, 4, 4))
            result.forEach { println(it) }
        }

        fun solution(N: Int, A: IntArray): IntArray {
            val counter = IntArray(N)
            var maxCounter = 0
            var lastUpdate = 0

            A.forEach {
                if (N + 1 == it) {
                    lastUpdate = maxCounter
                } else {
                    val index = it - 1
                    if (counter[index] < lastUpdate) {
                        counter[index] = lastUpdate
                    }

                    counter[index]++

                    if (counter[index] > maxCounter) {
                        maxCounter = counter[index]
                    }
                }
            }

            counter.forEachIndexed { index, it ->
                if (lastUpdate > it) counter[index] = lastUpdate
            }

            return counter
        }

        fun solution2(N: Int, A: IntArray): IntArray {
            // Implement your solution here

            var map = generateSequence(1) { it + 1 }
                .take(N)
                .associateWith { 0 }.toMutableMap()

            A.forEach {
                if (N + 1 == it) {
                    val max = map.values.maxOf { it }
                    map = generateSequence(1) { m -> m + 1 }
                        .take(N)
                        .associateWith { max }.toMutableMap()
                } else {
                    map[it] = map[it]!! + 1
                }
            }

            return map.values.map { it }.toIntArray()
        }
    }
}