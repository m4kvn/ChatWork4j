package com.masahirosaito.chatwork4j.data.me

/**
 * Meデータクラス
 *
 * @property account_id アカウントID
 * @property room_id ルームID
 * @property name 名前
 * @property chatwork_id チャットーワークID
 * @property organization_id 組織ID
 * @property organization_name 組織名
 * @property department 部署
 * @property title タイトル
 * @property url URL
 * @property introduction 自己紹介
 * @property mail メールアドレス
 * @property tel_organization 組織電話番号
 * @property tel_extension 内線電話番号
 * @property tel_mobile 携帯電話番号
 * @property skype SkypeID
 * @property facebook FacebookID
 * @property twitter TwitterID
 * @property avatar_image_url アバター画像URL
 */
data class Me(
        val account_id: Int,
        val room_id: Int,
        val name: String,
        val chatwork_id: String,
        val organization_id: Int,
        val organization_name: String,
        val department: String,
        val title: String,
        val url: String,
        val introduction: String,
        val mail: String,
        val tel_organization: String,
        val tel_extension: String,
        val tel_mobile: String,
        val skype: String,
        val facebook: String,
        val twitter: String,
        val avatar_image_url: String
)