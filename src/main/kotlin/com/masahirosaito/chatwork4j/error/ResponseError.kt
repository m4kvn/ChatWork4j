package com.masahirosaito.chatwork4j.error

import java.util.*

/**
 * ResponseError
 *
 * @property errors　エラーメッセージ一覧
 */
data class ResponseError(val errors: Array<String>) {

    /**
     * 他のオブジェクトと比較
     *
     * @param other 比較対象
     * @return 一緒ならtrue
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as ResponseError

        if (!Arrays.equals(errors, other.errors)) return false

        return true
    }

    /**
     * ハッシュコードを取得
     *
     * @return ハッシュコード
     */
    override fun hashCode(): Int {
        return Arrays.hashCode(errors)
    }
}