package io.github.stscoundrel.norsedictionaries.oldnorse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OldNorseDictionaryEntry(
    @SerialName("word")
    val headword: String,
    val definitions: List<String>
)