package com.masahirosaito.chatwork4j.data

/**
 * Created by masahiro on 2017/01/19.
 */
data class Message(
        val message_id: Int,
        val account: Account,
        val body: String,
        val send_time: Long,
        val update_time: Long
)