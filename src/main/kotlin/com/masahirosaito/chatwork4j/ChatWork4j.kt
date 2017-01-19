package com.masahirosaito.chatwork4j

import com.google.gson.Gson
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

    companion object {
        val CHATWORK_API_URL_ROOT = "https://api.chatwork.com/v1"
        val CHATWORK_API_TOKEN_HEADER = "X-ChatWorkToken"
        var CHATWORK_API_TOKEN: String = ""

        val client = OkHttpClient()

        fun getJsonFromResponse(url: String) : String {
            val request = Request.Builder()
                    .url(CHATWORK_API_URL_ROOT + url)
                    .addHeader(CHATWORK_API_TOKEN_HEADER, CHATWORK_API_TOKEN)
                    .build()
            val response = client.newCall(request).execute()
            return response.body().string()
        }

        fun <T> getObjectFromGson(url: String, clazz: Class<T>) : T? {
            val json = getJsonFromResponse(url)
            if (json.isNullOrBlank()) return null
            else return Gson().fromJson(json, clazz)
        }

        fun post(url: String, body: RequestBody) : String {
            val request = Request.Builder()
                    .url(CHATWORK_API_URL_ROOT + url)
                    .addHeader(CHATWORK_API_TOKEN_HEADER, CHATWORK_API_TOKEN)
                    .post(body)
                    .build()
            val response = client.newCall(request).execute()
            return response.body().string()
        }
    }

    init {
        CHATWORK_API_TOKEN = TOKEN
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
}