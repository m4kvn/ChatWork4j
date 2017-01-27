package com.masahirosaito.chatwork4j.data.incomingrequests

/**
 * 自分に対するコンタクト認証依頼データ
 *
 * @property request_id リクエストID
 * @property account_id アカウントID
 * @property message メッセージ
 * @property name 名前
 * @property chatwork_id チャットワークID
 * @property organization_id 組織ID
 * @property organization_name 組織名
 * @property department 部署名
 * @property avatar_image_url アバター画像URL
 * @constructor データクラスを生成
 */
data class IncomingRequest(
        val request_id: Int,
        val account_id: Int,
        val message: String,
        val name: String,
        val chatwork_id: String,
        val organization_id: Int,
        val organization_name: String,
        val department: String,
        val avatar_image_url: String
)