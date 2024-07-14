package com.example.blog.level1

fun main(args: Array<String>) {
    val nums = intArrayOf(1, 2, 3, 4)
    val result = Solution1().solution(nums)
    println("result: $result")
}

/**
 *
 * - nums에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
 * - nums의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.
 */
class Solution1 {
    fun solution(nums: IntArray): Int {
        var answer = -1

        val numsSize = nums.size - 1
        val sumNumbers: MutableList<Int> = mutableListOf()

        for (i in 0..numsSize) {
            for (j in i + 1..numsSize) {
                for (k in j + 1..numsSize) {
                    val sum = nums[i] + nums[j] + nums[k]
                    sumNumbers.add(sum)
                }
            }
        }

        val result: MutableList<Int> = mutableListOf()

        sumNumbers.forEach {
            var count = 0
            for (i in 1..it) {
                if (it % i == 0) {
                    count++
                }
            }
            if (count == 2)
                result.add(it)
        }

        answer = result.size

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        println(answer)

        return answer
    }
}