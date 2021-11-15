package com.example.table

import org.jetbrains.exposed.dao.id.IntIdTable

object Memos: IntIdTable(name = "memos") {
    val title = varchar("title", 128)
    val body = varchar("body", 256)
}