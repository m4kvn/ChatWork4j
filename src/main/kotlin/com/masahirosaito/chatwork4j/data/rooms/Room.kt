package com.masahirosaito.chatwork4j.data.rooms

/**
 * Roomデータクラス
 *
 * @property room_id ルームID
 * @property name 名前
 * @property type タイプ
 * @property role 役割
 * @property sticky ピン留め
 * @property unread_num 未読メッセージ数
 * @property mention_num 自分宛てメッセージ数
 * @property mytask_num 自分のタスク数
 * @property message_num メッセージ数
 * @property file_num ファイル数
 * @property task_num タスク数
 * @property icon_path アイコン画像URL
 * @property last_update_time 最後の更新時間
 * @property description 概要
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