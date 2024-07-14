package com.example.blog.level1


fun main(args: Array<String>) {
    val nums = arrayOf(
        intArrayOf(24, 10),
        intArrayOf(28, 39),
        intArrayOf(43, 20),
        intArrayOf(37, 5),
        intArrayOf(47, 22),
        intArrayOf(20, 47),
        intArrayOf(15, 34),
        intArrayOf(15, 2),
        intArrayOf(35, 43),
        intArrayOf(26, 1)
    )
    val result = Solution2().solution(nums)
    println("result: $result")
}

private class Solution2 {

    // 요청 시간으로 1차 정렬
    // 첫번째 요청 시간 기준으로 작업 시간
    fun solution(jobs: Array<IntArray>): Int {
        var answer = 0

        val mutableJobs = jobs.sortedWith(compareBy({ it[0] }, { it[1] })).toMutableList()

        val jobsSize = 500
        val firstJob = mutableJobs.first()
        var totalJobSecond = firstJob.sum()

        mutableJobs.remove(firstJob)

        val resultList: MutableList<Int> = mutableListOf()
        resultList.add(totalJobSecond - firstJob[0])

        for (i in 0..jobsSize) {

            val findJob = findJob(mutableJobs, totalJobSecond) ?: break
            if (totalJobSecond < findJob[0]) totalJobSecond = findJob[0]
            totalJobSecond += findJob[1]
            mutableJobs.remove(findJob)
            resultList.add(totalJobSecond - findJob[0])
        }

        answer = resultList.average().toInt()

        return answer
    }

    fun findJob(jobs: MutableList<IntArray>, totalJobSecond: Int): IntArray? {
        return jobs.filter { it[0] < totalJobSecond }.minByOrNull { it[1] } ?: jobs.minByOrNull { it[0] }
    }
}

class `0002_디스크 컨트롤러` {
}