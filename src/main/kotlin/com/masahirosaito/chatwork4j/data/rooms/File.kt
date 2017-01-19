package com.masahirosaito.chatwork4j.data.rooms

/**
 * Created by masahiro on 2017/01/19.
 */
data class File(
        val file_id: Int,
        val account: Account,
        val message_id: Int,
        val filename: String,
        val filesize: Int,
        val upload_time: Long
)