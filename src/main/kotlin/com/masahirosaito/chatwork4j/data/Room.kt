package com.masahirosaito.chatwork4j.data

/**
* Created by masahiro on 2017/01/19.
*/
data class Room(
        val room_id: Int,
        val name: String,
        val type: String,
        val role: String,
        val sticky: Boolean,
        val unread_num: Int,
        val mention_num: Int,
        val mytask_num: Int,
        val message_num: Int,
        val file_num: Int,
        val task_num: Int,
        val icon_path: String,
        val last_update_time: Long,
        val description: String
)