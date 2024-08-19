package com.example.blog.codeBility.lesson4

class PermCheck {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = solution2(intArrayOf(4, 1, 3,2))
            println(result)
        }

        fun solution(A: IntArray): Int {
            // Implement your solution here

            val set = mutableSetOf<Int>()
            A.forEach {
                if (A.size >= it) {
                    set.add(it)
                } else {
                    return 0
                }
            }
            if (set.size == A.size) {
                return 1
            } else {
                return 0
            }
        }


        fun solution2(A: IntArray): Int {
            val n = A.size
            val seen = BooleanArray(n + 1) // 숫자를 체크할 배열 생성

            for (num in A) {
                if (num in 1..n && !seen[num]) {
                    seen[num] = true
                } else {
                    return 0 // 범위 밖이거나 중복이 있는 경우 0 반환
                }
            }

            return 1 // 모든 숫자가 정확히 한 번씩 등장했다면 1 반환
        }
    }
}