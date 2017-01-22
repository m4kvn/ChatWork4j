package com.masahirosaito.chatwork4j.data.rooms

import com.masahirosaito.chatwork4j.ChatWork4j.Companion.getObjectFromGson
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
    fun getMembers(): Array<Member>? = getObjectFromGson("/rooms/$room_id/members", Array<Member>::class.java)

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

    fun getMessages(force: Boolean = false): Array<Message>? {
        val url = buildString {
            append("/rooms/$room_id/messages?force=")
            if (force) append("1") else append("0")
        }
        return getObjectFromGson(url, Array<Message>::class.java)
    }

    fun postMessage(message: String): String = post(
            "/rooms/$room_id/messages",
            FormBody.Builder().add("body", message).build()
    )

    fun getMessage(messageId: Int): Message? =
            getObjectFromGson("/rooms/$room_id/messages/$messageId", Message::class.java)

    fun getTask(taskId: Int): Task? = getObjectFromGson("/rooms/$room_id/tasks/$taskId", Task::class.java)

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

        return getObjectFromGson(url, Array<Task>::class.java)
    }

    fun getFiles(accountId: Int? = null): Array<File>? {
        val url = buildString {
            append("/rooms/$room_id/files")
            if (accountId != null) append("?account_id=$accountId")
        }
        return getObjectFromGson(url, Array<File>::class.java)
    }

    fun getFile(fileId: Int, createDownloadUrl: Boolean = false): File? {
        val url = buildString {
            append("/rooms/$room_id/files/$fileId")
            if (createDownloadUrl) append("?create_download_url=1")
        }
        return getObjectFromGson(url, File::class.java)
    }
}