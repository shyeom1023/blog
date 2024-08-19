package com.example.blog.codeBility.lesson3

fun main(args: Array<String>) {
    val solution = solution(10, 85, 30)
    println(solution)
}

fun solution(X: Int, Y: Int, D: Int): Int {
    // Implement your solution here
    val diff = Y - X
    return if (diff % D == 0) diff / D else (diff / D) + 1
}