package com.example.blog.level1

fun main(args: Array<String>) {
    val wallpaper: Array<String> =
        arrayOf("..........", ".....#....", "......##..", "...##.....", "....#.....")
    val result = Solution11().solution(wallpaper)

//    println("result : $result")

    result.forEach { println(it) }
}

private class Solution11 {

    fun solution(wallpaper: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        var min = 0
        var max = 0
        var minIndex = 0
        var maxIndex = 0
        var isFirst = false

        wallpaper.forEachIndexed { i, s ->
            s.forEachIndexed { ix, c ->
                if (c == '#') {
                    if (!isFirst) {
                        minIndex = i
                        maxIndex = i
                        min = ix
                        max = ix
                        isFirst = true
                    } else {
                        maxIndex = i
                        if (min >= ix) {
                            min = ix
                        }

                        if (max <= ix) {
                            max = ix
                        }
                    }
                }
            }
        }

//        println("${minIndex},${min},${maxIndex + 1},${max + 1}")

        answer = intArrayOf(minIndex, min, maxIndex + 1, max + 1)


        return answer
    }
}