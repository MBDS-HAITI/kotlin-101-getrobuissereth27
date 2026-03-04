package com.android.BattleArena

class GameSetup (private val readLine: () -> String?) {

    companion object {
        // Nombre de personnages que chaque joueur doit créer.
        const val TEAM_SIZE = 3
    }

    // Ensemble global de tous les noms déjà pris (---les deux équipes, en minuscules).
    private val usedNames = mutableSetOf<String>()

    // Lance la configuration interactive pour les deux joueurs.
    // 1. @param player1 Premier joueur (déjà nommé)
    // 2. @param player2 Deuxième joueur (déjà nommé)
    fun setup(player1: Player, player2: Player) {
        println("\n=== BATTLE ARENA – Team Setup ===\n")
        CharacterFactory.printTypeTable()

        buildTeam(player1)
        buildTeam(player2)
    }

    // Constitue de manière interactive une équipe complète pour le joueur donné.
    private fun buildTeam(player: Player) {
        println("\n--- ${player.name}, create your team ---")

        // Track types already chosen by THIS player (no duplicates per team)
        val usedTypes = mutableSetOf<String>()

        repeat(TEAM_SIZE) { index ->
            println("\n  Character ${index + 1} of $TEAM_SIZE:")

            val typeKey = promptType(usedTypes)
            usedTypes.add(typeKey)

            val name = promptName()
            val character = CharacterFactory.create(typeKey, name)
            player.addCharacter(character)
            usedNames.add(name.lowercase())

            println("   Created: $character")
        }

        println("\n${player.name}'s team is ready!")
    }

    // 1. Invite le joueur à choisir un type valide qui ne fait pas déjà partie de son équipe.
    // 2. @param usedTypes types déjà choisis par ce joueur
    // 3. @return clé de type validée, en majuscules

    private fun promptType(usedTypes: Set<String>): String {
        while (true) {
            val available = CharacterFactory.validTypes.filter { it !in usedTypes }
            val options   = available.joinToString(", ") { it.lowercase().replaceFirstChar { c -> c.uppercase() } }
            print("  Choose type [$options]: ")

            val input = readLine()?.trim()?.uppercase() ?: continue

            when {
                input !in CharacterFactory.validTypes -> println("  ! Invalid type. Please try again.")
                input in usedTypes                    -> println("  ! You already have a ${input.lowercase().replaceFirstChar { it.uppercase() }} in your team.")
                else                                  -> return input
            }
        }
    }

    // Invite le joueur à saisir un nom de personnage unique.
    // @return nom validé et tronqué
    private fun promptName(): String {
        while (true) {
            print("  Enter a unique name for this character: ")
            val input = readLine()?.trim() ?: continue

            when {
                input.isEmpty()                      -> println("  ! Name cannot be empty.")
                input.lowercase() in usedNames       -> println("  ! That name is already taken. Choose another.")
                else                                 -> return input
            }
        }
    }
}
