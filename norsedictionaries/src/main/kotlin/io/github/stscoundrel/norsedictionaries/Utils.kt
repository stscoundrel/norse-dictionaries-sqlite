package io.github.stscoundrel.norsedictionaries

import org.sqlite.SQLiteConfig
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

fun readResourceFile(fileName: String): String {
    return File(Thread.currentThread().contextClassLoader.getResource(fileName).file).readText()
}

fun connectToSQLiteDatabase(databasePath: String): Connection {
    val config = SQLiteConfig()
    config.enforceForeignKeys(true)
    val url = "jdbc:sqlite:$databasePath"
    return DriverManager.getConnection(url, config.toProperties())
}