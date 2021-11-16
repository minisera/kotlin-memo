package com.example.repository

import com.example.domain.Memo

interface MemoRepository {

    /**
     * 全件取得
     */
    fun all(): List<Memo>

    /**
     * IDと一致するMemoを取得する
     */
    fun findById(id: Int): Memo?

    /**
     * Memoをinsertする
     */
    fun create(memo: Memo): Int
}