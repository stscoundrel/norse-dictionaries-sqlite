package io.github.stscoundrel.norsedictionaries.oldswedish

import kotlinx.serialization.Serializable

@Serializable
data class OldSwedishDictionaryEntry(
    val headword: String,
    val partOfSpeech: List<String>,
    val grammaticalAspect: String,
    val information: String,
    val definitions: List<String>,
    val alternativeForms: List<String>
)