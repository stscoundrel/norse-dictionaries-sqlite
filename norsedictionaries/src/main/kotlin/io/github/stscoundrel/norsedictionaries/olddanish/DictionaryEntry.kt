package io.github.stscoundrel.norsedictionaries.olddanish


import kotlinx.serialization.Serializable

@Serializable
data class OldDanishDictionaryEntry(
    val headword: String,
    val definitions: List<String>
)