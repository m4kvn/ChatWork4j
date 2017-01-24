---
title: ChatWork4j.getTasks - ChatWork4j
---

[ChatWork4j](../../index.md) / [com.masahirosaito.chatwork4j](../index.md) / [ChatWork4j](index.md) / [getTasks](.)

# getTasks

`fun getTasks(room_id: Int, account_id: Int? = null, assigned_by_account_id: Int? = null, status: `[`TaskStatus`](../../com.masahirosaito.chatwork4j.data.my/-task-status/index.md)`? = null): Array<`[`Task`](../../com.masahirosaito.chatwork4j.data.my/-task/index.md)`>` [(source)](https://github.com/MasahiroSaito/ChatWork4j/tree/master/src/main/kotlin/com/masahirosaito/chatwork4j/ChatWork4j.kt#L311)

チャットルームのタスク一覧を取得

### Parameters

`room_id` - チャットルームID

`account_id` - タスクを受け取ったアカウントID

`assigned_by_account_id` - タスクを投げたアカウントID

`status` - タスクの状態

**Return**
取得したタスク一覧

