package com.example.repository.implement

import com.example.domain.Memo
import com.example.repository.MemoRepository
import com.example.table.Memos
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class MemoRepositoryImpl: MemoRepository {

    override fun all(): List<Memo> {
        return Memos.selectAll().map {
            Memo(
                id = it[Memos.id].value,
                title = it[Memos.title],
                body = it[Memos.body]
            )
        }
    }

    override fun findById(id: Int): Memo? {
        return Memos.select { Memos.id eq id }.map {
            Memo(
                id = it[Memos.id].value,
                title = it[Memos.title],
                body = it[Memos.body]
            )
        }.firstOrNull()
    }

    override fun create(memo: Memo): Int {
        return Memos.insertAndGetId {
            it[title] = memo.title
            it[body]  = memo.body
        }.value
    }

}