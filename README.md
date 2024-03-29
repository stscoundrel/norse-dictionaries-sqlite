# Norse Dictionaries SQLite

Build Norse language group dictionaries into SQLite databases.

Dictionaries available:
- Old Norse Dictionary
- Old Icelandic Dictionary
- Old Norwegian Dictionary
- Old Swedish Dictionary
- Old Danish Dictionary

## Build

Run in norsedictionaries folder:

`./gradlew run`

The script will take some minutes. It will generate all SQLite DB files in the `resources` folder. Original JSON files used to populate the database are in the same folder.

## Dictionaries in other formats

The dictionaries are also available as standalone websites. This SQLite dataset is provided as additional dataformat, should one have need for these as a database.

- [Old Norse Dictionary](https://cleasby-vigfusson-dictionary.vercel.app/)
- [Old Icelandic Dictionary](https://old-icelandic.vercel.app/)
- [Old Norwegian Dictionary](https://old-norwegian-dictionary.vercel.app/)
- [Old Swedish Dictionary](https://old-swedish-dictionary.vercel.app/)
- [Old Danish Dictionary](https://old-danish-dictionary.vercel.app/)
