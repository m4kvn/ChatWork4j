package com.masahirosaito.chatwork4j.data.my

/**
 * Created by masahiro on 2017/01/19.
 */
data class Status(
        val unread_room_num: Int,
        val mention_room_num: Int,
        val mytask_room_num: Int,
        val unread_num: Int,
        val mention_num: Int,
        val mytask_num: Int
)