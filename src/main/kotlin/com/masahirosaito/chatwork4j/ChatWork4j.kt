package com.masahirosaito.chatwork4j

import com.google.gson.Gson
import com.masahirosaito.chatwork4j.data.MemberResponse
import com.masahirosaito.chatwork4j.data.MessageResponse
import com.masahirosaito.chatwork4j.data.RoomResponse
import com.masahirosaito.chatwork4j.data.me.Me
import com.masahirosaito.chatwork4j.data.contacts.Contact
import com.masahirosaito.chatwork4j.data.my.Status
import com.masahirosaito.chatwork4j.data.my.Task
import com.masahirosaito.chatwork4j.data.my.TaskStatus
import com.masahirosaito.chatwork4j.data.rooms.File
import com.masahirosaito.chatwork4j.data.rooms.Member
import com.masahirosaito.chatwork4j.data.rooms.Message
import com.masahirosaito.chatwork4j.data.rooms.Room
import com.masahirosaito.chatwork4j.error.ResponseErrorsException
import com.masahirosaito.chatwork4j.error.NullOrBlankResponseException
import okhttp3.*

/**
 * ChatWork API を利用するためのクラス
 *
 * @property TOKEN ChatWorkAPI TOKEN
 * @property client HTTP通信用クライアント
 */
class ChatWork4j(private val TOKEN: String) {
    private val client = OkHttpClient()

    /**
     * companion objects
     *
     * @property CHATWORK_API_URL_ROOT ChatWork API エンドポイントのベースURL
     * @property CHATWORK_API_TOKEN_HEADER ChatWork API Header
     */
    companion object {
        val CHATWORK_API_URL_ROOT = "https://api.chatwork.com/v2"
        val CHATWORK_API_TOKEN_HEADER = "X-ChatWorkToken"
    }

    private fun <T> newObjectFromJson(json: String, clazz: Class<T>): T {
        if (json.contains("errors")) throw ResponseErrorsException(json)
        if (json.isNullOrBlank()) throw NullOrBlankResponseException()
        else return Gson().fromJson(json, clazz)
    }

