package com.masahirosaito.chatwork4j.data.my

/**
 * Statusデータクラス
 *
 * @property unread_room_num 自分の未読メッセージがあるルーム数
 * @property mention_room_num 自分宛ての未読メッセージがあるルーム数
 * @property mytask_room_num 自分の未完了タスクがあるルーム数
 * @property unread_num　自分の未読メッセージ数
 * @property mention_num 自分宛ての未読メッセージ数
 * @property mytask_num 自分の未完了タスク数
 */
data class Status(
        val unread_room_num: Int,
        val mention_room_num: Int,
        val mytask_room_num: Int,
        val unread_num: Int,
        val mention_num: Int,
        val mytask_num: Int
)