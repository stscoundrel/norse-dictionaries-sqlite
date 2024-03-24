package io.github.stscoundrel.norsedictionaries.oldnorse

import io.github.stscoundrel.norsedictionaries.connectToSQLiteDatabase
import io.github.stscoundrel.norsedictionaries.readResourceFile
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.sql.Connection

private const val jsonPath = "old-norse-dictionary.json"
private const val databasePath = "src/main/resources/old-norse-dictionary.db"


fun createOldNorseDatabase() {
    val entries = getDictionaryEntries()

    val connection = connectToSQLiteDatabase(databasePath)

    createDatabaseTable(connection)
    createDatabaseEntries(connection, entries)

    connection.close()
}

private fun getDictionaryEntries(): List<OldNorseDictionaryEntry> {
    val jsonContent = readResourceFile(jsonPath)
    return Json.decodeFromString<List<OldNorseDictionaryEntry>>(jsonContent)
}

private fun createDatabaseTable(connection: Connection) {
    val statement = connection.createStatement()
    val sql = """
        CREATE TABLE IF NOT EXISTS old_norse (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            headword TEXT NOT NULL,
            definitions TEXT NOT NULL
        );
    """.trimIndent()
    statement.executeUpdate(sql)
    statement.close()
}

private fun createDatabaseEntries(connection: Connection, entries: List<OldNorseDictionaryEntry>) {
    val sql = "INSERT INTO old_norse (headword, definitions) VALUES (?, ?)"
    val preparedStatement = connection.prepareStatement(sql)

    for (entry in entries) {
        val definitionsString = entry.definitions.joinToString(separator = "\n")
        preparedStatement.setString(1, entry.headword)
        preparedStatement.setString(2, definitionsString)
        preparedStatement.addBatch()
    }

    preparedStatement.executeBatch()
    preparedStatement.close()
}