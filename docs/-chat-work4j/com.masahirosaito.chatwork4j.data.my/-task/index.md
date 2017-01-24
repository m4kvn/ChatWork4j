---
title: Task - ChatWork4j
---

[ChatWork4j](../../index.md) / [com.masahirosaito.chatwork4j.data.my](../index.md) / [Task](.)

# Task

`data class Task` [(source)](https://github.com/MasahiroSaito/ChatWork4j/tree/master/src/main/kotlin/com/masahirosaito/chatwork4j/data/my/Task.kt#L14)

Taskデータクラス

### Constructors

| [&lt;init&gt;](-init-.md) | `Task(task_id: Int, room: `[`TaskRoom`](../-task-room/index.md)`, assigned_by_account: `[`AssignedByAccount`](../-assigned-by-account/index.md)`, message_id: Int, body: String, limit_time: Long, status: String)`<br>Taskデータクラス |

### Properties

| [assigned_by_account](assigned_by_account.md) | `val assigned_by_account: `[`AssignedByAccount`](../-assigned-by-account/index.md)<br>タスクを投げたアカウント |
| [body](body.md) | `val body: String`<br>メッセージ本文 |
| [limit_time](limit_time.md) | `val limit_time: Long`<br>タスク期日 |
| [message_id](message_id.md) | `val message_id: Int`<br>メッセージID |
| [room](room.md) | `val room: `[`TaskRoom`](../-task-room/index.md)<br>ルーム |
| [status](status.md) | `val status: String`<br>タスク状態 |
| [task_id](task_id.md) | `val task_id: Int`<br>タスクID |

