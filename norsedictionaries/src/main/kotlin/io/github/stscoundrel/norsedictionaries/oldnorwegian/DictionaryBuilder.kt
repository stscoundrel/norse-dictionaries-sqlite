package io.github.stscoundrel.norsedictionaries.oldnorwegian

import io.github.stscoundrel.norsedictionaries.connectToSQLiteDatabase
import io.github.stscoundrel.norsedictionaries.readResourceFile
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.sql.Connection

private const val jsonPath = "old-norwegian-dictionary.json"
private const val databasePath = "src/main/resources/old-norwegian-dictionary.db"


fun createOldNorwegianDatabase() {
    val entries = getDictionaryEntries()

    val connection = connectToSQLiteDatabase(databasePath)

    createDatabaseTable(connection)
    createDatabaseEntries(connection, entries)

    connection.close()
}

private fun getDictionaryEntries(): List<OldNorwegianDictionaryEntry> {
    val jsonContent = readResourceFile(jsonPath)
    return Json.decodeFromString<List<OldNorwegianDictionaryEntry>>(jsonContent)
}

private fun createDatabaseTable(connection: Connection) {
    val statement = connection.createStatement()
    val sql = """
        CREATE TABLE IF NOT EXISTS old_norwegian (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            headword TEXT NOT NULL,
            definition TEXT NOT NULL,
            part_of_speech TEXT NOT NULL
        );
    """.trimIndent()
    statement.executeUpdate(sql)
    statement.close()
}

private fun createDatabaseEntries(connection: Connection, entries: List<OldNorwegianDictionaryEntry>) {
    val sql = "INSERT INTO old_norwegian (headword, definition, part_of_speech) VALUES (?, ?, ?)"
    val preparedStatement = connection.prepareStatement(sql)

    for (entry in entries) {
        preparedStatement.setString(1, entry.headword)
        preparedStatement.setString(2, entry.definition)
        preparedStatement.setString(3, entry.partOfSpeech)
        preparedStatement.addBatch()
    }

    preparedStatement.executeBatch()
    preparedStatement.close()
}