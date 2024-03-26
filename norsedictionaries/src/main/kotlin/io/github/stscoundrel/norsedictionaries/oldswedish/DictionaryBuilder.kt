package io.github.stscoundrel.norsedictionaries.oldswedish


import io.github.stscoundrel.norsedictionaries.connectToSQLiteDatabase
import io.github.stscoundrel.norsedictionaries.readResourceFile
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.sql.Connection

private const val jsonPath = "old-swedish-dictionary.json"
private const val databasePath = "src/main/resources/old-swedish-dictionary.db"


fun createOldSwedishDatabase() {
    val entries = getDictionaryEntries()

    val connection = connectToSQLiteDatabase(databasePath)

    createDatabaseTable(connection)
    createDatabaseEntries(connection, entries)

    connection.close()
}

private fun getDictionaryEntries(): List<OldSwedishDictionaryEntry> {
    val jsonContent = readResourceFile(jsonPath)
    return Json.decodeFromString<List<OldSwedishDictionaryEntry>>(jsonContent)
}

private fun createDatabaseTable(connection: Connection) {
    val statement = connection.createStatement()
    val sql = """
        CREATE TABLE IF NOT EXISTS old_swedish (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            headword TEXT NOT NULL,
            partOfSpeech TEXT NOT NULL,
            grammaticalAspect TEXT NOT NULL,
            information TEXT NOT NULL,
            definitions TEXT NOT NULL,
            alternativeForms TEXT NOT NULL
        );
    """.trimIndent()
    statement.executeUpdate(sql)
    statement.close()
}

private fun createDatabaseEntries(connection: Connection, entries: List<OldSwedishDictionaryEntry>) {
    val sql = """
        INSERT INTO old_swedish (
            headword, 
            partOfSpeech, 
            grammaticalAspect, 
            information, 
            definitions, 
            alternativeForms
        ) 
        VALUES (?, ?, ?, ?, ?, ?)
    """.trimIndent()
    val preparedStatement = connection.prepareStatement(sql)

    for (entry in entries) {
        val partOfSpeechString = entry.partOfSpeech.joinToString(separator = "\n")
        val definitionsString = entry.definitions.joinToString(separator = "\n")
        val alternativeFormsString = entry.alternativeForms.joinToString(separator = "\n")

        preparedStatement.setString(1, entry.headword)
        preparedStatement.setString(2, partOfSpeechString)
        preparedStatement.setString(3, entry.grammaticalAspect)
        preparedStatement.setString(4, entry.information)
        preparedStatement.setString(5, definitionsString)
        preparedStatement.setString(6, alternativeFormsString)
        preparedStatement.addBatch()
    }

    preparedStatement.executeBatch()
    preparedStatement.close()
}