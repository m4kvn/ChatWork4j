package com.masahirosaito.chatwork4j.data.my

/**
 * Taskデータクラス
 *
 * @property task_id タスクID
 * @property room ルーム
 * @property assigned_by_account タスクを投げたアカウント
 * @property message_id メッセージID
 * @property body メッセージ本文
 * @property limit_time タスク期日
 * @property status タスク状態
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