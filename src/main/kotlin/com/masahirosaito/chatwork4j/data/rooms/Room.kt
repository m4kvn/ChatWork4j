package com.masahirosaito.chatwork4j.data.rooms

import com.masahirosaito.chatwork4j.ChatWork4j
import com.masahirosaito.chatwork4j.ChatWork4j.Companion.getObjectFromJson
import com.masahirosaito.chatwork4j.ChatWork4j.Companion.post
import com.masahirosaito.chatwork4j.ChatWork4j.Companion.put
import com.masahirosaito.chatwork4j.data.my.Task
import okhttp3.FormBody

/**
 * Created by masahiro on 2017/01/19.
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
) {

    /**
     * チャットルームのアクションタイプ
     */
    enum class ActionType { leave, delete }

    /**
     * チャットルームの削除/退席
     *
     * @param action_type 削除か退席の種類
     * @return レスポンスのJSON文字列
     */
    fun deleteRoom(action_type: ActionType): String {
        val body = FormBody.Builder()
                .add("action_type", action_type.name)
                .build()
        return ChatWork4j.delete("/rooms/$room_id", body)
    }

    /**
     * チャットルームのメンバー一覧を取得
     * @return チャットルームのメンバー一覧
     */
    fun getMembers(): Array<Member>? = getObjectFromJson("/rooms/$room_id/members", Array<Member>::class.java)

    /**
     * チャットルームのメンバーを更新
     *
     * @param members_admin_ids 管理者権限のユーザー(必須)
     * @param members_member_ids メンバー権限のユーザー
     * @param members_readonly_ids 閲覧のみ権限のユーザー
     * @return レスポンスのJSON文字列
     */
    fun putMembers(members_admin_ids: Array<Int>,
                   members_member_ids: Array<Int>? = null,
                   members_readonly_ids: Array<Int>? = null): String {

        val body = FormBody.Builder().apply {

            if (members_member_ids != null) add("members_member_ids", buildString {
                members_member_ids.forEach { append(",").append(it) }
            }.replaceFirst(",", ""))

            if (members_readonly_ids != null) add("members_readonly_ids", buildString {
                members_readonly_ids.forEach { append(",").append(it) }
            }.replaceFirst(",", ""))

            add("members_admin_ids", buildString {
                members_admin_ids.forEach { append(",").append(it) }
            }.replaceFirst(",", ""))
        }.build()

        return put(url = "/rooms/$room_id/members", body = body)
    }

    /**
     * チャットルームのメッセージ100件を取得
     *
     * @param force falseで未取得メッセージのみ取得
     * @return 取得したメッセージ一覧
     */
    fun getMessages(force: Boolean = false): Array<Message>? {
        val url = buildString {
            append("/rooms/$room_id/messages?force=")
            if (force) append("1") else append("0")
        }
        return getObjectFromJson(url, Array<Message>::class.java)
    }

    /**
     * チャットルームにメッセージを送信
     *
     * @param message 送信するメッセージ
     * @return レスポンスのJSON文字列
     */
    fun postMessage(message: String): String = post(
            "/rooms/$room_id/messages",
            FormBody.Builder().add("body", message).build()
    )

    /**
     * チャットルームのメッセージを取得
     *
     * @param messageId メッセージID
     * @return 取得したメッセージ
     */
    fun getMessage(messageId: Int): Message? =
            getObjectFromJson("/rooms/$room_id/messages/$messageId", Message::class.java)

    /**
     * チャットルームのタスクを取得
     *
     * @param taskId タスクID
     * @return タスク
     */
    fun getTask(taskId: Int): Task? = getObjectFromJson("/rooms/$room_id/tasks/$taskId", Task::class.java)

    /**
     * チャットルームのタスク一覧を取得
     *
     * @param account_id タスクを受け取ったアカウントID
     * @param assigned_by_account_id タスクを作成したアカウントID
     * @param status タスクの状態
     * @return タスク一覧
     */
    fun getTasks(account_id: Int? = null,
                 assigned_by_account_id: Int? = null,
                 status: Task.Status? = null): Array<Task>? {

        val url = buildString {
            append("/rooms/$room_id/tasks")
            if (account_id != null || assigned_by_account_id != null || status != null) {
                append("?")
                if (account_id != null) append("&account_id=$account_id")
                if (assigned_by_account_id != null) append("&assigned_by_account_id=$assigned_by_account_id")
                if (status != null) append("&status=${status.name.toLowerCase()}")
            }
        }

        return getObjectFromJson(url, Array<Task>::class.java)
    }

    /**
     * チャットルームのファイル一覧を取得
     *
     * @param accountId ファイルをアップロードしたアカウントID
     * @return ファイル一覧
     */
    fun getFiles(accountId: Int? = null): Array<File>? {
        val url = buildString {
            append("/rooms/$room_id/files")
            if (accountId != null) append("?account_id=$accountId")
        }
        return getObjectFromJson(url, Array<File>::class.java)
    }

    /**
     * チャットルームのファイルを取得
     *
     * @param fileId ファイルID
     * @param createDownloadUrl trueでダウンロードURLを作成
     */
    fun getFile(fileId: Int, createDownloadUrl: Boolean = false): File? {
        val url = buildString {
            append("/rooms/$room_id/files/$fileId")
            if (createDownloadUrl) append("?create_download_url=1")
        }
        return getObjectFromJson(url, File::class.java)
    }
}