package com.example

import com.example.table.Memos
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    Database.connect(
        "jdbc:mysql://127.0.0.1:33060/kotlin_memo",
        user = "root",
        password = "mysql"
    )

    transaction {
        addLogger(StdOutSqlLogger)

        val id = Memos.insert {
            it[title] = "初めての本"
            it[body] = "いい本でした"
        } get Memos.id
        println("id: $id")

        val memo = Memos.select{ Memos.id eq id }.single()
        println("memo: $memo")

    }
}