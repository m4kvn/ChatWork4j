package com.masahirosaito.chatwork4j.data.rooms

import com.google.gson.Gson
import com.masahirosaito.chatwork4j.ChatWork4j.Companion.getJsonFromResponse
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

    fun getTasks() : Array<Task> = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/tasks"),
            Array<Task>::class.java
    )

    fun getTask(taskId: Int) : Task = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/tasks/$taskId"),
            Task::class.java
    )

    fun getFiles() : Array<File> = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/files"),
            Array<File>::class.java
    )

    fun getFile(fileId: Int) : File = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/files/$fileId"),
            File::class.java
    )
}