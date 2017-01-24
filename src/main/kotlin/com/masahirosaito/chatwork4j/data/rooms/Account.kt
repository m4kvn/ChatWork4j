package com.masahirosaito.chatwork4j.data.rooms

/**
 * Accountデータクラス
 *
 * @property account_id アカウントID
 * @property name 名前
 * @property avatar_image_url アバター画像URL
 */
data class Account(
        val account_id: Int,
        val name: String,
        val avatar_image_url: String
)