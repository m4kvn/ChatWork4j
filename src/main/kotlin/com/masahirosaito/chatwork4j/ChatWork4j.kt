package com.masahirosaito.chatwork4j

import com.google.gson.Gson
import com.masahirosaito.chatwork4j.data.Me
import com.masahirosaito.chatwork4j.data.Message
import com.masahirosaito.chatwork4j.data.Room
import com.masahirosaito.chatwork4j.data.my.Status
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

    fun getMy() : Me = Gson().fromJson(
            getJsonFromResponse("/me"),
            Me::class.java
    )

    fun getMyStatus() : Status = Gson().fromJson(
            getJsonFromResponse("/my/status"),
            Status::class.java
    )

    fun getRooms() : Array<Room> = Gson().fromJson(
            getJsonFromResponse("/rooms"),
            Array<Room>::class.java
    )

    fun getMessages(roomId: Int, force: Int) : Array<Message> = Gson().fromJson(
            getJsonFromResponse("/rooms/$roomId/messages?force=$force"),
            Array<Message>::class.java
    )
}