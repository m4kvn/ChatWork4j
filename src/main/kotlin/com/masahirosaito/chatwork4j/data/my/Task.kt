package com.masahirosaito.chatwork4j.data.my

/**
 * Created by masahiro on 2017/01/19.
 */
data class Task(
        val task_id: Int,
        val room: TaskRoom,
        val assigned_by_account: AssignedByAccount,
        val message_id: Int,
        val body: String,
        val limit_time: Long,
        val status: String
)