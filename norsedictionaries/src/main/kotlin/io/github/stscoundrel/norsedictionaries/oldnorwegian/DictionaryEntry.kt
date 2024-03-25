package io.github.stscoundrel.norsedictionaries.oldnorwegian

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OldNorwegianDictionaryEntry(
    @SerialName("word")
    val headword: String,
    val definition: String,
    val partOfSpeech: String,
)