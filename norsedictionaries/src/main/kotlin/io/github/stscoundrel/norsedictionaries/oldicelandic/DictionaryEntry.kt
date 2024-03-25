package io.github.stscoundrel.norsedictionaries.oldicelandic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OldIcelandicDictionaryEntry(
    @SerialName("word")
    val headword: String,
    val definitions: List<String>
)