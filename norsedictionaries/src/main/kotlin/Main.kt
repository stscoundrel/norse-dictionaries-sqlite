import io.github.stscoundrel.norsedictionaries.oldicelandic.createOldIcelandicDatabase
import io.github.stscoundrel.norsedictionaries.oldnorse.createOldNorseDatabase
import io.github.stscoundrel.norsedictionaries.oldnorwegian.createOldNorwegianDatabase

fun main() {
    createOldNorseDatabase()
    createOldNorwegianDatabase()
    createOldIcelandicDatabase()
}