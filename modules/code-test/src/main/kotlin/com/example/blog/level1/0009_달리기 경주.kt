package com.example.blog.level1

fun main(args: Array<String>) {
    val friends: Array<String> =
        arrayOf("mumu", "soe", "poe", "kai", "mine")
    val gifts: Array<String> =
        arrayOf("kai", "kai", "mine", "mine")
    val result = Solution9().solution(friends, gifts)

//    println("result : $result")

    result.forEach { println(it) }
}

private class Solution9 {

    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        var answer: Array<String> = arrayOf<String>()

        val associate = players.withIndex().associate { it.value to it.index }.toMutableMap()

        callings.forEach {
            val index = associate[it]!!
            val p = players[index - 1]

            associate[p] = index
            associate[it] = index -1

            players[index - 1] = it
            players[index] = p
        }

        answer = players

        return answer
    }
}