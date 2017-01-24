package com.masahirosaito.chatwork4j.error

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * ResponseErrorsException
 *
 * @param json エラーレスポンスのJSON文字列
 */
class ResponseErrorsException(json: String) : ChatWork4jException(
        GsonBuilder().setPrettyPrinting().create().toJson(
                Gson().fromJson(json, ResponseError::class.java)
        )
)