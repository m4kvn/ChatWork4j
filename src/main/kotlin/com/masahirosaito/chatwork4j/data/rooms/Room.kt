package com.masahirosaito.chatwork4j.data.rooms

import com.google.gson.Gson
import com.masahirosaito.chatwork4j.ChatWork4j.Companion.getJsonFromResponse
import com.masahirosaito.chatwork4j.ChatWork4j.Companion.getObjectFromGson
import com.masahirosaito.chatwork4j.data.my.Task

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
    fun getMembers() : Array<Member> = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/members"),
            Array<Member>::class.java
    )

    fun getMessages(force: Int) : Array<Message> = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/messages?force=$force"),
            Array<Message>::class.java
    )

    fun getMessage(messageId: Int) : Message = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/messages/$messageId"),
            Message::class.java
    )

    fun getTask(taskId: Int) : Task = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/tasks/$taskId"),
            Task::class.java
    )

    fun getTasks(account_id: Int? = null,
                assigned_by_account_id: Int? = null,
                status: Task.Status? = null) : Array<Task>? {

        val url = buildString { append("/rooms/$room_id/tasks")
            if (account_id != null || assigned_by_account_id != null || status != null) { append("?")
                if (account_id != null)             append("&account_id=$account_id")
                if (assigned_by_account_id != null) append("&assigned_by_account_id=$assigned_by_account_id")
                if (status != null)                 append("&status=${status.name.toLowerCase()}")
            }
        }

        return getObjectFromGson(url, Array<Task>::class.java)
    }

    fun getFiles() : Array<File> = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/files"),
            Array<File>::class.java
    )

    fun getFiles(accountId: Int) : Array<File> = Gson().fromJson(
            getJsonFromResponse("/rooms/$room_id/files?account_id=$accountId"),
            Array<File>::class.java
    )

    fun getFile(fileId: Int, createDownloadUrl: Boolean) : File? {
        val url = buildString {
            append("/rooms/$room_id/files/$fileId")
            if (createDownloadUrl) append("?create_download_url=1")
        }
        return getObjectFromGson(url, File::class.java)
    }
}