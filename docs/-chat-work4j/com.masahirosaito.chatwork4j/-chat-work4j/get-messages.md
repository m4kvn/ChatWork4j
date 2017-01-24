---
title: ChatWork4j.getMessages - ChatWork4j
---

[ChatWork4j](../../index.md) / [com.masahirosaito.chatwork4j](../index.md) / [ChatWork4j](index.md) / [getMessages](.)

# getMessages

`fun getMessages(room_id: Int, force: Boolean = false): Array<`[`Message`](../../com.masahirosaito.chatwork4j.data.rooms/-message/index.md)`>` [(source)](https://github.com/MasahiroSaito/ChatWork4j/tree/master/src/main/kotlin/com/masahirosaito/chatwork4j/ChatWork4j.kt#L260)

チャットルームのメッセージ一覧を取得

### Parameters

`room_id` - チャットルームID

`force` - false で未取得のみ取得 true で最新100件取得

**Return**
取得したメッセージ一覧

