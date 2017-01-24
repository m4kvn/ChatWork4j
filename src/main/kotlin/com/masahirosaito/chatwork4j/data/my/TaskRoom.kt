package com.masahirosaito.chatwork4j.data.my

/**
 * TaskRoomデータクラス
 *
 * @property room_id ルームID
 * @property name 名前
 * @property icon_path アイコン画像URL
 */
data class TaskRoom(
        val room_id: Int,
        val name: String,
        val icon_path: String
)