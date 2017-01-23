package com.masahirosaito.chatwork4j

import com.google.gson.Gson
import com.masahirosaito.chatwork4j.data.RoomResponse
import com.masahirosaito.chatwork4j.data.me.Me
import com.masahirosaito.chatwork4j.data.rooms.Room
import com.masahirosaito.chatwork4j.data.contacts.Contact
import com.masahirosaito.chatwork4j.data.my.Status
import com.masahirosaito.chatwork4j.data.my.Task
import okhttp3.*

/**
 * Created by masahiro on 2017/01/19.
 */
class ChatWork4j(val TOKEN: String) {

    init {
        CHATWORK_API_TOKEN = TOKEN
    }

    companion object {
        val CHATWORK_API_URL_ROOT = "https://api.chatwork.com/v1"
        val CHATWORK_API_TOKEN_HEADER = "X-ChatWorkToken"
        var CHATWORK_API_TOKEN: String = ""

        val client = OkHttpClient()

        /**
         * GETリクエスト送信
         *
         * @param url URL
         * @return レスポンスのJSON文字列
         */
        fun get(url: String): String {
            val request = Request.Builder()
                    .url(CHATWORK_API_URL_ROOT + url)
                    .addHeader(CHATWORK_API_TOKEN_HEADER, CHATWORK_API_TOKEN)
                    .build()
            val response = client.newCall(request).execute()
            return response.body().string()
        }

        /**
         * Json文字列からオブジェクトを生成
         *
         * @param json Json文字列
         * @param clazz 生成するオブジェクトのクラス
         * @return 生成したオブジェクト
         */
        fun <T> newObjectFromJson(json: String, clazz: Class<T>): T? {
            if (json.isNullOrBlank()) return null
            else return Gson().fromJson(json, clazz)
        }

        /**
         *　POSTリクエスト送信
         *
         * @param url URL
         * @param body RequestBody
         * @return レスポンスのJSON文字列
         */
        fun post(url: String, body: RequestBody): String {
            val request = Request.Builder()
                    .url(CHATWORK_API_URL_ROOT + url)
                    .addHeader(CHATWORK_API_TOKEN_HEADER, CHATWORK_API_TOKEN)
                    .post(body)
                    .build()
            val response = client.newCall(request).execute()
            return response.body().string()
        }

        /**
         * PUTリクエスト送信
         *
         * @param url URL
         * @param body RequestBody
         * @return レスポンスのJSON文字列
         */
        fun put(url: String, body: RequestBody): String {
            val request = Request.Builder()
                    .url(CHATWORK_API_URL_ROOT + url)
                    .addHeader(CHATWORK_API_TOKEN_HEADER, CHATWORK_API_TOKEN)
                    .put(body)
                    .build()
            val response = client.newCall(request).execute()
            return response.body().string()
        }

        /**
         * DELETEリクエスト送信
         *
         * @param url URL
         * @param body RequestBody
         * @return レスポンスのJSON文字列
         */
        fun delete(url: String, body: RequestBody): String {
            val request = Request.Builder()
                    .url(CHATWORK_API_URL_ROOT + url)
                    .addHeader(CHATWORK_API_TOKEN_HEADER, CHATWORK_API_TOKEN)
                    .delete(body)
                    .build()
            val response = client.newCall(request).execute()
            return response.body().string()
        }
    }

    /**
     * 自分の情報を取得
     *
     * @return 自分の情報
     */
    fun getMe(): Me? = newObjectFromJson(get("/me"), Me::class.java)

    /**
     * 自分のデータを取得
     *
     * @return 自分のデータ
     */
    fun getMyStatus(): Status? = newObjectFromJson(get("/my/status"), Status::class.java)

    /**
     * 自分のタスク一覧を取得
     *
     * @return 自分のタスク一覧
     */
    fun getMyTasks(): Array<Task>? = newObjectFromJson(get("/my/tasks"), Array<Task>::class.java)

    /**
     * 自分のコンタクト一覧を取得
     *
     * @return 自分のコンタクト一覧
     */
    fun getContacts(): Array<Contact>? = newObjectFromJson(get("/contacts"), Array<Contact>::class.java)

    /**
     * 自分のチャットルーム一覧を取得
     *
     * @return 自分のチャット一覧
     */
    fun getRooms(): Array<Room>? = newObjectFromJson(get("/rooms"), Array<Room>::class.java)

    /**
     * 指定したチャットルーム情報を取得
     *
     * @param room_id チャットのルームID
     * @return チャットルーム
     */
    fun getRoom(room_id: Int): Room? = newObjectFromJson(get("/rooms/$room_id"), Room::class.java)

    /**
     * グループチャットを新規作成
     *
     * @param description グループチャットの概要説明テキスト
     * @param icon_preset グループチャットのアイコン種類
     * @param members_admin_ids 管理者権限のユーザー(必須)
     * @param members_member_ids メンバー権限のユーザー
     * @param members_readonly_ids 閲覧のみ権限のユーザー
     * @param name グループチャット名
     * @return レスポンスのJSON文字列
     */
    fun postRoom(description: String? = null,
                 icon_preset: IconPreset? = null,
                 members_admin_ids: Array<Int>,
                 members_member_ids: Array<Int>? = null,
                 members_readonly_ids: Array<Int>? = null,
                 name: String): RoomResponse? {

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
     * @param room_id グループチャットのルームID
     * @param description グループチャットの概要説明テキスト
     * @param icon_preset グループチャットのアイコン種類
     * @param name グループチャット名
     * @return レスポンスのJSON文字列
     */
    fun putRoom(room_id: Int,
                description: String? = null,
                icon_preset: IconPreset? = null,
                name: String? = null): RoomResponse? {

        val body = FormBody.Builder().apply {

            if (description != null) add("description", description)

            if (icon_preset != null) add("icon_preset", icon_preset.name)

            if (name != null) add("name", name)

        }.build()

        return newObjectFromJson(put("/rooms/$room_id", body), RoomResponse::class.java)
    }

    /**
     * チャットルームのアイコンの種類
     */
    enum class IconPreset {
        group, check, document, meeting, event, project, business, study,
        security, star, idea, heart, magcup, beer, music, sports, travel
    }
}