    private fun get(url: String): String {
        val request = Request.Builder()
                .url(CHATWORK_API_URL_ROOT + url)
                .addHeader(CHATWORK_API_TOKEN_HEADER, TOKEN)
                .build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    private fun post(url: String, body: RequestBody): String {
        val request = Request.Builder()
                .url(CHATWORK_API_URL_ROOT + url)
                .addHeader(CHATWORK_API_TOKEN_HEADER, TOKEN)
                .post(body)
                .build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    private fun put(url: String, body: RequestBody): String {
        val request = Request.Builder()
                .url(CHATWORK_API_URL_ROOT + url)
                .addHeader(CHATWORK_API_TOKEN_HEADER, TOKEN)
                .put(body)
                .build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    private fun delete(url: String, body: RequestBody): String {
        val request = Request.Builder()
                .url(CHATWORK_API_URL_ROOT + url)
                .addHeader(CHATWORK_API_TOKEN_HEADER, TOKEN)
                .delete(body)
                .build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    /**
     * 自分の情報を取得
     *
     * @return 自分の情報
     */
    fun getMe(): Me = newObjectFromJson(get("/me"), Me::class.java)

    /**
     * 自分の状態を取得
     *
     * @return 自分の状態
     */
    fun getMyStatus(): Status = newObjectFromJson(get("/my/status"), Status::class.java)

    /**
     * 自分のタスク一覧を取得
     *
     * @return 自分のタスク一覧
     */
    fun getMyTasks(): Array<Task> {
        try {
            return newObjectFromJson(get("/my/tasks"), Array<Task>::class.java)
        } catch(e: NullOrBlankResponseException) {
            return emptyArray()
        }
    }

    /**
     * 自分のコンタクト一覧を取得
     *
     * @return 自分のコンタクト一覧を取得
     */
    fun getContacts(): Array<Contact> {
        try {
            return newObjectFromJson(get("/contacts"), Array<Contact>::class.java)
        } catch(e: NullOrBlankResponseException) {
            return emptyArray()
        }
    }

    /**
     * チャットルーム一覧を取得
     *
     * @return チャットールーム一覧
     */
    fun getRooms(): Array<Room> {
        try {
            return newObjectFromJson(get("/rooms"), Array<Room>::class.java)
        } catch(e: NullOrBlankResponseException) {
            return emptyArray()
        }
    }

    /**
     * チャットルームを取得
     *
     * @param room_id チャットールームID
     * @return チャットルーム
     */
    fun getRoom(room_id: Int): Room = newObjectFromJson(get("/rooms/$room_id"), Room::class.java)

    /**
     * チャットルームを作成
     *
     * @param name チャットルームの名前
     * @param members_admin_ids 管理者権限のアカウントID一覧
     * @param members_member_ids メンバー権限のアカウントID一覧
     * @param members_readonly_ids 閲覧のみ権限のアカウントID一覧
     * @param description チャットールームの概要
     * @param icon_preset チャットルームアイコンの種類
     * @return 作成したチャットルームIDを持つオブジェクト
     */
    fun postRoom(name: String,
                 members_admin_ids: Array<Int>,
                 members_member_ids: Array<Int>? = null,
                 members_readonly_ids: Array<Int>? = null,
                 description: String? = null,
                 icon_preset: IconPreset? = null): RoomResponse {

        val body = FormBody.Builder().apply {

            if (description != null) add("description", description)

            if (icon_preset != null) add("icon_preset", icon_preset.name)

            if (members_member_ids != null && members_admin_ids.isNotEmpty())
                add("members_member_ids", buildString {
                    members_member_ids.forEach { append(",").append(it) }
                }.replaceFirst(",", ""))

            if (members_readonly_ids != null && members_readonly_ids.isNotEmpty())
                add("members_readonly_ids", buildString {
                    members_readonly_ids.forEach { append(",").append(it) }
                }.replaceFirst(",", ""))

            add("members_admin_ids", buildString {
                members_admin_ids.forEach { append(",").append(it) }
            }.replaceFirst(",", ""))

            add("name", name)

        }.build()

        return newObjectFromJson(post("/rooms", body), RoomResponse::class.java)
    }

    /**
     * チャットルームの情報を更新
     *
     * @param room_id チャットルームID
     * @param description チャットルームの概要
     * @param icon_preset チャットルームアイコンの種類
     * @param name チャットルームのアイコン
     * @return 更新したチャットルームIDを持ったオブジェクト
     */
    fun putRoom(room_id: Int,
                description: String? = null,
                icon_preset: IconPreset? = null,
                name: String? = null): RoomResponse {

        val body = FormBody.Builder().apply {

            if (description != null) add("description", description)

            if (icon_preset != null) add("icon_preset", icon_preset.name)

            if (name != null) add("name", name)

        }.build()

        return newObjectFromJson(put("/rooms/$room_id", body), RoomResponse::class.java)
    }

    /**
     * チャットルームを削除・退席
     *
     * @param room_id チャットルームID
     * @param action_type 行動のタイプ
     */
    fun deleteRoom(room_id: Int, action_type: ActionType) {
        val body = FormBody.Builder()
                .add("action_type", action_type.name)
                .build()
        delete("/rooms/$room_id", body)
    }

    /**
     * チャットルームのメンバー一覧を取得
     *
     * @param room_id チャットルームID
     * @return チャットルームのメンバー一覧
     */
    fun getMembers(room_id: Int): Array<Member> {
        try {
            return newObjectFromJson(get("/rooms/$room_id/members"), Array<Member>::class.java)
        } catch(e: NullOrBlankResponseException) {
            return emptyArray()
        }
    }

    /**
     * チャットルームのメンバーを更新
     *
     * @param room_id チャットルームID
     * @param members_admin_ids 管理者権限のアカウントID一覧
     * @param members_member_ids メンバー権限のアカウントID一覧
     * @param members_readonly_ids 閲覧のみ権限のアカウントID一覧
     * @return 更新したチャットルームのアカウント一覧を持ったオブジェクト
     */
    fun putMembers(room_id: Int,
                   members_admin_ids: Array<Int>,
                   members_member_ids: Array<Int>? = null,
                   members_readonly_ids: Array<Int>? = null): MemberResponse {

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

        return newObjectFromJson(put("/rooms/$room_id/members", body), MemberResponse::class.java)
    }

    /**
     * チャットルームのメッセージ一覧を取得
     *
     * @param room_id チャットルームID
     * @param force false で未取得のみ取得 true で最新100件取得
     * @return 取得したメッセージ一覧
     */
    fun getMessages(room_id: Int, force: Boolean = false): Array<Message> {
        val url = buildString {
            append("/rooms/$room_id/messages?force=")
            if (force) append("1") else append("0")
        }
        try {
            return newObjectFromJson(get(url), Array<Message>::class.java)
        } catch(e: NullOrBlankResponseException) {
            return emptyArray()
        }
    }

    /**
     * チャットルームにメッセージを投稿
     *
     * @param room_id チャットルームID
     * @param message 投稿メッセージ
     * @return 投稿したメッセージIDを持ったオブジェクト
     */
    fun postMessage(room_id: Int, message: String): MessageResponse {

        val body = FormBody.Builder().add("body", message).build()

        return newObjectFromJson(post("/rooms/$room_id/messages", body), MessageResponse::class.java)
    }

    /**
     * チャットルームのメッセージを取得
     *
     * @param room_id チャットルームID
     * @param messageId メッセージID
     * @return 取得したメッセージ
     */
    fun getMessage(room_id: Int, messageId: Int): Message =
            newObjectFromJson(get("/rooms/$room_id/messages/$messageId"), Message::class.java)

    /**
     * チャットルームのタスクを取得
     *
     * @param room_id チャットルームiD
     * @param taskId タスクID
     * @return 取得したタスク
     */
    fun getTask(room_id: Int, taskId: Int): Task =
            newObjectFromJson(get("/rooms/$room_id/tasks/$taskId"), Task::class.java)

    /**
     * チャットルームのタスク一覧を取得
     *
     * @param room_id チャットルームID
     * @param account_id タスクを受け取ったアカウントID
     * @param assigned_by_account_id タスクを投げたアカウントID
     * @param status タスクの状態
     * @return 取得したタスク一覧
     */
    fun getTasks(room_id: Int,
                 account_id: Int? = null,
                 assigned_by_account_id: Int? = null,
                 status: TaskStatus? = null): Array<Task> {

        val url = buildString {
            append("/rooms/$room_id/tasks")
            if (account_id != null || assigned_by_account_id != null || status != null) {
                append("?")
                if (account_id != null) append("&account_id=$account_id")
                if (assigned_by_account_id != null) append("&assigned_by_account_id=$assigned_by_account_id")
                if (status != null) append("&status=${status.name}")
            }
        }

        try {
            return newObjectFromJson(get(url), Array<Task>::class.java)
        } catch(e: NullOrBlankResponseException) {
            return emptyArray()
        }
    }

    /**
     * チャットルームのファイル一覧を取得
     *
     * @param room_id チャットルームID
     * @param accountId ファイルをアップロードしたアカウントID
     * @return 取得したファイル一覧
     */
    fun getFiles(room_id: Int, accountId: Int? = null): Array<File> {
        val url = buildString {
            append("/rooms/$room_id/files")
            if (accountId != null) append("?account_id=$accountId")
        }
        try {
            return newObjectFromJson(get(url), Array<File>::class.java)
        } catch(e: NullOrBlankResponseException) {
            return emptyArray()
        }
    }

    /**
     * チャットルームのファイルを取得
     *
     * @param room_id チャットルームID
     * @param fileId ファイルID
     * @param createDownloadUrl true で30秒間のダウンロードリンクを生成
     */
    fun getFile(room_id: Int, fileId: Int, createDownloadUrl: Boolean = false): File {
        val url = buildString {
            append("/rooms/$room_id/files/$fileId")
            if (createDownloadUrl) append("?create_download_url=1")
        }
        return newObjectFromJson(get(url), File::class.java)
    }
}