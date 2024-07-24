package com.example.blog.level1

fun main(args: Array<String>) {
    val park: Array<String> =
        arrayOf("SOO", "OXX", "OOO")
    val routes: Array<String> =
        arrayOf("E 2", "S 2", "W 1")
    val result = Solution10().solution(park, routes)

//    println("result : $result")

    result.forEach { println(it) }
}

private class Solution10 {

    fun solution(park: Array<String>, routes: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        val map: MutableList<MutableList<String>> = mutableListOf()
        val point = mutableListOf<Int>()

        park.forEachIndexed { index, s ->
            map.add(s.toCharArray().mapIndexed { i, it ->
                if (it.toString() == "S") {
                    point.add(index)
                    point.add(i)
                }
                it.toString()
            }.toMutableList())
        }

        routes.forEach {
            val item = it.split(" ")
            var y = point[0]
            var x = point[1]
            var excute = true
            for (i in 0 until item[1].toInt()) {
                if (item[0] == "E") x += 1
                if (item[0] == "S") y += 1
                if (item[0] == "W") x -= 1
                if (item[0] == "N") y -= 1

                if (map.getOrNull(y) == null || map[y].getOrNull(x) == null || map[y][x] == "X") {
                    excute = false
                    break
                }
            }
            if (excute) {
                point[0] = y
                point[1] = x
            }
        }

        answer = point.toIntArray()

        return answer
    }
}