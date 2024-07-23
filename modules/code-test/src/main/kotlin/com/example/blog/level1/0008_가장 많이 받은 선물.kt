package com.example.blog.level1

fun main(args: Array<String>) {
    val friends: Array<String> = arrayOf("a", "b", "c")
    val gifts: Array<String> = arrayOf(
        "a b", "b a", "c a", "a c", "a c", "c a"
    )
    val result = Solution8().solution(friends, gifts)

    println("result : $result")

//    result.forEach { //println(it) }
}

private class Solution8 {

    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        var answer: Int = 0

        val map: MutableMap<String, MutableMap<String, Int>> = mutableMapOf()
        friends.forEach { f ->
            val pMap = mutableMapOf<String, Int>()
            val rMap = mutableMapOf<String, Int>()
            //println("===========${f}=========")
            gifts.forEach { g ->
                val item = g.split(" ")
                if (item[0] == f) {
                    // 준 선물
                    val p = map[f]?.get(item[1]) ?: 0
                    if (map[f] == null) {
                        pMap[item[1]] = p + 1
                        map[f] = pMap
                    } else {
                        map[f]!![item[1]] = p + 1
                    }
                    //println("item[0] ${f} : ${map[f]}")
                }
                if (item[1] == f) {
                    // 받은 선물
                    val r = map[f]?.get(f) ?: 0

                    if (map[f] == null) {
                        rMap[f] = r + 1
                        map[f] = rMap
                    } else {
                        map[f]!![f] = r + 1
                    }
                    //println("item[1] ${f} : ${map[f]}")
                }
            }
        }

//        map.forEach { (key, value) ->
//            var data = ""
//            value.forEach { (key2, value2) ->
//                data += "\t${key2}:${value2}";
//            }
//            //println("${key}: ${data}")
//        }

        val result = mutableMapOf<String, Int>()

        friends.forEach { m ->
            //println("===========${m}=========")
            friends.forEach { y ->
                if (m == y) return@forEach
                val me = map[m]?.get(y) ?: 0
                val meSum = (map[m]?.filter { it.key !== m }?.values?.sum()
                    ?: 0) - (map[m]?.filter { it.key == m }?.values?.sum() ?: 0)
                val you = map[y]?.get(m) ?: 0
                val youSum = (map[y]?.filter { it.key !== y }?.values?.sum()
                    ?: 0) - (map[y]?.filter { it.key == y }?.values?.sum() ?: 0)

                // 선물을 서로 줬는지
                if (me > 0 && you > 0 && me > you) {
                    //println("1.${y}")
                    result[m] = (result[m] ?: 0) + 1
                }

                // 선물을 나만 줬는지
                if (me > 0 && you == 0) {
                    //println("2.${y}")
                    result[m] = (result[m] ?: 0) + 1
                }

                // 선물을 서로 안줬는지
                if (me == you && meSum > youSum) {
                    //println("3.${y}")
                    result[m] = (result[m] ?: 0) + 1
                }
            }
        }
        //println(result)

        answer = result.values.maxOfOrNull { it } ?: 0

        return answer
    }
}