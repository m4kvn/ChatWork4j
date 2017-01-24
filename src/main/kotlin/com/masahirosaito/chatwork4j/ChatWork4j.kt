package com.masahirosaito.chatwork4j

import com.google.gson.Gson
import com.masahirosaito.chatwork4j.data.MemberResponse
import com.masahirosaito.chatwork4j.data.MessageResponse
import com.masahirosaito.chatwork4j.data.RoomResponse
import com.masahirosaito.chatwork4j.data.me.Me
import com.masahirosaito.chatwork4j.data.contacts.Contact
import com.masahirosaito.chatwork4j.data.my.Status
import com.masahirosaito.chatwork4j.data.my.Task
import com.masahirosaito.chatwork4j.data.rooms.File
import com.masahirosaito.chatwork4j.data.rooms.Member
import com.masahirosaito.chatwork4j.data.rooms.Message
import com.masahirosaito.chatwork4j.data.rooms.Room
import com.masahirosaito.chatwork4j.error.ResponseErrorsException
import com.masahirosaito.chatwork4j.error.NullOrBlankResponseException
import okhttp3.*

class ChatWork4j(private val TOKEN: String) {
    private val client = OkHttpClient()

    companion object {
        val CHATWORK_API_URL_ROOT = "https://api.chatwork.com/v1"
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

    enum class IconPreset {
        group, check, document, meeting, event, project, business, study,
        security, star, idea, heart, magcup, beer, music, sports, travel
    }

    enum class ActionType { leave, delete }

    fun getMe(): Me = newObjectFromJson(get("/me"), Me::class.java)

    fun getMyStatus(): Status = newObjectFromJson(get("/my/status"), Status::class.java)

    fun getMyTasks(): Array<Task> = newObjectFromJson(get("/my/tasks"), Array<Task>::class.java)

    fun getContacts(): Array<Contact> = newObjectFromJson(get("/contacts"), Array<Contact>::class.java)

    fun getRooms(): Array<Room> = newObjectFromJson(get("/rooms"), Array<Room>::class.java)

    fun getRoom(room_id: Int): Room = newObjectFromJson(get("/rooms/$room_id"), Room::class.java)

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

    fun deleteRoom(room_id: Int, action_type: ActionType) {
        val body = FormBody.Builder()
                .add("action_type", action_type.name)
                .build()
        delete("/rooms/$room_id", body)
    }

    fun getMembers(room_id: Int): Array<Member> =
            newObjectFromJson(get("/rooms/$room_id/members"), Array<Member>::class.java)

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

    fun getMessages(room_id: Int, force: Boolean = false): Array<Message> {
        val url = buildString {
            append("/rooms/$room_id/messages?force=")
            if (force) append("1") else append("0")
        }
        return newObjectFromJson(get(url), Array<Message>::class.java)
    }

    fun postMessage(room_id: Int, message: String): MessageResponse {

        val body = FormBody.Builder().add("body", message).build()

        return newObjectFromJson(post("/rooms/$room_id/messages", body), MessageResponse::class.java)
    }

    fun getMessage(room_id: Int, messageId: Int): Message =
            newObjectFromJson(get("/rooms/$room_id/messages/$messageId"), Message::class.java)

    fun getTask(room_id: Int, taskId: Int): Task =
            newObjectFromJson(get("/rooms/$room_id/tasks/$taskId"), Task::class.java)

    fun getTasks(room_id: Int,
                 account_id: Int? = null,
                 assigned_by_account_id: Int? = null,
                 status: Task.Status? = null): Array<Task> {

        val url = buildString {
            append("/rooms/$room_id/tasks")
            if (account_id != null || assigned_by_account_id != null || status != null) {
                append("?")
                if (account_id != null) append("&account_id=$account_id")
                if (assigned_by_account_id != null) append("&assigned_by_account_id=$assigned_by_account_id")
                if (status != null) append("&status=${status.name.toLowerCase()}")
            }
        }

        return newObjectFromJson(get(url), Array<Task>::class.java)
    }

    fun getFiles(room_id: Int, accountId: Int? = null): Array<File> {
        val url = buildString {
            append("/rooms/$room_id/files")
            if (accountId != null) append("?account_id=$accountId")
        }
        return newObjectFromJson(get(url), Array<File>::class.java)
    }

    fun getFile(room_id: Int, fileId: Int, createDownloadUrl: Boolean = false): File {
        val url = buildString {
            append("/rooms/$room_id/files/$fileId")
            if (createDownloadUrl) append("?create_download_url=1")
        }
        return newObjectFromJson(get(url), File::class.java)
    }
}