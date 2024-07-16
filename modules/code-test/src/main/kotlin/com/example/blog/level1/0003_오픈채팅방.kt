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
        val uidList = mutableSetOf<String>()

        // uid 추출
        record.forEach {
            val items = it.split(" ")
            uidList.add(items[1])
        }

        val uidMap = mutableMapOf<String, String>()

        // uid 맵핑된 이름 정보 추출
        uidList.forEach { uid ->
            val lastItem =
                record.lastOrNull { it.contains(uid) && (it.contains("Enter") || it.contains("Change")) }

            lastItem?.let {
                val item = it.split(" ")
                uidMap[uid] = item[2]
            }
        }

        val result = mutableListOf<String>()

        record.forEachIndexed { index, s ->
            uidMap.forEach { (k, v) ->
                if (s.contains(k) && s.contains("Enter")) {
                    result.add(v + "님이 들어왔습니다.")
                } else if (s.contains(k) && s.contains("Leave")) {
                    result.add(v + "님이 나갔습니다.")
                }
            }
        }

        answer = result.toTypedArray()

        return answer
    }
}