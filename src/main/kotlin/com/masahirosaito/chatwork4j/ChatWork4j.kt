package com.masahirosaito.chatwork4j

import com.google.gson.Gson
import com.masahirosaito.chatwork4j.data.Me
import com.masahirosaito.chatwork4j.data.rooms.Message
import com.masahirosaito.chatwork4j.data.rooms.Room
import com.masahirosaito.chatwork4j.data.contacts.Contact
import com.masahirosaito.chatwork4j.data.my.Status
import com.masahirosaito.chatwork4j.data.my.Task
import com.masahirosaito.chatwork4j.data.rooms.Member
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by masahiro on 2017/01/19.
 */
class ChatWork4j(val TOKEN: String) {
    val CHATWORK_API_URL_ROOT = "https://api.chatwork.com/v1"
    val CHATWORK_API_TOKEN_HEADER = "X-ChatWorkToken"

    val client = OkHttpClient()

    private fun getJsonFromResponse(url: String) : String {
        val request = Request.Builder()
                .url(CHATWORK_API_URL_ROOT + url)
                .addHeader(CHATWORK_API_TOKEN_HEADER, TOKEN)
                .build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    fun getMe() : Me = Gson().fromJson(
            getJsonFromResponse("/me"),
            Me::class.java
    )

    fun getMyStatus() : Status = Gson().fromJson(
            getJsonFromResponse("/my/status"),
            Status::class.java
    )

    fun getMyTasks() : Array<Task> = Gson().fromJson(
            getJsonFromResponse("/my/tasks"),
            Array<Task>::class.java
    )

    fun getContacts() : Array<Contact> = Gson().fromJson(
            getJsonFromResponse("/contacts"),
            Array<Contact>::class.java
    )

    fun getRooms() : Array<Room> = Gson().fromJson(
            getJsonFromResponse("/rooms"),
            Array<Room>::class.java
    )

    fun getRoom(roomId: Int) : Room = Gson().fromJson(
            getJsonFromResponse("/rooms/$roomId"),
            Room::class.java
    )

    fun Room.getMembers() : Array<Member> = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/members"),
            Array<Member>::class.java
    )

    fun Room.getMessages(force: Int) : Array<Message> = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/messages?force=$force"),
            Array<Message>::class.java
    )

    fun Room.getMessage(messageId: Int) : Message = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/messages/$messageId"),
            Message::class.java
    )

    fun Room.getTasks() : Array<Task> = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/tasks"),
            Array<Task>::class.java
    )

    fun Room.getTask(taskId: Int) : Task = Gson().fromJson(
            getJsonFromResponse("/rooms/${this.room_id}/tasks/$taskId"),
            Task::class.java
    )
}