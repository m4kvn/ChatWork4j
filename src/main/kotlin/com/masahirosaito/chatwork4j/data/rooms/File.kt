package com.masahirosaito.chatwork4j.data.rooms

/**
 * Fileデータクラス
 *
 * @property file_id ファイルID
 * @property account アカウント
 * @property message_id メッセージID
 * @property filename ファイル名
 * @property filesize ファイルサイズ
 * @property upload_time アップロード時間
 * @property download_url ダウンロードURL
 */
data class File(
        val file_id: Int,
        val account: Account,
        val message_id: Int,
        val filename: String,
        val filesize: Int,
        val upload_time: Long,
        val download_url: String
)