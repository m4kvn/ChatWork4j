package com.masahirosaito.chatwork4j.data.my

/**
 * AssignedByAccount
 *
 * @property account_id アカウントID
 * @property name アカウント名
 * @property avatar_image_url アカウントアバター画像URL
 */
data class AssignedByAccount(
        val account_id: Int,
        val name: String,
        val avatar_image_url: String
)