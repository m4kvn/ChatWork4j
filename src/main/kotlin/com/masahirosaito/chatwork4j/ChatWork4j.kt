package com.masahirosaito.chatwork4j

import com.google.gson.Gson
import com.masahirosaito.chatwork4j.data.Status
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

    fun getMyStatus() : Status = Gson().fromJson(
            getJsonFromResponse("/my/status"),
            Status::class.java
    )
}