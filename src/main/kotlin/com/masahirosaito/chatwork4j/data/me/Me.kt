package com.masahirosaito.chatwork4j.data.me

/**
 * Meデータクラス
 *
 * @param account_id アカウントID
 * @param room_id ルームID
 * @param name 名前
 * @param chatwork_id チャットーワークID
 * @param organization_id 組織ID
 * @param organization_name 組織名
 * @param department 部署
 * @param title タイトル
 * @param url URL
 * @param introduction 自己紹介
 * @param mail メールアドレス
 * @param tel_organization 組織電話番号
 * @param tel_extension 内線電話番号
 * @param tel_mobile 携帯電話番号
 * @param skype SkypeID
 * @param facebook FacebookID
 * @param twitter TwitterID
 * @param avatar_image_url アバター画像URL
 */
data class Me(
        /** アカウントID */
        val account_id: Int,
        /** ルームID */
        val room_id: Int,
        /** 名前 */
        val name: String,
        /** チャットワークID */
        val chatwork_id: String,
        /** 組織ID */
        val organization_id: Int,
        /** 組織名 */
        val organization_name: String,
        /** 部署 */
        val department: String,
        /** タイトル */
        val title: String,
        /** URL */
        val url: String,
        /** 自己紹介 */
        val introduction: String,
        /** メールアドレス */
        val mail: String,
        /** 組織電話番号 */
        val tel_organization: String,
        /** 内線電話番号 */
        val tel_extension: String,
        /** 携帯電話番号 */
        val tel_mobile: String,
        /** SkypeID */
        val skype: String,
        /** FacebookID */
        val facebook: String,
        /** TwitterID */
        val twitter: String,
        /** アバター画像URL */
        val avatar_image_url: String
)