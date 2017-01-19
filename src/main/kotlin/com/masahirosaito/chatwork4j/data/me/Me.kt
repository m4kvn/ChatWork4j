package com.masahirosaito.chatwork4j.data.me

/**
 * Created by masahiro on 2017/01/19.
 */
data class Me(
        val account_id: Int,
        val room_id: Int,
        val name: String,
        val chatwork_id: String,
        val organization_id: Int,
        val organization_name: String,
        val department: String,
        val title: String,
        val url: String,
        val introduction: String,
        val mail: String,
        val tel_organization: String,
        val tel_extension: String,
        val tel_mobile: String,
        val skype: String,
        val facebook: String,
        val twitter: String,
        val avatar_image_url: String
)