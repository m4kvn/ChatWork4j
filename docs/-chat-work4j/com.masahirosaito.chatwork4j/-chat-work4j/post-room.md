---
title: ChatWork4j.postRoom - ChatWork4j
---

[ChatWork4j](../../index.md) / [com.masahirosaito.chatwork4j](../index.md) / [ChatWork4j](index.md) / [postRoom](.)

# postRoom

`fun postRoom(name: String, members_admin_ids: Array<Int>, members_member_ids: Array<Int>? = null, members_readonly_ids: Array<Int>? = null, description: String? = null, icon_preset: `[`IconPreset`](../-icon-preset/index.md)`? = null): `[`RoomResponse`](../../com.masahirosaito.chatwork4j.data/-room-response/index.md) [(source)](https://github.com/MasahiroSaito/ChatWork4j/tree/master/src/main/kotlin/com/masahirosaito/chatwork4j/ChatWork4j.kt#L138)

チャットルームを作成

### Parameters

`name` - チャットルームの名前

`members_admin_ids` - 管理者権限のアカウントID一覧

`members_member_ids` - メンバー権限のアカウントID一覧

`members_readonly_ids` - 閲覧のみ権限のアカウントID一覧

`description` - チャットールームの概要

`icon_preset` - チャットルームアイコンの種類

**Return**
作成したチャットルームIDを持つオブジェクト

