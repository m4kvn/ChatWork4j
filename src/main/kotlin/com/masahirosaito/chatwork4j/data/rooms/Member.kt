package com.masahirosaito.chatwork4j.data.rooms

/**
 * Memberデータクラス
 *
 * @property account_id アカウントID
 * @property role 役割名
 * @property name 名前
 * @property chatwork_id チャットワークID
 * @property organization_id 組織ID
 * @property organization_name 組織名
 * @property department 部署名
 * @property avatar_image_url アバター画像URL
 */
data class Member(
        val account_id: Int,
        val role: String,
        val name: String,
        val chatwork_id: String,
        val organization_id: Int,
        val organization_name: String,
        val department: String,
        val avatar_image_url: String
)