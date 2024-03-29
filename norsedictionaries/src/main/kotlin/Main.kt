import io.github.stscoundrel.norsedictionaries.olddanish.createOldDanishDatabase
import io.github.stscoundrel.norsedictionaries.oldicelandic.createOldIcelandicDatabase
import io.github.stscoundrel.norsedictionaries.oldnorse.createOldNorseDatabase
import io.github.stscoundrel.norsedictionaries.oldnorwegian.createOldNorwegianDatabase
import io.github.stscoundrel.norsedictionaries.oldswedish.createOldSwedishDatabase

fun main() {
    createOldNorseDatabase()
    createOldNorwegianDatabase()
    createOldIcelandicDatabase()
    createOldSwedishDatabase()
    createOldDanishDatabase()
}