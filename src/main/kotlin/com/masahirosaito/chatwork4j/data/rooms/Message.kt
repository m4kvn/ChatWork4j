package com.masahirosaito.chatwork4j.data.rooms

/**
 * Messageデータクラス
 *
 * @property message_id メッセージID
 * @property account アカウント
 * @property body メッセージ
 * @property send_time 送信時間
 * @property update_time 更新時間
 */
data class Message(
        val message_id: Int,
        val account: Account,
        val body: String,
        val send_time: Long,
        val update_time: Long
)