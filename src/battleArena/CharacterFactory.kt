package com.android.BattleArena

object CharacterFactory {
    // Toutes les touches de type acceptées (en majuscules). */
    val validTypes = listOf("WARRIOR", "MAGUS", "COLOSSUS", "DWARF")

    // Crée la sous-classe [Character] appropriée pour la [typeKey] donnée.
    // 1. @param typeKey: l'un des types suivants: «WARRIOR», «MAGUS», «COLOSSUS», «DWARF» (insensible à la casse)
    // 2. @param name: le nom unique à attribuer au nouveau personnage
    // @return: un personnage nouvellement créé du type demandé
    // @throws IllegalArgumentException: si la clé de type n'est pas reconnue

    fun create(typeKey: String, name: String): Character = when (typeKey.uppercase()) {
        "WARRIOR"  -> Warrior(name)
        "MAGUS"    -> Magus(name)
        "COLOSSUS" -> Colossus(name)
        "DWARF"    -> Dwarf(name)
        else       -> throw IllegalArgumentException("Unknown character type: $typeKey")
    }

    /** Imprime un tableau formaté de tous les types de caractères disponibles. */
    fun printTypeTable() {
        println()
        println("  +-----------+-----------------------------+---------+--------------+")
        println("  | Type      | Description                 |   HP    | Weapon Power |")
        println("  +-----------+-----------------------------+---------+--------------+")
        println("  | %-9s | %-27s | %-7d | %-12d |".format("Warrior",  "Balanced fighter",      Warrior.BASE_HP,  Warrior.WEAPON_POWER))
        println("  | %-9s | %-27s | %-7d | %-12d |".format("Magus",    "Healer + attacker",     Magus.BASE_HP,    Magus.WEAPON_POWER))
        println("  | %-9s | %-27s | %-7d | %-12d |".format("Colossus", "Tank – very high HP",   Colossus.BASE_HP, Colossus.WEAPON_POWER))
        println("  | %-9s | %-27s | %-7d | %-12d |".format("Dwarf",    "Glass cannon – low HP", Dwarf.BASE_HP,    Dwarf.WEAPON_POWER))
        println("  +-----------+-----------------------------+---------+--------------+")
        println()
    }
}