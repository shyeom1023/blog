package com.example.blog.level1


fun main(args: Array<String>) {
    val record = arrayOf(
        "Enter uid1234 Muzi",
        "Enter uid4567 Prodo",
        "Leave uid1234",
        "Enter uid1234 Prodo",
        "Change uid4567 Ryan"
    )
    val result = Solution3().solution(record)

    result.forEach {
        println(it)
    }
}

private class Solution3 {

    fun solution(record: Array<String>): Array<String> {
        var answer = arrayOf<String>()

        val uidMap = mutableMapOf<String, String>()

        // uid 맵핑된 이름 정보 추출
        record.forEach {
            val item = it.split(" ")
            if (item[0].contains("Enter") || item[0].contains("Change")) {
                uidMap[item[1]] = item[2]
            }
        }

        val result = mutableListOf<String>()

        record.forEach {
            val item = it.split(" ")
            if (item[0].contains("Enter")) {
                result.add(uidMap[item[1]] + "님이 들어왔습니다.")
            } else if (item[0].contains("Leave")) {
                result.add(uidMap[item[1]] + "님이 나갔습니다.")
            }
        }

        answer = result.toTypedArray()

        return answer
    }
}