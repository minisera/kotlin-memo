package com.example

import com.example.module.memoModule
import com.example.route.root
import com.example.table.Memos
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.Koin
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    Database.connect(
        "jdbc:mysql://127.0.0.1:33060/kotlin_memo",
        user = "root",
        password = "mysql"
    )

    data class JsonErrorResponse(val error: String)
    install(StatusPages) {
        exception<NotFoundException> { cause ->
            val errorMessage: String = cause.message ?: "Unknown error"
            call.respond(HttpStatusCode.NotFound, JsonErrorResponse(errorMessage))
        }
    }
    install(Locations)
    install(ContentNegotiation) {
        jackson()
    }
    install(Koin) {
        modules(memoModule)
    }

    routing {
        root()
    }
}