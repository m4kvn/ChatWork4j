package com.masahirosaito.chatwork4j.error

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Created by masahiro on 2017/01/24.
 */
class ResponseErrorsException(json: String) : ChatWork4jException(
        GsonBuilder().setPrettyPrinting().create().toJson(
                Gson().fromJson(json, ResponseError::class.java)
        )
)